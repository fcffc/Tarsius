package br.jus.tjmt.tarsius.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
//Notação da classe dizendo que não é uma table, mas que será usada por
//outros para gerar table
@SuppressWarnings("serial")
@MappedSuperclass
public class GenericDomain implements Serializable {
	//Chave primária automática pelo banco de outras tabelas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)// Auto incremento
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	
	
	

}
