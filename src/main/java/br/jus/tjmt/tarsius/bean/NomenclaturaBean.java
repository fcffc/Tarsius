package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.NomenclaturaDAO;
import br.jus.tjmt.tarsius.domain.Nomenclatura;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class NomenclaturaBean implements Serializable {
	private Nomenclatura nomenclatura;
	private List<Nomenclatura> nomenclaturas;

	public Nomenclatura getNomenclatura() {
		return nomenclatura;
	}

	public void setNomenclatura(Nomenclatura nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

	public List<Nomenclatura> getNomenclaturas() {
		return nomenclaturas;
	}

	public void setNomenclaturas(List<Nomenclatura> nomenclaturas) {
		this.nomenclaturas = nomenclaturas;
	}

	// Chama automaticamente o método Listar
	@PostConstruct
	public void listar() {
		try {
			NomenclaturaDAO nomenclaturaDAO = new NomenclaturaDAO();
			nomenclaturas = nomenclaturaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar carregar listagem de Nomenclaturas.");
			erro.printStackTrace();
		}
	}

	// Instancia a classe para novo registro
	public void novo() {
		nomenclatura = new Nomenclatura();
	}

	public void salvar() {
		try {
			NomenclaturaDAO nomenclaturaDAO = new NomenclaturaDAO();
			nomenclaturaDAO.merge(nomenclatura);
			// Reinstancia o Nomenclatura
			novo(); // Limpa a tela após inserção dos dados
			// Recarrega a listagem de Nomenclatura
			nomenclaturas = nomenclaturaDAO.listar();
			Messages.addGlobalInfo("Nomenclatura salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar a Namenclatura.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona a Nomenclatura a ser excluído
			nomenclatura = (Nomenclatura) evento.getComponent().getAttributes().get("nomenclaturaSelecionada");
			// Exclui a namonclatura
			NomenclaturaDAO nomenclaturaDAO = new NomenclaturaDAO();
			nomenclaturaDAO.excluir(nomenclatura);
			// Recarrega a listagem de nomenclatura
			nomenclaturas = nomenclaturaDAO.listar("sigla");
			Messages.addGlobalInfo("Nomenclatura: " + nomenclatura.getSigla() + " excluída com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir Nomenclatura.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona a nomenclatura a ser editada
			nomenclatura = (Nomenclatura) evento.getComponent().getAttributes().get("nomenclaturaSelecionada");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}