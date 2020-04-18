package com.example.hospital.Data;

import java.util.ArrayList;
import java.util.List;
public class Department<Doctor> extends Hospital implements Constants {

    private String deptName;
    private List<Doctor> doctors;

    public Department(){

    }

    public Department(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void addDoctor(Doctor newDoc) {
            if (doctors == null ){
                    doctors = new ArrayList<Doctor>();
                    doctors.add(newDoc);
            } else {
                doctors.add(newDoc);
            }
    }

    @Override
    public String toString() {
        return getDeptName();
    }
}
