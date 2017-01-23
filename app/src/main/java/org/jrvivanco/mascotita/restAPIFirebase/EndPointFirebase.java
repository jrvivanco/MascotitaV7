package org.jrvivanco.mascotita.restAPIFirebase;

import org.jrvivanco.mascotita.restAPIFirebase.model.UsrIDInstTokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by jrvivanco on 09/01/2017.
 */
public interface EndPointFirebase {

    @FormUrlEncoded
    @POST(ConstantesAPIFirebase.KEY_POST_ID_USR)
    Call<UsrIDInstTokenResponse> registrarTokenID(
            @Field("id_dispositivo") String id_dispositivo,
            @Field("id_usuario_instagram") String id_usuario_instagram,
            @Field("nombre_usuario_instagram") String nombre_usuario_instagram
    );

    @FormUrlEncoded
    @POST(ConstantesAPIFirebase.KEY_POST_LIKE)
    Call<UsrIDInstTokenResponse> darLike(
            @Field("id_dispositivo") String id_dispositivo,
            @Field("id_usuario_instagram") String id_usuario_instagram,
            @Field("id_foto_instagram") String nombre_usuario_instagram
    );

    @FormUrlEncoded
    @POST(ConstantesAPIFirebase.KEY_POST_NOTIFICACION)
    Call<UsrIDInstTokenResponse> notificacionUsuario(
            @Field("id_token") String token,
            @Field("id_usr_inst") String usrInstID,
            @Field("url_foto_usuario") String urlFotoUsr

    );
}
