package br.jus.tjmt.tarsius.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class EvidenciaPergunta extends GenericDomain {
	@ManyToOne
	private EvidenciaSistema evidenciaAplic;
	@ManyToOne
	private EvidenciaProcesso evidenciaProc;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private ItemChecklist pergunta;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private ItemInspecao itemInspecao;

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

	public ItemChecklist getPergunta() {
		return pergunta;
	}

	public void setPergunta(ItemChecklist pergunta) {
		this.pergunta = pergunta;
	}

	public ItemInspecao getItemInspecao() {
		return itemInspecao;
	}

	public void setItemInspecao(ItemInspecao itemInspecao) {
		this.itemInspecao = itemInspecao;
	}
}