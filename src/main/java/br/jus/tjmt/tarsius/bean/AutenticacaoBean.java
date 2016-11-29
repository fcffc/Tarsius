package br.jus.tjmt.tarsius.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.UsuarioDAO;
import br.jus.tjmt.tarsius.domain.Pessoa;
import br.jus.tjmt.tarsius.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getMatricula(), usuario.getSenha());

			if (usuarioLogado == null) {
				Messages.addGlobalError("Matrícula e/ou senha incorretos");
				return;
			}
			Faces.redirect("./pages/inicial.xhtml");
		} catch (IOException erro) {
			Messages.addGlobalError("Falha ao logar.");
			erro.printStackTrace();
		}
	}
}