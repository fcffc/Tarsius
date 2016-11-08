package br.jus.tjmt.tarsius.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	// Lê o arquivo hibernate.cfg.xml
	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	private static SessionFactory criarFabricaDeSessoes() {
		try {
			// Busca o arquivo hibernate.cfg e lê
			Configuration configuracao = new Configuration().configure();

			// Registra o serviço
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties())
					.build();

			// Cria a fábrica de sessão
			SessionFactory fabrica = configuracao.buildSessionFactory(registro);
			return fabrica;
		} catch (Throwable ex) {
			System.err.println("A fábrica de sessão não pode ser criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
