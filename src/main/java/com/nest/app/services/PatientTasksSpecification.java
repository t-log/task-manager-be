package com.nest.app.services;

import com.nest.app.dto.PatientTasksDTO;
import com.nest.app.dto.RequestBodyDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class PatientTasksSpecification {

    public static Specification<PatientTasksDTO> whichAllPrioritySelected(RequestBodyDTO body){

        return new Specification<PatientTasksDTO>() {
            @Override
            public Predicate toPredicate(Root<PatientTasksDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(String.valueOf(body.getPriority().get(0).get("high"))),true);
            }
        };
    }

    public static Specification<PatientTasksDTO> whichAllStatusSelected(){
        return null;
    }

}
