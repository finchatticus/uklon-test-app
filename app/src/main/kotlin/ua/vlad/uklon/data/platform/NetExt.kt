package ua.vlad.uklon.data.platform

fun <T: Any> checkInternetConnection(onEnabled: () -> T, onDisabled: () -> T): T {
    return when (NetConnectionHandler.isConnected()) {
        true -> onEnabled.invoke()
        false -> onDisabled.invoke()
    }
}