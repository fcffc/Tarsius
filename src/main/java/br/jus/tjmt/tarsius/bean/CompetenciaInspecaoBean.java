package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

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

	// Inicia uma nova instancia ao abrir a tela de cadastro/edição
	public void novo() {
		competenciaInspecao = new CompetenciaInspecao();
	}

	public void salvar() {
		try {
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaoDAO.merge(competenciaInspecao);
			// Reinstancia o Cargo
			novo(); // Limpa a tela após inserção dos dados
			// Recarrega a listagem de cargo
			competenciaInspecaos = competenciaInspecaoDAO.listar();
			Messages.addGlobalInfo("Competência salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar a Competência.");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
			// Seleciona o cargo a ser excluído
			competenciaInspecao = (CompetenciaInspecao) evento.getComponent().getAttributes().get("competenciaInspecaoSelecionada");
			// Exclui o cargo
			CompetenciaInspecaoDAO competenciaInspecaoDAO = new CompetenciaInspecaoDAO();
			competenciaInspecaoDAO.excluir(competenciaInspecao);
			// Recarrega a listagem de cargo
			competenciaInspecaos = competenciaInspecaoDAO.listar("descricao");
			Messages.addGlobalInfo("Competência: " + competenciaInspecao.getDescricao() + "excluída com sucesso.");
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Falha ao excluir Competência.");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try{
			// Seleciona o estado a ser editado
			competenciaInspecao = (CompetenciaInspecao) evento.getComponent().getAttributes().get("competenciaInspecaoSelecionada");
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}
