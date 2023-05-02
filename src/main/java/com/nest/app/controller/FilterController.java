package com.nest.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nest.app.dto.PatientTasks;
import com.nest.app.dto.RequestBodyDTO;
import com.nest.app.entity.Tasks;
import com.nest.app.repository.FilterRepository;
import com.nest.app.services.FilterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FilterController {

    @Autowired
    FilterServiceImpl filterService;

//    @CrossOrigin(origins = "*")
//    @RequestMapping(value = "/filter", method = RequestMethod.POST)
//    public PatientTasks dynamicFilter(@RequestBody RequestBodyDTO body){
//        System.out.println(body.getPriority());
//        System.out.println(body.getStatus());
//        System.out.println(body.getPreset());
//
//        System.out.println(body.getPriority().get(0).get("high"));
//
////        return body;   //made a DTO (RequestBodyDTO) and its mapping is compatible with request
//          return filterService.dynamicFilter();
//    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public List<PatientTasks> viewDetails(){
        System.out.println("View Details as string"+filterService.viewDetails());
        return filterService.viewDetails();
    }


}
