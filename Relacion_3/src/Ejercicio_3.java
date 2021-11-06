import java.sql.Array;

public class Ejercicio_3 {

    public static class par{
        int der;
        int izq;
    }


    public static int mayoritario(int v[], int inf, int sup){

        int minted=(inf+sup)/2;

        String izq;

        if(sup-inf>1) {
            mayoritario(v, inf, minted);
            mayoritario(v, minted, sup);

        }
        int sol;
        //Array aux;
       // if(v[inf]==v[sup])return aux;
        return 5;
    }

    public static void main(String[] args) {

        int v[]={2,2,3,3,3,3,5,5};
        System.out.print(mayoritario(v,0,v.length-1));
        
    }
}
