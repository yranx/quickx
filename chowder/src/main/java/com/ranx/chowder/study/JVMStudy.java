package com.ranx.chowder.study;

/**
 * @author ranx
 * @create 2018-10-03 21:57
 **/
public class JVMStudy {
//    public static void main(String[] args) {
//        byte[] b = null;
//        for (int i=0; i<10; i++) {
//            b = new byte[1 * 1024 * 1024];
//        }
//    }

    private static int count = 0;
    public static void recursion(long a, long b, long c) {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        recursion(a, b, c);
    }

    public static void main(String args[]) {
        try {
            recursion(0L, 0L, 0L);
        } catch (Exception e) {
            System.out.println("deep of calling = " + count);
            e.printStackTrace();
        }
    }
}
