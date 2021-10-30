package Feedback;


public class NonLinearSVModel<GetObjectiveValue> {
    double objectiveValue;//Ŀ�꺯��
    double curObjective;//��ǰĿ�꺯����ֵ
    double curX;//��ǰxֵ
    double minX;//��ǰ��Χ��Сֵ
    double maxX;//��ǰ��Χ���ֵ
    double initX;//�趨��ʼֵ
    int p;//�����Ĵ󲽲���
    int q;//������С������
    int step ;//�����ķ���
    double x1;//��ʾ���߱���x��ǰֵ
    double x2;//��ʾ���߱���x�ĺ�ֵ,x1->x2��������뷽��direction���ʹ��
    int direction;//0��ʾ����1��ʾ����
    double balanceX;//ƽ���
    double ut;//��ʾ����֮��Ĳ���
    boolean ct;//ct��ʾ�Ƿ�����Լ������
    //wl

    /*
     * ����Ŀ�꺯����ֵ
     */
    public double GetObjectiveValue()
    {
        return curObjective;
    }

    /*
     * ��⵱ǰxֵ�µ�Ŀ��ֵ�����е�Ŀ��ֵ�Ƚϣ�
     * ���С�ڵ������е�Ŀ��ֵ����Ϊtrue������Ϊfalse��
     */
    public boolean CheckCurObjective(double x)
    {
        if(GetObjectiveValue(x)<=curObjective) return true;
        return false;
    }
    /*
     * ����Ƿ�����Լ������
     */
    public boolean CheckConstraints(double x)
    {
        if(x>=0 && x<=100)
        //if((Math.sqrt(x)+x*x*x)*(Math.sqrt(x)+x*x*x)>=4650&&x<=100)
        //if(Math.sin(0.1*x)*Math.sin(0.1*x)*Math.exp(0.1*x)>=4&&x<=1000)
        //if(10*Math.sin(0.1*x-0.5)>=0&&50*Math.pow((x+0.1),(-1))<=40)
        //if(10*Math.cos(0.1*x)*Math.cos(0.1*x)+10*Math.sin(0.1*x)>=12)
        {
            return true;
        }
        return false;
    }

    /*
     * �趨��ʼֵ
     */
    public void SetInitValue(double x)
    {
        initX = x;
    }

    /*
     * function:GetObjectiveValue�õ�Ŀ�꺯����ֵ
     * parameters:
     * 	inX:double���߱���
     * return:doubleĿ�꺯��
     */
    public double GetObjectiveValue(double inX)
    {
        //return inX;
        //return Math.pow(Math.sin(2*Math.PI*inX),3)*(Math.sin(2*Math.PI*inX*inX)/Math.pow(inX,3));//������(0,10]
        //return inX*inX*inX - inX*inX - inX;//������[0,5]
        //return (Math.pow(Math.cos(10*inX),5) - 2*Math.pow(Math.cos(10*inX),3))/Math.pow(inX,(double)5/3);//������[0.16,10]
        //return Math.pow((0.1*inX-10),3)+Math.pow((0.1*inX-5),-5);//������[54.1,200]
        //return Math.pow((inX-10),2)+Math.pow(inX,(double)1/2);//������[0,50]����ʵ�ʲ��Խӽ�9.99999999999�޷��õ����������ԭ��
        //return Math.sin(2*inX+1)+3*Math.sin(4*inX+3)+5*Math.sin(6*inX)+5;
        return Math.pow(inX,3)-3*inX+2;
        //return inX*inX-Math.log(inX)-5;
        //return (Math.exp(-inX*inX))*(inX+Math.sin(inX));//return Math.pow(Math.E,-inX*inX)*(inX+Math.sin(inX));[0,10]
        //return (inX*inX+inX-1)*(inX*inX+inX-1)+(2*inX*inX-3)*(2*inX*inX-3);//[0,5]
        //return -1/((Math.pow(inX-2,2)+3))-1/((3*Math.pow(inX-5,2)+4))-1/(2*(inX-1)*(inX-1)+1);
        //return Math.abs((inX-1)*Math.sin(inX));
        //return Math.pow(inX,3)-Math.pow(inX,2)-inX;
        //return inX*inX-10*inX+24;

    }

