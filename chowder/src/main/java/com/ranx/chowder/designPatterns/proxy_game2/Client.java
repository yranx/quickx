package com.ranx.chowder.designPatterns.proxy_game2;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

/**
 * @Description: 场景类
 * @author ranx
 * @date 2019-3-21-下午6:10:02

 */
public class Client {
	
	public static void main(String[] args) {
		IGamePlayer player = new GamePlayer("张三");
		IGamePlayer proxy = new GamePlayerProxy(player);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("开始时间：" + sdf.format(System.currentTimeMillis()));
		proxy.login("ranx", "password");
		proxy.killBoss();
		proxy.upgreade();
		System.out.println("结束时间：" + sdf.format(System.currentTimeMillis()));
	}

}
