import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Mesa {
    private final Lock[] garfos = new ReentrantLock[5];

    public Mesa() {
        for (int i = 0; i < 5; i++) {
            garfos[i] = new ReentrantLock();
        }
    }

    public boolean pegarGarfos(int id) {
        int garfoDireito = id;
        int garfoEsquerdo = (id + 1) % 5;

        int primeiroGarfo = Math.min(garfoEsquerdo, garfoDireito);
        int segundoGarfo = Math.max(garfoEsquerdo, garfoDireito);

        if (garfos[primeiroGarfo].tryLock()) {
            System.out.println("Filósofo " + id + " pegou o garfo " + primeiroGarfo + ".");
            if (garfos[segundoGarfo].tryLock()) {
                System.out.println("Filósofo " + id + " pegou o garfo " + segundoGarfo + ".");
                return true;
            } else {
                System.out.println("Filósofo " + id + " não conseguiu pegar o garfo " + segundoGarfo + " e liberou " + primeiroGarfo + ".");
                garfos[primeiroGarfo].unlock();
            }
        }
        return false;
    }


    public void liberarGarfos(int id) {
        int garfoDireito = id;
        int garfoEsquerdo = (id + 1) % 5;

        garfos[garfoEsquerdo].unlock();
        garfos[garfoDireito].unlock();
        System.out.println("Filósofo " + id + " liberou os garfos.");
    }
}