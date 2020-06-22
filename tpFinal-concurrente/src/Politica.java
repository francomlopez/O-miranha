import java.util.Random;

public class Politica {
    private RdP red;
    private Random rand;
    public Politica(RdP red) {
        this.red = red;
        rand = new Random();
    }
    public int cualDespierto(int[] c){
        // Primero se analizan las transiciones instantaneas y sus conflictos

        if(c[1] == 1 && c[2] == 1){
            // Este caso es cuando asignarP1 y asignarP2 estan habilitadas
            if(red.getMarca()[0] > red.getMarca()[1]){  // si colaP1 > colaP2 (plazas)
                return 2;   // retorna el numero de la transicion asignaP2
            }
            else{
                return 1; // retorna el numero de cualquier transicion
            }
        }
        else if(c[1] == 1){return 1;} // asignaP1
        else if(c[2] == 1){return 2;} // asignaP2
        else if(c[3] == 1){return 3;} // EmpezarP1
        else if(c[4] == 1){return 4;} // EmpezarP2
        else if(c[9] == 1 && c[10] == 1){
            // Este caso es cuando P1M1 y P1M2 estan habilitadas
            if(red.getMarca()[3] > red.getMarca()[4]){  // si DisponibleM1 > DisponibleM2 (plazas)
                return 9;   // retorna el numero de la transicion P1M1
            }
            else{
                return 10; // retorna el numero de transicion de P1M2
            }
        }
        else if(c[9] == 1){return 9;} // P1M1
        else if(c[10] == 1){return 10;} // P1M2
        else if(c[11] == 1 && c[12] == 1){
            // Este caso es cuando P2M1 y P2M2 estan habilitadas
            if(red.getMarca()[3] > red.getMarca()[4]){  // si DisponibleM1 > DisponibleM2 (plazas)
                return 11;   // retorna el numero de la transicion P2M1
            }
            else{
                return 12; // retorna el numero de transicion de P2M2
            }
        }
        else if(c[11] == 1){return 11;}  // P2M1
        else if(c[12] == 1){return 12;} // P2M2

        // Fin de las transiciones instantaneas


        //primero las de vaciado

        //primero las de vaciado 
        else if(c[15] == 1){return 15;}
        else if(c[16] == 1){return 16;}
        else if(c[5] == 1 && c[13] == 1){ //Siempre ambas transiciones estaran habilitadas al mismo tiempo
            // Este caso es cuando finalizarT1P1 y procesarT2P1 estan habilitadas

            if(rand.nextInt(2) == 0){  // hacemos un random para ver que transicion disparar
                return 5;   // retorna el numero de la transicion finalizarTIP1
            }
            else{
                return 13; // retorna el numero de transicion de procesarT2P1
            }
        }
        else if(c[6] == 1 && c[14] == 1){ //Siempre ambas transiciones estaran habilitadas al mismo tiempo
            // Este caso es cuando finalizarT1P2 y procesarT2P2 estan habilitadas


            if(rand.nextInt(2) == 0){  // hacemos un random para ver que transicion disparar
                return 6;   // retorna el numero de la transicion finalizarTIP1
            }
            else{
                return 14; // retorna el numero de transicion de procesarT2P1
            }
        }
        else if(c[7] == 1){return 7;}
        else if(c[8] == 1){return 8;}
        else if(c[0] == 1){return 0;}
        else{return -1;}

    }
}
