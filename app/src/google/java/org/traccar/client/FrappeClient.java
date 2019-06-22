package org.traccar.client;
import android.content.Context;
import android.util.Log;
import com.codepath.oauth.OAuthBaseClient;
import com.github.scribejava.core.builder.api.BaseApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FrappeClient extends OAuthBaseClient {
    public static final BaseApi REST_API_INSTANCE = FrappeApi.instance();
    public static final String REST_URL = "http://dev.staging.vedarths.com/api";
    public static final String REST_CONSUMER_KEY = "1a9a9f1545";
    public static final String REST_CONSUMER_SECRET = "62ceb0bc43";
    public static final String REST_CALLBACK_URL = "oauth2://frappeclient";
    public static String msg1="MY_TAG_";

    public FrappeClient(Context context) {
        super(context, REST_API_INSTANCE, REST_URL,
                REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    // ENDPOINTS BELOW
   public void getHomeTimeline(int page, AsyncHttpResponseHandler handler){
        String apiUrl=getApiUrl("method/frappe.integrations.oauth2.openid_profile");
       RequestParams params=new RequestParams();
       params.put("page",String.valueOf(page));
       client.get(apiUrl,params,handler);
      // Log.d(msg1, String.valueOf(client.get(apiUrl,params,handler)));




   }

}