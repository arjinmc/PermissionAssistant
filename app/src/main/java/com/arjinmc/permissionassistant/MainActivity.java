package com.arjinmc.permissionassistant;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements PermissionAssistant.PermissionCallback {

    //this variable control force to grant all permissions
    private boolean isForceGrantAllPermissions;
    //init some permissions
    private String[] permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set permission request callback
        PermissionAssistant.setCallback(this);
        //add permissions for request
        PermissionAssistant.addPermission(permissions);

        Button btnCheck = (Button) findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isForceGrantAllPermissions = true;
                PermissionAssistant.setForceGrantAllPermissions(isForceGrantAllPermissions);

                requestPermission();
            }
        });

        Button btnSetting = (Button) findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionAssistant.openSystemPermissionSettting(MainActivity.this);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionAssistant.onRequestPermissionResult(permissions, grantResults);
    }

    @Override
    public void onAllow(String[] permission) {
        for (int i = 0; i < permission.length; i++) {
            Log.e("allow", permission[i]);
        }

    }

    @Override
    public void onDeny(String[] permission) {
        for (int i = 0; i < permission.length; i++) {
            Log.e("deny", permission[i]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isForceGrantAllPermissions) {
            requestPermission();
        }
    }

    private void requestPermission() {

        //if is not grant all permissions
        if (!PermissionAssistant.isGrantedAllPermissions(MainActivity.this)) {
            PermissionAssistant.requestPermissions(MainActivity.this);
        } else {
            Log.e("PermissionAssistant", "is Granted All Permissions");
        }
    }
}
