package mobile.maps.itsbustracker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;

public class SplashScreen extends Activity {
    public static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_splash_screen);

/*        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String bg = sharedPref.getString("pref_theme", "");

        getWindow().getDecorView().setBackgroundColor(Color.parseColor(bg));*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}