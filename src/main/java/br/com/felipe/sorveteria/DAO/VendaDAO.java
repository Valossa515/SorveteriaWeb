package br.com.felipe.sorveteria.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.felipe.sorveteria.domain.ItemVenda;
import br.com.felipe.sorveteria.domain.Produto;
import br.com.felipe.sorveteria.domain.Venda;
import br.com.felipe.sorveteria.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {
	@SuppressWarnings("deprecation")
	public void salvar(Venda venda, List<ItemVenda> itensVenda){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
		
			sessao.save(venda);
			
			for(int posicao = 0; posicao < itensVenda.size(); posicao++){
				ItemVenda itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);
				
				sessao.save(itemVenda);
				
				Produto p = itemVenda.getProduto();
				int qtd_atual = p.getQuantidade() - itemVenda.getQuantidade();
				if(qtd_atual >= 0) {
					p.setQuantidade( new Short( qtd_atual + ""));
					sessao.update(p);
				}
				else {
					throw new RuntimeException("Quantidade insuficiente em Estoque!!!");
				}
			}
			
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
