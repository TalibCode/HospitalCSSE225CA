package com.example.hospital.Data;

import java.util.List;

public class DataManupultaions {

    final static StaticDatas loadData;
    final static City CITY;
    final static List<Hospital> hospitalList;
    static {
        loadData = new StaticDatas();
        loadData.loadData();
        CITY = City.getInstance();
        hospitalList = CITY.getHospitalList();
    }
    public static List<Hospital> getAllHospitals(){
        return hospitalList;
    }

    public static Hospital getHostipatAtIndex(int index){
        return hospitalList.get(index);
    }
    public static List<Department> getAllDepartmentsofHospital(Hospital hospital){
        return hospital.getDepartments();
    }
    public static List<Doctor> getAllDoctorsofDepartement(Department department){
        return department.getDoctors();
    }

}
