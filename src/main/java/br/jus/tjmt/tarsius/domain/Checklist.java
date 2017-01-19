package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Checklist extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;
	@Column(length = 20, nullable = false)
	private String versao;
	@Column(nullable = false)
	private Boolean situacao;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira
	private TipoInspecao tipoInspecao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public TipoInspecao getTipoInspecao() {
		return tipoInspecao;
	}

	public void setTipoInspecao(TipoInspecao tipoInspecao) {
		this.tipoInspecao = tipoInspecao;
	}

	// Formata True / False para Ativo / Inativo
	@Transient
	public String getSituacaoFormatada() {
		String situacaoFormatada = "Inativo";
		if (situacao) {
			situacaoFormatada = "Ativo";
		}
		return situacaoFormatada;
	}
}