package com.wolfbeisz.spiegeltv_archive_importer.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wolfbeisz.spiegeltv_archive_importer.entity.Medium;

public class MediumDao {
	
	public static final String JPQL_FIND_MEDIUM_BY_MEDIUMID_AND_UPDATED = "select m from Medium m where m.mediumId_ = :mediumId and m.updated_ = :updated";
	
	private EntityManager em_;

	public MediumDao(EntityManager em) {
		em_ = em;
	}
	
	public void persist(Medium medium)
	{
		em_.persist(medium);
	}
	
	public TypedQuery<Medium> createFindMediumByMediumIdQuery() {
//		return em_.createNamedQuery("Medium_findMediumyByMediumId", Medium.class);
		return em_.createQuery(JPQL_FIND_MEDIUM_BY_MEDIUMID_AND_UPDATED, Medium.class);
	}
	
	/**
	 * 
	 * @param query
	 * @param mediumId
	 * @return 
	 */
	public List<Medium> findMediumByMediumId(TypedQuery<Medium> query, Integer mediumId, Date updated) {
		query.setParameter("mediumId", mediumId);
		query.setParameter("updated", updated);
		return query.getResultList();
	}
}
