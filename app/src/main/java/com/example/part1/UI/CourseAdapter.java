package com.example.part1.UI;

import android.content.Context;
import android.content.Intent;

import com.example.part1.Entity.CourseEntity;
import com.example.part1.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.PartViewHolder> {

    class PartViewHolder extends RecyclerView.ViewHolder {
        private final TextView partItemView;

        private final TextView partItemView2;


        private PartViewHolder(View itemView) {
            super(itemView);
            partItemView = itemView.findViewById(R.id.assessmentTextView);
            partItemView2 = itemView.findViewById(R.id.assessmentTextView2);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final CourseEntity current = mParts.get(position);
                Intent intent = new Intent(context, CourseDetails.class);
                intent.putExtra("partName", current.getCourseName());
                intent.putExtra("partPrice", current.getCourseNotes());
                intent.putExtra("position",position);
                intent.putExtra("partID",current.getCourseID());
                intent.putExtra("productID", current.getTermID());
                intent.putExtra("startDate", current.getStartDate());
                intent.putExtra("endDate", current.getEndDate());
                intent.putExtra("mentor", current.getMentor());
                intent.putExtra("phone", current.getPhone());
                intent.putExtra("status", current.getStatus());
                intent.putExtra("email", current.getEmail());

                context.startActivity(intent);

            });
        }

    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<CourseEntity> mParts; // Cached copy of words

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public PartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);

        return new PartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PartViewHolder holder, int position) {
        if (mParts != null) {
            CourseEntity current = mParts.get(position);
            holder.partItemView.setText(current.getCourseName());
            holder.partItemView2.setText(current.getStartDate());
        } else {

            holder.partItemView.setText("No Word");
            holder.partItemView2.setText("No Word");
        }

    }

    public void setWords(List<CourseEntity> words) {
        mParts = words;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mParts != null)
            return mParts.size();
        else return 0;
    }
}