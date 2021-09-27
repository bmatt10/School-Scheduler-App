package com.example.part1.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.part1.Entity.AssessmentEntity;
import com.example.part1.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder>{
    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;

        private final TextView assessmentItemView2;


        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.assessmentTextView3);
            assessmentItemView2 = itemView.findViewById(R.id.assessmentTextView4);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final AssessmentEntity current = mAssessments.get(position);
                Intent intent = new Intent(context, AssessmentActivity.class);
                intent.putExtra("assessmentName", current.getAssessmentName());
                intent.putExtra("assessmentNotes", current.getAssessmentNotes());
                intent.putExtra("position",position);
                intent.putExtra("assessmentID",current.getAssessmentID());
                intent.putExtra("partID", current.getCourseID());
                intent.putExtra("productID", current.getTermID());
                intent.putExtra("assessmentDate", current.getAssessmentDate());
                intent.putExtra("assessmentType", current.getAssessmentType());
                context.startActivity(intent);
            });
        }

    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<AssessmentEntity> mAssessments;

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);

        return new AssessmentAdapter.AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            AssessmentEntity current = mAssessments.get(position);
            holder.assessmentItemView.setText(current.getAssessmentName());
            holder.assessmentItemView2.setText(current.getAssessmentDate());
        } else {

            holder.assessmentItemView.setText("No Word");
            holder.assessmentItemView2.setText("No Word");
        }

    }

    public void setWords(List<AssessmentEntity> words) {
        mAssessments = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else return 0;
    }
}
