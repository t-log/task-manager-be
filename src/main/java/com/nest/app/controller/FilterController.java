package com.nest.app.controller;

import com.nest.app.dto.PatientTasksDTO;
import com.nest.app.dto.RequestBodyDTO;
import com.nest.app.services.FilterServiceImpl;
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
            if(body.getPriority().get(1).get("medium")){
                priority.add("Medium");
            }
            if(body.getPriority().get(2).get("low")){
                priority.add("Low");
            }

            //For Status
            if(body.getStatus().get(0).get("not started")){
                status.add("Not Started");
            }
            if(body.getStatus().get(1).get("on hold")){
                status.add("On Hold");
            }
            if(body.getStatus().get(2).get("in progress")){
                status.add("In Progress");
            }
            if(body.getStatus().get(3).get("completed")){
                status.add("Completed");
            }
            if(body.getStatus().get(4).get("over due")){
                status.add("Overdue");
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

            //The preset Logic if Preset is not selected in fe
            if(body.getPreset() == 0){
                System.out.println("Preset value is "+body.getPreset());
                dateAndTimeNow = null;
                dateAndTimeNowPlusPreset = null;
            }
            if(body.getPriority().isEmpty()){
                priority = null;
            }
            if(body.getStatus().isEmpty()){
                status = null;
            }

            System.out.println("Date time values after check "+dateAndTimeNow+"Plus Preset "+dateAndTimeNowPlusPreset);


//        return body;   //made a DTO (RequestBodyDTO) and its mapping is compatible with request
            return filterService.dynamicFilter(priority,status,dateAndTimeNow,dateAndTimeNowPlusPreset);
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
