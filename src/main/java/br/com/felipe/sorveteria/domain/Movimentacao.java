package br.com.felipe.sorveteria.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "movimentacao")
public class Movimentacao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "movimentacao_id")
	private Integer movimentacao_id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "caixa_id", referencedColumnName = "caixa_id", nullable = false)
	private Caixa caixa;

	public Integer getMovimentacao_id() {
		return movimentacao_id;
	}

	public void setMovimentacao_id(Integer movimentacao_id) {
		this.movimentacao_id = movimentacao_id;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caixa == null) ? 0 : caixa.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((movimentacao_id == null) ? 0 : movimentacao_id.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Movimentacao other = (Movimentacao) obj;
		if (caixa == null) {
			if (other.caixa != null)
				return false;
		} else if (!caixa.equals(other.caixa))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (movimentacao_id == null) {
			if (other.movimentacao_id != null)
				return false;
		} else if (!movimentacao_id.equals(other.movimentacao_id))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movimentacao [movimentacao_id=" + movimentacao_id + ", horario=" + horario + ", valor=" + valor
				+ ", caixa=" + caixa + "]";
	}
}
