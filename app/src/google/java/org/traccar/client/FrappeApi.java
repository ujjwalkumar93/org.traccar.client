package org.traccar.client;
import android.util.Log;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
public class FrappeApi extends DefaultApi20 {
String msg="Token";
    protected FrappeApi() {
    }
    private static class InstanceHolder {
        private static final FrappeApi INSTANCE = new FrappeApi();
    }
    public static FrappeApi instance() {
        return InstanceHolder.INSTANCE;
    }
    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }
    @Override
    public String getAccessTokenEndpoint() {
        return "http://dev.staging.vedarths.com/api/method/frappe.integrations.oauth2.get_token";
    }
    @Override
    protected String getAuthorizationBaseUrl() {
        return "http://dev.staging.vedarths.com/api/method/frappe.integrations.oauth2.authorize";
    }
   /* @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        //return OAuth2AccessTokenExtractor.instance();

        return new TokenExtractor<OAuth2AccessToken>() {
            @Override
            public OAuth2AccessToken extract(Response response) throws IOException, OAuthException {
             String token=null;

                try {
                    JSONObject bearer_Token=new JSONObject(response.getBody());
                    token= bearer_Token.toString();

                    Log.d(msg,bearer_Token.toString(1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new OAuth2AccessToken(token);
            }
        };

    }
 */

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        //return OAuth2AccessTokenExtractor.instance();

        return new TokenExtractor<OAuth2AccessToken>() {
            @Override
            public OAuth2AccessToken extract(Response response) throws IOException, OAuthException {
                String token=null;

                try {
                    JSONObject bearer_Token=new JSONObject(response.getBody());
                    token= String.valueOf(bearer_Token);

                    Log.d(msg,bearer_Token.toString(4));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new OAuth2AccessToken(token);
            }
        };

    }

}