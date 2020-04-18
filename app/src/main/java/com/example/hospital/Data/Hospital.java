package com.example.hospital.Data;

import java.util.ArrayList;
import java.util.List;

public class Hospital<Department>{
    private String hosName;
    private List<Department> depts;

    public Hospital() {
    }

    public Hospital(String hosName) {
        this.hosName = hosName;
    }
    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public void addDepartment(Department newDept) {
       if (depts == null){
           depts = new ArrayList<Department>();
           depts.add(newDept);
       } else {
           depts.add(newDept);
       }
    }

    public String getHosName() {
        return hosName;
    }

    public List<Department> getDepartments() {
            return depts;
    }

    @Override
    public String toString() {
        return getHosName();
    }
}
