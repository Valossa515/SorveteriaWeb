package br.com.felipe.sorveteria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.felipe.sorveteria.DAO.ProdutoDAO;
import br.com.felipe.sorveteria.DAO.FabricanteDAO;
import br.com.felipe.sorveteria.domain.Produto;
import br.com.felipe.sorveteria.domain.Fabricante;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private Fabricante fabricante = new Fabricante();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private FabricanteDAO fabricanteDAO = new FabricanteDAO();
	private List<Produto> produtos = new ArrayList<>();
	private List<Fabricante> fabricantes = new ArrayList<>();
	public Produto getProduto() {
		return produto;
	}
	

	public Fabricante getFabricante() {
		return fabricante;
	}


	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	public void novo() {
		try
		{
			produto = new Produto();
			fabricantes = fabricanteDAO.Listar("nome");
		}
		catch(RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Gerar os dados!!!");
			e.printStackTrace();
		}
		
	}
	@PostConstruct
	public void Listar() {
		try {
			produtos = produtoDAO.Listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Listar os dados!!!");
			e.printStackTrace();
		}
	}

	public void Salvar() {
		try {
			produtoDAO.Merge(produto);
			novo();
			produtos = produtoDAO.Listar();
			fabricantes = fabricanteDAO.Listar("nome");
			Messages.addGlobalInfo("Salvo com Sucesso!!!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Salvar os dados!!!");
			e.printStackTrace();
		}
	}
	public void Excluir(ActionEvent e) {
		
		try
		{
			produto = (Produto) e.getComponent().getAttributes().get("produtoSelecionado");
			produtoDAO.Excluir(produto);
			produtos = produtoDAO.Listar();
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
			produto = (Produto) e.getComponent().getAttributes().get("produtoSelecionado");
			fabricantes = fabricanteDAO.Listar();
		}
		catch(RuntimeException err)
		{
			Messages.addGlobalError("Erro ao tentar Editar os dados!!!");
			err.printStackTrace();
		}
		
	}
}
