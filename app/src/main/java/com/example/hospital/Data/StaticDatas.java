package com.example.hospital.Data;

import java.util.ArrayList;
import java.util.Arrays;

public class StaticDatas {
    public void loadData(){

        final ArrayList<String> timetable1 = new ArrayList<>(Arrays.asList(Constants.FRIDAY, Constants.TUESDAY, Constants.SUNDAY));
        final ArrayList<String> timetable2 = new ArrayList<>(Arrays.asList(Constants.WEDNESDAY, Constants.TUESDAY, Constants.SUNDAY));

        final Hospital hospital1 = new Hospital("Delhi Hospital");
        final Hospital hospital2 = new Hospital("Jalandhar Hospital");
        final Hospital hospital3 = new Hospital("Gourgoun Hospital");
        final Hospital hospital4 = new Hospital("Chandigrah Hospital");

        final Department<Doctor> department1 = new Department<>(Constants.Cardiology);
        final Department<Doctor> department2 = new Department<>(Constants.Neurology);
        final Department<Doctor> department3 = new Department<>(Constants.Oncology);
        final Department<Doctor> department4 = new Department<>(Constants.SURGURY);
        final Department<Doctor> department5 = new Department<>(Constants.TNT);

        final Doctor doc1 = new Doctor(Constants.TNT, "daryabi", "talib", "200", timetable1);
        final Doctor doc5 = new Doctor(Constants.TNT, "shahab", "sadeqi", "300", timetable1);

        final Doctor doc2 = new Doctor(Constants.SURGURY, "fatima", "amiri", "100", timetable1);
        final Doctor doc13 = new Doctor(Constants.SURGURY, "farzana", "amiri", "100", timetable2);
        final Doctor doc14 = new Doctor(Constants.SURGURY, "farida", "amiri", "100", timetable1);
        final Doctor doc15 = new Doctor(Constants.SURGURY, "fayeq", "amiri", "100", timetable2);

        final Doctor doc3 = new Doctor(Constants.Oncology, "maisam", "rezaie", "500", timetable2);
        final Doctor doc12 = new Doctor(Constants.Oncology, "mahi", "rezaie", "500", timetable1);

        final Doctor doc6 = new Doctor(Constants.Cardiology, "rahim", "kamali", "200", timetable2);
        final Doctor doc8 = new Doctor(Constants.Cardiology, "reza", "jurabi", "200", timetable1);
        final Doctor doc4 = new Doctor(Constants.Cardiology, "rarhad", "gharib", "400", timetable2);

        final Doctor doc7 = new Doctor(Constants.Neurology, "abbas", "div", "100", timetable2);
        final Doctor doc9 = new Doctor(Constants.Neurology, "ahamad", "div", "100", timetable1);
        final Doctor doc10 = new Doctor(Constants.Neurology, "ali", "div", "100", timetable2);
        final Doctor doc11 = new Doctor(Constants.Neurology, "asghar", "div", "100", timetable1);

        department1.addDoctor(doc4);
        department1.addDoctor(doc6);
        department1.addDoctor(doc8);

        department2.addDoctor(doc7);
        department2.addDoctor(doc9);
        department2.addDoctor(doc10);
        department2.addDoctor(doc11);


        department3.addDoctor(doc3);
        department3.addDoctor(doc12);

        department4.addDoctor(doc2);
        department4.addDoctor(doc13);
        department4.addDoctor(doc14);
        department4.addDoctor(doc15);


        department5.addDoctor(doc1);
        department5.addDoctor(doc5);


        hospital1.addDepartment(department1);
        hospital1.addDepartment(department2);
        hospital1.addDepartment(department3);

        hospital2.addDepartment(department1);
        hospital2.addDepartment(department2);
        hospital2.addDepartment(department5);


        hospital3.addDepartment(department3);
        hospital3.addDepartment(department4);
        hospital3.addDepartment(department5);


        hospital4.addDepartment(department1);
        hospital4.addDepartment(department2);
        hospital4.addDepartment(department3);
        hospital4.addDepartment(department4);
        hospital4.addDepartment(department5);

        final City<Hospital> city = City.getInstance();

        city.addHospital(hospital1);
        city.addHospital(hospital2);
        city.addHospital(hospital3);
        city.addHospital(hospital4);


    }
}
