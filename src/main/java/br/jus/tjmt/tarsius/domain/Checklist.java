package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Fluxo fluxo;
	@Column(nullable = false)
	private Boolean situacao;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private ItemChecklist itemPergunta;
	@Column(nullable = false)
	private String usuarioLogado;

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

	public Fluxo getFluxo() {
		return fluxo;
	}

	public void setFluxo(Fluxo fluxo) {
		this.fluxo = fluxo;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public ItemChecklist getItemPergunta() {
		return itemPergunta;
	}

	public void setItemPergunta(ItemChecklist itemPergunta) {
		this.itemPergunta = itemPergunta;
	}

	public String getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
