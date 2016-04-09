package com.becivells.db;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class DB {
	private static Map<String, InetAddress> macToIpMap = new HashMap<String, InetAddress>();
	private static Map<InetAddress, InetAddress> ipToIpMap = new HashMap<InetAddress, InetAddress>();

	public static void addMacToIpMap(String mac, InetAddress inetAddress) {
		macToIpMap.put(mac, inetAddress);
	}

	public static void addIpToIpMap(String mac, InetAddress fromIP) {
		// 得到目的地址的IP
		InetAddress toIp = macToIpMap.get(mac);

		// 把目的IP和源IP绑定
		if (toIp != null) {//如果目的MAC已上线
			if (ipToIpMap.get(fromIP) == null) {//如果目的IP的对应关系还没建立
				ipToIpMap.put(fromIP, toIp);
				ipToIpMap.put(toIp, fromIP);
			}
		}
	}

	public static InetAddress ipGetSendtoWhere(InetAddress fromIP) {
		return ipToIpMap.get(fromIP);
	}

}
