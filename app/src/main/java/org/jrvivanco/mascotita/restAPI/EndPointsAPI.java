package org.jrvivanco.mascotita.restAPI;

import org.jrvivanco.mascotita.restAPI.modelo.LikeResponse;
import org.jrvivanco.mascotita.restAPI.modelo.MascotaResponse;
import org.jrvivanco.mascotita.restAPI.modelo.MediaResponse;
import org.jrvivanco.mascotita.restAPI.modelo.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jrvivanco on 21/01/2017.
 */
public interface EndPointsAPI {

    @GET(ConstantesRestAPI.URL_GET_INFO_USER)
    Call<UsuarioResponse> getDatosUsuario(@Query("q") String nomUsuario);

    @GET(ConstantesRestAPI.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia(@Path("userid") String userID);

    //para averiguar el dueño de una foto y averiguar a quién enviar notificaciones
    @GET(ConstantesRestAPI.URL_GET_MEDIA_USER)
    Call<MediaResponse> getMediaUser(@Path("media-id") String mediaID);

    //Guarda los likes generados
    @FormUrlEncoded
    @POST(ConstantesRestAPI.URL_POST_SET_LIKE)
    Call<LikeResponse> setLikeMedia(@Path("media-id") String mediaID, @Field(ConstantesRestAPI.KEY_ACCESS_TOKEN) String tokenAccess);

}
