package br.com.br.felipe.sorveteria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import br.com.felipe.sorveteria.DAO.PessoaDAO;
import br.com.felipe.sorveteria.DAO.UsuarioDAO;
import br.com.felipe.sorveteria.domain.Pessoa;
import br.com.felipe.sorveteria.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	public void salvar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.Buscar(3);
		
		System.out.println("Pessoa Encontrada");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setNcp_senha("Felipe51");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getNcp_senha());
		usuario.setSenha(hash.toHex());
		
		usuario.setTipo('A');
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.Salvar(usuario);
		
		System.out.println("Usuário salvo com sucesso.");
	}
}
