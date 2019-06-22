package org.traccar.client;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;




import java.io.IOException;

import java.util.List;
import java.util.Locale;



public class Attendance_Location implements LocationListener {
   // private static final String APP_SHARED_PREFERENCE_NAME = "Loc_Pref";
   // private final static String Save_Loc = "Save_Loc";
    Context context;
    String myAdd;
    static boolean grant;

    public Attendance_Location(Context c) {
        context = c;
    }

    public Location getCordinate() {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Location permission not granted" + "\n" + "Please grant the permission first", Toast.LENGTH_LONG).show();
            grant=false;
            return null;
        }
        else if(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            grant=true;
        }
        //
        if (!isGPSEnabled && !isNetworkEnabled) {

            Toast.makeText(context, "No Services Enabled", Toast.LENGTH_LONG).show();
        } else if (isNetworkEnabled) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 6000, 30, this);
            Location l = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            return l;
        } else if (isGPSEnabled) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 30, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            return l;
        }

      /*  else {
           Toast.makeText(context,"Please Enable The Location First",Toast.LENGTH_LONG).show();

        }*/
        return null;

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());

        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    public String Street_Add() {

        Location l = getCordinate();
        if (l != null) {

            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(l.getLatitude(), l.getLongitude(), 5);
                StringBuilder sb = new StringBuilder();
                if (addresses.size() > 0) {
                    Address address = addresses.get(2);
                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++)



                        sb.append(address.getAddressLine(i)).append("\n");
                    // if(!address.getThoroughfare().equals("Unnamed Road")){
                    if(address.getThoroughfare()!=null && address.getThoroughfare().length()>0 && address.getSubLocality()!=null && address.getSubLocality().length()>0 &&address.getLocality()!=null && address.getLocality().length()>0 &&address.getPostalCode()!=null && address.getPostalCode().length()>0 && address.getAdminArea()!=null && address.getAdminArea().length()>0){
                        sb.append("Near:-"+"\n"+address.getThoroughfare()).append("\n");
                        sb.append(address.getSubLocality()).append("\n");
                        sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getAdminArea());
                        String s= sb.toString();
                        return s;
                    }
                    else if(address.getThoroughfare()!=null && address.getThoroughfare().length()>0 && address.getSubLocality()!=null && address.getSubLocality().length()>0 &&address.getLocality()!=null && address.getLocality().length()>0 ){
                        sb.append("Near:-"+"\n"+address.getThoroughfare()).append("\n");
                        sb.append(address.getSubLocality()).append("\n");
                        sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getAdminArea());
                        String s= sb.toString();
                        return s;
                    }
                    else if(address.getSubLocality()!=null && address.getSubLocality().length()>0 &&address.getLocality()!=null && address.getLocality().length()>0 &&address.getPostalCode()!=null && address.getPostalCode().length()>0 && address.getAdminArea()!=null && address.getAdminArea().length()>0){
                        sb.append("Near:-"+"\n"+address.getThoroughfare()).append("\n");
                        sb.append(address.getSubLocality()).append("\n");
                        sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getAdminArea());
                        String s= sb.toString();
                        return s;
                    }
                    else if(address.getLocality()!=null && address.getLocality().length()>0 &&address.getPostalCode()!=null && address.getPostalCode().length()>0 && address.getAdminArea()!=null && address.getAdminArea().length()>0){
                        sb.append("Near:-"+"\n"+address.getThoroughfare()).append("\n");
                        sb.append(address.getSubLocality()).append("\n");
                        sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getAdminArea());
                        String s= sb.toString();
                        return s;
                    }
                    else{
                        sb.append("Near:-"+"\n"+address.getThoroughfare()).append("\n");
                        sb.append(address.getSubLocality()).append("\n");
                        sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getAdminArea());
                        String s= sb.toString();
                        return s;
                    }





                 /*  else if(address.getSubLocality()!=null && address.getSubLocality().length()>0){
                        return address.getSubLocality();
                    }
                    else if(address.getLocality()!=null && address.getLocality().length()>0){
                        return address.getLocality();
                    }
                   else if(address.getPostalCode()!=null && address.getPostalCode().length()>0){
                        return address.getPostalCode();
                    }
                   else if(address.getAdminArea()!=null && address.getAdminArea().length()>0){
                        return address.getAdminArea();
                    } */

                   // sb.append("Near:-"+"\n"+address.getThoroughfare()).append("\n");
                  //  sb.append(address.getSubLocality()).append("\n");


                   /* sb.append(address.getLocality()).append("\n");
                    sb.append(address.getPostalCode()).append("\n");
                    sb.append(address.getAdminArea()); */
                }

               // myAdd = sb.toString();
               // myAdd=s;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myAdd;
    }
}


