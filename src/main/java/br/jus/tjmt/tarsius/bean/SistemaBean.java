package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.SistemaDAO;
import br.jus.tjmt.tarsius.domain.Sistema;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SistemaBean implements Serializable {
	private Sistema sistema;
	private List<Sistema> sistemas;

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public List<Sistema> getSistemas() {
		return sistemas;
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}

	// Chama automaticamente o método Listar
	@PostConstruct
	public void listar() {
		try {
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemas = sistemaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar carregar listagem de Sistemas.");
			erro.printStackTrace();
		}
	}

	// Instancia a classe para novo registro
	public void novo() {
		sistema = new Sistema();
	}

	public void salvar() {
		try {
			SistemaDAO sistemaDAO = new SistemaDAO();
			sistemaDAO.salvar(sistema);
			novo(); // Limpa a tela após inserção dos dados
			Messages.addGlobalInfo("Sistema salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar o Sistema.");
			erro.printStackTrace();
		}
	}
}
