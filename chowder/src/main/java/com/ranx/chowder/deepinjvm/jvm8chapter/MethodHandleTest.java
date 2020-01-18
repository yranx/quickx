package com.ranx.chowder.deepinjvm.jvm8chapter;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
* @Description: JSR-292 Method Handle基础用法演示
* @author ranx
* @date 2019年12月19日15:09:33
 */
public class MethodHandleTest {

	static class ClassA {
		public void println(String s) {
			System.out.println(s);
		}
	}
	
	public static void main(String[] args) throws Throwable {
		Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
		
		getPrintlnMH(obj).invokeExact("icyfenix");
	}
	
	public static MethodHandle getPrintlnMH(Object reveiver) throws Throwable {
		/**
		 * MethodType: 代表“方法类型”，包含了方法的返回值（methodType()的第一个参数）和
		 * 具体参数（methodType()第二个及以后的参数）
		 */
		MethodType mt = MethodType.methodType(void.class, String.class);
		
		/**
		 * lookup()方法来自于MethodHandles.lookup
		 * 本句 在指定类中查找符合给定的方法名称、方法类型，并且符合调用权限的方法句柄
		 */
		/**
		 * 因为这里调用的是一个虚方法，按照Java语言规则，方法第一个参数是隐式，
		 * 代表该方法的接受者，也即是this指向的对象，这个参数以前是放在参数列表中进行传递的，
		 * 而现在提供了bindTo（）方法来完成这件事情
		 */
		return MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
		
	}

}
