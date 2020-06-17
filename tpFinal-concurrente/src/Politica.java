public class Politica {
    private RdP red;
    public Politica(RdP red) {
        this.red = red;
    }
    public int cualDespierto(int[] c){
        // Primero se analizan las transiciones instantaneas y sus conflictos

        if(c[1] == 1 && c[2] == 1){
            // Este caso es cuando asignarP1 y asignarP2 estan habilitadas
            if(red.getMarca()[0] > red.getMarca()[1]){  // si colaP1 > colaP2 (plazas)
                return 2;   // retorna el numero de la transicion asignaP2
            }
            else{
                return 1; // retorna el numero de transicion de asignaP1
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
        else if(c[10] == 1){return[10];} // P1M2
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


    }
}
