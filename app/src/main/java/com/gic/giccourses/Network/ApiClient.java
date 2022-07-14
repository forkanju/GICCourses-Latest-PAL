package com.gic.giccourses.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Retrofit retrofit = null;

    /***
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     **/
    public Api getAPI() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(Api.class);
    }
}
