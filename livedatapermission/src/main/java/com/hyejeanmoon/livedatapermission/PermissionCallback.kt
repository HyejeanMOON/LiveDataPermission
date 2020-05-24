package com.hyejeanmoon.livedatapermission

interface PermissionCallback {
    fun showExplanation()
    fun getPermission()
    fun setAllGrantedFlag(flag: Boolean)

}