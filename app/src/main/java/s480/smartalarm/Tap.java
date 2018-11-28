package s480.smartalarm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tap extends AppCompatActivity implements SensorEventListener{

    //private SensorManager sensorManager;
    //double ax,ay,az;
    private boolean mInitialized;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mGyroscoper;
    private final float NOISE = (float) 2.0;
    private float mLastX, mLastY, mLastZ;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        //start music
        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.clock_tower_chimes);
        //mp.start();

        final int resID = getResources().getIdentifier("loud_alarm_clock", "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), resID);

        mediaPlayer.start();

        SharedPreferences sharedpreferences = getSharedPreferences("Alarms", Context.MODE_PRIVATE);

        if (!(sharedpreferences.getString("None", null) == null)) {

            Button stop = findViewById(R.id.Stop);
            stop.setText("TAP");
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /**Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                     .setAction("Action", null).show();**/
                    //startActivity(intent);
                    mediaPlayer.release();
                    finish();
                }
            });
        } else if (!(sharedpreferences.getString("Shaker", null) == null)) {
            mInitialized = false;
            SensorManager mSensorManager;
            Sensor mSensor;
            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

            Button stop = findViewById(R.id.Stop);
            stop.setText("SHAKE");
        }

        else if (!(sharedpreferences.getString("Rotater", null) == null)) {
            SensorManager mSensorManager;
            Sensor mSensor;
            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mGyroscoper = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            mSensorManager.registerListener(this, mGyroscoper , SensorManager.SENSOR_DELAY_NORMAL);
            Button stop = findViewById(R.id.Stop);
            stop.setText("ROTATE");

        }

        /** additional ideas can go here as "else ifs" **/
    }

    /*protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }*/

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            TextView tvX = (TextView) findViewById(R.id.textView);
            TextView tvY = (TextView) findViewById(R.id.textView2);
            TextView tvZ = (TextView) findViewById(R.id.textView3);
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            if (!mInitialized) {
                mLastX = x;
                mLastY = y;
                mLastZ = z;
                tvX.setText("0.0");
                tvY.setText("0.0");
                tvZ.setText("0.0");
                mInitialized = true;
            } else {
                float deltaX = Math.abs(mLastX - x);
                float deltaY = Math.abs(mLastY - y);
                float deltaZ = Math.abs(mLastZ - z);
                if (deltaX < NOISE) deltaX = (float) 0.0;
                if (deltaY < NOISE) deltaY = (float) 0.0;
                if (deltaZ < NOISE) deltaZ = (float) 0.0;
                mLastX = x;
                mLastY = y;
                mLastZ = z;
                tvX.setText(Float.toString(deltaX));
                tvY.setText(Float.toString(deltaY));
                tvZ.setText(Float.toString(deltaZ));
                if (deltaX + deltaY + deltaZ > 16) {
                    mediaPlayer.release();
                    finish();
                }
            }
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            if (event.values[2] > 5f || event.values[2] < -5f) {
                mediaPlayer.release();
                finish();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
