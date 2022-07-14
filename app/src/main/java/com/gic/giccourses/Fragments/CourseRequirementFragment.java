package com.gic.giccourses.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gic.giccourses.Adapters.CourseRequirementsAdapter;
import com.gic.giccourses.Models.CourseDetails;
import com.gic.giccourses.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseRequirementFragment extends Fragment {

    private CourseDetails mCourseDetails;
    private RecyclerView mCourseRequirementsRecyclerView;
    public CourseRequirementFragment(CourseDetails courseDetails) {
        // Required empty public constructor
        this.mCourseDetails = courseDetails;
    }

    private void init(View view) {
        mCourseRequirementsRecyclerView = view.findViewById(R.id.courseRequirementRecyclerView);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_course_requirement, container, false);
        init(view);
        getRequirementsData();
        return view;
    }

    private void getRequirementsData() {
        CourseRequirementsAdapter adapter = new CourseRequirementsAdapter(getActivity(), mCourseDetails.getCourseRequirements());
        mCourseRequirementsRecyclerView.setAdapter(adapter);
        mCourseRequirementsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
