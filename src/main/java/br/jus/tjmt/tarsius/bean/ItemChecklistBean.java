package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.ToggleEvent;

import br.jus.tjmt.tarsius.dao.ChecklistDAO;
import br.jus.tjmt.tarsius.dao.ItemChecklistDAO;
import br.jus.tjmt.tarsius.dao.TipoInspecaoDAO;
import br.jus.tjmt.tarsius.domain.Checklist;
import br.jus.tjmt.tarsius.domain.ItemChecklist;
import br.jus.tjmt.tarsius.domain.TipoInspecao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemChecklistBean implements Serializable {
	private ItemChecklist itemChecklist;
	private List<ItemChecklist> itemChecklists;
	private List<Checklist> checklists;
	// var lógica
	private List<TipoInspecao> tipoInspecaos;
	private TipoInspecao tipoInspecao; // armazena temporariamente o tipo

	public ItemChecklist getItemChecklist() {
		return itemChecklist;
	}

	public void setItemChecklist(ItemChecklist itemChecklist) {
		this.itemChecklist = itemChecklist;
	}

	public List<ItemChecklist> getItemChecklists() {
		return itemChecklists;
	}

	public void setItemChecklists(List<ItemChecklist> itemChecklists) {
		this.itemChecklists = itemChecklists;
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

	public TipoInspecao getTipoInspecao() {
		return tipoInspecao;
	}

	public void setTipoInspecao(TipoInspecao tipoInspecao) {
		this.tipoInspecao = tipoInspecao;
	}

	@PostConstruct
	public void listar() {
		try {
			ItemChecklistDAO itemChecklistDAO = new ItemChecklistDAO();
			itemChecklists = itemChecklistDAO.listar("pergunta");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar os itens do checklist.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			itemChecklist = new ItemChecklist();
			tipoInspecao = new TipoInspecao();
			// Popular a seleção de tipo
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar("tipoInspecao");

			// Lista de Checklist vazios
			checklists = new ArrayList<Checklist>();
		} catch (RuntimeException erro) {
			// Validar a exception do banco listagem de checklist
			Messages.addFlashGlobalError("Falha ao cadastrar um novo checklist.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ItemChecklistDAO itemChecklistDAO = new ItemChecklistDAO();
			itemChecklistDAO.merge(itemChecklist);

			// Limpa objeto
			itemChecklist = new ItemChecklist();
			tipoInspecao = new TipoInspecao();
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar("tipoInspecao");
			checklists = new ArrayList<>();
			// Recarrega a listagem de Item de Checklist e Checklist
			ChecklistDAO checklistDAO = new ChecklistDAO();
			checklists = checklistDAO.listar("nome");
			itemChecklists = itemChecklistDAO.listar("pergunta");
			Messages.addGlobalInfo("Pergunta salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao cadastrar uma nova Pergunta.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona a pergunta a ser excluida
			itemChecklist = (ItemChecklist) evento.getComponent().getAttributes().get("perguntaSelecionada");
			// Exclui pergunta
			ItemChecklistDAO itemChecklistDAO = new ItemChecklistDAO();
			itemChecklistDAO.excluir(itemChecklist);
			// Recarrega a listagem de perguntas
			itemChecklists = itemChecklistDAO.listar("pergunta");
			Messages.addGlobalInfo("Pergunta : " + itemChecklist.getPergunta() + " excluída com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir pergunta.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona a pergunta a ser editado
			itemChecklist = (ItemChecklist) evento.getComponent().getAttributes().get("perguntaSelecionada");
			tipoInspecao = itemChecklist.getChecklist().getTipoInspecao();
						
			// Carrega checklist e tipo
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar("tipoInspecao");
			ChecklistDAO checklistDAO = new ChecklistDAO();
			checklists = checklistDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}

	// Popular o checklist com base no tipo selecionado
	public void popular() {
		try {
			if (tipoInspecao != null) {
				ChecklistDAO checklistDAO = new ChecklistDAO();
				checklists = checklistDAO.buscarPorTipoInspecao(tipoInspecao.getCodigo());
			} else {
				// Checklist vazio
				checklists = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar listar os Checklist.");
			erro.printStackTrace();
		}
	}

	public void handleToggle(ToggleEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selecionar Seção",
				"Visibility:" + event.getVisibility());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}