package hossamscott.com.github.exampleproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import hossamscott.com.github.backgroundservice.RunService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_once = findViewById(R.id.bt_once);
        Button bt_repeat = findViewById(R.id.bt_repeat);
        Button bt_range = findViewById(R.id.bt_range);

        bt_once.setOnClickListener(this);
        bt_repeat.setOnClickListener(this);
        bt_range.setOnClickListener(this);

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

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(alarm_receiver); // to stop the broadcast when the app is killed
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_once:
                /*
                Start the servuce to run for 5 minutes only once
                 */
                RunService service = new RunService(this);
                service.call(300);
                break;

            case R.id.bt_repeat:
                /*
                If you want to repeat the alarm every X sec just add true in your call
                 */
                RunService repeat = new RunService(this);
                repeat.call(300, true);
                break;

            case R.id.bt_range:
                /*
                 * If you want to start the service at random times within range limit
                 * First Value need to be smaller than second value
                 */
                RunService range = new RunService(this);
                range.call(5, 30);
                break;
        }
    }
}
