package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.PessoaDAO;
import br.jus.tjmt.tarsius.dao.UsuarioDAO;
import br.jus.tjmt.tarsius.domain.Pessoa;
import br.jus.tjmt.tarsius.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("papel");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao tentar listar usuário.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			usuario = new Usuario();
			// Popular a seleção de Usuário
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome"); // ordena por nome
		} catch (RuntimeException erro) {
			// Validar a exception do banco listagem de pessoas
			Messages.addFlashGlobalError("Falha ao listar pessoas.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
			usuario.setSenha(hash.toHex());
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			// Limpa o objeto
			usuario = new Usuario();
			// Recarrega a listagem de Usuário e Pessoa
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			usuarios = usuarioDAO.listar("papel");
			Messages.addGlobalInfo("Usuário salvo com sucesso.");			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao cadastrar um novo usuário.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona o usuário a ser excluído
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			// Exclui usuário
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);
			// Recarrega a listagem de usuários
			usuarios = usuarioDAO.listar("papel");
			Messages.addGlobalInfo("Usuário: " + usuario.getPessoa().getNome() + " excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir usuário.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o usuário a ser editado
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			// Carrega Pessoa
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}