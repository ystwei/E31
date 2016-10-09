package com.weikun.C;

/**
 * Created by Administrator on 2016/10/9.
 * '
 * 邻接矩阵的普利姆算法--最小生成树
 */
public class E {
    private static final int INF = Integer.MAX_VALUE;
    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵

    /**
     *
     * @param start:从哪个顶点开始
     */
    public void prim(int start) {
        int count=this.mVexs.length;
        char [] prim=new char[count];//结果顶点数组
        int [] weight=new int[count];//每条边的权值数组
        int index=0;//prim数组中的索引号

        prim[index++]=this.mVexs[start];//走的start所指的那个顶点

        for(int i=0;i<count;i++){//初始化权值数组，根据start行，找到所有的列的权值，挨个赋值
            weight[i]=mMatrix[start][i];
        }
        //当前行下，哪个列权值最小
        for(int i=0;i<count;i++){
            if(start==i){//自己不能访问自己 A《》 A
                continue;

            }
            int j=0;//种子
            int k=0;//最小权值索引
            int min=INF;//最小权值


            while(j<count){

                if( weight[j]!=0 &&weight[j]<min){//j不是自身，并且，有比他小的权值
                    min=weight[j];
                    k=j;
                }

                j++;
            }
            //已经找到了最小值
            prim[index++]=this.mVexs[k];//把找到的最小数组送入结果集

            weight[k]=0;//不用在一次访问该索引指的权值了，已经访问过了

            for(j=0;j<count;j++){
                if(weight[j]!=0&&this.mMatrix[k][j]<weight[j]){
                    weight[j]=this.mMatrix[k][j];
                }
            }
        }
        // 计算最小生成树的权值
        int sum=0;
        for(int i=1;i<index;i++){//1:结尾那个点 B
            int n=this.getPosition(prim[i]);//从结果集中查询,找到在顶点的索引
            int min=INF;
            for (int j=0;j<i;j++){//A
                int m=this.getPosition(prim[j]);//
                if(this.mMatrix[m][n]<min){
                    min=this.mMatrix[m][n];
                }
            }
            sum+=min;
        }
        System.out.print(sum);
        System.out.printf("PRIM权值和(%c)=%d: ", mVexs[start], sum);
        for (int i = 0; i < index; i++){
            System.out.printf("%c ", prim[i]);
        }
        System.out.printf("\n");

    }
    //计算ch在顶点集合的索引号
    private int getPosition(char ch) {
        for(int i=0;i<this.mVexs.length;i++){
            if(ch==this.mVexs[i]){
                return i;

            }

        }
        return -1;

    }

    public E(char[] tops, int[][] matrix1) {
        int ven=tops.length;
        this.mVexs=tops;//把顶点集合付过来
        mMatrix=new int[ven][ven];
        //初始化边
        for(int i=0;i<ven;i++){

            for(int j=0;j<ven;j++){
                this.mMatrix[i][j]=matrix1[i][j];

            }
        }


    }

    public static void main(String[] args) {
        //顶点
        char[] tops = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};


        int matrix1[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
         /*A*/ {   0,  12, INF, INF, INF,  16,  14},
         /*B*/ {  12,   0,  10, INF, INF,   7, INF},
         /*C*/ { INF,  10,   0,   3,   5,   6, INF},
         /*D*/ { INF, INF,   3,   0,   4, INF, INF},
         /*E*/ { INF, INF,   5,   4,   0,   2,   8},
         /*F*/ {  16,   7,   6, INF,   2,   0,   9},
         /*G*/ {  14, INF, INF, INF,   8,   9,   0}
        };

        E e=new E(tops,matrix1);
        e.prim(0);
    }


}
