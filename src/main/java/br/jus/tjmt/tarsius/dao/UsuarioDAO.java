package br.jus.tjmt.tarsius.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.jus.tjmt.tarsius.domain.Usuario;
import br.jus.tjmt.tarsius.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	public Usuario autenticar(String matricula, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			consulta.add(Restrictions.eq("p.matricula", matricula));
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
//	Já com criptografia, voltar se der pau ->	consulta.add(Restrictions.eq("senha", senha));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}