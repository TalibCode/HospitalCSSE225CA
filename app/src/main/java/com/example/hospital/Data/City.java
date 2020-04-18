package com.example.hospital.Data ;

import java.util.ArrayList;
import java.util.List;

public class City<Hospital> {
    private static City single_instance = null;
    private List<Hospital> hospitalList;

    private City(){
    }
    public void addHospital (Hospital newHospital){
        if (hospitalList == null){
            hospitalList = new ArrayList<>();
            hospitalList.add(newHospital);
        } else {
            hospitalList.add(newHospital);
        }
    }
    public List<Hospital> getHospitalList() {
        return hospitalList;
    }
    public static City getInstance()
    {
        if (single_instance == null)
            single_instance = new City();
        return single_instance;
    }

}
