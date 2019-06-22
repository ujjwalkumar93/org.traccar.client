package org.traccar.client;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.TwoStatePreference;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import java.util.HashSet;
import java.util.Set;

public class LocationSwitch extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private static final String TAG = MainFragment.class.getSimpleName();

    private static final int ALARM_MANAGER_INTERVAL = 15000;

    public static final String KEY_STATUS = "switch";
    private static final int PERMISSIONS_REQUEST_LOCATION = 2;

    private SharedPreferences sharedPreferences;

    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        addPreferencesFromResource(R.xml.locationswitch);
        //initPreferences();
        if (sharedPreferences.getBoolean(KEY_STATUS, false)) {
            startTrackingService(true, false);
        }

        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmIntent = PendingIntent.getBroadcast(getActivity(), 0, new Intent(getActivity(), AutostartReceiver.class), 0);

      /*  if (sharedPreferences.getBoolean(KEY_STATUS, false)) {
            startTrackingService(true, false);
        } */


    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }




    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(KEY_STATUS)) {
           try {
               if (sharedPreferences.getBoolean(KEY_STATUS, false)) {
                   startTrackingService(true, false);
               } else {
                   stopTrackingService();
               }
           }catch (NullPointerException e){e.printStackTrace();}
        }
    }

    private void startTrackingService(boolean checkPermission, boolean permission) {
        if (checkPermission) {
            Set<String> missingPermissions = new HashSet<>();
          try {
              if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                  missingPermissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
              }
          }catch (NullPointerException e){e.printStackTrace();}
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (missingPermissions.isEmpty()) {
                permission = true;
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(missingPermissions.toArray(new String[missingPermissions.size()]),
                            PERMISSIONS_REQUEST_LOCATION);
                }
                return;
            }
        }
        //to handle nullPointerException
        try {
        if (permission) {
             // setPreferencesEnabled(false);
                ContextCompat.startForegroundService(getActivity(), new Intent(getActivity(), TrackingService.class));
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        ALARM_MANAGER_INTERVAL, ALARM_MANAGER_INTERVAL, alarmIntent);
            } else{
                sharedPreferences.edit().putBoolean(KEY_STATUS, false).apply();
                TwoStatePreference preference = (TwoStatePreference) findPreference(KEY_STATUS);
                preference.setChecked(false);
            }
        }catch(NullPointerException e){System.out.print(e);}
    }



    private void stopTrackingService() {
        alarmManager.cancel(alarmIntent);
        getActivity().stopService(new Intent(getActivity(), TrackingService.class));
        //setPreferencesEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            boolean granted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    granted = false;
                    break;
                }
            }
            startTrackingService(false, granted);


        }
    }

    //new code

    //end code

}
