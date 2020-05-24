package ua.vlad.uklon.data.platform

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.lang.ref.WeakReference

object NetConnectionHandler {

    private var contextWeakReference: WeakReference<Context>? = null

    private val context: Context?
        get() = contextWeakReference?.get() ?: throw IllegalStateException("Context cannot be null")

    private val connectivityManager: ConnectivityManager?
        get() = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    private val networkInfo: NetworkInfo?
        get() = connectivityManager?.activeNetworkInfo

    fun initialize(context: Context) {
        contextWeakReference = WeakReference(context.applicationContext)
    }

    fun isConnected(): Boolean {
        return networkInfo?.isConnected ?: false
    }

}