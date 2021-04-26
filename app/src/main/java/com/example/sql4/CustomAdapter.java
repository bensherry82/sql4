package com.example.sql4;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, course_name, student_name, grade_name;

    CustomAdapter(Activity activity, Context context, ArrayList book_id, ArrayList course_array, ArrayList student_array,
                  ArrayList grade_array){
        this.activity = activity;
        this.context = context;
        this.id = book_id;
        this.course_name = course_array;
        this.student_name = student_array;
        this.grade_name = grade_array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.course_id_txt.setText(String.valueOf(id.get(position)));
        holder.course_name_txt.setText(String.valueOf(course_name.get(position)));
        holder.student_name_txt.setText(String.valueOf(student_name.get(position)));
        holder.grade_name_txt.setText(String.valueOf(grade_name.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("course", String.valueOf(course_name.get(position)));
                intent.putExtra("student", String.valueOf(student_name.get(position)));
                intent.putExtra("grade", String.valueOf(grade_name.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView course_id_txt, course_name_txt, student_name_txt, grade_name_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            course_id_txt = itemView.findViewById(R.id.id_txt);
            course_name_txt = itemView.findViewById(R.id.course_name_txt);
            student_name_txt = itemView.findViewById(R.id.student_name_txt);
            grade_name_txt = itemView.findViewById(R.id.grade_name_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animation
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
