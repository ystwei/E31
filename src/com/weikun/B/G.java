package com.weikun.B;

import java.util.*;

/**
 * Created by Administrator on 2016/9/24.
 * 哈夫曼树
 */
public class G {


    public static void main(String[] args) {
        G g=new G();
        List<Node> list = new ArrayList<Node>();
        list.add(new Node("a",3));
        list.add(new Node("b",4));
        list.add(new Node("c",15));
        list.add(new Node("d",18));

        g.breath(g.createTree(list));
    }
    public void breath(Node root){//广度遍历

        Queue<Node> queue=new ArrayDeque<Node>();

        queue.add(root);

        while(!queue.isEmpty()){
            Node current=queue.poll();
            System.out.println(current.getWeight());
            Node left=current.getLeft();
            Node right=current.getRight();
            if(left!=null){
                queue.offer(left);
            }
            if(right!=null){
                queue.offer(right);
            }
        }


    }
    public Node createTree(List<Node> list){
        while(list.size()>1){
            Collections.sort(list);//排序
            Node left=list.get(0);//左
            Node right=list.get(1);//右
            Node parent=new Node(null,left.getWeight()+right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        return list.get(0);

    }

}
class Node implements Comparable<Node> {
    private String data;
    private int weight;  //权重
    private Node left;
    private Node right;

    public Node(String data, int weight){
        this.data = data;
        this.weight = weight;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


    @Override
    public int compareTo(Node o) {


        return this.getWeight()-o.getWeight();
    }
}

