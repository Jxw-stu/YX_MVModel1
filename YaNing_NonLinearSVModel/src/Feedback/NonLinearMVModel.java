package Feedback;
import java.lang.Exception;

public class NonLinearMVModel {
    double objectiveValue;//目标函数
    double curObjective;//当前目标函数的值
    double curValue[];//当前值
    int curx;
    int varsSum;//变量数量
    double initValue[]; //初始值
    double varsMinValue[];//变量最小值
    double varsMaxValue[];//变量最大值
    int p;//调整的大步步长
    int q;//调整的小步步长
    int step ;//调整的方向
    double x1;//表示决策变量x的前值
    double x2;//表示决策变量x的后值,x1->x2组成链，与方向direction配合使用
    int direction;//0表示向左，1表示向右
    double ut;//表示两点之间的差异
    boolean ct;//ct表示是否满足约束条件

    double qiexueData[][]={{1,0.1,60,740.24},{1.41,0.2,60,793.8},{2,0.4,60,854.80},
            {1.41,0.1,94.8,819.42},{2,0.2,94.8,870.00},{1,0.4,94.8,904.80},
            {2,0.1,150,879.60},{1,0.2,150,911.10},{1.41,0.4,150,974.10}};

    /*
     * 返回目标函数的值
     */
    public double GetObjectiveValue()
    {
        return curObjective;
    }

    /*
     * 检测当前数组X[]所有值下的目标值与已有的目标值比较，
     * 如果小于等于已有的目标值，则为true；否则为false；
     */
    public boolean CheckCurObjective(double x[])
    {
        if(GetObjectiveValue(x)<=curObjective) return true;
        return false;
    }

    /*
     * 检测是否满足约束条件
     */
    public boolean CheckConstraints(double x[])
    {
        //if(X>=0&&X<=20  &&  Y>=0&&Y<=20)
        //if(Y-X*X==0 && X>=0 && X<=1 && Y>=0&& Y<=1)
        //if(X*X-Y+1<=0 && 1-X+(Y-4)*(Y-4)<=0 && X>=0&&X<=10 && Y>=0 &&Y<=10)
        //if((X+Y) <= 10 && (X-Y)>=6 && X>=0 && Y>=0 && X<=20 && Y<=20)

        //if(-1*(x[0]-5)*(x[0]-5)-(x[1]-5)*(x[1]-5)+100<=0.1 && (x[0]-6)*(x[0]-6)-(x[1]-5)*(x[1]-5)-82.81<=0.1 && x[0]>=13 && x[0]<=100 && x[1]>=0 && x[1] <=100)// && x[2]>=0 && x[2] <=100
        //if(-1*(X-1)*(X-1)+Y>=0 && -2*X-3*Y+6>=0 && X>=0 && X<=10 && Y>=0 && Y <=10)
        //if(x[0]>=0 && x[0]<=10 && x[1]>=0 &&x[1]<=10)
        //if(x[0]*x[0]-x[1]+1<=0 && 1-x[0]+(x[1]-4)*(x[1]-4)<=0 && x[0]>=0&&x[0]<=10 && x[1]>=0 &&x[1]<=10)
        //if(-(x[0]-1)*(x[0]-1)+x[1]>=0 && (-2)*x[0]-3*(x[1])+6>=0 && x[0]>=0&&x[0]<=10 && x[1]>=0 &&x[1]<=10)//(-2)*x[0]-3*(x[1])+6>=-0.00001就不满足约束了
        if(x[0]<500&&x[0]>400&&x[1]<0.1&&x[1]>0.01&&x[2]<0.2&&x[2]>0.01&&x[3]<0.5&&x[3]>0.1)
        //if(-1*(x[0]-5)*(x[0]-5)-(x[1]-5)*(x[1]-5)+100<=0.1 && (x[0]-6)*(x[0]-6)-(x[1]-5)*(x[1]-5)-82.81<=0.1 && x[0]>=13 && x[0]<=100 && x[1]>=0 && x[1] <=100 )
        //if(-1*(X-1)*(X-1)+Y>=0 && -2*X-3*Y+6>=0 && X>=0 && X<=10 && Y>=0 && Y <=10)
        //if(x[0]>=0 && x[0]<=10 && x[1]>=0 &&x[1]<=10)
        //if(x[0]*x[0]-x[1]+1<=0 && 1-x[0]+(x[1]-4)*(x[1]-4)<=0 && x[0]>=0&&x[0]<=10 && x[1]>=0 &&x[1]<=10)
        {
            return true;
        }
        return false;
    }

