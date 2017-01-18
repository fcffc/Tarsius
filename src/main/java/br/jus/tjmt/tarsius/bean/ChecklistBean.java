package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.ChecklistDAO;
import br.jus.tjmt.tarsius.domain.Checklist;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ChecklistBean implements Serializable {
	private Checklist checklist;
	private List<Checklist> checklists;

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
		checklist = new Checklist();
	}

	public void salvar() {
		try {
			ChecklistDAO checklistDAO = new ChecklistDAO();
			checklistDAO.merge(checklist);

			// Limpa objeto
			checklist = new Checklist();
			// Recarrega a listagem de Checklist
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
			Messages.addGlobalInfo("Checklist: " + checklist.getNome() + " excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir checklist.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o checklist a ser editado
			checklist = (Checklist) evento.getComponent().getAttributes().get("checklistSelecionado");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}