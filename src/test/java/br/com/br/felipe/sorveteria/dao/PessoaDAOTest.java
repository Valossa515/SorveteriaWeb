package br.com.br.felipe.sorveteria.dao;

import org.junit.Test;

import br.com.felipe.sorveteria.DAO.CidadeDAO;
import br.com.felipe.sorveteria.DAO.PessoaDAO;
import br.com.felipe.sorveteria.domain.Cidade;
import br.com.felipe.sorveteria.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	public void salvar() {
	
		Integer cidade_id = 1;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.Buscar(cidade_id);
		Pessoa p = new Pessoa();
		PessoaDAO pdao = new PessoaDAO(); 
		p.setNome("Felipe Martins");
		p.setCpf("442.446.478-48");
		p.setRg("49.447.615-1");
		p.setRua("Rua Perrella");
		p.setNumero((short) 145);
		p.setBairro("Fundação");
		p.setComplemento("apto 11");
		p.setCep("09.520.660");
		p.setCelular("(11) 95432-3543");
		p.setTelefone("(11) 4221-7671");
		p.setCidade(cidade);
		pdao.Salvar(p);
	}
}
