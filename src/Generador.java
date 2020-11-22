


import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Generador extends Thread{
    
    NewJFrame1 frame = null;
    Contenedor contenedor;
    private  boolean ejecucion = true;
   
      public Generador(NewJFrame1 g,Contenedor c){
          this.frame = g;
          this.contenedor = c;
      }

    public void setEjecucion(boolean ejecucion) {
        this.ejecucion = ejecucion;
    }

    
        @Override
        public void run() {
            frame.jLabel2.setForeground(Color.red);
            frame.jLabel2.setText("BUSCANDO!....");
            while(ejecucion){
                try {
                    int numero = (int)(Math.random()*(500000 - 10000)+1000);
                    contenedor.insertar(numero);
                    frame.m.addRow(new Object[]{numero,""});
                    frame.jTable1.changeSelection(frame.jTable1.getRowCount() - 1, 0, false, false);
                    sleep(45);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, null, ex);
                } catch(IndexOutOfBoundsException ex){
                    System.out.println("KAKA");
                }
            }
        
        }
}
