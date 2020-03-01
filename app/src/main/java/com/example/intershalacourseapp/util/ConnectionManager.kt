package com.example.intershalacourseapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class ConnectionManager {
    fun checkConnection(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activenetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            activenetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activenetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activenetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }


        return result


    }
}
