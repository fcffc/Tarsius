package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.jus.tjmt.tarsius.dao.CargoDAO;
import br.jus.tjmt.tarsius.domain.Cargo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CargoBean implements Serializable {
	private Cargo cargo;
	private List<Cargo> cargos;

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	// Chama automaticamente o método Listar
	@PostConstruct
	public void listar() {
		try {
			CargoDAO cargoDAO = new CargoDAO();
			cargos = cargoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar carregar listagem de Cargos.");
			erro.printStackTrace();
		}
	}

	// Inicia uma nova instancia ao abrir a tela de cadastro/edição
	public void novo() {
		cargo = new Cargo();
	}

	public void salvar() {
		try {
			CargoDAO cargoDAO = new CargoDAO();
			cargoDAO.merge(cargo);
			// Reinstancia o Cargo
			novo(); // Limpa a tela após inserção dos dados
			// Recarrega a listagem de cargo
			cargos = cargoDAO.listar();
			Messages.addGlobalInfo("Cargo salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar o Cargo.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// Seleciona o cargo a ser excluído
			cargo = (Cargo) evento.getComponent().getAttributes().get("cargoSelecionado");
			// Exclui o cargo
			CargoDAO cargoDAO = new CargoDAO();
			cargoDAO.excluir(cargo);
			// Recarrega a listagem de cargo
			cargos = cargoDAO.listar("nome");
			Messages.addGlobalInfo("Cargo : " + cargo.getNome() + " excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao excluir Cargo.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			// Seleciona o estado a ser editado
			cargo = (Cargo) evento.getComponent().getAttributes().get("cargoSelecionado");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Falha ao carregar tela de edição.");
			erro.printStackTrace();
		}
	}
}