package br.com.felipe.sorveteria.DAO;

import java.time.LocalDateTime;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.felipe.sorveteria.domain.Evento;
import br.com.felipe.sorveteria.util.HibernateUtil;

public class EventoDAO extends GenericDAO<Evento>{
	@SuppressWarnings("deprecation")
	public Evento buscar(LocalDateTime inicio) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Evento.class);
			consulta.add(Restrictions.eq("inicio", inicio));
			Evento resultado = (Evento) consulta.uniqueResult();
			return resultado;
		}
		catch(RuntimeException e) {
			throw e;
		}
		finally {
			sessao.close();
		}
	}
}
