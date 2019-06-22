package org.traccar.client;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.codepath.oauth.OAuthLoginActivity;

public class LoginActivity extends OAuthLoginActivity<FrappeClient> {

    public static String msg1="MY_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.button);
        Button logout = findViewById(R.id.button3);
        ImageView iv=findViewById(R.id.imageView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getClient().checkAccessToken()==null){
                    getClient().connect();
                    Log.d(msg1, "Login is in progress......!!!");

                    if(getClient().checkAccessToken()!=null){Log.d(msg1, "Logged in ");}
                }
                else {
                    Log.d(msg1, "There is already Login");
                    Toast.makeText(getBaseContext(),"Please Log Out First!",Toast.LENGTH_LONG).show();
                }

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getClient().checkAccessToken()==null)
                {
                    Log.d(msg1, "There is no login");
                    Toast.makeText(getBaseContext(),"Please LogIn First!",Toast.LENGTH_LONG).show();
                }
                else {
                    getClient().clearAccessToken();
                    Toast.makeText(getBaseContext(), "Logged Out!", Toast.LENGTH_LONG).show();
                    Log.d(msg1, "Log Out");
                }
            }
        });

    }
    @Override
    public void onLoginSuccess() {

        Log.d(msg1, String.valueOf(getClient().isAuthenticated()));
        Log.d(msg1,getClient().checkAccessToken().toString());
        Log.d(msg1,"Login success");

       Toast.makeText(getBaseContext(), "Logged In!", Toast.LENGTH_LONG).show();


    }

    // Fires if the authentication process fails for any reason.
    @Override
    public void onLoginFailure(Exception e) {


        Log.d(msg1,"Login failled");
        Toast.makeText(getBaseContext(), "ERROR", Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }



}