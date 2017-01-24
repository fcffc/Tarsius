package br.jus.tjmt.tarsius.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.jus.tjmt.tarsius.domain.Fluxo;
import br.jus.tjmt.tarsius.util.HibernateUtil;

public class FluxoDAO extends GenericDAO<Fluxo> {
	@SuppressWarnings("unchecked")
	public List<Fluxo> buscarPorProcesso(Long processoCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Fluxo.class);
			// Where para localizar o fluxo pelo código.
			consulta.add(Restrictions.eq("processo.codigo", processoCodigo));
			consulta.addOrder(Order.asc("processo"));
			List<Fluxo> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
