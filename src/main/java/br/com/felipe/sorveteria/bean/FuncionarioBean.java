package br.com.felipe.sorveteria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.felipe.sorveteria.DAO.PessoaDAO;
import br.com.felipe.sorveteria.DAO.FuncionarioDAO;
import br.com.felipe.sorveteria.domain.Pessoa;
import br.com.felipe.sorveteria.domain.Funcionario;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Funcionario funcionario;
	private PessoaDAO pessoaDAO = new PessoaDAO();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private List<Pessoa> pessoas = new ArrayList<>();
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public void novo() {
		try {
			funcionario = new Funcionario();
			pessoas = pessoaDAO.Listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			erro.printStackTrace();
		}
		
	}
	@PostConstruct
	public void Listar() {
		try {
			funcionarios = funcionarioDAO.Listar("dataAdmissao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Listar os dados!!!");
			e.printStackTrace();
		}
	}

	public void Salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.Merge(funcionario);;
			
			funcionario = new Funcionario();
			funcionarios = funcionarioDAO.Listar("dataAdmissao");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
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
			funcionario = (Funcionario) e.getComponent().getAttributes().get("funcionarioSelecionado");
			funcionarioDAO.Excluir(funcionario);
			funcionarios = funcionarioDAO.Listar("dataAdmissao");
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
			funcionario = (Funcionario) e.getComponent().getAttributes().get("funcionarioSelecionado");
			pessoas = pessoaDAO.Listar();
		}
		catch(RuntimeException err)
		{
			Messages.addGlobalError("Erro ao tentar Editar os dados!!!");
			err.printStackTrace();
		}
		
	}
}
