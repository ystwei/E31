package com.weikun.B;


/**
 * Created by Administrator on 2016/9/28.
 */
public class H {


    private BSTNode mRoot;//根节点
    public class BSTNode{
        Integer key;//关键字(键值)
        BSTNode left;//左孩子
        BSTNode right;//右孩子
        BSTNode parent;//父节点
        public BSTNode(Integer key,BSTNode parent,BSTNode left,BSTNode right){
            this.key=key;
            this.parent=parent;
            this.left=left;
            this.right=right;
        }
    }
    //前序遍历
    public void preOrder(BSTNode node){
        //DLR
        if(node!=null){
            System.out.print(node.key);//D
            this.preOrder(node.left);//L
            this.preOrder(node.right);//R
        }


    }
    public void preOrder(){
        preOrder(this.mRoot);
    }

    //后序遍历
    public void postOrder(BSTNode node){
        //LRD
        if(node!=null){

            this.postOrder(node.left);//L
            this.postOrder(node.right);//R
            System.out.print(node.key);//D
        }


    }
    public void postOrder(){
        postOrder(this.mRoot);
    }


    //中序遍历
    public void inOrder(BSTNode node){
        //LDR
        if(node!=null){

            this.inOrder(node.left);//L
            System.out.print(node.key);//D
            this.inOrder(node.right);//R

        }


    }
    public void inOrder(){
        inOrder(this.mRoot);
    }
    //前驱：查找该节点(x)的前驱节点，即查找二叉树中数据值小于该节点的最大节点
    public BSTNode predecessor(BSTNode x){
        if(x.left!=null){//有左子
            return this.maximum(x.left);

        }
        //如果x没有左孩子，则x有以下两种可能：
        //1.x是一个右孩子，则x的前驱节点为它的父节点
        //2.x是一个左孩子，则查找x的最低的父节点，并且该节点要具有右孩子(因为都比他大)，
        //找到的这个最低的父节点就是x的前驱节点
        //其实就是，该节点左子树为空, 则其前驱为：其祖先节点(递归), 且该祖先节点的右孩子也是其祖先节点
        //  通俗的说，一直往上找找到最后出现左拐那次后的祖先节点;
        if(x.parent==null){//根节点没有前驱
            System.out.println("根节点没有前驱");
            return null;
        }

        BSTNode y=x.parent;//x的父亲

        while((y!=null)&&(x==y.left)){//x一定是y的左子
            x=y;
            y=y.parent;
        }

        return y;

    }
    /*后继节点
	 * 找结点x的后继节点，即，查找二叉树中数据值大于该节点的最小节点
	 */
    public BSTNode successor(BSTNode x){
        if(x.right!=null){
            return this.minmum(x.right);//

        }
        //如果没有右孩子，则x有以下两种可能
        //1.x是一个左孩子，则x的后继节点为它的父节点
        //2.x是一个右孩子，则查找x的最低父节点，并且该父节点要具有左孩子，找到的这个最低的父节点就是x的后继节点
        //
        //就是说一直往上找其祖先节点，直到出现最后出现右拐后的那个祖先节点：

        BSTNode y=x.parent;

        while((y!=null)&&(x==y.right)){
            x=y;
            y=y.parent;
        }
        return y;
    }

    public void insert(Integer key){
        BSTNode z=new BSTNode(key,null,null,null);
        //如果新建节点失败，则返回
        if(z!=null){
            insert(this,z);
        }
    }


    /*
	 * 删除节点z，并返回被删除的节点
	 * bst：二叉树
	 * z删除的节点
	 * 1.没有儿子，即为叶结点。直接把父结点的对应儿子指针设为NULL，就OK了。
	   2.只有一个儿子。那么把父结点的相应儿子指针指向儿子的独生子，删除儿子结点也OK了。
	   3.有两个儿子。这是最麻烦的情况，因为你删除节点之后，还要保证满足搜索二叉树的结构。其实也比较容易，
	   我们可以选择左儿子中的最大元素或者右儿子中的最小元素放到待删除节点的位置，就可以保证结构的不变。
	   当然，你要记得调整子树，毕竟又出现了节点删除。习惯上大家选择左儿子中的最大元素，其实选择右儿子的最小元素也一样，
	   没有任何差别，只是人们习惯从左向右。这里咱们也选择左儿子的最大元素，将它放到待删结点的位置。
	   左儿子的最大元素其实很好找，只要顺着左儿子不断的去搜索右子树就可以了，直到找到一个没有右子树的结点。那就是最大的了。
	 */

