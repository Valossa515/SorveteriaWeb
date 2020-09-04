package br.com.felipe.sorveteria.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.felipe.sorveteria.DAO.EventoDAO;
import br.com.felipe.sorveteria.DAO.FuncionarioDAO;
import br.com.felipe.sorveteria.domain.Evento;
import br.com.felipe.sorveteria.domain.Funcionario;

@Named
@ViewScoped
public class EventoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Evento evento;
	private ScheduleModel eventos;
	private List<Funcionario> funcionarios;
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public ScheduleModel getEventos() {
		return eventos;
	}
	public void setEventos(ScheduleModel eventos) {
		this.eventos = eventos;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	@PostConstruct
	public void listar() {
		eventos = new DefaultScheduleModel();
	}
	public LocalDateTime getRandomDateTime(LocalDateTime base) {
        LocalDateTime dateTime = base.withMinute(0).withSecond(0).withNano(0);
        return dateTime.plusDays(((int) (Math.random()*30)));
    }
     
	public void novo(SelectEvent<LocalDateTime> e) {
		evento = new Evento();
		evento.setInicio(e.getObject());
		evento.setFim(e.getObject());
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.Listar();
	}
	public void salvar() {
		EventoDAO eventoDAO = new EventoDAO();
		eventoDAO.Salvar(evento);
		Messages.addFlashGlobalInfo("Evento do caixa salvo com sucesso!!!");
	}
}
