package com.weikun.A;
import java.util.Stack;

/**
 * Created by Administrator on 2016/9/13.
 */
//最小栈
public class C {

    private Stack<Integer> stackData;//数据栈
    private Stack<Integer> stackMin;//最小栈
    public C(){
        this.stackData=new Stack<Integer>();
        this.stackMin=new Stack<Integer>();

    }
    public void push(Integer data){

        if(this.stackMin.isEmpty()){

            this.stackMin.push(data);
        }else if(data<this.getMin()){
            this.stackMin.push(data);
        }

        this.stackData.push(data);
    }
    //看最先栈中的栈顶元素，因为他可能是最小的
    public Integer getMin(){
        return this.stackMin.peek();

    }

    public static void main(String[] args) {
        C c=new C();
        c.push(100);
        c.push(200);
        c.push(300);
        System.out.print(c.getMin());


    }
}
