package Feedback;
import Feedback.aim_func;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Arrays;

public class NonLinearMVModel_1 {
    double objectiveValue;//目标函数
    double curObjective;//当前目标函数的值
    double curValue[];//当前值
    int varsSum;//变量数量
    double initValue[]; //初始值
    double varsMinValue[];//变量最小值
    double varsMaxValue[];//变量最大值
    int p;
    int q;
    double max;
    double min;
    public double GetObjectiveValue(double x[]) {
        aim_func res=new aim_func();
        return res.GetObjectiveValue1(x);
    }

    /*切削实验Data*/
    double qiexueData[][] = {{1, 0.1, 60, 740.24}, {1.41, 0.2, 60, 793.8}, {2, 0.4, 60, 854.80},
            {1.41, 0.1, 94.8, 819.42}, {2, 0.2, 94.8, 870.00}, {1, 0.4, 94.8, 904.80},
            {2, 0.1, 150, 879.60}, {1, 0.2, 150, 911.10}, {1.41, 0.4, 150, 974.10}};

    double fdj[][]={{0.03546,38.30553,1243.531},{0.02111,36.32782,1658.569},{0.01799,38.27041,1356.6592}};

    /*大豆原文Data*/
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
    double b2=-1.0444;

    /*大豆复现Data*/
    /*double w1[][]={{-0.19172828, 0.04039612,0.2576459,-0.14752804},
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
    double b2=0.0661879;*/

    /*玉米实验Data*/
    /*double w1[][] = {{-2.6629, 5.9471, 0.2134, -9.1766},
            {-2.8220, -1.5566, -1.1020, -4.9485},
            {-0.3416, -16.6746, -3.8079, -6.7462},
            {1.3100, 0.5167, -10.1131, 7.0357},
            {3.8324, 2.9777, -7.7359, 4.7049},
            {-17.4792, 3.5559, 20.7811, -11.3949},
            {-0.2762, 1.3379, -1.1120, 1.8320}};
    double w2[] = {11.3948, -19.3894, -5.6696, 6.8314, -9.4303, -2.4501, 2.8836};
    double b1[] = {-1.5704, -2.3435, 3.9201, -1.2868, 0.3434, 0.0328, -0.3224};
    double b2 = -0.8123;*/

    /*功耗实验Data*/
   /* double x1=dg1(1.39,1.5,1);
    double x2=dg1(210,266,200);
    double x3=dg1(55,58.41,41.59);
    double x[]={x1,x2,x3};
    double w1[][]= {{-1.3047, -11.0498, 2.3569},
            {-4.2216,1.8650,-3.9918},
            {0.5543,-9.6378,7.2247},
            {-5.8672,1.0995,1.4527},
            {-4.8335,-1.5613,-5.2040}};
    double w2[]={-11.2921,7.6752,10.9691,-3.3505,12.7070};
    double b1[]={-4.4035,-1.4455,-0.3823,-2.0287,-1.12346};
    double b2=-0.3727;*/

    /*功耗复现Data*/
   /* double x1=dg1(1.39,1.5,1);
    double x2=dg1(210,266,200);
    double x3=dg1(55,58.41,41.59);
    double x[]={x1,x2,x3};
    double w1[][]= {{-7.30997086e-01, -1.42548037e+00, -4.51665282e-01},
            {-2.85642326e-01 ,-4.84396070e-01 ,-1.96124017e-01},
             {-3.83972168e-01,-5.13188422e-01 ,-3.36272240e-01},
            {-5.38928413e+00 ,-6.85849953e+00 ,-2.61559415e+00},
            {-2.57752419e-01 ,-1.09958916e-03 ,-1.24627076e-01}};
    double w2[]={-0.66034675 ,-0.05452669 ,-0.02775798 ,-6.5953703 ,  0.23833363};
    double b1[]={-0.82386416,-0.7320169,-0.7147423,3.2028933,-0.8323059};
    double b2=0.60831743;*/

    /*数据归一化*/
    public double dg1(double a, double amax, double amin) {
        double b = (0.6 - 0.2) * ((a - amin) / (amax - amin)) + 0.2;
        //double b=1.8668*((a-amin)/(amax-amin))-1;
        return b;
    }

    /*数据反归一化*/
    public double dfg1(double b, double amax, double amin) {
        double a = ((b - 0.2) * (amax - amin) / (0.6 - 0.2)) + amin;
        //double a=(b*(amax-amin))+amin;
        return a;
    }

    /*双极性sigmoid函数*/
    public double sigmoid(double zi) {
        double fenzi=(Math.pow(Math.E, zi)-Math.pow(Math.E, -zi));
        double fenmu=(Math.pow(Math.E, zi)+Math.pow(Math.E, -zi));
        return fenzi/fenmu;
    }

    /*单极性sigmoid函数*/
    /*public double sigmoid(double zi) {
        double fenzi = 1;
        double fenmu = 1 + Math.pow(Math.E, -zi);
        return Math.pow(fenmu,(-1));
    }*/

    public double GetObjectiveValue() {
        return curObjective;
    }

