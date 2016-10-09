package com.weikun.C;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/10/9.
 * 迪杰斯特拉最短路径
 */
public class G {
    private Map<String,Integer> path=new HashMap<String,Integer>();//封装路径距离
    private Set<Node> open=new HashSet<Node>();//open用于存储未遍历的点
    private Set<Node> close=new HashSet<Node>();//close用来存储已遍历的节点
    public static void main(String[] args) {
        G g=new G();
        Node n=g.init1();
        g.djstla(n);
    }
    /*
	 * Node对象用于封装节点信息，包括名字和子节点
	 */
    public  class Node {
        private String name;
        //子节点
        private Map<Node,Integer> child=new HashMap<Node,Integer>();
        public Node(String name){
            this.name=name;
        }
        public String getName() {
            return name;
        }

        public Map<Node, Integer> getChild() {
            return child;
        }

    }

    public Node init1(){
        //计算出V0-V8的最短距离，需要设置V0节点和所有其他所有节点的关系
        path.put("V1", 1);
        path.put("V2", 5);

        //初始路径,因没有V3->V0这条路径,所以path(E)设置为Integer.MAX_VALUE
        path.put("V3",Integer.MAX_VALUE);
        path.put("V4", Integer.MAX_VALUE);

        path.put("V5", Integer.MAX_VALUE);


        path.put("V6", Integer.MAX_VALUE);


        path.put("V7", Integer.MAX_VALUE);



        path.put("V8", Integer.MAX_VALUE);


        //将初始节点放入close,其他节点放入open
        Node start=new MapBuilder().build1(open,close);
        return start;
    }
    public class MapBuilder {
        public Node build1(Set<Node> open, Set<Node> close){
            Node node0=new Node("V0");
            Node node1=new Node("V1");
            Node node2=new Node("V2");
            Node node3=new Node("V3");
            Node node4=new Node("V4");
            Node node5=new Node("V5");
            Node node6=new Node("V6");
            Node node7=new Node("V7");
            Node node8=new Node("V8");

            node0.getChild().put(node1, 1);//V0--V1
            node0.getChild().put(node2, 5);//V0--V2

            node1.getChild().put(node0, 1);
            node1.getChild().put(node2, 3);
            node1.getChild().put(node3, 7);
            node1.getChild().put(node4, 5);

            node2.getChild().put(node0, 5);
            node2.getChild().put(node1, 3);
            node2.getChild().put(node4, 1);
            node2.getChild().put(node5, 7);

            node3.getChild().put(node1, 7);
            node3.getChild().put(node4, 2);
            node3.getChild().put(node6, 3);

            node4.getChild().put(node1, 5);
            node4.getChild().put(node2, 1);
            node4.getChild().put(node3, 2);
            node4.getChild().put(node5, 3);
            node4.getChild().put(node6, 6);
            node4.getChild().put(node7, 9);

            node5.getChild().put(node2, 7);
            node5.getChild().put(node4, 3);
            node5.getChild().put(node7, 5);


            node6.getChild().put(node3, 3);
            node6.getChild().put(node4, 6);
            node6.getChild().put(node7, 2);
            node6.getChild().put(node8, 7);


            node7.getChild().put(node4, 9);
            node7.getChild().put(node6, 2);
            node7.getChild().put(node8, 4);

            node8.getChild().put(node6, 7);
            node8.getChild().put(node7, 4);
            ////初始节点放入close 其他节点放入open
            open.add(node1);
            open.add(node2);
            open.add(node3);
            open.add(node4);
            open.add(node5);
            open.add(node6);
            open.add(node7);
            open.add(node8);
            close.add(node0);//初始节点放入close
            return node0;

        }

    }

    /**
     *
     * @param start:从哪个节点开始
     */
    public void djstla(Node start){
        Node nearest=this.getShortNode(start);
        if(nearest==null){
            return ;
        }
        close.add(nearest);//访问过了
        open.remove(nearest);//

        System.out.print(nearest.getName()+":"+path.get(nearest.getName())+"\n");

        Map <Node,Integer> childs=nearest.getChild();

        for(Node child :childs.keySet()) {

            if(open.contains(child)){//没被访问
                                //V0---V1      V1---V2
                int i1=path.get(nearest.getName());
                int i2=childs.get(child);

                int newWeight=i1+i2;
                if(newWeight<path.get(child.getName())){

                   path.put(child.getName(),newWeight);
                }

            }

        }
        this.djstla(nearest);


    }

    /**
     *
     * @param start:获得临start节点最近的节点
     * @return :返回最近的节点
     */
    private Node getShortNode(Node start) {
        Node res=null;
        int min=Integer.MAX_VALUE;

        Map<Node, Integer> map=start.getChild();
        for( Node node:map.keySet()){

            if(open.contains(node)){//该节点没有访问
                int distance=map.get(node);//权值
                if(distance<min){
                    min=distance;
                    res=node;
                }

            }

        }

        return res;
    }
}
