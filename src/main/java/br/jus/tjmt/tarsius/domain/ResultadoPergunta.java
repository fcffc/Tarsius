package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.jus.tjmt.tarsius.enumeracao.StatusInspecao;

@SuppressWarnings("serial")
@Entity
public class ResultadoPergunta extends GenericDomain {
	private String comentario;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private ItemChecklist pergunta;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Inspecao inspecao;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusInspecao statusInspecao;
	@OneToOne
	private Nomenclatura tipoNConformidade;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Pessoa responsavel;

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public ItemChecklist getPergunta() {
		return pergunta;
	}

	public void setPergunta(ItemChecklist pergunta) {
		this.pergunta = pergunta;
	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public StatusInspecao getStatusInspecao() {
		return statusInspecao;
	}

	public void setStatusInspecao(StatusInspecao statusInspecao) {
		this.statusInspecao = statusInspecao;
	}

	public Nomenclatura getTipoNConformidade() {
		return tipoNConformidade;
	}

	public void setTipoNConformidade(Nomenclatura tipoNConformidade) {
		this.tipoNConformidade = tipoNConformidade;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}
}