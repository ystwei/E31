package com.weikun.A;

/**
 * Created by Administrator on 2016/9/13.
 */
//循环队列
public class E {

    //默认长度
    private int DEFAULT_SIZE = 3;
    //保存数组的长度。
    private int capacity;
    //定义一个数组用于保存顺序队列的元素
    private Object[] elementData;
    //保存顺序队列中元素的当前个数
    private int front = 0;//出
    private int rear = 0;//进
    //以默认数组长度创建空顺序队列
    public E(){
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }
    public void add(Object obj){

        elementData[rear++]=obj;

        rear=rear==this.capacity?0:rear;

    }
    public boolean empty(){
        return this.front==this.rear;
    }
    public Object remove(){

        Object o=elementData[front];
        elementData[front++]=null;
        front=front==this.capacity?0:front;
        return o;
    }

    public static void main(String[] args) {
        E d=new E();
        d.add("A");
        d.add("B");
        d.add("C");
        d.add("D");
        d.add("E");
        System.out.print(d.remove());
        System.out.print(d.remove());
        System.out.print(d.remove());

    }

}
