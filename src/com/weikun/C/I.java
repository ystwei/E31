package com.weikun.C;

/**
 * Created by Administrator on 2016/10/15.
 *
 *
 * AVL树
 */
public class I {
    private AVLTreeNode mRoot;    // 根结点
    // AVL树的节点(内部类)
    class AVLTreeNode {
        Integer key;                // 关键字(键值)
        int height;         // 当前节点中子节点的最大高度
        AVLTreeNode left;    // 左孩子
        AVLTreeNode right;    // 右孩子

        public AVLTreeNode(Integer key, AVLTreeNode left, AVLTreeNode right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    // 构造函数
    public I() {
        mRoot = null;
    }

    private void preOrder(AVLTreeNode tree) {
        if(tree != null) {
            System.out.print(tree.key+" ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"AVL树"
     * LDR
     */
    private void inOrder(AVLTreeNode tree) {
        if(tree != null){
            inOrder(tree.left);
            System.out.print(tree.key+" ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    /*
     * 后序遍历"AVL树"
     * LRD
     */
    private void postOrder(AVLTreeNode tree) {
        if(tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key+" ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /*
	 * 获取树的高度
	 */
    private int height(AVLTreeNode tree) {
        if (tree != null){
            return tree.height;
        }

        return 0;
    }

    public int height() {
        return height(mRoot);
    }
    /*
	 * 将结点插入到AVL树中，并返回根节点	 *
	 * 参数说明：
	 *     tree AVL树的根结点
	 *     key 插入的结点的键值
	 * 返回值：
	 *     根节点
	 */
    private AVLTreeNode insert(AVLTreeNode tree, Integer key) {

        if(tree==null){

            tree=new  AVLTreeNode(key,null,null);
        }else{
            if(key.compareTo(tree.key)<0){
                tree.left=insert(tree.left,key);//加到左子
                if(this.height(tree.left)-this.height(tree.right)==2){//打破左右平衡

                    if(key.compareTo(tree.left.key)<0){//比左节点还小，就是左左
                        tree=this.leftLeftRotation(tree);

                    }else{//比左节点还大，就是左左
                        tree=this.leftRightRotation(tree);

                    }
                }
            }else if(key.compareTo(tree.key)>0){//tree.key.compareTo(key)>0

                tree.right=insert(tree.right,key);//加到右子
                if(this.height(tree.right)-this.height(tree.left)==2) {//打破左右平衡 右-左
                    if(key-tree.right.key<0){//比右子还小
                        tree=this.rightLeftRotation(tree);

                    }else{

                        tree=this.rightRightRotation(tree);
                    }

                }


            }else{

                System.out.println("添加失败：不允许添加相同的节点！");
            }


        }
        tree.height = max( height(tree.left), height(tree.right)) + 1;//从0开始算高度

        return tree;

    }

    /*
	 * 比较两个节点的height的两个值的大小
	 */
    private int max(int a, int b) {
        return a>b ? a : b;
    }

    /**
     *
     *  *        2
     *       A                     B
     *     /  1                 /   \
     *   B   -->>LL--->>      X     A
     *  /
     * X
     *
     * @param k2:A
     * @return
     */
    private AVLTreeNode leftLeftRotation(AVLTreeNode k2) {//K2是A
        AVLTreeNode k1=k2.left;//B
        k2.left=k1.right;
        k1.right=k2;

        k2.height=max(height(k2.left),height(k2.right))+1;
        k1.height=max(height(k1.left),k2.height)+1;

        return k1;
    }
    /*
	 * RR：右右对应的情况(右单旋转)。
	 *
	 *      -2                              0
	 *     A                               B
	 *      \ -1                         /0  \0
	 *       B         -->>RR--->>      A     X
	 *     /  \ 0                        \
	 *    C    X                          C
	 *
	 *(插入节点是右子树的右边节点)
	 * 返回值：旋转后的根节点
	 */
    private AVLTreeNode rightRightRotation(AVLTreeNode k1) {//k1:A
        AVLTreeNode k2=k1.right;//B
        k1.right=k2.left;
        k2.left=k1;

        k1.height=max(height(k1.left),height(k1.right))+1;

        k2.height=max(height(k2.right) ,k1.height)+1;

        return k2;


    }

    /*
	 * RL：右左对应的情况(右双旋转)。对应 LL-RR
	 *(插入节点是右子树的左边节点)
	 * 返回值：旋转后的根节点
	 */
    private AVLTreeNode rightLeftRotation(AVLTreeNode k1) {
        k1.right = leftLeftRotation(k1.right);

        return rightRightRotation(k1);
    }
    /*
	 * LR：左右对应的情况(左双旋转)。--对应RR-LL
	 *(插入节点是左子树的右边节点)
	 * 返回值：旋转后的根节点
	 */
    private AVLTreeNode leftRightRotation(AVLTreeNode k3) {
        k3.left = rightRightRotation(k3.left);

        return leftLeftRotation(k3);
    }

    private static int arr[]= {3,2,1,4,5,6,7,10,9,8};
    public static void main(String[] args) {
        int i;
        I tree = new I();

        System.out.printf("== 依次添加: ");
        for(i=0; i<arr.length; i++) {
            //tree.insert(arr[i]);
            tree.mRoot=tree.insert(tree.mRoot,arr[i]);
            System.out.printf("%d ", arr[i]);
        }
        System.out.printf("\n== 前序遍历: ");
        tree.preOrder();
        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();
        System.out.printf("\n== 后序遍历: ");
        tree.postOrder();
        System.out.printf("\n");
        System.out.printf("== 高度: %d\n", tree.height());
        System.out.printf("== 最小值: %d\n", tree.minimum());
        System.out.printf("== 最大值: %d\n", tree.maximum());
        System.out.printf("== 树的详细信息: \n");
        tree.print();
    }
    /*
	 * 打印"二叉查找树"
	 *
	 * key        -- 节点的键值
	 * direction  --  0，表示该节点是根节点;
	 *               -1，表示该节点是它的父结点的左孩子;
	 *                1，表示该节点是它的父结点的右孩子。
	 */
    private void print(AVLTreeNode tree, Integer key, int direction) {
        if(tree != null) {
            if(direction==0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.key, key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction==1?"right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right,tree.key,  1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
    /*
	 * 查找最小结点：返回tree为根结点的AVL树的最小结点。
	 */
    private AVLTreeNode minimum(AVLTreeNode tree) {
        if (tree == null){
            return null;
        }

        while(tree.left != null){
            tree = tree.left;
        }
        return tree;
    }

    public Integer minimum() {
        AVLTreeNode p = minimum(mRoot);
        if (p != null){
            return p.key;
        }

        return null;
    }

    /*
     * 查找最大结点：返回tree为根结点的AVL树的最大结点。
     */
    private AVLTreeNode maximum(AVLTreeNode tree) {
        if (tree == null){
            return null;
        }

        while(tree.right != null){
            tree = tree.right;
        }
        return tree;
    }

    public Integer maximum() {
        AVLTreeNode p = maximum(mRoot);
        if (p != null){
            return p.key;
        }

        return null;
    }


}
