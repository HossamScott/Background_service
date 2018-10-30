package hossamscott.com.github.exampleproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import hossamscott.com.github.backgroundservice.RunService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Start the servuce to run for 5 minutes
         */
        RunService service = new RunService(this);
        service.call(300);

        /*
        Register BroadcastReceiver to get notification when service is over
         */
        IntentFilter intentFilter = new IntentFilter("alaram_received");
        registerReceiver(alarm_receiver, intentFilter);
    }

    BroadcastReceiver alarm_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // your logic here
            Log.i("alarm_received", "success");

            /*
            If you want the service to run only once than remove the 2 lines below
             */
            RunService service = new RunService(context);
            service.call(300);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(alarm_receiver); // to stop the broadcast when the app is killed
    }
}
