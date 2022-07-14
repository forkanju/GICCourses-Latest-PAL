package com.gic.giccourses.presenters;

import com.gic.giccourses.Models.LatestCourse;

import java.util.List;

public interface CourseView {

    public void onSuccess(List<LatestCourse> latestCourses);
    public void onError(String error);
}
