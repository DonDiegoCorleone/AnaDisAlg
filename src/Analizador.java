import java.util.ArrayList;
import java.util.Arrays;

public class Analizador {
	
	private static long v1[];
	private static long v2[];
	/* 
	 * NOTA IMPORTANTE
	 * 
	 * Esta clase se proporciona solamente para ilustrar el formato de
	 * salida que deberia tener la solucion a este ejericio.
	 * Esta clase debe modificarse completamente para cumplir 
	 * mínimamente los requisitos de esta practica.
	 * Notese que ni siquiera esta completa......
	 */
	
	public static String masCercano(double ratio) {
			if (ratio < 1.5) {                      // aprox 1.0
				return "1";	
			} else if (1 <= ratio && ratio < 3.0) { // aprox 2.0
				return "LOG";	
			} else if (1 <= ratio && ratio < 3.0) { // aprox 2.0
				return "N";
			} else if (1 <= ratio && ratio < 3.0) { // aprox 2.0
				return "NLOG";
			} else if (3 <= ratio && ratio < 6.0) { // aprox 4.0
				return "N2";
			} else if (6 <= ratio && ratio <12.0) { // aprox 8.0
				return "N3";
			} else if (6 <= ratio && ratio < 10.0) { // aprox 8.0
				return "2N";
			} else { 								 // otras
				return "NF";
			}
	}

	private static final long[] p = {1,2,3,4,5,6,7,8,9,10,12,14,16,18,20,22,24,26,28,30,40,50,60,70,80,90,
			100,300,500,700,1000,3000,5000,7000,10000,30000,50000,70000,
			100000,200000,300000,500000,700000,800000,850000,900000,950000,
			1000000,1500000,2000000,2500000,3000000,3500000,5000000,7000000,
			10000000,30000000,50000000,800000000,90000000,
			100000000,500000000,999999999};

	private static final double[] uno={421800,800,300,400,300,300,300,300,300,300,300,300,
			200,300,300,300,300,500,300,300,300,200,300,400,300,300,300,300,300,300,200,
			200,300,300,300,300,300,300,300,300,300,300,300,300,300,200,200,300,300,300,
			300,200,300,300,300,300,300,300,300,300,300,700,300
	};
	//Tiempos para complejidad uno

	private static final long[] logn={510800,1000,500,400,300,400,300,300,300,300,300,300,400,
			400,700,500,400,400,400,300,300,700,500,500,500,400,600,700,700,700,500,500,500,500,
			500,500,500,500,500,500,500,500,400,400,500,500,400,500,500,500,600,600,500,500,500,500,
			600,500,600,600,500,1000,500
	};
	//Tiempos para complejidad logn

	private static final double[] n={471300,1000,1100,600,500,500,700,500,400,400,300,400,400,500,500,500,500,500,600,
			500,600,700,700,700,900,1000,1200,3500,3900,6000,15800,46800,59800,57000,78800,225700,671000,598400,596200,
			555000,109600,174800,298500,365700,363000,743300,423300,441400,686600,867000,1075300,1295100,1673600,2236100,3311200,
			4293200,13935300,24203900,352435100,41514500,46293800,226431600,439615300
	};
	//Tiempos para complejidad n

	private static final long[] n2={329800,1100,600,600,600,700,900,1000,1100,1300,1600,2100,4000,3000,3600,4900,4900,
			6300,7000,8100,13200,20600,29400,39400,50400,63900,78400,705100,1896100,699500,347000,3021200,10790100,21128900,
			44044300,398378100,1093916400,2104804500};
	//Tiempos para complejidad n2

	private static final double[] n3={334200,1800,1200,1200,1400,1800,2400,3200,6500,6800,8400,13500,18900,26500,
			35600,46500,59700,75300,93500,113100,263900,280700,171800,226200,341800,482800,660900,3865400,
			11118700,31096700,92011900};
	//Tiempos para complejidad n3

	private static final double[] e2n={369600,1500,1300,1700,2800,6300,16700,20400,39600,79500,
			422400,1360600,2293000,3669700,13692300,54747600,222822500,917437600};
	//Tiempos para complejidad 2n

