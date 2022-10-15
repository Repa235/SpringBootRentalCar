package com.example.springbootrentalcar.specifications;

import com.example.springbootrentalcar.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<User> {

    String filter;
    String textToSearch;

    public UserSpecification(String filter, String textToSearch) {
        this.filter = filter;
        this.textToSearch = textToSearch;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate filterPred = criteriaBuilder.like(root.get(filter), "%"+textToSearch+"%");
        Predicate customerPred = criteriaBuilder.isFalse(root.get("isAdmin"));
        return criteriaBuilder.and(filterPred,customerPred);
    }
}
