public class Ejercicio_T3 {

    public static void intercambiar(int[] a,int p,int d) {

        int aux = a[p];
        a[p] = a[d];
        a[d] = aux;
    }

    public static int partir(int[] v, int inf,int sup){
        int pivote=inf;
        int i=inf+1;
        int j=sup;

        while(i<j){
            while(v[pivote]<v[j])j--;
            while(v[pivote]>v[i])i++;

            if(i<j)intercambiar(v,i,j);
        }

        if(v[pivote]>v[j])intercambiar(v,pivote,j);

        return j;

    }

    public static int kesimoElem(int v[],int izq,int der,int k){
        int s=partir(v,izq,der);
        if(k==s){
            return v[k];
        }else if(k<s){
            return kesimoElem(v,izq,s-1,k);
        }else{
            return kesimoElem(v,s+1,der,k);
        }
    }

    public static void main(String[] args) {
        int v[]={4,6,2,7,5,3,1};
        int k=5;
        System.out.print(kesimoElem(v,0,v.length-1,k-1));
    }
}