    /**
     *
     * @param bst:要操作的那颗树
     * @param z：要删除的节点
     * @return y:要删除的节点
     */
    private BSTNode remove(H bst,BSTNode z){
        BSTNode x=null;
        BSTNode y=null;
        if(z.left==null || z.right==null){

            y=z;//如果要删除的节点没有左子，或者没有右子，或者左右都没有，那么 y就是要删除的节点z

        }else{//左右都有
            y=this.successor(z);//y就是z的后继

        }

        if(y.left!=null){
            x=y.left;

        }else{
            x=y.right;

        }
        if(x!=null){
            x.parent=y.parent;
        }
        //来判断x代替y的哪个位置
        if(y.parent==null){

            this.mRoot=x;
        }else if(y==y.parent.left){//y就是其父亲左子
            y.parent.left=x;//x

        }else{

            y.parent.right=x;
        }
        if(y!=z){//他两个不是一个节点
            z.key=y.key;//删除节点的后继节点值给要删除的节点
        }
        return y;



    }
    /*递归实现，查找二叉树x中键值为key的节点
	 *
	 */
    private BSTNode search(BSTNode x,Integer  key){
        if(x==null){

            return null;
        }
        int cmp=key.compareTo(x.key);
        if(cmp<0){
           return this.search(x.left,key);
        }else if(cmp>0){
           return this.search(x.right,key);

        }else{
            return x;

        }
    }
   /* * 删除节点z，并返回被删除的节点
    * tree：二叉树的根节点
    * z 删除的节点
    */
    public void remove(Integer key){
        BSTNode z, node;
        if((z=this.search(mRoot, key))!=null){
            if((node=remove(this,z))!=null){
                node=null;//把删除节点清空
            }
        }
    }

    private  void insert(H h,BSTNode z){
        int cmp=0;
        BSTNode y=null;
        BSTNode x=this.mRoot;//根节点
        while(x!=null){
            y=x;
            cmp=z.key.compareTo(x.key);
            if(cmp<0){
                x=x.left;
            }else if(cmp>0){
                x=x.right;
            }else{
                System.out.print("不允许插入");
                return;
            }
        }
        //x==null y就是即将插入节点的父节点
        z.parent=y;
        if(y==null){

            this.mRoot=z;
        }else{
            cmp=z.key.compareTo(y.key);
            if(cmp<0){
                y.left=z;
            }else if(cmp>0){
                y.right=z;
            }else{

                return ;
            }


        }
    }
    private static final Integer arr[] = {1,5,4,3,2,6};
    /*
	 * 		    1
	 * 		     \
	 *            5
	 *          /   \
	 *         4     6
	 *        /
	 *       3
	 *      /
	 *     2
	 */
    public static void main(String[] args) {

            int i, ilen;
            H tree=new H();

            System.out.print("== 依次添加: ");
            ilen = arr.length;
            for(i=0; i<ilen; i++) {
                System.out.print(arr[i]+" ");
                tree.insert(arr[i]);
            }
            System.out.print("\n== 前序遍历DLR: ");
            tree.preOrder();

            System.out.print("\n== 中序遍历LDR: ");
            tree.inOrder();

            System.out.print("\n== 后序遍历LRD: ");
            tree.postOrder();
            System.out.println();

            System.out.println("== 最小值: "+ tree.minmum(tree.mRoot).key);
            System.out.println("== 最大值: "+ tree.maximum(tree.mRoot).key);
            System.out.println("== 树的详细信息: ");


            System.out.print("\n== 删除节点: "+ arr[1]);
            tree.remove(arr[1]);

            System.out.print("\n== 中序遍历: ");
            tree.inOrder();
            System.out.println();

            // 销毁二叉树



    }

    /*
	 * 查找最大结点，返回tree为根节点的二叉树的最大节点，在右侧找
	 */
    private BSTNode maximum(BSTNode tree){

        if(tree==null){

            return null;
        }
        //!=null
        while(tree.right!=null){

           tree=tree.right ;
        }
        return tree;
    }

    /*
	 * 查找最小结点，返回tree为根节点的二叉树的最小结点，在左侧找
	 */
    private BSTNode minmum(BSTNode tree){

        if(tree==null){

            return null;
        }
        //!=null
        while(tree.left!=null){

            tree=tree.left ;
        }
        return tree;
    }




}
