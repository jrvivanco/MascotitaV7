package org.jrvivanco.mascotita.restAPI.deserializador;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.jrvivanco.mascotita.pojo.LikeInst;
import org.jrvivanco.mascotita.restAPI.JSONKeys;
import org.jrvivanco.mascotita.restAPI.modelo.LikeResponse;

import java.lang.reflect.Type;

/**
 * Created by jrvivanco on 12/01/2017.
 * //para deserializar lo que devuelve el endPoint del like
 */
public class LikeDeserializador implements JsonDeserializer<LikeResponse> {

    @Override
    public LikeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        LikeResponse likeResponse =  gson.fromJson(json, LikeResponse.class);
        //JsonArray likeResponseData = json.getAsJsonObject().getAsJsonArray(JSONKeys.LIKE_META);
        //likeResponse.setLikeInst(deserializaLikeDeJSON(likeResponseData));

        // no me devuelve un array, sino un objeto,así que me quedo con el objeto y saco su código
        JsonObject likeDataObj = json.getAsJsonObject();
        JsonObject likeMetaDataObj = likeDataObj.getAsJsonObject(JSONKeys.LIKE_META);
        int code = likeMetaDataObj.get(JSONKeys.LIKE_META_CODE).getAsInt();

        LikeInst like = new LikeInst();
        like.setCode(code);

        likeResponse.setLikeInst(like);


        return likeResponse;
    }

/*
    private LikeInst deserializaLikeDeJSON(JsonArray likeResponseDatos){
        LikeInst like = new LikeInst();
        //sólo devuelve un objeto, y sólo me interesa el "code"
        JsonObject likeDataObj = likeResponseDatos.get(0).getAsJsonObject();
        JsonObject likeMetaDataObj = likeDataObj.getAsJsonObject(JSONKeys.LIKE_META);
        int code = likeMetaDataObj.get(JSONKeys.LIKE_META_CODE).getAsInt();

        like.setCode(code);

        return like;
    }
*/
}