    /*判断函数值是否降低*/
    public boolean CheckCurObjective(double x[]) {
        if (GetObjectiveValue(x) <= curObjective) return true;
        return false;
    }

    /*判断约束*/
    public boolean CheckConstraints(double x[]) {
        //if(-(x[0]-1)*(x[0]-1)+x[1]>=0 && (-2)*x[0]-3*(x[1])+6>=0 && x[0]>=0&&x[0]<=10 && x[1]>=0 &&x[1]<=10){
        //if(-1*(x[0]-5)*(x[0]-5)-(x[1]-5)*(x[1]-5)+100<=0 && (x[0]-6)*(x[0]-6)+(x[1]-5)*(x[1]-5)-82.81<=0 && x[0]>=13 && x[0]<=100 && x[1]>=0 && x[1] <=100 ){
        //if(x[1]-100-100*Math.sin(0.1*x[0]-0.5)<=0 && (50/(x[0]+0.1))-40-(x[1]-100)<=0 && x[0]>=0 && x[0]<=100 && x[1]>=0 && x[1]<=200){

        for(int i = 0 ;i<varsSum ; i++) {
            if(x[i] > this.max || x[i] < this.min){
            //if(x[i] > 200 || x[i] < 0){
            //if(x[i] > 1200 || x[i] < 0){
            //if(x[i] > 200 || x[i] < 0){
            //if (x[i] < 0 || x[i] > Math.PI) {
            //if(x[i] > 300|| x[i] < 0){
            //if((x[0] > 210|| x[0] < 35)||(x[1] > 325|| x[1] < 130)||(x[2] > 315|| x[2] < 125)){
//                if((x[0] < dg1(1, 1.5,1) || x[0] > dg1(1.5, 1.5,1))
//                        && (x[1] < dg1(200, 266.64,199.36) || x[1] > dg1(266, 266.64,199.36))
//                        && (x[2] < dg1(41.59, 58.41,41.59) || x[2] > dg1(58.41, 58.41,41.59)) ){
//            if(x[i] > 20 || x[i] < 0){
            //if(x[0] > 1.5 &&x[0]<1&&x[1]>266&&x[1]<200&&x[2]>58.41&&x[2]<41.59){
                    return false;
                }
        }
        return true;
        /*if (x[0] < dg1(10.78, 10.78, 6.86) && x[0] > dg1(6.86, 10.78, 6.86)
                && x[1] < dg1(216, 216, 40) && x[1] > dg1(40, 216, 40)
                && x[2] < dg1(151.8, 151.8, 32.2) && x[2] > dg1(32.2, 151.8, 32.2)
                && x[3] < dg1(115, 115, 25) && x[3] > dg1(25, 115, 25)) {*/
        /*if(x[0]>=35&&x[0]<=210&&x[1]>=130&&x[1]<=325&&x[2]>=125&&x[2]<=315){
            return true;
        }
        return false;*/
    }

    /*获取初始点*/
    public void SetInitValue(int inVarsSum, double initValue[]) {
        varsSum = inVarsSum;
        this.initValue = new double[inVarsSum];
        for (int i = 0; i < inVarsSum; i++) {
            this.initValue[i] = initValue[i];
            this.initValue[i] = initValue[i];
        }
    }


