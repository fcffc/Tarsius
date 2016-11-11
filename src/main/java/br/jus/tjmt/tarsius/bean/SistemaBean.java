package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.SistemaDAO;
import br.jus.tjmt.tarsius.domain.Sistema;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SistemaBean implements Serializable {
	private Sistema sistema;
	private List<Sistema> sistemas;

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public List<Sistema> getSistemas() {
		return sistemas;
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}

	// Chama automaticamente o método Listar
	@PostConstruct
	public void listar() {
		try {
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemas = sistemaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar carregar listagem de Sistemas.");
			erro.printStackTrace();
		}
	}

	// Instancia a classe para novo registro
	public void novo() {
		sistema = new Sistema();
	}

	public void salvar() {
		try {
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemaDAO.merge(sistema);
			// Reinstancia o Sistema
			novo(); // Limpa a tela após inserção dos dados
			// Recarrega a listagem de Sistema
			sistemas = sistemaDAO.listar();
			Messages.addGlobalInfo("Sistema salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar o Sistema.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona o sistema a ser excluído
			sistema = (Sistema) evento.getComponent().getAttributes().get("sistemaSelecionado");
			// Exclui o sistema
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemaDAO.excluir(sistema);
			// Recarrega a listagem de Sistema
			sistemas = sistemaDAO.listar("sigla");
			Messages.addGlobalInfo("Sistema: " + sistema.getSigla() + "excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir Sistema.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o Sistema a ser editado
			sistema = (Sistema) evento.getComponent().getAttributes().get("sistemaSelecionado");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}