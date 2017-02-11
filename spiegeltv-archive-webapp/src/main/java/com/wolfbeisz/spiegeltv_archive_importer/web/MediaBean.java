package com.wolfbeisz.spiegeltv_archive_importer.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import com.wolfbeisz.spiegeltv_archive_importer.entity.Medium;
import com.wolfbeisz.spiegeltv_archive_importer.web.listener.LocalEntityManagerFactory;
import com.wolfbeisz.spiegeltv_archive_importer.web.model.MediumDataModel;
import com.wolfbeisz.spiegeltv_archive_importer.web.service.MediumDataProvider;

@ManagedBean
@ViewScoped 
public class MediaBean implements Serializable {
	
	// TODO: restore useful values after de-serialization
	private transient EntityManager em_;
	private transient MediumDataModel dm_;
	private Medium selectedMedium_;
	
	public Medium getSelectedMedium() {
		return selectedMedium_;
	}

	public void setSelectedMedium(Medium selectedMedium) {
		this.selectedMedium_ = selectedMedium;
	}

	public MediumDataModel getDm() {
		return dm_;
	}

	@PostConstruct
	public void init() {
		em_ = LocalEntityManagerFactory.createEntityManager();
		
		MediumDataProvider mediumDataProvider = new MediumDataProvider(em_);
		dm_ = new MediumDataModel(mediumDataProvider);
	}
	
	public String m3u8StreamUrl(Medium medium) {
		if (medium.getWide()) {
			return "http://sptv-vod.dcp.adaptive.level3.net/"+ medium.getUuid() +"_spiegeltv_0500_16x9.m4v.m3u8";
		}
		return "http://sptv-vod.dcp.adaptive.level3.net/"+ medium.getUuid() +"_spiegeltv_0500_4x3.m4v.m3u8";
	}
	
	public String rtmpStreamUrl(Medium medium) {
		if (medium.getWide()) {
			return "rtmp://s3pwwozuj5hrsa.cloudfront.net/cfx/st/mp4:"+ medium.getUuid() + "_spiegeltv_0500_16x9.m4v";
		}
		return "rtmp://s3pwwozuj5hrsa.cloudfront.net/cfx/st/mp4:"+ medium.getUuid() + "_spiegeltv_0500_4x3.m4v";
	}
}
