package br.jus.tjmt.tarsius.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
}