    /*获取函数值*/
    public double GetObjectiveValue0(double x[]) {
//        System.out.println(varsSum);
       /* double m=3;
        double f=0;

        f=x[0]*x[0]*fdj[0][0]+x[0]*fdj[0][1]+fdj[0][2]+x[1]*x[1]*fdj[0][0]+x[1]*fdj[0][1]+fdj[0][2]
                +x[2]*x[2]*fdj[0][0]+x[2]*fdj[0][1]+fdj[0][2]-43.6758552368013*(x[0]+x[1]+x[2]-400);

        return f;*/


       /* double m = 7;
        double z1[] = {0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < m; i++) {
            z1[i] = x[0] * w1[i][0] + x[1] * w1[i][1] + x[2] * w1[i][2] + x[3] * w1[i][3] + b1[i]; //  X*wij+b1
            z1[i] = sigmoid(z1[i]);
        }
        double z2 = 0;
        for (int i = 0; i < m; i++) {
            z2 += z1[i] * w2[i];
        }
        double result_value = sigmoid(z2 + b2);
        return -result_value;*/
        /*double value=0;
        return value=165.90-92.43*x[0]-0.83*x[1]-0.64*x[2]+0.11*x[0]*x[1]+0.003*x[1]*x[2]+28.97*x[0]*x[0]+0.001*x[1]*x[1];*/


            /*double m = 5;
            double z1[] = {0, 0, 0, 0, 0};
            for (int i = 0; i < m; i++) {
                z1[i] = x[0] * w1[i][0] + x[1] * w1[i][1] + x[2] * w1[i][2]  + b1[i]; //  X*wij+b1
                z1[i] = sigmoid(z1[i]);
            }
            double z2 = 0;
            for (int i = 0; i < m; i++) {
                z2 += z1[i] * w2[i];
            }
            double result_value = sigmoid(z2 + b2);
            //return -result_value;
            return result_value;*/


       /* double m= 15;
        double z1[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i=0;i<m;i++){
            z1[i]=x[0]*w1[i][0]+x[1]*w1[i][1]+x[2]*w1[i][2]+x[3]*w1[i][3]+b1[i]; //  X*wij+b1
            z1[i]=sigmoid(z1[i]);
        }
        double z2=0;
        for(int i=0;i<m;i++){
            z2+=z1[i]*w2[i];
        }
        double result_value=z2+b2;
        return -result_value/1000;*/

        //return Math.pow(x[0],2)+Math.pow(x[1],2)-x[0]*x[1]-2*x[0]-5*x[1];
        //return Math.pow(x[0]-10,3)+Math.pow(x[1]-20,3);
        //return Math.pow(x[0],2)-Math.pow((x[1]-100),3);
        /*double Value = 0;
        double value1 = 0;
        double value2 = 0;
        double qiexueSum = 0;
        double qiexueM = 9;*/

        double value1 = 0;
        double value2 = 0;
        double value3 = 0;
        double Value = 0;
        for (int i =0;i<varsSum;i++){
            Value += Math.pow(x[i]-100,2);
           /* value1 +=Math.pow(x[i]-5,2)-10*Math.cos(2*Math.PI*(x[i]-5));*/
            /*value1 +=Math.pow(x[i]-30,2);
            value2 +=Math.cos(2*Math.PI*(x[i]-30));*/
            /*value1 +=(x[i]-500)*Math.sin(Math.pow(x[i]-500,0.5));*/
            /*value1 +=100*Math.pow(((x[i+1]-30)-Math.pow((x[i]-30),2)),2)+Math.pow((x[i]-31),2);*/
            /*value1 +=Math.pow(x[i]-5,2);
            value2 +=Math.pow(0.5*i*(x[i]-5),2);
            value3 +=Math.pow(0.5*i*(x[i]-5),4);*/
          /*  value1 +=Math.abs(x[i]*Math.sin(x[i])+0.1*x[i]);*/
            /*value1 +=Math.pow(x[i]-1,2);*/
            /*value1 =Math.pow(varsSum,(-1));
            value2 +=Math.pow(x[i]-5,4)-16*Math.pow(x[i]-5,2)+5*(x[i]-5);*/
        /*for (int i =0;i<qiexueM;i++){
            double x1=x[0];
            double yx2=Math.pow(qiexueData[i][0],x[1]);
            double bx2=Math.pow(qiexueData[i][1],x[2]);
            double vx2=Math.pow(qiexueData[i][2]/100,x[3]);
            double Ti=qiexueData[i][3]/1000;
            qiexueSum+=Math.pow(x1*yx2*bx2*vx2-Ti,2);
            Value += Math.pow(x[i]-100,2);
            value1 += Math.pow(x[i]-600,2) ;
            value2 *= Math.cos( (x[i]-600)/Math.sqrt(i+1) )+1;
            //Value += Math.sin(x[i])*Math.pow(Math.sin( ((i+1)*x[i]*x[i])/Math.PI),20 );
            //value1 += Math.pow(( (x[i]-900) -1),2);
            value1 += Math.abs(x[i]-10);
            value2 *= Math.abs(x[i]-10);
            //Value += (i+1) * ( (x[i]-10)*(x[i]-10));*/
        }
        /*Value=-20*Math.exp(-0.02*Math.pow((Math.pow(varsSum,(-1))*value1),(0.5)))-Math.exp((Math.pow(varsSum,(-1))*value2))+20+Math.E;*/

       /* double item=qiexueSum/(qiexueM-2);
        double Qx=Math.pow(item,0.5);
        for (int i =1;i<varsSum;i++){
            value2 += (x[i]-900)*(x[i]-900)-1;
        //value2 += Math.pow(x[i]-100,2);
        // }
        //Value = (1/4000)*value1 -value2 ;
        //Value = (x[0]-100)*(x[0]-100) + Math.pow(10,6)* value2;
        //Value = value1 + value2;
        //Value =value1 - value2;
        Value=Qx;*/
        /*Value=value1*value2;*/
        /*Value=-Math.exp(-0.5*value1);*/
        /*Value=Math.abs(value1);*/
        /*Value=value1+value2+value3;*/
        /*Value=-value1;*/
        /*Value=10*varsSum+value1;*/
//        System.out.println(Value);
        return Value;
    }


    /*获取最大最小值域*/
    public void SetMaxMinValue(int inVarsSum,double varsMinValue[], double varsMaxValue[])
    {
        varsSum = inVarsSum;
        this.varsMinValue =new double[inVarsSum];
        this.varsMaxValue =new double[inVarsSum];
        for(int i=0;i<inVarsSum;i++){
            this.varsMinValue[i] = varsMinValue[i];
            this.varsMaxValue[i] = varsMaxValue[i];
        }
    }

    /*判断是否为可行解*/
    public boolean  JudgeIfFeasibleSolution(double x[])
    {
        if(CheckConstraints(x)) return true;
        return false;
    }

