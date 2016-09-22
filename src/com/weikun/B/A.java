package com.weikun.B;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 * 父亲表示法构造树
 */
public class A {
    public static void main(String [] args){

        TreeParent tp = new TreeParent("root");//第一个节点
        TreeParent.Node root = tp.root();
        System.out.println(root);
        tp.addNode("节点1" , root);
        System.out.println("此树的深度:" + tp.deep());
        System.out.println("----------------");

        tp.addNode("节点2" , root);
        //获取根节点的所有子节点
        List<TreeParent.Node> nodes = tp.getChildren(root);
        System.out.println("根节点的第一个子节点：" + nodes.get(0));

        System.out.println("根节点的第二个子节点：" + nodes.get(1));


        tp.addNode("节点3" , nodes.get(0));

        List<TreeParent.Node> nodes1 = tp.getChildren(nodes.get(0));
        System.out.println("根节点的第三个子节点：" + nodes1.get(0));
        System.out.println("此树的深度:" + tp.deep());

    }
}
class TreeParent{
    public static class Node{
        //节点数据
        String data;
        //记录其父节点的位置
        int parent;

        public Node(String data , int parent){
            this.data = data;
            this.parent = parent;
        }
        public String toString(){
            return "父节点表示法[data=" + data + ", parent="+ parent + "]";
        }
    }
    //树节点的数量
    private int treeSize =100;
    //使用一个Node[]数组来记录该树里的所有节点
    private Node [] nodes;
    //记录节点数
    private int nodeNums;
    public TreeParent(String data){
        nodes=new Node[treeSize];
        nodes[0]=new Node(data,-1);//首个根节点
        nodeNums++;

    }
    //返回根节点
    public Node root(){
        //返回根节点
        return nodes[0];
    }
    //找到其父节点的索引号
    public int pos(Node node){

        for(int i=0;i<this.nodeNums;i++){
            if(nodes[i]==node){

                return i;
            }
        }
        return -1;
    }
    public void addNode(String data,Node parent){

        for(int i=0;i<this.treeSize;i++){

            if(nodes[i]==null){//数组对象中一旦找到空位置，就可以添加新元素了

                Node temp=new Node(data,pos(parent));
                nodes[i]=temp;
                this.nodeNums++;
                return ;
            }
        }
    }
    public List<Node> getChildren(Node parent) {//铜鼓欧父亲找到其所有孩子
        List<Node> list = new ArrayList<Node>();

        for (int i = 0; i < this.nodeNums; i++) {
            if (nodes[i] != null && nodes[i].parent == pos(parent)) {
                list.add(nodes[i]);
            }

        }
        return list;
    }

    //返回该树的深度:树中节点的最大层次值
    public int deep(){
        int max=0;
        for(int i=0;i<this.nodeNums;i++){
            int def=1;
            int m=nodes[i].parent;
            while(m!=-1&&nodes[m]!=null){

                m=nodes[m].parent;
                def++;

            }
            if(def>max){

              max=def;
            }

        }


        return max;//返回了最大层次值


    }



}