package br.jus.tjmt.tarsius.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.jus.tjmt.tarsius.util.HibernateUtil;
import br.jus.tjmt.tarsius.domain.Checklist;

public class ChecklistDAO extends GenericDAO<Checklist> {
	@SuppressWarnings("unchecked")
	public List<Checklist> buscarPorTipoInspecao(Long tipoInspecaoCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Checklist.class);
			// Where para localizar o tipo pelo código.
			consulta.add(Restrictions.eq("tipoInspecao.codigo", tipoInspecaoCodigo));
			consulta.addOrder(Order.asc("tipoInspecao"));
			List<Checklist> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}