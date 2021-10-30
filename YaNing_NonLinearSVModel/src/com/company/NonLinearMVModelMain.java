package com.company;

import Feedback.NonLinearMVModel;

public class NonLinearMVModelMain {
    public static void main(String[] args) {
        double varsMinValue[] ;//变量最小值
        double varsMaxValue[];//变量最大值
        varsMinValue = new double[]{0, 0,0,0};
        varsMaxValue = new double[]{500,0.1,0.2,0.5};
        double initx[] ={400,0.05,0.1,0.3};
        NonLinearMVModel NoLinearMVMoel = new NonLinearMVModel();
        NoLinearMVMoel.SetMaxMinValue(4,varsMinValue, varsMaxValue);
        //NoLinearMVMoel.SetInitValue(2,initx);
        double a[] = NoLinearMVMoel.GetGlobalOptimal(4,initx);
       // System.out.println("全局最优解为X:" + a[0] + " Y:" + a[1]);
        System.out.println("f(x) =  "  +  NoLinearMVMoel.GetObjectiveValue(a));
       // System.out.println(  -2*1.0374999999999246-3*1.3083333333337166+6);
       // System.out.println(  -2*0.7733149486709074-3*1.4847900342193951+6);
        //System.out.println(  -2*0.9005000000000003-3*1.4+6);
        //System.out.println(  -2*0.7728149486709077-3*1.4847900342193951+6);
        /*System.out.println(Math.pow(3,2)+Math.pow(4,2)-3*4-2*3-5*4);// f(x,y) = x^2+y^2-xy-2x-5y
        System.out.println(Math.pow(2.2274999999999934,2)+Math.pow(3.6170507812502106,2)-2.2274999999999934*3.6170507812502106-2*2.2274999999999934-5*3.6170507812502106);*/
    }
}
