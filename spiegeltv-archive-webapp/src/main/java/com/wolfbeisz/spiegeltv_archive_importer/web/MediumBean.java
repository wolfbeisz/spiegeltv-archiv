package com.wolfbeisz.spiegeltv_archive_importer.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wolfbeisz.spiegeltv_archive_importer.entity.Medium;
import com.wolfbeisz.spiegeltv_archive_importer.web.listener.LocalEntityManagerFactory;

@ManagedBean
@RequestScoped
public class MediumBean {
	private Long mediumId_;
	private Medium medium_;

	private EntityManager em_;
	
	public Long getMediumId() {
		return mediumId_;
	}

	public void setMediumId(Long id) {
		this.mediumId_ = id;
	}
	
	public Medium getMedium() {
		return medium_;
	}

	public void loadMedium() {
		em_ = LocalEntityManagerFactory.createEntityManager();
		TypedQuery<Medium> query = em_.createQuery("select m from Medium m where m.id_ = :id", Medium.class);
		query.setParameter("id", mediumId_);
		Medium displayedMedium = query.getSingleResult();
		medium_ = displayedMedium;
	}
}
