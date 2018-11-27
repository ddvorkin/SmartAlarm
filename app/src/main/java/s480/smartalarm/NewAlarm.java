package s480.smartalarm;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

public class NewAlarm extends AppCompatActivity {

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));

        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user

            final TextView text = getActivity().findViewById(R.id.inputTime);
            if (minute >= 10) {
                text.setText("" + hourOfDay + ":" + minute);
            }
            else{
                text.setText("" + hourOfDay + ":0" + minute);
            }
        }

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);

        final Button button = findViewById(R.id.SetTime);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        final Button submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final TextView text = findViewById(R.id.inputTime);
                final CheckBox Mon = findViewById(R.id.Mon);
                final CheckBox Tues = findViewById(R.id.Tues);
                final CheckBox Wed = findViewById(R.id.Wed);
                final CheckBox Thur = findViewById(R.id.Thur);
                final CheckBox Fri = findViewById(R.id.Fri);
                final CheckBox Sat = findViewById(R.id.Sat);
                final CheckBox Sun = findViewById(R.id.Sun);
                final RadioButton None = findViewById(R.id.None);
                final RadioButton Shaker = findViewById(R.id.shaker);
                final RadioButton Rotater = findViewById(R.id.rotater);
                SharedPreferences sharedpreferences = getSharedPreferences("Alarms", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("name", text.getText().toString());
                if (Mon.isChecked()) {
                    editor.putString("Monday", Mon.getText().toString());
                }
                else{
                    editor.putString("Monday", null);
                }
                if (Tues.isChecked()) {
                    editor.putString("Tuesday", Tues.getText().toString());
                }
                else{
                    editor.putString("Tuesday", null);
                }
                if (Wed.isChecked()) {
                    editor.putString("Wednesday", Wed.getText().toString());
                }
                else{
                    editor.putString("Wednesday", null);
                }
                if (Thur.isChecked()) {
                    editor.putString("Thursday", Thur.getText().toString());
                }
                else{
                    editor.putString("Thursday", null);
                }
                if (Fri.isChecked()) {
                    editor.putString("Friday", Fri.getText().toString());
                }
                else{
                    editor.putString("Friday", null);
                }
                if (Sat.isChecked()) {
                    editor.putString("Saturday", Sat.getText().toString());
                }
                else{
                    editor.putString("Saturday", null);
                }
                if (Sun.isChecked()) {
                    editor.putString("Sunday", Sun.getText().toString());
                }
                else{
                    editor.putString("Sunday", null);
                }
                if (None.isChecked()){
                    editor.putString("None", "\n Alarm off method: None");
                }
                else{
                    editor.putString("None", null);
                }
                if (Shaker.isChecked()){
                    editor.putString("Shaker", "\n Alarm off method: Shake");
                }
                else{
                    editor.putString("Shaker", null);
                }
                if (Rotater.isChecked()){
                    editor.putString("Rotater", "\n Alarm off method: Rotate");
                }
                else{
                    editor.putString("Rotater", null);
                }

                editor.commit();
                finish();
            }
        });


    }
}
