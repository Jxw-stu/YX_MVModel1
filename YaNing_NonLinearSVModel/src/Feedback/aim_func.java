package Feedback;

public class aim_func {

	public double sigmoid(double zi) {
		double fenzi = 1;
		double fenmu = (1 + Math.pow(Math.E, -zi));
		return fenzi / fenmu;
	}
	/*获取函数值*/

	//bp神经网络目标函数
	public double GetObjectiveValue100(double x[]) {
		double w1[][] = {{-1.7997184, -2.652359, -0.87416047},
				{-0.8246904, -1.1237719, -0.45217603},
				{-0.67125803, -1.2813694, -0.5124201},
				{-2.0824401, -3.0379853, -0.85570717},
				{0.44216174, 0.65016323, 0.44850305}};
		double w2[] = {-2.967962, -0.99628997, -1.1252046, -3.3123672, 2.0470862};
		double b1[] = {3.1938488, 0.08385954, 0.24470149, 3.5251305, -0.04041011};
		double b2 = 3.0383568;
		double m = 5;

		double z1[] = {0, 0, 0, 0, 0};
		for (int i = 0; i < m; i++) {
			z1[i] = x[0] * w1[i][0] + x[1] * w1[i][1] + x[2] * w1[i][2] + b1[i]; // 	X*wij+b1
			z1[i] = sigmoid(z1[i]);
		}
		double z2 = 0;
		for (int i = 0; i < m; i++) {
			z2 += z1[i] * w2[i];
		}
		double result_value = sigmoid(z2 + b2);
		return result_value;

	}

	//切削实验目标函数
	public double GetObjectiveValue200(int index, double x, double parameters[]) {
		double qiexueData[][] = {{1, 0.1, 60, 740.24},
				{1.41, 0.2, 60, 793.8},
				{2, 0.4, 60, 854.80},
				{1.41, 0.1, 94.8, 819.42},
				{2, 0.2, 94.8, 870.00},
				{1, 0.4, 94.8, 904.80},
				{2, 0.1, 150, 879.60},
				{1, 0.2, 150, 911.10},
				{1.41, 0.4, 150, 974.10}};

		double Value = 0;
		double value1 = 0;
		double value2 = 0;
		double qiexueSum = 0;
		double qiexueM = 9;
		parameters[index] = x;
		for (int i = 0; i < qiexueM; i++) {

			double x1 = parameters[0];
			double yx2 = Math.pow(qiexueData[i][0], parameters[1]);
			double bx2 = Math.pow(qiexueData[i][1], parameters[2]);
			double vx2 = Math.pow(qiexueData[i][2], parameters[3]);
			double Ti = qiexueData[i][3];
			qiexueSum += Math.pow(x1 * yx2 * bx2 * vx2 - Ti, 2);

		}
		double item = qiexueSum / (qiexueM - 2);
		double Qx = Math.pow(item, 0.5);

		Value = Qx;
		return Value;
	}

	//期货拟合实验目标函数
	public double GetObjectiveValue300(int index, double x, double parameters[]) {
		double qhData[][] = {{1,5424.5},
				{2,5424},
				{3,5423},
				{4,5419.5},
				{5,5414},
				{6,5411},
				{7,5411},
				{8,5414.5}};

		double Value = 0;
		double value1 = 0;
		double value2 = 0;
		double value3 = 0;
		double qhSum = 0;
		double qhM = 8;
		parameters[index] = x;
		for (int i = 0; i < qhM; i++) {

			double a = parameters[0];
			double b = parameters[1];
			double c = parameters[2];
			double d = parameters[3];
			value1 = a*Math.sin(qhData[i][0]);
			value2 = b*Math.pow(qhData[i][0],2);
			value3 = c*qhData[i][0];
			double Ti = qhData[i][1];
			qhSum += Math.pow(value1+value2+value3+d - Ti, 2);

		}
		double item = qhSum / (qhM - 2);
		double Qx = Math.pow(item, 0.5);
		Value = Qx;
		return Value;
	}

	//平移函数
	public double[] ping_yi(double pra1[], double num) {
		int len = pra1.length;
		double[] item1=new double[len];
		for (int i = 0; i < len; i++) {
			item1[i] = pra1[i] - num;
		}
		return item1;
	}

