package Feedback;

public class Fill_function {

    double objectiveValue;//目标函数
    double curObjective;//当前目标函数的值
    double curValue[];//当前值
    int varsSum;//变量数量
    double initValue[]; //初始值
    double varsMinValue[];//变量最小值
    double varsMaxValue[];//变量最大值
    int p;
    int q;
    double xk[]; //局部最小值 填充函数极大值x*
    double Ix[]; //填充函数&范围初始值
    //double max[];
    //double min[];

    public double GetObjectiveValue()
    {
        return curObjective;
    }

    /*判断函数值是否降低*/
    public boolean CheckCurObjective(double x[])
    {
        if(GetObjectiveValue(x)<=curObjective) return true;
        return false;
    }

    /*判断约束*/
    public boolean CheckConstraints(double x[])
    {
        /*double sum =0;
        for (int i=0;i<varsSum;i++)
            sum+=Math.pow(Ix[i]-xk[i],2);
        double t=Math.sqrt(sum);*/

        for(int i = 0 ;i<varsSum ; i++) {

            /*多变量无约束测试*/
            //if(x[i] > xk[i] + t/2 || x[i] < xk[i]- t/2){
                if(x[i] > this.varsMaxValue[i] || x[i] < this.varsMinValue[i]){
                //if(x[i] > 100 || x[i] < 0){
                return false;
            }

        }
        return true;
    }

    /*获取初始点*/
    public void SetInitValue(int inVarsSum ,double initValue[])
    {
        //varsSum = inVarsSum;
        this.initValue =new double[inVarsSum];
        for(int i=0;i<inVarsSum;i++){
            this.initValue[i] = initValue[i];
            this.initValue[i] = initValue[i];
        }
    }

