package com.weikun.C;

/**
 * Created by Administrator on 2016/10/9.
 */
public class F {
    private static int INF=Integer.MAX_VALUE;
    private int ecount=0;//有多少有效边，不包括0，也不包括INF
    private char[] tops;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵
    public F(char[] vexs, int[][] matrix) {
        this.tops=vexs;//初始化顶点
        //初始化边
        this.mMatrix=new int[this.tops.length][this.tops.length];
        for(int i=0;i<tops.length;i++){
            for(int j=0;j<tops.length;j++){
                this.mMatrix[i][j]=matrix[i][j];

            }

        }
        //有效边的个数
        for(int i=0;i<tops.length;i++){
            for(int j=i+1;j<tops.length;j++){

                if(this.mMatrix[i][j]!=INF){
                    ecount++;//有效边的个数
                }
            }
        }



    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
				       /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
				/*A*/ {   0,  12, INF, INF, INF,  16,  14},
				/*B*/ {  12,   0,  10, INF, INF,   7, INF},
				/*C*/ { INF,  10,   0,   3,   5,   6, INF},
				/*D*/ { INF, INF,   3,   0,   4, INF, INF},
				/*E*/ { INF, INF,   5,   4,   0,   2,   8},
				/*F*/ {  16,   7,   6, INF,   2,   0,   9},
				/*G*/ {  14, INF, INF, INF,   8,   9,   0}
        };


        F f = new F(vexs, matrix);
        f.kruskaer();

    }

    private void kruskaer() {
        int index=0;
        int tends[]=new int[this.tops.length];//保存最小生成树的终点集合
        EData []rets=new EData[this.ecount];//有效边的数组
        EData [] datas=this.getEdges();//有效边
        //按权值进行边对象排序
        this.sortEdges(datas,this.ecount);
        for(int i=0;i<this.ecount;i++){
            int m=this.getPosition(datas[i].start);//边集合中的顶点在顶点集合的位置
            int n=this.getPosition(datas[i].end);//边集合中的终点在顶点集合的位置

            int p=this.getEnd(tends,m);
            int q=this.getEnd(tends,n);
            if(p!=q){//不是同一个终点,就不是环路
                tends[p]=q;//m的终点就是n
                rets[index++]=datas[i];//真正无环路的有效边
            }
        }
        // 统计并打印"kruskal最小生成树"的信息
        int length = 0;
        for (int i = 0; i < index; i++){
            length += rets[i].weight;
        }
        System.out.printf("Kruskal=%d: ", length);
        for (int i = 0; i < index; i++){
            System.out.printf("(%c,%c) ", rets[i].start, rets[i].end);
        }
        System.out.printf("\n");

    }

    /*
	 * 获取i的终点，第一次选取的点，并没有终点，因为默认的元素都是0
	 */
    private int getEnd(int[] tends, int i) {
        while(tends[i]!=0){
            i=tends[i];

        }
        return i;

    }
    /**
     *
     * @param ch:顶点值
     * @return：返回该顶点在顶点集合的索引号
     */
    private int getPosition(char ch) {

        for(int i=0;i<this.tops.length;i++){
            if(this.tops[i]==ch){
                return i;
            }

        }
        return -1;
    }

    /**
     *按权值从小到大，进行排序
     *
     * @param edges:有效边数组
     * @param elen：有效边个数
     *
     */
    private void sortEdges(EData[] edges, int elen) {

        for(int i=0;i<elen;i++){
            for(int j=i+1;j<elen;j++){
                EData temp;
                if(edges[i].weight-edges[j].weight>0){
                    temp=edges[i];
                    edges[i]=edges[j];
                    edges[j]=temp;
                }

            }

        }
    }

    /**
     *
     * @return返回有效边的数组
     */
    private EData[] getEdges() {
        int index=0;
        EData [] datas=new EData[this.ecount];
        for(int i=0;i<this.tops.length;i++ ){
            for(int j=i+1;j<this.tops.length;j++){

                if(this.mMatrix[i][j]!=INF){
                    datas[index++]=new EData(this.tops[i] ,this.tops[j], this.mMatrix[i][j]);
                }
            }

        }
        return datas;
    }

    // 边的结构体
    private static class EData {
        char start; // 边的起点
        char end;   // 边的终点
        int weight; // 边的权重

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    };

}

