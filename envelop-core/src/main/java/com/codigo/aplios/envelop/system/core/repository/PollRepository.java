package com.codigo.aplios.envelop.system.core.repository;

//@Component
//class PollRepository {
//
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Transactional
//	public void save(Poll poll) {
//
//		this.entityManager.persist(poll);
//		this.entityManager.flush();
//	}
//
//	public <T> List<T> findAllBySpecification(Specification<T> specification) {
//
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(specification.getType());
//		Root<T> root = criteriaQuery.from(specification.getType());
//
//		Predicate predicate = specification.toPredicate(root, criteriaBuilder);
//
//		criteriaQuery.where(predicate);
//		return entityManager.createQuery(criteriaQuery).getResultList();
//	}
//
//}