
package com.mycompany.sistemasoperativos1;

public class PCB {
    int PID;
    String ImageName;
  //  String BlackRam;
    String reg;
    int prioridad;
    int qq;
    
    public PCB(String cad){
        PID = 1111;
        ImageName = cad;
      //  String BlackRam;
        reg = cad;
    }
    
    public PCB(String cad,int p){
        PID = 1111;
        ImageName = cad;
      //  String BlackRam;
        reg = cad;
        prioridad = p;
        qq = 0;
        
    }
}
