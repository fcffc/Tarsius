package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

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
			tipoArtefatoDAO.merge(tipoArtefato);
			// Reinstancia o tipoArtefato
			novo(); // Limpa a tela após inserção dos dados
			// Recarrega a listagem de tipo de artefato
			tipoArtefatos = tipoArtefatoDAO.listar();
			Messages.addGlobalInfo("Tipo de artefato salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar o tipo de artefato.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona o cargo a ser excluído
			tipoArtefato = (TipoArtefato) evento.getComponent().getAttributes().get("tipoArtefatoSelecionado");
			// Exclui tipoArtefato
			TipoArtefatoDAO tipoArtefatoDAO = new TipoArtefatoDAO();
			tipoArtefatoDAO.excluir(tipoArtefato);
			// Recarrega a listagem de tipoArtefato
			tipoArtefatos = tipoArtefatoDAO.listar("sigla");
			Messages.addGlobalInfo("Tipo Artefato: " + tipoArtefato.getSigla() + " excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir tipo de artefato.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o tipoArtefato a ser editado
			tipoArtefato = (TipoArtefato) evento.getComponent().getAttributes().get("tipoArtefatoSelecionado");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}