	private static final double[] nf={333000,1400,1200,3500,19500,84100,630800,3265800,4843900,59017900};
	//Tiempos para complejidad nf

	private static final double[] nlogn={371300,4600,2500,2300,2800,3000,3500,3700,5900,5200,6300,14500,9900,11800,12400,146100,4300,
			3800,3900,245700,14700,8100,10100,11000,12700,13900,15500,106300,84900,119600,170800,549900,
			923000,1539300,1172800,3415700,4492500,5521600,8299500,16716400,23850500,43099700,60831100,69981500,
			79897900,79201200,81651800,88957300,141066600,183230700,223651100,274510100,317492900,463982300,662904200,
			964047000};
	//Tiempos para complejidad nlogn

	private static double[] ratioUno={};
	private static double[] ratioLogn={};
	private static double[] ratioN={};
	private static double[] ratioNlogn={};
	private static double[] ratioN2={};
	private static double[] ratioN3={};
	private static double[] ratio2N={};
	private static double[] ratioNf={};

	public static void main(String arg[]) {
		ratioUno=new double[63];
		ratioLogn=new double[63];
		ratioN=new double[63];
		ratioNlogn=new double[63];


		double mRatioUno = 0;
		double mRatioN = 0;
		double mRatioNlogn = 0;
		double mRatioLogn = 0;


		Temporizador t = new Temporizador();
		for(int i=0;i<63;i++) {
			t.iniciar();
			algo.f(p[i]);
			t.parar();
			ratioUno[i]=t.tiempoPasado()/uno[i];
			t.reiniciar();
		} //compara con c-1

		for(int i=0;i<ratioUno.length;i++){
			mRatioUno+=ratioUno[i];
		}
		mRatioUno=mRatioUno/(ratioUno.length);

		System.out.println(mRatioUno);


		for(int i=0;i<63;i++) {
			t.iniciar();
			algo.f(p[i]);
			t.parar();
			ratioLogn[i]=t.tiempoPasado()/logn[i];
			t.reiniciar();
		} //compara con c-1

		for(int i=0;i<ratioLogn.length;i++){
			mRatioLogn+=ratioLogn[i];
		}
		mRatioLogn=mRatioLogn/(ratioLogn.length);

		System.out.println(mRatioLogn+"\n");


		for(int i=0;i<63;i++) {
			t.iniciar();
			algo.f(p[i]);
			t.parar();
			ratioN[i]=t.tiempoPasado()/n[i];
			t.reiniciar();
		} //compara con c-n

		for(int i=0;i<ratioUno.length;i++){
			mRatioN+=ratioN[i];
		}
		mRatioN=mRatioN/(ratioN.length);

		System.out.println("\n"+mRatioN);

		for(int i=0;i<nlogn.length;i++) {
			t.iniciar();
			algo.f(p[i]);
			t.parar();
			ratioNlogn[i]=t.tiempoPasado()/nlogn[i];
			t.reiniciar();
		} //compara con c-nlogn

		for(int i=0;i<ratioNlogn.length;i++){
			mRatioNlogn+=ratioNlogn[i];
		}
		mRatioNlogn=mRatioNlogn/(ratioNlogn.length);

		System.out.println("\n"+mRatioNlogn);




		/*int n1 = 100000;
		int n2 = 200000;
		
		v1=new long[20];
		v2=new long[20];
	
		int tn1=0;
		Temporizador t = new Temporizador();
		while(tn1<20) {
			
			t.iniciar();
			algo.f(n1);
			t.parar();

			v1[tn1]=t.tiempoPasado();
			tn1++;
			
			t.reiniciar();
		}
		
		int tn2=0;
		while(tn2<20) {
			
			t.iniciar();
			algo.f(n2);
			t.parar();

			v2[tn2]=t.tiempoPasado();
			tn2++;
			
			t.reiniciar();
		}
		
	
		//double ratio = (double)t2/t1;
		System.out.println("T1: "+Arrays.toString(v1)+"\nT2: "+Arrays.toString(v2));
		//System.out.println(masCercano(ratio));*/
	}
}
