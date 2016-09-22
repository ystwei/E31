package com.weikun.A;

/**
 * Created by Administrator on 2016/9/22.
 * 约瑟夫环
 */
public class J {
    public static void main(String[] args) {
       new J().yuesefuKill();
    }
    public class Node{
        int data;//当前值
        Node next;//下一个地址
        Node(int data){//构造当前节点
            this.data = data;
        }
    }

    public void yuesefuKill(){
        //形成第一个节点
        int n=41;
        int m=3;
        Node first=new Node(0);
        first.next=first;//因为只有一个节点，让他首尾相连

        Node last=first;//作一个临时变量

        for(int i=1;i<n;i++){//生成真正的约瑟夫环0---40
            Node temp=new Node(i);
            last.next=temp;
            last=last.next;//节点一个一个接续
        }
        last.next=first;//首尾相连
        //跳圈过程
        while(last!=last.next){
            for(int i=1;i<m;i++){//仅遍历时要删除节点的前面节点
                last=last.next;
            }
            last.next=last.next.next;
        }
        System.out.print(last.data);
    }

}
