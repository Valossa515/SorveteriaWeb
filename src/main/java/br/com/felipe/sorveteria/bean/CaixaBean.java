package br.com.felipe.sorveteria.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.felipe.sorveteria.DAO.FuncionarioDAO;
import br.com.felipe.sorveteria.domain.Caixa;
import br.com.felipe.sorveteria.domain.Funcionario;

@SuppressWarnings("deprecation")
@Named
@ViewScoped
public class CaixaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Caixa caixa;

	private ScheduleModel caixas;
	private List<Funcionario> funcionarios;

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public ScheduleModel getCaixas() {
		return caixas;
	}

	public void setCaixas(ScheduleModel caixas) {
		this.caixas = caixas;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PostConstruct
	public void listar() {
		caixas = new DefaultScheduleModel();
	}

	public void novo(SelectEvent evento) {
		caixa = new Caixa();
		caixa.setDataAbertura((Date) evento.getObject());

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.Listar();
	}
}
