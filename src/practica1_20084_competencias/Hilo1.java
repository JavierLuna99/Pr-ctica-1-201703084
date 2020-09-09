//Javier Enrique Luna Díaz 201703084
package practica1_20084_competencias;

import javax.swing.JTextArea;

public class Hilo1 extends Thread{
    private JTextArea area;
    private boolean pausa;
    private boolean detener;
    
    public Hilo1(JTextArea area){
        this.area = area; 
        pausa = false;
        detener = false;
    }
    
    public synchronized void pausar(){
        pausa = true;
    }
    
    public synchronized void reanudar(){
        pausa = false;
        this.notify();
    }
    
    public synchronized void parar(){
        detener = true;
    }
    
    /*public void run(){
        int i = 0;
        while(!this.isInterrupted()){
            area.append(i + "\n");
            i++;
        }
    }*/
    
    public void run(){
        int i = 0;
        while (!detener){
            try{
                synchronized(this){
                    if (pausa){
                        wait();
                    }
                }
                area.append(i+"\n");  
                i++;
            }catch(InterruptedException ex){
                System.err.println(ex);
            }
        }
    }    
    
}
