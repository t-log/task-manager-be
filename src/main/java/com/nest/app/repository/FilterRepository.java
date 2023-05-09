package com.nest.app.repository;

import com.nest.app.dto.PatientTasksDTO;
import com.nest.app.entity.Tasks;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;



public interface FilterRepository extends JpaRepository<Tasks,Integer> , JpaSpecificationExecutor<PatientTasksDTO> {
    @Query(value = "SELECT p.age as age, p.bed_no as bedNo, p.facility as facility, p.full_name as fullName, p.physician_name as physicianName, p.room_no as roomNo, p.unit_name as unitName,t.comment as comment, t.description as description, t.due_date as dueDate, t.priority as priority, t.task_status as taskStatus FROM patient p JOIN tasks t ON p.id = t.patient_id",nativeQuery = true)
    List<PatientTasksDTO> viewDetails();  //List<Map<String,String>>
    @Query(value = "SELECT " +
                    "p.age as age, p.bedNo as bedNo, p.facility as facility, " +
                    "p.fullName as fullName, p.physicianName as physicianName, " +
                    "p.roomNo as roomNo, p.unitName as unitName, " +
                    "t.comment as comment, t.description as description, " +
                    "t.dueDate as dueDate, t.priority as priority, " +
                    "t.taskStatus as taskStatus " +
                    "FROM Patient p JOIN Tasks t ON p.id = t.patientId " +
                    "WHERE " +
                    "(t.priority IN (:priorityList) OR :priorityList IS NULL) " +
                    "AND (t.taskStatus IN (:statusList) OR :statusList IS NULL) " +
                    "AND ((t.dueDate BETWEEN :currentDateTime AND :plusPreset) OR :currentDateTime IS NULL)")   //OR t.priority IN(:priorityList) OR t.taskStatus IN(:statusList) OR t.dueDate BETWEEN :currentDateTime AND :plusPreset
    List<PatientTasksDTO> searchPatientTasksFilter(@Param("priorityList") List<String> priority, @Param("statusList") List<String> status, @Param("currentDateTime")LocalDateTime dateTimeNow,@Param("plusPreset") LocalDateTime preset);  //findAll(Specification<PatientTasksDTO> spec)

}
