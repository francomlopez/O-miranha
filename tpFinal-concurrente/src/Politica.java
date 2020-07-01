import java.util.Random;

public class Politica {
    private RdP red;
    private Random rand;
    public Politica(RdP red) {
        this.red = red;
        rand = new Random();
    }
    public int[] cualDespierto(int[] c){
        // Primero se analizan las transiciones instantaneas y sus conflictos

        if(c[1] == 1 && c[2] == 1){
            // Este caso es cuando asignarP1 y asignarP2 estan habilitadas
            if(red.getMarca()[0] > red.getMarca()[1]){  // si colaP1 > colaP2 (plazas)
                return returnSoloUna(2,c);   // retorna el numero de la transicion asignaP2
            }
            else{
                return returnSoloUna(1,c); // retorna el numero de cualquier transicion
            }
        }
        else if(c[1] == 1){return returnSoloUna(1,c);} // asignaP1
        else if(c[2] == 1){return returnSoloUna(2,c);} // asignaP2
        else if(c[3] == 1){return returnSoloUna(3,c);} // EmpezarP1
        else if(c[4] == 1){return returnSoloUna(4,c);} // EmpezarP2
        else if(c[9] == 1 && c[10] == 1){
            // Este caso es cuando P1M1 y P1M2 estan habilitadas
            if(red.getMarca()[3] > red.getMarca()[4]){  // si DisponibleM1 > DisponibleM2 (plazas)
                return returnSoloUna(9,c);   // retorna el numero de la transicion P1M1
            }
            else{
                return returnSoloUna(10,c); // retorna el numero de transicion de P1M2
            }
        }
        else if(c[9] == 1){return returnSoloUna(9,c);} // P1M1
        else if(c[10] == 1){return returnSoloUna(10,c);} // P1M2
        else if(c[11] == 1 && c[12] == 1){
            // Este caso es cuando P2M1 y P2M2 estan habilitadas
            if(red.getMarca()[3] > red.getMarca()[4]){  // si DisponibleM1 > DisponibleM2 (plazas)
                return returnSoloUna(11,c);   // retorna el numero de la transicion P2M1
            }
            else{
                return returnSoloUna(12,c); // retorna el numero de transicion de P2M2
            }
        }
        else if(c[11] == 1){return returnSoloUna(11,c);}  // P2M1
        else if(c[12] == 1){return returnSoloUna(12,c);} // P2M2

        // Fin de las transiciones instantaneas

        // comienzo t temporales

        if(c[5] == 1 && c[13] == 1){ //Siempre ambas transiciones estaran habilitadas al mismo tiempo
            // Este caso es cuando finalizarT1P1 y procesarT2P1 estan habilitadas

            if(Math.random() >= 0.5){  // hacemos un random para ver que transicion disparar
                c[13] = 0;   // setea en 0 una de las 2 transiciones
            }
            else{
                c[5] = 0; //
            }
        }
        else{   // este es el caso en que una transicion ya se encuentre esperando por la ventana para disparar
            c[13] = 0;
            c[5] = 0;
        }
        if(c[6] == 1 && c[14] == 1){ //Siempre ambas transiciones estaran habilitadas al mismo tiempo
            // Este caso es cuando finalizarT1P2 y procesarT2P2 estan habilitadas
            if(Math.random() >= 0.5){  // hacemos un random para ver que transicion disparar
                c[14] = 0;   // setea en 0 una de las 2 transiciones
            }
            else{
                c[6] = 0;
            }
        }
        else{    // este es el caso en que una transicion ya se encuentre esperando por la ventana para disparar
            c[14] = 0;
            c[6] = 0;
        }
        return c;
    }

    // devuelve un arreglo con una sola transicion para disparar (se usa solo para las transiciones instantaneas)
    private int[] returnSoloUna(int t, int[] c){
        for(int i = 0; i < c.length; i++){
            if(i==t){c[i]=1;}
            else{c[i]=0;}
        }
        return c;
    }
}
