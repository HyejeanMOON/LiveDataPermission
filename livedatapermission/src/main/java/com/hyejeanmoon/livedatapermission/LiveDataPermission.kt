package com.hyejeanmoon.livedatapermission

import android.app.Activity
import androidx.fragment.app.FragmentActivity

class LiveDataPermission {

    val permissionResult = PermissionResult()

    fun init(activity: Activity): LiveDataPermissionClass =
        LiveDataPermissionClass(activity, permissionResult)

}

class LiveDataPermissionClass internal constructor(
    private val activity: Activity,
    private val permissionResult: PermissionResult
) {

    fun permission(vararg permission: String): LiveDataPermissionBuilder {
        return LiveDataPermissionBuilder(
            activity as FragmentActivity,
            permission.toList(),
            permissionResult
        )
    }
}