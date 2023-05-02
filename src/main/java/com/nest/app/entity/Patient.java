package com.nest.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private int age;
    private int bedNo;
    private String facility;
    private int roomNo;
    private String physicianName;
    private String unitName;
    private boolean isDeleted;

    public Patient() {
    }

    public Patient(int id, String fullName, int age, int bedNo, String facility, int roomNo, String physicianName, String unitName, boolean isDeleted) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.bedNo = bedNo;
        this.facility = facility;
        this.roomNo = roomNo;
        this.physicianName = physicianName;
        this.unitName = unitName;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBedNo() {
        return bedNo;
    }

    public void setBedNo(int bedNo) {
        this.bedNo = bedNo;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public boolean getIdDeleted() {
        return isDeleted;
    }

    public void setIdDeleted(boolean idDeleted) {
        this.isDeleted = idDeleted;
    }
}
