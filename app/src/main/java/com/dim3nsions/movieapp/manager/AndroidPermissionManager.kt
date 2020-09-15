package com.dim3nsions.movieapp.manager

import android.app.Activity
import androidx.core.app.ActivityCompat

interface AndroidPermissionManager {

    fun requestPermission(activity: Activity, permissions: Array<String>)

    companion object {
        val instance: AndroidPermissionManager by lazy {
            AndroidPermissionManagerImp()
        }
    }
}

class AndroidPermissionManagerImp : AndroidPermissionManager {

    override fun requestPermission(activity: Activity, permissions: Array<String>) {
        ActivityCompat.requestPermissions(activity, permissions, 1200)
    }
}

