package com.ug.route.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.navigation.NavController

fun isInternetConnected(context: Context): Boolean {

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)
    return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}


fun handelInternetError(context: Context, successScenario : () -> Unit, failScenario : () -> Unit){

    if (isInternetConnected(context)) successScenario()
    else failScenario()
}