package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.GestorDAO;
import br.jus.tjmt.tarsius.dao.InspecaoDAO;
import br.jus.tjmt.tarsius.dao.ItemChecklistDAO;
import br.jus.tjmt.tarsius.dao.ItemInspecaoDAO;
import br.jus.tjmt.tarsius.dao.SistemaDAO;
import br.jus.tjmt.tarsius.dao.TipoInspecaoDAO;
import br.jus.tjmt.tarsius.domain.Gestor;
import br.jus.tjmt.tarsius.domain.Inspecao;
import br.jus.tjmt.tarsius.domain.ItemChecklist;
import br.jus.tjmt.tarsius.domain.ItemInspecao;
import br.jus.tjmt.tarsius.domain.Sistema;
import br.jus.tjmt.tarsius.domain.TipoInspecao;
import br.jus.tjmt.tarsius.enumeracao.SecaoProcesso;
import br.jus.tjmt.tarsius.enumeracao.SecaoSistema;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemInspecaoBean implements Serializable {
	private ItemInspecao itemInspecao;
	private List<ItemInspecao> itemInspecaos;
	private List<Inspecao> inspecaos;
	private List<ItemChecklist> itemChecklists;
	private List<Sistema> sistemas;
	private List<Gestor> gestores;

	// var lógica
	private List<TipoInspecao> tipoInspecaos;
	private TipoInspecao tipoInspecao; // armazena temporariamente o tipo
	private List<SecaoProcesso> secaoProcessos;
	private SecaoProcesso secaoProcesso; // armazena temporariamente a
											// secaoProcesso
	private List<SecaoSistema> secaoSistemas;
	private SecaoSistema secaoSistema; // armazena temporariamente a
										// secaoSistema

	public ItemInspecao getItemInspecao() {
		return itemInspecao;
	}

	public void setItemInspecao(ItemInspecao itemInspecao) {
		this.itemInspecao = itemInspecao;
	}

	public List<ItemInspecao> getItemInspecaos() {
		return itemInspecaos;
	}

	public void setItemInspecaos(List<ItemInspecao> itemInspecaos) {
		this.itemInspecaos = itemInspecaos;
	}

	public List<Inspecao> getInspecaos() {
		return inspecaos;
	}

	public void setInspecaos(List<Inspecao> inspecaos) {
		this.inspecaos = inspecaos;
	}

	public List<ItemChecklist> getItemChecklists() {
		return itemChecklists;
	}

	public void setItemChecklists(List<ItemChecklist> itemChecklists) {
		this.itemChecklists = itemChecklists;
	}

	public List<Sistema> getSistemas() {
		return sistemas;
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}

	public List<Gestor> getGestores() {
		return gestores;
	}

	public void setGestores(List<Gestor> gestores) {
		this.gestores = gestores;
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

	public List<SecaoProcesso> getSecaoProcessos() {
		return secaoProcessos;
	}

	public void setSecaoProcessos(List<SecaoProcesso> secaoProcessos) {
		this.secaoProcessos = secaoProcessos;
	}

	public SecaoProcesso getSecaoProcesso() {
		return secaoProcesso;
	}

	public void setSecaoProcesso(SecaoProcesso secaoProcesso) {
		this.secaoProcesso = secaoProcesso;
	}

	public List<SecaoSistema> getSecaoSistemas() {
		return secaoSistemas;
	}

	public void setSecaoSistemas(List<SecaoSistema> secaoSistemas) {
		this.secaoSistemas = secaoSistemas;
	}

	public SecaoSistema getSecaoSistema() {
		return secaoSistema;
	}

	public void setSecaoSistema(SecaoSistema secaoSistema) {
		this.secaoSistema = secaoSistema;
	}

	@PostConstruct
	public void listar() {
		try {
			ItemInspecaoDAO itemInspecaoDAO = new ItemInspecaoDAO();
			itemInspecaos = itemInspecaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar os itens da inspeção.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			itemInspecao = new ItemInspecao();
			tipoInspecao = new TipoInspecao();
			secaoProcesso = new ItemInspecaoBean().getSecaoProcesso();
			secaoSistema = new ItemInspecaoBean().getSecaoSistema();
			// Popular a seleção de tipo
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemas = sistemaDAO.listar("sigla");
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar("tipoInspecao");
			ItemChecklistDAO itemChecklistDAO = new ItemChecklistDAO();
			itemChecklists = itemChecklistDAO.listar("pergunta");
			GestorDAO gestorDAO = new GestorDAO();
			gestores = gestorDAO.listar("pessoa");
			// Lista de Checklist vazios
			inspecaos = new ArrayList<Inspecao>();
			itemChecklists = new ArrayList<ItemChecklist>();
		} catch (RuntimeException erro) {
			// Validar a exception do banco listagem de checklist
			Messages.addFlashGlobalError("Falha ao cadastrar um novo item de Inspeção.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ItemInspecaoDAO itemInspecaoDAO = new ItemInspecaoDAO();
			itemInspecaoDAO.merge(itemInspecao);

			// Limpa objeto
			itemInspecao = new ItemInspecao();
			tipoInspecao = new TipoInspecao();
			secaoProcesso = new ItemInspecaoBean().getSecaoProcesso();
			secaoSistema = new ItemInspecaoBean().getSecaoSistema();
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar("tipoInspecao");
			inspecaos = new ArrayList<Inspecao>();
			itemChecklists = new ArrayList<ItemChecklist>();
			// Recarrega a listagem de Item de Checklist e Checklist
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemas = sistemaDAO.listar("sigla");
			InspecaoDAO inspecaoDAO = new InspecaoDAO();
			inspecaos = inspecaoDAO.listar();
			ItemChecklistDAO itemChecklistDAO = new ItemChecklistDAO();
			itemChecklists = itemChecklistDAO.listar("pergunta");
			GestorDAO gestorDAO = new GestorDAO();
			gestores = gestorDAO.listar("pessoa");
			itemInspecaos = itemInspecaoDAO.listar();
			Messages.addGlobalInfo("item de inspeção salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao cadastrar um item de inspeção.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona a pergunta a ser excluida
			itemInspecao = (ItemInspecao) evento.getComponent().getAttributes().get("ItemInspSelecionado");
			// Exclui pergunta
			ItemInspecaoDAO itemInspecaoDAO = new ItemInspecaoDAO();
			itemInspecaoDAO.excluir(itemInspecao);
			// Recarrega a listagem de perguntas
			itemInspecaos = itemInspecaoDAO.listar();
			Messages.addGlobalInfo(
					"Item da Inspeção : " + itemInspecao.getItemPergunta().getPergunta() + " excluída com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir Item da Inspeção.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona a pergunta a ser editado
			itemInspecao = (ItemInspecao) evento.getComponent().getAttributes().get("ItemInspSelecionado");
			tipoInspecao = itemInspecao.getInspecao().getChecklist().getTipoInspecao();
			// Carrega checklist e tipo
			TipoInspecaoDAO tipoInspecaoDAO = new TipoInspecaoDAO();
			tipoInspecaos = tipoInspecaoDAO.listar("tipoInspecao");
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemas = sistemaDAO.listar("sigla");
			InspecaoDAO inspecaoDAO = new InspecaoDAO();
			inspecaos = inspecaoDAO.listar();
			ItemChecklistDAO itemChecklistDAO = new ItemChecklistDAO();
			itemChecklists = itemChecklistDAO.listar("pergunta");
			GestorDAO gestorDAO = new GestorDAO();
			gestores = gestorDAO.listar("pessoa");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}

}
