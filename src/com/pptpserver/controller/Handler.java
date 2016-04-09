package com.pptpserver.controller;

import com.pptpserver.pack.Pack;

public class Handler implements Runnable {
	Pack pack;

	public Handler(Pack _pack) {
		this.pack = _pack;
	}

	@Override
	public void run() {
		
		new MsgJump(pack).jump();
	

	}

}
