////////////////////////////////////////////////////////////////////////////////////////////
// ALUMNO:
// GRUPO:
////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public final class BuscaElem extends Ordenacion{


	public static <T extends Comparable<? super T>> int partir(T v[], T pivote, int izq, int der) {
		int pivoteT=izq;
		int i=izq+1;
		int j=der;

		while(i<j) {
			while(v[j].compareTo(v[pivoteT])>0)j--;
			while(v[i].compareTo(v[pivoteT])<0)i++;

			if(i<j)intercambiar(v,i,j);
		}
		if(v[pivoteT].compareTo(v[j])>0)intercambiar(v,pivoteT,j);

		return j;
	}
	
	public static <T extends Comparable<? super T>> T kesimo(T v[], int k) {
		return kesimoRec(v,0,v.length-1,k);
	}

	public static <T extends Comparable<? super T>> T kesimoRec(T v[], int izq, int der, int k) {
		if (izq < der) {
			int s = OrdenacionRapida.partir(v, v[izq], izq, der);
			if (k == s) {
				return v[k];
			} else if (k < s) {
				return kesimoRec(v, izq, s - 1, k);
			} else {
				return kesimoRec(v, s + 1, der, k);
			}
		}
		return v[k];
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int maxvector;
		int i,k;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce el numero de posiciones del vector: ");
		maxvector=sc.nextInt();
		Integer v[]=new Integer[maxvector];

		System.out.print("Introduce "+maxvector+" enteros separados por espacios: ");
		for (i=0;i<maxvector;i++) v[i]=sc.nextInt();
		System.out.print("Introduce la posicion k deseada (de 1-"+maxvector+"): ");k=sc.nextInt();
		Integer elem=kesimo(v,k-1);
		System.out.print("El elemento "+k+"-esimo es: "+elem);
	}

}