    /*
     * �趨��Сֵ�����ֵ
     */
    public void SetMaxMinValue(double inMinX,double inMaxX)
    {
        minX = inMinX;//��ǰ��Χ��Сֵ
        maxX = inMaxX;//��ǰ��Χ���ֵ
    }

    /*
     * �ж��Ƿ��ǿ��н�
     */
    public boolean JudgeIfFeasibleSolution(double x)
    {
        if(CheckConstraints(x)) return true;
        return false;
    }

    /*
     * function:���ݳ�ʼֵ�ͷ���(0��1��)������н�
     * creator:chen xiaohua
     * date:2020-12-11
     * parameters��
     * 	1)initX:double��ʼ�⣬�����ǿ��н⣬�����ǲ����н�
     * 	2)direction:int����0��ʾ����1��ʾ����
     * return�����н�
     */
    public boolean GetFeasibleSolution(double initX,int direction,double ret[])
    {
        boolean find = false;
        //��ʼֵ�����߽�����±߽�����ÿ��н�
        if(initX > maxX) {ret[0] = maxX; initX = ret[0];}
        if(initX < minX) {ret[0] = minX; initX = ret[0];}

        if(JudgeIfFeasibleSolution(initX)) {
            ret[0] = initX;
            return true;
        }


        if(direction == 0){//0��������<---,��initX��������
            find = false;
            while(initX <= maxX){
                if(initX > 0) initX *= 2;//initX����0�����ҿ���
                else if(initX == 0) initX = 0.01;//initX����0ʱ�����ҿ���
                else if(initX >= -0.000001 && initX < 0) initX = 0;//initXС��0����������ӽ�0ʱ������Ϊ0
                else if(initX < -0.000001) initX /= -2;//initXС��0�����

                if(JudgeIfFeasibleSolution(initX)) {
                    find = true;
                    break;
                }
            }
            if(find) {
                ret[0] = initX;
                return true;
            }
            if(JudgeIfFeasibleSolution(maxX)) {
                ret[0] = maxX;
                return true;
            }
            //���ֵ���޷�����Ҫ���������������
            initX = maxX;
            while(initX >= minX){
                if(initX > 0) initX /= 2;//initX>0�����󿿽�
                else if(initX < 0.000001 && initX > 0) initX = 0;//initX>0�����󿿽�
                else if(initX == 0) initX = -0.01;//initX==0�����󿿽�
                else if(initX < 0) initX *= 2;//initX<0�����󿿽�
                if(JudgeIfFeasibleSolution(initX)) {
                    find = true;
                    break;
                }
            }
            if(find) {
                ret[0] = initX;
                return true;
            }
            if(JudgeIfFeasibleSolution(minX)) {
                ret[0] = minX;
                return true;
            }
        }

        if(direction == 1){//1��������---->,initX��������
            find = false;
            while(initX >= minX){
                if(initX > 0) initX /= 2;//initX>0,���󿿽�
                else if(initX < 0.000001 && initX > 0) initX = 0;//initX>0,���󿿽�
                else if(initX == 0) initX = -0.01;//initX==0,���󿿽�
                else if(initX < -0.000001) initX *= 2;//initX<0,���󿿽�
                //else if(initX >= -0.000001 && initX < 0) initX = 0;//initX in [-0.000001,0],���󿿽�
                if(JudgeIfFeasibleSolution(initX)) {
                    find = true;
                    break;
                }
            }
            if(find) {
                ret[0] = initX;
                return true;
            }
            if(JudgeIfFeasibleSolution(minX)) {
                ret[0] = minX;
                return true;
            }
            //��Сֵ���޷�����Ҫ���������������
            initX = minX;
            while(initX <= maxX){
                if(initX > 0) initX *= 2;//initX>0�����ҿ���
                else if(initX > -0.000001 && initX < 0) initX = 0;//initX>0�����ҿ���
                else if(initX == 0) initX = 0.01;//initX==0�����ҿ���
                else if(initX < 0) initX /= 2;//initX<0�����ҿ���
                if(JudgeIfFeasibleSolution(initX)) {
                    find = true;
                    break;
                }
            }
            if(find) {
                ret[0] = initX;
                return true;
            }
            if(JudgeIfFeasibleSolution(maxX)) {
                ret[0] = maxX;
                return true;
            }
        }
        return false;
    }

