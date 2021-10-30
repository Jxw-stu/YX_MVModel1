package com.company;

import Feedback.NonLinearMVModel;
import Feedback.NonLinearMVModel_1;
import Feedback.Tools;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main_Block {
        public static void main(String[] args) {
                Tools myTools = new Tools();
                String str = "";
                String res="";
                //时间
                double ave_time=0;
                double ave_value=0;
                double total_value=0;
                //初始值矩阵
                double[] init_matrix={-11,-5.04,-4.85,3.42,4.29,5.06,16};

                //矩阵大小
                double init_matrix_n=init_matrix.length;

                //初始值设置
                double init_value=0;
                //模型名字
                String model_name="f34";
                //边界
                //double max=Math.PI;
                double max=30*2;
                double min=0;
                //变量个数
                int n =30;
                int dimension=n;


                double varsMinValue[] ;//变量最小值
                double varsMaxValue[];//变量最大值
                varsMinValue = new double[n];
                varsMaxValue = new double[n];
                double areaMin[] = new double[2];//块最小值
                double areaMax[] = new double[2];//块最大值
                double initx [] =new double[n] ;
                double optimal[] = new double[2];



                for(int j=0;j<init_matrix_n;j++){
                        long startTime=System.currentTimeMillis();   //获取开始时间
                        init_value=init_matrix[j];
                        for(int i =0; i<n;i++){
                                //边界，默认为约束
                                varsMaxValue[i] =max;
                                varsMinValue[i] =min;
                                initx[i] =init_value;
                        }

                /*initx[0] =11.873;
                initx[1] =24.626;
                initx[2] =23.501;*/
                        // initx[3] =46.7256087449728;


                /*initx[0] =75.729;
                initx[1] =174.01;
                initx[2] =150.26;
                varsMaxValue[0] = 210;
                varsMinValue[0] = 75.729;
                varsMaxValue[1] = 325;
                varsMinValue[1] = 174.01;
                varsMaxValue[2] = 315;
                varsMinValue[2] = 150.23;*/
               /* varsMaxValue[3] = 50;
                varsMinValue[3] = 0;*/

//                initx[0] =0.5119999999999999;
//                initx[1] =0.2632580261593341;
//                initx[2] =0.5189060642092747;

                /*initx[0] =1.3;
                initx[1] =213;
                initx[2] =55;*/

              /*  initx[0] =0.4510204081632654;
                initx[1] =0.42613636363636365;
                initx[2] =0.37792642140468224;
                initx[3] =0.40355555555555556;*/






                        NonLinearMVModel_1 NoLinearMVMoel = new NonLinearMVModel_1();
                        NoLinearMVMoel.SetMaxMinValue(n,varsMinValue, varsMaxValue);
                        //NoLinearMVMoel.GetArea(2,0,1.09,varsMinValue,varsMaxValue);


   /*             System.out.println(NoLinearMVMoel.FindInitSolution(initx,2,0,Min,Max));
                System.out.println(initx[0]+" "+initx[1]);*/
        /*        NoLinearMVMoel.FindInitSolution(initx,2,0,varsMinValue,varsMaxValue);
                System.out.println(initx[0]+" "+initx[1]);*/

                        //NoLinearMVMoel.GetAreaNum();
        /*        NoLinearMVMoel.FindInitSolution(initx,2,0,varsMinValue,varsMaxValue);
                NoLinearMVMoel.GetOptimalInArea(initx,optimal,areaMin,areaMax);
                System.out.println(optimal[0] + " " + optimal[1]);
                System.out.println(initx[0]+" "+initx[1]);*/
                        //NoLinearMVMoel.GetByOneVariableOptimal(initx,optimal,0,areaMin,areaMax);
                        //NoLinearMVMoel.ReadAreatest();
                        //NoLinearMVMoel.GetGlobalOptimal(2,initx);

                        double a[] = NoLinearMVMoel.GetGlobalOptimal(n,initx,max,min);



                        // 初始化 Date 对象
                        Date date = new Date();
                        SimpleDateFormat formate1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        res+=String.format("%-20s||  ","时间： "+formate1.format(date));
                        res+=String.format("%-10s||  ","模型： "+model_name);
                        res+=String.format("%-10s||  ","维数： "+dimension);
                        res+=String.format("%-20s||  ","初始值:  "+init_value);

                        for(int i =0;i<n;i++) {
                                System.out.println("X" + i + ": " + a[i] + " ");
                        }
                        String value="f(x) =  " +NoLinearMVMoel.GetObjectiveValue(a);
                        System.out.println(value);
                        res+=String.format("%-30s||  ",value);

                        double value1=NoLinearMVMoel.GetObjectiveValue(a);
                        total_value+=value1;

                        long endTime=System.currentTimeMillis(); //获取结束时间
                        double time=endTime-startTime;
                        ave_time+=time;
                        System.out.println("程序运行时间： "+(time)+"ms");
                        res+="程序运行时间： "+(time)+"ms"+"\n";

                }
                ave_value=total_value/init_matrix_n;
                ave_time=ave_time/init_matrix_n;
                res+="函数平均值： "+ave_value+"\n";
                res+="程序运行平均时间： "+(ave_time/1000)+"秒"+"\n";

                try {
                        FileWriter fos;
                        fos = new FileWriter("result.txt", true); //就是这个构造方法的第二个参数，为true则是追加内容
                        BufferedWriter bos = new BufferedWriter(fos);
                        bos.write(res);
                        bos.flush();
                        bos.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }


                //double b[] ={20.6,200};
                //myTools.SaveFile("a1.txt", str, false);
                //myTools.SaveFile("a.txt", str, false);
/*                System.out.println(89.12073600614355-100*Math.sin(0.1*16-0.5) );
                System.out.println((50/(16+0.1))-40-(89.12073600614355));
                System.out.println("f(x) =  "  +  NoLinearMVMoel.GetObjectiveValue(b));*/
        }
}
