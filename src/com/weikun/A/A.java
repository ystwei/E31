package com.weikun.A;

import java.util.Stack;

/**
 * Created by Administrator on 2016/9/13.
 */
//使用jDK栈
public class A {

    public static void main(String[] args) {
        Stack s=new Stack();
        s.push("A");
        s.push("B");
        s.push("C");
        s.push(100);

//        Enumeration es=s.elements();
//        while (es.hasMoreElements()){
//            System.out.print(es.nextElement());
//        }
        System.out.print(s.pop());
        System.out.print(s.pop());
    }
}
