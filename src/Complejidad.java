public class Complejidad {

    public static void uno(long n){
        for(int i=0;i<10;i++){

        }
    }

    public static void logn(long n){
        int c= 1;
        while (c < n) {
            c= 2*c;
        }
    }

    public static void n(long n){
        for(int i=0; i<n;i++){

        }
    }

    public static void n2(long n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            }
        }
    }

    public static void n3(long n){
        int[] a = new int[(int) n];
        int suma = 0;
        int suma2 = 0;

        for (int i = 0; i < a.length-1; i++) {
            a[i] = 1;
			for (int j = i+1; j < a.length; j++) {
				suma = suma + a[j];
                for (int k = i+1; k < a.length; k++) {
                   suma2 = suma2 + a[k];
               }
			}
        }
    }

    public static synchronized int e2n(int x, long n) {
        if (n == 0) {
            return 1;

        } else {
            return (x / (e2n(x, n - 1))) + (e2n(x, n - 1));
        }
    }

    public static synchronized long nf(long n) {
        for(int i = 0; i < n; i++) {
            nf(n-1);
        }

        return n;
    }

    private static int[] a;
    private static int n;
    public static synchronized void nlogn(long n) {
        int[] a = new int[(int) n];

        for(int i = a.length-1; i >= 0; i--) {
            a[i] = i;
        }

        ordenar(a);
    }


    private static void construirHeap(int[] a){
        n = a.length-1;

        for(int i = n/2; i >= 0; i--){
            maximoHeap(a, i);
        }
    }

    private static void maximoHeap(int[] a, int i){
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest;

        if(left <= n && a[left] > a[i]){
            largest = left;
        }else{
            largest = i;
        }

        if(right <= n && a[right] > a[largest]){
            largest = right;
        }
        if(largest != i){
            intercambiar(i, largest);
            maximoHeap(a, largest);
        }
    }

    private static void intercambiar(int i, int j){
        int t = a[i];

        a[i] = a[j];
        a[j] = t;
    }

    private static void ordenar(int[] a0){
        a = a0;

        construirHeap(a);

        for(int i = n; i > 0; i--){
            intercambiar(0, i);
            n = n - 1;
            maximoHeap(a, 0);
        }
    }

}
