package com.company;
import Feedback.NonLinearMVModel_1;
import Feedback.Tools;
//import com.mathworks.toolbox.javabuilder.MWException;

import java.io.File;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class test {
        public static void main(String[] args) {


                double a[] = {100.00000382092614, 100.00000234185006, 100.00000028711521, 100.0000000810428, 100.00001190213361,
                        100.00000564185335, 100.00000059011523, 100.00000002092663, 100.00000518369525, 100.00000894185423,
                        100.00001212565188, 100.00000003551763, 100.00002008370188, 100.00000978781381, 100.00000084524751,
                        100.00000069569569, 100.00001249592546, 100.000000002023, 100.00000068023171, 100.00000145687517,
                        100.0000013451773, 100.00002248370237, 100.00000152092632, 100.00002169152025, 100.00000196046345,
                        100.000004617884, 100.0000000037877, 100.00000152092679, 100.00000000281469, 100.00000007849343};
                double b[]={901.0000500292856, 1800.0, 1800.0, 1800.0, 1800.0,
                        1800.0, 1800.0, 1800.0, 1800.0 ,1800.0 ,1800.0, 1800.0,
                        1800.0, 1800.0 ,1800.0, 1800.0, 1800.0, 1800.0 ,1800.0 ,
                        1800.0, 1800.0, 1800.0, 1800.0 ,1800.0, 1800.0 ,1800.0, 1800.0, 1800.0 ,1800.0 ,1800.0};

                NonLinearMVModel_1 NoLinearMVMoel = new NonLinearMVModel_1();
               double q= NoLinearMVMoel.GetObjectiveValue(b);
                System.out.println(q);
        }
        /*public static void main(String[] args) throws Exception{

                BufferedReader reader = new BufferedReader(new FileReader("gsfb.csv"));//换成你的文件名和地址


//                reader.readLine();//第一行信息，为标题信息，不用。如果不需要，注释掉
                String line = null;
                int index=0;
                //String tempX[] =new double[30];
                 double a[] =new double[30];
                //读取每行，直到为空
                while((line=reader.readLine())!=null){
                        String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                        if(index==1){ //读取指定行
                                //System.out.println(line);
                                for(int i=0;i<30;i++){
                                        //String tempX=line.split(",")[i];
                                         a [i] =Double.valueOf(line.split(",")[i]);

                                        System.out.println(line.split(",")[i]);

                                }
                        }
                        index++;
                }
        }*/

       /* public static void main(String[] args) throws MWException, IOException {
                double[] datas = new double[30];
                double[] tempX = new double[30];
                File f = new File("test.xls");
                FileInputStream in = new FileInputStream(f);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line = null;
                //按行读取
                while((line = br.readLine()) != null){
                        int g=0;
                        String a=line;
                        datas[g] = Double.valueOf(a);
                        g++;
                }
                tempX =datas;
        }*/
//读取test.xls 或者 gsfb.csv表格得第k行数据存入temp数组
//       public static void main(String[] args) throws MWException, IOException {
//                double temp[] =new double[30];
//               String filePath = "gsfb.csv";
//                try {
//                        // 创建CSV读对象
//                        CsvReader csvReader = new CsvReader(filePath);
//                        // 读表头
//                        csvReader.readHeaders();
//                        while (csvReader.readRecord()){
//                                // 读一整行
//                                int k=0;
//                               temp= csvReader.getRawRecord(k);
//                                System.out.println(csvReader.getRawRecord());
//                                for (int i=0;i<30;i++){
//                                        System.out.println();
//                                }
//                                // 读这行的某一列
//                               // System.out.println(csvReader.get("Link"));
//                        }
//                } catch (IOException e) {
//                        e.printStackTrace();
//                }
//        }
      /* public void  readeCsv(){
               try {
                       ArrayList<String[]> csvList = new ArrayList<String[]>(); //用来保存数据
                       String csvFilePath = "c:/test.csv";
                       CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("SJIS"));    //一般用这编码读就可以了
                       reader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
                       while (reader.readRecord()) { //逐行读入除表头的数据
                               csvList.add(reader.getValues());
                       }
                       reader.close();
                       for (int row = 0; row < csvList.size(); row++) {
                               String cell = csvList.get(row)[0]; //取得第row行第0列的数据
                               System.out.println(cell);
                       }
               } catch (Exception ex) {
                       System.out.println(ex);
               }
       }*/
}









                /*System.out.println(2.0547299342372436*Math.sin(1)+5417.29118788778);
                System.out.println(2.0547299342372436*Math.sin(2)+5417.29118788778);
                System.out.println(2.0547299342372436*Math.sin(3)+5417.29118788778);
                System.out.println(2.0547299342372436*Math.sin(4)+5417.29118788778);
                System.out.println(2.0547299342372436*Math.sin(5)+5417.29118788778);
                System.out.println(2.0547299342372436*Math.sin(6)+5417.29118788778);
                System.out.println(2.0547299342372436*Math.sin(7)+5417.29118788778);
                System.out.println(2.0547299342372436*Math.sin(8)+5417.29118788778);*/
                /*test test1=new test();
                double result=test1.GetObjectiveValue_ds();
                //double a=-7.30997086e-01;
                //ystem.out.println(a);
                //System.out.println(result);
                //System.out.println(test1.fg1(result,3786.3,1697.75));
                //System.out.println(test1.dfg1(result,15381.0,11560.5));
                System.out.println(test1.dfg1(result,15.051,9.275));
               // System.out.println(test1.dfg1(0.48919553341930994,15.051,9.275));
                System.out.println(test1.dfg1(9.285626027856306E-17  ,1.5,1));
                System.out.println(test1.dfg1(1.0 ,266.64,199.36));
                System.out.println(test1.dfg1(0.9088935642188245  ,58.41,41.59));
        }
        *//* 归一化[0,1]：b=(a-amin)/(amax-amin)
        反归一化[0,1]：a=b*(amax-amin)+amin
        归一化[-1,1]：b=2*(a-amin)/(amax-amin)-1
        反归一化[-1,1]：a=((b+1)*(amax-amin)/2)+amin
        均值归一化: a=(x-ave)/(max-min);*//*

                 *//*数据归一化*//*
        public double g1(double a,double amax,double amin){
                double b=2*((a-amin)/(amax-amin))-1;
                //double b=1.8668*((a-amin)/(amax-amin))-1;
                return b;
        }

        public double dg1(double a,double amax,double amin){
                double b=(0.6-0.2)*((a-amin)/(amax-amin))+0.2;
                return b;
        }

        *//*数据反归一化*//*
        public double fg1(double b,double amax,double amin){
                double a=((b+1)*(amax-amin)/2)+amin;
                //double a=(b*(amax-amin))+amin;
                return a;

        }

        public double dfg1(double b,double amax,double amin){
                double a=((b-0.2)*(amax-amin)/(0.6-0.2))+amin;
                return a;
        }

        *//*大豆原始论文实验*//*
                 *//*double x1=g1(47.8,47.8,21.8);
        double x2=g1(41.14764,95,12.6);
        double x3=g1(82.76652,122.6,21);
        double x4=g1(16.03752,22,8);
        double x[]={x1,x2,x3,x4};
        double w1[][]={{-1.6310,1.1011,0.5969,1.8985},
                {1.3209,-1.4885,-1.6763,-0.6473},
                {-0.0335,1.1340,-2.2739,1.1957},
                {1.7913,-1.9753,0.0904,-1.2257},
                {1.0476,1.2616,-0.2882,2.1977},
                {-0.5819,-0.6451,1.9866,1.6458},
                {-1.7725,-0.2343,1.9658,0.6343},
                {2.0742,0.7114,-0.3371,-1.5799},
                {-1.0378,-0.8157,1.8880,1.4529},
                {2.4707,0.6839,-1.0109,0.1995},
                {1.8822,-0.4079,-1.5593,-1.1304},
                {-0.1239,-0.4302,0.2370,-2.6744},
                {-1.3504,-0.6905,-1.9294,1.0509},
                {-0.9531,-1.7629,-1.6484,0.9978},
                {1.2606,-1.6664,1.7724,0.0967}};
        double w2[]={0.2661,-0.3078,0.4609,0.8309,-0.2396,0.4325,0.0535,0.3316,-0.0641,-0.4618,0.0115,-0.7142,0.3105,-0.4724,-0.0900};
        double b1[]={2.7248,-2.4582,1.8240,
                -1.7894,-1.2047,0.9728,
                0.2339,0.0424,-0.3614,
                0.8517,0.9173,-2.0029,
                -2.0102,-2.2651,2.7889};
        double b2=-1.0444;*//*

                 *//*大豆复现论文实验*//*
                 *//* double x1=g1(47.8,47.8,21.8);
        double x2=g1(41.14764,95,12.6);
        double x3=g1(82.76652,122.6,21);
        double x4=g1(16.03752,22,8);
        double x[]={x1,x2,x3,x4};
        double w1[][]={{-0.19172828, 0.04039612,0.2576459,-0.14752804},
                {2.416382, 2.7055576,-3.4114454,0.7793055},
                {-0.6826618,-1.4551815,1.3565991,1.8069984 },
                {0.23285142,-0.39614597,0.03151084 ,-0.5076294},
                {0.7833894,0.22168444,0.18607126,-0.5210566},
                {-0.15033197,-0.13696744,-0.31124517,0.07931323},
                {0.15093549,0.01412933,-0.28670794,0.0504239},
                {0.32419768,0.08582721,-0.91453886,-0.1205467},
                {-0.06239459, 0.25299397,-0.06340943,0.11571692},
                {-0.9070884,-1.7198782,1.7794176,-1.5460403},
                {-0.30092448,1.0823158,0.33163264,0.01029441},
                {-0.3043171,0.02496256,0.30043828,-0.22459702},
                { -0.07653937, 0.09441992,-0.12194654,0.01829894},
                {0.24970558,-0.05430865,-0.12670383,0.03872615},
                { 0.1312738,-0.00921015,-0.04610264,0.03866626}};
        double w2[]={0.21809281,-1.5104722,-0.59102684,0.2601074,0.5451427,-0.00591632,-0.20286179,-0.6280802,-0.05296968,
                -2.0075748,-0.764381,0.34172556,-0.01706021,-0.18390864,-0.09292072};
        double b1[]={0.04808838,-1.0288575,0.90264153,0.04011114,0.9158201,0.03901561,
                -0.01813501,-0.38395742,-0.02744733,0.8587963,0.52429163,0.16357473,
                -0.01899995,-0.02168346,-0.01532079};
        double b2=0.0661879;*//*

                 *//* 玉米实验*//*
                 *//*   double x1=dg1(9.32,10.78,6.86);
        double x2=dg1(139.5,216,40);
        double x3=dg1(85.4,151.8,32.2);
        double x4=dg1(70.8,115,25);
        double x[]={x1,x2,x3,x4};
        double w1[][]={{-2.6629,5.9471,0.2134,-9.1766},
                {-2.8220,-1.5566,-1.1020,-4.9485},
                {-0.3416,-16.6746,-3.8079,-6.7462},
                {1.3100,0.5167,-10.1131,7.0357},
                {3.8324,2.9777,-7.7359,4.7049},
                {-17.4792,3.5559,20.7811,-11.3949},
                {-0.2762,1.3379,-1.1120,1.8320}};
        double w2[]={11.3948,-19.3894,-5.6696,6.8314,-9.4303,-2.4501,2.8836};
        double b1[]={-1.5704,-2.3435,3.9201,-1.2868,0.3434,0.0328,-0.2324};
        double b2=-0.8123;*//*

                 *//*功耗实验*//*
        double x1=dg1(1.39,1.5,1);
        double x2=dg1(210,266.64,199.36);
        double x3=dg1(55,58.41,41.59);
        double x[]={x1,x2,x3};
        double w1[][]= {{-1.3047, -11.0498, 2.3569},
                {-4.2216,1.8650,-3.9918},
                {0.5543,-9.6378,7.2247},
                {-5.8672,1.0995,1.4527},
                {-4.8335,-1.5613,-5.2040}};
        double w2[]={-11.2921,7.6752,10.9691,-3.3505,12.7070};
        double b1[]={-4.4035,-1.4455,-0.3823,-2.0287,-1.12346};
        double b2=-0.3727;

        *//*单极性sigmoid*//*
        public double sigmoid_ds(double zi) {
                double fenzi=1;
                double fenmu=1+Math.pow(Math.E, (-zi));
                return Math.pow(fenmu,(-1));
                //return fenzi/fenmu;
        }
        *//*双极性sigmoid*//*
        public double sigmoid(double zi) {
                double fenzi=(Math.pow(Math.E, zi)-Math.pow(Math.E, -zi));
                double fenmu=(Math.pow(Math.E, zi)+Math.pow(Math.E, -zi));
                return fenzi/fenmu;
        }

        *//*大豆ObjectiveValue*//*
                 *//* public double GetObjectiveValue( )
        {
                double m = 15;
                double z1[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                for (int i = 0; i < m; i++) {
                        z1[i] = x[0] * w1[i][0] + x[1] * w1[i][1] + x[2] * w1[i][2] + x[3] * w1[i][3] + b1[i]; //  X*wij+b1
                        z1[i] = sigmoid(z1[i]);
                }
                double z2 = 0;
                for (int i = 0; i < m; i++) {
                        z2 += z1[i] * w2[i];
                }
                double result_value = z2 + b2;
                //return -result_value;
                return result_value;

        }*//*

                 *//*玉米ObjectiveValue*//*
                 *//* public double GetObjectiveValue_ds( )
        {
                double m = 7;
                System.out.println(x[0]+" "+x[1]+" "+x[2]+" "+x[3]);
                //System.out.println(sigmoid_ds(-10));
                double z1[] = {0, 0, 0, 0, 0, 0, 0};
                for (int i = 0; i < m; i++) {
                        z1[i] = x[0] * w1[i][0] + x[1] * w1[i][1] + x[2] * w1[i][2] + x[3] * w1[i][3] + b1[i]; //  X*wij+b1
                        //System.out.println("************");
                        //System.out.println(z1[i]);

                        z1[i] = sigmoid_ds(z1[i]);
                        //System.out.println(z1[i]);
                }
                System.out.println("--------------");
                double z2 = 0;
                for (int i = 0; i < m; i++) {
                        z2 += z1[i] * w2[i];
                        System.out.println(z1[i] * w2[i]);
                }
                System.out.println("--------------");
                System.out.println(z2+b2);
                double result_value = sigmoid_ds(z2 + b2);
                System.out.println("--------------");
                System.out.println(result_value);
                //return -result_value;
                return result_value;
        }*//*
                 *//*功耗ObjectiveValue*//*
        public double GetObjectiveValue_ds( )
        {
                double m = 5;
                System.out.println(x[0]+" "+x[1]+" "+x[2]);
                //System.out.println(sigmoid_ds(-10));
                double z1[] = {0, 0, 0, 0, 0};
                for (int i = 0; i < m; i++) {
                        z1[i] = x[0] * w1[i][0] + x[1] * w1[i][1] + x[2] * w1[i][2]  + b1[i]; //  X*wij+b1
                        z1[i] = sigmoid_ds(z1[i]);
                }
                double z2 = 0;
                for (int i = 0; i < m; i++) {
                        z2 += z1[i] * w2[i];

                }
                double result_value = sigmoid_ds(z2 + b2);
                //return -result_value;
                return result_value;
        }

*/