    /*
     * function:����һ�����н�ͷ���(0��1��)�������һ�����н�
     * creator:chen xiaohua
     * date:2020-12-15
     * update Name:yeyaning
     * date:2020-12-16
     * reason:first modify the code
     * update Name:chen xiaohua
     * date:2020-12-16
     * reason:�����˷�������
     * parameters��
     * 	1)initX:double��ʼ�⣬�����ǿ��н⣬�����ǲ����н�
     * 	2)direction:int����0��ʾ����1��ʾ����
     * return�����н�
     */
    public boolean GetOtherFeasibleSolution(double initFeasibleX,int direction,double ret[])
    {
        boolean find = false;
        double otherFX = initFeasibleX;//����һ�����н�
        if(initFeasibleX == maxX) otherFX = initFeasibleX-0.001;
        else if(initFeasibleX == minX) otherFX = initFeasibleX+0.001;

        /*if(direction == 0){//0��ʾ����
            otherFX = initFeasibleX-0.01;
            if(JudgeIfFeasibleSolution(otherFX) && GetObjectiveValue(initFeasibleX) < GetObjectiveValue(otherFX) && initFeasibleX < otherFX){
                ret[0] = initFeasibleX;
                ret[1] = otherFX;
                return true;
            } else if(JudgeIfFeasibleSolution(otherFX) && GetObjectiveValue(initFeasibleX) > GetObjectiveValue(otherFX) && initFeasibleX > otherFX){
                ret[0] = otherFX;
                ret[1] = initFeasibleX;
                return true;
            }
        } else if(direction == 1){//1��ʾ����
            otherFX = initFeasibleX+0.01;
            if(JudgeIfFeasibleSolution(otherFX) && GetObjectiveValue(initFeasibleX) > GetObjectiveValue(otherFX) && initFeasibleX < otherFX){
                ret[0] = initFeasibleX;
                ret[1] = otherFX;
                return true;
            } else if(JudgeIfFeasibleSolution(otherFX) && GetObjectiveValue(initFeasibleX) < GetObjectiveValue(otherFX) && initFeasibleX > otherFX){
                ret[0] = otherFX;
                ret[1] = initFeasibleX;
                return true;
            }
        }
*/
        if(direction == 0){//0��������<---
            find = false;
            otherFX = initFeasibleX-0.001;
            while (otherFX >= minX+0.000000000001){
                if(JudgeIfFeasibleSolution(otherFX)) {
                    if(GetObjectiveValue(initFeasibleX) > GetObjectiveValue(otherFX)){
                        find = true;
                        break;
                    }
                }
                otherFX /=2;
            }
            if(find) {
                ret[0] = otherFX;
                ret[1] = initFeasibleX;
                return true;
            }
            //��1-2֮���һ����Χ
            find = false;
            otherFX = initFeasibleX-0.001;
            while (otherFX >= minX+0.000000000001){
                if(JudgeIfFeasibleSolution(otherFX)) {
                    if(GetObjectiveValue(initFeasibleX) > GetObjectiveValue(otherFX)){
                        find = true;
                        break;
                    }
                }
                otherFX /=1.5;
            }
            if(find) {
                ret[0] = otherFX;
                ret[1] = initFeasibleX;
                return true;
            }




            //��Сֵ���޷�����Ҫ���������������
            otherFX = initFeasibleX+0.001;
            while(otherFX <= maxX){
                if(JudgeIfFeasibleSolution(otherFX)) {
                    if(GetObjectiveValue(initFeasibleX) > GetObjectiveValue(otherFX)){
                        find = true;
                        break;
                    }
                }
                otherFX *=2;
            }
            if(find) {
                ret[0] = initFeasibleX;//����仯�ˣ��Ӵ������ң�����directionû�б仯�����޸���ret[0]��ret[1]
                ret[1] = otherFX;
                return true;
            }
        }

        if(direction == 1){//1��������---->
            find = false;
            otherFX = initFeasibleX+0.001;
            while(otherFX <= maxX){
                if(JudgeIfFeasibleSolution(otherFX)) {
                    if(GetObjectiveValue(initFeasibleX) > GetObjectiveValue(otherFX)){
                        find = true;
                        break;
                    }
                }
                otherFX *= 2;
            }
            if(find) {
                ret[0] = otherFX;
                ret[1] = initFeasibleX;
                return true;
            }
            //��Сֵ���޷�����Ҫ���������������
            otherFX = initFeasibleX-0.001;
            while(otherFX >= minX+0.000000000001){
                if(JudgeIfFeasibleSolution(otherFX)) {
                    if(GetObjectiveValue(initFeasibleX) > GetObjectiveValue(otherFX)){
                        find = true;
                        break;
                    }
                }
                otherFX /=2;
            }
            if(find) {
                ret[0] = initFeasibleX;//����仯�ˣ��������󣬵���directionû�б仯�����޸���ret[0]��ret[1]
                ret[1] = otherFX;
                return true;
            }
            //��1-2֮���һ����Χ
            find = false;
            otherFX = initFeasibleX-0.001;
            while (otherFX >= minX+0.000000000001){
                if(JudgeIfFeasibleSolution(otherFX)) {
                    if(GetObjectiveValue(initFeasibleX) > GetObjectiveValue(otherFX)){
                        find = true;
                        break;
                    }
                }
                otherFX /=1.5;
            }
            if(find) {
                ret[0] = initFeasibleX;
                ret[1] = otherFX;
                return true;
            }
        }
        return false;
    }

