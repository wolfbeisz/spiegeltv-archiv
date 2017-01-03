package com.wolfbeisz.spiegeltv_archive_importer.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class RestApi {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id_;
	
	@ManyToMany(mappedBy = "apis_")
	private Collection<Medium> media_ = new ArrayList<>();
	
	@Column(name = "VERSION")
	private Date version_;

	public Long getId() {
		return id_;
	}

	public void setId(Long id_) {
		this.id_ = id_;
	}

	public Collection<Medium> getMedia() {
		return media_;
	}

	public void setMedia(Collection<Medium> media_) {
		this.media_ = media_;
	}

	public Date getVersion() {
		return version_;
	}

	public void setVersion(Date version_) {
		this.version_ = version_;
	}
}
