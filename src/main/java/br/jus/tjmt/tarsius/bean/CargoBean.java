package br.jus.tjmt.tarsius.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

	// Instancia a classe para novo registro
	public void novo() {
		cargo = new Cargo();
	}

	public void salvar() {
		try {
			CargoDAO cargoDAO = new CargoDAO();
			cargoDAO.salvar(cargo);
			novo(); // Limpa a tela após inserção dos dados
			Messages.addGlobalInfo("Cargo salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar salvar o Cargo.");
			erro.printStackTrace();
		}
	}
}
