package br.com.felipe.sorveteria.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_venda")
public class ItemVenda implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_venda_id")
	private Integer item_venda_id;
	
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal precoParcial;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "produto_id", nullable = false)
	private Produto produto;
	
	
	@ManyToOne
	@JoinColumn(name = "venda_id", referencedColumnName = "venda_id", nullable = false)
	private Venda venda;


	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Integer getItem_venda_id() {
		return item_venda_id;
	}

	public void setItem_venda_id(Integer item_venda_id) {
		this.item_venda_id = item_venda_id;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoParcial() {
		return precoParcial;
	}

	public void setPrecoParcial(BigDecimal precoParcial) {
		this.precoParcial = precoParcial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item_venda_id == null) ? 0 : item_venda_id.hashCode());
		result = prime * result + ((precoParcial == null) ? 0 : precoParcial.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenda other = (ItemVenda) obj;
		if (item_venda_id == null) {
			if (other.item_venda_id != null)
				return false;
		} else if (!item_venda_id.equals(other.item_venda_id))
			return false;
		if (precoParcial == null) {
			if (other.precoParcial != null)
				return false;
		} else if (!precoParcial.equals(other.precoParcial))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemVenda [item_venda_id=" + item_venda_id + ", quantidade=" + quantidade + ", precoParcial="
				+ precoParcial + ", produto=" + produto + ", venda=" + venda + "]";
	}
	

}
