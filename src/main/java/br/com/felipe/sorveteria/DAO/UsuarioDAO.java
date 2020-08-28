package br.com.felipe.sorveteria.DAO;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.felipe.sorveteria.domain.Usuario;
import br.com.felipe.sorveteria.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	
	public void salvarCripto(Usuario u) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		try {
			trans = sessao.beginTransaction();
			SimpleHash hash = new SimpleHash("md5", u.getNcp_senha());
			u.setSenha(hash.toHex());
			sessao.merge(u);
			trans.commit();
		}
		catch(RuntimeException e){
			throw e;
		}
		finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("deprecation")
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			
			consulta.add(Restrictions.eq("p.cpf", cpf));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
