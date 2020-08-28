package br.com.felipe.sorveteria.DAO;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.felipe.sorveteria.domain.Caixa;
import br.com.felipe.sorveteria.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa>{
	@SuppressWarnings("deprecation")
	public Caixa buscar(Date dataAbertura) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Caixa.class);
			consulta.add(Restrictions.eq("dataAbertura", dataAbertura));
			Caixa resultado = (Caixa) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
