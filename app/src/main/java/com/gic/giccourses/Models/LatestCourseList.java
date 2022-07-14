package com.gic.giccourses.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LatestCourseList  {

    @SerializedName("latest_courses")
    @Expose
    private List<LatestCourse> latestCourses = null;

    public List<LatestCourse> getLatestCourses() {
        return latestCourses;
    }

}