	//f1
	public double GetObjectiveValue1(double pra[]) {
		int N = pra.length;
		double[] x= new double[N];
		x = ping_yi(pra, 100);

		double res = 0;
		for (int i = 0; i < N; i++) {
			res += Math.pow(x[i], 2);
		}
		return res;
	}

	//f2
	public double GetObjectiveValue2(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 10);

		double res = 0;
		for (int i = 0; i < N; i++) {
			res += Math.pow(x[i], 2) * (i + 1);
		}
		return res;
	}

	//f3
	public double GetObjectiveValue3(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 10);

		double res = 0;
		double area1 = 0;
		double area2 = 1;

		for (int i = 0; i < N; i++) {
			area1 += Math.abs(x[i]);
		}
		for (int i = 0; i < N; i++) {
			area2 *= Math.abs(x[i]);
		}
		res = area1 + area2;
		return res;
	}

	//f4
	public double GetObjectiveValue4(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 1);

		double res = 0;
		for (int i = 0; i < N; i++) {
			res += Math.pow(x[i], 2);
		}
		res = -Math.exp(res * (-0.5));
		return res;
	}

	//f5
	public double GetObjectiveValue5(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 100);

		double res = 0;
		double area1 = 0;
		double area2 = 0;
		area1 = Math.pow(x[0], 2);
		for (int i = 1; i < N; i++) {
			area2 += Math.pow(x[i], 2);
		}
		res = area1 + Math.pow(10, 6) * area2;
		return res;
	}

	//f6
	public double GetObjectiveValue6(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res = 0;
		for (int i = 0; i < N; i++) {
			res += Math.pow(Math.floor(x[i] + 0.5), 2);
		}
		return res;
	}

	//f7
	public double GetObjectiveValue7(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 5);

		double res = 0;
		double area1 = 0;
		double area2 = 0;
		double area3 = 0;
		for (int i = 0; i < N; i++) {
			area1 += Math.pow(x[i], 2);
		}
		for (int i = 0; i < N; i++) {
			area2 += Math.pow(0.5 * (i + 1) * x[i], 2);
		}
		for (int i = 0; i < N; i++) {
			area3 += Math.pow(0.5 * (i + 1) * x[i], 4);
		}
		res = area1 + area2 + area3;
		return res;
	}

	//f8
	public double GetObjectiveValue8(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 30);

		double res = 0;
		double area = 0;
		for (int i = 0; i < N - 1; i++) {
			area += 100 * Math.pow(x[i + 1] - Math.pow(x[i], 2), 2) + Math.pow((x[i] - 1), 2);
		}
		res = area;
		return res;
	}

	//f9
	public double GetObjectiveValue9(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 600);

		double res = 0;
		double area1 = 0;
		double area2 = 1;
		for (int i = 0; i < N; i++) {
			area1 += Math.pow(x[i], 2);
			area2 *= Math.cos(x[i] / Math.sqrt(i + 1));
		}
		res = 1 + (1 / 4000) * area1 - area2;
		return res;
	}

	//f10
	public double GetObjectiveValue10(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 100);

		double res = 0;
		double area1 = 0;
		double area2 = 0;
		double area = 0;
		for (int i = 0; i < N - 1; i++) {
			area1 = (Math.pow(Math.pow(x[i], 2) + Math.pow(x[i + 1], 2), 0.25));
			area2 = (Math.pow(Math.sin(50 * (Math.pow((Math.pow(x[i], 2) + Math.pow(x[i + 1], 2)), 0.1))), 2) + 1);
			area += area1 * area2;
		}
		res = area;
		return res;
	}

	//f11
	public double GetObjectiveValue11(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 500);

		double res = 0;
		for (int i = 0; i < N; i++) {
			res += x[i] * Math.sin(Math.sqrt(Math.abs(x[i])));
		}
		res = -res;
		return res;
	}

	//f12
	public double GetObjectiveValue12(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 5);

		double res = 0;
		for (int i = 0; i < N; i++) {
			res += Math.pow(x[i], 4) - 16 * Math.pow(x[i], 2) + 5 * x[i];
		}
		res = Math.pow(N, -1) * res;
		return res;
	}

	//f13(x) = π/N(10 sin2(πy1) +N−1 Pi=1(yi − 1)2[1+10 sin2(πyi+1)] + (yN − 1)2), yi = 1 + 1/4 (xi + 1)
	public double GetObjectiveValue13(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 10);

		double PI = Math.PI;
		double[] y = new double[N];
		for (int i = 0; i < N; i++) {
			y[i] = 1 + 1 / 4 * (x[i] + 1);
		}
		double area1 = 10 * Math.pow(Math.sin(PI * y[0]), 2);
		double area2 = 0;
		for (int i = 0; i < N - 1; i++) {
			area2 += Math.pow((y[i] - 1), 2) * (1 + 10 * Math.pow(Math.sin(PI * y[i + 1]), 2));
		}
		double area3 = Math.pow(y[N - 1] - 1, 2);
		double res = (PI / N)* (area1 + area2 + area3);
		return res;
	}

	//f14
	public double GetObjectiveValue14(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 5);
		double PI = Math.PI;

		double area1 = Math.pow(Math.sin(3 * PI * x[0]), 2);
		double area2 = 0;
		for (int i = 0; i < N - 1; i++) {
			area2 += Math.pow((x[i] - 1), 2) * (1 + Math.pow(Math.sin(PI * 3 * x[i + 1]), 2));
		}
		double area3 = Math.pow(x[N - 1] - 1, 2) * (1 + Math.pow(Math.sin(2 * PI * x[N - 1]), 2));
		double res = 0.1 * (area1 + area2 + area3);
		return res;
	}

	//f15
	public double GetObjectiveValue15(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 30);

		double PI = Math.PI;
		double area1 = 0;
		double area2 = 0;
		for (int i = 0; i < N; i++) {
			area1 += Math.pow(x[i], 2);
			area2 += Math.cos(2 * PI * x[i]);
		}
		double area3 = -20 * Math.exp(-0.02 * Math.sqrt((Math.pow(N, (-1)) * area1))) - Math.exp((Math.pow(N, (-1)) * area2)) + 20 + Math.E;
		double res = area3;
		return res;
	}

	//f16
	public double GetObjectiveValue16(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 5);

		double PI = Math.PI;
		double area1 = 0;
		double area2 = 0;
		for (int i = 0; i < N; i++) {
			area1 += Math.pow(x[i], 2) - 10 * Math.cos(2 * PI * x[i]);
		}
		double res = 10 * N + area1;
		return res;
	}

	//f17
	public double GetObjectiveValue17(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 50);
		double PI = Math.PI;
		double[] y = new double[N];
		for (int i = 0; i < N; i++) {
			y[i] = 1 + 1 / 4 * (x[i] + 1);
		}

		double area1 = 0;
		for (int i = 0; i < N - 1; i++) {
			area1 += Math.pow((y[i] - 1), 2) * (1 + Math.sin(PI * y[i + 1]));
		}
		area1 += Math.pow(y[N - 1] - 1, 2) + 10 * Math.pow(Math.sin(PI * y[0]), 2);

		double area2 = 0;
		double a = 10;
		double k = 100;
		double m = 4;
		for (int i = 0; i < N; i++) {
			double item = 0;
			if (x[i] > a) {
				item = k * Math.pow((x[i] - a), m);
			} else if (-a <= x[i] && x[i] <= a) {
				item = 0;
			} else if (x[i] < -a) {
				item = k * Math.pow((-x[i] - a), m);
			}
			area2 += item;
		}
		double res = area1 + area2;

		return res;
	}

	//f18
	public double GetObjectiveValue18(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 50);
		double PI = Math.PI;

		double area1 = 0;
		double area11 = Math.pow(Math.sin(3 * PI * x[0]), 2);
		double area12 = 0;
		for (int i = 0; i < N - 1; i++) {
			area12 += Math.pow((x[i] - 1), 2) * (1 + Math.pow(Math.sin(3 * PI * x[i + 1]), 2));
		}

		double area13 = Math.pow(x[N - 1] - 1, 2) + Math.pow(Math.sin(PI * 2 * x[N - 1]), 2);

		area1 = 0.1 * (area11 + area12 + area13);

		double area2 = 0;
		double a = 5;
		double k = 100;
		double m = 4;
		for (int i = 0; i < N; i++) {
			double item = 0;
			if (x[i] > a) {
				item = k * Math.pow((x[i] - a), m);
			} else if (-a <= x[i] && x[i] <= a) {
				item = 0;
			} else if (x[i] < -a) {
				item = k * Math.pow((-x[i] - a), m);
			}
			area2 += item;
		}
		double res = area1 + area2;

		return res;
	}

	//f19
	public double GetObjectiveValue19(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 900);

		double area1 = 0;
		double area2 = 0;
		double area3 = 0;
		for (int i = 0; i<N; i++) {
			area1 += Math.pow(x[i]-1,2);
		}
		for (int i = 1; i<N; i++) {
			area2 += x[i]*x[i];
		}
		/*area3 =(N*(N+4)*(N-1))/6;
		double res = area1+area2+area3;*/
		double res = area1-area2;
		return res;
	}

	//f20
	public double GetObjectiveValue20(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 10);

		double area1 = 0;
		double area2 = 0;
		for (int i = 0; i<N; i++) {
			area1 += Math.abs(x[i] * Math.sin(x[i]) + 0.1 * x[i]);
		}
		double res = area1;
		return res;
	}

	//f21
	public double GetObjectiveValue21(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double area1 = 0;
		for (int i = 0; i<N; i++) {
			area1 += Math.sin(x[i])*Math.pow(Math.sin( ((i+1)*x[i]*x[i])/Math.PI),20 );
		}
		double res = -area1;
		return res;
	}

	//f22
	public double GetObjectiveValue22(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 4.5);

		double area1 = 0;
		double area2 = 0;
		double area3 = 0;
		area1 =Math.pow(1.5-x[0]+x[0]*x[1],2);
		area2 =Math.pow(2.25-x[0]+x[0]*x[1]*x[1],2);
		area3 =Math.pow(2.65-x[0]+x[0]*x[1]*x[1]*x[1],2);
		double res = area1 + area2 + area3;
		return res;
	}

	//f23
	public double GetObjectiveValue23(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 1.28);

		double area1 = 0;
		for (int i = 0; i<N; i++) {
			area1 += (i+1)*Math.pow(x[i],4);
		}
		double res = area1+Math.random();
		return res;
	}

	//f24
	public double GetObjectiveValue24(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 100);

		double area1 = 0;
		area1 =-0.3*Math.cos(3*Math.PI*x[0])-0.4*Math.cos(4*Math.PI*x[1]);
		double res = Math.pow(x[0],2)+2*Math.pow(x[1],2)+area1+0.7;
		return res;
	}

	//f25
	public double GetObjectiveValue25(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 10);

		double area1 = 0;
		double area2 = 0;
		double area3 = 0;
		area1 =Math.pow(Math.sin(3*Math.PI*x[0]),2);
		area2 =(1+ Math.pow(Math.sin(3*x[1]),2))*Math.pow((x[0]-1),2);
		area3 =(1+ Math.pow(Math.sin(2*Math.PI*x[1]),2))*Math.pow((x[0]-1),2);
		double res = area1 + area2 + area3;
		return res;
	}

	//f26
	public double GetObjectiveValue26(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 100);

		double area1 = 0;
		double area2 = 0;
		area1 =Math.pow(x[0]*x[0]+x[1]*x[1],0.25);
		area2 =50*Math.pow(x[0]*x[0]+x[1]*x[1],0.1)+1;
		double res = area1 * area2;
		return res;
	}

	//f27
	public double GetObjectiveValue27(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 100);

		double area1 = 0;
		double area2 = 0;
		area1 =-Math.cos(x[1])*Math.cos(x[0]);
		area2 =Math.exp(-Math.pow((x[0]-Math.PI),2)-Math.pow((x[1]-Math.PI),2));
		double res = area1 * area2;
		return res;
	}

	//f28
	public double GetObjectiveValue28(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 10);

		double area1 = 0;
		double area2 = 0;
		for (int i = 1; i<=5; i++) {
			area1 += i*Math.cos((i+1)*x[0]+i);
			area2 += i*Math.cos((i+1)*x[1]+i);
		}
		double res = area1 * area2;
		return res;
	}

	//f29
	public double GetObjectiveValue29(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 10);

		double area1 = 0;
		double area2 = 0;
		area1 = Math.pow((x[0]+2*x[1]-7),2);
		area2 = Math.pow((2*x[0]+x[1]-5),2);
		double res = area1 + area2;
		return res;
	}

	//f30
	public double GetObjectiveValue30(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 2);

		double area1 = 0;
		double area2 = 0;
		area1 = (19-14*x[0]+3*x[0]*x[0]-14*x[1]+6*x[0]*x[1]+3*x[1]*x[1])*Math.pow((x[0]+x[1]+1),2)+1;
		area2 = (18-32*x[0]+12*x[0]*x[0]+48*x[1]-36*x[0]*x[1]+27*x[1]*x[1])*Math.pow((2*x[0]-3*x[1]),2)+30;
		double res = area1 * area2;
		return res;
	}

	//f31
	public double GetObjectiveValue31(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 10);

		double area1 = 0;
		double area2 = 0;
		area1 = 0.26*(x[0]*x[0]+x[1]*x[1]);
		area2 = 0.48*x[0]*x[1];
		double res = area1 - area2;
		return res;
	}

