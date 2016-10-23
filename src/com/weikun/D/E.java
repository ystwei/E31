package com.weikun.D;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/23.
 * Shell排序
 */
public class E {

    public static int count = 0;
    private int [] data={47,55,10,40,15,94,5,70};

    public  void shellSort(){
        System.out.println("原始数组：");
        System.out.println(Arrays.toString(data));
        System.out.println("-------------------");
        System.out.println("开始排序：");
        int arrayLength = data.length;
        int h=0;
        while(h<=arrayLength/3){
            h=h*3 +1;
        }
        while(h>0){
            for(int i=h;i<arrayLength;i++){
                int tmp=data[i];//
                if(data[i-h]-data[i]>0){
                    int j=i-h;
                    for(  ;j>=0 && data[j]-tmp>0; j=j-h ){
                        data[j+h]=data[j];
                    }
                    data[j+h]=tmp;
                }
            }
            h=(h-1)/3;

        }




    }
    public static void main(String[] args) {
        E e=new E();
        e.shellSort();
        for(int i :e.data){
            System.out.println(i);
        }



    }


}
