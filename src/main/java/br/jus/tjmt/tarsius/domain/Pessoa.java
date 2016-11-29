package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Pessoa extends GenericDomain {
	@Column(length = 80, nullable = false) // Não pode ser nulo
	private String nome;
	@Column(length = 20, nullable = false)
	private String matricula;
	@Column(length = 14)
	private String telefone;
	@Column(length = 14)
	private String celular;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Cargo cargo;
	@Column(length = 60, nullable = false) // Não pode ser nulo
	private String email;
	@Column(length = 60)
	private String formacao;
	
	private Boolean situacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public Boolean getSituacao() {
		return situacao;
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

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}
}
