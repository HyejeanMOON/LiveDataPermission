package com.hyejeanmoon.livedatapermission

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class InvisibleFragment : Fragment() {

    private lateinit var builder: LiveDataPermissionBuilder
    private lateinit var permissionList: List<String>
    private lateinit var callback: PermissionCallback

    fun request(
        builder: LiveDataPermissionBuilder,
        permissionList: List<String>,
        callback: PermissionCallback
    ) {
        this.builder = builder
        this.permissionList = permissionList
        this.callback = callback

        requestPermission()
    }

    private fun requestPermission() {
        builder.grantedPermissionList.clear()
        builder.deniedPermissionList.clear()
        val requestList: ArrayList<String> = arrayListOf()
        permissionList.forEachIndexed { index, permission ->
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                builder.grantedPermissionList.add(permission)
                builder.deniedPermissionList.remove(permission)
            } else {
                requestList.add(permission)
            }
        }

        if (requestList.isNotEmpty()) {
            requestPermissions(requestList.toTypedArray(), PERMISSION_REQUEST_CODE)
        }

        if (permissionList.size == builder.grantedPermissionList.size) {
            callback.setAllGrantedFlag(true)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            val numberOfPermission = builder.grantedPermissionList.size
            grantResults.forEachIndexed { index, permission ->
                if (permission == PackageManager.PERMISSION_GRANTED) {
                    builder.grantedPermissionList.add(permissions[index])
                    builder.deniedPermissionList.remove(permissions[index])

                } else {
                    builder.deniedPermissionList.add(permissions[index])
                    builder.grantedPermissionList.remove(permissions[index])
                }
            }
            callback.getPermission()

            if (permissions.size + numberOfPermission == builder.grantedPermissionList.size) {
                callback.setAllGrantedFlag(true)
            } else {
                callback.setAllGrantedFlag(false)
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1
    }

}