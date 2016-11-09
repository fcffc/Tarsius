package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.CompetenciaInspecaoDAO;
import br.jus.tjmt.tarsius.domain.CompetenciaInspecao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CompetenciaInspecaoBean implements Serializable {
	private CompetenciaInspecao competenciaInspecao;
	private List<CompetenciaInspecao> competenciaInspecaos;

	public CompetenciaInspecao getCompetenciaInspecao() {
		return competenciaInspecao;
	}

	public void setCompetenciaInspecao(CompetenciaInspecao competenciaInspecao) {
		this.competenciaInspecao = competenciaInspecao;
	}

	public List<CompetenciaInspecao> getCompetenciaInspecaos() {
		return competenciaInspecaos;
	}

	public void setCompetenciaInspecaos(List<CompetenciaInspecao> competenciaInspecaos) {
		this.competenciaInspecaos = competenciaInspecaos;
	}

	// Chama automaticamente o método Listar
	@PostConstruct
	public void listar() {
		try {
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaos = competenciaInspecaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar carregar listagem de Competências.");
			erro.printStackTrace();
		}
	}

	// Instancia a classe para novo registro
	public void novo() {
		competenciaInspecao = new CompetenciaInspecao();
	}

	public void salvar() {
		try {
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaoDAO.salvar(competenciaInspecao);
			novo(); // Limpa a tela após inserção dos dados
			Messages.addGlobalInfo("Cargo salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar a Competência.");
			erro.printStackTrace();
		}
	}
}
