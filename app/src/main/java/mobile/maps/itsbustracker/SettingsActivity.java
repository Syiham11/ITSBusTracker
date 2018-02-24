package mobile.maps.itsbustracker;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends FragmentActivity {
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
        setContentView(R.layout.activity_settings);

        /* Change toolbar color */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSetting);
        if (theme.equals("default")) {
            toolbar.setBackgroundColor(Color.parseColor("#147786"));
        } else if (theme.equals("pink")) {
            toolbar.setBackgroundColor(Color.parseColor("#ff4081"));
        } else if (theme.equals("dark")) {
            toolbar.setBackgroundColor(Color.parseColor("#181818"));
        }

        /* Change font */
        Typeface light = Typeface.createFromAsset(getAssets(), "font/Montserrat-Light.ttf");

        TextView tvTitle = (TextView) findViewById(R.id.titleSetting);
        tvTitle.setTypeface(light);

/*        String bg = "#ffffff";
        getWindow().getDecorView().setBackgroundColor(Color.parseColor(bg));*/

        // Display the fragment as the main content.
/*        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();*/
    }

    public void upHandler(View view) {
        this.finish();
    }
}
