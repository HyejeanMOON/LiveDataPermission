package com.hyejeanmoon.livedatapermission

import androidx.fragment.app.FragmentActivity

class LiveDataPermissionBuilder internal constructor(
    private val activity: FragmentActivity,
    private val requestPermissionList: List<String>,
    private val permissionResult: PermissionResult
) {

    val grantedPermissionList: HashSet<String> = hashSetOf()
    val deniedPermissionList: HashSet<String> = hashSetOf()

    private var isNeedForExplanationMessage = false


    private fun getInvisibleFragment(): InvisibleFragment {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        return if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
    }

    fun requestShowExplanationMessage(): LiveDataPermissionBuilder {
        isNeedForExplanationMessage = true
        return this
    }

    fun build() {
        getInvisibleFragment().request(this, requestPermissionList, object : PermissionCallback {

            override fun showExplanation() {
                if (isNeedForExplanationMessage) {
                    permissionResult.setShowExplanationMessageEvent(event = Event(Unit))
                }
            }

            override fun getPermission() {
                permissionResult.setGrantedPermissionList(Event(grantedPermissionList.toList()))
                permissionResult.setDeniedPermissionList(Event(deniedPermissionList.toList()))
            }

            override fun setAllGrantedFlag(flag: Boolean) {
                permissionResult.setAllGranted(Event(flag))
            }
        })

    }

    companion object {
        private const val TAG = "InvisibleFragment"
    }
}