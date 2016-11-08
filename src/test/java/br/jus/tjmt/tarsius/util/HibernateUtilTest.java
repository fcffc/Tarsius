package br.jus.tjmt.tarsius.util;

import org.hibernate.Session;
import org.junit.Test;

// Testa a fabrica de sessao
public class HibernateUtilTest {
	@Test
	public void conectar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
