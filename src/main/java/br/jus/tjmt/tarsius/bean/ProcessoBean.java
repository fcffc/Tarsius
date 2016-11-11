package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.ProcessoDAO;
import br.jus.tjmt.tarsius.domain.Processo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProcessoBean implements Serializable {
	private Processo processo;
	private List<Processo> processos;

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	// Chama automaticamente o método Listar
	@PostConstruct
	public void listar() {
		try {
			ProcessoDAO processoDAO = new ProcessoDAO();
			processos = processoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar carregar listagem de Processos.");
			erro.printStackTrace();
		}
	}

	// Instancia a classe para novo registro
	public void novo() {
		processo = new Processo();
	}

	public void salvar() {
		try {
			ProcessoDAO processoDAO = new ProcessoDAO();
			processoDAO.merge(processo);
			// Reinstancia o Processo
			novo(); // Limpa a tela após inserção dos dados
			// Recarrega a listagem de Processo
			processos = processoDAO.listar();
			Messages.addGlobalInfo("Processo salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar o Processo.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona o processo a ser excluído
			processo = (Processo) evento.getComponent().getAttributes().get("processoSelecionado");
			// Exclui o processo
			ProcessoDAO processoDAO = new ProcessoDAO();
			processoDAO.excluir(processo);
			// Recarrega a listagem de processo
			processos = processoDAO.listar("nome");
			Messages.addGlobalInfo("Processo: " + processo.getNome() + " excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir Processo.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o Processo a ser editado
			processo = (Processo) evento.getComponent().getAttributes().get("processoSelecionado");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}