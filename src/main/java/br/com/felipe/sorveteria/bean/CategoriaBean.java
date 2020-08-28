package br.com.felipe.sorveteria.bean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.felipe.sorveteria.DAO.CategoriaDAO;
import br.com.felipe.sorveteria.DAO.ProdutoDAO;
import br.com.felipe.sorveteria.domain.Categoria;
import br.com.felipe.sorveteria.domain.Produto;

@Named
@ViewScoped
public class CategoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private Categoria categoria;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private List<Produto> produtos = new ArrayList<>();
	private List<Categoria> categorias = new ArrayList<>();
	
	public Produto getProduto() {
		return produto;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
	
	public void novo() {
		try
		{
			categoria = new Categoria();
			produtos = produtoDAO.Listar("nome");
		}
		catch(RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Gerar os dados!!!");
			e.printStackTrace();
		}
		
	}
	@PostConstruct
	public void Listar() {
		try {
			categorias = categoriaDAO.Listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Listar os dados!!!");
			e.printStackTrace();
		}
	}

	public void Salvar() {
		try {
			categoriaDAO.Merge(categoria);
			novo();
			categorias = categoriaDAO.Listar("nome");
			produtos = produtoDAO.Listar("nome");
			Messages.addGlobalInfo("Salvo com Sucesso!!!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Salvar os dados!!!");
			e.printStackTrace();
		}
	}
	public void Excluir(ActionEvent e) {
		
		try
		{
			categoria = (Categoria) e.getComponent().getAttributes().get("cidadeSelecionado");
			categoriaDAO.Excluir(categoria);
			categorias = categoriaDAO.Listar("nome");
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
			categoria = (Categoria) e.getComponent().getAttributes().get("categoriaSelecionado");
			produtos = produtoDAO.Listar("nome");
		}
		catch(RuntimeException err)
		{
			Messages.addGlobalError("Erro ao tentar Editar  os dados!!!");
			err.printStackTrace();
		}
		
	}
}
