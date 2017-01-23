package org.jrvivanco.mascotita.restAPI;

/**
 * Created by jrvivanco on 21/01/2017.
 */
public class ConstantesRestAPI {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "4393478762.0e27517.16ce49021ccd486eb4f0c37e8cc142c7";
    public static final String KEY_ACCESS_TOKEN = "access_token";

    //EndPoints
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/{userid}/media/recent/";
    public static final String KEY_GET_INFO_USER = "users/search?q=usuario";

    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + "?" + KEY_ACCESS_TOKEN +"="+ ACCESS_TOKEN;
    public static final String URL_GET_INFO_USER = KEY_GET_INFO_USER + "&" + KEY_ACCESS_TOKEN +"=" + ACCESS_TOKEN;
    //EndPoint setLike en Instagram
    public static final String URL_POST_SET_LIKE = "media/{media-id}/likes/";
    //en el POST del like el token va como parámetro ACCESS_TOKEN sobra:+ "&" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    //EndPoint para averiguar quién es el dueño de una foto
    public static final String URL_GET_MEDIA_USER = "media/{media-id}?"+KEY_ACCESS_TOKEN+"="+ACCESS_TOKEN;



    //para que muestre algo inicialmente
    public static final String SELF_USER = "4393478762";//"juravica2016";
    public static final String DEFAULT_USERNAME = "juravica2016";
    public static final String DEFAULT_URL_PROFILE_IMG = "https://scontent.cdninstagram.com/t51.2885-19/s150x150/15876710_1438907612842774_4761186653110796288_n.jpg";

}
