import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

	private static final double[] n = {1,2,3,4,5,6,7,8,9,10,12,14,16,18,20,22,24,26,28,30,40,50,60,70,80,90,
			100,300,500,700,1000,3000,5000,7000,10000,30000,50000,70000,
			100000,200000,300000,500000,700000,800000,850000,900000,950000,
			1000000,1500000,2000000,2500000,3000000,3500000,5000000,7000000};

	private static double[] tiempos = {};
	private static double[] ratio = {};

	public static boolean esGrande(){ //ignora los primeros valores
		double media1=0;
		double media2=0;
		boolean esNF=false;
		Temporizador t = new Temporizador();

		int tam=12;

		for(int i = 0;i<tam;i++){
			t.iniciar();
			Complejidad.nf(n[i]);
			t.parar();
			tiempos[i]=t.tiempoPasado();
			System.out.println(t.tiempoPasado());
			if(i!=0)System.out.println(t.tiempoPasado()/tiempos[i-1]);

			if(i!=0) {
				if ((t.tiempoPasado() / tiempos[i - 1]) > 10) {
					i = tam;
					esNF = true;
				}
			}

			t.reiniciar();
		}

		if(!esNF) {

			for (int i = 2; i <= (tam / 2); i++) {
				media1 += tiempos[i];
			}
			for (int i = ((tam / 2) + 1); i < tam; i++) {
				media2 += tiempos[i];
			}


			double Mmedia = media2 / media1;
			System.out.println(Mmedia);
			if ((media2 / media1) < 1.7) {
				return false;
			} else {
				if (Mmedia < 3.5) {
					System.out.print("NLOG o N2");
				} else if (Mmedia < 8) {
					System.out.print("N3");
				} else {
					System.out.print("2N");
				}

				return true;
			}

		}
		System.out.print("NF");
		return true;
	}

	public static double media(double V[]){  //ignora el primer valor
		double media=0;
		for(int i = 1;i<V.length;i++){
			media+=V[i];
		}
		return media/(V.length-1);

	}

	// uno-media -> 380<
	// logn-media -> 1200<
	// n-media -> 100000<
	// nlogn-media -> resto

	// n2-esGrande -> 1.7>
	// nlogn-esGrande -> 1.7>
	// n3-esGrande -> 3,5>
	// 2n-esGrande -> 8>
	// nf-esGrande -> no termina

	public static void main(String arg[]) {


		tiempos=new double[63];
		double media=0;
		Temporizador t = new Temporizador();

		if(!esGrande()) {

			for (int i = 0; i < n.length; i++) {
				t.iniciar();
				Complejidad.n3(n[i]);
				t.parar();
				tiempos[i] = t.tiempoPasado();
				t.reiniciar();
			}


			media=media(tiempos);
			System.out.println(media);
			if(media<180){
				System.out.print("1");
			}else if(media<1200){
				System.out.print("LOGN");
			}else if(media<1000000){
				System.out.print("N");
			}else{
				System.out.print("NLOGN");
			}
		}



	/*
		tiempos=new double[63];

		double ratio=0;

		Temporizador t = new Temporizador();

		for(int i = 0;i<n.length;i++){
			t.iniciar();
			Complejidad.uno(n[i]);
			t.parar();
			tiempos[i]=t.tiempoPasado();
			t.reiniciar();
		}

		tiempos.

		System.out.println("\n");
		ratio=0;

		for(int i = 0;i<n.length;i++){
			t.iniciar();
			Complejidad.logn(n[i]);
			t.parar();
			System.out.print(" "+t.tiempoPasado());
			t.reiniciar();
		}

		System.out.println(ratio/n.length);

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
		*/
	
		//double ratio = (double)t2/t1;
		//System.out.println(masCercano(ratio));*/
	}
}
