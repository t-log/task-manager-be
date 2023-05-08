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
    public List<PatientTasksDTO> dynamicFilter(@RequestParam(value = "filter") Boolean filterFlag,@RequestBody(required = false) RequestBodyDTO body){

        if(filterFlag){
            //checking whether the front end request is mapped to RequestBodyDTO type
            System.out.println(body.getPriority());
            System.out.println(body.getStatus());
            System.out.println(body.getPreset());
            System.out.println(body.getPriority().get(1).get("medium"));
            System.out.println(body.getPriority().size());
            System.out.println("Body Of Filter "+body);

            //Simplifying the request body for sanity :)

            List<String> priority = new ArrayList<String>();
            List<String> status = new ArrayList<String>();
            List<LocalDateTime> preset = new ArrayList<LocalDateTime>();

            //For Priority
            if(body.getPriority().get(0).get("high")){
                priority.add("High");
            }
            else if(body.getPriority().get(1).get("medium")){
                priority.add("Medium");
            }
            else if(body.getPriority().get(2).get("low")){
                priority.add("Low");
            }
            else{
                priority.add("");
            }

            //For Status
            if(body.getStatus().get(0).get("not started")){
                status.add("Not Started");
            }
            else if(body.getStatus().get(1).get("on hold")){
                status.add("On Hold");
            }
            else if(body.getStatus().get(2).get("in progress")){
                status.add("In Progress");
            }
            else if(body.getStatus().get(3).get("completed")){
                status.add("Completed");
            }
            else if(body.getStatus().get(4).get("over due")){
                status.add("Overdue");
            }
            else{
                status.add("");
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


            System.out.println("Priority & status is "+ priority +""+status);

            LocalDateTime trail = dateAndTimeNow.plusHours(body.getPreset());

            System.out.println("plus preset is" +trail);

            System.out.println("Date time values after check"+dateAndTimeNow+"Plus Preset"+dateAndTimeNowPlusPreset);


            //criteria api
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PatientTasksDTO> query = builder.createQuery(PatientTasksDTO.class);
            Root<Patient> root = query.from(Patient.class);
            Join<Patient, Tasks> tasks = root.join("patient");

            List<Predicate> predicates = new ArrayList<>();

            if (!body.getPriority().isEmpty()) {
                predicates.add(root.get("priority").in(priority));
            }

            if (!body.getStatus().isEmpty()) {
                predicates.add(root.get("status").in(status));
            }

            if (body.getPreset() !=0) {
                predicates.add(builder.between(root.get("preset"), dateAndTimeNow, dateAndTimeNowPlusPreset));
            }

            query.where(predicates.toArray(new Predicate[0]));

            TypedQuery<PatientTasksDTO> typedQuery = entityManager.createQuery(query);
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
