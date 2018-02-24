package mobile.maps.itsbustracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Load preference */
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
        setContentView(R.layout.activity_main);

        ImageButton btnMaps = (ImageButton) findViewById(R.id.btnMaps);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);

                startActivity(intent);
            }
        });

        /* Change font */
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/Montserrat-ExtraLight.ttf");

        TextView tvTrack = (TextView) findViewById(R.id.tvTrack);
        tvTrack.setTypeface(typeface);

        TextView tvSchedule = (TextView) findViewById(R.id.tvSchedule);
        tvSchedule.setTypeface(typeface);

        TextView tvAbout = (TextView) findViewById(R.id.tvAbout);
        tvAbout.setTypeface(typeface);

        TextView tvSetting = (TextView) findViewById(R.id.tvSetting);
        tvSetting.setTypeface(typeface);
    }

    public void intent_jadwal(View view) {
        Intent intent = new Intent(MainActivity.this, Jadwal.class);
        startActivity(intent);
    }

    public void intent_setting(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void intent_about(View view) {
        startActivity(new Intent(MainActivity.this, AboutActivity.class));
    }
}
