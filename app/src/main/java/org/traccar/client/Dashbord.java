package org.traccar.client;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Dashbord extends AppCompatActivity {

    Button erp;
    ImageButton rep,atten;
    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_dashbord);
        erp=findViewById(R.id.btn_erp);
        rep=findViewById(R.id.btnVisit);
        atten=findViewById(R.id.btn_Att);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.switchcontain, new LocationSwitch()).commit();

        }

        rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Uri uri=Uri.parse("http://om.vedarths.com/desk#Form/Maintenance%20Visit/MV00001");
                Intent i=new Intent(Dashbord.this,Web_Report.class);
                startActivity(i);
            }
        });
       erp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // getClient().connect();
                Intent i=new Intent(Dashbord.this,LoginActivity.class);
                startActivity(i);

            }
        });



        atten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Dashbord.this,Attendance.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.setting :
                Intent i=new Intent(Dashbord.this,MainActivity.class);
                startActivity(i);

        }
            return super.onOptionsItemSelected(item);
    }

}




