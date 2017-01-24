package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.ChecklistDAO;
import br.jus.tjmt.tarsius.dao.FluxoDAO;
import br.jus.tjmt.tarsius.dao.ProcessoDAO;
import br.jus.tjmt.tarsius.dao.TipoInspecaoDAO;
import br.jus.tjmt.tarsius.domain.Checklist;
import br.jus.tjmt.tarsius.domain.Fluxo;
import br.jus.tjmt.tarsius.domain.Processo;
import br.jus.tjmt.tarsius.domain.TipoInspecao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ChecklistBean implements Serializable {
	private Checklist checklist;
	private List<Checklist> checklists;
	private List<TipoInspecao> tipoInspecaos;
	private List<Fluxo> fluxos;
	// var lógica
	private List<Processo> processos;
	private Processo processo;

	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}

	public List<Checklist> getChecklists() {
		return checklists;
	}

	public void setChecklists(List<Checklist> checklists) {
		this.checklists = checklists;
	}

	public List<TipoInspecao> getTipoInspecaos() {
		return tipoInspecaos;
	}

	public void setTipoInspecaos(List<TipoInspecao> tipoInspecaos) {
		this.tipoInspecaos = tipoInspecaos;
	}

	public List<Fluxo> getFluxos() {
		return fluxos;
	}

	public void setFluxos(List<Fluxo> fluxos) {
		this.fluxos = fluxos;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	@PostConstruct
	public void listar() {
		try {
			ChecklistDAO checklistDAO = new ChecklistDAO();
			checklists = checklistDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar Checklist");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			checklist = new Checklist();
			processo = new Processo();
			// Popular a seleção de tipos			
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar();// Ordena por nome
			ProcessoDAO processoDAO = new ProcessoDAO();
			processos = processoDAO.listar("nome");
			// Lista de Fluxos vazios
			fluxos = new ArrayList<Fluxo>();			
		} catch (RuntimeException erro) {
			// Validar a exception do banco listagem de fluxo, processo e tipo
			Messages.addFlashGlobalError("Falha ao cadastrar um novo checklist.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ChecklistDAO checklistDAO = new ChecklistDAO();
			checklistDAO.merge(checklist);

			// Limpar o objeto
			checklist = new Checklist();
			processo = new Processo();
			ProcessoDAO processoDAO = new ProcessoDAO();
			processos = processoDAO.listar("nome");
			fluxos = new ArrayList<>();
			// Recarrega a listagem de fluxo e tipos
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar("tipoInspecao");
			FluxoDAO fluxoDAO = new FluxoDAO();
			fluxos = fluxoDAO.listar("nome");
			checklists = checklistDAO.listar("nome");
			Messages.addGlobalInfo("Checklist salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao cadastrar um novo Checklist.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona o checklist a ser excluído
			checklist = (Checklist) evento.getComponent().getAttributes().get("checklistSelecionado");
			// Exclui Checklist
			ChecklistDAO checklistDAO = new ChecklistDAO();
			checklistDAO.excluir(checklist);
			// Recarrega a Listagem de checklist
			checklists = checklistDAO.listar("nome");
			Messages.addGlobalInfo("Checklist: " + checklist.getNome() + ", excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir checklist.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o checklist a ser editado
			checklist = (Checklist) evento.getComponent().getAttributes().get("checklistSelecionado");
			processo = checklist.getFluxo().getProcesso();
			// Carrega o tipo
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar("tipoInspecao");
			ProcessoDAO processoDAO = new ProcessoDAO();
			processos = processoDAO.listar("nome");
			FluxoDAO fluxoDAO = new FluxoDAO();
			fluxos = fluxoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
	
	// Popular o checklist com base no tipo selecionado
		public void popular() {
			try {
				if (processo != null) {
					FluxoDAO fluxoDAO = new FluxoDAO();
					fluxos = fluxoDAO.buscarPorProcesso(processo.getCodigo());					
				} else {
					// Fluxo vazio
					fluxos = new ArrayList<>();					
				}
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Falha ao tentar listar os Checklist.");
				erro.printStackTrace();
			}
	}
}