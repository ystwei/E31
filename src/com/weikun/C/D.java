package com.weikun.C;

/**
 * Created by Administrator on 2016/9/29.
 * 深度优先遍历
 * 广度优先遍历
 */
public class D {
    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵

    public D(char[] vexs,char[][] edges){
        this.mVexs=vexs;
        int elen=edges.length;
        mMatrix=new int[elen][elen];//开堆
        for(int i=0;i<elen;i++){

            int p1=this.getPosition(edges[i][0]);//左节点 A
            int p2=this.getPosition(edges[i][1]);//右节点 B
            mMatrix[p1][p2]=1;//代表有边
        }

    }
    public int getPosition(char ch){

        for(int i=0;i<this.mVexs.length;i++){
            if(this.mVexs[i]==ch){

                return i;
            }
        }
        return -1;

    }
    /*
	 * 打印矩阵队列图
	 */
    public void print() {
        System.out.printf("邻接矩阵图的遍历:\n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++){
                System.out.printf("%d ", mMatrix[i][j]);
            }
            System.out.printf("\n");
        }
    }
    /*
    * 深度优先搜索遍历图
    */
    public void DFS(){
        boolean [] visited=new boolean[this.mVexs.length];

        //看是否曾经被访问了，
        for(int i=0;i<this.mVexs.length;i++){
            if(visited[i]==false){
                DFS(i,visited);

            }

        }





    }

    /**
     * 找到和v顶点相连的另一个顶点的索引
     * @param v：当前顶点索引
     * @return：另个顶点在mVexs的索引号
     */
    private int firstVertex(int v) {
        for(int i=0;i<this.mVexs.length;i++){
            if(this.mMatrix[v][i]==1){//代表连接
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param v：当前顶点
     * @param w  下一个顶点
     * @return：下下没被访问的顶点
     */
    private int nextVertex(int v, int w) {
        for(int i=w+1;i<this.mVexs.length;i++){
           if(this.mMatrix[v][i]==1){//有连接
                return i;
            }

        }
        return -1;
    }
    public void BFS(){
        int head=0;
        int rear=0;
        boolean [] visited=new boolean[this.mVexs.length];
        int [] queue=new int[this.mVexs.length];
        for(int i=0;i<this.mVexs.length;i++){
            if(visited[i]==false){//没被访问，然他被访问，之后打印
                visited[i]=true;
                System.out.printf("%s",this.mVexs[i]);
                queue[rear++]=i;
            }
            while(head!=rear){//队列里面有否值

                int k=queue[head++];//第一个顶点
                int l=this.firstVertex(k);//找到下一个顶点
                while(l>=0){//要看下所有点
                    if(visited[l]==false){
                        visited[l]=true;
                        System.out.printf("%s",this.mVexs[l]);
                        queue[rear++]=l;//
                    }
                    l=this.nextVertex(k,l);

                }


            }

        }

    }

        /**
         * 核心dfs
         * @param i：顶点的索引号
         * @param visited：被访问过的集合
         */
    private void DFS(int i, boolean[] visited) {
        visited[i]=true;//被访问了
        System.out.printf("%s",this.mVexs[i]);
        int w=this.firstVertex(i);//下一个顶点索引
        while(w>=0){//=0代表包括所有顶点
           if(visited[w]==false){//下一个顶点没有被访问
                DFS(w,visited);
            }
            //已经被访问了
            w=this.nextVertex(i ,w);//返回当前顶点下一个没被访问的顶点的索引//
        }////
    }


    public static void main(String[] args) {
        /*
		 *        图形见PPT
		 */
        char[] vexs = {'A', 'B', 'C', 'D','E','F','G','H','I'};
        char[][] edges = new char[][]{//边是个二维数组
                {'A', 'B'},
                {'A', 'F'},
                {'B', 'G'},
                {'B', 'C'},
                {'B', 'I'},
                {'C', 'B'},
                {'C', 'I'},
                {'C', 'D'},
                {'D', 'C'},
                {'D', 'I'},
                {'D', 'G'},
                {'D', 'H'},
                {'D', 'E'},
                {'E', 'H'},
                {'E', 'F'},
                {'F', 'G'},
                {'F', 'A'},
                {'F', 'E'},
                {'G', 'H'},
                {'G', 'D'},
                {'G', 'B'},
                {'H', 'G'},
                {'H', 'D'},
                {'H', 'E'},
                {'I', 'B'},
                {'I', 'C'},
                {'I', 'D'}

        };
        D d=new D(vexs,edges);
        d.print();
        d.DFS();
        System.out.println();
        d.BFS();
    }

}
