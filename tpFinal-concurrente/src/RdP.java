import java.util.HashMap;

public class RdP {
    private final int[][] incidencia;
    private int[] marca;
    private boolean[] temporales; // vector que indica que transiciones son temporales
    private long[] tiempoInicial; // vector que lleva el tiempo inicial de las transiciones temporales
    private long[] alpha;
    private long beta;
    private Monitor monitor;
    //private long tinicial;
    private HashMap<Integer, String> nombreT;
    private HashMap<Integer, String> nombreP;

    public RdP() {
        incidencia = new int[][]{{0,1,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,1,0,-1,0,0,0,0,0,0,0,0,0,0,0,0},
                                {1,-1,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,-1,0,-1,0,0,0,4,0},
                                {0,0,0,0,0,0,0,0,0,0,-1,0,-1,0,0,0,4},
                                {0,-1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,-1,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,1,0,1,0,-1,-1,0,0,0,0,0,0},
                                {0,0,0,0,0,0,1,0,1,0,0,-1,-1,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,-4,0},
                                {0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,-4},
                                {-1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,-1,0,0,0,0,0,1,1,0,0,0,0,0,0},
                                {0,0,0,0,-1,0,0,0,0,0,0,1,1,0,0,0,0},
                                {0,0,0,1,0,-1,0,0,0,0,0,0,0,-1,0,0,0},
                                {0,0,0,0,1,0,-1,0,0,0,0,0,0,0,-1,0,0},
                                {0,0,0,-1,-1,1,1,1,1,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,-1,0,0,0,0,0,1,0,0,0},
                                {0,0,0,0,0,0,0,0,-1,0,0,0,0,0,1,0,0}};

        marca = new int[]{0,0,0,8,8,4,4,0,0,0,0,1,1,1,0,0,1,0,0};
        temporales = new boolean[]{true,false,false,false,false,true,true,true,true,false,false,false,false,true,true,true,true};
        tiempoInicial = new long[]{0,-1,-1,-1,-1,0,0,0,0,-1,-1,-1,-1,0,0,0,0};
        alpha = new long[]{50,-1,-1,-1,-1,50,50,50,50,-1,-1,-1,-1,150,150,500,500};
        beta = 30000;
        actTimeStamps();
        //tinicial = System.currentTimeMillis();
        nombreT = new HashMap<>();
        nombreT.put(0,"ArrivalRate");
        nombreT.put(1,"AsignaP1");
        nombreT.put(2,"AsignaP2");
        nombreT.put(3,"EmpezarP1");
        nombreT.put(4,"EmpezarP2");
        nombreT.put(5,"FinalizarT1P1");
        nombreT.put(6,"FinalizarT1P2");
        nombreT.put(7,"FinalizarT2P1");
        nombreT.put(8,"FinalizarT2P2");
        nombreT.put(9,"P1M1");
        nombreT.put(10,"P1M2");
        nombreT.put(11,"P2M1");
        nombreT.put(12,"P2M2");
        nombreT.put(13,"ProcesarT2P1");
        nombreT.put(14,"ProcesarT2P2");
        nombreT.put(15,"VaciadoM1");
        nombreT.put(16,"VaciadoM2");

        nombreP = new HashMap<>();
        nombreP.put(0,"ColaP1");
        nombreP.put(1,"ColaP2");
        nombreP.put(2,"ColaProcesos");
        nombreP.put(3,"DisponibleM1");
        nombreP.put(4,"DisponibleM2");
        nombreP.put(5,"LimiteColaP1");
        nombreP.put(6,"LimiteColaP2");
        nombreP.put(7,"ListoP1");
        nombreP.put(8,"ListoP2");
        nombreP.put(9,"M1");
        nombreP.put(10,"M2");
        nombreP.put(11,"P0");
        nombreP.put(12,"ProcesadorP1");
        nombreP.put(13,"ProcesadorP2");
        nombreP.put(14,"ProcesandoP1");
        nombreP.put(15,"ProcesandoP2");
        nombreP.put(16,"RecursoTareas");
        nombreP.put(17,"Tarea2P1");
        nombreP.put(18,"Tarea2P2");
    }

    // Metodo que se le pasa el vector de disparo y actualiza la marca
    // Se debe verificar que el disparo se puede realizar previamente

