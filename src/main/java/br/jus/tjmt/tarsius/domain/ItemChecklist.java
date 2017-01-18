package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.jus.tjmt.tarsius.enumeracao.SecaoProcesso;
import br.jus.tjmt.tarsius.enumeracao.SecaoSistema;
import br.jus.tjmt.tarsius.enumeracao.TipoPergunta;

@SuppressWarnings("serial")
@Entity
public class ItemChecklist extends GenericDomain {
	@Column(nullable = false)
	private String pergunta;
	@Enumerated(EnumType.STRING)
	private SecaoProcesso secaoProc;
	@Enumerated(EnumType.STRING)
	private SecaoSistema secaoAplic;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Checklist checklist;
	@Column(nullable = true)
	private String usuarioLogado;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoPergunta tipo;

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public SecaoProcesso getSecaoProc() {
		return secaoProc;
	}

	public void setSecaoProc(SecaoProcesso secaoProc) {
		this.secaoProc = secaoProc;
	}

	public SecaoSistema getSecaoAplic() {
		return secaoAplic;
	}

	public void setSecaoAplic(SecaoSistema secaoAplic) {
		this.secaoAplic = secaoAplic;
	}

	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}

	public String getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public TipoPergunta getTipo() {
		return tipo;
	}

	public void setTipo(TipoPergunta tipo) {
		this.tipo = tipo;
	}
}