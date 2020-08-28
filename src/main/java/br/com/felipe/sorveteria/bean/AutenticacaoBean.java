package br.com.felipe.sorveteria.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.felipe.sorveteria.DAO.UsuarioDAO;
import br.com.felipe.sorveteria.domain.Pessoa;
import br.com.felipe.sorveteria.domain.Usuario;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public void autenticar() throws IOException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());
		
		if(usuarioLogado == null){
			Messages.addGlobalError("CPF e/ou senha incorretos");
			return;
		}
		
		Faces.redirect("./pages/Principal.xhtml");
	}
	public boolean temPermissoes(List<String> permissoes) {
		
		for(String permissao : permissoes) {
			if(usuarioLogado.getTipo() == permissao.charAt(0)) {
				return true;
			}
		}
		return false;
	}
	public void deslogar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());;
		usuarioLogado = null;
		Faces.redirect("./pages/Autenticacao.xhtml");
		return;
	}
}
