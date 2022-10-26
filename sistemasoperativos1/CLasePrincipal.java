
package com.mycompany.sistemasoperativos1;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextField;

public class CLasePrincipal {
    PCB CPU = new PCB("CPU");
    PCB PRUN;
    Cola QQ = new Cola(); //la cola de la memoria ram
      ColaAux C= new ColaAux(5);
    int cqp ;
    JTextField ta;
    JTextField txtSalidaQ1;
    JTextField txtSalidaQ2;
    JTextField txtSalidaQ3;
    JTextField txtSalidaQ4;
    
    
    //PLANIDOR 
    Cola[] Q = {new Cola(),new Cola(),new Cola(),new Cola()};
    int cqc = 0; //cantidad de qunatum por cola
    int K = 0;   //de que cola debes sacr
    int N ;   //cantidad de colas
    int P[] = new int[4];
    
    
    
    
    //constructor 
    public  CLasePrincipal(JTextField q1,JTextField q2,JTextField q3,
                            JTextField q4,JTextField cajaQuantum,JTextField cajaCPU  ){
       txtSalidaQ1 = q1;
        txtSalidaQ2 = q2;
        txtSalidaQ3 = q3;
        txtSalidaQ4 = q4;
                         
       Q[0].meter(new PCB("P7",0));
       Q[0].meter(new PCB("P1",0));
       Q[0].meter(new PCB("P2",0));
       Q[0].meter(new PCB("P3",0));
       Q[0].meter(new PCB("P4",0));
       Q[0].meter(new PCB("P5",0));
       Q[0].meter(new PCB("P6",0));
//      // txtSalida.setText(Q[0].MostrarCola());
//       
       Q[1].meter(new PCB("R1",1));
       Q[1].meter(new PCB("R2",1));
       Q[1].meter(new PCB("R3",1));
////       
      Q[2].meter(new PCB("S1",2));
      Q[2].meter(new PCB("S2",2));
      Q[2].meter(new PCB("S3",2));
       Q[2].meter(new PCB("S4",2));
       Q[2].meter(new PCB("S5",2));
       
       Q[3].meter(new PCB("T1",3));
       Q[3].meter(new PCB("T2",3));
       Q[3].meter(new PCB("T3",3));
       Q[3].meter(new PCB("T4",3));
        
      //  ta.setText(Q.MostrarCola());
        PRUN = Q[0].sacar();
        cqp = 0;
        CPU.reg = PRUN.reg;
         MostrarColas();
         cajaQuantum.setText(Integer.toString(cqp));
         cajaCPU.setText(CPU.reg);
    
       
    }
    public CLasePrincipal(  JTextField tata,JTextField cajaQuantum,
            JTextField cajaCPU , int nn){
        ta =tata;
        N = nn;
       // C =  new ColaAux(N);
      // QQ.meter(new PCB("P5"));
        QQ.meter(new PCB("P1"));
        QQ.meter(new PCB("P2"));
        QQ.meter(new PCB("P3"));
        QQ.meter(new PCB("P4"));
        QQ.meter(new PCB("P5"));
        
        PRUN = QQ.sacar();
        cqp = 0;
        CPU.reg = PRUN.reg;
     //    MostrarColas();
         cajaQuantum.setText(Integer.toString(cqp));
         cajaCPU.setText(CPU.reg);
        
        ta.setText(QQ.MostrarCola());
    }
    
    public void prioridades(int p1,int p2,int p3, int p4){
        P[0] = p1;
        P[1] = p2;
        P[2] = p3;
        P[3] = p4;
          
    }
    