    /*获取函数值*/
    public double GetObjectiveValue(double x[])
    {
        NonLinearMVModel_1 nlm = new NonLinearMVModel_1();
        double sum=0;
        double t;
        double P;
        //aim_func res=new aim_func();
        //return res.GetObjectiveValue8(x);

        double fxk = nlm.GetObjectiveValue(xk);
        double fx = nlm.GetObjectiveValue(x);
        int k =0;
        if (fx - fxk >=0){
            k = 1;
        }else if (fx - fxk <0) {
            k = 0;
        }
        for (int i=0;i<varsSum;i++) {
            sum += Math.pow(x[i] - xk[i], 2);
        }
        double norm = Math.sqrt(sum);
        P = Math.log(1+k)/(1+Math.pow(norm,2)) + Math.pow(Math.min(0,fx-fxk),3);
        return P;
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
    public boolean JudgeIfFeasibleSolution(double x[])
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
    public boolean FindInitSolution(double init[] ,int versum ,int curver,double min[],double max[])
    {

/*        Tools myTools = new Tools();
        String str = "";
        for(int i=0;i<init.length;i++) str += "x["+i+"]:"+init[i]+" ";
        str += "\f\n";
        myTools.SaveFile("a.txt", str, true);*/
        //System.out.println(init[0] + " " + init[1] + " " + curver + " " );
        if (CheckConstraints(init) && CheckBlockMinMax(init,versum,min,max)){
            //System.out.println("可行解1");
            return true;
        }
        if (versum > curver){
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
            otherFX = temp[index]- Tools.AreaOtherSolution;
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
            otherFX = temp[index]- Tools.AreaOtherSolution;
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
            otherFX = temp[index]+ Tools.AreaOtherSolution;
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
            otherFX = temp[index]+ Tools.AreaOtherSolution;
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
            otherFX = temp[index]- Tools.AreaOtherSolution;
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
            otherFX = temp[index]- Tools.AreaOtherSolution;
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
            //if (utX[0] > 0.00000000000000001) {
            //if (utX[0] > 0.000000000000001) {
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
                //if(Math.abs(curoptimalx[i] - optimalx[i])>0.00000000000000001){
                //if(Math.abs(curoptimalx[i] - optimalx[i])>0.000000000000001){
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

        for(step = 2 ; step<=varsMaxValue[index]/2; step++){//更改步长以初始点在此求最优

            if(!Tools.IsPrime(step)){//判断是否为素数步长
                continue;
            }
            //System.out.println(step);

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

            //while (Math.abs(balanceDot[1] - balanceDot[2]) > 0.00000000000000001) {//寻找平衡点
            while (Math.abs(balanceDot[1] - balanceDot[2]) > 0.000000000000001) {//寻找平衡点
                //while (Math.abs(balanceDot[1] - balanceDot[2]) > 0.00000000000000001) {//寻找平衡点
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
                //System.out.println("balanceDot[1]:"+ balanceDot[1] + " balanceDot[2]:" + balanceDot[2]);
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
            /*System.out.print("可行解 " );
            for (int i = 0; i < varsSum; i++) {//求得的数组curoptimalotherx为当前最优解，进行赋值并输出
                System.out.print(" index "+ i + " = "+ feasibleX[i] + " ");
            }*/
            //System.out.println();
            System.out.print("寻找填充函数极小化点->初始点：");
            for (int i = 0; i < varsSum; i++) {//求得的数组curoptimalotherx为当前最优解，进行赋值并输出
                System.out.print("X"+ i + " = "+ feasibleX[i] + " ");
            }
            System.out.println();
            for (int i = 0; i < varsSum; i++) {//块内可行解作为当前最优解
                curoptimalx[i]=feasibleX[i];
            }
        }else{//否则放弃这个块
            return false;
        }
/*        int k =0;
        int kteration = 100000;*/
        while (CheckIfOptimal(curoptimalx,optimalArea)) {//寻找块内最优解
            //k++;

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

/*            if(k>kteration){
                break;
            }*/
        }
        NonLinearMVModel_1 nlm = new NonLinearMVModel_1();
        System.out.println("F(x) = " +nlm.GetObjectiveValue(curoptimalx));
        return true;
    }


    /*寻找全局最优解*/
    /*
     * name:GetGlobalOptimal
     * function:寻找全局最优解
     * creator:ya ning
     * date:2021/1/28
     */
    public double[] GetGlobalOptimal(int n,double initValue[],double max[],double min[],double x1[],double inx[])
    {
        this.varsSum = n;
        varsMaxValue = new double[varsSum];
        varsMinValue = new double[varsSum];
        Ix = new double[varsSum];
        xk = new double[varsSum];
        for(int i=0;i<varsSum;i++){
            this.xk[i] = x1[i];
            this.Ix[i] = inx[i];
            this.varsMaxValue[i] = max[i];
            this.varsMinValue[i] = min[i];
        }

        double GlobalOptimal[] = new double[varsSum];
        double optimalArea[] = new double[varsSum];
        double AreaMin[] = new double[varsSum];
        double AreaMax[] = new double[varsSum];
        this.initValue = new double[varsSum];
        double initX[] = new  double[varsSum] ;

        int curver=0;
        for (int i = 0 ; i < varsSum ; i++ ){
            this.initValue[i] = initValue[i];
            initX[i] = initValue[i];
            AreaMax[i] = varsMaxValue[i];
            AreaMin[i] = varsMinValue[i];
        }

        FindInitSolution(initX, varsSum, curver, varsMinValue, varsMaxValue);
        for (int i = 0; i < varsSum; i++) {//防止最优解大于0，而最优解不设定初始值而默认为0
            GlobalOptimal[i] = initX[i];
            optimalArea[i] = GlobalOptimal[i]+0.01;
        }

        if(GetOptimalInArea(initX,optimalArea,AreaMin,AreaMax)){
            //System.out.println("1111");
            if (GetObjectiveValue(GlobalOptimal) > GetObjectiveValue(optimalArea)) {
                //System.out.println("2222");
                for (int k = 0; k < varsSum; k++) {
                    GlobalOptimal[k] = optimalArea[k];
                }
            }
        }else {
            System.out.println("未寻找到此块内可行解");
        }

        return GlobalOptimal;
    }

}
