// ALUMNO:
// GRUPO: 

import java.util.*;


public class TableroSudoku implements Cloneable {
	
	// constantes relativas al nº de filas y columnas del tablero
	protected static final int MAXVALOR=9; 
	protected static final int FILAS=9; 
	protected static final int COLUMNAS=9; 
							 
	protected static Random r = new Random();
	
	protected int celdas[][]; // una celda vale cero si est\u00E1 libre.
	
	public TableroSudoku() {
		celdas = new int[FILAS][COLUMNAS]; //todas a cero.
	}

	// crea una copia de su par\u00E1metro
	public TableroSudoku(TableroSudoku uno) {
		TableroSudoku otro = (TableroSudoku) uno.clone();
		this.celdas = otro.celdas;
	}

	// crear un tablero a parir de una configuraci\u00D3n inicial (las celdas vac\u00EDas
	// se representan con el caracter ".".
    public TableroSudoku(String s) {
    	this();
    	if(s.length() != FILAS*COLUMNAS) {
    		throw new RuntimeException("Construcci\u00D3n de sudoku no v\u00E1lida.");
    	} else {
    		for(int f=0;f<FILAS;f++) 
				for(int c=0;c<COLUMNAS;c++) {
					Character ch = s.charAt(f*FILAS+c);
					celdas[f][c] = (Character.isDigit(ch) ? Integer.parseInt(ch.toString()) : 0 ); 
				}		
		}		
    }

	
	/* Realizar una copia en profundidad del objeto
	 * @see java.lang.Object#clone()
	 */
	public Object clone()  {
		TableroSudoku clon;
		try {
			clon = (TableroSudoku) super.clone();
			clon.celdas = new int[FILAS][COLUMNAS]; 
			for(int i=0; i<celdas.length; i++)
				System.arraycopy(celdas[i], 0, clon.celdas[i], 0, celdas[i].length);
		} catch (CloneNotSupportedException e) {
			clon = null;
		}	
		return clon;
	}
	
	/* Igualdad para la clase
	 * @see java.lang.Object#equals()
	 */
	public boolean equals(Object obj) {
		if (obj instanceof TableroSudoku) {
			TableroSudoku otro = (TableroSudoku) obj;
			for(int f=0; f<FILAS; f++)
				if(!Arrays.equals(this.celdas[f],otro.celdas[f]))
					return false;
			return true;		
		} else
			return false;
	}
	


	public String toString() {
		String s = "";

		for(int f=0;f<FILAS;f++) {
			for(int c=0;c<COLUMNAS;c++) 
				s += (celdas[f][c]==0 ? "." : String.format("%d",celdas[f][c])); 
		}
		return s;	
	}


	// devuelva true si la celda del tablero dada por fila y columna est\u00E1 vac\u00EDa.
	protected boolean estaLibre(int fila, int columna) {
		return celdas[fila][columna] == 0;
	}
	
	// devuelve el número de casillas libres en un sudoku.
	protected int numeroDeLibres() {
		int n=0;
	    for (int f = 0; f < FILAS; f++) 
	        for (int c = 0; c < COLUMNAS; c++)
	        	if(estaLibre(f,c))
	        		n++;
	    return n;
	}
	
	protected int numeroDeFijos() {
		return FILAS*COLUMNAS - numeroDeLibres();
	}

	// Devuelve true si @valor ya esta en la fila @fila.
	protected boolean estaEnFila(int fila, int valor) {
		boolean estaV=false;

		for(int i=0;i<COLUMNAS;i++){
			if (valor == celdas[fila][i]) {
				estaV = true;
				break;
			}
		}
		return estaV;
	}    

	// Devuelve true si @valor ya esta en la columna @columna.
	protected boolean estaEnColumna(int columna, int valor) {
		boolean estaV=false;

		for(int i=0;i<FILAS;i++){
			if (valor == celdas[i][columna]) {
				estaV = true;
				break;
			}
		}
		return estaV;
	}    
	

	// Devuelve true si @valor ya esta en subtablero al que pertence @fila y @columna.
	protected boolean estaEnSubtablero(int fila, int columna, int valor) {
		boolean estaV=false;

		int f=(fila-(fila%3));
		int limF=f+3;
		int c=(columna-(columna%3));
		int limC=c+3;
		int cAux=(columna-(columna%3));
		while(f<limF){
			while(c<limC){
				if(celdas[f][c]==valor)estaV=true;
				c++;
			}
			c=cAux;
			f++;
		}
		return estaV;
	}    

	
	// Devuelve true si se puede colocar el @valor en la @fila y @columna dadas.
	protected boolean sePuedePonerEn(int fila, int columna, int valor) {
		if(estaEnColumna(columna,valor) || estaEnFila(fila,valor) || estaEnSubtablero(fila, columna, valor)) return false;
		else return true;
	}
	
	
	

	protected void resolverTodos(List<TableroSudoku> soluciones, int fila, int columna) {
		if(estaLibre(fila, columna)) {
			for (int i=1;i<=MAXVALOR;i++){
				if(sePuedePonerEn(fila,columna,i)){
					celdas[fila][columna]=i;

					//ultima fila y columna
					if(fila==FILAS-1 && columna==COLUMNAS-1){
						soluciones.add(new TableroSudoku(this));
					}

					//ultima columna de la fila
					else if(fila<FILAS-1 && columna==COLUMNAS-1)
						resolverTodos(soluciones,fila+1,0);
					//avanza columnas
					else resolverTodos(soluciones,fila,columna+1);

					//para que se pueda a volver a evaluar la celda con distinto numero
					celdas[fila][columna]=0;
				}
			}
			//no esta libre
		}else{
			if(fila==FILAS-1&&columna==COLUMNAS-1){

				soluciones.add(new TableroSudoku(this));
			}else if(fila<FILAS-1 && columna==COLUMNAS-1)
				resolverTodos(soluciones,fila+1,0);
			else resolverTodos(soluciones,fila,columna+1);
		}
	}
	

	public List<TableroSudoku> resolverTodos() {
        List<TableroSudoku> sols  = new LinkedList<TableroSudoku>();
        resolverTodos(sols, 0, 0);
		return sols;
	}
	
	
	public static void main(String arg[]) {
		TableroSudoku t = new TableroSudoku( 
			    ".4....36263.941...5.7.3.....9.3751..3.48.....17..62...716.9..2...96.......312..9.");
		System.out.println(t);
		List<TableroSudoku> lt = t.resolverTodos();
		System.out.println(t);
		System.out.println(lt.size());
		for(Iterator<TableroSudoku> i= lt.iterator(); i.hasNext();) {
			TableroSudoku ts = i.next(); 
			System.out.println(ts);
			
		}

	}
	
	
}
