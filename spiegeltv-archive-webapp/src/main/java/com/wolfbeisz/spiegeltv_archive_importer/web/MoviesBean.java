package com.wolfbeisz.spiegeltv_archive_importer.web;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import com.wolfbeisz.spiegeltv_archive_importer.entity.RestApi;
import com.wolfbeisz.spiegeltv_archive_importer.web.listener.LocalEntityManagerFactory;

@ManagedBean(name="moviesBean")
@RequestScoped
public class MoviesBean {
	private EntityManager em_;
	private List<RestApi> apis_;
	private Date latestRestapi_;
	
	
	public List<RestApi> getApis() {
		return apis_;
	}


	@PostConstruct
	public void init() {
		em_ = LocalEntityManagerFactory.createEntityManager();
		apis_ = em_.createQuery("select r from RestApi r", RestApi.class).getResultList();
		latestRestapi_ = em_.createQuery("select MAX(r.version_) from RestApi r", Date.class).getSingleResult();
	}
}
