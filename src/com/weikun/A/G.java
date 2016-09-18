package com.weikun.A;

/**
 * Created by Administrator on 2016/9/18.
 * 双向队列：可以在前和后分别都可以增加和删除
 *
 */
public class G {
    public class Link {//链结点
        String data;     //数据域
        Link next; //后继指针，结点           链域
        Link previous; //前驱指针，结点       链域

        Link(String data) {
            this.data = data;
        }

        void displayLink() {
            System.out.println("数据是： " + data.toString());
        }
    }

    private Link head;     //首结点
    private Link rear;     //尾部指针
    public void displayList4Head(){//从头遍历
        Link l=head;
        while(l!=null){
            l.displayLink();
            l=l.next;
        }


    }


    public void displayList4Rear(){//从尾部遍历
        Link l=rear;
        while(l!=null){

            l.displayLink();
            l=l.previous;
        }


    }
    public boolean isEmpty() {//判断该队列是否为空
        return head == null;
    }

    public void insertFirst(String data) {

        Link newl = new Link(data);
        if (isEmpty()) {// 如果没有节点

            head = newl;//从头进入
            rear=head;
        } else {//有节点
            head.previous = newl;
            newl.next = head;//新节点的下一节点就是老队列的头
            head = newl;//现在队列的头就是新节点
        }



    }

    public void insertLast(String data) {

        Link newl = new Link(data);
        if (isEmpty()) {// 如果没有节点
            rear = newl;//从头进入
            head=rear;
        } else {//有节点
            rear.next = newl;
            newl.previous = rear;//
            rear = newl;//
        }
    }
    public String deleteHead() {//删除 链头
        if (isEmpty()) {
            return null;
        }
        Link temp = head;
        head = head.next;//取出老队列的头部的下一节点付给新队列的头
        if (head == null) {//只有一个要删除的节点
            rear = null;
        } else {//不只有一个要删除的节点
            head.previous = null;
        }
        return temp.data;
    }

    public String deleteRear() {//删除 链尾

        if (isEmpty()) {
            return null;
        }
        Link l = rear;
        rear = rear.previous;
        if (rear == null) {//只有这一个
            head = null;
        } else {
            rear.next = null;

        }

        return l.data;
    }

    public static void main(String[] args) {
        G g=new G();
        g.insertFirst("A");
        g.insertFirst("B");
        g.insertLast("C");

        g.insertLast("D");
        System.out.println("删除："+g.deleteHead());
        System.out.println("删除："+g.deleteRear());
        g.displayList4Head();
        g.displayList4Rear();
    }


}
