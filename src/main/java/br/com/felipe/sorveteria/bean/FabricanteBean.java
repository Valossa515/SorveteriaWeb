package br.com.felipe.sorveteria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.felipe.sorveteria.DAO.FabricanteDAO;
import br.com.felipe.sorveteria.domain.Fabricante;

@Named
@ViewScoped
public class FabricanteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Fabricante fabricante;
	private FabricanteDAO fabricanteDAO = new FabricanteDAO();
	private List<Fabricante> fabricantes = new ArrayList<>();

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public void novo() {
		fabricante = new Fabricante();
	}
	@PostConstruct
	public void Listar() {
		try {
			fabricantes = fabricanteDAO.Listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Listar os dados!!!");
			e.printStackTrace();
		}
	}

	public void Salvar() {
		try {
			fabricanteDAO.Merge(fabricante);
			novo();
			fabricantes = fabricanteDAO.Listar();
			Messages.addGlobalInfo("Salvo com Sucesso!!!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Salvar os dados!!!");
			e.printStackTrace();
		}
	}
	public void Excluir(ActionEvent e) {
		
		try
		{
			fabricante = (Fabricante) e.getComponent().getAttributes().get("fabricanteSelecionado");
			fabricanteDAO.Excluir(fabricante);
			fabricantes = fabricanteDAO.Listar();
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
			fabricante = (Fabricante) e.getComponent().getAttributes().get("fabricanteSelecionado");
		}
		catch(RuntimeException err)
		{
			Messages.addGlobalError("Erro ao tentar Editar  os dados!!!");
			err.printStackTrace();
		}
		
	}
}
