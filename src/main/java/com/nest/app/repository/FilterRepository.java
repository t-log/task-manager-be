package com.nest.app.repository;

import com.nest.app.dto.PatientTasks;
import com.nest.app.entity.Tasks;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface FilterRepository extends JpaRepository<Tasks,Integer> {
    @Query(value = "SELECT p.age, p.bed_no, p.facility, p.full_name, p.physician_name, p.room_no, p.unit_name,t.comment, t.description, t.due_date, t.priority, t.task_status FROM patient p JOIN tasks t ON p.id = t.patient_id",nativeQuery = true)
    List<Map<String,String>> viewDetails();  //List<Map<String,String>>

    @Query(value = "SELECT p.`age`, p.`bed_no`, p.`facility`, p.`full_name`, p.`physician_name`, p.`room_no`, p.`unit_name`, t.`comment`, t.`description`,t.`due_date`, t.`priority`, t.`task_status` FROM `patient` p JOIN tasks t ON p.id = t.patient_id WHERE t.priority IN(\"\",\"\",\"\") AND t.task_status IN(\"\",\"\",\"\")",nativeQuery = true)
    PatientTasks searchTasksFilter();
}
