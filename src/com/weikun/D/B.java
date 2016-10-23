package com.weikun.D;

/**
 * Created by Administrator on 2016/10/23.\
 * 归并排序
 */
public class B {
    private int[] data={50,10,90,30,70,40,80,60,20};

    /**
     * 
     */
    public  void mergeSort(){
        //归并排序
        sort(data , 0 , data.length - 1);
    }

    private void sort(int[] data, int left, int right) {

        if(left<right){
            int center=(left+right)/2;
            sort(data,left,center);//左半部

            sort(data,center+1,right);//左半部

            merge(data,left,center,right);
        }

    }
    /** 合并
     * 将两个数组进行归并，归并前两个数组已经有序，归并后依然有序。
     * @param data 数组对象
     * @param left 左数组的第一个元素的索引
     * @param center 左数组的最后一个元素的索引，center+1是右数组第一个元素的索引
     * @param right 右数组的最后一个元素的索引
     */
    private  void merge(int[] data, int left, int center, int right) {
       //System.out.printf("%d--%d-%d",left,center,right);
        //System.out.println();
        int [] temp=new int[data.length];//临时数组用来存放，排序后从小到大的临时结果
        int mid=center+1;//右半边的左起点
        int tmp=left;
        int third=left;
        while(left<=center&&mid<=right){
            if(data[left]-data[mid]>0){
                temp[third++]=data[mid++];
            }else{
                temp[third++]=data[left++];
            }
        }
        while( mid<=right){//右半边剩余
            temp[third++]=data[mid++];
        }

        while(left<=center){//左半步剩余
            temp[third++]=data[left++];

        }
        //把临时数组已经排序好的结果，依次放入data数组中

        while(tmp<=right){
            data[tmp]=temp[tmp++];

        }
    }

    public static void main(String[] args) {
        B b=new B();
        b.mergeSort();

        for(int i :b.data){
            System.out.println(i);
        }
    }
}