    /*
     * 设定初始值
     */
    public void SetInitValue(int inVarsSum ,double initValue[])
    {
        varsSum = inVarsSum;
        this.initValue =new double[inVarsSum];
        for(int i=0;i<inVarsSum;i++){
            this.initValue[i] = initValue[i];
            this.initValue[i] = initValue[i];
        }
    }

    /*
     * function:GetObjectiveValue得到目标函数的值
     * parameters:
     * 	x[]:double决策变量x[]数组
     * return:double目标函数
     */
    public double GetObjectiveValue(double x[])
    {
        //return Math.pow(inX-10,3)+Math.pow(inY-20,3);
        //return Math.pow(inX,2)+Math.pow(inY-1,2);// f(X,Y) = X^2+(Y-1)^2
        //return -2*inX+inY;//f(x,y) = -2x+y;
        //return Math.pow(inX,2)+Math.pow(inY,2)-inX*inY-2*inX-5*inY;// f(x,y) = x^2+y^2-xy-2x-5y
        //return (Math.pow(Math.sin(2*Math.PI*inX),3)*Math.sin(2*Math.PI*inY))/(inX*inX*inX*(inX+inY));

        //return (Math.pow(Math.sin(2*Math.PI*x[0]),3)*Math.sin(2*Math.PI*x[1]))/(x[0]*x[0]*x[0]*(x[0]+x[1]));
        //return Math.pow(x[0],2)+Math.pow(x[1],2)-x[0]*x[1]-2*x[0]-5*x[1];// f(x,y) = x^2+y^2-xy-2x-5y

        //return (Math.pow(Math.sin(2*Math.PI*x[0]),3)*Math.sin(2*Math.PI*x[1]))/(x[0]*x[0]*x[0]*(x[0]+x[1]));

        //return Math.pow(x[0],2)+Math.pow(x[1],2)-x[0]*x[1]-2*x[0]-5*x[1];// f(x,y) = x^2+y^2-xy-2x-5y
        //return -2*x[0]+x[1];//f(x,y) = -2x+y;
        //return Math.pow(x[0],2)+Math.pow(x[1]-1,2);// f(X,Y) = X^2+(Y-1)^2
        double Value = 0;
        double value1 =0;
        double value2 =0;
        double qiexueSum=0;
        double qiexueM=9;

        for (int i =0;i<qiexueM;i++) {
            double x1 = x[0];
            double yx2 = Math.pow(qiexueData[i][0], x[1]);
            double bx2 = Math.pow(qiexueData[i][1], x[2]);
            double vx2 = Math.pow(qiexueData[i][2], x[3]);
            double Ti = qiexueData[i][3];
            qiexueSum += Math.pow(x1 * yx2 * bx2 * vx2 - Ti, 2);
        }
        double item=qiexueSum/(qiexueM-2);
        double Qx=Math.pow(item,0.5);
        Value=Qx;
        return Value;
    }

    /*
     * 设定最小值和最大值
     */
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

