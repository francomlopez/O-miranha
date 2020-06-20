public class RdP {
    private final int[][] incidencia;
    private int[] marca;
    private boolean[] temporales; // vector que indica que transiciones son temporales
    private long[] tiempoInicial; // vector que lleva el tiempo inicial de las transiciones temporales
    private long[] alpha;
    private long beta;
    private Monitor monitor;
    public RdP(Monitor m) {
        incidencia = new int[][]{{0,1,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,1,0,-1,0,0,0,0,0,0,0,0,0,0,0,0},
                                {1,-1,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,-1,0,-1,0,0,0,1,0},
                                {0,0,0,0,0,0,0,0,0,0,-1,0,-1,0,0,0,1},
                                {0,-1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,-1,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,1,0,1,0,-1,-1,0,0,0,0,0,0},
                                {0,0,0,0,0,0,1,0,1,0,0,-1,-1,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,-1,0},
                                {0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,-1},
                                {-1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,-1,0,0,0,0,0,1,1,0,0,0,0,0,0},
                                {0,0,0,0,-1,0,0,0,0,0,0,1,1,0,0,0,0},
                                {0,0,0,1,0,-1,0,0,0,0,0,0,0,-1,0,0,0},
                                {0,0,0,0,1,0,-1,0,0,0,0,0,0,0,-1,0,0},
                                {0,0,0,-1,-1,1,1,1,1,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,-1,0,0,0,0,0,1,0,0,0},
                                {0,0,0,0,0,0,0,0,-1,0,0,0,0,0,1,0,0}};

        monitor = m;
        marca = new int[]{0,0,0,8,8,4,4,0,0,0,0,1,1,1,0,0,1,0,0};
        temporales = new boolean[]{true,false,false,false,false,true,true,true,true,false,false,false,false,true,true,true,true};
        tiempoInicial = new long[]{0,-1,-1,-1,-1,0,0,0,0,-1,-1,-1,-1,0,0,0,0};
        alpha = new long[]{300,-1,-1,-1,-1,100,100,100,100,-1,-1,-1,-1,300,300,1500,1500};
        beta = 10000;
    }

    // Metodo que se le pasa el vector de disparo y actualiza la marca
    // Se debe verificar que el disparo se puede realizar previamente

    public int dispararTransicion(int t) {
        if(!tHabilitada(t)){return 1;}

        if(esTemporal(t)){
            if(!checkVentana(t)) {
                monitor.releaseMutex();
                long nap = alpha[t] - (System.currentTimeMillis() + tiempoInicial[t]);
                try {
                    Thread.currentThread().sleep(nap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 2;
            }

            tiempoInicial[t] = 0; // Significa que la transicion va a ser disparada por lo que reiniciamos el tiempo
        }
        int[] b = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};  // tiene 17 ceros
        b[t] = 1;  // seteamos el 1 en la transicion que se quiere disparar

        int[] c = new int[incidencia.length];
        // se comprueba si las matrices se pueden multiplicar
        if (incidencia[0].length == b.length) {
            for (int i = 0; i < incidencia.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    // aquí se multiplica la matriz
                    c[i] += incidencia[i][j] * b[j];
                }
            }
        }
        for(int i = 0; i < marca.length; i++){
            marca[i] += c[i];
        }
        // CAMBIOS A CONTADORES DE TRANSICIONES TEMPORALES
        for(int i = 0;i<this.getCantTransiciones(); i++){
            if(tHabilitada(i) && temporales[i]){
                if(tiempoInicial[i] == 0){tiempoInicial[i] = System.currentTimeMillis();}
            }
            if(!tHabilitada(i) && temporales[i]){tiempoInicial[i] = 0;}
        }

        return 0;
    }

    private boolean checkVentana(int t) {
        long gap = System.currentTimeMillis() - tiempoInicial[t];
        if(gap > alpha[ t] && gap < beta) {return true;}
        return false;
    }

    public boolean tHabilitada(int t){
        for(int i = 0; i < marca.length; i++){
            int a = incidencia[i][t] + marca[i];
            if(a < 0){return false;}
        }
        return true;
    }

    public int[] tHabilitadas(){
        int[] c = new int[getCantTransiciones()];

        for(int i = 0; i < getCantTransiciones(); i++){
            if(tHabilitada(i)){c[i] = 1;}
            else{c[i] = 0;}
        }
        return c;
    }

    public int getCantTransiciones(){return incidencia[0].length;}
    public int getCantPlazas(){return incidencia.length;}
    public int[] getMarca(){return marca;}
    public boolean esTemporal(int t){return temporales[t];}
}
