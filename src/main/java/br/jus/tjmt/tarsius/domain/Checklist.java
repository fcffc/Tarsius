package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import br.jus.tjmt.tarsius.enumeracao.TipoPergunta;

@SuppressWarnings("serial")
@Entity
public class Checklist extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;
	@Column(length = 20, nullable = false)
	private String versao;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoPergunta tipo;
	@Column(nullable = false)
	private Boolean situacao;

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

	public TipoPergunta getTipo() {
		return tipo;
	}

	public void setTipo(TipoPergunta tipo) {
		this.tipo = tipo;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}
	
	// Formata True / False para Ativo / Inativo
	@Transient
	public String getSituacaoFormatada(){
		String situacaoFormatada = "Inativo";
		if(situacao){
			situacaoFormatada = "Ativo";
		}
		return situacaoFormatada;
	}
}