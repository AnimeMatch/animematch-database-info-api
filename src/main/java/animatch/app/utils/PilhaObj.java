package animatch.app.utils;

public class PilhaObj<T> {

    // Atributos
    private T[] pilha;
    private int topo;

    // 02) Construtor
    public PilhaObj(int capacidade) {
        this.pilha = (T[]) new Object[capacidade];
        this.topo = -1;
    }

    // 03) MÃ©todo isEmpty
    public Boolean isEmpty() {
        if (topo == -1) {
            return true;
        }
        return false;
    }

    // 04) MÃ©todo isFull
    public Boolean isFull() {
        if (topo + 1 == pilha.length) {
            return true;
        }
        return false;
    }

    // 05) MÃ©todo push
    public void push(T info) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        pilha[++topo] = info;
    }

    // 06) MÃ©todo pop
    public T pop() {
        T valorTopo = pilha[topo];
        pilha[topo] = null;
        topo--;
        return valorTopo;
    }

    // 07) MÃ©todo peek
    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            T valorTopo = pilha[topo];
            return valorTopo;
        }
    }

    // 08) MÃ©todo exibe
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha esta vazia");
        } else {
            System.out.println("Conteudo da Pilha:");
            for (int i = topo; i >= 0 ; i--) {
                System.out.println(pilha[i]);
            }
        }

    }


    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }

    public T[] getPilha() {
        return pilha;
    }

}