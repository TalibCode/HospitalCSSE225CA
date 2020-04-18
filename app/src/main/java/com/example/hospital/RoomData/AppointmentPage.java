package com.example.hospital.RoomData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hospital.Data.Constants;
import com.example.hospital.Data.DataManupultaions;
import com.example.hospital.Data.Department;
import com.example.hospital.Data.Doctor;
import com.example.hospital.Data.Hospital;
import com.example.hospital.R;
import com.example.hospital.Utils.User;

import java.util.List;

public class AppointmentPage extends AppCompatActivity implements Constants{
    private static final String TAG = "TALIBMYAddEditNoteActivity";
    private NumberPicker numberPickerPriority;
    Spinner spinner, docterSpinner, departmentSpinner;
    static DataManupultaions dataManupultaions;
    User user;
    TextView t2,t4,t6,summaryTextView;
    String selectedHospital, selectedDepartment, selectedDoctor, summary;
    int selectedHospitalIndex, selectedDepartmentIndex , selectedDoctorIndex;
    static {
        dataManupultaions = new DataManupultaions();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        spinner = findViewById(R.id.spinner);

        numberPickerPriority = findViewById(R.id.number_picker_priority);

        docterSpinner = findViewById(R.id.doctorSpinner);
        departmentSpinner = findViewById(R.id.departmentSpinner);

        docterSpinner.setVisibility(View.INVISIBLE);
        departmentSpinner.setVisibility(View.INVISIBLE);

        t2 = findViewById(R.id.textView2);
        t4 = findViewById(R.id.textView4);
        t6 = findViewById(R.id.textView6);
        summaryTextView = findViewById(R.id.some_id);


        user = User.getInstance();

        t2.setText(user.getUserName());
        t4.setText(user.getPhoneNumer());
        t6.setText(user.getEmail());

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        setCitySpinner();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Hospital selectedItem = (Hospital) parent.getItemAtPosition(position);
                selectedHospital = selectedItem.toString();
                selectedHospitalIndex = position;
                setDepartmentSpinner();
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Book Appointment");
//            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
//            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add note");
        }

}

    private void setCitySpinner() {

        List<Hospital> allHospitals = dataManupultaions.getAllHospitals();
        ArrayAdapter<Hospital> adapter = new ArrayAdapter<Hospital>(this, android.R.layout.simple_spinner_item, allHospitals);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setDepartmentSpinner() {
        departmentSpinner.setVisibility(View.VISIBLE);
        List<Department> departments = dataManupultaions.getAllHospitals().get(selectedHospitalIndex).getDepartments();
        ArrayAdapter<Department> adapter = new ArrayAdapter<Department>(this, android.R.layout.simple_spinner_item, departments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(adapter);

        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Department selectedItem = (Department) parent.getItemAtPosition(position);
                selectedDepartment = selectedItem.toString();
                selectedDepartmentIndex = position;
                setDoctorSpinner();
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });


    }

    private void setDoctorSpinner() {
        docterSpinner.setVisibility(View.VISIBLE);
        List<Doctor> allDoctors =
                DataManupultaions.getAllDoctorsofDepartement(
                        DataManupultaions.getAllDepartmentsofHospital(
                                DataManupultaions.getHostipatAtIndex(selectedHospitalIndex))
                                .get(selectedDepartmentIndex));

        ArrayAdapter<Doctor> adapter = new ArrayAdapter<Doctor>(this, android.R.layout.simple_spinner_item, allDoctors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        docterSpinner.setAdapter(adapter);

        docterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Doctor selectedItem = (Doctor) parent.getItemAtPosition(position);
                selectedDoctor = selectedItem.toString();
                selectedDoctorIndex = position;
                setsummyText();
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
    }

    private void setsummyText() {
        summary = "Hospital : " + "\t\t" + selectedHospital +"\n" + "Department : " + "\t\t" + selectedDepartment + "\n" + "Doctor : " + "\t\t\t\t" + selectedDoctor;
        summaryTextView.setText(summary.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void saveNote() {
        int priority = numberPickerPriority.getValue();
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, selectedHospital);
        data.putExtra(EXTRA_DESCRIPTION, selectedDepartment + " \\ " + selectedDoctor );
        data.putExtra(EXTRA_PRIORITY, priority);
        data.putExtra(SUMMARY,summary);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        Log.d(TAG,"in sove note");
        finish();
    }
}