    public void run(JTextField cajaQuantum,JTextField cajaCPU){
        Timer time  = new Timer();
         
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                cajaQuantum.setText(Integer.toString(cqp+1));
                //planificadorRR();
                planificador();
                cajaCPU.setText(CPU.reg);
                MostrarColas();
              // txtSalida.setText(Q[0].MostrarCola());
            }
        };
        
        //hacer una taeras de inmeidaot cada 1000;
        time.schedule(tarea, 0, 1000);
        
    }
    
      public void runRR(JTextField cajaQuantum,JTextField cajaCPU){
        Timer time  = new Timer();
         
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                cajaQuantum.setText(Integer.toString(cqp+1));
                //planificadorRR();
                planificadorSecuencia2();
                cajaCPU.setText(CPU.reg);
                ta.setText(QQ.MostrarCola());
            }
        };
        
        //hacer una taeras de inmeidaot cada 1000;
        time.schedule(tarea, 0, 1000);
        
    }
    
    
    public void MostrarColas(){
        txtSalidaQ1.setText(Q[0].MostrarCola());
        txtSalidaQ2.setText(Q[1].MostrarCola());
        txtSalidaQ3.setText(Q[2].MostrarCola());
        txtSalidaQ4.setText(Q[3].MostrarCola());
    }
    
    
    public void planificadorRR(){
        cqp++;
        if(cqp == 3 || finalizo(PRUN)){
            if(finalizo(PRUN)){
                freeMen();
            }else{ //si os i hacr un proceso que ya este corriendo
                PRUN.reg = CPU.reg;
                
                Q[0].meter(PRUN);
                //aqui terminaria el 1er if
                }
            
        PRUN = Q[0].sacar();
        cqp = 0;
        CPU.reg = PRUN.reg;
            
        }
     
    } 
    
    public void planificador(){
        cqp++;
        if(cqp == 2 || finalizo(PRUN)){
            if(finalizo(PRUN)){
            freeMen();
            }else{ //si os i hacr un proceso que ya este corriendo
                PRUN.reg = CPU.reg;
                int i = PRUN.prioridad;
                Q[i].meter(PRUN);
                //aqui terminaria el 1er if
                }
            
        PRUN = Q[K].sacar();
        //saber si ya se cumplio con la cantidad de quamtus
        cqc++;
        if(cqc == P[PRUN.prioridad]){
            K = nextK(K);
            cqc = 0;
        }
        
        cqp = 0;
        CPU.reg = PRUN.reg;
            
        }
     
     }
      
    public boolean finalizo(PCB PRUN){
        return false;
    }
    
    public void freeMen(){
        int c=1;
    }

    public int nextK(int k){
        //Lenthg -1= 4 ya que solo hay 4 filas 
        if(k < 1){
            k++;
        }else{
            k= 0; 
            }
        return k;
    }
    
    
    
    /*2 colas q1 y q2 
    asignando 1q x cola y 1q x proceso, pero:
    "Si un proceso P sale de una cola, se depositara en la otra, si es que ya 
    ha reicbido 2q en esa cola. Si solo ha recibido un solo quantum, se 
    depositara en la misma cola de donde salio "
    */
    //creo las variables qq en el pcb y con eso comparo si se hizo el quamtum
    public void planificadorx(){
        cqp++;
        if(cqp== 1||finalizo(PRUN)){
            if(finalizo(PRUN)){
                freeMen();
            }else{
                PRUN.reg = CPU.reg;
                int i = PRUN.prioridad;
                    if(PRUN.qq==0){
                        Q[i].meter(PRUN);
                        PRUN.qq++;
                    }else{
                        i++;
                        if(i>3)
                          i=0;
                        PRUN.prioridad = i;
                        Q[i].meter(PRUN);
                        
                    }
            
                }
        PRUN = Q[K].sacar();
        //saber si ya se cumplio con la cantidad de quamtus
        cqc++;
        if(cqc == 1){
            K = nextK(K);
            cqc = 0;
        }   
        cqp = 0;
        CPU.reg = PRUN.reg;         
        }
        
    }
    
    
    /*cola q1 y q2
      los procesos nunca finalizan por sus propias medios 
      los procesos de la colaQ[1], reciben q x proceso, segun la secuencia:
      1,2,3,1,2,3,1,2,3...
      y los colaQ[2] recibne 2,3,2,3,2,3,2,3....
      se asigna 1qxcola
    
    */
    int contador1=1;
    int contador2=2;
    public void planificadorxd(){
        cqp++;
        if(cqp== cantProceso(PRUN)){
            PRUN.reg = CPU.reg;
            int i = PRUN.prioridad;
            Q[i].meter(PRUN);   
            if(i==0){
                contador1++;
                if(contador1==4)
                  contador1 = 1;
                
            }else{
                contador2++;
                    if(contador2==4)
                    contador1 = 2;
                    
                }
            
            
            PRUN = Q[K].sacar();
            //saber si ya se cumplio con la cantidad de quamtus
            cqc++;
                if(cqc == 1){
                K = nextK(K);
                cqc = 0;
                }   
            cqp = 0;
            CPU.reg = PRUN.reg;    
       
        }
        
    }
        
    public int cantProceso(PCB P){
        if(P.prioridad ==0){
            return contador1;  
        }else{
            return contador2;    
            }
      }
    
    
    
    
    //N procesos, nunca finalixan 
    //caund oen Prun es atentido por 1ra vez recibe 1qxp y si es la 2da 2 qxp, 
    //si es la 3ra recibe 1qxp y asi sucesivametne 1,2,1,2,1,2...
  //  int N = 5; //catnidad de elemetos en la cola
  //  ColaAux C = new ColaAux(N);
    int Aux = C.sacar();
    void planificadorSecuencia1(){
        cqp++;
        if(cqp == Aux){
            PRUN.reg = CPU.reg;
            QQ.meter(PRUN);
            if(Aux==2){
               C.meter(1);
            }else C.meter(2);
            
            PRUN = QQ.sacar();
            Aux = C.sacar();
            cqp = 0;
            CPU.reg = PRUN.reg;
        }
    } 
    
    //false = 1, true = 2
    int contador = 1;
    boolean ban = false;
     void planificadorSecuencia2(){
        cqp++;
        if(cqp == cantidadQQQ()){
                contador++;
            PRUN.reg = CPU.reg;
            QQ.meter(PRUN);                   
            PRUN = QQ.sacar();   
            cqp = 0;
            CPU.reg = PRUN.reg;
        }
    } 
     
     int cantidadQQQ(){
         if (contador > 5){
             contador = 1;
             if(ban == true){
                 ban = false;
             }else ban= true;
         }
         
         if(ban == true ){
             return 2;
         }else return 1;
     }
    
}
