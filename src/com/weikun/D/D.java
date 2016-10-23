package com.weikun.D;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/23.
 * 折半插入排序
 */
public class D {

    public static void main(String[] args) {

        int[] data = new int[] {49,38,10,97,76,13,27};
        System.out.println("原始数据："+ Arrays.toString(data));
        //binaryInsertSort(data);
        binaryInsertSort(data);

        for(int i :data){
            System.out.println(i);
        }
    }

    private static void binaryInsertSort(int[] data) {

        for(int i=0;i<data.length-1;i++){

            if(data[i]>data[i+1]){//进行折半查找 找到其最合适的索引号

                int tmp=data[i+1];
                int low=0;
                int high=i;
                while(low<=high){//折半
                    int mid=(low+high)/2;
                    if(data[mid]<tmp){
                        low=mid+1;
                    }else{
                        high=mid-1;
                    }
                }
                //跳出循环之后，知道了合适位置索引
                for(int j=i;j>=low;j--){
                    data[j+1]=data[j];
                }
                data[low]=tmp;


            }

        }

    }
}
