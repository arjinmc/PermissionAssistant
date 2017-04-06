# PermissionAssistant
A common tool for Android Permission,support above Android SDK 23 (Android 6.0)  
support Phone xiaomi.  

#How to use
## add permissions
```java

    private String[] permissions = {
        Manifest.permission.READ_CONTACTS
        , Manifest.permission.SEND_SMS};

    //or

    private String permission = Manifest.permission.READ_CONTACTS;

    PermissionAssistant.addPermission(permissions);

```

## add callback
```java

implements PermissionAssistant.PermissionCallback  

PermissionAssistant.setCallback(this);

```

###callback response for these methods:

```java

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

```

## set force grant all permissions 
```java

    PermissionAssistant.setForceGrantAllPermissions(true);

```

## request Permissions
```java
    //if is not grant all permissions
    if (!PermissionAssistant.isGrantedAllPermissions(context)) {
        PermissionAssistant.requestPermissions(context);
    } else {
        Log.e("PermissionAssistant", "is Granted All Permissions");
    }
```




