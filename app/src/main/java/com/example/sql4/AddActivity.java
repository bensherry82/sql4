package com.example.sql4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText course_input, student_input, grade_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        course_input = findViewById(R.id.course_input);
        student_input = findViewById(R.id.student_input);
        grade_input = findViewById(R.id.grade_input);
        add_button = findViewById(R.id.add_button);

        //WHen FAB is clicked on, open Add Student Activity
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(course_input.getText().toString().trim(),
                        student_input.getText().toString().trim(),
                        Integer.valueOf(grade_input.getText().toString().trim()));
                            }
        });

    }
}
