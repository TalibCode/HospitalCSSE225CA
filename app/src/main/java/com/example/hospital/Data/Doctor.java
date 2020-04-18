package com.example.hospital.Data;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Department {
    private String docName;
    private String docLastName;
    private String docFees;

    private List<String> docWorkDays;
    private List<String> copyDocWorkDays;

    public Doctor(String deptName, String docName, String docLastName, String docFees, List<String> docWorkDays) {
        super(deptName);
        this.docName = docName;
        this.docLastName = docLastName;
        this.docFees = docFees;
        this.docWorkDays = docWorkDays;
    }
    public Doctor(String deptName) {
        super(deptName);
    }
    public void setDocName(String docName) {
        this.docName = docName;
    }
    public void setDocLastName(String docLastName) {
        this.docLastName = docLastName;
    }

    public void setDocWorkDays(List<String> docWorkDays) {
        this.docWorkDays = docWorkDays;
    }

    public String getDocName() {
        return docName;
    }
    public String getDocLastName() {
        return docLastName;
    }
    public List<String> getDocWorkDays() {
        if (copyDocWorkDays != null){
            return copyDocWorkDays;
        } else {
            copyDocWorkDays = new ArrayList<>(docWorkDays);
            return copyDocWorkDays;
        }
    }

    @Override
    public String toString() {
        return getDocName();
    }
}
