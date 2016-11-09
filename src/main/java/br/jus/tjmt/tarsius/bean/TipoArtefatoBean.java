package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.TipoArtefatoDAO;
import br.jus.tjmt.tarsius.domain.TipoArtefato;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoArtefatoBean implements Serializable {
	private TipoArtefato tipoArtefato;
	private List<TipoArtefato> tipoArtefatos;

	public TipoArtefato getTipoArtefato() {
		return tipoArtefato;
	}

	public void setTipoArtefato(TipoArtefato tipoArtefato) {
		this.tipoArtefato = tipoArtefato;
	}

	public List<TipoArtefato> getTipoArtefatos() {
		return tipoArtefatos;
	}

	public void setTipoArtefatos(List<TipoArtefato> tipoArtefatos) {
		this.tipoArtefatos = tipoArtefatos;
	}

	// Chama automaticamente o método Listar
	@PostConstruct
	public void listar() {
		try {
			TipoArtefatoDAO tipoArtefatoDAO = new TipoArtefatoDAO();
			tipoArtefatos = tipoArtefatoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar carregar listagem de Tipo de Artefatos.");
			erro.printStackTrace();
		}
	}

	// Instancia a classe para novo registro
	public void novo() {
		tipoArtefato = new TipoArtefato();
	}

	public void salvar() {
		try {
			TipoArtefatoDAO tipoArtefatoDAO = new TipoArtefatoDAO();
			tipoArtefatoDAO.salvar(tipoArtefato);
			novo(); // Limpa a tela após inserção dos dados
			Messages.addGlobalInfo("Tipo de artefato salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar o tipo de artefato.");
			erro.printStackTrace();
		}
	}
}
