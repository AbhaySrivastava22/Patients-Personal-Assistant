package com.example.mytherapy;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class nearbydc extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearbydc);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        // Add a marker in Sydney and move the camera
        LatLng rama_shiv_chemist = new LatLng(26.432565,80.312318);
        /*LatLng apoorva_chemist=new LatLng(26.445670,80.308540);
        LatLng katiyar_chemist=new LatLng(26.4338, 80.3003);
        LatLng anand_store=new LatLng(26.4356, 80.3041);
        LatLng maa_sharda=new LatLng(26.4348, 80.3017);
        LatLng shukla_store=new LatLng(26.4341, 80.3019);
        LatLng purnima=new LatLng(26.4348, 80.3017);
        LatLng sanjana=new LatLng(26.4343, 80.3051);
        LatLng vinayak=new LatLng(26.4312, 80.3040);
        LatLng medical=new LatLng(26.4316, 80.2984);
        LatLng manvi=new LatLng(26.4309, 80.3078);
        mMap.addMarker(new MarkerOptions().position(rama_shiv_chemist).title("Rama Shiv Chemist"));
        mMap.addMarker(new MarkerOptions().position(apoorva_chemist).title("Apoorva Chemist"));
        mMap.addMarker(new MarkerOptions().position(katiyar_chemist).title("Katiyar Medical Store"));
        mMap.addMarker(new MarkerOptions().position(anand_store).title("Anand Medical Store"));
        mMap.addMarker(new MarkerOptions().position(maa_sharda).title("Maa Sharda Medical Store"));
        mMap.addMarker(new MarkerOptions().position(shukla_store).title("Shukla Medical Store"));
        mMap.addMarker(new MarkerOptions().position(purnima).title("Purnima Medical Store"));
        mMap.addMarker(new MarkerOptions().position(sanjana).title("Sanjana Medical Store"));
        mMap.addMarker(new MarkerOptions().position(vinayak).title("Vinayak Medical Store"));
        mMap.addMarker(new MarkerOptions().position(medical).title("Medical Point"));
        mMap.addMarker(new MarkerOptions().position(vinayak).title("Manvi Chemist"));*/
        mMap.addMarker(new MarkerOptions().position(rama_shiv_chemist).title("Marker in Sydney"));
    }
}
