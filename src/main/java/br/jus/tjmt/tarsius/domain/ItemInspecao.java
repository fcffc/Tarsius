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
public class ItemInspecao extends GenericDomain {
	@ManyToOne
	private EvidenciaSistema evidenciaAplic;
	@ManyToOne
	private EvidenciaProcesso evidenciaProc;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private ItemChecklist itemPergunta;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Pessoa responsavel;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Gestor gestor;
	@OneToOne
	private Nomenclatura tipoNConformidade;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusInspecao statusInspecao;

	public EvidenciaSistema getEvidenciaAplic() {
		return evidenciaAplic;
	}

	public void setEvidenciaAplic(EvidenciaSistema evidenciaAplic) {
		this.evidenciaAplic = evidenciaAplic;
	}

	public EvidenciaProcesso getEvidenciaProc() {
		return evidenciaProc;
	}

	public void setEvidenciaProc(EvidenciaProcesso evidenciaProc) {
		this.evidenciaProc = evidenciaProc;
	}

	public ItemChecklist getItemPergunta() {
		return itemPergunta;
	}

	public void setItemPergunta(ItemChecklist itemPergunta) {
		this.itemPergunta = itemPergunta;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public Nomenclatura getTipoNConformidade() {
		return tipoNConformidade;
	}

	public void setTipoNConformidade(Nomenclatura tipoNConformidade) {
		this.tipoNConformidade = tipoNConformidade;
	}

	public StatusInspecao getStatusInspecao() {
		return statusInspecao;
	}

	public void setStatusInspecao(StatusInspecao statusInspecao) {
		this.statusInspecao = statusInspecao;
	}
}