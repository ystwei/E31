package com.weikun.B;

/**
 * Created by Administrator on 2016/9/24.
 * 二叉树的-顺序存储
 */
public class C {
    public static void main(String[] args) {
        ArrayBinTree a=new ArrayBinTree();
        a.add(0,"A",true);//左子
        a.add(0,"B",false);//右子

        a.add(2,"C",true);//左子
        a.add(2,"D",false);//右子

        System.out.println(a.left(2));
        System.out.println(a.right(2));
    }
}
class ArrayBinTree{
    private String [] datas;
    private int  DEFAULT_DEEP=8;//默认深度
    private int arraySize;//总节点的个数
    private int deep;//深度
    public ArrayBinTree(){
        deep=this.DEFAULT_DEEP;
        arraySize=(int)(Math.pow(2,deep)-1);//2(i)-1总结点的个数
        datas=new String[arraySize];
        datas[0]="ROOT";
    }
    public ArrayBinTree(int deep){
        this.deep=deep;
        arraySize=(int)(Math.pow(2,deep)-1);//2(i)-1总结点的个数
        datas=new String[arraySize];
    }
    /**
     *
     * @param deep
     * @param data:根节点的值
     */
    public ArrayBinTree(int deep,String data){
        this.deep=deep;
        arraySize=(int)(Math.pow(2,deep)-1);//2(i)-1总结点的个数
        datas=new String[arraySize];
        datas[0]=data;//初始化数据
    }
    /**
     *
     * @param index:要添加节点的父节点索引
     * @param data：该节点数据
     * @param left：是否是左子
     */
    public void add(int index , String data , boolean left){
        if(this.datas[index]==null){//看其index所指定的父节点是否存在
            System.out.println("父亲并不存在，儿子当然也不存在");
            return ;
        }

        if(2*index+1>=this.arraySize){//越界
            System.out.println("越界了");
            return ;
        }

        if(left){//左子
            this.datas[2*index+1]=data;
        }else{
            this.datas[2*index+2]=data;

        }

    }

    public String left(int index ){//根据index找到其左子结点
        if(2*index+1>=this.arraySize){
            System.out.println("越界了");
            return null;
        }
        return this.datas[2*index+1];
    }

    public String right(int index ){//根据index找到其左子结点
        if(2*index+2>=this.arraySize){
            System.out.println("越界了");
            return null;
        }
        return this.datas[2*index+2];
    }
}
