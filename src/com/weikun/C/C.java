package com.weikun.C;

/**
 * Created by Administrator on 2016/9/29.
 * //有向图的邻接表表示方法
 */
public class C {

    // 邻接表中表对应的链表的顶点
    private class ENode {
        int tp;       // 该边所指向的顶点的位置
        ENode nextEdge; // 指向下一条弧的指针
    }

    // 邻接表中表的顶点
    private class VNode {
        String data;          // 顶点信息
        ENode firstEdge;    // 指向第一条依附该顶点的弧
    };
    private VNode[] top;

    /**
     *
     * @param list:要连接的边节点
     * @param node：将被连接的边节点
     */
    private void linkLast(ENode list, ENode node) {
        ENode p=list;
        while(p.nextEdge!=null){
            p=p.nextEdge;
        }
        p.nextEdge=node;

    }
    /**
     *
     * @param vexs:顶点信息数组
     * @param edges：边信息数组
     */
    public C(String[] vexs, String[][] edges){

        //初始化top
        int vlen=vexs.length;
        top=new VNode[vlen];
        for(int i=0;i<vlen;i++){
            top[i]=new VNode();
            top[i].data=vexs[i];
            top[i].firstEdge=null;
        }
        int elen=edges.length;
        //初始化边数组
        for(int i=0;i<elen;i++){
            int p1=this.getPosition(edges[i][0]);
            int p2=this.getPosition(edges[i][1]);

            ENode ep2=new ENode();
            ep2.tp=p2;
            //p1连p2
            //判断firset域是否为空
            if(top[p1].firstEdge==null){
                top[p1].firstEdge=ep2;

            }else{//不为空，即必须找到链表最后一个节点，并且连上

                this.linkLast(top[p1].firstEdge,ep2);
            }

        }


    }

    public static void main(String[] args) {
        String[] vexs = {"V0", "V1", "V2", "V3"};
        String[][] edges = new String[][]{//无向图，全部表示节点之间的关系即可
                {"V0", "V3"},
                {"V1", "V0"},
                {"V1", "V2"},
                {"V2", "V0"},
                {"V2", "V1"}
        };
        C b=new C( vexs,edges );
        b.print();
    }

    /*
    * 打印矩阵队列图
    */
    public void print() {
        for( int i=0;i<top.length;i++){

            System.out.printf("%s(%d):",top[i].data,i);
            ENode node=top[i].firstEdge;
            while(node!=null){

                System.out.print(top[node.tp].data+" ");
                node=node.nextEdge;
            }
            System.out.println();

        }


    }

    /**
     *
     * @param ch:是代表每个边的顶点
     * @return在top的位置索引
     */
    private int getPosition(String ch) {

        for(int i=0;i<top.length;i++){
            if(top[i].data.equals(ch)){
                return i;
            }


        }
        return -1;
    }
}
