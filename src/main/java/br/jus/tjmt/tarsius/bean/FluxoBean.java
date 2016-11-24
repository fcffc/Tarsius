package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.FluxoDAO;
import br.jus.tjmt.tarsius.dao.ProcessoDAO;
import br.jus.tjmt.tarsius.domain.Fluxo;
import br.jus.tjmt.tarsius.domain.Processo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FluxoBean implements Serializable {
	private Fluxo fluxo;
	private List<Fluxo> fluxos;
	private List<Processo> processos;

	public Fluxo getFluxo() {
		return fluxo;
	}

	public void setFluxo(Fluxo fluxo) {
		this.fluxo = fluxo;
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

	@PostConstruct
	public void listar() {
		try {
			FluxoDAO fluxoDAO = new FluxoDAO();
			fluxos = fluxoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar os fluxos.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			fluxo = new Fluxo();
			// Popular a seleção de Processos
			ProcessoDAO processoDAO = new ProcessoDAO();
			processos = processoDAO.listar("nome"); // Ordena por nome
		} catch (RuntimeException erro) {
			// Validar a exception do banco listagem de processos
			Messages.addFlashGlobalError("Falha ao cadastrar um novo fluxo.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			FluxoDAO fluxoDAO = new FluxoDAO();
			fluxoDAO.merge(fluxo);

			// Limpar o objeto
			fluxo = new Fluxo();
			// Recarrega a listagem de Fluxos e Processos
			ProcessoDAO processoDAO = new ProcessoDAO();
			processos = processoDAO.listar("nome");
			fluxos = fluxoDAO.listar("nome");
			Messages.addGlobalInfo("Fluxo salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao cadastrar um novo Fluxo.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona o fluxo a ser excluído
			fluxo = (Fluxo) evento.getComponent().getAttributes().get("fluxoSelecionado");
			// Exclui o fluxo
			FluxoDAO fluxoDAO = new FluxoDAO();
			fluxoDAO.excluir(fluxo);
			// Recarrega a listagem de fluxo
			fluxos = fluxoDAO.listar("nome");
			Messages.addGlobalInfo("Fluxo : " + fluxo.getNome() + " excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir o fluxo.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o fluxo a ser editado
			fluxo = (Fluxo) evento.getComponent().getAttributes().get("fluxoSelecionado");
			// Carrega o Processo
			ProcessoDAO processoDAO = new ProcessoDAO();
			processos = processoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}