    /*判断满足块内最大最小值*/
    public boolean CheckBlockMinMax(double x[],int vars,double min[],double max[]){
        for(int i=0;i<vars;i++){
            if(x[i]<min[i]){
                return false;
            }
            if(x[i]>max[i]){
                return false;
            }
        }
        return true;
    }


    /*寻找初始可行解*/
    /*----*/
    public boolean FindInitSolution(double init[] ,int versum ,int curver,double min[],double max[])
    {

/*        Tools myTools = new Tools();
        String str = "";
        for(int i=0;i<init.length;i++) str += "x["+i+"]:"+init[i]+" ";
        str += "\f\n";
        myTools.SaveFile("a.txt", str, true);*/
        //System.out.println(init[0] + " " + init[1] + " " + curver + " " );
        if (CheckConstraints(init) && CheckBlockMinMax(init,versum,min,max)){
            return true;
        }
        if (versum > curver){//变量总个数，当前变量下标
            double coreinit = init[curver];
            if(init[curver] < min[curver]){
                while (init[curver] < max[curver]){
                    if(init[curver] < min[curver]) init[curver] = min[curver] - 0.01;
                    init[curver] +=0.01;
                    if (FindInitSolution(init,versum ,curver+1,min,max)){
                        return true;
                    }
                }
                init[curver] = coreinit;
            }else if (init[curver] > max[curver]){
                while (init[curver] > min[curver]){
                    if (init[curver] > max[curver]) init[curver] = max[curver]  + 0.01;
                    init[curver] -=0.01;
                    if (FindInitSolution(init,versum ,curver+1,min,max)){
                        return true;
                    }
                }
                init[curver] = coreinit;
            }else {

                //向左找
                while (init[curver] >= min[curver]){
                    init[curver] -=0.01;
                    if(init[curver] < min[curver]){
                        init[curver] +=0.01;
                        break;
                    }
                    if (FindInitSolution(init,versum ,curver+1,min,max)){
                        return true;
                    }
                }

                init[curver] = coreinit;
                //向右找
                while (init[curver] <= max[curver]){
                    init[curver] +=0.01;
                    if(init[curver] > max[curver]){
                        init[curver] -=0.01;
                        break;
                    }
                    if (FindInitSolution(init,versum ,curver+1,min,max)){
                        return true;
                    }
                }
                init[curver] = coreinit;
            }
        }
        return false;
    }

    /*求决策变量x1x2可行解*/
    /*
     * name:GetFeasibleSolution
     * function:求决策变量x1x2可行解
     * creator:ya ning
     * date:2021/1/28
     */
    /*----*/
    public boolean GetFeasibleSolution(int varsSum,double x[],int direction,int index,double ret[],double Min[],double Max[])
    {
        double temp[] = new  double[varsSum];
        boolean find = false;
        for(int i=0; i< varsSum; i++ ){
            temp[i] = x[i];
        }
        //初始值在最大边界和最下边界外求得可行解
        if(temp[index] > Max[index]) { ret[0] = Max[index];  temp[index] = ret[0]; }
        if(temp[index] < Min[index]) { ret[0] = Min[index];  temp[index] = ret[0]; }

        if(JudgeIfFeasibleSolution(temp)) {
            ret[0] = temp[index];
            return true;
        }


        if(direction == 0){//0从右向左<---,则initX尽量靠右
            find = false;
            while(temp[index] <= Max[index]){
                if(temp[index] > 0) temp[index] *= 2;//initX大于0，向右靠近
                else if(temp[index] == 0) temp[index] = 0.01;//initX等于0时，向右靠近
                else if(temp[index] >= -0.000001 && temp[index] < 0) temp[index] = 0;//initX小于0的情况，当接近0时，设置为0
                else if(temp[index] < -0.000001) temp[index] /= -2;//initX小于0的情况

                if(JudgeIfFeasibleSolution(temp)) {
                    find = true;
                    break;
                }
            }
            if(find) {
                ret[0] = temp[index];
                return true;
            }
            temp[index] = Max[index];
            if(JudgeIfFeasibleSolution(temp)) {
                ret[0] = Max[index];
                return true;
            }
            //最大值都无法满足要求，则从右向左搜索
            while(temp[index] >= Min[index] + 0.000000000001){
                if(temp[index] > 0) temp[index] /= 2;//initX>0，向左靠近
                else if(temp[index] < 0.000001 && temp[index] > 0) temp[index] = 0;//initX>0，向左靠近
                else if(temp[index] == 0) temp[index] = -0.01;//initX==0，向左靠近
                else if(temp[index] < 0) temp[index] *= 2;//initX<0，向左靠近
                if(JudgeIfFeasibleSolution(temp)) {
                    find = true;
                    break;
                }
            }
            if(find) {
                ret[0] = temp[index];
                return true;
            }
            temp[index] = Min[index];
            if(JudgeIfFeasibleSolution(temp)) {
                ret[0] = temp[index];
                return true;
            }
        }

        if(direction == 1){//1从左向右---->,initX尽量靠左
            find = false;
            while(temp[index] >= Min[index]+0.000000000001){
                if(temp[index] > 0) temp[index] /= 2;//initX>0,向左靠近
                else if(temp[index] < 0.000001 && temp[index] > 0) temp[index] = 0;//initX>0,向左靠近
                else if(temp[index] == 0) temp[index] = -0.01;//initX==0,向左靠近
                else if(temp[index]< -0.000001) temp[index] *= 2;//initX<0,向左靠近
                //else if(initX >= -0.000001 && initX < 0) initX = 0;//initX in [-0.000001,0],向左靠近
                if(JudgeIfFeasibleSolution(temp)){
                    find = true;
                    break;
                }
            }
            if(find) {
                ret[0] = temp[index];
                return true;
            }
            temp[index] = Min[index];
            if(JudgeIfFeasibleSolution(temp)) {
                ret[0] = Min[index];
                return true;
            }
            //最小值都无法满足要求，则从左向右搜索
            temp[index] = Min[index];
            while(temp[index] <= Max[index]){
                if(temp[index] > 0) temp[index] *= 2;//initX>0，向右靠近
                else if(temp[index] > -0.000001 && temp[index] < 0) temp[index] = 0;//initX>0，向右靠近
                else if(temp[index] == 0) temp[index] = 0.01;//initX==0，向右靠近
                else if(temp[index] < 0) temp[index] /= 2;//initX<0，向右靠近
                if(JudgeIfFeasibleSolution(temp)) {
                    find = true;
                    break;
                }
            }
            if(find) {
                ret[0] = temp[index];
                return true;
            }
            temp[index] = Max[index];
            if(JudgeIfFeasibleSolution(temp)) {
                ret[0] = Max[index];
                return true;
            }
        }
        return false;
    }

