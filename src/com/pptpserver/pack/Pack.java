package com.pptpserver.pack;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Pack {
	String msg;
	DatagramSocket ds;
	DatagramPacket dp;

	String MAC;
	String controlMsg="default";
	InetAddress fromIP;
	int fromPort;

	public Pack(DatagramSocket ds, DatagramPacket dp, String msg) {
		this.setDs(ds);
		this.setMsg(msg);
		this.split();

		this.fromIP = dp.getAddress();
		this.fromPort = dp.getPort();
	}

	private void split() {
		String[] split = msg.split(":");
		this.MAC = split[0].trim();
		if (split.length > 1) {
			this.controlMsg = split[1].trim();
		}
	}

	public String getMAC() {
		return MAC;
	}

	public String getControlMsg() {
		return controlMsg;
	}

	String getMsg() {
		return msg;
	}

	void setMsg(String msg) {
		this.msg = msg;
	}

	public DatagramSocket getDs() {
		return ds;
	}

	void setDs(DatagramSocket ds) {
		this.ds = ds;
	}

	public InetAddress getFromIP() {
		return fromIP;
	}

	public int getFromPort() {
		return fromPort;
	}

}
