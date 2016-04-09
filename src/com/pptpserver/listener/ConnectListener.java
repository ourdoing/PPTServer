package com.pptpserver.listener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.pptpserver.pack.Pack;

public class ConnectListener {

	byte[] buf;
	DatagramSocket ds;
	DatagramPacket dp;

	public ConnectListener() {
		try {
			this.ds = new DatagramSocket(11751);
			this.ds.setSoTimeout(100);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public Pack listen() {

		try {
			buf = new byte[1024];

			dp = new DatagramPacket(buf, buf.length);

			synchronized (ds) {
				ds.receive(dp);
			}
			return new Pack(ds, dp, new String(buf));
			
		} catch (java.net.SocketTimeoutException e) {
			try {
				Thread.sleep(100);
				//System.err.println("未监听到请求");
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
			
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