    /*求决策变量x1x2可行解*/
    /*
     * name:GetOtherFeasibleSolution
     * function:求决策变量x1x2可行解
     * creator:ya ning
     * date:2021/1/28
     */
    public boolean GetOtherFeasibleSolution(int varsSum,double x[],int direction,int index,double ret[],double Min[],double Max[])
    {
        /*String str = "";
        str = "direction:"+direction+" index:"+index+ "\n\r";
        for(int i=0;i<x.length;i++){
            str += x[i] + " ";
        }
        Tools myTools = new Tools();
        myTools.SaveFile("debug.txt",str,true);*/
        boolean find = false;
        double temp[] = new  double[varsSum];
        for(int i=0; i< varsSum; i++ ){
            temp[i] = x[i];
        }

        double otherFX = temp[index];//另外一个可行解
        if (temp[index] == Max[index]) otherFX = temp[index] - Tools.AreaOtherSolution;
        else if (temp[index] == Min[index] ) otherFX = temp[index] + Tools.AreaOtherSolution;

        if(direction == 0){//0从右向左<---
            find = false;
            otherFX = temp[index]-Tools.AreaOtherSolution;
            while (otherFX >= Min[index]+0.000000000001){
                temp[index] = otherFX;
                if(JudgeIfFeasibleSolution(temp)) {
                    if(GetObjectiveValue(x) > GetObjectiveValue(temp)){
                        find = true;
                        break;
                    }
                }
                otherFX /=2;
            }
            if(find) {
                ret[0] = otherFX;
                ret[1] = x[index];
                return true;
            }
            //在1-2之间的一个范围
            find = false;
            otherFX = temp[index]-Tools.AreaOtherSolution;
            while (otherFX >= Min[index]+0.000000000001){
                temp[index] = otherFX;
                if(JudgeIfFeasibleSolution(temp)) {
                    if(GetObjectiveValue(x) > GetObjectiveValue(temp)){
                        find = true;
                        break;
                    }
                }
                otherFX /=1.5;
            }
            if(find) {
                ret[0] = otherFX;
                ret[1] = x[index];
                return true;
            }

            //最小值都无法满足要求，则从左向右搜索
            otherFX = temp[index]+Tools.AreaOtherSolution;
            while(otherFX <= Max[index]){
                temp[index] = otherFX;
                if(JudgeIfFeasibleSolution(temp)) {
                    if(GetObjectiveValue(x) > GetObjectiveValue(temp)){
                        find = true;
                        break;
                    }
                }
                otherFX *=2;
            }
            if(find) {
                ret[0] = x[index];//方向变化了，从从左向右，但是direction没有变化，则修改了ret[0]和ret[1]
                ret[1] = otherFX;
                return true;
            }
        }

        if(direction == 1){//1从左向右---->
            find = false;
            otherFX = temp[index]+Tools.AreaOtherSolution;
            while(otherFX <= Max[index]){
                temp[index] = otherFX;
                if(JudgeIfFeasibleSolution(temp)) {
                    if(GetObjectiveValue(x) > GetObjectiveValue(temp)){
                        find = true;
                        break;
                    }
                }
                otherFX *= 2;
            }
            if(find) {
                ret[0] = otherFX;
                ret[1] = x[index];
                return true;
            }
            //最小值都无法满足要求，则从右向左搜索
            otherFX = temp[index]-Tools.AreaOtherSolution;
            while(otherFX >= Min[index]+0.000000000001){
                temp[index] = otherFX;
                if(JudgeIfFeasibleSolution(temp)) {
                    if(GetObjectiveValue(x) > GetObjectiveValue(temp)){
                        find = true;
                        break;
                    }
                }
                otherFX /=1.5;
            }
            if(find) {
                ret[0] = x[index];//方向变化了，从右向左，但是direction没有变化，则修改了ret[0]和ret[1]
                ret[1] = otherFX;
                return true;
            }
            //在1-2之间的一个范围
            find = false;
            otherFX = temp[index]-Tools.AreaOtherSolution;
            while (otherFX >= Min[index]+0.000000000001){
                temp[index] = otherFX;
                if(JudgeIfFeasibleSolution(temp)) {
                    if(GetObjectiveValue(x) > GetObjectiveValue(temp)){
                        find = true;
                        break;
                    }
                }
                otherFX /=1.5;
            }
            if(find) {
                ret[0] = x[index];
                ret[1] = otherFX;
                return true;
            }
        }
        return false;
    }

