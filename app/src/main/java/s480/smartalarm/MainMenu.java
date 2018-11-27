package s480.smartalarm;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedpreferences = getSharedPreferences("Alarms", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedpreferences.edit();
        //editor.putString("key", "value");
        //editor.commit();
        //TextView dynamicTextView = new TextView(this);
        //dynamicTextView.setLayoutParams(new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
        //dynamicTextView.setText("Did I make text?");

        RecyclerView alarms = findViewById(R.id.AlarmList);
        Switch alarm1 = findViewById(R.id.switch1);
        String holder = "";

        if (!(sharedpreferences.getString("name", null) == null)){
            holder += sharedpreferences.getString("name", null);
        }

        else {holder = "Default";}

        if (!(sharedpreferences.getString("Monday", null) == null)){
            holder += " " + sharedpreferences.getString("Monday", null);
        }
        if (!(sharedpreferences.getString("Tuesday", null) == null)) {
            holder += " " + sharedpreferences.getString("Tuesday", null);
        }
        if (!(sharedpreferences.getString("Wednesday", null) == null)) {
            holder += " " + sharedpreferences.getString("Wednesday", null);
        }
        if (!(sharedpreferences.getString("Thursday", null) == null)) {
            holder += " " + sharedpreferences.getString("Thursday", null);
        }
        if (!(sharedpreferences.getString("Friday", null) == null)) {
            holder += " " + sharedpreferences.getString("Friday", null);
        }
        if (!(sharedpreferences.getString("Saturday", null) == null)) {
            holder += " " + sharedpreferences.getString("Saturday", null);
        }
        if (!(sharedpreferences.getString("Sunday", null) == null)) {
            holder += " " + sharedpreferences.getString("Sunday", null);
        }
        if (!(sharedpreferences.getString("None", null) == null)) {
            holder += sharedpreferences.getString("None", null);
        }
        if (!(sharedpreferences.getString("Shaker", null) == null)) {
            holder += sharedpreferences.getString("Shaker", null);
        }
        if (!(sharedpreferences.getString("Rotater", null) == null)) {
            holder += sharedpreferences.getString("Rotater", null);
        }

        alarm1.setText(holder);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final Intent intent = new Intent(this, NewAlarm.class);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();**/
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        SharedPreferences sharedpreferences = getSharedPreferences("Alarms", Context.MODE_PRIVATE);
        Switch alarm1 = findViewById(R.id.switch1);
        String holder = "";
        // Check which request we're responding to
        if (requestCode == 1) {
                if (!(sharedpreferences.getString("name", null) == null)){
                    holder += sharedpreferences.getString("name", null);
                }
                if (!(sharedpreferences.getString("Monday", null) == null)){
                    holder += " " + sharedpreferences.getString("Monday", null);
                }
                if (!(sharedpreferences.getString("Tuesday", null) == null)) {
                    holder += " " + sharedpreferences.getString("Tuesday", null);
                }
                if (!(sharedpreferences.getString("Wednesday", null) == null)) {
                    holder += " " + sharedpreferences.getString("Wednesday", null);
                }
                if (!(sharedpreferences.getString("Thursday", null) == null)) {
                    holder += " " + sharedpreferences.getString("Thursday", null);
                }
                if (!(sharedpreferences.getString("Friday", null) == null)) {
                    holder += " " + sharedpreferences.getString("Friday", null);
                }
                if (!(sharedpreferences.getString("Saturday", null) == null)) {
                    holder += " " + sharedpreferences.getString("Saturday", null);
                }
                if (!(sharedpreferences.getString("Sunday", null) == null)) {
                    holder += " " + sharedpreferences.getString("Sunday", null);
                }
                if (!(sharedpreferences.getString("None", null) == null)) {
                    holder += sharedpreferences.getString("None", null);
                }
                if (!(sharedpreferences.getString("Shaker", null) == null)) {
                    holder += sharedpreferences.getString("Shaker", null);
                }
                if (!(sharedpreferences.getString("Rotater", null) == null)) {
                    holder += sharedpreferences.getString("Rotater", null);
                }

                alarm1.setText(holder);
           // }
        }
    }
    private void scheduleAlarm(int dayOfWeek, int hour, int minute) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        // Check we aren't setting it in the past which would trigger it to fire instantly
        if(calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }

        // Set this to whatever you were planning to do at the given time
        //PendingIntent yourIntent;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, /**yourIntent**/);
    }
}
