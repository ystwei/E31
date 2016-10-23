package com.weikun.D;

/**
 * Created by Administrator on 2016/10/22.
 * 堆排序
 */
public class A {

    private int [] data={9,79,46,30,58,49};

    public static void main(String[] args) {
        A h=new A();

        System.out.println("排序之前：\n"
                + java.util.Arrays.toString(h.data));
        h.heapSort();
        System.out.println("排序之后：\n"
                + java.util.Arrays.toString(h.data));
    }

    private void heapSort() {
        System.out.println("开始排序");
        int arrayLength = data.length;
        //循环建堆，构建完全二叉树
		/*
		 *        9
		 *      /   \
		 *    79    46
		 *   / \   /
		 * 30  58 49
		 */
        //针对完全二叉树，大顶堆，最顶端的值(0号索引)肯定是在没交换之前的最大的值，
        //对其在数组里面进行排序交换，大的排在数组后面，虽然最大的在最上面，但是其他的数据并没有排好，因此，需要不停更换顶点，重新建堆，需要循环

        for(int i=0;i<arrayLength-1;i++){
            System.out.println("创建大顶堆");
            builMaxdHeap(arrayLength-1-i);
        }
    }
    //对data数组从0到lastIndex建大顶堆.
    //大顶堆的特性是：所有父节点的值都大于两个左右子节点
    private  void builMaxdHeap( int lastIndex) {//修建大顶堆
        for(int i=(lastIndex-1)/2;i>=0;i--){//倒着找叶子节点的父亲
            int k=i;
            /*
		 *        9
		 *      /   \
		 *    79    46
		 *   / \   /
		 * 30  58 49
		 */
            while(k*2+1<=lastIndex){//找到其叶子节点索引，不要超过他的父亲控制范围
                int biggerIndex=k*2+1;
                if(biggerIndex<lastIndex){//没越界

                    if(data[biggerIndex]-data[biggerIndex+1]<0){//同兄弟比
                        biggerIndex++;
                    }

                }
                //biggerIndex>=lastIndex，父亲和你比
                if(data[k]-data[biggerIndex]<0){

                    //交换
                    this.swap(k,biggerIndex);
                }else{
                    break;
                }

            }

        }



    }

    /**
     *
     * @param i
     * @param j
     */
    //交换data数组中i、j两个索引处的元素
    private  void swap( int i , int j){
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}
