package com.weikun.A;

/**
 * Created by Administrator on 2016/9/18.
 * 链式的单向队列，从尾部插入，从头部删除
 * 其中每个节点都存储着下一个节点的地址，
 */
public class F {
    private class Node{
        //保存节点的数据
        private String data;
        //指向下个节点的引用
        private Node next;

        //初始化全部属性的构造器
        public Node(String data ,  Node next){
            this.data = data;
            this.next = next;
        }
    }
    //首结点
    public Node front;
    //尾戒点
    public Node rear;
    //一共有多少个节点
    public int size;


    public F(){
        front=new Node("Root",null);
        rear=front;//因为就是喔一个结点

        size++;

    }
    public void add(String element){
        if(front==null){//没节点

            front=new Node("Root",null);
            rear=front;//因为就是喔一个结点

            size++;
        }else{

            Node n=new Node(element,null);
            rear.next=n;//新节点的地址就是当前队列尾戒点的next节点
            rear=n;//现在的队列的尾戒点就是新节点的地址

            size++;
        }
    }
    public String remove(){
        Node oldNode=front;
        String t=oldNode.data;
        front=front.next;
        oldNode.next=null;
        oldNode=null;
        size--;
        return t;
    }

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer();
        for(Node current =front;current!=null;current=current.next){

            sb.append(current.data);

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        F f=new F();
        f.add("A");
        f.add("B");
        f.add("C");
        System.out.println(f);
        f.remove();
        System.out.println(f);


    }
}
