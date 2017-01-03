package com.wolfbeisz.spiegeltv_archive_importer.model;

import javax.xml.bind.annotation.XmlElement;

public class Image {
	private String url_;
	private Integer width_;
	@XmlElement(name="spec_slug")
	private String spec_slug_;
	private Integer height_;
	
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
	public String getSpec_slug() {
		return spec_slug_;
	}
	public void setSpec_slug(String spec_slug_) {
		this.spec_slug_ = spec_slug_;
	}
	public Integer getHeight() {
		return height_;
	}
	public void setHeight_(Integer height_) {
		this.height_ = height_;
	}
}
