package mobile.maps.itsbustracker;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Load preferences */
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = sharedPref.getString("pref_theme", "");

        /* Change theme based on preference */
        if (theme.equals("default")) {
            setTheme(R.style.AppTheme_NoActionBar);
        } else if (theme.equals("pink")) {
            setTheme(R.style.Pink_NoActionBar);
        } else if (theme.equals("dark")) {
            setTheme(R.style.Dark_NoActionBar);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        /* Change toolbar color */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAbout);
        toolbar.setBackgroundColor(Color.TRANSPARENT);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Change font */
        Typeface light = Typeface.createFromAsset(getAssets(), "font/Montserrat-Light.ttf");
        Typeface extralight = Typeface.createFromAsset(getAssets(), "font/Montserrat-ExtraLight.ttf");

        TextView tvTitle = (TextView) findViewById(R.id.titleAbout);
        tvTitle.setTypeface(light);

        TextView tvTitleVersion = (TextView) findViewById(R.id.tvTitleVersion);
        tvTitleVersion.setTypeface(light);

        TextView tvVersion = (TextView) findViewById(R.id.tvVersion);
        tvVersion.setTypeface(extralight);

        TextView tvTitleTeam = (TextView) findViewById(R.id.tvTitleTeam);
        tvTitleTeam.setTypeface(light);

        TextView tvTeam1 = (TextView) findViewById(R.id.tvTeam1);
        tvTeam1.setTypeface(extralight);

        TextView tvTeam2 = (TextView) findViewById(R.id.tvTeam2);
        tvTeam2.setTypeface(extralight);

        TextView tvTeam3 = (TextView) findViewById(R.id.tvTeam3);
        tvTeam3.setTypeface(extralight);

        TextView tvTeam4 = (TextView) findViewById(R.id.tvTeam4);
        tvTeam4.setTypeface(extralight);

        TextView tvTeam5 = (TextView) findViewById(R.id.tvTeam5);
        tvTeam5.setTypeface(extralight);
    }

    public void upHandler(View view) {
        this.finish();
    }
}