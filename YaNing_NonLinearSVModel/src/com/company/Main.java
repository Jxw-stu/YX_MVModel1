package com.company;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;//.swing.JOptionPane;
import Feedback.NonLinearSVModel;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.omg.CORBA.SetOverrideType;

public class Main {
    public static void main(String[] args)
    {
//        try{
//            String DATAFile;
//            DATAFile ="input.txt";
//            double ret[]={0};
//            double blancep[] = {0};
//            double utx[]={0};
//        NonLinearSVModel nonLinearSVModel = new NonLinearSVModel();
//            Tools myDowith = new Tools();
//            String data = "";
//            double max=200,min=0;
//            double ran2 = (double) (Math.random()*(max-min)+min);
//            int m=50;
//            for(int i=1;i<=m;i++){
//                double r = (double) (Math.random()*(max-min)+min);
//                data += r + "\r\n";
//            }
//            String fileName = "a.txt";
//            myDowith.SaveFile(fileName, data, true);
//            data = "";
//            System.out.println("It has already succeeded in file.");
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }


        double ret[]={0};
        double blancep[] = {0};
        double utx[]={0};
        //NonLinearSVModel mySVM = new NonLinearSVModel();
        //mySVM.SetInitValue(100);
        //System.out.println("hello");
        long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��
        NonLinearSVModel nonLinearSVModel = new NonLinearSVModel();
        nonLinearSVModel.SetMaxMinValue(0,100);
        //System.out.println(nonLinearSVModel.GetBalanceDot(1.01,1.00,0,blancep,utx));
        //System.out.println(blancep[0]);
        System.out.println("ȫ�����Ž�Ϊ:"+nonLinearSVModel.GetGlobalOptimal(98));
        System.out.println("f(x)Ϊ��"+nonLinearSVModel.GetObjectiveValue(nonLinearSVModel.GetGlobalOptimal(98)));

        //return;
        //��ȡ����ʱ��
        long endTime=System.currentTimeMillis();
        System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms");
    }

}