    /*
     * 判断是否是可行解
     */
    public boolean JudgeIfFeasibleSolution(double x[])
    {
        if(CheckConstraints(x)) return true;
        return false;
    }

/*    *//*
     * function:找到一个可行解
     * creator:Chen Xiaohua
     * datetime:2021-1-22
     * paramters:
     *    1)x[]:提供的值
     *    2)vNum:变量数量
     *    3)minX:最小值
     *    4)maxX:最大值
     * return：
     *    true:x[]为可行解
     *    false:未找到可行解
     *//*
    public boolean FindOneFeasibleSolution(double x[],double minX[])
    {

        Tools myTools = new Tools();

        for(int i=0;i<x.length;i++){
            if(x[i] < minX[i]) return false;
        }
        System.out.println("x[0]:"+x[0]+" x[1]:"+x[1]);
        if (CheckConstraints(x)){//x为可行解
            return true;
        }

        myTools.SaveFile("a.txt", "x[0]:"+x[0]+" x[1]:"+x[1]+" f\n", true);
        double x1[] = new double[x.length];
        for(int k=0;k<x1.length;k++) x1[k] = x[k];


        //for(int i=0;i<x.length;i++){
        for(int j=0;j<x.length;j++){
            //if(x[j]-0.1 >= minX[j]){
            //x1[i] = x1[i]-0.1;
            x1[j] = x1[j]-0.1;
            FindOneFeasibleSolution(x1,minX);

            //}
        }
        //}
        return false;
    }

    *//*
     * function:FindOneFSolution(double x[],double minX[],double maxX[])
     *//*
    public boolean FindOneFSolution(double x[],double minX[],double maxX[])
    {
        if (CheckConstraints(x)){//x为可行解
            return true;
        }
        for(int i=0;i<x.length;i++)
            x[i] = maxX[i];
        System.out.println("Start:");
        return FindOneFeasibleSolution(x,minX);
    }

    public void PrintX(double x[])
    {
        for(int i=0;i<x.length;i++)
            System.out.println(x[i]);
    }*/

    /*
     * function:根据一个全局可行解
     * creator:wang hong
     * date:2021/1/21
     * parameters：
     * 	1)init[]:初始值，
     *  2)versum:变量数
     * 	3)curver:当前变量的index
     * return：是否找到初始全局可行解
     */
    public boolean findinitsolution(double init[] ,int versum ,int curver,double min[],double max[])
    {
		/*
		Tools myTools = new Tools();
		String str = "";
		for(int i=0;i<init.length;i++) str += "x["+i+"]:"+init[i]+" ";
		str += "\f\n";
		myTools.SaveFile("a.txt", str, true);
		 */
        if (CheckConstraints(init)){
            return true;
        }
        if (versum > curver){
            if(init[curver] < min[curver]){
                while (init[curver] < max[curver]){
                    if(init[curver] < min[curver]) init[curver] = min[curver] - 0.1;
                    init[curver] +=1;
                    if (findinitsolution(init,versum ,curver+1,min,max)){
                        return true;
                    }
                }
            }else if (init[curver] > max[curver]){
                while (init[curver] > min[curver]){
                    if (init[curver] > max[curver]) init[curver] = max[curver]  + 0.1;
                    init[curver] -=0.1;
                    if (findinitsolution(init,versum ,curver+1,min,max)){
                        return true;
                    }
                }
            }else {
                double coreinit = init[curver];
                //向左找
                while (init[curver] >= min[curver]){

                    if (findinitsolution(init,versum ,curver+1,min,max)){
                        return true;
                    }
                    init[curver] -=0.1;
                }
                init[curver] = coreinit;
                //向右找
                while (init[curver] < max[curver]){

                    if (findinitsolution(init,versum ,curver+1,min,max)){
                        return true;
                    }
                    init[curver] +=0.1;
                }
            }
        }

        return false;
    }



