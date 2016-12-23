package com.wolfbeisz.spiegeltv_archive_importer.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Medium {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id_;
	
//	@OneToOne
//	@JoinColumn(name = "RESTAPI_ID")
	@ManyToMany
	@JoinTable(name = "RESTAPI_MEDIUM", joinColumns = @JoinColumn(name="RESTAPI_ID"), inverseJoinColumns = @JoinColumn(name = "MEDIUM_ID"))
	private Collection<RestApi> apis_ = new ArrayList<>();
	
	@OneToMany(mappedBy = "medium_")
	private List<Image> images_ = new ArrayList<>();
	
	@OneToMany(mappedBy = "medium_")
	private List<Tag> tags_ = new ArrayList<>();
	
	@Column(name = "UUID")
	private String uuid_;
	
	@Column(name = "SUBTITLE")
	private String subtitle_;
	
	@Column(name = "AIRDATE")
	private Date airdate_;
	
	@Column(name = "PREMIUM")
	private Boolean isPremium_;
	
	@Column(name = "PARENTMEDIUM")
	private Integer parentMedium_; // TODO: actually reference to a Medium
	
	@Column(name = "ADALLOWED")
	private Boolean adAllowed_;
	
	@Column(name = "MEDIUMID")
	private Integer mediumId_;
	
	@Column(name = "TITLE")
	private String title_;
	
	@Column(name = "TYPE")
	private String type_;
	
	@Column(name = "UPDATED")
	private Date updated_;
	
	@Column(name = "DESCRIPTION", length = 4000)
	private String description_;
	
	@Column(name = "DURATIONINMS")
	private Integer durationInMs_;
	
	@Column(name = "TEASER", length = 4000)
	private String teaser_;
	
	@Column(name = "SLUG")
	private String slug_;
	
	@Column(name = "LANGUAGE")
	private String language_;
	
	@Column(name = "CREATED")
	private Date created_;
	
	@Column(name = "WEBAIRDATE")
	private Date webAirdate_;
	
	@Column(name = "WIDE")
	private Boolean wide_;
	
	public Long getId() {
		return id_;
	}
	public void setId(Long id_) {
		this.id_ = id_;
	}
	public Collection<RestApi> getApi() {
		return apis_;
	}
	public void setApi(Collection<RestApi> apis_) {
		this.apis_ = apis_;
	}
	public List<Image> getImages() {
		return images_;
	}
	public void setImages(List<Image> images_) {
		this.images_ = images_;
	}
	public List<Tag> getTags() {
		return tags_;
	}
	public void setTags(List<Tag> tags_) {
		this.tags_ = tags_;
	}
	public String getUuid() {
		return uuid_;
	}
	public void setUuid(String uuid_) {
		this.uuid_ = uuid_;
	}
	public String getSubtitle() {
		return subtitle_;
	}
	public void setSubtitle(String subtitle_) {
		this.subtitle_ = subtitle_;
	}
	public Date getAirdate() {
		return airdate_;
	}
	public void setAirdate(Date airdate_) {
		this.airdate_ = airdate_;
	}
	public Boolean getIsPremium() {
		return isPremium_;
	}
	public void setIsPremium(Boolean isPremium_) {
		this.isPremium_ = isPremium_;
	}
	public Integer getParentMedium() {
		return parentMedium_;
	}
	public void setParentMedium(Integer parentMedium_) {
		this.parentMedium_ = parentMedium_;
	}
	public Boolean getAdAllowed() {
		return adAllowed_;
	}
	public void setAdAllowed(Boolean adAllowed_) {
		this.adAllowed_ = adAllowed_;
	}
	public Integer getMediumId() {
		return mediumId_;
	}
	public void setMediumId(Integer mediumId_) {
		this.mediumId_ = mediumId_;
	}
	public String getTitle() {
		return title_;
	}
	public void setTitle(String title_) {
		this.title_ = title_;
	}
	public String getType() {
		return type_;
	}
	public void setType(String type_) {
		this.type_ = type_;
	}
	public Date getUpdated() {
		return updated_;
	}
	public void setUpdated(Date updated_) {
		this.updated_ = updated_;
	}
	public String getDescription() {
		return description_;
	}
	public void setDescription(String description_) {
		this.description_ = description_;
	}
	public Integer getDurationInMs() {
		return durationInMs_;
	}
	public void setDurationInMs(Integer durationInMs_) {
		this.durationInMs_ = durationInMs_;
	}
	public String getTeaser() {
		return teaser_;
	}
	public void setTeaser(String teaser_) {
		this.teaser_ = teaser_;
	}
	public String getSlug() {
		return slug_;
	}
	public void setSlug(String slug_) {
		this.slug_ = slug_;
	}
	public String getLanguage() {
		return language_;
	}
	public void setLanguage(String language_) {
		this.language_ = language_;
	}
	public Date getCreated() {
		return created_;
	}
	public void setCreated(Date created_) {
		this.created_ = created_;
	}
	public Date getWebAirdate() {
		return webAirdate_;
	}
	public void setWebAirdate(Date webAirdate_) {
		this.webAirdate_ = webAirdate_;
	}
	public Boolean getWide() {
		return wide_;
	}
	public void setWide(Boolean wide_) {
		this.wide_ = wide_;
	}

	
}
