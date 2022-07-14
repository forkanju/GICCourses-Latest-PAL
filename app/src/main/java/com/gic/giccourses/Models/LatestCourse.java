package com.gic.giccourses.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestCourse {


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("is_free_course")
    @Expose
    private String isFreeCourse;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("price_with_currency")
    @Expose
    private String priceWithCurrency;
    @SerializedName("course_cover")
    @Expose
    private String courseCover;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("is_top_course")
    @Expose
    private String isTopCourse;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("course_overview_provider")
    @Expose
    private String courseOverviewProvider;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("discount_flag")
    @Expose
    private Object discountFlag;
    @SerializedName("discounted_price")
    @Expose
    private String discountedPrice;
    @SerializedName("description")
    @Expose
    private String description;

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getIsFreeCourse() {
        return isFreeCourse;
    }

    public void setIsFreeCourse(String isFreeCourse) {
        this.isFreeCourse = isFreeCourse;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceWithCurrency() {
        return priceWithCurrency;
    }

    public void setPriceWithCurrency(String priceWithCurrency) {
        this.priceWithCurrency = priceWithCurrency;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIsTopCourse() {
        return isTopCourse;
    }

    public void setIsTopCourse(String isTopCourse) {
        this.isTopCourse = isTopCourse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseOverviewProvider() {
        return courseOverviewProvider;
    }

    public void setCourseOverviewProvider(String courseOverviewProvider) {
        this.courseOverviewProvider = courseOverviewProvider;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Object getDiscountFlag() {
        return discountFlag;
    }

    public void setDiscountFlag(Object discountFlag) {
        this.discountFlag = discountFlag;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
