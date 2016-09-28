package com.weikun.C;

/**
 * Created by Administrator on 2016/9/28.
 * 无向图的邻接矩阵表示法
 */
public class A {


    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D'};
        //
        //		//有向图是
//		char[][] edges = new char[][]{//边是个二维数组
//				{'A', 'D'},
//				{'B','A'},
//				{'C', 'A'},
//				{'C', 'B'},
//				{'B', 'C'}
//
//		};
        //无向图是,无向图和有向图代码一致，只不过无向图AB两个顶点，边表示是A->B B->A
        char[][] edges = new char[][]{//边是个二维数组
                {'A', 'B'},{'B','A'},
                {'B', 'C'}, {'C', 'B'},
                {'C', 'D'},{'D', 'C'},
                {'A', 'D'},{'D','A'},
                {'A', 'C'},{'C', 'A'}


        };


        //
        // 采用已有的"图"
        A pG = new A(vexs, edges);

        pG.print();   // 打印图
    }
    private char[] vexs;
    private int[][] mMatrix;    // 邻接矩阵

    /**
     *
     * @param ch:顶点值
     * @return：顶点在顶点集合的索引号
     */
    private int getPosition(char ch) {
        for (int i=0;i<this.vexs.length;i++){
            if(ch==this.vexs[i]){
                return  i;
            }

        }
        return -1;

    }
    public A(  char[] vexs,  char[][] edges){
        this.vexs=vexs;
        int len= vexs.length;
        this.mMatrix=new int[len][len];

        for( int i=0;i<edges.length;i++){

            int p1=this.getPosition(edges[i][0]);
            int p2=this.getPosition(edges[i][1]);

            this.mMatrix[p1][p2]=1;
        }


    }
    public void print() {

        for(int i=0;i<this.vexs.length;i++){
            for(int j=0;j<this.vexs.length;j++){
                System.out.printf("%d ",this.mMatrix[i][j]);
            }
            System.out.println();


        }
    }
}
