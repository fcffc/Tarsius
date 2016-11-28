package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.CompetenciaInspecaoDAO;
import br.jus.tjmt.tarsius.dao.EvidenciaSistemaDAO;
import br.jus.tjmt.tarsius.dao.SistemaDAO;
import br.jus.tjmt.tarsius.domain.CompetenciaInspecao;
import br.jus.tjmt.tarsius.domain.EvidenciaSistema;
import br.jus.tjmt.tarsius.domain.Sistema;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EvidenciaSistemaBean implements Serializable {
	private EvidenciaSistema evidenciaSistema;
	private List<EvidenciaSistema> evidenciaSistemas;
	private List<Sistema> sistemas;
	private List<CompetenciaInspecao> competenciaInspecaos;

	public EvidenciaSistema getEvidenciaSistema() {
		return evidenciaSistema;
	}

	public void setEvidenciaSistema(EvidenciaSistema evidenciaSistema) {
		this.evidenciaSistema = evidenciaSistema;
	}

	public List<EvidenciaSistema> getEvidenciaSistemas() {
		return evidenciaSistemas;
	}

	public void setEvidenciaSistemas(List<EvidenciaSistema> evidenciaSistemas) {
		this.evidenciaSistemas = evidenciaSistemas;
	}

	public List<Sistema> getSistemas() {
		return sistemas;
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}

	public List<CompetenciaInspecao> getCompetenciaInspecaos() {
		return competenciaInspecaos;
	}

	public void setCompetenciaInspecaos(List<CompetenciaInspecao> competenciaInspecaos) {
		this.competenciaInspecaos = competenciaInspecaos;
	}

	@PostConstruct
	public void listar() {
		try {
			EvidenciaSistemaDAO evidenciaSistemaDAO = new EvidenciaSistemaDAO();
			evidenciaSistemas = evidenciaSistemaDAO.listar("funcionalidade");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar evidências do sistema.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			evidenciaSistema = new EvidenciaSistema();
			// Popular a seleção de EvidenciaSistema
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemas = sistemaDAO.listar("sigla"); // ordena por sigla
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaos = competenciaInspecaoDAO.listar("descricao"); // ordena
																				// por
																				// descrição
		} catch (RuntimeException erro) {
			// Validar a exception do banco listagem de sistemas e competências
			Messages.addFlashGlobalError("Falha ao cadastrar uma nova evidência do tipo sistema.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			EvidenciaSistemaDAO evidenciaSistemaDAO = new EvidenciaSistemaDAO();
			evidenciaSistemaDAO.merge(evidenciaSistema);

			// Limpar o objeto
			evidenciaSistema = new EvidenciaSistema();
			// Recarrega a listagem de EvidenciaSistema, Sistemas e Competência
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemas = sistemaDAO.listar("sigla");
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaos = competenciaInspecaoDAO.listar("descricao");
			evidenciaSistemas = evidenciaSistemaDAO.listar("funcionalidade");
			Messages.addGlobalInfo("Evidência do sistema salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao cadastrar uma nova evidência do tipo sistema.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona o evidenciaSistema a ser excluída
			evidenciaSistema = (EvidenciaSistema) evento.getComponent().getAttributes()
					.get("evidenciaSistemaSelecionada");
			// Exclui a evidência
			EvidenciaSistemaDAO evidenciaSistemaDAO = new EvidenciaSistemaDAO();
			evidenciaSistemaDAO.excluir(evidenciaSistema);
			// Recarrega a listagem de evidencias
			evidenciaSistemas = evidenciaSistemaDAO.listar("funcionalidade");
			Messages.addGlobalInfo(
					"Evidência do tipo sistema: " + evidenciaSistema.getFuncionalidade() + " excluída com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir a evidência do tipo sistema.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o evidenciaSistema a ser editada
			evidenciaSistema = (EvidenciaSistema) evento.getComponent().getAttributes()
					.get("evidenciaSistemaSelecionada");
			// Carrega Sistema e Competencia
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemas = sistemaDAO.listar("sigla");
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaos = competenciaInspecaoDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}