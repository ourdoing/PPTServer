package com.pptpserver.controller;

import com.becivells.db.DB;
import com.pptpserver.pack.Pack;
import com.pptpserver.sender.MsgSender;

public class MsgJump {
	Pack pack;

	public MsgJump(Pack _pack) {
		this.pack = _pack;
	}

	public void jump() {
		switch (pack.getControlMsg()) {
		case "ready":
			System.out.print("ready-");
			System.out.println(pack.getFromIP().getHostAddress());
			
			//保存MAC-IP对应关系
			DB.addMacToIpMap(pack.getMAC(), pack.getFromIP());
						
			new MsgSender(pack).sendBack("ready");//原路返回
			
			break;
			
		case "pre":
			System.out.print("pre-");
			System.out.println(pack.getFromIP().getHostAddress());
			
			//添加IP对应关系
			DB.addIpToIpMap(pack.getMAC(), pack.getFromIP());
			
			new MsgSender(pack).sendTo("pre");//发送到目标IP
			
			break;
			
		case "next":
			System.out.print("next-");
			System.out.println(pack.getFromIP().getHostAddress());
			
			//添加IP对应关系
			DB.addIpToIpMap(pack.getMAC(), pack.getFromIP());
			
			new MsgSender(pack).sendTo("next");//发送到目标IP
			
			break;

		default:
			System.out.print("def-");
			System.out.println(pack.getDs().getInetAddress());
			break;
		}
		

	}

}
