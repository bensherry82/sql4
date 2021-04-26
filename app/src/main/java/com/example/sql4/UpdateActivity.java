package com.example.sql4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText course_input, student_input, grade_input;
    Button update_button, delete_button;

    String id, course, student, grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        course_input = findViewById(R.id.course_input2);
        student_input = findViewById(R.id.student_input2);
        grade_input = findViewById(R.id.grade_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(course);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                course = course_input.getText().toString().trim();
                student = student_input.getText().toString().trim();
                grade = grade_input.getText().toString().trim();
                myDB.updateData(id, course, student, grade);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("course") &&
                getIntent().hasExtra("student") && getIntent().hasExtra("grade")){
            id = getIntent().getStringExtra("id");
            course = getIntent().getStringExtra("course");
            student = getIntent().getStringExtra("student");
            grade = getIntent().getStringExtra("grade");

            course_input.setText(course);
            student_input.setText(student);
            grade_input.setText(grade);
            Log.d("stev", course+" "+student+" "+grade);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + course + " ?");
        builder.setMessage("Are you sure you want to delete " + course + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