/*	//f32
	public double GetObjectiveValue32(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 4);

		double area1 = 0;
		double area2 = 0;
		double area3 = 0;
		double area4 = 0;
		for (int i = 1; i<=8; i++) {
			area1 += Math.pow(x[4*i-3]+10*x[4*i-2],2);
			area2 += 5*Math.pow(x[4*i-1]-x[4*i],2);
			area3 += Math.pow(x[4*i-2]-2*x[4*i-1],4);
			area4 += 10*Math.pow(x[4*i-3]-x[4*i],4);
		}
		double res = area1 + area2 + area3 + area4;
		return res;
	}*/

	//f32
	public double GetObjectiveValue32(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 1);

		double area1 = 0;
			area1 = Math.pow(x[0]*x[0]+x[1]*x[1]-2*x[0],2);
		double res = area1 + 0.25*x[0];
		return res;
	}

	//f33
	public double GetObjectiveValue33(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 1.2);

		double area1 = 0;
		double area2 = 0;
		area1 = 100*Math.pow(x[1]-Math.pow(x[0],3),2);
		area2 =Math.pow(1-x[0],2);
		double res = area1 + area2;
		return res;
	}

    //f34
    public double GetObjectiveValue34(double pra[]) {
        int N = pra.length;
        double[] x=new double[N];
        x = ping_yi(pra, 5.12);

        double area1 = 0;
        for (int i = 0; i<N; i++) {
            area1 += Math.pow(x[i],2) - 10*Math.cos(2*Math.PI*x[i]);
        }
        double res = area1 + 10*30;
        return res;
    }

    //f35
    public double GetObjectiveValue35(double pra[]) {
        int N = pra.length;
        double[] x=new double[N];
        x = ping_yi(pra, 2);

        double area1 = 0;
        for (int i = 0; i<N-1; i++) {
            area1 += 100*Math.pow((x[i+1]-x[i]*x[i]),2)+Math.pow((1-x[i+1]),2);
        }
        double res = area1;
        return res;
    }

    //f36
	public double GetObjectiveValue36(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 500);

		double res = 0;
		for (int i = 0; i < N; i++) {
			res += x[i] * Math.sin(Math.sqrt(Math.abs(x[i])));
		}
		res =-res;
		return res;
	}

	//f37
	public double GetObjectiveValue37(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 10);
		double PI = Math.PI;

		double area1 = 0;
		double area11 = Math.pow(Math.sin(3 * PI * x[0]), 2);
		double area12 = 0;
		for (int i = 0; i < N - 1; i++) {
			area12 += Math.pow((x[i] - 1), 2) * (1 + Math.pow(Math.sin(3 * PI * x[i + 1]), 2));
		}

		double area13 = (Math.abs(x[N - 1] - 1)) * (1+Math.pow(Math.sin(PI * 3 * 30), 2));

		double res = area11 + area12 + area13;

		return res;
	}

	public double GetObjectiveValue38(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 50);
		double PI = Math.PI;
		double[] y = new double[N];
		for (int i = 0; i < N; i++) {
			y[i] = 1 + (1 / 4) * (x[i] +1);
		}

		double area1 = 0;
		double area2 = 0;
		double area3 = 0;
		for (int i = 0; i < N - 1; i++) {
			area2 += Math.pow((y[i] - 1), 2) * (1 + 10*Math.pow(Math.sin(PI * y[i + 1]),2));
		}
		area1 = 10 * Math.pow(Math.sin(PI * y[0]), 2);
		area3 =Math.pow(y[N-1]-1,2);
		double res = (PI/N)*(area1 + area2+area3);
		return res;
	}

	public double GetObjectiveValue221(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.pow(Math.sin(2*Math.PI*x[0]),3)*(Math.sin(2*Math.PI*x[0]*x[0])/Math.pow(x[0],3));
		return res;
	}

	public double GetObjectiveValue222(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= (Math.pow(Math.cos(10*x[0]),5) - 2*Math.pow(Math.cos(10*x[0]),3))/Math.pow(x[0],(double)5/3);
		return res;
	}

	public double GetObjectiveValue223(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.sin(2*x[0]+1)+3*Math.sin(4*x[0]+3)+5*Math.sin(6*x[0])+5;
		return res;
	}

	public double GetObjectiveValue224(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.abs((x[0]-1)*Math.sin(x[0]));
		return res;
	}

	public double GetObjectiveValue225(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.pow(x[0],3)-Math.pow(x[0],2)-x[0];
		return res;
	}
	public double GetObjectiveValue226(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.pow(x[0],3)-3*x[0]+2;
		return res;
	}

	public double GetObjectiveValue227(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.pow(Math.E,-x[0]*x[0])*(x[0]+Math.sin(x[0]));
		return res;
	}

	public double GetObjectiveValue228(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= -1/((Math.pow(x[0]-2,2)+3))-1/((3*Math.pow(x[0]-5,2)+4))-1/(2*(x[0]-1)*(x[0]-1)+1);
		return res;
	}

	public double GetObjectiveValue229(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.pow((0.1*x[0]-10),3)+Math.pow((0.1*x[0]-5),-5);
		return res;
	}

	public double GetObjectiveValue230(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.pow((x[0]-10),2)+Math.pow(x[0],0.5);
		return res;
	}
	public double GetObjectiveValue231(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= x[0]*x[0]-Math.log(x[0])-5;
		return res;
	}

	public double GetObjectiveValue232(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.pow(x[0]*x[0]+x[0]-1,2)+Math.pow(2*x[0]*x[0]-3,2);
		return res;
	}


	public double GetObjectiveValue233(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res = Math.sin(x[0])+Math.sin(10*x[0]/3)+Math.log(x[0])-0.84*x[0];
		return res;
	}


	public double GetObjectiveValue234(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res = Math.sin(x[0])+Math.sin(2*x[0]/3);
		return res;
	}

	public double GetObjectiveValue235(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 10);

		double res =0;
		for (int i = 1; i < 6; i++) {
			res+= Math.sin((i+1)*x[0]+i);
		}
		return -res;
	}

	public double GetObjectiveValue236(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.pow(x[0],4)-8.5*Math.pow(x[0],3)-31.0625*Math.pow(x[0],2)-7.5*x[0]+45;
		return res;
	}

	public double GetObjectiveValue237(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= (Math.pow(x[0]+2,2))*(x[0]+4)*(x[0]+5)*(x[0]+8)*(x[0]-16);
		return res;
	}

	public double GetObjectiveValue238(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.exp(x[0])-3*Math.pow(x[0],2);
		return res;
	}

	public double GetObjectiveValue239(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= Math.cos(x[0])+Math.pow(x[0]-2,2);
		return res;
	}
	public double GetObjectiveValue240(double pra[]) {
		int N = pra.length;
		double[] x=new double[N];
		x = ping_yi(pra, 0);

		double res =0;
		res= 10.2/x[0]+6.2*Math.pow(x[0],3);
		return res;
	}
}