    public double GetValid(double min,double max)
    {
        return 1;
    }

    /*
     * name:GetBalanceDot()
     * function:����x1��x2�Լ�����(0��1��)���ж��Ƿ����ƽ���
     * creator:chen xiaohua
     * date:2020-12-19
     * update Name:yeyaning
     * date:2020-12-19
     * reason:����Ѱ��ƽ�⵱ǰ��ͺ���������ֵ���Ƿ��
     * update Name:yeyaning
     * date:2020-12-20
     * reason:�޸��߼��ж�
     * parameters��
     * 	1)x1,x2:double�������н�,���direction=0,������x1>=x2�����direction=1,������,x1<=x2
     * 	2)direction:int����0��ʾ����1��ʾ����
     * 	3)p,q,step:������������
     * 	4)double balanceX[]:ƽ���
     * return��true�ҵ���ƽ���curX��falseδ�ҵ�ƽ���
     */
    public boolean GetBalanceDot(double x1,double x2,int direction,int step,double balanceX[],double utX[])
    {
        curX = x2;
        boolean highct = false; //�жϺ���ֵ�Ƿ��½�
        System.out.println("direction:"+ direction+" ut:" + utX[0] + " curX:"+ curX +" x2:" + x2 +" x1:" + x1 + " p:"+ p +" q:" + q +" step:" + step);
        if(utX[0] > 0.00000000000000001) {
            if(direction == 0){//direction����0��ʾ��������x1>x2////�ȼ���curX������������㣬��仯p��q��x1��x2
                curX = curX/Math.pow(step,p) - (x1-x2)/Math.pow(step,q);

            } else if(direction == 1){//direction����1��ʾ���ң�����x2>x1////�ȼ���curX������������㣬��仯p��q��x1��x2
                curX = curX*Math.pow(step,p) - (x1-x2)/Math.pow(step,q);

            }
        } else {

            balanceX[0] = curX;
            return true;//����ƽ���curX
        }

        if(CheckConstraints(curX)){//����Լ������
            ct = true;
            if(GetObjectiveValue(curX)<=GetObjectiveValue(x2)){//���㺯��ֵ�½�
                highct = true;
            }else {
                highct = false;
            }
        }else {//������Լ������
            ct =false;
        }


        if(ct == true) {//Լ����������
            if (highct == true) {
                p++;
                q = 0;
            } else if (highct == false) {
                curX = x2;
                q++; p=0;
            }
        }else {
            curX = x2;
            q++; p=0;
        }

        if(ct == true && highct == true){//Լ����������
            utX[0] = Math.abs(curX-x2);//��ʾ����֮��Ĳ���
            balanceX[0] = curX;
            //ut=Math.abs(curX-x2);//��ʾ����֮��Ĳ���
            if(direction == 0){//direction����0��ʾ��������x1>x2
                x1 = x2;
                x2 = curX;
            } else if(direction == 1){//direction����1��ʾ���ң�����x1<x2
                x1 = x2;
                x2 = curX;
            }
        }

        return false;
    }


