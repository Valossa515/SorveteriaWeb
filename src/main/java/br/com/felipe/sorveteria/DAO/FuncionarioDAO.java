package br.com.felipe.sorveteria.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.felipe.sorveteria.domain.Funcionario;
import br.com.felipe.sorveteria.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario> {
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Funcionario> listarOrdenado() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			consulta.createAlias("pessoa", "p");
			consulta.addOrder(Order.asc("p.nome"));
			List<Funcionario> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
