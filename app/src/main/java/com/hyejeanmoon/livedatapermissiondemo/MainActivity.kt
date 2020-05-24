package com.hyejeanmoon.livedatapermissiondemo

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hyejeanmoon.livedatapermission.LiveDataPermission
import com.hyejeanmoon.livedatapermissiondemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var permission = LiveDataPermission()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.btPermission.setOnClickListener {
            permission.init(this).permission(Manifest.permission.CAMERA).build()
        }

        permission.permissionResult.grantedPermissionList.observe(this, Observer {
            Toast.makeText(this, it.peekContent().toString(), Toast.LENGTH_LONG).show()
        })
    }

}
