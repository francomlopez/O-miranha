public class Main {


    public static void main(String[] args)
    {
        System.out.println("miranha");
        Politica polit = new Politica(new RdP(null))  ;
        int[] c = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println(polit.cualDespierto(c));

    }
}
