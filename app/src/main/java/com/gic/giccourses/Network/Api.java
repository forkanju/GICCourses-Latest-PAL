package com.gic.giccourses.Network;

import android.text.Editable;

import com.gic.giccourses.JSONSchemas.CategorySchema;
import com.gic.giccourses.JSONSchemas.CertificateAddonSchema;
import com.gic.giccourses.JSONSchemas.CourseDetailsSchema;
import com.gic.giccourses.JSONSchemas.CourseSchema;
import com.gic.giccourses.JSONSchemas.LanguageSchema;
import com.gic.giccourses.JSONSchemas.LessonCompletionSchema;
import com.gic.giccourses.JSONSchemas.SectionSchema;
import com.gic.giccourses.JSONSchemas.StatusSchema;
import com.gic.giccourses.JSONSchemas.SystemSettings;
import com.gic.giccourses.JSONSchemas.UserSchema;
import com.gic.giccourses.Models.LatestCourseList;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {

    // API PREFIX FOR OFFLINE
    String BASE_URL_PREFIX = "https://courses.gicbdedu.com";

    // BASE URL
    String BASE_URL = BASE_URL_PREFIX + "/api/"; //upload_user_image

    // BASE URL FOR QUIZ
    String QUIZ_BASE_URL = BASE_URL_PREFIX + "/home/quiz_mobile_web_view/";

    // BASE URL FOR COURSE PURCHASE VIEW
    String COURSE_PURCHASE_URL = BASE_URL_PREFIX + "/home/course_purchase/";     //shopping_cart //course_purchase


    // Api call for fetching Top Courses
    @GET("top_courses")
    Call<List<CourseSchema>> getTopCourses();

    // Api call for fetching Categories
    @GET("categories")
    Call<List<CategorySchema>> getCategories();

    @GET("latest_courses")
    Call<LatestCourseList> getLatestCourse();


    @GET("filter_course")
    Call<List<CourseSchema>> getAllCourses(
            @Query("selected_category") String selectedCategory,
            @Query("selected_price") String selectedPrice);

    // Api call for fetching Category Wise Courses
    @GET("category_wise_course")
    Call<List<CourseSchema>> getCourses(@Query("category_id") int categoryId);

    // Api call for fetching Languages
    @GET("languages")
    Call<List<LanguageSchema>> getLanguages();

    // Api call for Filtering Courses
    @GET("filter_course")
    Call<List<CourseSchema>> getFilteredCourses(
            @Query("selected_category") String selectedCategory,
            @Query("selected_price") String selectedPrice,
            @Query("selected_level") String selectedLevel,
            @Query("selected_language") String selectedLanguage,
            @Query("selected_rating") String selectedRating,
            @Query("selected_search_string") String searchString);

    // Api call for fetching System Settings
    @GET("system_settings")
    Call<SystemSettings> getSystemSettings();

    // Api call for fetching Course list from Search String
    @GET("courses_by_search_string")
    Call<List<CourseSchema>> getCoursesBySearchString(@Query("search_string") Editable searchString);

    // Api call for Login
    @GET("login")
    Call<UserSchema> getUserDetails(
            @Query("email") Editable emailAddressForLogin,
            @Query("password") Editable passwordForLogin);

    // Api call for Fetching My Courses
    @GET("my_courses")
    Call<List<CourseSchema>> getMyCourses(@Query("auth_token") String authToken);

    // Api call for Fetching My Wishlist
    @GET("my_wishlist")
    Call<List<CourseSchema>> getMyWishlist(@Query("auth_token") String authToken);

    // Api call for Fetching My Wishlist
    @GET("toggle_wishlist_items")
    Call<StatusSchema> toggleWishListItems(@Query("auth_token") String authToken, @Query("course_id") int courseId);

    // Api call for Fetching My Wishlist
    @GET("sections")
    Call<List<SectionSchema>> getAllSections(@Query("auth_token") String authToken, @Query("course_id") int courseId);

    // Api call for Fetching Course Details
    @GET("course_details_by_id")
    Call<List<CourseDetailsSchema>> getCourseDetails(@Query("auth_token") String authToken, @Query("course_id") int courseId);

    // Api call for Fetching Course Details
    @GET("course_object_by_id")
    Call<CourseSchema> getCourseObject(@Query("course_id") int courseId);

    // Api call for Saving course progress with lesson completion status
    @GET("save_course_progress")
    Call<LessonCompletionSchema> saveCourseProgress(@Query("auth_token") String authToken, @Query("lesson_id") int lessonId, @Query("progress") int progress);

    @Multipart
    @POST("upload_user_image")
    Call<StatusSchema> uploadUserImage(
            @Part("auth_token") RequestBody authToken,
            @Part MultipartBody.Part userImage
    );

    // Api call for Fetching Logged In User Details
    @GET("userdata")
    Call<UserSchema> getUserProfileInfo(@Query("auth_token") String authToken);

    // Api call for Fetching Logged In User Details
    @FormUrlEncoded
    @POST("update_userdata")
    Call<UserSchema> updateUserData(
            @Field("auth_token") String auth_token,
            @Field("first_name") Editable firstName,
            @Field("last_name") Editable lastName,
            @Field("email") Editable email,
            @Field("biography") Editable biography,
            @Field("twitter_link") Editable twitterLink,
            @Field("facebook_link") Editable facebookLink,
            @Field("linkedin_link") Editable linkedInLink);

    // Api call for Fetching Logged In User Details
    @FormUrlEncoded
    @POST("update_password")
    Call<StatusSchema> updatePassword(
            @Field("auth_token") String auth_token,
            @Field("current_password") Editable currentPassword,
            @Field("new_password") Editable newPassword,
            @Field("confirm_password") Editable confirmPassword);

    // API FOR CHECKING AND GETTING CERTIFICATE
    @GET("certificate_addon")
    Call<CertificateAddonSchema> certificateAddon(@Query("auth_token") String authToken, @Query("course_id") int courseId);
}

