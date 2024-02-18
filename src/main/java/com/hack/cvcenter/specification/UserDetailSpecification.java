package com.hack.cvcenter.specification;

import com.hack.cvcenter.model.UserDetail;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class UserDetailSpecification {

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

}
