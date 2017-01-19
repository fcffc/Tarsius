package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.TipoInspecaoDAO;
import br.jus.tjmt.tarsius.domain.TipoInspecao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoInspecaoBean implements Serializable {
	private TipoInspecao tipoInspecao;
	private List<TipoInspecao> tipoInspecaos;

	public TipoInspecao getTipoInspecao() {
		return tipoInspecao;
	}

	public void setTipoInspecao(TipoInspecao tipoInspecao) {
		this.tipoInspecao = tipoInspecao;
	}

	public List<TipoInspecao> getTipoInspecaos() {
		return tipoInspecaos;
	}

	public void setTipoInspecaos(List<TipoInspecao> tipoInspecaos) {
		this.tipoInspecaos = tipoInspecaos;
	}

	// Chama automaticamente o método Listar
	@PostConstruct
	public void listar() {
		try {
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar carregar listagem de Tipo de Inspeção.");
			erro.printStackTrace();
		}
	}

	// Inicia uma nova instancia ao abrir a tela de cadastro/edição
	public void novo() {
		tipoInspecao = new TipoInspecao();
	}

	public void salvar() {
		try {
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaoDAO.merge(tipoInspecao);
			// Reinstancia o tipo
			novo(); // Limpa a tela após inserção dos dados
			// Recarrega a listagem de tipo
			tipoInspecaos = tipoInspecaoDAO.listar();
			Messages.addGlobalInfo("Tipo de Inspeção salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar o tipo de inspeção.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona o tipo a ser excluído
			tipoInspecao = (TipoInspecao) evento.getComponent().getAttributes().get("tipoInspecaoSelecionado");
			// Exclui o tipo
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaoDAO.excluir(tipoInspecao);
			// Recarrega a listagem de tipo
			tipoInspecaos = tipoInspecaoDAO.listar("tipoInspecao");
			Messages.addGlobalInfo("Tipo de Inspeção : " + tipoInspecao.getTipoInspecao() + ", excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir Tipo de Inspeção.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o Tipo a ser editado
			tipoInspecao = (TipoInspecao) evento.getComponent().getAttributes().get("tipoInspecaoSelecionado");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}