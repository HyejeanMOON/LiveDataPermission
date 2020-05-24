package com.hyejeanmoon.livedatapermissiondemo

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyejeanmoon.livedatapermission.EventObserver
import com.hyejeanmoon.livedatapermission.LiveDataPermission
import com.hyejeanmoon.livedatapermissiondemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var permission = LiveDataPermission()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.btPermission.setOnClickListener {
            permission.init(this)
                .permission(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_CALENDAR
                )
                .requestShowExplanationMessage().build()
        }

        permission.permissionResult.grantedPermissionList.observe(this, EventObserver {
            Log.d("LiveDataPermission", "grantedPermissionList: ${it.size}")
        })

        permission.permissionResult.deniedPermissionList.observe(this, EventObserver {
            Log.d("LiveDataPermission", "deniedPermissionList: ${it.size}")
        })

        permission.permissionResult.showExplanationMessageEvent.observe(this, EventObserver {
            Log.d("LiveDataPermission", "showExplanationMessageEvent")
        })

        permission.permissionResult.allGranted.observe(this, EventObserver {
            if (it) {
                Toast.makeText(this, "AllGranted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "No AllGranted", Toast.LENGTH_LONG).show()
            }

        })
    }

}
