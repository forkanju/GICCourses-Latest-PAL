package com.gic.giccourses.presenters;

import android.util.Log;

import androidx.annotation.NonNull;

import com.gic.giccourses.Models.LatestCourseList;
import com.gic.giccourses.Network.ApiClient;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class LatestCoursePresenter  {

    private final CourseView mCourseView;
    private ApiClient mApiClient;

    public LatestCoursePresenter(CourseView view) {
        this.mCourseView = (CourseView) view;

        if (this.mApiClient == null) {
            this.mApiClient = new ApiClient();
        }
    }

    public void getLatestCourses() {



        mApiClient.getAPI()
                .getLatestCourse()
                .enqueue(new Callback<LatestCourseList>() {
                    @Override
                    public void onResponse(@NonNull Call<LatestCourseList> call, @NonNull Response<LatestCourseList> response) {

                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            mCourseView.onSuccess(response.body().getLatestCourses());
                        } else {
                            mCourseView.onError("No Data Found!");
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<LatestCourseList> call, @NonNull Throwable e) {


                        if (e instanceof HttpException) {
                            int code = Objects.requireNonNull(((HttpException) e).response()).code();
                            Log.d("UNKNOWN_CODE: ", code+"");
                            ResponseBody responseBody = Objects.requireNonNull(((HttpException) e).response()).errorBody();
                            mCourseView.onError(getErrorMessage(responseBody));

                        } else if (e instanceof SocketTimeoutException) {

                            mCourseView.onError("Server connection error");

                        } else if (e instanceof IOException) {

                            mCourseView.onError("IOException");

                        } else {
                            mCourseView.onError("Unknown  error");
                        }
                    }
                });
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

