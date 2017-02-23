package uniandes.guiamoviles.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 19/02/2017.
 */

public class RestClient {

    private static final String BASE_URL="http://demo7116198.mockable.io";
    private ApiService apiService;
    private static RestClient instance;

    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    public ApiService getApiService(){
        return apiService;
    }

    public static RestClient getInstance(){
        if (instance ==null){
            instance=new RestClient();
        }
        return instance;
    }
}
