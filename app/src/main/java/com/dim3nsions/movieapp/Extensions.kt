package com.dim3nsions.movieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes resource: Int) = LayoutInflater.from(context).inflate(resource,this,false)
