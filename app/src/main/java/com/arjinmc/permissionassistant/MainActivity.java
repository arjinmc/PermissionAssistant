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

        //Add permissions for request.
        PermissionAssistant.addPermission(permissions);
        //Set if need to force grant all permissions.
        PermissionAssistant.setForceGrantAllPermissions(isForceGrantAllPermissions);

        //Set permission request callback if you need to know which permission is granted,which is denied,
        //and override onAllow() on onDeny().
        //This callback is not necessary.
        PermissionAssistant.setCallback(this);

        Button btnCheck = (Button) findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If you don't need to force grant all permission,call this to request.
                PermissionAssistant.requestPermissions(MainActivity.this);
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
        //Add this method if you need the grant callback when use PermissionAssistant.setCallback(PermissionCallback);
        PermissionAssistant.onRequestPermissionResult(permissions, grantResults);
    }

    @Override
    public void onAllow(String[] permission) {
        if(permission==null)
            return ;
        for (int i = 0; i < permission.length; i++) {
            Log.e("allow", permission[i]);
        }

    }

    @Override
    public void onDeny(String[] permission) {
        if(permission==null)
            return ;
        for (int i = 0; i < permission.length; i++) {
            Log.e("deny", permission[i]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //If you need to force grant all permission,call this at in Activity.onReumse()
        PermissionAssistant.forceRequestPermissions(this);
    }


}
