import java.util.ArrayList;
import java.util.List;

/**
 * Estoys to' ciego hermano.
 *
 * @author Diego
 */
public class Ejercicio_3C {

    public static int comparacionesB;
    public static int comparacionesT;

    public static int busquedaTernaria(int v[],int inf,int sup,int x) {

        int tercio1 = (inf +((sup-inf)/ 3)); //es el primer tercio del vector
        int tercio2 = (sup -((sup-inf)/ 3)); //es el segundo tercio del vector

        comparacionesT++;
        comparacionesT++;
        if (v[tercio1] != x && v[tercio2] != x && inf<sup) { //se busca por 3 partes(medio-izq,medio,medio-der)
            comparacionesT=comparacionesT+3;
            if (x < v[tercio1]) {
                comparacionesT++;
                return busquedaTernaria(v, inf, tercio1, x); //busca por medio-izquieda
            } else if (x < v[tercio2]) {
                comparacionesT++;
                return busquedaTernaria(v, tercio1+1, tercio2-1, x); //busca por medio
            } else
                return busquedaTernaria(v, tercio2, sup, x); //busca por medio-derecha
        }
        int solucion=-1;
        comparacionesT++;
        if(v[tercio1]==x){
            solucion=v[tercio1];
            comparacionesT++;
            comparacionesT++;
        }
        else if(v[tercio2]==x){
            solucion=v[tercio2];
            comparacionesT++;
            comparacionesT++;
        }
        return solucion;
    }

    public static int busquedaBinaria(int v[],int inf,int sup,int x) {

        int centro = (sup + inf) / 2;


        comparacionesB++;


        if (inf < sup) {  //comparacion
            comparacionesB++;
            if (x < v[centro]) { //comparacion
                comparacionesB++;
                return busquedaBinaria(v, inf, centro, x);
            } else if(x > v[centro]){
                comparacionesB++;
                return busquedaBinaria(v, centro + 1, sup, x);
            }
        }

        int solucion = -1;
        comparacionesB++;
        if (v[centro] == x) solucion = x;
        comparacionesB++;
        comparacionesB++;

        return solucion;
    }

    static int[] toIntArray(List<Integer> list){
        int[] ret = new int[list.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = list.get(i);
        return ret;
    }

    public static void main(String[] args) {
        int totalB=0;
        int totalT=0;

        int x;
        Temporizador t=new Temporizador(2);


        int rep=5;
        while(rep<50) {
            List<Integer> lista=new ArrayList<Integer>();
            x=((int)(Math.random()*rep)+1);

            int i = 1;

            while (i < rep) {
                lista.add(i);
                i++;
            }
            System.out.print(lista+" "+"busca: "+x+" ratio: ");
            int v[]=toIntArray(lista);

            comparacionesT=0;
            comparacionesB=0;
            t.iniciar();
            busquedaTernaria(v,0,v.length-1,x);
            t.parar();
            double t1=t.tiempoPasado();
            t.reiniciar();
            t.iniciar();
            busquedaBinaria(v,0,v.length-1,x);
            t.parar();
            double t2=t.tiempoPasado();
            totalB=totalB+comparacionesB;
            totalT=totalT+comparacionesT;
            System.out.println(t1/t2+" "+ "B: "+comparacionesB+" T:"+comparacionesT);

            // Devuelve uno más de la repetición
            rep++;
        }
        System.out.println("TotalB: "+totalB+" Total T:"+totalT);


    }

}
