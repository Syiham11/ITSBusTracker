package mobile.maps.itsbustracker;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

public class Jadwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Load preferences */
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = sharedPref.getString("pref_theme", "");

        /* Set theme based on preference */
        if (theme.equals("default")) {
            setTheme(R.style.AppTheme_NoActionBar);
        } else if (theme.equals("pink")) {
            setTheme(R.style.Pink_NoActionBar);
        } else if (theme.equals("dark")) {
            setTheme(R.style.Dark_NoActionBar);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        /* Change toolbar color */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSchedule);
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        /* Change Font */
        Typeface light = Typeface.createFromAsset(getAssets(), "font/Montserrat-Light.ttf");
        Typeface extralight = Typeface.createFromAsset(getAssets(), "font/Montserrat-ExtraLight.ttf");

        TextView tvTitle = (TextView) findViewById(R.id.titleSchedule);
        tvTitle.setTypeface(light);

        TextView tvRoute1 = (TextView) findViewById(R.id.tvRoute1);
        tvRoute1.setTypeface(extralight);

        TextView tvRoute2 = (TextView) findViewById(R.id.tvRoute2);
        tvRoute2.setTypeface(extralight);

        TextView tvRouteA = (TextView) findViewById(R.id.tvRouteA);
        tvRouteA.setTypeface(extralight);

        TextView tvRouteB = (TextView) findViewById(R.id.tvRouteB);
        tvRouteB.setTypeface(extralight);

        TextView tvRouteC = (TextView) findViewById(R.id.tvRouteC);
        tvRouteC.setTypeface(extralight);

        TextView tvRouteD = (TextView) findViewById(R.id.tvRouteD);
        tvRouteD.setTypeface(extralight);

        TextView tvRouteE = (TextView) findViewById(R.id.tvRouteE);
        tvRouteE.setTypeface(extralight);

        TextView tvRouteF = (TextView) findViewById(R.id.tvRouteF);
        tvRouteF.setTypeface(extralight);

        TextView tvRouteG = (TextView) findViewById(R.id.tvRouteG);
        tvRouteG.setTypeface(extralight);

        TextView tvRouteH = (TextView) findViewById(R.id.tvRouteH);
        tvRouteH.setTypeface(extralight);

        TextView tvRouteI = (TextView) findViewById(R.id.tvRouteI);
        tvRouteI.setTypeface(extralight);

        TextView tvRouteJ = (TextView) findViewById(R.id.tvRouteJ);
        tvRouteJ.setTypeface(extralight);

        TextView tvRouteK = (TextView) findViewById(R.id.tvRouteK);
        tvRouteK.setTypeface(extralight);

        TextView tvRouteL = (TextView) findViewById(R.id.tvRouteL);
        tvRouteL.setTypeface(extralight);

        TextView tvRouteM = (TextView) findViewById(R.id.tvRouteM);
        tvRouteM.setTypeface(extralight);

        TextView tvRouteN = (TextView) findViewById(R.id.tvRouteN);
        tvRouteN.setTypeface(extralight);

        TextView tvRouteO = (TextView) findViewById(R.id.tvRouteO);
        tvRouteO.setTypeface(extralight);

        TextView tvRouteP = (TextView) findViewById(R.id.tvRouteP);
        tvRouteP.setTypeface(extralight);

        TextView tvRouteQ = (TextView) findViewById(R.id.tvRouteQ);
        tvRouteQ.setTypeface(extralight);

        TextView tvRouteR = (TextView) findViewById(R.id.tvRouteR);
        tvRouteR.setTypeface(extralight);

        TextView tvRouteNB = (TextView) findViewById(R.id.tvNB);
        tvRouteNB.setTypeface(extralight);
    }

    public void upHandler(View view) {
        this.finish();
    }
}