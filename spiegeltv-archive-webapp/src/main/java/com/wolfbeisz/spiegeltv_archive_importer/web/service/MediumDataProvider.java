package com.wolfbeisz.spiegeltv_archive_importer.web.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.event.ListSelectionEvent;

import com.wolfbeisz.spiegeltv_archive_importer.entity.Medium;
import com.wolfbeisz.spiegeltv_archive_importer.web.model.FilterOption;
import com.wolfbeisz.spiegeltv_archive_importer.web.model.LoadOptions;
import com.wolfbeisz.spiegeltv_archive_importer.web.model.SortOption;

public class MediumDataProvider {
	
	private EntityManager em_;
	
	
	public MediumDataProvider(EntityManager em) {
		em_ = em;
	}
	
	public List<Medium> loadData(LoadOptions loadOptions) { 
//		TypedQuery<Medium> query = em_.createQuery("select m from Medium m left join Medium m2 on m.mediumId_ = m2.mediumId_ and m.updated_ < m2.updated_ where m2 is null", Medium.class);
//		TypedQuery<Medium> query = em_.createQuery("select m from Medium m where m.id_ in (select max(m1.id_) from Medium m1 group by m1.mediumId_)", Medium.class);
//		query.setFirstResult(loadOptions.getSkipItems());
//		query.setMaxResults(loadOptions.getTakeItems());
//		return query.getResultList();
		
//		TypedQuery<Long> idQuery = em_.createQuery("select max(m.id_) as id from Medium m group by m.mediumId_ order by id", Long.class);
//		idQuery.setFirstResult(loadOptions.getSkipItems());
//		idQuery.setMaxResults(loadOptions.getTakeItems());
//		List<Long> ids = idQuery.getResultList();

		CriteriaBuilder cb = em_.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<Medium> root = criteriaQuery.from(Medium.class);
		criteriaQuery.select(cb.max(root.<Long>get("id_")));
		applyFilters(cb, criteriaQuery, root, loadOptions.getFilters());
		criteriaQuery.groupBy(root.<Integer>get("mediumId_"));
		criteriaQuery.orderBy(cb.asc(cb.max(root.<Long>get("id_"))));
		
		TypedQuery<Long> criteriaIdQuery = em_.createQuery(criteriaQuery);
		criteriaIdQuery.setFirstResult(loadOptions.getSkipItems());
		criteriaIdQuery.setMaxResults(loadOptions.getTakeItems());
		List<Long> ids = criteriaIdQuery.getResultList();
		
		if (ids.isEmpty())
			return  Collections.EMPTY_LIST;
		
		TypedQuery<Medium> mediumQuery = em_.createQuery("select m from Medium m where m.id_ in :ids", Medium.class);
		mediumQuery.setParameter("ids", ids);
		return mediumQuery.getResultList();
	}
	
	private void applyFilters(CriteriaBuilder cb, CriteriaQuery<?> query, Root<Medium> root, List<FilterOption> filters) {
		List<Predicate> predicates = new ArrayList<>();
		
		for (FilterOption filter : filters) {
			String memberName = filter.getField(); // value of p:column.field
			Serializable value = filter.getValue();
			Predicate predicate = null;
			if (value instanceof String && isMemberOfTypeString(Medium.class, memberName)) {
				predicate = cb.like(root.<String>get(memberName), "%"+value+"%");
			} else {
				predicate = cb.equal(root.get(memberName), value);
			}
			predicates.add(predicate);
		}
		query.where(predicates.toArray(new Predicate[]{}));
	}
	
	private boolean isMemberOfTypeString(Class<?> clazz, String member) {
		try {
			return String.class.equals(Medium.class.getDeclaredField(member).getType());
		} catch (Exception e) {
			return false;
		}
	}
	
	public  long loadRowCount(LoadOptions loadOptions) {
//		TypedQuery<Long> query = em_.createQuery("select count(distinct m.mediumId_) from Medium m", Long.class);
		
		CriteriaBuilder cb = em_.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<Medium> root = criteriaQuery.from(Medium.class);
		applyFilters(cb, criteriaQuery, root, loadOptions.getFilters());
		criteriaQuery.select(cb.countDistinct(root.get("mediumId_")));
		
		TypedQuery<Long> query = em_.createQuery(criteriaQuery);
		return query.getSingleResult();
	}
	
//    public List<Medium> getObjects(LoadOptions loadOptions) {
//        Session session = sessionFactory.getCurrentSession();
//        
//        PagedList<Student> result = new PagedList<>();
//        try {
//            Criteria countCriteria = session.createCriteria(StudentEntity.class);
//            fillCriteria(countCriteria, loadOptions, true);
// 
//            long rowsCount = (long) countCriteria.uniqueResult();
// 
//            if (rowsCount > 0) {
//                Criteria criteria = session.createCriteria(StudentEntity.class);
//                fillCriteria(criteria, loadOptions, false);
// 
//                List<StudentEntity> dataEntities = criteria.list();
//                for (StudentEntity de : dataEntities) {
//                    Student student = mappingService.map(de, Student.class);
//                    result.add(student);
//                }
//            }
// 
//            result.setTotalSize(rowsCount);
//        } catch (Exception ex) {
//            LOG.error("Error loading students", ex);
//        }
// 
//        return result;
//    }
// 
//private void fillCriteria(Criteria source, LoadOptions loadOptions, boolean countOnly) {
//        List<FilterOption> filters = loadOptions.getFilters();
//        if (CollectionUtils.hasItems(filters)) {
//            for (FilterOption f : filters) {
//                source.add(Restrictions.eq(f.getField(), f.getValue()));
//            }
//        }
// 
//        List<SortOption> sorts = loadOptions.getSorts();
//        Collections.sort(sorts);
// 
//        if (CollectionUtils.hasItems(sorts)) {
//            for (SortOption s : sorts) {
//                switch (s.getSortDirection()) {
//                case ASCENDING:
//                    source.addOrder(Order.asc(s.getSortField()));
//                    break;
//                case DESCENDING:
//                    source.addOrder(Order.desc(s.getSortField()));
//                    break;
//                default:
//                    break;
//                }
//            }
//        }
// 
//        if (countOnly) {
//            source.setProjection(Projections.rowCount());
//        } else {
//            source.setFirstResult(loadOptions.getSkipItems());
//            source.setMaxResults(loadOptions.getTakeItems());
//        }
//    }
}
