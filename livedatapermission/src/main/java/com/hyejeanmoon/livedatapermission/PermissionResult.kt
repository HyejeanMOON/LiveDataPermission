package com.hyejeanmoon.livedatapermission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PermissionResult {

    private val _grantedPermissionList: MutableLiveData<Event<List<String>>> = MutableLiveData()
    val grantedPermissionList: LiveData<Event<List<String>>> get() = _grantedPermissionList

    private val _deniedPermissionList: MutableLiveData<Event<List<String>>> = MutableLiveData()
    val deniedPermissionList: LiveData<Event<List<String>>> get() = _deniedPermissionList

    private val _showExplanationMessageEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val showExplanationMessageEvent: LiveData<Event<Unit>> get() = _showExplanationMessageEvent

    fun setGrantedPermissionList(list: Event<List<String>>) {
        _grantedPermissionList.value = list
    }

    fun setDeniedPermissionList(list: Event<List<String>>) {
        _deniedPermissionList.value = list
    }

    fun setShowExplanationMessageEvent(event: Event<Unit>) {
        _showExplanationMessageEvent.value = event
    }

}