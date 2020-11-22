



public class Contenedor {
    
    private boolean contenedorLleno = false;
    private int[] arreglo = new int[100];
    private int cont = -1, numEjecuciones = 0, contador = 0;

    public boolean isContenedorLleno() {
        return contenedorLleno;
    }

    public int getNumEjecuciones() {
        return numEjecuciones;
    }

    public int getContador() {
        return contador;
    }


    
    public synchronized void insertar(int numero){
        while(contenedorLleno){
            try {
                System.out.println("Contenedor lleno");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
        }
        numEjecuciones++;
        arreglo[++cont] = numero;
          if(numEjecuciones % 100 == 0)
              contador++;
        if(cont == 99)
            contenedorLleno = true;
        notify();
    }
    
    public synchronized int sacar(){
        while(cont == -1){
            try {
                System.out.println("Contenedor vacio");
                wait();
            } catch (InterruptedException e) {
                System.out.println("error");
            }
        }
        int x = arreglo[cont];
        cont--;
            contenedorLleno = false;
        notify();
        return x;
    }
}
