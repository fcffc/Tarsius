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
import br.jus.tjmt.tarsius.dao.CompetenciaInspecaoDAO;
import br.jus.tjmt.tarsius.dao.InspecaoDAO;
import br.jus.tjmt.tarsius.dao.TipoInspecaoDAO;
import br.jus.tjmt.tarsius.domain.Checklist;
import br.jus.tjmt.tarsius.domain.CompetenciaInspecao;
import br.jus.tjmt.tarsius.domain.Inspecao;
import br.jus.tjmt.tarsius.domain.TipoInspecao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class InspecaoBean implements Serializable {
	private Inspecao inspecao;
	private List<Inspecao> inspecaos;
	private List<Checklist> checklists;
	private List<CompetenciaInspecao> competenciaInspecaos;
	// var lógica

	private List<TipoInspecao> tipoInspecaos;
	private TipoInspecao tipoInspecao; // armazena temporariamente o tipo

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public List<Inspecao> getInspecaos() {
		return inspecaos;
	}

	public void setInspecaos(List<Inspecao> inspecaos) {
		this.inspecaos = inspecaos;
	}

	public List<Checklist> getChecklists() {
		return checklists;
	}

	public void setChecklists(List<Checklist> checklists) {
		this.checklists = checklists;
	}

	public List<CompetenciaInspecao> getCompetenciaInspecaos() {
		return competenciaInspecaos;
	}

	public void setCompetenciaInspecaos(List<CompetenciaInspecao> competenciaInspecaos) {
		this.competenciaInspecaos = competenciaInspecaos;
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
			InspecaoDAO inspecaoDAO = new InspecaoDAO();
			inspecaos = inspecaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar a Inspeção.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			inspecao = new Inspecao();
			tipoInspecao = new TipoInspecao();

			// Popular a seleção de competencias tipos e checklis
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaos = competenciaInspecaoDAO.listar("descricao");
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar();// Ordena por tipo
			// Lista de Checklist vazios
			checklists = new ArrayList<Checklist>();
		} catch (RuntimeException erro) {
			// Validar a exception do banco listagem de estados
			Messages.addFlashGlobalError("Falha ao cadastrar uma nova inspeção.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			InspecaoDAO inspecaoDAO = new InspecaoDAO();
			inspecaoDAO.merge(inspecao);

			// Limpar o objeto
			inspecao = new Inspecao();
			tipoInspecao = new TipoInspecao();
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaos = competenciaInspecaoDAO.listar("descricao");
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar("tipoInspecao");
			checklists = new ArrayList<>();
			// Recarrega a listagem de checklist e tipos
			ChecklistDAO checklistDAO = new ChecklistDAO();
			checklists = checklistDAO.listar("nome");
			inspecaos = inspecaoDAO.listar("competencia");
			Messages.addGlobalInfo("Inspeção salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao cadastrar um novo Checklist.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona a inspeção a ser excluído
			inspecao = (Inspecao) evento.getComponent().getAttributes().get("inspecaoSelecionada");
			// Exclui Inspeção
			InspecaoDAO inspecaoDAO = new InspecaoDAO();
			inspecaoDAO.excluir(inspecao);
			// Recarrega a Listagem de inspeção
			inspecaos = inspecaoDAO.listar("competencia");
			Messages.addGlobalInfo(
					"Inspeção de competência: " + inspecao.getCompetencia().getDescricao() + ", excluída com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir inspeção.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona a inspeção a ser editado
			inspecao = (Inspecao) evento.getComponent().getAttributes().get("inspecaoSelecionada");
			tipoInspecao = inspecao.getChecklist().getTipoInspecao();
			
			// Carrega o Competencia, tipo e checklist
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaos = competenciaInspecaoDAO.listar("descricao");
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
}