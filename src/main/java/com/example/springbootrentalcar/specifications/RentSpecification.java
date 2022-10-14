package com.example.springbootrentalcar.specifications;

import com.example.springbootrentalcar.entity.Rent;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RentSpecification implements Specification<Rent> {

    private DateInterval dateInterval;

    public RentSpecification(DateInterval dateInterval) {
        this.dateInterval = dateInterval;
    }


    @Override
    public Predicate toPredicate(Root<Rent> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate checkLimitInizio = criteriaBuilder.lessThanOrEqualTo(
                root.get("startDate"), dateInterval.getStartDate());
        Predicate checkLimitFine = criteriaBuilder.greaterThanOrEqualTo(
                root.get("endDate"), dateInterval.getEndDate());
        Predicate limits = criteriaBuilder.and(checkLimitFine, checkLimitInizio);

        Predicate checkInizio = criteriaBuilder.between(
                root.get("startDate"), dateInterval.getStartDate(), dateInterval.getEndDate());
        Predicate checkfine = criteriaBuilder.between(
                root.get("endDate"), dateInterval.getStartDate(), dateInterval.getEndDate());


        Predicate dates = criteriaBuilder.or(checkInizio, checkfine, limits);

        Predicate approved = criteriaBuilder.equal(root.<Boolean>get("isApproved"), true);

        return criteriaBuilder.and(dates,approved);
    }
}
