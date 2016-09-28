package com.weikun.B;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Administrator on 2016/9/24.
 */
public class F {
    public static void main(String[] args) {
        /*
	 *
	 *
	 * 	 				      0
	 *  				  /      \
	 *  			     1        2
	 *  			   /    \    / \
	 *                3      4  5   6
	 *               / \    /
	 *              7   8  9
	 *
	 */
        F f=new F();
        Integer [] is=new Integer[]{0,1,2,3,4,5,6,7,8,9};

        f.createTreeByArray(is);
        f.depthOrderTraversal();
        System.out.println("---");
        f.levelOrderTraversal();
    }

    public class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private Integer data;

        public TreeNode(Integer data, TreeNode left, TreeNode right) {

            this.data = data;
            this.left = left;
            this.right = right;
        }

    }
    public void levelOrderTraversal(){//广度遍历
        if(root==null){
            System.out.print("不能广度度遍历");
            return;

        }
        Queue<TreeNode> queue=new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode current=queue.remove();
            System.out.print(current.data);
            if(current.left!=null){
                queue.add(current.left);

            }
            if(current.right!=null){
                queue.add(current.right);

            }
        }

    }
    public void depthOrderTraversal(){//DLR
        if(root==null){
            System.out.print("不能深度遍历");
            return;

        }
        Stack<TreeNode> stack=new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode current=stack.pop();//D
            System.out.print(current.data);
            if(current.right!=null){
                stack.push(current.right);
            }
            if(current.left!=null){
                stack.push(current.left);
            }
        }


    }
    private TreeNode root;
    /**
     * @param items:数据集合
     */
    //通过数组创建二叉树
    private void createTreeByArray(Integer[] items) {
        root = new TreeNode(items[0], null, null);//根
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

        queue.add(root);

        int half=items.length/2;//防止找左子 右子的时候越界
        for(int i=0;i<half;i++){
            if(items[i]!=null){//有元素
                TreeNode current=queue.poll();
                int left=2*i+1;
                int right=2*i+2;
                if(items[left]!=null){
                    current.left=new TreeNode(items[left], null, null);//

                    queue.add(current.left);
                }
                if(right<items.length&&items[right]!=null){
                    current.right=new TreeNode(items[right], null, null);//
                    queue.add(current.right);
                }




            }

        }

    }
}
