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
import br.com.felipe.sorveteria.DAO.UsuarioDAO;
import br.com.felipe.sorveteria.domain.Pessoa;
import br.com.felipe.sorveteria.domain.Usuario;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Usuario usuario;
	
	private PessoaDAO pessoaDAO = new PessoaDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<Pessoa> pessoas = new ArrayList<>();
	private List<Usuario> usuarios = new ArrayList<>();
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public void novo() {
		try {
			usuario = new Usuario();
			pessoas = pessoaDAO.Listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			erro.printStackTrace();
		}
		
	}
	@PostConstruct
	public void Listar() {
		try {
			usuarios = usuarioDAO.Listar("tipo");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar Listar os dados!!!");
			e.printStackTrace();
		}
	}

	public void Salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvarCripto(usuario);
			usuario = new Usuario();
			usuarios = usuarioDAO.Listar("tipo");
			
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
			usuario = (Usuario) e.getComponent().getAttributes().get("usuarioSelecionado");
			usuarioDAO.Excluir(usuario);
			usuarios = usuarioDAO.Listar("tipo");
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
			usuario = (Usuario) e.getComponent().getAttributes().get("usuarioSelecionado");
			pessoas = pessoaDAO.Listar();
		}
		catch(RuntimeException err)
		{
			Messages.addGlobalError("Erro ao tentar Editar os dados!!!");
			err.printStackTrace();
		}
		
	}
}