    /*
     * function:根据初始值和方向(0左1右)求出可行解
     * creator:ye yaning
     * date:2020-12-30
     * parameters：
     *  1)varsSum:int固定值,表示变量个数
     *  2)x[]:double固定值,存放决策变量
     * 	3)direction:int方向0表示往左；1表示向右
     *  4)ret:double数组存储两可行解
     *  5)index:int判断变量是哪个需要寻找变量的可行解
     * return：boolean是否找到可行解和返回可行解数组
     */
    public boolean GetFeasibleSolution(int varsSum,double x[],int direction,int index,double ret[])
    {
        //double initFeasible = x[index];
        double temp[] = new  double[varsSum];
        boolean find = false;
        for(int i=0; i< varsSum; i++ ){
            temp[i] = x[i];
        }
        //初始值在最大边界和最下边界外求得可行解
        if(temp[index] > varsMaxValue[index]) { ret[0] = varsMaxValue[index];  temp[index] = ret[0]; }
        if(temp[index] < varsMinValue[index]) { ret[0] = varsMinValue[index];  temp[index] = ret[0]; }

        if(JudgeIfFeasibleSolution(temp)) {
            ret[0] = temp[index];
            return true;
        }


        if(direction == 0){//0从右向左<---,则initX尽量靠右
            find = false;
            while(temp[index] <= varsMaxValue[index]){
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
            temp[index] =varsMaxValue[index];
            if(JudgeIfFeasibleSolution(temp)) {
                ret[0] = varsMaxValue[index];
                return true;
            }
            //最大值都无法满足要求，则从右向左搜索
            while(temp[index] >= varsMinValue[index] + 0.000000000001){
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
            temp[index] =varsMinValue[index];
            if(JudgeIfFeasibleSolution(temp)) {
                ret[0] = temp[index];
                return true;
            }
        }

        if(direction == 1){//1从左向右---->,initX尽量靠左
            find = false;
            while(temp[index] >= varsMinValue[index]+0.000000000001){
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
            temp[index] =varsMinValue[index];
            if(JudgeIfFeasibleSolution(temp)) {
                ret[0] = varsMinValue[index];
                return true;
            }
            //最小值都无法满足要求，则从左向右搜索
            temp[index] = varsMinValue[index];
            while(temp[index] <= varsMaxValue[index]){
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
            temp[index] =varsMaxValue[index];
            if(JudgeIfFeasibleSolution(temp)) {
                ret[0] = varsMaxValue[index];
                return true;
            }
        }
        return false;
    }

    /*
     * function:根据一个可行解和方向(0左1右)求出另外一个可行解
     * creator:ye yaning
     * date:2020-12-30
     * parameters：
     * 	1)varsSum:int固定值,表示变量个数
     *  2)x[]:double固定值,存放决策变量
     * 	3)direction:int方向0表示往左；1表示向右
     *  4)ret:double数组存储两可行解
     *  5)index:int判断变量是哪个需要寻找变量的可行解
     * return：可行解
     */
    public boolean GetOtherFeasibleSolution(int varsSum,double x[],int direction,int index,double ret[])
    {
        boolean find = false;
        double temp[] = new  double[varsSum];
        for(int i=0; i< varsSum; i++ ){
            temp[i] = x[i];
        }

        double otherFX = temp[index];//另外一个可行解
        if (temp[index] == varsMaxValue[index]) otherFX = temp[index] - 0.01;
        else if (temp[index] == varsMinValue[index] ) otherFX = temp[index] + 0.01;

        if(direction == 0){//0从右向左<---
            find = false;
            otherFX = temp[index]-0.01;
            while (otherFX >= varsMinValue[index]+0.000000000001){
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
            otherFX = temp[index]-0.01;
            while (otherFX >= varsMinValue[index]+0.000000000001){
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
            otherFX = temp[index]+0.01;
            while(otherFX <= varsMaxValue[index]){
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
            otherFX = temp[index]+0.01;
            while(otherFX <= varsMaxValue[index]){
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
            otherFX = temp[index]-0.01;
            while(otherFX >= varsMinValue[index]+0.000000000001){
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
            otherFX = temp[index]-0.01;
            while (otherFX >= varsMinValue[index]+0.000000000001){
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

    /*
     * name:GetBalanceDot()
     * function:根据x1和x2以及方向(0左1右)，判断是否求出平衡点
     * creator:ye yaning
     * date:2020-12-30
     * parameters：
     * 	1)x1,x2:double两个可行解,如果direction=0,则向左，x1>=x2；如果direction=1,则向右,x1<=x2
     *  2)x[]:double决策变量数组
     * 	3)direction:int方向0表示往左；1表示向右
     * 	4)step:调整参数
     * 	5)double balanceX[]:平衡点
     *  6)double utX[]:差量值
     *  7)index:int判断变量是哪个需要寻找变量的可行解
     * return：true找到了平衡点curX，false未找到平衡点
     */
    public boolean GetBalanceDot(double x1,double x2,double x[],int index ,int direction,int step,double balanceX[],double utX[])
    {
        this.curValue =new double[varsSum];
        curValue[index] = x2;
        double temp1[] = new double[varsSum];
        double temp2[] = new double[varsSum];//决策变量x2和所有变量数组
        for(int i=0; i< varsSum; i++ ){
            temp1[i] = x[i];
            temp2[i] = x[i];
        }

        temp2[index] = x2;
        boolean highct = false; //判断函数值是否下降
//        System.out.println("direction:"+ direction+" ut:" + utX[0] + " curX:"+ curValue[index] +" x2:" + x2 +" x1:" + x1 + " p:"+ p +" q:" + q +" step:" + step);
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
        if (CheckConstraints(temp1)&&CheckConstraints(temp2)){//满足约束条件
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

    /*
     * name:CheckIfOptimal()
     * function:根据curoptimalx[]当前最优解数组和optimalx[]当前最优解数组，判断是否求出平衡点
     * creator:ye yaning
     * date:2021-1-8
     * creator:ye yaning
     * return: true表示继续寻找，false已经找到最优解
     */
    public boolean CheckIfOptimal(double curoptimalx[],double optimalx[]){
        int i ;
        for (i = 0; i<varsSum;i++){
            if(Math.abs(curoptimalx[i] - optimalx[i])>0.00000000000000001){
                return true;
            }
        }
        return false;
    }

    /*
     * name:GetOptimal()
     * function:根据initX,initY,step和direction找到最优解
     * creator:ye yaning
     * date:2020-12-30
     * updata:2021-1-18
     * parameters：
     *  1)initValue[]:double多个变量的初始值
     * 	2)x1,x2:double两个可行解,如果direction=0,则向左，x1>=x2；如果direction=1,则向右,x1<=x2
     * 	2)direction:int方向0表示往左；1表示向右
     * 	3)p,q,step:三个调整参数
     * 	4)double balanceDot[]:平衡点数组
     * 	5)optimalX[]:最优解及最优值数组
     * return：true找到了最优解，false未找到最优解
     */
    public boolean GetOptimal(double initValuex[],double optimalx[])
    {
        double[] utX = new double[1];
        double[] ret = new  double[2];
        double curoptimalx[] = new double[varsSum];
        double curoptimalotherx[] = new double[varsSum];
        double balanceDot[] = new  double[3];
        int direction = 0;
        utX[0] = 0.1;

        for (int i = 0; i<varsSum;i++){
            curoptimalx[i] = initValuex[i];
        }

        int index;

        while (CheckIfOptimal(curoptimalx,optimalx)){
            //while(Math.abs(curoptimalx[0] - optimalx[0])>0.00000000000000001 ||  Math.abs(curoptimalx[1] - optimalx[1])>0.00000000000000001 ){
            index = 0;
            while (index < varsSum) {
                GetFeasibleSolution(varsSum, curoptimalx, direction, index, ret);
                //while (index<varsSum){
                if(!GetFeasibleSolution(varsSum,curoptimalx,direction,index,ret)){
                    return false;
                }
                //}
                for (int i = 0; i < varsSum; i++) {
                    optimalx[i] = curoptimalx[i];
                }
                for (int i =0 ;i<varsSum;i++)    {
                    curoptimalotherx[i] = curoptimalx[i];
                }
                int step =2;
                while(true){//更改步长

                    int indexStep = 2;
                    balanceDot[0] = ret[0];
                    balanceDot[1] = -1;//向左平衡点
                    balanceDot[2] = -1;//向右平衡点
                    balanceDot[direction + 1] = ret[0];
                    curoptimalx[index] = balanceDot[0];

                    while (Math.abs(balanceDot[1] - balanceDot[2]) > 0.00000000000000001) {//寻找平衡点
                        //求两可行解
                        GetFeasibleSolution(varsSum, curoptimalx, direction, index, ret);

                        x2 = ret[0];
                        GetOtherFeasibleSolution(varsSum, curoptimalx, direction, index, ret);

                        x2 = ret[0];
                        x1 = ret[1];
                        //curoptimalx[index] = x2;

                        GetBalanceDot(x1, x2, curoptimalx, index, direction, step, balanceDot, utX);
                        curoptimalx[index] = balanceDot[0];

                        //根据方向求平衡点
                        if (direction == 0) {//向左
                            boolean find1;
                            find1 = false;
                            while (!find1) {
                                GetFeasibleSolution(varsSum, curoptimalx, direction, index, ret);

                                x2 = ret[0];
                                GetOtherFeasibleSolution(varsSum, curoptimalx, direction, index, ret);

                                x2 = ret[0];
                                x1 = ret[1];
                                //curoptimalx[index] = x2;

                                GetBalanceDot(x1, x2, curoptimalx, index, direction, step, balanceDot, utX);
                                curoptimalx[index] = balanceDot[0];
                                balanceDot[1] = balanceDot[0];
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
                                GetFeasibleSolution(varsSum, curoptimalx, direction, index, ret);

                                x2 = ret[0];
                                GetOtherFeasibleSolution(varsSum, curoptimalx, direction, index, ret);

                                x2 = ret[0];
                                x1 = ret[1];
                                GetBalanceDot(x1, x2, curoptimalx, index, direction, step, balanceDot, utX);
                                curoptimalx[index] = balanceDot[0];
                                balanceDot[2] = balanceDot[0];

                                if (utX[0] < 0.00000000000000001) {
                                    find1 = true;
                                }

                            }
                            direction = 0;
                            utX[0] = 0.1;
                        }
                    }
                    curoptimalx[index] = balanceDot[0];

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
                for (int i =0 ;i<varsSum;i++) {
                    curoptimalx[i] = curoptimalotherx[i];
                }
//                curoptimalx[index] = balanceDot[0];
                System.out.print("变量: " + index + " 当前平衡点: " );
                for (int i =0 ;i<varsSum;i++){
                    System.out.print(curoptimalx[i] + " ");
                }
                System.out.print("\n");
                direction = 0;
                index++;
                p = 0;
                q = 0;
                ret[0] = 0;
                ret[1] = 0;
            }
        }
        return true;
    }


    /*
     * name:GetOptimalInArea
     * function:根据分块找到最优解
     * 目前是两变量模块， s是比例系数
     */
    public boolean GetOptimalInArea(double doublX[],double optimalOther[],int n,double s) {

        double[] balanceDot = new double[3];
        int direction = 0;
        int step = 2;
        this.varsSum = varsSum;
        double[] optimalX = new double[varsSum];
        for(int i =0; i<varsSum;i++){
            optimalX[i] = optimalOther[i];
        }

/*        if(n > varsSum){
            return false;
        }
        else {
            GetOptimalInArea(doublX,optimalOther,n+1,s);
        }*/
        while (true) {
            if (doublX[0] < varsMinValue[0]) doublX[0] = varsMinValue[0];
            //先向左
            doublX[1] = initValue[1];
            while (true) {
                if (doublX[1] < varsMinValue[1]) doublX[1] = varsMinValue[1];
                if (GetOptimal(doublX, optimalX)) {
                    System.out.println(GetObjectiveValue(optimalX) + " " + optimalX[0] + " " + optimalX[1] + " initX:" + doublX[0] + " initY:" + doublX[1]);
                    if (GetObjectiveValue(optimalX) < GetObjectiveValue(optimalOther)) {
                        for(int i =0; i<varsSum;i++){
                            optimalOther[i] = optimalX[i];
                        }
                    }
                }
//                    System.out.println(GetObjectiveValue(optimalOther) + " " + optimalOther[0] + " " + optimalOther[1] + " initX:" + doublX[0] + " initY:" + doublX[1]);

                if (doublX[1] <= varsMinValue[1] + 0.1 && doublX[1] >= varsMinValue[1] - 0.1) break;
                doublX[1] /= s;
            }
            //后向右
            doublX[1] = initValue[1];
            while (true) {
                if (doublX[1] > varsMaxValue[1]) doublX[1] = varsMaxValue[1];
                GetOptimal(doublX, optimalX);
                if (GetOptimal( doublX, optimalX)) {
                    System.out.println(GetObjectiveValue(optimalX) + " " + optimalX[0] + " " + optimalX[1] + " initX:" + doublX[0] + " initY:" + doublX[1]);
                    if (GetObjectiveValue(optimalX) < GetObjectiveValue(optimalOther)) {
                        for(int i =0; i<varsSum;i++){
                            optimalOther[i] = optimalX[i];
                        }
                    }
                }
//                    System.out.println(GetObjectiveValue(optimalOther) + " " + optimalOther[0] + " " + optimalOther[1] + " initX:" + doublX[0] + " initY:" + doublX[1]);

                if (doublX[1] <= varsMaxValue[1] + 0.1 && doublX[1] >= varsMaxValue[1] - 0.1) break;
                doublX[1] *= s;
            }
            if (doublX[0] <= varsMinValue[0] + 0.1 && doublX[0] >= varsMinValue[0] - 0.1) break;

            doublX[0] /= s;
        }
        doublX[0] = initValue[0];
        doublX[1] = initValue[1];
        while (true) {
            //先向右
//                System.out.println("向右 initX:" + doublX[0] + " minX:" + varsMinValue[0] + " maxX:" + varsMinValue[0]);

            if (doublX[0] > varsMaxValue[0]) doublX[0] = varsMaxValue[0];

            System.out.println("initX:" + doublX[0]);

            //先向左
            doublX[1] = initValue[1];
            while (true) {
                if (doublX[1] < varsMinValue[1]) doublX[1] = varsMinValue[1];
                GetOptimal(doublX, optimalX);
                if (GetOptimal( doublX, optimalX)) {
                    System.out.println(GetObjectiveValue(optimalX) + " " + optimalX[0] + " " + optimalX[1] + " initX:" + doublX[0] + " initY:" + doublX[1]);
                    if (GetObjectiveValue(optimalX) < GetObjectiveValue(optimalOther)) {
                        for(int i =0; i<varsSum;i++){
                            optimalOther[i] = optimalX[i];
                        }
                    }
                }
//                    System.out.println(GetObjectiveValue(optimalOther) + " " + optimalOther[0] + " " + optimalOther[1] + " initX:" + doublX[0] + " initY:" + doublX[1]);

                if (doublX[1] <= varsMinValue[1] + 0.1 && doublX[1] >= varsMinValue[1] - 0.1) break;
                doublX[1] /= s;
            }
            //后向右
            doublX[1] = initValue[1];
            while (true) {
                if (doublX[1] > varsMaxValue[1]) doublX[1] = varsMaxValue[1];
                GetOptimal( doublX,  optimalX);
                if (GetOptimal( doublX,  optimalX)) {
                    System.out.println(GetObjectiveValue(optimalX) + " " + optimalX[0] + " " + optimalX[1] + " initX:" + doublX[0] + " initY:" + doublX[1]);
                    if (GetObjectiveValue(optimalX) < GetObjectiveValue(optimalOther)) {
                        for(int i =0; i<varsSum;i++){
                            optimalOther[i] = optimalX[i];
                        }
                    }
                }
//                    System.out.println(GetObjectiveValue(optimalOther) + " " + optimalOther[0] + " " + optimalOther[1] + " initX:" + doublX[0] + " initY:" + doublX[1]);

                if (doublX[1] <= varsMaxValue[1] + 0.1 && doublX[1] >= varsMaxValue[1] - 0.1) break;
                doublX[1] *= s;
            }

            if (doublX[0] <= varsMaxValue[0] + 0.1 && doublX[0] >= varsMaxValue[0] - 0.1) break;
            doublX[0] *= s;
        }
        return false;
    }

    /*
     * name:GetGlobalOptimal
     * function:根据initX、initY、step和direction找到全局最优解
     * creator:ye yaning
     * date:2020-12-30
     * parameters：
     * 	1)initX,initY:double 用户给定的初始值
     * 	2)direction:int方向0表示往左；1表示向右
     * 	3)p,q,step:三个调整参数
     * 	4)double balanceDot:平衡点
     * 	5)optimal[]:最优解的决策变量值及最优值
     * return：optimalOther[0];找到的全局最优解的值
     */
    public double[] GetGlobalOptimal(int varsSum,double initValue[])
    {
        double[] balanceDot = new double[3];
        int direction = 0;
        int step =2;
        this.varsSum = varsSum;
        double[] optimalX = new double[varsSum];
        double optimalOther[] = new double[varsSum];
        this.initValue = new double[varsSum];

        for (int i = 0 ; i < varsSum ; i++ ){
            this.initValue[i] = initValue[i];
        }

        double initX1[] = new  double[varsSum] ;

        for (int i = 0 ; i < varsSum ; i++ ){
            initX1[i] = initValue[i];
        }
/*
        1.求全局可行解
        GetAllFeasibleSolution(varsSum,doublX,0);

 */
/*        findinitsolution(initX1,varsSum,0);
        if (GetOptimal(balanceDot, initX1, direction, step, optimalX)) {
            for(int i =0; i<varsSum;i++){
                optimalOther[i] = optimalX[i];
            }
        }*/

        if (CheckConstraints(initX1)){
            //System.out.println(initX1[0]+" "+ initX1[1] + " 是可行解");
        }else {
            //System.out.println(initValue[0]+" "+ initValue[1] + "不可行解为:");
            for (int i = 0; i < varsSum; i++) {
                System.out.println(" " + initValue[i] + " ");
            }
            if(!findinitsolution(initX1,varsSum,0,varsMinValue,varsMaxValue)){
                double ret[] = new double[initX1.length];
                ret[0] = -1;
                return ret;
            }

            System.out.println("可行解为:");
            for (int i = 0; i < varsSum; i++) {
                System.out.println(" " + initX1[i] + " ");

            }
        }/*
		if (CheckConstraints(initX1)){
			System.out.println(initX1[0]+" "+ initX1[1] + "是可行解");
		}else {
			System.out.println(initX1[0]+" "+ initX1[1] + "不是可行解");
		}*//*
		if (GetOptimal(balanceDot, initX1, direction, step, optimalX)) {
			for (int i = 0; i < varsSum; i++) {
				optimalOther[i] = optimalX[i];
			}
		}*/

       /*        double[] doublX =new  double[varsSum];
        for (int i = 0 ; i < varsSum ; i++ ){
            doublX[i] = initValue[i];
        }
        while (true) {
            if (doublX[0] < varsMinValue[0]) doublX[0] = varsMinValue[0];
            //先向左
            doublX[1] = initValue[1];
            while (true) {
                if (doublX[1] < varsMinValue[1]) doublX[1] = varsMinValue[1];
                if (GetOptimal(balanceDot, doublX, direction, step, optimalX)) {
                    for(int i =0; i<varsSum;i++){
                        optimalOther[i] = optimalX[i];
                    }
                }
                if (doublX[1] <= varsMinValue[1] + 0.1 && doublX[1] >= varsMinValue[1] - 0.1) break;
                doublX[1] /= 1.5;
            }
            //后向右
            doublX[1] = initValue[1];
            while (true) {
                if (doublX[1] > varsMaxValue[1]) doublX[1] = varsMaxValue[1];
                GetOptimal(balanceDot, doublX, direction, step, optimalX);
                if (GetOptimal(balanceDot, doublX, direction, step, optimalX)) {
                    for(int i =0; i<varsSum;i++){
                        optimalOther[i] = optimalX[i];
                    }
                }
                if (doublX[1] <= varsMaxValue[1] + 0.1 && doublX[1] >= varsMaxValue[1] - 0.1) break;
                doublX[1] *= 1.5;
            }
            if (doublX[0] <= varsMinValue[0] + 0.1 && doublX[0] >= varsMinValue[0] - 0.1) break;

            doublX[0] /= 1.5;
        }*/

        //2.寻找最优解
        GetOptimalInArea(initX1,optimalOther,0,1.111);


        return optimalOther;
    }
}