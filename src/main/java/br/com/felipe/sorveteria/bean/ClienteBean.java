package br.com.felipe.sorveteria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.felipe.sorveteria.DAO.ClienteDAO;
import br.com.felipe.sorveteria.DAO.PessoaDAO;
import br.com.felipe.sorveteria.domain.Cliente;
import br.com.felipe.sorveteria.domain.Pessoa;

@Named
@ViewScoped
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Cliente cliente;
	
	private PessoaDAO pessoaDAO = new PessoaDAO();
	private ClienteDAO clienteDAO = new ClienteDAO();
	private List<Pessoa> pessoas = new ArrayList<>();
	private List<Cliente> clientes = new ArrayList<>();
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void novo() {
		try {
			cliente = new Cliente();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.Listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			erro.printStackTrace();
		}
		
	}
	@PostConstruct
	public void Listar() {
		try {
			clientes = clienteDAO.Listar("dataCadastro");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Listar os dados!!!");
			e.printStackTrace();
		}
	}

	public void Salvar() {
		try {
			clienteDAO.Merge(cliente);
			cliente = new Cliente();
			clientes = clienteDAO.Listar("dataCadastro");
			pessoas = pessoaDAO.Listar("nome");
			Messages.addGlobalInfo("Salvo com Sucesso!!!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Salvar os dados!!!");
			e.printStackTrace();
		}
	}
	public void Excluir(ActionEvent e) {
		
		try
		{
			cliente = (Cliente) e.getComponent().getAttributes().get("clienteSelecionado");
			clienteDAO.Excluir(cliente);
			clientes = clienteDAO.Listar("dataCadastro");
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
			cliente = (Cliente) e.getComponent().getAttributes().get("clienteSelecionado");
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.Listar("nome");
		}
		catch(RuntimeException err)
		{
			Messages.addGlobalError("Erro ao tentar Editar os dados!!!");
			err.printStackTrace();
		}
		
	}
}
