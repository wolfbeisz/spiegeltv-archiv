package com.wolfbeisz.spiegeltv_archive_importer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Image {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id_;
	
	@OneToOne
	@JoinColumn(name = "MEDIUM_ID")
	private Medium medium_;
	
	@Column(name = "URL")
	private String url_;
	
	@Column(name = "WIDTH")
	private Integer width_;
	
	@Column(name = "SPECSLUG")
	private String specSlug_;
	
	@Column(name = "HEIGHT")
	private Integer height_;
	
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
	public String getUrl() {
		return url_;
	}
	public void setUrl(String url_) {
		this.url_ = url_;
	}
	public Integer getWidth() {
		return width_;
	}
	public void setWidth(Integer width_) {
		this.width_ = width_;
	}
	public String getSpecSlug() {
		return specSlug_;
	}
	public void setSpecSlug(String specSlug_) {
		this.specSlug_ = specSlug_;
	}
	public Integer getHeight() {
		return height_;
	}
	public void setHeight(Integer height_) {
		this.height_ = height_;
	}
}
