package br.com.br.felipe.sorveteria.dao;

import org.junit.Test;

import br.com.felipe.sorveteria.DAO.EstadoDAO;
import br.com.felipe.sorveteria.domain.Estado;

public class EstadoDAOTest {
	@Test
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("São Paulo");
		estado.setSigla("SP");
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.Salvar(estado);
	}
}
