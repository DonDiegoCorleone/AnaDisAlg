// ALUMNO: DAVID HURTADO ORTEGA
// GRUPO:  2ºE

import java.util.*;


public class ejercicio8 implements Cloneable {
	
	// constante relativa al numero maximo de funciones que queremos aplicar
	protected static final int N=7;
	
	//--------------------------------------------------------------------------------------------
	//
	//
	//Si se desea probar el programa para otro array de funciones más largo o más corto, cambiar N 
	//
	//
	//--------------------------------------------------------------------------------------------

	protected String celdas; 
	
	public ejercicio8() {
		celdas = "";             
	}

	// crea una copia de su parametro
	public ejercicio8(ejercicio8 uno) {
		ejercicio8 otro = (ejercicio8) uno.clone();
		this.celdas = otro.celdas;
	} 

	
	
	public ejercicio8(String s) {
    	this();
    	if(s.length() >0) {
    		throw new RuntimeException("Construcción de string no válida");
    	} 		
    }
	
	

	public Object clone()  {
		ejercicio8 clon;
		try {
			clon = (ejercicio8) super.clone();
			clon.celdas = new String(); 
			for(int i=0; i<celdas.length(); i++)
				clon.celdas += celdas.charAt(i);
		} catch (CloneNotSupportedException e) {
			clon = null;
		}	
		return clon;
	}
	

	
	protected int[] buscaCandidatos(int numero) {         
		int[] validos = new int[2];
		
		validos[0]=numero/2;
		validos[1]=numero*3;
		
		return validos;
		
	}
	
	

	protected void resolverTodos(List<ejercicio8> soluciones, int n, int m,int contador) {
		if (contador <= N ) {                		
			if(n==m) {
				soluciones.add(new ejercicio8(this));
			}
			else if (n!=0){	                                
			
			int [] candidatos = buscaCandidatos(n);
			
			for(int i=0;i<=1;i++) {
				int numerocandidato= candidatos[i] ;
				
				ejercicio8 clon = new ejercicio8(this);

				if (i==1)
					clon.celdas+="f";
				else
					clon.celdas+="g";
				
				//System.out.println(clon.celdas);
				
				clon.resolverTodos(soluciones, numerocandidato, m,contador+1);
				
			}
		} 
	}
	
}
	
	public List<ejercicio8> resolverTodos(int n,int m) {
        List<ejercicio8> sols  = new LinkedList<ejercicio8>();
        resolverTodos(sols, n, m,0);                        //M, N y el contador inicializado a 0
		return sols;
	}
	
	public static String invertir(String a){
		StringBuilder strb = new StringBuilder(a);
		a = strb.reverse().toString();
		return a;
	}

	public static void main(String arg[]) {						
		int m=4;
		int n=15;
		ejercicio8 t = new ejercicio8(""); 
		List<ejercicio8> lt = t.resolverTodos(n,m);
		System.out.print("Se han encontrado ");
		System.out.println(lt.size()+" solucion/es posible/s usando "+N+" o menos funciones. Con n = "+n+" y m = "+m);
		
		for(Iterator<ejercicio8> i= lt.iterator(); i.hasNext();) {
			ejercicio8 ts = i.next(); 
			System.out.print(invertir(ts.celdas));                     
			System.out.println("("+n+")");
		}
		
		if(lt.size()==0) 
			System.out.println("No hay solución ");
		
		

	}
}