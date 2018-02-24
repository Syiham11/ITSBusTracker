package mobile.maps.itsbustracker;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    public double latitude_bus1, longitude_bus1;
    public double latitude_bus2, longitude_bus2;
    public double latitude_bus3, longitude_bus3;
    MarkerOptions markerOpt;
    Marker marker_bus1, marker_bus2, marker_bus3;

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = sharedPref.getString("pref_theme", "");

        if (theme.equals("default")) {
            setTheme(R.style.AppTheme_NoActionBar);
        } else if (theme.equals("pink")) {
            setTheme(R.style.Pink_NoActionBar);
        } else if (theme.equals("dark")) {
            setTheme(R.style.Dark_NoActionBar);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        /* Change toolbar color */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMap);
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        /* Change font */
        Typeface light = Typeface.createFromAsset(getAssets(), "font/Montserrat-Light.ttf");

        TextView tvTitle = (TextView) findViewById(R.id.titleMap);
        tvTitle.setTypeface(light);

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

        LatLngBounds ITS = new LatLngBounds(
                new LatLng(-7.2901064, 112.78989315), new LatLng(-7.27524977, 112.79942036)
        );
        mMap.setLatLngBoundsForCameraTarget(ITS);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ITS.getCenter(), 16));

        mMap.addMarker(new MarkerOptions().position(new LatLng(-7.27787, 112.797))
                .title("Bus Stop").icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));

        mMap.addMarker(new MarkerOptions().position(new LatLng(-7.27955, 112.798))
                .title("Bus Stop").icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));

        mMap.addMarker(new MarkerOptions().position(new LatLng(-7.28135, 112.79805))
                .title("Bus Stop").icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));

        mDatabase.child("driver1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                latitude_bus1 = (double) dataSnapshot.child("latitude").getValue();
                longitude_bus1 = (double) dataSnapshot.child("longitude").getValue();

                LatLng bus1 = new LatLng(latitude_bus1, longitude_bus1);

                if (marker_bus1 != null) {
                    marker_bus1.setPosition(bus1);
                } else {
                    markerOpt = new MarkerOptions().position(bus1).title("Bus 1")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker1));
                    marker_bus1 = mMap.addMarker(markerOpt);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MapsActivity.this, "Error: Can not fetch location", Toast.LENGTH_SHORT).show();
            }
        });

        mDatabase.child("driver2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                latitude_bus2 = (double) dataSnapshot.child("latitude").getValue();
                longitude_bus2 = (double) dataSnapshot.child("longitude").getValue();

                LatLng bus2 = new LatLng(latitude_bus2, longitude_bus2);

                if (marker_bus2 != null) {
                    marker_bus2.setPosition(bus2);
                } else {
                    markerOpt = new MarkerOptions().position(bus2).title("Bus 2")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker2));
                    marker_bus2 = mMap.addMarker(markerOpt);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MapsActivity.this, "Error: Can not fetch location", Toast.LENGTH_SHORT).show();
            }
        });

        mDatabase.child("driver3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                latitude_bus3 = (double) dataSnapshot.child("latitude").getValue();
                longitude_bus3= (double) dataSnapshot.child("longitude").getValue();

                LatLng bus3 = new LatLng(latitude_bus3, longitude_bus3);

                if (marker_bus3 != null) {
                    marker_bus3.setPosition(bus3);
                } else {
                    markerOpt = new MarkerOptions().position(bus3).title("Bus 3")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker3));
                    marker_bus3 = mMap.addMarker(markerOpt);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MapsActivity.this, "Error: Can not fetch location", Toast.LENGTH_SHORT).show();
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

    public void upHandler(View view) {
        this.finish();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}