# Background-Service

Android library for calling service in background or foreground service.

With this library, you can call any method with specific timer and get a broadcast call-back when that timer expires.


# How to integrate the library in your app?


## Gradle Dependency 
```

dependencies {
        implementation 'com.hossam.backgroundService:backgroundService:0.1.0'
}
```
### or use 

## Maven Dependency 
```
<dependency>
  <groupId>com.hossam.backgroundService</groupId>
  <artifactId>backgroundService</artifactId>
  <version>0.1.0</version>
  <type>pom</type>
</dependency>
```

# How it works

In your MainActivity call the service and give it the time you want in **Seconds**.
> Example : You need to set service.call(...); to 1800 if you want to call the service in 30 minutes.

## Java Code
```
RunService service = new RunService(this);
service.call(1800);
```

## Kotlin Code
```
val service = RunService(this)
service.call(1740)
```
Now, to call your method after 30 minues, 
You have to register broadcastreciever by making following changes.

Inside your OnCreate() add following line:

## Java Code
```
IntentFilter intentFilter = new IntentFilter("alaram_received");
registerReceiver(alarm_receiver, intentFilter);
```

## Kotlin Code
```
val intentFilter = IntentFilter("alaram_received")
registerReceiver(alarm_receiver, intentFilter)
```
Place your code inside onReceive

## Java Code
```
  BroadcastReceiver alarm_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // your logic here
            Log.i("alarm_received", "success");
 
        }
    };
```

## Kotlin Code
```
   internal var alarm_receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // your logic here
            Log.i("alarm_received", "success")

        }
    }
```

If you want to recall the receiver, call the service inside of the method onRecieve which will start the service again.



Dont forget: 

## Java Code
```
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(alarm_receiver);
    }
```
## Kotlin Code
```
     override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(alarm_receiver)
    }
```


## Gradle Settings

```
defaultConfig {
        minSdkVersion 15
        targetSdkVersion 28
    }
```
# License
Apache License v2.0.

# Contact 
Via-email : Hossamscott@gmail.com

**_If you would like to suggest feature or report a bug please send it as issue at the top_**

