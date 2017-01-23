package org.jrvivanco.mascotita.restAPI.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.jrvivanco.mascotita.pojo.UsuarioInst;
import org.jrvivanco.mascotita.restAPI.JSONKeys;
import org.jrvivanco.mascotita.restAPI.modelo.UsuarioResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by jrvivanco on 28/12/2016.
 */
public class UsuarioInstDeserializador implements JsonDeserializer<UsuarioResponse>{


    @Override
    public UsuarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson=new Gson();
        UsuarioResponse usuarioResponse= gson.fromJson(json, UsuarioResponse.class);
        JsonArray usuarioResponseData = json.getAsJsonObject().getAsJsonArray(JSONKeys.RESPONSE_ARRAY);

        usuarioResponse.setUsuariosInst(deserializarUsuarioDeJSON(usuarioResponseData));
        return usuarioResponse;
    }

    private UsuarioInst deserializarUsuarioDeJSON(JsonArray usuarioResponseData){
        //ArrayList<UsuarioInst> usuarios = new ArrayList<>();
        UsuarioInst usuario = new UsuarioInst();
        //sólo debe devolver 1 usuario
        //for (int i = 0; i < usuarioResponseData.size(); i++) {
            JsonObject usuarioResponseDataObject = usuarioResponseData.get(0).getAsJsonObject();
            String idUsr                = usuarioResponseDataObject.get(JSONKeys.USER_ID).getAsString();
            String fullNameUsr          = usuarioResponseDataObject.get(JSONKeys.USER_FULLNAME).getAsString();
            String urlUsr               = usuarioResponseDataObject.get(JSONKeys.USER_URL_PROFILE_PICTURE).getAsString();

            /*UsuarioInst usuarioActual = new UsuarioInst();
            usuarioActual.setId(idUsr);
            usuarioActual.setFullName(fullNameUsr);
            usuarioActual.setUrlFotoPerfil(urlUsr);

            usuarios.add(usuarioActual);
            */
        //}

        usuario.setId(idUsr);
        usuario.setFullName(fullNameUsr);
        usuario.setUrlFotoPerfil(urlUsr);

        return usuario;
        //aunque sólo debe devolver 1 usuario
    }
}
