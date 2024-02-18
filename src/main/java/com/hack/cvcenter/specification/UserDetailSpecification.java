package com.hack.cvcenter.specification;

import com.hack.cvcenter.model.UserDetail;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Set;

public class UserDetailSpecification implements Specification<UserDetail>, Serializable {

    public static Specification<UserDetail> withRole(String role) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userInfo").get("role"), role);
    }

    public static Specification<UserDetail> withYearOfExp(Integer yearOfExp) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userInfo").get("totalYearOfExp"), yearOfExp);
    }

    public static Specification<UserDetail> withState(String state) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userInfo").get("state"), state);
    }

    public static Specification<UserDetail> withSkills(Set<String> skills) {
        return (root, query, criteriaBuilder) ->
                root.join("skills").get("skill").in(skills);
    }

    @Override
    public Predicate toPredicate(Root<UserDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    @Override
    public String toString() {
        // Provide a meaningful representation for the Specification
        return "UserDetailSpecification";
    }
}
