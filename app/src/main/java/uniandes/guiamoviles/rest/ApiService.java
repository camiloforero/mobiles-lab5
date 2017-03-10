package uniandes.guiamoviles.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import uniandes.guiamoviles.entities.Imagen;
import uniandes.guiamoviles.entities.Pedido;
import uniandes.guiamoviles.entities.ResponseMessage;

/**
 * Created by ASUS on 19/02/2017.
 */

public interface ApiService {

    @GET("/interests")
    Call<List<Imagen>> getIntereses();
    @GET("/paises")
    Call<List<Imagen>> getPaises();
    @POST("/interests")
    Call<ResponseMessage> saveIntereses(@Body Pedido pedido);

}
