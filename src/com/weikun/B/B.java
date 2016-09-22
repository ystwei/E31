package com.weikun.B;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */
public class B {
    public static void main(String[] args){
        TreeChild tp = new TreeChild("root");
        TreeChild.Node root = tp.root();
        System.out.println("根节点：" + root);
        tp.addNode("节点1" , root);
        tp.addNode("节点2" , root);
        //tp.addNode("节点3" , root);
        System.out.println("添加子节点后的根节点：" + root);

        System.out.println("此树的深度:" + tp.deep(root));
//		System.out.println("------------");
//		//获取根节点的所有子节点
		List<TreeChild.Node> nodes = tp.getChildren(root);
		System.out.println("根节点的第一个子节点：" + nodes.get(0));
//		//为根节点的第一个子节点新增一个子节点
		tp.addNode("节点4" , nodes.get(0));
        TreeChild.Node n=nodes.get(0);

		System.out.println("节点1第一个子节点" +tp.getChildren(tp.nodes[root.first.pos]).get(0));
		System.out.println("此树的深度:" + tp.deep(root));
    }
}
class TreeChild{
    public static class SonNode{
        //记录当前节点的位置
        public int pos;
        //子节点的中记录的下一个子节点的对象
        private SonNode next;
        public SonNode(int pos , SonNode next){
            this.pos = pos;//当前节点位置
            this.next = next;//下一节点位置
        }
    }


    //子节点链
    public static class Node{
        public String data;
        //记录第一个子节点:用于保存对该子节点链的引用，通过这种关系 ，可以记录树中节点之间的父子关系。
        SonNode first;
        public Node(String data){
            this.data = data;
            this.first = null;
        }
        public String toString(){
            if (first != null){
                return "TreeChild$Node[data=" + data + ", first="
                        + first.pos + "]";
            }else{
                return "TreeChild$Node[data=" + data + ", first=-1]";
            }
        }
    }

    private int treeSize = 100;
    //使用一个Node[]数组来记录该树里的所有节点
    public Node[] nodes;
    //记录节点数目
    private int nodeNums;
    //以指定根节点创建树
    public TreeChild(String data){
        nodes = new Node[this.treeSize];
        nodes[0] = new Node(data);
        nodeNums++;
    }

    //为指定节点添加子节点
    public void addNode(String data , Node parent){//
        for(int i=0;i<this.treeSize;i++){
            if(nodes[i]==null){//可以增加新子节点

                nodes[i]=new Node(data);//新生成节点

                if(parent.first==null){//i节点并没有子节点
                    parent.first=new SonNode(i,null);

                }else{//i节点有子节点，就得找到子节点的最后
                    SonNode  first=parent.first;
                    while(first.next!=null){

                        first=first.next;
                    }

                    first.next=new SonNode(i,null);//加到最后一个子节点后面

                }
                this.nodeNums++;
                return;
            }
        }
    }
    //返回根节点
    public Node root(){
        //返回根节点
        return nodes[0];
    }
    //返回指定节点（非叶子节点）的所有子节点。
    public List<Node> getChildren(Node parent){

        List <Node> list=new ArrayList<Node>();
        SonNode next=parent.first;
        while(next!=null){
            list.add(nodes[next.pos]);
            next=next.next;
        }

        return list;

    }

    //返回该节点的深度，这是一个递归方法：每棵子树的深度为其所有子树的最大深度 + 1
    public int deep(Node node){
        int max=0;//子节点最大值

        if(node.first==null){

            return 1;
        }else{//有孩子链
            SonNode s=node.first;
            while(s!=null){

                int def=deep(nodes[s.pos]);//返回子节点所对应的深度
                if(def>max){
                    max=def;
                }
                s=s.next;//找到所有孩子子节点
            }
        }

        return max+1;//父节点的深度

    }


}
