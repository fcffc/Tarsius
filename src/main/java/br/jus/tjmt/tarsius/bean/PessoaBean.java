package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.CargoDAO;
import br.jus.tjmt.tarsius.dao.PessoaDAO;
import br.jus.tjmt.tarsius.domain.Cargo;
import br.jus.tjmt.tarsius.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Cargo> cargos;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar pessoas.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			pessoa = new Pessoa();
			// Popular a seleção de Cargos
			CargoDAO cargoDAO = new CargoDAO();
			cargos = cargoDAO.listar("nome"); // orderna por nome
		} catch (RuntimeException erro) {
			// Validar a exception do banco listagem de cargos
			Messages.addFlashGlobalError("Falha ao cadastrar uma nova pessoa.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);

			// Limpar o objeto
			pessoa = new Pessoa();
			// Recarrega a listagem de Pessoas e Cargos
			CargoDAO cargoDAO = new CargoDAO();
			cargos = cargoDAO.listar("nome");
			pessoas = pessoaDAO.listar("nome");
			Messages.addGlobalInfo("Pessoa salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao cadastrar uma nova Pessoa.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona a pessoa a ser excluída
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			// Exclui a pessoa
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			// Recarrega a listagem da Pessoa
			pessoas = pessoaDAO.listar("nome");
			Messages.addGlobalInfo("Pessoa : " + pessoa.getNome() + " excluída com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir a pessoa.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona a pessoa a ser editada
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			// Carrega o Cargo
			CargoDAO cargoDAO = new CargoDAO();
			cargos = cargoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}