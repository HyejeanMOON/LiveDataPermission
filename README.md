# LiveDataPermission

The library is developing.

## Summary
There is a library that easily request application permissions in Android , and data type of result is LiveData.

## How to use
```kotlin
        // create a LivedataPermisstion instance.
        var permission = LiveDataPermission()
```

```kotlin
        binding.btPermission.setOnClickListener {
            // initialize libarary
            permission.init(this)
                .permission(
                    // add a list of required permissions.
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_CALENDAR
                )
                // if you need to show explanation dialog, you should pass a message to library.
                .showDefaultExplanationDialog("The application is need to request some permissions, please agree!")
                .build()
        }
```

```kotlin
        // observe result of permission request.
      
        permission.permissionResult.grantedPermissionList.observe(this, EventObserver {
            // receive a list of granted permissions, but [grantedPermissionList] do not receive result when all of permissions are granted.
            Log.d("LiveDataPermission", "grantedPermissionList: ${it.size}")
        })

        permission.permissionResult.deniedPermissionList.observe(this, EventObserver {
            // receive a list of denied permissions, but [deniedPermissionList] do not receive result when all of permissions are granted. 
            Log.d("LiveDataPermission", "deniedPermissionList: ${it.size}")
        })
        
        permission.permissionResult.allGranted.observe(this, EventObserver {
            // receive result every time.
            if (it) {
                Toast.makeText(this, "AllGranted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "No AllGranted", Toast.LENGTH_LONG).show()
            }

        })
```

## Others

### About EventObserver
We should use [EventObserver] when observe a result from library. Because [Observer] should receive every time when onResume is called. So, I wraped [Observer] and it is called [EventObserver], it receive result just one time.

### Problems
The function of show explanation dialog is not good. I'll optimize later.
