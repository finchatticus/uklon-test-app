package ua.vlad.uklon.data.platform

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

object NetConnectionHandler {

    private var isConnected = AtomicBoolean(false)

    private var contextWeakReference: WeakReference<Context>? = null

    private val context: Context?
        get() = contextWeakReference?.get() ?: throw IllegalStateException("Context cannot be null")

    private val connectivityManager: ConnectivityManager?
        get() = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    fun initialize(context: Context) {
        contextWeakReference = WeakReference(context.applicationContext)
        registerNetConnectionListener()
    }

    fun isConnected(): Boolean {
        return isConnected.get()
    }

    private fun registerNetConnectionListener() {
        connectivityManager?.registerNetworkCallback(NetworkRequest.Builder().build(), NetConnectionListener())
    }

    private class NetConnectionListener : ConnectivityManager.NetworkCallback() {

        private val enabledNetworkConnections = mutableSetOf<Int>()

        override fun onLost(network: Network) {
            super.onLost(network)
            enabledNetworkConnections.remove(network.hashCode())
            isConnected.set(enabledNetworkConnections.isNotEmpty())
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            enabledNetworkConnections.add(network.hashCode())
            isConnected.set(true)
        }

    }

}