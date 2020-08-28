package br.com.felipe.sorveteria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.felipe.sorveteria.DAO.EstadoDAO;
import br.com.felipe.sorveteria.domain.Estado;

@Named
@ViewScoped
public class EstadoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Estado estado;
	private EstadoDAO estadoDAO = new EstadoDAO();
	private List<Estado> estados = new ArrayList<>();

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void novo() {
		estado = new Estado();
	}
	@PostConstruct
	public void Listar() {
		try {
			estados = estadoDAO.Listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Listar os dados!!!");
			e.printStackTrace();
		}
	}

	public void Salvar() {
		try {
			estadoDAO.Merge(estado);
			novo();
			estados = estadoDAO.Listar();
			Messages.addGlobalInfo("Salvo com Sucesso!!!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Salvar os dados!!!");
			e.printStackTrace();
		}
	}
	public void Excluir(ActionEvent e) {
		
		try
		{
			estado = (Estado) e.getComponent().getAttributes().get("estadoSelecionado");
			estadoDAO.Excluir(estado);
			estados = estadoDAO.Listar();
			Messages.addGlobalInfo("Excluído com Sucesso!!!");
		}
		catch(RuntimeException err)
		{
			Messages.addGlobalError("Erro ao tentar Excluir os dados!!!");
			err.printStackTrace();
		}
		
	}
	public void Editar(ActionEvent e) {
		
		try
		{
			estado = (Estado) e.getComponent().getAttributes().get("estadoSelecionado");
		}
		catch(RuntimeException err)
		{
			Messages.addGlobalError("Erro ao tentar Editar os dados!!!");
			err.printStackTrace();
		}
		
	}
}
