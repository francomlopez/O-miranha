public class RdP {
    private final int[][] incidencia;
    private int[] marca;

    public RdP() {
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

        marca = new int[]{0,0,0,8,8,4,4,0,0,0,0,1,1,1,0,0,1,0,0};

    }

    // Metodo que se le pasa el vector de disparo y actualiza la marca
    // Se debe verificar que el disparo se puede realizar previamente

    public boolean dispararTransicion(int t) {
        for(int i = 0; i < marca.length; i++){
            int a = incidencia[i][t] + marca[i];
            if(a < 0){return false;}
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
        return true;
    }
}
