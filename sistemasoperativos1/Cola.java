
package com.mycompany.sistemasoperativos1;
import javax.swing.JTextArea;

public class Cola {
    private Nodo ini,fin;
    PCB PCBvacio = new PCB("PCB vacio"); 
    
    public Cola(){
        ini = null;
        fin = null;
        PCBvacio.ImageName = "PCB Vacio";
    };
    
    public boolean ColaVacia(){
        if(ini == null){
            return true;
        }else{
            return false;
        }
    }
    
    //metodo para insertar a la cola
    public void meter(PCB elem){
       // int hola = 77;
        
        Nodo N1 = new Nodo();
        N1.elem = elem;
        N1.sig = null;
        if(ColaVacia()){
            ini = N1;
            fin = N1;
        }else{
            fin.sig = N1;
            fin = N1;
            
        }
        
    }
    
    public PCB sacar(){
        if(!ColaVacia()){
        PCB  elemento = ini.elem;
            if(ini == fin){
                ini = null;
                fin = null;
            }else{
                ini = ini.sig;
                }
             return elemento;
        }else{
            return PCBvacio;
            }
    }
    
    public String MostrarCola(){
        Nodo Recorrido = ini;
        String Cola = "";
        while (Recorrido != null){
           // String xd = Recorrido.elem.ImageName;
            
            Cola += Recorrido.elem.ImageName+" - ";
            Recorrido = Recorrido.sig;
        }
        
        //String cadena[] = MostrarCola.split(" ");
        int L = Cola.length()-2;
        if(L>0){
            Cola = Cola.substring(0,L);
        }
        return Cola;
    }
   



}
