package br.com.br.felipe.sorveteria.dao;

import org.junit.Test;

import br.com.felipe.sorveteria.DAO.CidadeDAO;
import br.com.felipe.sorveteria.DAO.EstadoDAO;
import br.com.felipe.sorveteria.domain.Cidade;
import br.com.felipe.sorveteria.domain.Estado;


public class CidadeDAOTest {
	@Test
	public void salvar() {
		Integer estado_id = 1;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.Buscar(estado_id);
		Cidade cidade = new Cidade();
		cidade.setNome("São Caetano do Sul");
		cidade.setEstado(estado);
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.Salvar(cidade);
	}
}