    /*求单变量平衡点*/
    /*
     * name:GetBalanceDot
     * function:求单变量平衡点
     * creator:ya ning
     * date:2021/1/28
     */
    public boolean GetBalanceDot(double x1,double x2,double x[],int index ,int direction,int step,double balanceX[],double utX[],double Min[],double Max[])
    {
        this.curValue =new double[varsSum];
        boolean ct ;
        curValue[index] = x2;
        double temp1[] = new double[varsSum];
        double temp2[] = new double[varsSum];//决策变量x2和所有变量数组
        for(int i=0; i< varsSum; i++ ){
            temp1[i] = x[i];
            temp2[i] = x[i];
        }

        temp2[index] = x2;
        boolean highct = false; //判断函数值是否下降
        //System.out.println("direction:"+ direction+" ut:" + utX[0] + " curX:"+ curValue[index] +" x2:" + x2 +" x1:" + x1 + " p:"+ p +" q:" + q +" step:" + step);
        if (utX[0] > 0.00000000000000001) {
            if (direction == 0) {//direction方向0表示往左；满足x1>x2////先计算curX，如果条件满足，则变化p、q和x1、x2

                curValue[index] = curValue[index] / Math.pow(step, p) - (x1 - x2) / Math.pow(step, q);
                //System.out.println(curValue[index]);

            } else if (direction == 1) {//direction方向1表示往右；满足x2>x1////先计算curX，如果条件满足，则变化p、q和x1、x2
                curValue[index] = curValue[index] * Math.pow(step, p) - (x1 - x2) / Math.pow(step, q);

            }
        } else {

            balanceX[0] = curValue[index];
            return true;//到达平衡点curX
        }
        temp1[index] = curValue[index];
        if (CheckConstraints(temp1) && CheckBlockMinMax(temp1,varsSum,Min,Max)){//满足约束条件
            ct = true;
            if (GetObjectiveValue(temp1) <= GetObjectiveValue(temp2)) {//满足函数值下降
                highct = true;
            } else {
                highct = false;
            }
        } else {//不满足约束条件
            ct = false;
        }


        if (ct == true) {//约束条件满足
            if (highct == true) {
                p++;
                q = 0;
            } else if (highct == false) {
                curValue[index] = x2;
                q++;
                p = 0;
            }
        } else {
            curValue[index] = x2;
            q++;
            p = 0;
        }

        if (ct == true && highct == true) {//约束条件满足
            utX[0] = Math.abs(curValue[index] - x2);//表示两点之间的差异
            balanceX[0] = curValue[index];
            //ut=Math.abs(curX-x2);//表示两点之间的差异
            if (direction == 0) {//direction方向0表示往左；满足x1>x2
                x1 = x2;
                x2 = curValue[index];
            } else if (direction == 1) {//direction方向1表示往右；满足x1<x2
                x1 = x2;
                x2 = curValue[index];
            }
        }
        return false;
    }

    /*与GetOptimalInArea配合使用*/
    /*
     * name:CheckIfOptimal
     * function:判断每组变量是否都达到平衡状态，是否为最优解
     * creator:ya ning
     * date:2021/1/28
     */
    public boolean CheckIfOptimal(double curoptimalx[],double optimalx[])
    {
        int i ;
        for (i = 0; i<varsSum;i++){
            if(Math.abs(curoptimalx[i] - optimalx[i])>0.00000000000000001){
                return true;
            }
        }
        return false;
    }

