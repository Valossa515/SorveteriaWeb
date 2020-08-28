package br.com.felipe.sorveteria.DAO;

import java.lang.reflect.ParameterizedType;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.felipe.sorveteria.util.HibernateUtil;

public class GenericDAO<Entidade> {
	private Class<Entidade> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	public void Salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		
		try
		{
			trans = sessao.beginTransaction();
			sessao.save(entidade);
			trans.commit();
		}
		catch(RuntimeException e)
		{
			if(trans != null) {
				trans.rollback();
			}
			throw e;
		}
		
		finally
		{
			sessao.close();
		}
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Entidade> Listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria cri = sessao.createCriteria(classe);
			List<Entidade> res = cri.list();
			return res;
		}
		catch(RuntimeException e) {
			throw e;
		}
		finally{
			sessao.close();
		}
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Entidade> Listar(String ordem){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria cri = sessao.createCriteria(classe);
			cri.addOrder(Order.asc(ordem));
			List<Entidade> res = cri.list();
			return res;
		}
		catch(RuntimeException e) {
			throw e;
		}
		finally{
			sessao.close();
		}
	}
	public List<Entidade> Listarnovo(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			CriteriaQuery<Entidade> criteriaQuery = sessao.getCriteriaBuilder().createQuery(classe);
			criteriaQuery.from(classe);
			List<Entidade> entidade = sessao.createQuery(criteriaQuery).getResultList();
			return  entidade;
		}
		catch(RuntimeException e) {
			throw e;
		}
		finally{
			sessao.close();
		}
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Entidade Buscar(Integer codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria cri = sessao.createCriteria(classe);
			cri.add(Restrictions.idEq(codigo));
			Entidade res = (Entidade) cri.uniqueResult();
			return res;
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		finally
		{
			sessao.close();
		}
	}
	public void Excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		try
		{
			trans = sessao.beginTransaction();
			sessao.delete(entidade);
			trans.commit();
		}
		catch(RuntimeException e)
		{
			if(trans != null) {
				trans.rollback();
			}
			throw e;
		}
		finally
		{
			sessao.close();
		}
	}
	public void Editar(Entidade entidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		try
		{
			trans = sessao.beginTransaction();
			sessao.update(entidade);
			trans.commit();
		}
		catch(RuntimeException e)
		{
			if(trans != null) {
				trans.rollback();
			}
			throw e;
		}
		finally
		{
			sessao.close();
		}
	}
	public void Merge(Entidade entidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		
		try
		{
			trans = sessao.beginTransaction();
			sessao.merge(entidade);
			trans.commit();
		}
		catch(RuntimeException e)
		{
			if(trans != null) {
				trans.rollback();
			}
			throw e;
		}
		
		finally
		{
			sessao.close();
		}
	}
}
