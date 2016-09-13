package com.weikun.A;

/**
 * Created by Administrator on 2016/9/13.
 */
//自己栈
public class B implements  MyStack{

    public String[] os=new String[2];
    public int size;
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void clear() {
        for(int i=0;i<os.length;i++){

            os[i]=null;

        }
    }

    @Override
    public int length() {
        return os.length;
    }

    /**
     * 扩容，用数组扩
     */
    public void resize(){

        String [] temp=new String[os.length*3];
        for(int i=0;i<os.length;i++){
            temp[i]=os[i];
            os[i]=null;

        }
        os=temp;
    }

    public static void main(String[] args) {


        B b=new B();
        b.push("A");
        b.push("B");
        b.push("C");

        System.out.println(b.pop());
        System.out.println(b.pop());
        System.out.println(b.pop());


    }
    @Override
    public boolean push(String data) {
        if( size>= os.length){
            resize();
        }
        os[size++]=data;
        return true;
    }

    @Override
    public String pop() {
        if(isEmpty()){
            System.out.print("空了！");
            return null;
        }
        String t=os[size-1];
        os[--size]=null;
        return t;
    }

    @Override
    public String peek() {
        return null;
    }
}
interface MyStack {
    /**
     * 判断栈是否为空
     */
    boolean isEmpty();
    /**
     * 清空栈
     */
    void clear();
    /**
     * 栈的长度
     */
    int length();
    /**
     * 数据入栈
     */
    boolean push(String data);
    /**
     * 数据出栈
     */
    //返回栈顶元素，删除栈顶元素
    String pop();
    //返回栈顶元素，但不删除栈顶元素
    String peek();
}