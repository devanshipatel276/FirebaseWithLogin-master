package com.example.devanshi.firebasewithlogin.fragement;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.devanshi.firebasewithlogin.R;
import com.example.devanshi.firebasewithlogin.util.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GoogleMap mMap;
    GPSTracker mloc;
    Button mBtnMyLocation;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

      mBtnMyLocation = view.findViewById(R.id.btn_mylocation);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (googleMap != null) {
            mMap = googleMap;
            LatLng doctor1 = new LatLng(23.0694985, 72.520996);
            LatLng doctor2 = new LatLng(23.0432584, 72.5476967);
            LatLng doctor3 = new LatLng(23.0297333, 72.5250718);
            LatLng doctor4 = new LatLng(23.0232346, 72.534574);
            LatLng doctor5 = new LatLng(23.7942379, 72.8914635);
            LatLng doctor6 = new LatLng(23.0544398, 72.5940073);

            LatLng hospital1 = new LatLng(23.0585348, 72.5152667);
            LatLng hospital2=new LatLng(21.7681294,72.1519258);
            LatLng hospital3=new LatLng(23.074673,72.5176434);
            LatLng hospital4=new LatLng(23.0378782,72.508453);
            LatLng hospital5=new LatLng(23.0986763,72.5461658);
            LatLng hospital6=new LatLng(23.057983,72.5102933);
            LatLng hospital7=new LatLng(23.0764756,72.5054148);
            LatLng hospital8=new LatLng(23.0686492,72.5412815);
            LatLng hospital9=new LatLng(23.02266,72.6214628);
            LatLng hospital10=new LatLng(23.0228075,72.55361);

            LatLng pharmacy1=new LatLng(23.0510915,72.5223351);
            LatLng pharmacy2=new LatLng(23.0645008,72.5404027);
            LatLng pharmacy3=new LatLng(23.0141642,72.5205598);
            LatLng pharmacy4=new LatLng(23.2282175,72.4914218);
            LatLng pharmacy5=new LatLng(22.1704137,70.7899017);
            LatLng pharmacy6=new LatLng(23.1090711,72.5849462);

            LatLng laboratory1 = new LatLng(23.0237,72.55912);
            LatLng laboratory2 = new LatLng(23.095115,72.544253);
            LatLng laboratory3 = new LatLng(23.0666949,72.562588);
            LatLng laboratory4 = new LatLng(23.0606059,72.5286723);
            LatLng laboratory5 = new LatLng(23.0535922,72.5767293);
            LatLng laboratory6 = new LatLng(23.0863612,72.5830689);
            LatLng laboratory7 = new LatLng(23.0526151,72.5981435);
            LatLng laboratory8 = new LatLng(23.1152308,72.5801596);
            LatLng laboratory9 = new LatLng(23.0380574,72.5573359);



            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


            mMap.addMarker(new MarkerOptions().position(doctor1)
                    .title("Dr Ajit M Sowani").snippet("Doctor")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(doctor1, 11.0f));

            mMap.addMarker(new MarkerOptions().position(doctor2)
                    .title("Paediatric Neurology Clinic").snippet("Doctor")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(doctor2, 11.0f));

            mMap.addMarker(new MarkerOptions().position(doctor3)
                    .title("Skin, Hair, Face Homeopathy Doctor, Clinic in Ahmedabad").snippet("Doctor")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(doctor3, 11.0f));

            mMap.addMarker(new MarkerOptions().position(doctor4)
                    .title("Shyam Hem Onc Clinic").snippet("Doctor")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(doctor4, 11.0f));

            mMap.addMarker(new MarkerOptions().position(doctor5)
                    .title("Aaryuratnam ayurvedic clinic").snippet("Doctor")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(doctor5, 11.0f));

            mMap.addMarker(new MarkerOptions().position(doctor6)
                    .title("Shreeji Skin Laser And Cosmetology Clinic").snippet("Doctor")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(doctor6, 11.0f));



            mMap.addMarker(new MarkerOptions().position(hospital1).title("Zydus Hospital").snippet("Hospital")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospital1, 11.0f));

            mMap.addMarker(new MarkerOptions().position(hospital2).title("Wockhardt Hospitals").snippet("Hospital")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospital2, 11.0f));

            mMap.addMarker(new MarkerOptions().position(hospital3).title("Sterling Hospital").snippet("Hospital")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospital3, 11.0f));

            mMap.addMarker(new MarkerOptions().position(hospital4).title("Sanjivani Hospital").snippet("Hospital")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospital4, 11.0f));

            mMap.addMarker(new MarkerOptions().position(hospital5).title("Lifeline Multispeciality Hospital, Gynaecology Doctors in Ahmedabad")
                    .snippet("Hospital") .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospital5, 11.0f));

            mMap.addMarker(new MarkerOptions().position(hospital6).title("Columbia Asia Hospital Ahmedabad").snippet("Hospital")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospital6, 11.0f));

            mMap.addMarker(new MarkerOptions().position(hospital7).title("CIMS Hospital").snippet("Hospital")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospital7, 11.0f));

            mMap.addMarker(new MarkerOptions().position(hospital8).title("Apollo Hospital").snippet("Hospital")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospital8, 11.0f));

            mMap.addMarker(new MarkerOptions().position(hospital9).title("Narayana Multispeciality Hospital, Ahmedabad ").snippet("Hospital")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospital9, 11.0f));

            mMap.addMarker(new MarkerOptions().position(hospital10).title("Shalby Hospitals").snippet("Hospital")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospital10, 11.0f));



            mMap.addMarker(new MarkerOptions().position(pharmacy1).title("Planet Health-SurdharaMedplus").snippet("Pharmacy")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pharmacy1, 11.0f));

            mMap.addMarker(new MarkerOptions().position(pharmacy2).title("Medplus").snippet("Pharmacy")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pharmacy2, 11.0f));

            mMap.addMarker(new MarkerOptions().position(pharmacy3).title("Frank Ross Pharmacy").snippet("Pharmacy")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pharmacy3, 11.0f));

            mMap.addMarker(new MarkerOptions().position(pharmacy4).title("Apollo Pharmacy").snippet("Pharmacy")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pharmacy4, 11.0f));

            mMap.addMarker(new MarkerOptions().position(pharmacy5).title("Akshar Pharmacy").snippet("Pharmacy")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pharmacy5, 11.0f));

            mMap.addMarker(new MarkerOptions().position(pharmacy6).title("Multicare Pharmacy").snippet("Pharmacy")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pharmacy6, 11.0f));



            mMap.addMarker(new MarkerOptions().position(laboratory1).title("Supratech Micropath").snippet("Laboratory")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(laboratory1, 11.0f));

            mMap.addMarker(new MarkerOptions().position(laboratory2).title("Sunrise Pathology Laboratory").snippet("Laboratory")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(laboratory2, 11.0f));

            mMap.addMarker(new MarkerOptions().position(laboratory3).title("Sun Pathology Laboratory ").snippet("Laboratory")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(laboratory3, 11.0f));

            mMap.addMarker(new MarkerOptions().position(laboratory4).title("Meet Pathology Laboratory").snippet("Laboratory")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(laboratory4, 11.0f));

            mMap.addMarker(new MarkerOptions().position(laboratory5).title("Gujarat Laboratory").snippet("Laboratory")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(laboratory5, 11.0f));

            mMap.addMarker(new MarkerOptions().position(laboratory6).title("Green Cross Pathology Laboratory").snippet("Laboratory")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(laboratory6, 11.0f));

            mMap.addMarker(new MarkerOptions().position(laboratory7).title("DNA Labs India").snippet("Laboratory")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(laboratory7, 11.0f));

            mMap.addMarker(new MarkerOptions().position(laboratory8).title("Bharat Pathology Laboratory").snippet("Laboratory")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(laboratory8, 11.0f));

            mMap.addMarker(new MarkerOptions().position(laboratory9).title("Altra Analytical Laboratories").snippet("Laboratory")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(laboratory9, 11.0f));


            mBtnMyLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mloc = new GPSTracker(getActivity());
                    if (mloc.canGetLocation()) {

                        double latitude = mloc.getLatitude();
                        double longitude = mloc.getLongitude();
                        LatLng currentlatlng = new LatLng(mloc.getLatitude(), mloc.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(currentlatlng)
                                .title("ME")
                                .snippet("CURRENT LOCATION")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentlatlng, 11.0f));
                        // \n is for new line
                        Toast.makeText(getContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    } else {
                        // can't get location
                        // GPS or Network is not enabled
                        // Ask user to enable GPS/network in settings
                        mloc.showSettingsAlert();
                    }

                }

            });

        }
    }


}
