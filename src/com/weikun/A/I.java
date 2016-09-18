package com.weikun.A;

/**
 * Created by Administrator on 2016/9/18.
 * 双向链表
 */
public class I {
    public static void main(String[] args){
        DLinkList l=new DLinkList();
        l.addHeader("A");
        l.addHeader("B");
        l.addHeader("C");
        l.del(2);
      //  l.insert("D",0);

        System.out.println(l);
    }
}
class DLinkList {
    public class Node {
        //保存节点的数据
        public String data;
        //指向下个节点的引用
        private Node next;

        //指向上个节点的引用
        private Node prev;
        //初始化全部属性的构造器
        public Node(String data,Node prev, Node next) {
            this.data = data;
            this.prev=prev;
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
    public DLinkList() {
        // TODO Auto-generated constructor stub
        header = null;
        tail = null;
    }

    //返回链表的长度
    public int length() {
        return size;
    }

    public void addTail(String element) {

        if (header == null) {//还没有节点
            Node newnode = new Node(element, null,null);
            header = newnode;
            tail = header;
        } else {//原始有节点
            Node newnode = new Node(element, tail,null);

            tail.next = newnode;
            tail = newnode;
        }

        size++;
    }


    public void addHeader(String element) {

        if (tail == null) {//还没有节点
            Node newnode = new Node(element, null,null);
            tail = newnode;
            header = tail;
        } else {
            Node newnode = new Node(element, null,header);
            header.prev=newnode;
            header = newnode;
        }

        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node current = header; current != null; current = current.next) {
            sb.append(current.data.toString() + ", ");
        }

        return sb.toString();
    }

    public Node getNodeByIndex(int index) {//根据索引号找到所指定的节点

        if(index<size/2){//从前往后
            Node current = header;
            for (int i = 0; i < size; i++) {
                if (current == null) {
                    break;
                }
                if (i == index) {//找到了当前节点

                    return current;
                }
                current = current.next;
            }
        }else{//后往前
            Node current = tail;
            for (int i = size-1; i >=0; i--) {
                if (current == null) {
                    break;
                }
                if (i == index) {//找到了当前节点

                    return current;
                }
                current = current.prev;
            }
        }


        return null;
    }

    public void insert(String element, int index) {
        if (index < 0 || index > size) {
            System.out.println("越界了");
            return;
        }
        if (header == null) {//
            this.addTail(element);//从尾部插入

        } else {//里面有元素

            if (index == 0) {
                this.addHeader(element);

            } else {//不是重头插入
                Node pnode = this.getNodeByIndex(index - 1);//找到当前index指定的前一节点
                Node n=new Node(element,pnode ,pnode.next);
                pnode.next.prev=n;
                pnode.next = n;

            }
        }
        size++;
    }

    public String del(int index) {
        Node del = null;
        if (index == 0) {//要删除首结点
            del = header;
            header = del.next;//指向新头
        } else {

            Node prev = this.getNodeByIndex(index - 1);//index:2
            del = prev.next;
            prev.next = del.next;
            if(del.next!=null){//后面还有元素，
                del.next.prev=del.prev;
            }else{//后面没有元素了，前一个就是队尾
                prev.next=null;
            }
        }
        size--;
        return del.data;
    }
}
