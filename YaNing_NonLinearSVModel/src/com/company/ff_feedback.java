package com.company;

import Feedback.Fill_function;
import Feedback.NonLinearMVModel_1;
import Feedback.Tools;
//import matlab_gsztfb.ztfb;
//import local_matlab.fmincon;
//import optimal_matlab.FillFunction;
//import com.mathworks.toolbox.javabuilder.MWException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ff_feedback {
//    public static void main(String[] args) throws MWException, IOException {
    public static void main(String[] args)  {
        Tools myTools = new Tools();
        String str = "";
        String res="";
        //时间
        double ave_time=0;
        double ave_value=0;
        double total_value=0;
        //初始值矩阵
        double[] init_matrix={-110,-100.11,-99.99,55,98.7,100.9,150};

        //矩阵大小
        double init_matrix_n=init_matrix.length;
        //初始值设置
        double init_value=0;
        //模型名字
        String model_name="f1";
        //边界
        //double max=Math.PI;
        double max=200;
        double min=0;
        //变量个数
        int n =30;
        int dimension=n;


        /*double csz_value=-110;
        double csz [] =new double[n];
        for(int i =0; i<n;i++){
            csz[i]=csz_value;
        }


        fmincon ff=new fmincon();
        Object[] d2 = ff.gof(1,10,n,csz,min,max);
        double[] datas = new double[n];
        File f = new File("global_value.txt");
        FileInputStream in = new FileInputStream(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String lline = null;
        //按行读取
        int g=0;
        while((lline = br.readLine()) != null){
            // int g=0;
            String a=lline;
            datas[g] = Double.valueOf(a);
            g++;
        }//g++;*/
        //double init_value[]={-2.65};


        double varsMinValue[] ;//变量最小值
        double varsMaxValue[];//变量最大值
        varsMinValue = new double[n];
        varsMaxValue = new double[n];
        double areaMin[] = new double[2];//块最小值
        double areaMax[] = new double[2];//块最大值
        double initx [] =new double[n];

        //long startTime=System.currentTimeMillis();   //获取开始时间

        for(int j=0;j<init_matrix_n;j++){
            long startTime=System.currentTimeMillis();   //获取开始时间
            init_value=init_matrix[j];
            for(int i =0; i<n;i++){
                varsMaxValue[i] =max;
                varsMinValue[i] =min;
                initx[i] =init_value;
            }


            NonLinearMVModel_1 NoLinearMVMoel = new NonLinearMVModel_1();
            NoLinearMVMoel.SetMaxMinValue(n,varsMinValue, varsMaxValue);
            double a[] = NoLinearMVMoel.GetGlobalOptimal(n,initx,max,min);

            // 初始化 Date 对象
            Date date = new Date();
            SimpleDateFormat formate1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            res+=String.format("%-20s||  ","时间： "+formate1.format(date));
            res+=String.format("%-10s||  ","模型： "+model_name);
            res+=String.format("%-10s||  ","维数： "+dimension);
            res+=String.format("%-20s||  ","初始值:  "+init_value);

            for( int i =0;i<n;i++) {
                System.out.println("X" + i + " = " + a[i] + " ");
            }
            String value="f(x) =  " +NoLinearMVMoel.GetObjectiveValue(a);
            System.out.println(value);
            for( int i =0;i<n;i++) {
            res+=String.format("%-30s||  ","X" + i + " = "  +a[i]);
            }
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
            fos = new FileWriter("finaliy_result.txt", true); //就是这个构造方法的第二个参数，为true则是追加内容
            BufferedWriter bos = new BufferedWriter(fos);
            bos.write(res);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}