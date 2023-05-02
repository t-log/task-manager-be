package com.nest.app.services;

import com.nest.app.dto.PatientTasks;
import com.nest.app.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    FilterRepository filterRepository;


    public List<Map<String,String>> viewDetails(){
        return filterRepository.viewDetails();
    }

    public PatientTasks dynamicFilter(){
          return filterRepository.searchTasksFilter();

    }



}
