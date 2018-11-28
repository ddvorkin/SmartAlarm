package s480.smartalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context ctxt, Intent intent) {

        Toast.makeText(ctxt, "OnReceive alarm test", Toast.LENGTH_SHORT).show();

        Intent i=new Intent(ctxt, Tap.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ctxt.startActivity(i);
    }
}