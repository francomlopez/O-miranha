public class Monitor {
    int[][] incidencia;

    public Monitor() {
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
    }
}