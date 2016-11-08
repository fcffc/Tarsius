package br.jus.tjmt.tarsius.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.jus.tjmt.tarsius.util.HibernateUtil;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAO<Entidade> {
	// API reflection : Pega qual é o tipo da classe filha
	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(Entidade entidade) {
		// Capturar uma sessão aberta
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		// Declarar um objeto p/ controlar as transações
		Transaction transacao = null;
		try {
			// Abre e salva a transação
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			// Verifica se a transação foi aberta
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		}
		// Se der certo ou errado finaliza a sessão
		finally {
			sessao.close();
		}
	}

	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public Entidade buscar(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			// Compara com a chave primária da entidade
			consulta.add(Restrictions.idEq(codigo));
			// Retorna apenas um resultado
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void editar(Entidade entidade) {
		// Capturar uma sessão aberta
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		// Declarar um objeto p/ controlar as transações
		Transaction transacao = null;
		try {
			// Abre e Altera a transação
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			// Verifica se a transação foi aberta
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		}
		// Se der certo ou errado finaliza a sessão
		finally {
			sessao.close();
		}
	}

	public void merge(Entidade entidade) {
		// Capturar uma sessão aberta
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		// Declarar um objeto p/ controlar as transações
		Transaction transacao = null;
		try {
			// Abre e salva a transação
			transacao = sessao.beginTransaction();
			sessao.merge(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			// Verifica se a transação foi aberta
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		}
		// Se der certo ou errado finaliza a sessão
		finally {
			sessao.close();
		}
	}
}
