package nsit.app.com.nsitapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Sidharth Patro on 01-Jul-15.
 */
public class LocationMapView extends Activity {
    com.google.android.gms.maps.MapFragment mapFragment;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view);
        this.mapFragment = (com.google.android.gms.maps.MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        Bundle extras = getIntent().getExtras();
        Log.e("extras",extras.toString());
        if (extras != null) {
            String LocationLat = extras.getString("LocationLat");
            String LocationLong = extras.getString("LocationLong");
            String LocationName = extras.getString("LocationName");
            Log.e("LocationLat",LocationLat);
            Log.e("LocationLong",LocationLong);
            Log.e("LocationName",LocationName);
            ShowMarker(Double.parseDouble(LocationLat),Double.parseDouble(LocationLong),LocationName);
        }
    }

    public void ShowMarker(Double LocationLat, Double LocationLong, String LocationName){
        GoogleMap map = mapFragment.getMap();
        LatLng Coord = new LatLng(LocationLat, LocationLong);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Coord, 17));

        map.addMarker(new MarkerOptions()
                .title(LocationName)
                .position(Coord)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }

}
