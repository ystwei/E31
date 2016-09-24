package com.weikun.B;



/**
 * Created by Administrator on 2016/9/24.
 * 二叉树---二叉链表存储
 *
 *     ROOT
 *    A   B
 *   C
 */
public class D {
    public static void main(String[] args) {
        TwoLinkBinTree tt=new TwoLinkBinTree("ROOT");
        TwoLinkBinTree.TreeNode a=tt.addNode(tt.root(),"A",true);
        tt.addNode(tt.root(),"B",false);


        tt.addNode(a,"C",true);
        tt.addNode(a,"D",false);
        System.out.print(tt.leftChild(a));
        System.out.print(tt.deep(tt.root()));
    }
}

class TwoLinkBinTree {
    static class TreeNode{
        String data;//当前节点的数据
        TreeNode left;//左子结点地址
        TreeNode right;//右子结点地址
        public TreeNode(String data){
            this.data=data;

        }
        public TreeNode(String data,TreeNode left,TreeNode right){
            this.data = data;
            this.left=left;
            this.right=right;
        }

    }
    private TreeNode root;//根节点
    public TwoLinkBinTree(String data){
        root=new TreeNode(data);
    }
    /**
     *
     * @param parent:要增加到父节点的地址
     * @param data：当前节点数据
     * @param isLeft：是否是左
     * @return：返回新节点地址
     */
    public TreeNode addNode(TreeNode parent , String data, boolean isLeft){
        if(parent==null){
            System.out.println("父节点为空，不能增加子节点");
            return null;
        }
        if(isLeft&&parent.left!=null){
            System.out.println("已经有左子了！");
            return null;
        }
        if(!isLeft&&parent.right!=null){
            System.out.println("已经有右子了！");
            return null;
        }
        TreeNode newNode=new TreeNode(data);
        if(isLeft){
            parent.left=newNode;
        }else{
            parent.right=newNode;
        }
        return newNode;
    }

    /**
     *
     * @param parent：父亲
     * @return左子
     */
    public String leftChild(TreeNode parent){

        return parent.left==null?null:parent.left.data;

    }
    public TreeNode root(){
        return root;
    }
    /**
     *
     * @param parent：父亲
     * @return右子
     */
    public String rightChild(TreeNode parent){

        return parent.right==null?null:parent.right.data;

    }

    /**
     *
     * @param node:代表当前节点地址
     * @return：最大深度
     */

    public int deep(TreeNode node){
        if(node==null){
            return 0;
        }
        if(node.left==null && node.right==null){
            return 1;

        }else{
            int leftdeep=this.deep(node.left);
            int rightdeep=this.deep(node.right);

            return leftdeep>rightdeep?leftdeep+1:rightdeep+1;
        }

    }


}
