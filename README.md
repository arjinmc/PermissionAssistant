# PermissionAssistant
A common tool for Android Permission,support above Android SDK 23 (Android 6.0)  
support Phone xiaomi.  

#How to use
## request permission / permissions
```java

private String[] permissions = {
    Manifest.permission.READ_CONTACTS
    , Manifest.permission.SEND_SMS};

PermissionAssistant.requestPermissions(MainActivity.this, permissions, isForceGrantAllPermissions);
     
//or      

PermissionAssistant.requestPermissions(MainActivity.this, Manifest.permission.READ_CONTACTS, isForceGrantAllPermissions);

```

## add callback
```java

implements PermissionAssistant.PermissionCallback


//add this to int Activity  onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

PermissionAssistant.onRequestPermissionResult(permissions, grantResults, this);

```

### callback for these methods:

```java

@Override
public void onAllow(String[] permissions) {
    for (int i = 0; i < permissions.length; i++) {
        Log.e("allow", permissions[i]);
    }
}

@Override
public void onDeny(String[] permissions) {
    for (int i = 0; i < permissions.length; i++) {
        Log.e("deny", permissions[i]);
    }
}

```

## check granted all Permissions
```java
//if is not grant all permissions
if (!PermissionAssistant.isGrantedAllPermissions(context)) {
    PermissionAssistant.requestPermissions(context);
} else {
    Log.e("PermissionAssistant", "is Granted All Permissions");
}
```
