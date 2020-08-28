package br.com.felipe.sorveteria.domain;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "funcionarios")
public class Funcionario implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "funcionario_id")
	private Integer funcionario_id;
	@Column(length = 15, nullable = false)
	private String carteiraTrabalho;
	@Column(nullable = false, unique = true)
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	@OneToOne
	@JoinColumn(name = "pessoa_id", referencedColumnName = "pessoa_id", nullable = false)
	private Pessoa pessoa;
	@Column(nullable = false)
	private Boolean ativo;
	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = "Não";

		if (ativo) {
			ativoFormatado = "Sim";
		}
		return ativoFormatado;
	}
	public Integer getFuncionario_id() {
		return funcionario_id;
	}
	public void setFuncionario_id(Integer funcionario_id) {
		this.funcionario_id = funcionario_id;
	}
	public String getCarteiraTrabalho() {
		return carteiraTrabalho;
	}
	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carteiraTrabalho == null) ? 0 : carteiraTrabalho.hashCode());
		result = prime * result + ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
		result = prime * result + ((funcionario_id == null) ? 0 : funcionario_id.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (carteiraTrabalho == null) {
			if (other.carteiraTrabalho != null)
				return false;
		} else if (!carteiraTrabalho.equals(other.carteiraTrabalho))
			return false;
		if (dataAdmissao == null) {
			if (other.dataAdmissao != null)
				return false;
		} else if (!dataAdmissao.equals(other.dataAdmissao))
			return false;
		if (funcionario_id == null) {
			if (other.funcionario_id != null)
				return false;
		} else if (!funcionario_id.equals(other.funcionario_id))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Funcionario [funcionario_id=" + funcionario_id + ", carteiraTrabalho=" + carteiraTrabalho
				+ ", dataAdmissao=" + dataAdmissao + ", pessoa=" + pessoa + "]";
	}
}
