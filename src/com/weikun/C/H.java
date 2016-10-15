package com.weikun.C;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Administrator on 2016/10/15.
 *  Java: 无回路有向图(Directed Acyclic Graph)的拓扑排序
 *       该DAG图是通过邻接表实现的。   *
 */
public class H {
    // 邻接表中表对应的链表的顶点
    private class ENode {
        int itop;       // 该边所指向的顶点的位置
        ENode next; // 指向下一条弧的指针
    }

    // 邻接表中表的顶点
    private class VNode {
        char data;          // 顶点信息
        ENode firstEdge;    // 指向第一条依附该顶点的弧
    };

    private List<VNode> mVexs;  // 顶点数组


    /**
     *
     * @param ch :顶点值
     * @return：该顶点值在顶点数组中的索引位置
     */
    private int getPosition(char ch) {
        for(int i=0;i<mVexs.size();i++){
            if(ch==mVexs.get(i).data){
                return i;

            }

        }
        return -1;

    }

    /**
     *找到最后一个有节点的那个域
     * @param first:邻接表中的那个firsetEdge域
     * @param node：新的节点
     *
     */
    private void linkLast(ENode first, ENode node) {
        ENode f=first;
        while(f.next!=null){
            f=f.next;
        }
        f.next=node;

    }
    public H(char[] vexs, char[][] edges) {

        int vLen= vexs.length;
        int eLen= edges.length;
        mVexs=new ArrayList<VNode>();
        //初始化顶点数组
        for(int i=0;i<vLen;i++){
            VNode v=new VNode();
            v.data=vexs[i];
            v.firstEdge=null;
            mVexs.add(v);
        }
        //初始化边
        for(int i=0;i<eLen;i++){

            char c1=edges[i][0];//A
            char c2=edges[i][1];//G

            int p1=this.getPosition(c1);//该顶点在顶点数组的位置索引

            int p2=this.getPosition(c2);//该顶点在顶点数组的位置索引
            //初始化ENode

            ENode enode=new ENode();
            enode.itop=p2;//终点
            if(this.mVexs.get(p1).firstEdge==null){//还没有值

                this.mVexs.get(p1).firstEdge=enode;

            }else{//已经firstEdge有值了，串到最后一个链接点
                this.linkLast(this.mVexs.get(p1).firstEdge,enode);

            }

        }





    }
    /*
     * 打印矩阵队列图
     */
    public void print() {
        System.out.printf("== List Graph:\n");
        for (int i = 0; i < mVexs.size(); i++) {
            System.out.printf("%d(%c): ", i, mVexs.get(i).data);
            ENode node = mVexs.get(i).firstEdge;
            while (node != null) {
                System.out.printf("%d(%c) ", node.itop, mVexs.get(node.itop).data);
                node = node.next;
            }
            System.out.printf("\n");
        }
    }

    /**
     * * 返回值：
     *     -1 -- 失败(由于内存不足等原因导致)
     *      0 -- 成功排序，并输入结果
     *      1 -- 失败(该有向图是有环的)
     * @return
     */
    public int topologicalSort() {
        int count=this.mVexs.size();
        Queue<Integer> queue = new LinkedList<Integer>();// 辅助队列
        //入度数组
        int  in[]=new int[count];
        //结果数组
        char results[]=new char[count];


        //统计每个顶点的入度数
        for(int i=0;i<count;i++){

            ENode node=this.mVexs.get(i).firstEdge;
            while(node!=null){

                in[node.itop]++;

                node=node.next;
            }
        }

        //把所有入度为0的顶点进入结果数组。
        for(int i=0;i<count;i++){
            if(in[i]==0){
                queue.add(i);
            }

        }
        //拆边，判断入度是否为0，做top排序
        int index=0;
        while(!queue.isEmpty()){

            int q=queue.poll().intValue();//其实就是B索引
            results[index++]=this.mVexs.get(q).data;
            VNode vnode=this.mVexs.get(q);//B
            ENode enode= vnode.firstEdge;
            while(enode!=null){
                //拆边
                in[enode.itop]--;
                if(in[enode.itop]==0){//判断入度是否为0
                    queue.add(enode.itop);

                }
                enode=enode.next;
            }

        }

        if(index != count) {
            System.out.printf("图是个环！\n");
            return 1;
        }

        // 打印拓扑排序结果
        System.out.printf("== TopSort: ");
        for(int i = 0; i < count; i ++){
            System.out.printf("%c ", results[i]);
        }
        System.out.printf("\n");

        return 0;





    }

    public static void main(String[] args) {



        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};//顶点数组
        char[][] edges = new char[][]{//边数组，例如A->G B->A等
                {'A', 'G'},
                {'B', 'A'},
                {'B', 'D'},
                {'C', 'F'},
                {'C', 'G'},
                {'D', 'E'},
                {'D', 'F'}};
        H h=new H(vexs,edges);
        h.print();
        h.topologicalSort();
    }
}
