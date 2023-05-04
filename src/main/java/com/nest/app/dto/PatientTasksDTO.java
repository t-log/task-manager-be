package com.nest.app.dto;


//@NamedNativeQuery(name = "PatientTasks.viewDetails",
//        query = "SELECT p.age, p.bed_no, p.facility, p.full_name, p.physician_name, p.room_no, p.unit_name,t.comment, t.description, t.due_date, t.priority, t.task_status FROM patient p JOIN tasks t ON p.id = t.patient_id",
//        resultSetMapping = "patientTasksMapping")
//@SqlResultSetMapping(name = "patientTasksMapping",
//        classes = @ConstructorResult(targetClass = PatientTasks.class,
//                columns = {
//                        @ColumnResult(name = "age",type = Integer.class),
//                        @ColumnResult(name = "bedNo",type = Integer.class),
//                        @ColumnResult(name = "facility",type = String.class),
//                        @ColumnResult(name = "fullName",type = String.class),
//                        @ColumnResult(name = "physicianName",type = String.class),
//                        @ColumnResult(name = "roomNo",type = Integer.class),
//                        @ColumnResult(name = "unitName",type = String.class),
//
//                        @ColumnResult(name = "comment",type = String.class),
//                        @ColumnResult(name = "description",type = String.class),
//                        @ColumnResult(name = "dueDate",type = String.class),
//                        @ColumnResult(name = "priority",type = String.class),
//                        @ColumnResult(name = "taskStatus",type = String.class),
//
//                })
//)

import java.time.LocalDateTime;

public interface PatientTasksDTO {


//    private int age;
//    private int bedNo;
//    private String facility;
//    private String fullName;
//    private String physicianName;
//    private int roomNo;
//    private String unitName;
//    private String comment;
//    private String description;
//    private String dueDate;
//    private String priority;
//    private String taskStatus;

    //Interface DTO Projection
    int getAge();
    int getBedNo();
    String getFacility();
    String getFullName();
    String getPhysicianName();
    int getRoomNo();
    String getUnitName();
    String getComment();
    String getDescription();
    LocalDateTime getDueDate();
    String getPriority();
    String getTaskStatus();





}





