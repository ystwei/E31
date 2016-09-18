package com.weikun.A;

/**
 * Created by Administrator on 2016/9/18.
 * 单向链表
 */
public class H {
    public static void main(String[] args) {
        LinkList l=new LinkList();
        l.addHeader("A");
        l.addHeader("B");
        l.addHeader("C");
        l.del(2);
        l.insert("D",0);

        System.out.println(l);



    }
}
class LinkList {
    public class Node {
        //保存节点的数据
        public String data;
        //指向下个节点的引用
        private Node next;

        //初始化全部属性的构造器
        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    //保存该链表的头节点
    private Node header;
    //保存该链表的尾节点
    private Node tail;
    //保存该链表中已包含的节点数
    private int size;

    //创建空链表
    public LinkList() {
        // TODO Auto-generated constructor stub
        header = null;
        tail = null;
    }
    //返回链表的长度
    public int length() {
        return size;
    }
    public void addTail(String element){
        Node newnode=new Node(element,null);
        if(header==null){//还没有节点
            header=newnode;
            tail=header;
        }else{
            tail.next=newnode;
            tail=newnode;

        }

        size++;
    }


    public void addHeader(String element){
        Node newnode=new Node(element,null);
        if(tail==null){//还没有节点
            tail=newnode;
            header=tail;
        }else{
           newnode.next=header;
           header=newnode;
        }

        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node current = header ; current != null; current = current.next ){
            sb.append(current.data.toString() + ", ");
        }

        return sb.toString();
    }

    public Node getNodeByIndex(int index){//根据索引号找到所指定的节点
        Node current=header;
        for(int i=0;i<size;i++){
            if(current==null){
                break;
            }
            if(i==index){//找到了当前节点

                return current;
            }
            current=current.next;
        }
        return null;
    }
    public void insert(String element,int index){
        if(index<0||index>size){
            System.out.println("越界了");
            return ;
        }
        if(header==null){//
            this.addTail(element);//从尾部插入

        }else{//里面有元素

            if(index==0){
                this.addHeader(element);

            }else{//不是重头插入
                Node pnode=this.getNodeByIndex(index-1);//找到当前index指定的前一节点
                pnode.next=new Node(element,pnode.next);
            }
        }
        size++;
    }
    public String del(int index){
        Node del=null;
        if(index==0){//要删除首结点

            del=header;
            header=del.next;//指向新头
        }else{

            Node prev=this.getNodeByIndex(index-1);
            del=prev.next;
            prev.next=del.next;

        }
        size--;
        return del.data;
    }
}