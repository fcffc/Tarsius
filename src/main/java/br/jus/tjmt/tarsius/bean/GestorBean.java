package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.GestorDAO;
import br.jus.tjmt.tarsius.dao.PessoaDAO;
import br.jus.tjmt.tarsius.domain.Gestor;
import br.jus.tjmt.tarsius.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GestorBean implements Serializable {
	private Gestor gestor;
	private List<Gestor> gestores;
	private List<Pessoa> pessoas;

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public List<Gestor> getGestores() {
		return gestores;
	}

	public void setGestores(List<Gestor> gestores) {
		this.gestores = gestores;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {
		try {
			GestorDAO gestorDAO = new GestorDAO();
			gestores = gestorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar os gestores.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			gestor = new Gestor();
			// Popular a seleção de Pessoas
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome"); // ordena por pessoa
		} catch (RuntimeException erro) {
			// Validar a exception do banco listagem de pessoas
			Messages.addFlashGlobalError("Falha ao cadastrar um novo gestor.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			GestorDAO gestorDAO = new GestorDAO();
			gestorDAO.merge(gestor);

			// Limpa o objeto
			gestor = new Gestor();
			// Recarrega a listagem de Gestores e Pessoas
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			gestores = gestorDAO.listar();
			Messages.addGlobalInfo("Gestor salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao cadastrar um novo Gestor.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona o gestor a ser excluído
			gestor = (Gestor) evento.getComponent().getAttributes().get("gestorSelecionado");
			// Excluir gestor
			GestorDAO gestorDAO = new GestorDAO();
			gestorDAO.excluir(gestor);
			// Recarrega a listagem de gestor
			gestores = gestorDAO.listar();
			Messages.addGlobalInfo("Gestor(a) : " + gestor.getPessoa().getNome() + " excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir o gestor.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o gestor a ser editado
			gestor = (Gestor) evento.getComponent().getAttributes().get("gestorSelecionado");
			// carrega a Pessoa
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}