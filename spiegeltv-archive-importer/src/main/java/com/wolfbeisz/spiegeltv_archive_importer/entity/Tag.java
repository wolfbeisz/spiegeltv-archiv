package com.wolfbeisz.spiegeltv_archive_importer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Tag {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id_;
	
	@OneToOne
	@JoinColumn(name = "MEDIUM_ID")
	private Medium medium_;
	
	@Column(name = "NAME")
	private String name_;

	public Long getId() {
		return id_;
	}

	public void setId(Long id_) {
		this.id_ = id_;
	}

	public Medium getMedium() {
		return medium_;
	}

	public void setMedium(Medium medium_) {
		this.medium_ = medium_;
	}

	public String getName() {
		return name_;
	}

	public void setName(String name_) {
		this.name_ = name_;
	}
}
