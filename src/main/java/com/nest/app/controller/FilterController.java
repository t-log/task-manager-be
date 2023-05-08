package com.nest.app.controller;

import com.nest.app.dto.PatientTasksDTO;
import com.nest.app.dto.RequestBodyDTO;
import com.nest.app.entity.Patient;
import com.nest.app.entity.Tasks;
import com.nest.app.services.FilterServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FilterController {

    @Autowired
    FilterServiceImpl filterService;

    @PersistenceContext
    EntityManager entityManager;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public List dynamicFilter(@RequestParam(value = "filter") Boolean filterFlag, @RequestBody(required = false) RequestBodyDTO body){

        if(filterFlag){
            //checking whether the front end request is mapped to RequestBodyDTO type
            System.out.println(body.getPriority());
            System.out.println(body.getStatus());
            System.out.println(body.getPreset());
            System.out.println(body.getPriority().get(1).get("medium"));
            System.out.println(body.getPriority().size());
            System.out.println("Body Of Filter "+body);

            //Simplifying the request body for sanity :)

            List<String> priorityList = new ArrayList<String>();
            List<String> statusList = new ArrayList<String>();
            List<LocalDateTime> preset = new ArrayList<LocalDateTime>();

            //For Priority
            if(body.getPriority().get(0).get("high")){
                priorityList.add("High");
            }
            if(body.getPriority().get(1).get("medium")){
                priorityList.add("Medium");
            }
            if(body.getPriority().get(2).get("low")){
                priorityList.add("Low");
            }

            //For Status
            if(body.getStatus().get(0).get("not started")){
                statusList.add("Not Started");
            }
            if(body.getStatus().get(1).get("on hold")){
                statusList.add("On Hold");
            }
            if(body.getStatus().get(2).get("in progress")){
                statusList.add("In Progress");
            }
            if(body.getStatus().get(3).get("completed")){
                statusList.add("Completed");
            }
            if(body.getStatus().get(4).get("over due")){
                statusList.add("Overdue");
            }

            //For Preset (aka dueDate)
            DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MMM/dd/yyyy HH:mm");
            LocalDateTime dateAndTimeNow = LocalDateTime.now();
            LocalDateTime dateAndTimeNowPlusPreset = dateAndTimeNow.plusHours(body.getPreset());
            System.out.println("Current Date and Time is "+dateTime.format(dateAndTimeNow));
            preset.add(dateAndTimeNow);
            preset.add(dateAndTimeNow.plusHours(body.getPreset()));
            System.out.println("Preset value is "+body.getPreset());
            System.out.println("Preset Adjusted for between clause is "+preset);


            System.out.println("Priority & status is "+ priorityList +""+statusList);

            LocalDateTime trail = dateAndTimeNow.plusHours(body.getPreset());

            System.out.println("plus preset is" +trail);

            System.out.println("Date time values after check"+dateAndTimeNow+"Plus Preset"+dateAndTimeNowPlusPreset);


            //criteria api
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Tasks.class);
            Root<Tasks> root = query.from(Tasks.class);
            query.select(root);
            Join<Patient, Tasks> patientTasks = root.join("patient");

            List<Predicate> predicates = new ArrayList<>();

            if ( !body.getPriority().isEmpty() ) {
//                predicates.add(root.get("priority").in(priorityList));

                builder.and(root.get("priority").in(priorityList));
            }

            if ( !body.getStatus().isEmpty()) {
//                predicates.add(root.get("taskStatus").in(statusList));
                  builder.and(root.get("taskStatus").in(statusList));
            }

            if (!(body.getPreset() ==0)) {
//                predicates.add(builder.between(root.get("dueDate"), dateAndTimeNow, dateAndTimeNowPlusPreset));
                  builder.between(root.get("dueDate"),dateAndTimeNow, dateAndTimeNowPlusPreset);
            }


            System.out.println("Predicates are"+predicates.toArray(new Predicate[0]));

            query.where(predicates.toArray(new Predicate[0]));



            TypedQuery typedQuery = entityManager.createQuery(query);
            System.out.println("Result List is"+ typedQuery.getResultList());
            return typedQuery.getResultList();
        }

        else {
            return null;
        }

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public List<PatientTasksDTO> viewDetails(@RequestParam(value = "filter") Boolean filterFlag,@RequestBody(required = false) RequestBodyDTO body){
        if(!filterFlag){
            return filterService.viewDetails();
        }
        else{
            return null;
        }
    }

}
