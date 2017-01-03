package com.wolfbeisz.spiegeltv_archive_importer.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Medium {
	
	private String uuid_;
	private String subtitle_;
	private String airdate_;
	@XmlElement(name="is_premium")
	private Boolean isPremium_;
	@XmlElement(name="parent_medium")
	private Integer parentMedium_;
	@XmlElement(name = "is_ad_allowed")
	private Boolean adAllowed_;
	private Integer id_;
	private String title_;
	private String type_;
	private String updated_;
	private String description_;
	@XmlElement(name="duration_in_ms")
	private Integer durationInMs_;
	private String teaser_;
	private String slug_;
	private String language_;
	private String created_;
	@XmlElement(name="web_airdate")
	private String webAirdate_;
	@XmlElement(name = "is_wide")
	private Boolean wide_;
	
	/** complex members */
	@XmlElement(name="tags")
	private List<String> tags_;
	
	@XmlElement(name="images")
	private List<Image> images_;

	public String getSubtitle() {
		return subtitle_;
	}

	public void setSubtitle(String subtitle_) {
		this.subtitle_ = subtitle_;
	}

	public String getAirdate() {
		return airdate_;
	}

	public void setAirdate(String airdate_) {
		this.airdate_ = airdate_;
	}

	public Boolean isPremium() {
		return isPremium_;
	}

	public void setPremium(Boolean isPremium_) {
		this.isPremium_ = isPremium_;
	}

	public Integer getParentMedium() {
		return parentMedium_;
	}

	public void setParentMedium(Integer parentMedium_) {
		this.parentMedium_ = parentMedium_;
	}

	public Boolean isAdAllowed() {
		return adAllowed_;
	}

	public void setAdAllowed(Boolean adAllowed) {
		this.adAllowed_ = adAllowed;
	}

	public Integer getId() {
		return id_;
	}

	public void setId(Integer id_) {
		this.id_ = id_;
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

	public String getUpdated() {
		return updated_;
	}

	public void setUpdated(String updated_) {
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

	public String getUuid() {
		return uuid_;
	}

	public void setUuid(String uuid_) {
		this.uuid_ = uuid_;
	}

	public String getLanguage() {
		return language_;
	}

	public void setLanguage(String language_) {
		this.language_ = language_;
	}

	public String getCreated() {
		return created_;
	}

	public void setCreated(String created_) {
		this.created_ = created_;
	}

	public String getWebAirdate() {
		return webAirdate_;
	}

	public void setWebAirdate(String webAirdate_) {
		this.webAirdate_ = webAirdate_;
	}

	public Boolean isWide() {
		return wide_;
	}

	public void setWide(Boolean wide) {
		this.wide_ = wide;
	}

	public List<String> getTags() {
		return tags_;
	}

	public void setTags(List<String> tags) {
		this.tags_ = tags;
	}

	public List<Image> getImages() {
		return images_;
	}
	
}
