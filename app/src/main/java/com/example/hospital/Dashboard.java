package com.example.hospital;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.Adapter.SliderAdapterExample;
import com.example.hospital.RoomData.AppointmentAdapter;
import com.example.hospital.RoomData.AppointmentPage;
import com.example.hospital.RoomData.AppointmentTable;
import com.example.hospital.RoomData.AppointmentViewModel;
import com.example.hospital.internalStorgae.filedataActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    private static final String TAG = "TalibDashboard";
    private AppointmentViewModel appointmentViewModel;
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int Edit_NOTE_REQUEST = 2;
    SliderView sliderView;
    String summary;
    Button mMap, mFileOpen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mFileOpen = findViewById(R.id.recordFile);
        mFileOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, filedataActivity.class );
                intent.putExtra("summary",summary);
                startActivity(intent);
            }
        });

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, AppointmentPage.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final AppointmentAdapter adapter = new AppointmentAdapter();
        Log.d(TAG, "MYonCreate:  before adapter setting");
        recyclerView.setAdapter(adapter);

        appointmentViewModel = ViewModelProviders.of(this).get(AppointmentViewModel.class);
        appointmentViewModel.getAllNotes().observe(this, new Observer<List<AppointmentTable>>() {
            @Override
            public void onChanged(List<AppointmentTable> appointmentTables) {
                // update recycler view
                adapter.submitList(appointmentTables);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                appointmentViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(Dashboard.this, "note deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new AppointmentAdapter.onItemClickListener() {
            @Override
            public void onItemClick(AppointmentTable appointmentTable) {
                Intent intent = new Intent(Dashboard.this, AppointmentPage.class);
                intent.putExtra(AppointmentPage.EXTRA_TITLE, appointmentTable.getTitle());
                intent.putExtra(AppointmentPage.EXTRA_DESCRIPTION, appointmentTable.getDescription());
                intent.putExtra(AppointmentPage.EXTRA_PRIORITY, appointmentTable.getPriority());
                intent.putExtra(AppointmentPage.EXTRA_ID, appointmentTable.getId());
                startActivityForResult(intent,Edit_NOTE_REQUEST);

            }
        });

        ArrayList<Integer> image = new ArrayList<>();
        image.add(R.drawable.z);
        image.add(R.drawable.zzz);
        image.add(R.drawable.doctor_login);
        image.add(R.drawable.splashlogo);
        SliderView sliderView = findViewById(R.id.imageSlider);

        SliderAdapterExample sliderAdapter = new SliderAdapterExample(this);
        sliderAdapter.renewItems(image);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AppointmentPage.EXTRA_TITLE);
            String desc = data.getStringExtra(AppointmentPage.EXTRA_DESCRIPTION);
            int priprity = data.getIntExtra(AppointmentPage.EXTRA_PRIORITY, 1);
            summary = title + "\n" + desc+ "\n" + priprity + "\n";
            Log.d(TAG, "onActivityResult: " + summary);

            AppointmentTable appointmentTable = new AppointmentTable(title, desc, priprity);

            appointmentViewModel.insert(appointmentTable);

        } else if (requestCode == Edit_NOTE_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(AppointmentPage.EXTRA_ID,-1);
            if (id == -1){
                Toast.makeText(this,"note can't be updated", Toast.LENGTH_LONG).show();
                return;
            }
            String title = data.getStringExtra(AppointmentPage.EXTRA_TITLE);
            String desc = data.getStringExtra(AppointmentPage.EXTRA_DESCRIPTION);
            int priprity = data.getIntExtra(AppointmentPage.EXTRA_PRIORITY, 1);
            AppointmentTable appointmentTable = new AppointmentTable(title,desc,priprity);
            appointmentTable.setId(id);
            appointmentViewModel.update(appointmentTable);
            Toast.makeText(this,"updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                appointmentViewModel.deleteAllNotes();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
