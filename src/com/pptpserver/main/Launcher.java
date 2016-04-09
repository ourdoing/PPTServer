package com.pptpserver.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pptpserver.controller.Handler;
import com.pptpserver.listener.ConnectListener;
import com.pptpserver.pack.Pack;

public class Launcher {

	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();  

		ConnectListener CL = new ConnectListener();

		while (true) {
			Pack pack = CL.listen();
			if (pack != null) {
				cachedThreadPool.execute(new Thread(new Handler(pack)));
			}

		}

	}

}
