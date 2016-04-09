package com.pptpserver.sender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.becivells.db.DB;
import com.pptpserver.pack.Pack;

public class MsgSender {
	Pack pack;
	DatagramSocket ds;

	public MsgSender(Pack pack) {

		this.pack = pack;
		this.ds = pack.getDs();

	}

	public void sendBack(String string) {

		byte[] sendbf = string.getBytes();
		try {
			synchronized (ds) {
				ds.send(new DatagramPacket(sendbf, sendbf.length, pack.getFromIP(), pack.getFromPort()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendTo(String string) {
		// 查找对应关系
		InetAddress sendTo = DB.ipGetSendtoWhere(pack.getFromIP());

		if (sendTo != null) {
			byte[] sendbf = string.getBytes();
			try {
				synchronized (ds) {
					ds.send(new DatagramPacket(sendbf, sendbf.length, sendTo, pack.getFromPort()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