    /*
     * name:GetOptimal()
     * function:����x1��x2��step��direction�ҵ����Ž�
     * creator:wang hong
     * date:2020-12-19
     * update Name:yeyaning
     * date:2020-12-20
     * reason:�޸���ƽ���洢
     * parameters��
     * 	1)x1,x2:double�������н�,���direction=0,������x1>=x2�����direction=1,������,x1<=x2
     * 	2)direction:int����0��ʾ����1��ʾ����
     * 	3)p,q,step:������������
     * 	4)double balanceDot:ƽ���
     * 	5)optimal[]:���Ž�ľ��߱���ֵ������ֵ
     * return��true�ҵ������Ž⣬falseδ�ҵ����Ž�
     */
    public boolean GetOptimal(double balanceDot[],double initX,int direction,int step,double optimal[])
    {
        //double[] balanceX = new double[3];
        double[] utX = new double[1];
        double[] ret = new  double[2];
        int changesum = 0;//��¼����任����
        int i = 1,j=1;
        int iteration =1000;
        int jteration =1000;
        utX[0] =0.1;
        //1�����ݳ�ʼֵ���ҵ���ʼ���н�initX;
        GetFeasibleSolution(initX,direction,ret);
        balanceDot[0] = ret[0];
        balanceDot[1] = -1;//����ƽ���
        balanceDot[2] = -1;//����ƽ���
        balanceDot[direction+1] = ret[0];
        //6�������������н�ͷ������ƽ��㲻�䣬��ƽ��㼴�����Ž�
        while(Math.abs(balanceDot[1]-balanceDot[2])>0.00000000000000001){  //4����ƽ���Ϊ�µĵ㣬�ٴ����ƽ��㣬ֱ��ƽ��㲻�ܼ�������
            //2�����ݳ�ʼ���н⣬�Լ������ҵ�����һ�����н�
            GetFeasibleSolution(balanceDot[0],direction,ret);
            x2 = ret[0];
            GetOtherFeasibleSolution(x2,direction,ret);
            x2 = ret[0];
            x1 = ret[1];
            //3�������������н�ͷ�����ƽ���
            GetBalanceDot(x1,x2,direction,step,balanceDot,utX);

            if(direction == 0){
                //System.out.println("������");
                boolean find1;
                find1 = false;
                //while(i<iteration){
                while(!find1){
                    //while(i<iteration){
                    GetFeasibleSolution(balanceDot[0],direction,ret);
                    x2 = ret[0];
                    GetOtherFeasibleSolution(x2,direction,ret);
                    x2 = ret[0];
                    x1 = ret[1];
                    GetBalanceDot(x1,x2,direction,step,balanceDot,utX);
                    balanceDot[1] = balanceDot[0];

                    //System.out.println("x1:"+x1+" x2:"+x2+" dir:"+direction+" step:"+step);

                    if(utX[0]<0.00000000000000001){
//                        System.out.println("<------���������Ѱ balanceDot[1]��"+ balanceDot[1]);
                        //i=1;
                        //break;
                        find1 = true;
                    }
                    //i++;
                }
                direction =1;
                utX[0]=0.1;
            }else if(direction == 1){
                boolean find1;
                find1 = false;
                //while(i<iteration){
                while(!find1){
                    GetFeasibleSolution(balanceDot[0],direction,ret);
                    x2 = ret[0];
                    GetOtherFeasibleSolution(x2,direction,ret);
                    x2 = ret[0];
                    x1 = ret[1];
                    GetBalanceDot(x1,x2,direction,step,balanceDot,utX);
                    balanceDot[2] = balanceDot[0];
                    if(utX[0]<0.00000000000000001){
//                        System.out.println("------->���������Ѱ balanceDot[2]��"+ balanceDot[2]);
                        //i=1;
                        find1 = true;
                        //break;
                    }
                    //i++;
                }
                direction =0;
                utX[0]=0.1;
            }
            System.out.println("���һ����Ѱ��balanceDot[1]:"+ balanceDot[1] + " balanceDot[2]:" + balanceDot[2]);
            j++;
            if(j>jteration){//����Ϊĳ��ֵʱ�޷���Ѱ����ƽ��㣬�����Ѱ��������������ƣ������˲�����ƽ���
                break;
            }
        }
//        System.out.println("");
//        System.out.println("-----------------------------------------");
        optimal[0]=balanceDot[0];
        return false;
    }

