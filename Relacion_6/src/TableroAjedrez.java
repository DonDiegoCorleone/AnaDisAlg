import java.util.*;

//Solución basada en la práctica 4 (TableroSudoku)

public class TableroAjedrez implements Cloneable {

    //Tablero NxN
    //Cambia la variable para cambiar el tablero
    protected static final int N=5;

    protected int celdas[][];

    public TableroAjedrez() {
        celdas = new int[N][N];
    }

    // crea una copia de su parametro
    public TableroAjedrez(TableroAjedrez uno) {
        TableroAjedrez otro = (TableroAjedrez) uno.clone();
        this.celdas = otro.celdas;
    }

    // crear un tablero a partir de una configuracion inicial (las celdas vacias
    // se representan con el caracter ".").

    public TableroAjedrez(String s) {
        this();
        if(s.length() != N*N) {
            throw new RuntimeException("Construcción de ajedrez no válida.");
        } else {
            for(int f=0;f<N;f++)
                for(int c=0;c<N;c++) {
                    Character ch = s.charAt(f*N+c);
                    celdas[f][c] = (Character.isDigit(ch) ? Integer.parseInt(ch.toString()) : 0 );
                }
        }
    }



    /* Realizar una copia en profundidad del objeto
     * @see java.lang.Object#clone()
     */
    public Object clone()  {
        TableroAjedrez clon;
        try {
            clon = (TableroAjedrez) super.clone();
            clon.celdas = new int[N][N];
            for(int i=0; i<celdas.length; i++)
                System.arraycopy(celdas[i], 0, clon.celdas[i], 0, celdas[i].length);
        } catch (CloneNotSupportedException e) {
            clon = null;
        }
        return clon;
    }



    public String toString() {
        String s = "";

        for(int f=0;f<N;f++) {
            for(int c=0;c<N;c++) {
                s += (celdas[f][c]==0 ? "  ." : String.format("%3d",celdas[f][c]));
            }
            s+= "\n";
        }
        return s;
    }

    protected int numeroDeLibres() {
        int n=0;
        for (int f = 0; f < N; f++)
            for (int c = 0; c < N; c++)
                if(celdas[f][c] == 0)
                    n++;
        return n;
    }

    protected int numeroDeFijos() {
        return N*N - numeroDeLibres();
    }


    //transforma a array una lista de posicionCaballo
    public static posicionCaballo[] toInt(ArrayList<posicionCaballo> arrayList) {
        posicionCaballo[] array = null;
        if (arrayList!=null) {
            array = new posicionCaballo[arrayList.size()];
            for(int i=0; i<arrayList.size(); i++) {
                array[i] = arrayList.get(i);
            }
        }
        return array;
    }

    protected static class posicionCaballo{ //clase en la que guardamos el par fila columna de la posición del caballo
        int fila;
        int columna;

        public posicionCaballo(int fila, int columna){
            this.columna=columna;
            this.fila=fila;
        }

        public int getFila() {
            return fila;
        }

        public int getColumna() {
            return columna;
        }

    }

    protected posicionCaballo[] buscarMovimientos(int fila, int columna) {
        posicionCaballo pos= new posicionCaballo(fila, columna);
        ArrayList<posicionCaballo> validos = new ArrayList<>();

        //tirar por arriba
        if(pos.fila>1){
            //¿es posible derecha?
            if(pos.columna<N-1 && celdas[fila-2][columna+1]==0) {
                validos.add(new posicionCaballo(fila - 2, columna + 1));
            }
            //¿es posible izquierda?
            if(pos.columna>0 && celdas[fila-2][columna-1]==0)
                validos.add(new posicionCaballo(fila - 2, columna - 1));
        }
        //tirar por abajo
        //¿es posible?
        if(pos.fila<N-2){
            //¿es posible derecha?
            if(pos.columna<N-1 && celdas[fila+2][columna+1]==0)
                validos.add(new posicionCaballo(fila + 2, columna + 1));
            //¿es posible izquierda?
            if(pos.columna>0 && celdas[fila+2][columna-1]==0)
                validos.add(new posicionCaballo(fila + 2, columna - 1));
        }
        //tirar por derecha
        //¿es posible?
        if(pos.columna<N-2){
            //¿es posible arriba?
            if(pos.fila>0 && celdas[pos.getFila()-1][columna+2]==0)
                validos.add(new posicionCaballo(fila - 1, columna + 2));
            //¿es posible abajo?
            if(pos.fila<N-1 && celdas[pos.getFila()+1][columna+2]==0)
                validos.add(new posicionCaballo(fila + 1, columna + 2));
        }
        //tirar por izquierda
        //¿es posible?
        if(pos.columna>1) {
            //¿es posible arriba?
            if (pos.fila > 0 && celdas[fila-1][pos.getColumna()-2]==0)
                validos.add(new posicionCaballo(fila - 1, columna - 2));
            //¿es posible abajo?
            if (pos.fila < N - 1 && celdas[fila+1][pos.getColumna()-2]==0)
                validos.add(new posicionCaballo(fila + 1, columna - 2));

        }

        return toInt(validos);

    }



    protected void resolverTodos(List<TableroAjedrez> soluciones, int fila, int columna) {
        if (numeroDeFijos() == N*N ) {
            //Si ocurre tenemos la solución
            soluciones.add(new TableroAjedrez(this));
        } else if (soluciones.size()==0){

            posicionCaballo [] posiblesMovimientos = buscarMovimientos(fila,columna);

            int i=0;
            while(i<posiblesMovimientos.length) {
                int sigPaso= celdas[fila][columna] +1 ;

                TableroAjedrez clon = new TableroAjedrez(this);

                int nuevaFila=posiblesMovimientos[i].fila;
                int nuevaColumna=posiblesMovimientos[i].columna;

                clon.celdas[nuevaFila][nuevaColumna] = sigPaso;
                clon.resolverTodos(soluciones, nuevaFila,nuevaColumna);

                i++;
            }
        }
    }



    public List<TableroAjedrez> resolverTodos() {
        List<TableroAjedrez> sols  = new LinkedList<TableroAjedrez>();
        resolverTodos(sols, N-1, 0);
        return sols;
    }


    public static String crearTablero(int n){
        String inicial="";
        for(int f=0;f<n;f++){
            for(int c=0;c<n;c++){
                if(f==n-1 && c==0){
                    inicial+="1";
                }else inicial+=".";
            }
        }
        return inicial;
    }

    public static void main(String arg[]) {
        int n=N; //Para cambiar de tablero se deberá cambiar la constante N, declarada arriba
        TableroAjedrez t = new TableroAjedrez(crearTablero(n));

        List<TableroAjedrez> lt = t.resolverTodos();
        if(lt.size()==0) {
            System.out.println("No hay solución para el tablero "+N+"x"+N);
        }else{
            System.out.println("Existe solución para el tablero "+N+"x"+N+" :");
            System.out.println(lt.get(0));
        }
        System.out.println("Tablero inicial: ");
        System.out.println(t);
    }
}
