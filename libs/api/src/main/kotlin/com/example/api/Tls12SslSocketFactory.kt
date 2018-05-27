package com.example.api

import okhttp3.TlsVersion
import java.io.IOException
import java.net.InetAddress
import java.net.Socket
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory

class Tls12SslSocketFactory internal constructor() : SSLSocketFactory() {
  companion object {
    private val PROTOCOL = TlsVersion.TLS_1_2.toString()
  }

  private val socketFactory: SSLSocketFactory = SSLSocketFactory.getDefault() as SSLSocketFactory

  override fun getDefaultCipherSuites(): Array<String> {
    return socketFactory.defaultCipherSuites
  }

  override fun getSupportedCipherSuites(): Array<String> {
    return socketFactory.supportedCipherSuites
  }

  @Throws(IOException::class)
  override fun createSocket(socket: Socket, host: String, port: Int, autoClose: Boolean): Socket {
    val sslSocket = socketFactory.createSocket(socket, host, port, autoClose) as SSLSocket
    sslSocket.enabledProtocols = arrayOf(PROTOCOL)

    return sslSocket
  }

  @Throws(IOException::class)
  override fun createSocket(host: String, port: Int): Socket {
    val sslSocket = socketFactory.createSocket(host, port) as SSLSocket
    sslSocket.enabledProtocols = arrayOf(PROTOCOL)

    return sslSocket
  }

  @Throws(IOException::class)
  override fun createSocket(host: String, port: Int, localHost: InetAddress, localPort: Int): Socket {
    val sslSocket = socketFactory.createSocket(host, port, localHost, localPort) as SSLSocket
    sslSocket.enabledProtocols = arrayOf(PROTOCOL)

    return sslSocket
  }

  @Throws(IOException::class)
  override fun createSocket(host: InetAddress, port: Int): Socket {
    val sslSocket = socketFactory.createSocket(host, port) as SSLSocket
    sslSocket.enabledProtocols = arrayOf(PROTOCOL)

    return sslSocket
  }

  @Throws(IOException::class)
  override fun createSocket(
    address: InetAddress,
    port: Int,
    localAddress: InetAddress,
    localPort: Int
  ): Socket {
    val sslSocket = socketFactory.createSocket(address, port, localAddress, localPort) as SSLSocket
    sslSocket.enabledProtocols = arrayOf(PROTOCOL)

    return sslSocket
  }
}
