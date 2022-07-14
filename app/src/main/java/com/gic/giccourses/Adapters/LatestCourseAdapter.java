package com.gic.giccourses.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gic.giccourses.Activities.CourseDetailsActivity;
import com.gic.giccourses.Models.Course;
import com.gic.giccourses.Models.LatestCourse;
import com.gic.giccourses.R;

import java.util.List;

public class LatestCourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<LatestCourse> latestCourses;
    private ItemClickListener mListener;


    public LatestCourseAdapter(Context mContext, List<LatestCourse> latestCourses, ItemClickListener mListener) {
        this.mContext = mContext;
        this.latestCourses = latestCourses;
        this.mListener = mListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.latest_course_cell, parent, false);

        return new ViewHolder(view);

    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final ViewHolder vh = (ViewHolder) holder;
        LatestCourse latestCourse = latestCourses.get(position);
        vh.title.setText(latestCourse.getTitle() != null ? latestCourse.getTitle() : "N/A");

        if (latestCourse.getIsFreeCourse() == null || latestCourse.getIsFreeCourse().equals("0")) {
            vh.price.setText(latestCourse.getPrice() != null ? "৳"+latestCourse.getPrice() : "N/A");
        } else {
            vh.price.setText("FREE");
        }


        Glide.with(mContext)
                .asBitmap()
                .load(latestCourse.getThumbnail())
                .into(vh.thumbnail);


    }

    @Override
    public int getItemCount() {
        return latestCourses != null ? latestCourses.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView invoices_item;
        private TextView title, price;
        private ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);

            invoices_item = view.findViewById(R.id.latest_course_card);
            title = view.findViewById(R.id.latestCourseTitle);
            price = view.findViewById(R.id.latestCoursePrice);
            thumbnail = view.findViewById(R.id.latestCourseThumbnail);


            invoices_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     if (mListener != null)
                     mListener.onItemClick(latestCourses.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface ItemClickListener {
        public void onItemClick(int id);
    }

}
