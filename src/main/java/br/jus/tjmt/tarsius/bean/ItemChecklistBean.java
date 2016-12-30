package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.ChecklistDAO;
import br.jus.tjmt.tarsius.dao.ItemChecklistDAO;
import br.jus.tjmt.tarsius.domain.Checklist;
import br.jus.tjmt.tarsius.domain.ItemChecklist;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemChecklistBean implements Serializable {
	private ItemChecklist itemChecklist;
	private List<ItemChecklist> itemChecklists;
	private List<Checklist> checklists;

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

	@PostConstruct
	public void listar() {
		try {
			ItemChecklistDAO itemChecklistDAO = new ItemChecklistDAO();
			itemChecklists = itemChecklistDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar os itens do checklist.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			itemChecklist = new ItemChecklist();
			// Popular a seleção de Checklist
			ChecklistDAO checklistDAO = new ChecklistDAO();
			checklists = checklistDAO.listar("pergunta"); // ordena por pessoa
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
			// Recarrega a listagem de Checklist
			ChecklistDAO checklistDAO = new ChecklistDAO();
			checklists = checklistDAO.listar("nome");
			itemChecklists = itemChecklistDAO.listar();
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
			// Carrega checklist
			ChecklistDAO checklistDAO = new ChecklistDAO();
			checklists = checklistDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}