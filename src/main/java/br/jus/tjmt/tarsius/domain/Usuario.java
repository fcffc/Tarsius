package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.jus.tjmt.tarsius.enumeracao.Papel;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {
	@OneToOne // 1 p/ 1
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Pessoa pessoa;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Papel papel;
	@Column(nullable = true)
	private Boolean notificacao;
	@Column(length = 32, nullable = false)
	private String senha;
	@Transient
	private String senhaSemCriptografia;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public Boolean getNotificacao() {
		return notificacao;
	}

	// Formata True / False para Sim / Não
	@Transient
	public String getNotificacaoFormatada() {
		String notificacaoFormatada = "Não";

		if (notificacao) {
			notificacaoFormatada = "Sim";
		}
		return notificacaoFormatada;
	}

	public void setNotificacao(Boolean notificacao) {
		this.notificacao = notificacao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}
}
