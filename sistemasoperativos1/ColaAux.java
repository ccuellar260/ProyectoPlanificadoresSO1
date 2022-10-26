
package com.mycompany.sistemasoperativos1;

public class ColaAux {

    private NodoAux ini,fin;
   // PCB PCBvacio = new PCB("PCB vacio"); 
    
    public ColaAux(int N){
        ini = null;
        fin = null;
        for (int i = 0; i < N; i++) {
            meter(1);
        }
       // PCBvacio.ImageName = "PCB Vacio";
    };
    
    public boolean ColaVacia(){
        if(ini == null){
            return true;
        }else{
            return false;
        }
    }
    
    //metodo para insertar a la cola
    public void meter(int elem){
       // int hola = 77;
        
        NodoAux N1 = new NodoAux();
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
    
    public int sacar(){
        if(!ColaVacia()){
        int elemento = ini.elem;
            if(ini == fin){
                ini = null;
                fin = null;
            }else{
                ini = ini.sig;
                }
             return elemento;
        }else{
            return -1;
            }
    }
    
    public String MostrarCola(){
        NodoAux Recorrido = ini;
        String Cola = "";
        while (Recorrido != null){
           // String xd = Recorrido.elem.ImageName;
            
            Cola += Integer.toString(Recorrido.elem) +" - ";
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
