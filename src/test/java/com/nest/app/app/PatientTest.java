package com.nest.app.app;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nest.app.entity.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class PatientTest {

    private Patient patientTest;

    @BeforeEach
    public void setUp(){
        patientTest = new Patient(1,"Jack Sparrow",35,100,
                "East Coast",1000,"Aslan Ali",
                "ICU",false);
    }

    @Test
    public void testPatientEntity(){
        Assertions.assertEquals(1,patientTest.getId());
        Assertions.assertEquals("Jack Sparrow",patientTest.getFullName());
        Assertions.assertEquals(35,patientTest.getAge());
        Assertions.assertEquals(100,patientTest.getBedNo());
        Assertions.assertEquals("East Coast",patientTest.getFacility());
        Assertions.assertEquals(1000,patientTest.getRoomNo());
        Assertions.assertEquals("Aslan Ali",patientTest.getPhysicianName());
        Assertions.assertEquals("ICU",patientTest.getUnitName());
        Assertions.assertEquals(false,patientTest.getIdDeleted());
    }
}
