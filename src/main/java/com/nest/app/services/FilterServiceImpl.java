package com.nest.app.services;

import com.nest.app.dto.PatientTasksDTO;
import com.nest.app.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    FilterRepository filterRepository;


    public List<PatientTasksDTO>  viewDetails(){
        return filterRepository.viewDetails();
    }

    public List<PatientTasksDTO> dynamicFilter(List<String> priority, List<String> status, LocalDateTime dateTimeNow,LocalDateTime plusPreset){
          return filterRepository.searchPatientTasksFilter(priority,status,dateTimeNow,plusPreset);
    }



}