    public int dispararTransicion(int t) {
        if(!tHabilitada(t)){return 1;}

        if(esTemporal(t)){
            if(!checkVentana(t)) {
                monitor.releaseMutex();
                long actual = System.currentTimeMillis();
                long nap = tiempoInicial[t] + alpha[t] - actual;
                //System.out.println(System.currentTimeMillis()-tinicial + ":"+"transicion t: " + t + "sin ventana. nap="+nap);
                try {
                    Thread.sleep(nap);
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
        actTimeStamps();

        System.out.print("T" + t + " ");

        // Comprobacion de invariantes de plaza
        if(marca[3]+marca[9] != 8){System.out.println("Error en el invariante " + nombreP.get(3) + "+" + nombreP.get(9) + ". Resultado: " + nombreP.get(3) + "+" + nombreP.get(9) + "=" +String.valueOf(marca[3]+marca[9]));}
        if(marca[4]+marca[10] != 8){System.out.println("Error en el invariante " + nombreP.get(4) + "+" + nombreP.get(10) + ". Resultado: " + nombreP.get(4) + "+" + nombreP.get(10) + "=" +String.valueOf(marca[4]+marca[10]));}
        if(marca[0]+marca[5] != 4){System.out.println("Error en el invariante " + nombreP.get(0) + "+" + nombreP.get(5) + ". Resultado: " + nombreP.get(0) + "+" + nombreP.get(5) + "=" +String.valueOf(marca[0]+marca[5]));}
        if(marca[1]+marca[6] != 4){System.out.println("Error en el invariante " + nombreP.get(1) + "+" + nombreP.get(6) + ". Resultado: " + nombreP.get(1) + "+" + nombreP.get(6) + "=" +String.valueOf(marca[1]+marca[6]));}
        if(marca[2]+marca[11] != 1){System.out.println("Error en el invariante " + nombreP.get(2) + "+" + nombreP.get(11) + ". Resultado: " + nombreP.get(2) + "+" + nombreP.get(11) + "=" +String.valueOf(marca[2]+marca[11]));}
        if(marca[7]+marca[12]+marca[14]+marca[17] != 1){System.out.println("Error en el invariante " + nombreP.get(7) + "+" + nombreP.get(12)+ "+" + nombreP.get(14)+ "+" + nombreP.get(17) + ". Resultado: " + nombreP.get(7) + "+" + nombreP.get(12)+ "+" + nombreP.get(14)+ "+" + nombreP.get(17) + "=" +String.valueOf(marca[7]+marca[12]+marca[14]+marca[17]));}
        if(marca[8]+marca[13]+marca[15]+marca[18] != 1){System.out.println("Error en el invariante " + nombreP.get(8) + "+" + nombreP.get(13)+ "+" + nombreP.get(15)+ "+" + nombreP.get(18) + ". Resultado: " + nombreP.get(8) + "+" + nombreP.get(13)+ "+" + nombreP.get(15)+ "+" + nombreP.get(18) + "=" +String.valueOf(marca[7]+marca[12]+marca[14]+marca[17]));}
        if(marca[14]+marca[15]+marca[16]+marca[17]+marca[18] != 1){System.out.println("Error en el invariante " + nombreP.get(14) + "+" + nombreP.get(15)+ "+"  + nombreP.get(16)+ "+" + nombreP.get(17)+ "+" + nombreP.get(18)+". Resultado: " + nombreP.get(14) + "+" + nombreP.get(15)+ "+"  + nombreP.get(16)+ "+" + nombreP.get(17)+ "+" + nombreP.get(18)+ "=" +String.valueOf(marca[14]+marca[15]+marca[16]+marca[17]+marca[18]));}

        return 0;
    }

    private boolean checkVentana(int t) {
        long gap = System.currentTimeMillis() - tiempoInicial[t];
        if(gap >= alpha[t] && gap < beta) {return true;}
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
            if(tHabilitada(i)){
                c[i] = 1;
            }
            else{c[i] = 0;}
        }
        return c;
    }

    public int getCantTransiciones(){return incidencia[0].length;}
    public int getCantPlazas(){return incidencia.length;}
    public int[] getMarca(){return marca;}
    public boolean esTemporal(int t){return temporales[t];}
    public void setMonitor(Monitor m){monitor = m;}
    public void actTimeStamps(){
        for(int i = 0;i<this.getCantTransiciones(); i++){
            if(tHabilitada(i) && temporales[i]){
                if(tiempoInicial[i] == 0){tiempoInicial[i] = System.currentTimeMillis();}
            }
            if(!tHabilitada(i) && temporales[i]){tiempoInicial[i] = 0;}
        }
    }
    public String marcaToString(){
        String marcaS = "[";
        for(int i = 0; i< 18 ; i++){
            marcaS += " " + marca[i] + ",";

        }
        marcaS += "]";
        return marcaS;
    }

    public HashMap<Integer, String> getNombreT() {
        return nombreT;
    }
}
