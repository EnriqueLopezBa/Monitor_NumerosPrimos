

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Enrique
 */
public class Consumidor extends Thread {

    boolean ejecucion = true;
    NewJFrame1 p = null;
    Contenedor contenedor;
    int pos = 0;
    List<Integer> lista = new ArrayList<>();

    public Consumidor(NewJFrame1 p, Contenedor c) {
        this.p = p;
        this.contenedor = c;
    }
    

    @Override
    public void run() {
        while(ejecucion){
            if(lista.size() == 20){
                 p.m.setRowCount(0);
                for(Object c : lista){
                    p.m.addRow(new Object[]{c,"SI"});
                }
                p.jLabel2.setText("Generando nueva lista.. Generadas: "+(float)contenedor.getNumEjecuciones()/100);
                 p.lbl.setText("TOTAL DATOS GENERADOS: "+contenedor.getNumEjecuciones());
                ejecucion = false;
                p.g.setEjecucion(false);
                p.ejecutando = false;
                return;
            }
          int numero =  contenedor.sacar();
       
            if(esPrimo(numero) && !lista.contains(numero) && lista.size() != 20)
                lista.add(numero);
            for (int i = pos; i < p.m.getRowCount(); i++) {
                if(lista.contains(p.m.getValueAt(i, 0)))
                    p.m.setValueAt("SI", pos, 1);
                pos++;
            }
          if(contenedor.getNumEjecuciones() % 100 == 0){
                p.jLabel2.setText("Generando nueva lista.. Generadas: "+contenedor.getNumEjecuciones()/100);
                p.m.setRowCount(0);
                for(Object c : lista){
                    p.m.addRow(new Object[]{c,"SI"});
                }
               
                }
            try {
                sleep(50);
            } catch (InterruptedException e) {
                System.err.println("Error");
            }
        }
   
    }


    boolean esPrimo(int n) {
        if (n%2==0) return false;
            for(int i = 3; i * i <= n; i += 2) {
                if(n % i == 0)
                    return false;  
            }
    return true;
    }
}
