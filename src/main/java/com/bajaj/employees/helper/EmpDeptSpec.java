package com.bajaj.employees.helper;

import com.bajaj.employees.bean.SearchCriteria;
import com.bajaj.employees.bean.SearchOperation;
import com.bajaj.employees.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmpDeptSpec implements Specification<EmployeeEntity> {
    //private static final long serialVersionUID = 1900581010229669687L;
    //private SearchCriteria criteria;
    private final List<SearchCriteria> list;

    public EmpDeptSpec() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }
    /**
     * Basic Specification Logic - Requires EmployeeBean_ and DepartmentBean_
     * This logic was used before changed to Dynamic Specification for Queries
     */
//    public Specification<EmployeeEntity> nameLike(String name) {
//        return new Specification<EmployeeEntity>() {
//            @Override
//            public Predicate toPredicate(Root<EmployeeEntity> root,
//                                         CriteriaQuery<?> query,
//                                         CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.like(root.get(EmployeeBean_.EMPLOYEE_NAME), "%" + name + "%");
//            }
//        };
//    }
//    public Specification<EmployeeEntity>  nameWhereSalaryAbove(int salary) {
//        return new Specification<EmployeeEntity>() {
//            @Override
//            public Predicate toPredicate(Root<EmployeeEntity> root,
//                                         CriteriaQuery<?> query,
//                                         CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.greaterThan(root.get(EmployeeBean_.EMPLOYEE_SALARY),">"+salary);
//            }
//        };
//    }

    /**
     *
     * @param root must not be {@literal null}.
     * @param query must not be {@literal null}.
     * @param criteriaBuilder must not be {@literal null}.
     * @return
     */
//    @Override
//    public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        //create a new predicate list
//        if (criteria.getOperation().equalsIgnoreCase(">")) {
//            return criteriaBuilder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()),
//                    criteria.getValue().toString());
//        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
//            return criteriaBuilder.lessThanOrEqualTo(root.<String>get(criteria.getKey()),
//                    criteria.getValue().toString());
//        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
//            if (root.get(criteria.getKey()).getJavaType() == String.class) {
//                return criteriaBuilder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
//            } else {
//                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
//            }
//        }
//        return null;
//    }

    /**
     * @param root            must not be {@literal null}.
     * @param query           must not be {@literal null}.
     * @param criteriaBuilder must not be {@literal null}.
     * @return Dynamic Specification for Queries
     */
    @Override
    public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(criteriaBuilder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(criteriaBuilder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(criteriaBuilder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(criteriaBuilder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_IGNORE_CASE)) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_CASE)) {
                predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()),
                        criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } else if (criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(criteriaBuilder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