    /*
     * name:GetGlobalOptimal
     * function:����initX��step��direction�ҵ�ȫ�����Ž�
     * creator:hong wang
     * date:2020-12-20
     * parameters��
     * 	1)initX:double �û������ĳ�ʼֵ
     * 	2)direction:int����0��ʾ����1��ʾ����
     * 	3)p,q,step:������������
     * 	4)double balanceDot:ƽ���
     * 	5)optimal[]:���Ž�ľ��߱���ֵ������ֵ
     * return��optimalOther[0];�ҵ���ȫ�����Ž��ֵ
     */
    public double GetGlobalOptimal(double initX)
    {
        System.out.println(initX);
        double[] balanceX = new double[3];
        double x1;
        double x2;
        int direction;
        int step;
        double optimal[] = new double[1];
        double optimalOther[] = new double[1];
        int indexStep;
        indexStep = step  = 2;
        direction = 0;
        this.initX = initX;
        optimalOther[0]=optimal[0] = initX;
        //1�����ݳ�ʼֵ���ҵ���ʼ���н�
        //2�����ݳ�ʼ���н⣬�Լ������ҵ�����һ�����н�
        //3�������������н�ͷ�����ƽ���
        //4����ƽ���Ϊ�µĵ㣬�ٴ����ƽ��㣬ֱ��ƽ��㲻�ܼ�������
        //5����������������һ�����н�
        //6�������������н�ͷ������ƽ��㲻�䣬��ƽ��㼴�Ǿֲ����Ž�
        //7�����Ĳ����ظ�1~6�����ó�ȫ�����Ž�


        GetOptimal(balanceX,initX,direction,step,optimal);//���ݳ�ʼ����һ�����Ž⣻
        optimalOther[0] = optimal[0];

        //���Ĳ��������ȫ�����Ž�
        for (step = 3;step <= maxX/2; step++){//���Ĳ����Գ�ʼ���ڴ�������
            if(!Tools.IsPrime(step)){//�ж��Ƿ�Ϊ��������
                continue;
            }

            GetOptimal(balanceX,initX,direction,step,optimal);//���ݳ�ʼ����һ�����Ž⣻
            if (GetObjectiveValue(optimalOther[0]) > GetObjectiveValue(optimal[0])){
                optimalOther[0] = optimal[0];
                indexStep = step;
            }

            System.out.println("��step:" + step + "ʱ����ǰȫ�����Ž⣺x=" + optimal[0] +"\n");
        }

        System.out.println("step=" + indexStep + "ʱ��ý�����");
        return optimalOther[0];
    }
}