    /*获取单变量最优解*/
    /*
     * name:GetByOneVariableOptimal
     * function:获取单变量最优解
     * creator:ya ning
     * date:2021/1/28
     */
    public boolean GetByOneVariableOptimal(double initValuex[],double curoptimalx[],int index,double AreaMin[],double AreaMax[])
    {
        double[] utX = new double[1];
        double[] ret = new  double[2];
        double balanceDot[] = new  double[3];
        double curoptimalotherx[] = new double[varsSum];//步长调整获取的最优解数组
        int direction = 0;
        double x1;//表示决策变量x的前值
        double x2;//表示决策变量x的后值,x1->x2组成链，与方向direction配合使用
        utX[0] = 0.1;
        int step = 2;
        int indexStep = 2;
        for (int i = 0; i<varsSum;i++){
            curoptimalx[i] = initValuex[i];
            curoptimalotherx[i] = curoptimalx[i];
        }

        while(true) {
            //先判断是否可求可行解x1
            if(GetFeasibleSolution(varsSum, curoptimalx, direction, index, ret, AreaMin, AreaMax)){
                //System.out.println("寻找到可行解");
            }else{
                //System.out.println("未寻找到可行解");
                return false;
            }
            balanceDot[0] = ret[0];
            balanceDot[1] = -1;//向左平衡点
            balanceDot[2] = -1;//向右平衡点
            balanceDot[direction + 1] = ret[0];
            curoptimalx[index] = balanceDot[0];

            while (Math.abs(balanceDot[1] - balanceDot[2]) > 0.00000000000000001) {//寻找平衡点
                //求两可行解
                GetFeasibleSolution(varsSum, curoptimalx, direction, index, ret, AreaMin, AreaMax);

                x2 = ret[0];
                if(!GetOtherFeasibleSolution(varsSum, curoptimalx, direction, index, ret, AreaMin, AreaMax)){
                    /*curoptimalx[index] = balanceDot[0];
                    //System.out.println("无第二个决策变量，当前点为平衡点！");
                    return true;*/
                }

                x2 = ret[0];
                x1 = ret[1];
                //curoptimalx[index] = x2;

                GetBalanceDot(x1, x2, curoptimalx, index, direction, step, balanceDot, utX, AreaMin, AreaMax);
                curoptimalx[index] = balanceDot[0];

                //根据方向求平衡点
                if (direction == 0) {//向左
                    boolean find1;
                    find1 = false;
                    while (!find1) {
                        GetFeasibleSolution(varsSum, curoptimalx, direction, index, ret, AreaMin, AreaMax);

                        x2 = ret[0];
                        GetOtherFeasibleSolution(varsSum, curoptimalx, direction, index, ret, AreaMin, AreaMax);

                        x2 = ret[0];
                        x1 = ret[1];
                        //curoptimalx[index] = x2;

                        GetBalanceDot(x1, x2, curoptimalx, index, direction, step, balanceDot, utX, AreaMin, AreaMax);
                        curoptimalx[index] = balanceDot[0];
                        balanceDot[1] = balanceDot[0];
                        //System.out.println("****");
                        /*if(q>=10000){
                            break;
                        }*/
                        if (utX[0] < 0.00000000000000001) {
                            find1 = true;
                        }

                    }
                    direction = 1;
                    utX[0] = 0.1;
                } else if (direction == 1) {//向右
                    boolean find1;
                    find1 = false;
                    while (!find1) {
                        GetFeasibleSolution(varsSum, curoptimalx, direction, index, ret, AreaMin, AreaMax);

                        x2 = ret[0];
                        GetOtherFeasibleSolution(varsSum, curoptimalx, direction, index, ret, AreaMin, AreaMax);

                        x2 = ret[0];
                        x1 = ret[1];
                        GetBalanceDot(x1, x2, curoptimalx, index, direction, step, balanceDot, utX, AreaMin, AreaMax);
                        curoptimalx[index] = balanceDot[0];
                        balanceDot[2] = balanceDot[0];
                        /*if(q>=10000){
                            break;
                        }*/
                        if (utX[0] < 0.00000000000000001) {
                            find1 = true;
                        }

                    }
                    direction = 0;
                    utX[0] = 0.1;
                }
            }
            curoptimalx[index] = balanceDot[0];
            if(varsSum>=10){//如果变量数大于10个，步数调整素数策略取消
                break;
            }
            if ( GetObjectiveValue(curoptimalotherx) > GetObjectiveValue(curoptimalx)){
                for (int i =0 ;i<varsSum;i++) {
                    curoptimalotherx[i] = curoptimalx[i];
                }
                indexStep = step;
            }
            //System.out.println("当step:" + step + "时，当前全局最优解index: " +index +" = " + curoptimalotherx[index] );
            step++;
            if(step > initValuex[index]/2){
                break;
            }
        }
        if(GetObjectiveValue(curoptimalotherx) < GetObjectiveValue(curoptimalx)){
            for (int i =0 ;i<varsSum;i++) {//curoptimalotherx为改变步长求得的最优的当前平衡点进行赋值
                curoptimalx[i] = curoptimalotherx[i];
            }
        }
        return true;
    }


