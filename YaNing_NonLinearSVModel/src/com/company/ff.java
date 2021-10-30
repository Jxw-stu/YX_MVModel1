package com.company;

import Feedback.Fill_function;
import Feedback.NonLinearMVModel_1;
import Feedback.Tools;
//import matlab_gsztfb.ztfb;
//import local_matlab.fmincon;
//import optimal_matlab.FillFunction;
//import com.mathworks.toolbox.javabuilder.MWException;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ff {
//    public static void main(String[] args) throws MWException, IOException {
    public static void main(String[] args)  {
        Tools myTools = new Tools();
        String str = "";

        int n =1;
        double max=10;
        double min=0;
        double initx [] =new double[n];

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
        double init_value[]={0.5};


        double varsMinValue[] ;//变量最小值
        double varsMaxValue[];//变量最大值
        varsMinValue = new double[n];
        varsMaxValue = new double[n];
        double areaMin[] = new double[2];//块最小值
        double areaMax[] = new double[2];//块最大值
        double u[] = new double[100];

        double optimal[] = new double[1];
        long startTime=System.currentTimeMillis();   //获取开始时间

        double inx[] = new double[n];
        double tempX[] =new double[n];
        //double xk1[]=new double[n];
        double c[] =new double[n];
        double[] te = new double[n];
        int p=1;


        for(int i =0; i<n;i++){
            varsMaxValue[i] =max;
            varsMinValue[i] =min;
            //initx[i] =init_value;
        }

        NonLinearMVModel_1 NoLinearMVMoel = new NonLinearMVModel_1();
        Fill_function fill = new Fill_function();
        NoLinearMVMoel.SetMaxMinValue(n,varsMinValue, varsMaxValue);
        double a[] = NoLinearMVMoel.GetGlobalOptimal(n,init_value,max,min);
        System.out.println("xk的函数值是"+NoLinearMVMoel.GetObjectiveValue(a));


        for( int i =0;i<n;i++) {
            System.out.println("X" + i + " = " + a[i] + " ");
        }

        System.out.println("f(x) =  "  +  NoLinearMVMoel.GetObjectiveValue(a));

     /*   double xk1[]=NoLinearMVMoel.GetGlobalOptimal(n,tempX,max,min);
        //System.out.println("xk1的函数值是"+NoLinearMVMoel.GetObjectiveValue(xk1));
        int m=3;
        int io1=0;
        int io2=0;
        int k=1;
        double xx[][]= new double[m][n];

        //xkk[]局部最优解矩阵
        double xkk[][]= new double[1][n];
        for (int i=0;i<n;i++){
            xkk[0][i] = xk[i];
        }System.out.println("44444");
        //xxk[]初始点矩阵
        double xxk[][]= new double[1][n];
        for (int i=0;i<n;i++){
            xxk[0][i] = initx[i];
        }System.out.println("33333");

        int i=0;
        while ( p<=m){
            if (k>m){
                break;
            }
            if (io1==0) {
                //int count=1;
                i = 1;
                double sum = 0;
                for (i = 0; i < n; i++) {
                    sum += Math.pow(initx[i] - xk[i], 2);
                }
                double t = Math.sqrt(sum);

                double deta_k = t / Math.pow(2, k);

                double D[][] = new double[n][n];
                for (i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i == j) {
                            D[i][j] = deta_k;
                        }
                    }
                }
                System.out.println("2222");

                ztfb cc = new ztfb();
                Object[] d = cc.mnd(1, n, xk[0], D, m);
                BufferedReader reader = new BufferedReader(new FileReader("gsfb.csv"));//文件名
                String line = null;
                int index = 0;
                //String tempX[] =new double[30];
                //读取每行，直到为空
                while ((line = reader.readLine()) != null) {
                    String item[] = line.split(",");
                    //System.out.println("i:"+i);
                    if (index == p) { //读取指定行
                        for (i = 0; i < n; i++) {
                            te[i] = Double.valueOf(line.split(",")[i]);
                            System.out.println(line.split(",")[i]);
                        }
                    }index++;
                }
            }
            if (io2==0){
                    tempX = te;
            }
            double f1 = NoLinearMVMoel.GetObjectiveValue(tempX);
            System.out.println("f1:"+f1);
            double f2 = NoLinearMVMoel.GetObjectiveValue(xkk[0]);
            System.out.println("f2:"+f2);
            if (f1>f2){
                FillFunction tt=new FillFunction();
                Object[] d1 = tt.gof1(1,xk,1,n,tempX[1],varsMinValue[0],varsMaxValue[0]);
                double[] datas2 = new double[n];
                File file = new File("output.txt");
                FileInputStream inn = new FileInputStream(file);
                BufferedReader brr = new BufferedReader(new InputStreamReader(inn));
                String line = null;
                //按行读取
                int gt=0;
                while((line = brr.readLine()) != null){
                   // int g=0;
                    String a=line;
                    datas2[gt] = Double.valueOf(a);
                    gt++;
                }//g++;
                tempX =datas2;
                System.out.println(NoLinearMVMoel.GetObjectiveValue(tempX));

              //tempX = fill.GetGlobalOptimal(n,tempX,varsMaxValue,varsMinValue,xk,tempX);
                if (NoLinearMVMoel.GetObjectiveValue(tempX)>f2){
                    //i=i+1;
                    p=p+1;
                    io1=1;
                    io2=0;
                    System.out.println("1:"+i);
                }else {

                    //i=i+1;
                    io1=1;
                    io2=1;
                }
            }else {
                xk1=NoLinearMVMoel.GetGlobalOptimal(n,tempX,max,min);
                double f_final =NoLinearMVMoel.GetObjectiveValue(xk1);
                if(f_final<f2){
                        k=k+1;
                        for (i=0;i<n;i++){
                            xkk[0][i] = xk1[i];
                        }
                        for (i=0;i<n;i++){
                            xxk[0][i] = tempX[i];
                        }
                        xk=xk1;
                        io1=0;
                        io2=0;
                        if (k>5){
                            break;
                        }

                }else{
                    //count=count+1;
                    p=p+1;
                    io1=1;
                    io2=0;
                    System.out.println("3:"+i);
                }
            }
        }//System.out.println(i);*/

      /*  for( int i =0;i<n;i++) {
            System.out.println("X" + i + " = " + xk[i] + " ");
        }

        System.out.println("f(x) =  "  +  NoLinearMVMoel.GetObjectiveValue(xk));*/

        long endTime=System.currentTimeMillis(); //获取结束时间

        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

    }
}
/*  //高斯分布随机数
        double sum1=0;
        double sum2=0;
        double sum3=0;
        for (i=0;i<n;i++) {
        sum1+=initx[i];
        }
        double mean1 = sum1/n;
        for(i=0;i<n;i++){
        for(int j=0;j<n;j++){
        sum2 +=D[i][j];
        double mean2=sum2/n*n;
        sum3 +=Math.pow(D[i][j]-mean2,2);
        }
        }System.out.println("1111111");
        double fc=sum3/n-1;
        for ( int j = 0 ; j < m; j++){
        for ( i = 0 ; i < n ; i++){
        Random r = new Random();
        double num=r.nextGaussian() * Math.sqrt(fc) + mean1;
        xx[j][i] = num;
        //System.out.println(num);
        }
        }System.out.println("*******"+i);*/