package br.com.felipe.sorveteria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.felipe.sorveteria.DAO.CidadeDAO;
import br.com.felipe.sorveteria.DAO.EstadoDAO;
import br.com.felipe.sorveteria.domain.Cidade;
import br.com.felipe.sorveteria.domain.Estado;

@Named
@ViewScoped
public class CidadeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Cidade cidade;
	private CidadeDAO cidadeDAO = new CidadeDAO();
	private EstadoDAO estadoDAO = new EstadoDAO();
	private List<Cidade> cidades = new ArrayList<>();
	private List<Estado> estados = new ArrayList<>();
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	public void novo() {
		try
		{
			cidade = new Cidade();
			estados = estadoDAO.Listar("nome");
		}
		catch(RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Gerar os dados!!!");
			e.printStackTrace();
		}
		
	}
	@PostConstruct
	public void Listar() {
		try {
			cidades = cidadeDAO.Listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Listar os dados!!!");
			e.printStackTrace();
		}
	}

	public void Salvar() {
		try {
			cidadeDAO.Merge(cidade);
			novo();
			cidades = cidadeDAO.Listar();
			estados = estadoDAO.Listar("nome");
			Messages.addGlobalInfo("Salvo com Sucesso!!!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Salvar os dados!!!");
			e.printStackTrace();
		}
	}
	public void Excluir(ActionEvent e) {
		
		try
		{
			cidade = (Cidade) e.getComponent().getAttributes().get("cidadeSelecionado");
			cidadeDAO.Excluir(cidade);
			cidades = cidadeDAO.Listar();
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
			cidade = (Cidade) e.getComponent().getAttributes().get("cidadeSelecionado");
			estados = estadoDAO.Listar();
		}
		catch(RuntimeException err)
		{
			Messages.addGlobalError("Erro ao tentar Editar  os dados!!!");
			err.printStackTrace();
		}
		
	}
}
