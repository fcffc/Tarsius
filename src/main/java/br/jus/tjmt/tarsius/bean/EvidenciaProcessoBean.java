package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.CompetenciaInspecaoDAO;
import br.jus.tjmt.tarsius.dao.EvidenciaProcessoDAO;
import br.jus.tjmt.tarsius.dao.TipoArtefatoDAO;
import br.jus.tjmt.tarsius.domain.CompetenciaInspecao;
import br.jus.tjmt.tarsius.domain.EvidenciaProcesso;
import br.jus.tjmt.tarsius.domain.TipoArtefato;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EvidenciaProcessoBean implements Serializable {
	private EvidenciaProcesso evidenciaProcesso;
	private List<EvidenciaProcesso> evidenciaProcessos;
	private List<TipoArtefato> tipoArtefatos;
	private List<CompetenciaInspecao> competenciaInspecaos;

	public EvidenciaProcesso getEvidenciaProcesso() {
		return evidenciaProcesso;
	}

	public void setEvidenciaProcesso(EvidenciaProcesso evidenciaProcesso) {
		this.evidenciaProcesso = evidenciaProcesso;
	}

	public List<EvidenciaProcesso> getEvidenciaProcessos() {
		return evidenciaProcessos;
	}

	public void setEvidenciaProcessos(List<EvidenciaProcesso> evidenciaProcessos) {
		this.evidenciaProcessos = evidenciaProcessos;
	}

	public List<TipoArtefato> getTipoArtefatos() {
		return tipoArtefatos;
	}

	public void setTipoArtefatos(List<TipoArtefato> tipoArtefatos) {
		this.tipoArtefatos = tipoArtefatos;
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
			EvidenciaProcessoDAO evidenciaProcessoDAO = new EvidenciaProcessoDAO();
			evidenciaProcessos = evidenciaProcessoDAO.listar("tipo");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar evidências do processo.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			evidenciaProcesso = new EvidenciaProcesso();
			// Popular a seleção de EvidenciaProcesso
			TipoArtefatoDAO tipoArtefatoDAO = new TipoArtefatoDAO();
			tipoArtefatos = tipoArtefatoDAO.listar("sigla");
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaos = competenciaInspecaoDAO.listar("descricao");// Ordena
																				// por
																				// descrição
		} catch (RuntimeException erro) {
			// Validar a exception do banco listagem de competências
			Messages.addFlashGlobalError("Falha ao cadastrar uma nova evidência do tipo processo.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			EvidenciaProcessoDAO evidenciaProcessoDAO = new EvidenciaProcessoDAO();
			evidenciaProcessoDAO.merge(evidenciaProcesso);

			// Limpa objeto
			evidenciaProcesso = new EvidenciaProcesso();
			// Recarrega a listagem de EvidenciaProcesso, TipoArtefato e Competência
			TipoArtefatoDAO tipoArtefatoDAO = new TipoArtefatoDAO();
			tipoArtefatos = tipoArtefatoDAO.listar("sigla");
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaos = competenciaInspecaoDAO.listar("descricao");
			evidenciaProcessos = evidenciaProcessoDAO.listar("tipo");
			Messages.addGlobalInfo("Evidência do processo salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao cadastrar uma nova evidência do tipo processo.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona a evidenciaProcesso a ser excluída
			evidenciaProcesso = (EvidenciaProcesso) evento.getComponent().getAttributes()
					.get("evidenciaProcessoSelecionada");
			// Exclui a evidência
			EvidenciaProcessoDAO evidenciaProcessoDAO = new EvidenciaProcessoDAO();
			evidenciaProcessoDAO.excluir(evidenciaProcesso);
			// Recarrega a listagem de evidencias
			evidenciaProcessos = evidenciaProcessoDAO.listar("tipo");
			Messages.addGlobalInfo(
					"Evidência do tipo Processo: " + evidenciaProcesso.getArtefato() + " excluída com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir a evidência do tipo processo.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona a evidenciaProcesso a ser editada
			evidenciaProcesso = (EvidenciaProcesso) evento.getComponent().getAttributes()
					.get("evidenciaProcessoSelecionada");
			// Carrega Tipo Artefato e Competencia
			TipoArtefatoDAO tipoArtefatoDAO = new TipoArtefatoDAO();
			tipoArtefatos = tipoArtefatoDAO.listar("sigla");
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaos = competenciaInspecaoDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}