package com.example.api;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.TlsVersion;

public class Tls12SslSocketFactory extends SSLSocketFactory {
	private final String PROTOCOL = TlsVersion.TLS_1_2.toString();
	private SSLSocketFactory socketFactory;

	Tls12SslSocketFactory() {
		socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	}

	@Override
	public String[] getDefaultCipherSuites() {
		return socketFactory.getDefaultCipherSuites();
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return socketFactory.getSupportedCipherSuites();
	}

	@Override
	public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
		SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket(socket, host, port, autoClose);
		sslSocket.setEnabledProtocols(new String[]{PROTOCOL});

		return sslSocket;
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException {
		SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket(host, port);
		sslSocket.setEnabledProtocols(new String[]{PROTOCOL});

		return sslSocket;
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
		SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket(host, port, localHost, localPort);
		sslSocket.setEnabledProtocols(new String[]{PROTOCOL});

		return sslSocket;
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket(host, port);
		sslSocket.setEnabledProtocols(new String[]{PROTOCOL});

		return sslSocket;
	}

	@Override
	public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
		SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket(address, port, localAddress, localPort);
		sslSocket.setEnabledProtocols(new String[]{PROTOCOL});

		return sslSocket;
	}
}
