package br.com.felipe.sorveteria.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.felipe.sorveteria.domain.Cidade;
import br.com.felipe.sorveteria.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Cidade> buscarporEstado(Integer estado_id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = sessao.createCriteria(Cidade.class);
			cri.add(Restrictions.eq("estado.estado_id", estado_id));
			cri.addOrder(Order.asc("nome"));
			List<Cidade> res = cri.list();
			return res;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
}
