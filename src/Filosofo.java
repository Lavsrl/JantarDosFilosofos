import java.util.concurrent.ThreadLocalRandom;

class Filosofo extends Thread {
    private final int id;
    private final Mesa mesa;

    public Filosofo(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000)); // 1 a 3 segundos
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comendo.");
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000)); // 1 a 3 segundos
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar(); // Inicia pensandoo
                boolean conseguiuGarfos = false;

                while (!conseguiuGarfos) {
                    conseguiuGarfos = mesa.pegarGarfos(id);
                    if (!conseguiuGarfos) {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1000));
                    }
                }

                comer();
                mesa.liberarGarfos(id);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}