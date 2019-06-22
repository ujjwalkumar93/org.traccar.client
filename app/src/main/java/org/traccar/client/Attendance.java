package org.traccar.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class Attendance extends AppCompatActivity {
    Button backbtn, btn_attendance;

    TextView mark, tdate, ttime, tlocation, ddate, dtime, dlocation;
    private static final String APP_SHARED_PREFERENCE_NAME = "AppSharedPref";
    private final static String Save_Time = "Save_Time";
    private final static String Save_Date = "Save_Date";
    private final static String Save_Loc = "Save_Loc";
    Context context;
    SharedPreferences sharedPreferences;
    boolean grant;
    public static String msg1="MY_TAG";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_attendance);
        sharedPreferences = getSharedPreferences(APP_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        mark = findViewById(R.id.textView);
        tdate = findViewById(R.id.tv_date);
        ttime = findViewById(R.id.textView3);
        tlocation = findViewById(R.id.textView4);
        ddate = findViewById(R.id.textView5);
        dtime = findViewById(R.id.textView6);
        dlocation = findViewById(R.id.textView7);
        backbtn = findViewById(R.id.btn1);
        btn_attendance = findViewById(R.id.button2);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mark.setText(mydate());
        mark.setTextSize(20);

        //code for upload




        //ends here




       if (Get_Date().equals(mydate()) && !Get_Location().isEmpty()) {
            btn_attendance.setVisibility(View.GONE);
            show();
        }

        btn_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mark.setText("Marked");
                Save_Date(mydate());
                Save_Time(My_Sys_Time());
                Save_Location(Location());
                show();
                btn_attendance.setVisibility(View.GONE);
                //upload();

                }
        });
    }


    public String mydate() {
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    public String My_Sys_Time() {
        DateFormat df = new SimpleDateFormat("hh:mm aa");
        String time = df.format(Calendar.getInstance().getTime());
        return time;
    }

    public String Location() {
        Attendance_Location al = new Attendance_Location(getApplicationContext());


        return al.Street_Add();
    }


    public void Save_Date(String Apply_Date) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Save_Date, Apply_Date);
        editor.commit();
    }

    public String Get_Date() {
        return sharedPreferences.getString(Save_Date, "");
    }

    public void Save_Time(String Apply_Time) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Save_Time, Apply_Time);
        editor.commit();
    }

    public String Get_Time() {
        return sharedPreferences.getString(Save_Time, "");
    }

    public void Save_Location(String Apply_Time) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Save_Loc, Apply_Time);
        editor.commit();
    }

    public String Get_Location() {
        return sharedPreferences.getString(Save_Loc, "");
    }

    public void show() {
        ddate.setText(Get_Date());
        dtime.setText(Get_Time());
        dlocation.setText(Get_Location());
        dlocation.setTextSize(15);
        mark.setText("Marked");
        mark.setTextSize(35);

}

/* public void upload(){

    FrappeClient frappeClient= (FrappeClient) FrappeClient.getInstance(FrappeClient.class,this);
    frappeClient.addHeader("val","hello World");

    frappeClient.getHomeTimeline(1, new AsyncHttpResponseHandler() {


        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            Toast.makeText(getApplicationContext(),"data uploaded",Toast.LENGTH_LONG).show();
            Log.d(msg1,responseBody.toString());
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Toast.makeText(getApplicationContext(),"failled to upload data",Toast.LENGTH_LONG).show();
            Log.d("Fail_Msg",responseBody.toString());
        }
    });



} */
}