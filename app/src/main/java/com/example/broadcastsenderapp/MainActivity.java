package com.example.broadcastsenderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;

import com.example.broadcastsenderapp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize(){
        context = this;

        binding.btnSendBroadcastMassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Implicit Broadcast Receiver Intent use

               /* Intent intent = new Intent();
                intent.setAction("com.broadcastFirstSenderApp.myBroadcastMessage");
                intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.putExtra("privateMessage",binding.edtMessage.getText().toString());
                sendBroadcast(intent);
                binding.edtMessage.getText().clear();*/

                //  Explicit Broadcast Receiver Intent use
                Intent intent = new Intent("com.broadcastFirstSenderApp.myBroadcastMessage");
                intent.putExtra("privateMessage", binding.edtMessage.getText().toString());

               /* ComponentName cn = new ComponentName("com.codinginflow.broadcastexample",
                "com.codinginflow.broadcastexample.ExampleBroadcastReceiver");
                intent.setComponent(cn);*/

                /*intent.setClassName("com.codinginflow.broadcastexample",
                "com.codinginflow.broadcastexample.ExampleBroadcastReceiver");*/

                // intent.setPackage("com.codinginflow.broadcastexample");

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> infos = packageManager.queryBroadcastReceivers(intent, 0);
                for (ResolveInfo info : infos) {
                    ComponentName cn = new ComponentName(info.activityInfo.packageName,
                            info.activityInfo.name);
                    intent.setComponent(cn);
                    sendBroadcast(intent);
                    binding.edtMessage.getText().clear();
                }
                //sendBroadcast(intent);
            }
        });
    }
}