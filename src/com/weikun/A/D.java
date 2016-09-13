package com.weikun.A;

/**
 * Created by Administrator on 2016/9/13.
 */
//顺序队列
public class D {

    //默认长度
    private int DEFAULT_SIZE = 10;
    //保存数组的长度。
    private int capacity;
    //定义一个数组用于保存顺序队列的元素
    private Object[] elementData;
    //保存顺序队列中元素的当前个数
    private int front = 0;//出
    private int rear = 0;//进
    //以默认数组长度创建空顺序队列
    public D(){
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }
    public void add(Object obj){
        if(rear>this.capacity-1){
            System.out.println("已经满了");
        }
        elementData[rear++]=obj;
    }
    public boolean empty(){
        return this.front==this.rear;
    }
    public Object remove(){
        if(this.empty()){
            System.out.println("不能删了！");
        }
        Object o=elementData[front];
        elementData[front++]=null;
        return o;
    }

    public static void main(String[] args) {
        D d=new D();
        d.add("A");
        d.add("B");
        d.add("C");
        System.out.print(d.remove());
        System.out.print(d.remove());
    }

}
