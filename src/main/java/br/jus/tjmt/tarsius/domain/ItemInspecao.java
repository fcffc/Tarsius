package br.jus.tjmt.tarsius.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class ItemInspecao extends GenericDomain {
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Inspecao inspecao;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private ItemChecklist itemPergunta;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Gestor gestor;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Sistema sistema;

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public ItemChecklist getItemPergunta() {
		return itemPergunta;
	}

	public void setItemPergunta(ItemChecklist itemPergunta) {
		this.itemPergunta = itemPergunta;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
}