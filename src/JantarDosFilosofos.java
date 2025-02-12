public class JantarDosFilosofos {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Mesa mesa = new Mesa();
        Filosofo[] filosofos = new Filosofo[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(i, mesa);
            filosofos[i].start();
        }
    }
}