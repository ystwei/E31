package com.weikun.C;

import java.security.Key;

/**
 * Created by Administrator on 2016/10/22.
 * 自己写HashMap，节点使用挂链法，链表
 */
public class J {
    class Entry {//节点类
        Entry next;// 下一个结点
        Integer key;// key
        String value;// value
        int hash;// 这个key对应的hash码，作为一个成员变量，当下次需要用的时候可以不用重新计算

        // 构造方法
        Entry(Integer k, String v, int hash) {
            this.key = k;
            this.value = v;
            this.hash = hash;
        }
        //相应的getter()方法
    }
    private int size;// 当前容量
    private static int INIT_CAPACITY = 16;// 默认容量
    private Entry [] container;// 实际存储数据的数组对象


    //装载因子:就是hash表中已经存储的关键字个数，
    //与可以散列位置的比值，表征着hash表中的拥挤情况，
    //一般而言，该值越大则越容易发生冲突，相应地ASL(平均查找长度)也增大
    private static float LOAD_FACTOR = 0.75f;//

    private int max;// 能存的最大的数=capacity*factor
    public  J(){
        this(INIT_CAPACITY,LOAD_FACTOR);

    }

    /**
     *
     * @param k:所要找的键
     * @return返回其值
     */
    public String get(Integer k) {
        int index=this.indexFor(k.hashCode(),this.container.length);
        Entry e=this.container[index];

        while(e!=null){
            if(e.key==k || e.key.equals(k)){

                return e.value;
            }
            e=e.next;
        }
        return null;
    }
    public static void main(String[] args) {
        J j=new J();
        Long aBeginTime=System.currentTimeMillis();//记录BeginTime
        for(int i=0;i<1000000;i++){
            j.put(i,""+i);
        }
        Long aEndTime=System.currentTimeMillis();//记录EndTime
        System.out.println("insert time-->"+(aEndTime-aBeginTime));

        System.out.print(j.get(10));
    }
    public  J(int init_cap,float load_fac){
        this.INIT_CAPACITY=init_cap;
        this.LOAD_FACTOR=load_fac;
        this.max= (int)(this.LOAD_FACTOR* INIT_CAPACITY); //拥挤程度，其中LOAD_FACTOR 越大，代表越接近极限容器值，越拥挤

        this.container=new Entry [this.INIT_CAPACITY];//开辟存储空间
    }
    /**
     * 根据hash码，容器数组的长度,计算该哈希码在容器数组中的下标值
     *
     * @param hashcode
     * @param containerLength
     * @return
     */
    public int indexFor(int hashcode, int containerLength) {
        return hashcode & containerLength-1;//2&3=0000 0010 & 0000 1111 =0000 0010=2
    }

    /**
     *
     *  把新节点加到老节点末尾
     * @param entry:老节点
     * @param temp：新节点
     */
    private void addEntry2Last(Entry entry, Entry temp) {
        if(this.size>this.max){
            this.resize(this.container.length*4);
        }
        entry.next=temp;


    }
    /**
     *将指定的结点temp添加到指定的hash表table当中
     * 添加时判断该结点是否已经存在
     * 如果已经存在，返回false
     * 添加成功返回true
     * @param temp
     * @param table
     * @return
     */
    private boolean setEntry(Entry temp,Entry[] table){
        int index=this.indexFor(temp.hash ,table.length);//生成唯一的数组下标
        Entry e=table[index];
        if(e!=null){

            while(e!=null){
                //判断是否能增加进去
                if(((temp.key==e.key)||(temp.key.equals(e.key)))&&( (e.hash==temp.hash))&&( (e.value== temp.value)||(e.value.equals(temp.value)))  ){


                    return false;//不能添加，重复了


                }else if ((temp.key!=e.key)||(!temp.key.equals(e.key))){
                    if(e.next==null){
                       break;
                    }

                }

                e=e.next;//串到最后的节点
            }
            //此时已经找到了最后的节点,我们需要把新节点加到此链中的末尾

            this.addEntry2Last(e,temp);


        }//else{//当前此index所指的元素不存在
        //当前此index所指的元素不存在
        this.setFirstEntry(temp,index,table);//此新节点进入容器

        return true;

    }
    /**
     * 将指定结点temp，添加到指定的hash表table的指定下标index中
     * @param temp:新节点
     * @param index：新节点所在的索引
     * @param table：老容器
     */
    private void setFirstEntry(Entry temp, int index, Entry[] table) {
        if(size>max){
            resize(table.length*4);//扩容，
            
            
        }
        table[index]=temp;//把新节点送入到老容器里面
        temp.next=null;
        
    }

    /**
     *
     * @param newSize:新开辟容量的大小
     */
    private void resize(int newSize) {
        Entry [] newC=new Entry[newSize];//新开辟容器
        this.max=(int)(newSize*this.LOAD_FACTOR);
        for(int j=0;j<this.container.length;j++){

            Entry temp=this.container[j];//得到每个老容器的节点

            while(temp!=null){

                newC[j]=temp;

                temp=temp.next;
            }
        }
        this.container=newC;

    }

    /**
     *
     * @param k:键，int
     * @param v，String
     * @return：是否put成功
     */
    public boolean put(Integer k, String v) {
        int hashCode=k.hashCode();
        Entry entry=new Entry(k,v,hashCode);
        if(this.setEntry(entry,container)){
            this.size++;
        }else{

            return false;
        }
        return true;
    }

}