    /*寻找块内最优解*/
    /*
     * name:GetOptimalInArea
     * function:寻找块内最优解
     * creator:ya ning
     * date:2021/1/28
     */
    public boolean GetOptimalInArea(double feasibleX[],double optimalArea[],double AreaMin[],double AreaMax[])
    {
        double curoptimalx[] = new double[varsSum];
        double curoptimalotherx[] = new double[varsSum];
        double ret[] = new double[2];

        if(FindInitSolution(feasibleX,varsSum,0,AreaMin,AreaMax)){//块内是否有可行解，如果有将块内可行解将可行解当做当前块内最优解
            System.out.println("可行解 " );
            for (int i = 0; i < varsSum; i++) {//求得的数组curoptimalotherx为当前最优解，进行赋值并输出
                System.out.print(" index="+ i + ": "+ feasibleX[i] + " ");
            }
            for (int i = 0; i < varsSum; i++) {//块内可行解作为当前最优解
                curoptimalx[i]=feasibleX[i];
            }
        }else{//否则放弃这个块
            return false;
        }

        while (CheckIfOptimal(curoptimalx,optimalArea)) {//寻找块内最优解


            for (int i = 0; i < varsSum; i++) {//当前可行解作为块内最优解
                optimalArea[i] = curoptimalx[i];
            }

            int index = 0;
            while (index < varsSum) {//块内每个变量固定进行固定后寻找平衡点

                GetByOneVariableOptimal(curoptimalx, curoptimalotherx, index, AreaMin, AreaMax);

                System.out.print("变量: " + index + " 当前平衡点: ");
                for (int i = 0; i < varsSum; i++) {//求得的数组curoptimalotherx为当前最优解，进行赋值并输出
                    curoptimalx[i] = curoptimalotherx[i];
                    System.out.print(curoptimalx[i] + " ");
                }
                System.out.print("\n");
                //每次一个变量寻找完平衡点重置p，q为全局变量
                p = 0;
                q = 0;
                index++;
            }
            //System.out.println("F(x) = " +GetObjectiveValue(curoptimalx));
        }
        return true;
    }



    /*寻找全局最优解*/
    /*
     * name:GetGlobalOptimal
     * function:寻找全局最优解
     * creator:ya ning
     * date:2021/1/28
     */
    public double[] GetGlobalOptimal(int varsSum,double initValue[],double max,double min)
    {
        this.max=max;
        this.min=min;

        double GlobalOptimal[] = new double[varsSum];
        double optimalArea[] = new double[varsSum];
        //Arrays.fill(optimalArea,-999999.0);
        double AreaMin[] = new double[varsSum];
        double AreaMax[] = new double[varsSum];
        this.initValue = new double[varsSum];
        double initX[] = new  double[varsSum] ;
        int areaNum ;
        int quadrant=0;
        int curver=0;
        for (int i = 0 ; i < varsSum ; i++ ){
            this.initValue[i] = initValue[i];
            initX[i] = initValue[i];
            AreaMax[i] = varsMaxValue[i];
            AreaMin[i] = varsMinValue[i];
        }

        Tools myTools = new Tools();
//        String str = "";

/*        GetArea(varsSum,0,5,AreaMin,AreaMax);//获取块
        areaNum = GetAreaNum();//获取区间数
        System.out.println("总块数为： " + areaNum);*/

        FindInitSolution(initX, varsSum, curver, varsMinValue, varsMaxValue);
        for (int i = 0; i < varsSum; i++) {//防止最优解大于0，而最优解不设定初始值而默认为0
            GlobalOptimal[i] = initX[i];
            optimalArea[i] = initX[i]+0.1;
        }

//        System.out.println(initX[0] +" " +initX[1]);
        //每个块进行最优解计算
/*        while (quadrant < areaNum) {
            //读取第quadrant中的各变量最小值边界
            //存储最小值
            for (int i = 0; i < varsSum; i++) {
                str = myTools.ReadAppointedLine("a1.txt", (varsSum * 2 + 3) * quadrant + i);//第quadrant块的第i行
                AreaMin[i] = Double.parseDouble(str);
                //System.out.println("变量 " + i + " 最小值为 " + areaMin[i]);
                //读取第quadrant中的各变量最大值边界
                // 存储最大值
            }
            //存储最大值
            for (int j = 0; j < varsSum; j++) {
                str = myTools.ReadAppointedLine("a1.txt", (varsSum * 2 + 3) * quadrant + varsSum + 1 + j);//第quadrant块的第i行
                AreaMax[j] = Double.parseDouble(str);
                //System.out.println("变量 " + i + " 最大值为 " + areaMax[j]);
            }
            System.out.println("第: " + quadrant + "块 ");*/
        //System.out.println("块最小值: "+ AreaMin[0] + " " +AreaMin[1] +" 块最大值: " + AreaMax[0] + " " + AreaMax[1]);
        if(GetOptimalInArea(initX,optimalArea,AreaMin,AreaMax)){
            if (GetObjectiveValue(GlobalOptimal) > GetObjectiveValue(optimalArea)) {
                for (int k = 0; k < varsSum; k++) {
                    GlobalOptimal[k] = optimalArea[k];
                }
            }
        }else {
            System.out.println("未寻找到此块内可行解");
        }
//            quadrant++;
//        }
        //myTools.SaveFile("a1.txt", str, false);//文件清空
        return GlobalOptimal;
    }

}
