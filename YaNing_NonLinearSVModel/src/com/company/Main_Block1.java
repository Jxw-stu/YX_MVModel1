package com.company;

import Feedback.NonLinearMVModel_1;
import Feedback.Tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main_Block1 {
        public static void main(String[] args) {
                Tools myTools = new Tools();
                String str = "";

                //矩阵大小
                //double init_matrix_n=7;

                //初始值设置3AGXEJXFK9-eyJsaWNlbnNlSWQiOiIzQUdYRUpYRks5IiwibGljZW5zZWVOYW1lIjoiaHR0cHM6Ly96aGlsZS5pbyIsImFzc2lnbmVlTmFtZSI6IiIsImFzc2lnbmVlRW1haWwiOiIiLCJsaWNlbnNlUmVzdHJpY3Rpb24iOiIiLCJjaGVja0NvbmN1cnJlbnRVc2UiOmZhbHNlLCJwcm9kdWN0cyI6W3siY29kZSI6IklJIiwiZmFsbGJhY2tEYXRlIjoiMjA4OS0wNy0wNyIsInBhaWRVcFRvIjoiMjA4OS0wNy0wNyJ9LHsiY29kZSI6IkFDIiwiZmFsbGJhY2tEYXRlIjoiMjA4OS0wNy0wNyIsInBhaWRVcFRvIjoiMjA4OS0wNy0wNyJ9LHsiY29kZSI6IkRQTiIsImZhbGxiYWNrRGF0ZSI6IjIwODktMDctMDciLCJwYWlkVXBUbyI6IjIwODktMDctMDcifSx7ImNvZGUiOiJQUyIsImZhbGxiYWNrRGF0ZSI6IjIwODktMDctMDciLCJwYWlkVXBUbyI6IjIwODktMDctMDcifSx7ImNvZGUiOiJHTyIsImZhbGxiYWNrRGF0ZSI6IjIwODktMDctMDciLCJwYWlkVXBUbyI6IjIwODktMDctMDcifSx7ImNvZGUiOiJETSIsImZhbGxiYWNrRGF0ZSI6IjIwODktMDctMDciLCJwYWlkVXBUbyI6IjIwODktMDctMDcifSx7ImNvZGUiOiJDTCIsImZhbGxiYWNrRGF0ZSI6IjIwODktMDctMDciLCJwYWlkVXBUbyI6IjIwODktMDctMDcifSx7ImNvZGUiOiJSUzAiLCJmYWxsYmFja0RhdGUiOiIyMDg5LTA3LTA3IiwicGFpZFVwVG8iOiIyMDg5LTA3LTA3In0seyJjb2RlIjoiUkMiLCJmYWxsYmFja0RhdGUiOiIyMDg5LTA3LTA3IiwicGFpZFVwVG8iOiIyMDg5LTA3LTA3In0seyJjb2RlIjoiUkQiLCJmYWxsYmFja0RhdGUiOiIyMDg5LTA3LTA3IiwicGFpZFVwVG8iOiIyMDg5LTA3LTA3In0seyJjb2RlIjoiUEMiLCJmYWxsYmFja0RhdGUiOiIyMDg5LTA3LTA3IiwicGFpZFVwVG8iOiIyMDg5LTA3LTA3In0seyJjb2RlIjoiUk0iLCJmYWxsYmFja0RhdGUiOiIyMDg5LTA3LTA3IiwicGFpZFVwVG8iOiIyMDg5LTA3LTA3In0seyJjb2RlIjoiV1MiLCJmYWxsYmFja0RhdGUiOiIyMDg5LTA3LTA3IiwicGFpZFVwVG8iOiIyMDg5LTA3LTA3In0seyJjb2RlIjoiREIiLCJmYWxsYmFja0RhdGUiOiIyMDg5LTA3LTA3IiwicGFpZFVwVG8iOiIyMDg5LTA3LTA3In0seyJjb2RlIjoiREMiLCJmYWxsYmFja0RhdGUiOiIyMDg5LTA3LTA3IiwicGFpZFVwVG8iOiIyMDg5LTA3LTA3In0seyJjb2RlIjoiUlNVIiwiZmFsbGJhY2tEYXRlIjoiMjA4OS0wNy0wNyIsInBhaWRVcFRvIjoiMjA4OS0wNy0wNyJ9XSwiaGFzaCI6IjEyNzk2ODc3LzAiLCJncmFjZVBlcmlvZERheXMiOjcsImF1dG9Qcm9sb25nYXRlZCI6ZmFsc2UsImlzQXV0b1Byb2xvbmdhdGVkIjpmYWxzZX0=-WGTHs6XpDhr+uumvbwQPOdlxWnQwgnGaL4eRnlpGKApEEkJyYvNEuPWBSrQkPmVpim/8Sab6HV04Dw3IzkJT0yTc29sPEXBf69+7y6Jv718FaJu4MWfsAk/ZGtNIUOczUQ0iGKKnSSsfQ/3UoMv0q/yJcfvj+me5Zd/gfaisCCMUaGjB/lWIPpEPzblDtVJbRexB1MALrLCEoDv3ujcPAZ7xWb54DiZwjYhQvQ+CvpNNF2jeTku7lbm5v+BoDsdeRq7YBt9ANLUKPr2DahcaZ4gctpHZXhG96IyKx232jYq9jQrFDbQMtVr3E+GsCekMEWSD//dLT+HuZdc1sAIYrw==-MIIElTCCAn2gAwIBAgIBCTANBgkqhkiG9w0BAQsFADAYMRYwFAYDVQQDDA1KZXRQcm9maWxlIENBMB4XDTE4MTEwMTEyMjk0NloXDTIwMTEwMjEyMjk0NlowaDELMAkGA1UEBhMCQ1oxDjAMBgNVBAgMBU51c2xlMQ8wDQYDVQQHDAZQcmFndWUxGTAXBgNVBAoMEEpldEJyYWlucyBzLnIuby4xHTAbBgNVBAMMFHByb2QzeS1mcm9tLTIwMTgxMTAxMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5ndaik1GD0nyTdqkZgURQZGW+RGxCdBITPXIwpjhhaD0SXGa4XSZBEBoiPdY6XV6pOfUJeyfi9dXsY4MmT0D+sKoST3rSw96xaf9FXPvOjn4prMTdj3Ji3CyQrGWeQU2nzYqFrp1QYNLAbaViHRKuJrYHI6GCvqCbJe0LQ8qqUiVMA9wG/PQwScpNmTF9Kp2Iej+Z5OUxF33zzm+vg/nYV31HLF7fJUAplI/1nM+ZG8K+AXWgYKChtknl3sW9PCQa3a3imPL9GVToUNxc0wcuTil8mqveWcSQCHYxsIaUajWLpFzoO2AhK4mfYBSStAqEjoXRTuj17mo8Q6M2SHOcwIDAQABo4GZMIGWMAkGA1UdEwQCMAAwHQYDVR0OBBYEFGEpG9oZGcfLMGNBkY7SgHiMGgTcMEgGA1UdIwRBMD+AFKOetkhnQhI2Qb1t4Lm0oFKLl/GzoRykGjAYMRYwFAYDVQQDDA1KZXRQcm9maWxlIENBggkA0myxg7KDeeEwEwYDVR0lBAwwCgYIKwYBBQUHAwEwCwYDVR0PBAQDAgWgMA0GCSqGSIb3DQEBCwUAA4ICAQBonMu8oa3vmNAa4RQP8gPGlX3SQaA3WCRUAj6Zrlk8AesKV1YSkh5D2l+yUk6njysgzfr1bIR5xF8eup5xXc4/G7NtVYRSMvrd6rfQcHOyK5UFJLm+8utmyMIDrZOzLQuTsT8NxFpbCVCfV5wNRu4rChrCuArYVGaKbmp9ymkw1PU6+HoO5i2wU3ikTmRv8IRjrlSStyNzXpnPTwt7bja19ousk56r40SmlmC04GdDHErr0ei2UbjUua5kw71Qn9g02tL9fERI2sSRjQrvPbn9INwRWl5+k05mlKekbtbu2ev2woJFZK4WEXAd/GaAdeZZdumv8T2idDFL7cAirJwcrbfpawPeXr52oKTPnXfi0l5+g9Gnt/wfiXCrPElX6ycTR6iL3GC2VR4jTz6YatT4Ntz59/THOT7NJQhr6AyLkhhJCdkzE2cob/KouVp4ivV7Q3Fc6HX7eepHAAF/DpxwgOrg9smX6coXLgfp0b1RU2u/tUNID04rpNxTMueTtrT8WSskqvaJd3RH8r7cnRj6Y2hltkja82HlpDURDxDTRvv+krbwMr26SB/40BjpMUrDRCeKuiBahC0DCoU/4+ze1l94wVUhdkCfL0GpJrMSCDEK+XEurU18Hb7WT+ThXbkdl6VpFdHsRvqAnhR2g4b+Qzgidmuky5NUZVfEaZqV/g==
                double init_value=65;
                //模型名字
                String model_name="f8";
                //变量个数
                int n =30;
                //边界
                double max=30*2;
                double min=0;

                double varsMinValue[] ;//变量最小值
                double varsMaxValue[];//变量最大值
                varsMinValue = new double[n];
                varsMaxValue = new double[n];
                double areaMin[] = new double[2];//块最小值
                double areaMax[] = new double[2];//块最大值
                double initx [] =new double[n] ;
                double optimal[] = new double[2];
                long startTime=System.currentTimeMillis();   //获取开始时间

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

                double a[] = NoLinearMVMoel.GetGlobalOptimal(n,initx,max,min);


                String res="";
                // 初始化 Date 对象
                Date date = new Date();
                SimpleDateFormat formate1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                res+="时间： "+formate1.format(date)+"      ||      ";
                res+="模型： "+model_name+"       ||      ";
                res+="初始值:  "+init_value+"       ||      ";

                for(int i =0;i<n;i++) {
                        System.out.println("X" + i + ": " + a[i] + " ");
                }

                String value="f(x) =  " +NoLinearMVMoel.GetObjectiveValue(a);

                res+=value+"       ||      ";

                System.out.println(value);

                long endTime=System.currentTimeMillis(); //获取结束时间

                System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

                res+="程序运行时间： "+(endTime-startTime)+"ms"+"\n";


                try {
                        FileWriter fos;
                        fos = new FileWriter("result_test.txt", true); //就是这个构造方法的第二个参数，为true则是追加内容
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
