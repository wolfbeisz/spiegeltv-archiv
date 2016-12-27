package com.wolfbeisz.spiegeltv_archive_importer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import com.wolfbeisz.spiegeltv_archive_importer.dao.MediumDao;
import com.wolfbeisz.spiegeltv_archive_importer.entity.Medium;
import com.wolfbeisz.spiegeltv_archive_importer.entity.RestApi;

public class DeduplicationService {
	
	private MediumDao mediumDao_;
	private TypedQuery<Medium> findMediumByMediumIdAndUpdateDateQuery_;
	
	public DeduplicationService(MediumDao mediumDao) {
		mediumDao_ = mediumDao;
		findMediumByMediumIdAndUpdateDateQuery_ = mediumDao_.createFindMediumByMediumIdQuery();
//		findMediumByMediumIdAndUpdateDateQuery_.setFlushMode(FlushModeType.COMMIT);
	}
	
	public void deduplicateMedia(RestApi api) {
		List<Medium> media = new ArrayList<>(api.getMedia()); // copy to avoid concurrent modification
		/** unmanaged -> managed entities*/
		Map<Medium, Medium> duplicates = new HashMap<>();
		
		for (Medium medium : media) {
			// do query
			List<Medium> equalMedia = mediumDao_.findMediumByMediumId(findMediumByMediumIdAndUpdateDateQuery_, medium.getMediumId(), medium.getUpdated());
			
			// if (result exists)
			if (equalMedia.size() == 1) {
				Medium result = equalMedia.get(0);
				duplicates.put(medium, result);

			} else if (equalMedia.size() > 1) {
				throw new IllegalStateException("database state is illegal");
			}
		}
		
		for (Medium duplicate : duplicates.keySet()) {
			// remove relationship with obsolete object
			api.getMedia().remove(duplicate);
			duplicate.getApi().remove(api); // not necessary ?

			Medium existing = duplicates.get(duplicate);
			// create relationship with new object
			api.getMedia().add(existing); 
			existing.getApi().add(api); // api is (still) unmanaged; next query (findMediumByMediumId) will try to  perform a flush before executing; when flushing: persistence provider will find unmanaged api-entity and throw an exception 
		}
		
	}
}
