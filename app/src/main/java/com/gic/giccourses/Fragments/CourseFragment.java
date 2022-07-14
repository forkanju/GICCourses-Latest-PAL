package com.gic.giccourses.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gic.giccourses.Activities.CourseDetailsActivity;
import com.gic.giccourses.Activities.CoursesActivity;
import com.gic.giccourses.Adapters.CategoryAdapter;
import com.gic.giccourses.Adapters.CoursesAdapter;
import com.gic.giccourses.Adapters.LatestCourseAdapter;
import com.gic.giccourses.Adapters.TopCourseAdapter;
import com.gic.giccourses.JSONSchemas.CategorySchema;
import com.gic.giccourses.JSONSchemas.CourseSchema;
import com.gic.giccourses.Models.Category;
import com.gic.giccourses.Models.Course;
import com.gic.giccourses.Models.LatestCourse;
import com.gic.giccourses.Network.Api;
import com.gic.giccourses.R;
import com.gic.giccourses.Utils.Helpers;
import com.gic.giccourses.presenters.CourseView;
import com.gic.giccourses.presenters.LatestCoursePresenter;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, CourseView, LatestCourseAdapter.ItemClickListener {

    //vars
    private static final String TAG = "Fragment";
    // CategorySchema array of objects.
    private ArrayList<Category> mCategory = new ArrayList<>();
    // CourseSchema array of objects.
    private ArrayList<Course> mtopCourse = new ArrayList<>();

    //private RecyclerView recyclerViewForTopCourses = null;
    private RecyclerView recyclerViewForCategories = null;
    private ProgressBar progressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //////////////////////Added for latest courses///////////////////////////
    private LatestCoursePresenter mPresenter;
    private LatestCourseAdapter mAdapter;
    RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        //This function is responsible for fetching the images from the web
        View view = inflater.inflate(R.layout.course_fragment, container, false);

        recyclerViewForCategories = view.findViewById(R.id.recyclerViewForCategories);

        mRecyclerView = view.findViewById(R.id.recyclerViewForLatestCourses);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPresenter = new LatestCoursePresenter(this);

        initProgressBar(view);
        initSwipeRefreshLayout(view);
        apiCalls();

        return view;
    }

    private void apiCalls() {
        getLatestCourses();
        getCategories();
    }

    private void initSwipeRefreshLayout(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.googleAccentColor1,
                R.color.googleAccentColor2,
                R.color.googleAccentColor3,
                R.color.googleAccentColor4);
    }

    // Initialize the progress bar
    private void initProgressBar(View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        Sprite circularLoading = new Circle();
        progressBar.setIndeterminateDrawable(circularLoading);
    }



    private void initCategoryListRecyclerView() {
        CategoryAdapter adapter = new CategoryAdapter(getActivity(), mCategory);
        recyclerViewForCategories.setAdapter(adapter);
        recyclerViewForCategories.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setHeight(int numberOfCategories) {

        int pixels = Helpers.convertDpToPixel((numberOfCategories * 90) + 10); // 9 is the number of categories and the 90 is each items height with the margin of top and bottom. Extra 10 dp for readability
        Log.d(TAG, "Lets change the height of recycler view");
        ViewGroup.LayoutParams params1 = recyclerViewForCategories.getLayoutParams();
        recyclerViewForCategories.setMinimumHeight(pixels);
        recyclerViewForCategories.requestLayout();
    }


    private void getCategories() {
        progressBar.setVisibility(View.VISIBLE);
        // Making empty array of category
        mCategory = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List<CategorySchema>> call = api.getCategories();
        call.enqueue(new Callback<List<CategorySchema>>() {
            @Override
            public void onResponse(Call<List<CategorySchema>> call, Response<List<CategorySchema>> response) {
                Log.d(TAG, "CategorySchema Fetched Successfully");
                List<CategorySchema> categorySchema = response.body();
                for (CategorySchema m : categorySchema) {
                    mCategory.add(new Category(m.getId(), m.getName(), m.getThumbnail(), m.getNumberOfCourses()));
                }
                initCategoryListRecyclerView();
                setHeight(mCategory.size());
                mSwipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<CategorySchema>> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onRefresh() {
        apiCalls();
    }

    //////////////Added for latest course view//////////////

    private void getLatestCourses() {

        //if (checkConnection()) {
           // mSwipeRefreshLayout.setRefreshing(true);
           mPresenter.getLatestCourses();
       // } else {
           // Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_SHORT).show();
      //  }
    }

    @Override
    public void onSuccess(List<LatestCourse> latestCourses) {

        if (latestCourses != null && latestCourses.size() > 0) {
           // hideAnimation();
            mAdapter = new LatestCourseAdapter(getActivity(), latestCourses, (LatestCourseAdapter.ItemClickListener) this);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
           // emptyAnimation();
            Toast.makeText(getActivity(), "Data not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onItemClick(int id) {
        Intent intent = new Intent(getActivity(), CourseDetailsActivity.class);
        intent.putExtra("courseId", id);
        getActivity().startActivity(intent);
    }
}
