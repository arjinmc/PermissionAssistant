package com.arjinmc.permissionassistant;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements PermissionAssistant.PermissionCallback {

    //This variable control force to grant all permissions,switch it with true/false.
    private boolean isForceGrantAllPermissions = true;
    //Init some permissions that you want to request
    private String[] permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheck = (Button) findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //request permission
                requestPermisstions();
            }
        });

        Button btnSetting = (Button) findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionAssistant.openSystemPermissionSetting(MainActivity.this);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("onRequest", "requestCode:" + requestCode);
        //Add this method if you need the grant callback when use PermissionAssistant.setCallback(PermissionCallback);
        PermissionAssistant.onRequestPermissionResult(permissions, grantResults, this);
    }

    @Override
    public void onAllow(String[] permissions) {
        if (permissions == null)
            return;
        for (int i = 0; i < permissions.length; i++) {
            Log.e("allow", permissions[i]);
        }

    }

    @Override
    public void onDeny(String[] permissions) {
        if (permissions == null)
            return;
        for (int i = 0; i < permissions.length; i++) {
            Log.e("deny", permissions[i]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isForceGrantAllPermissions) {
            requestPermisstions();
        }
    }


    private void requestPermisstions() {
        //request permission
        PermissionAssistant.requestPermissions(MainActivity.this, permissions, isForceGrantAllPermissions);
    }

}
