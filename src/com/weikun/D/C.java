package com.weikun.D;

/**
 * Created by Administrator on 2016/10/23.
 * //插值排序
 */
public class C {

    /**
     * @param source:待排序的数组
     * @return：已经排好的数组
     */
    public int[] insertSort(int[] source) {
        for(int i=0;i<source.length;i++){
            int index=-1;
            for(int j=0;j<i;j++){
                if(source[j]-source[i]>0){//

                    index=j;//较大值的索引
                    break;
                }

            }
            if(index!=-1){

                moveback(index ,i ,source);//较小索引 index，较大索引
            }



        }
        return source;

    }

    /**
     *
     * @param index:较大索引
     * @param i：较小索引
     * @param source：原数组
     */
    private void moveback(int index, int i, int[] source) {
        int tmp=source[i];//把较小的值放到临时变量
        for(int j=i;j>index;j-- ){//让所有元素挨个退格
            source[j]=source[j-1];
        }
        source[index]=tmp;
    }

    public static void main(String[] args) {
        C c=new C();
        int[] nums = {49,38,10,97,76,13,27};
        c.insertSort(nums);
        for( int i :nums){
            System.out.println(i);
        }
    }
}