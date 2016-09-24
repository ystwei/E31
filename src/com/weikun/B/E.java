package com.weikun.B;

/**
 * Created by Administrator on 2016/9/24.
 */
public class E {
    public static void main(String[] args) {
        E e=new E();
        TreeNode root=e.init();
        e.xianxu(root);
        System.out.println("----");
        e.zhongxu(root);
        System.out.println("----");
        e.houxu(root);

    }
    public void printNode(TreeNode node){
        System.out.print(node.data+" ");
    }
    /*
  * 				    A
  * 				/		\
  * 			 B             C
  * 			/ \          /   \
  * 		   D    E       F       G
  *             / \       \     /
  * 			  H   I       J   P
  *
  */
    //A B D E H I C F J G P
    public void xianxu(TreeNode node){//DLR
        printNode(node);
        if(node.left!=null){
            this.xianxu(node.left);
        }
        if(node.right!=null){
            this.xianxu(node.right);
        }
    }

    public void zhongxu(TreeNode node){//LDR

        if(node.left!=null){
            this.xianxu(node.left);
        }
        printNode(node);
        if(node.right!=null){
            this.xianxu(node.right);
        }
    }

    public void houxu(TreeNode node){//LRD

        if(node.left!=null){
            this.xianxu(node.left);
        }

        if(node.right!=null){
            this.xianxu(node.right);
        }
        printNode(node);
    }
    /**
     *
     * @return;ROOT
     */
    public TreeNode init(){

        /*
	 * 				    A
	 * 				/		\
	 * 			 B             C
	 * 			/ \          /   \
	 * 		   D    E       F       G
	 *             / \       \     /
	 * 			  H   I       J   P
	 *
	 */

            TreeNode D=new TreeNode("D",null,null);
            TreeNode H=new TreeNode("H",null,null);
            TreeNode I=new TreeNode("I",null,null);
            TreeNode J=new TreeNode("J",null,null);
            TreeNode P=new TreeNode("P",null,null);

            TreeNode G=new TreeNode("G",P,null);
            TreeNode F=new TreeNode("F",null,J);
            TreeNode E=new TreeNode("E",H,I);
            TreeNode B=new TreeNode("B",D,E);
            TreeNode C=new TreeNode("C",F,G);
            TreeNode A=new TreeNode("A",B,C);//ROOT
            return A;

    }
    public class TreeNode{
        private TreeNode left;
        private TreeNode right;
        private String data;
        public TreeNode(String data,TreeNode left,TreeNode right){

            this.data=data;
            this.left=left;
            this.right=right;
        }

    }


}

