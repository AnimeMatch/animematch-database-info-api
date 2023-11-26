package animatch.app.utils;

public class FilaObj<T> {
    // Atributos
    private int tamanho;
    private T[] fila;

    // Construtor
    public FilaObj(int capacidade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capacidade];
    }

    // Métodos

    /* Método isEmpty() - retorna true se a fila está vazia e false caso contrário */
    public boolean isEmpty() {
        return this.tamanho <= 0;
    }

    /* Método isFull() - retorna true se a fila está cheia e false caso contrário */
    public boolean isFull() {
        return tamanho == this.fila.length;
    }

    /* Método insert - recebe um elemento e insere esse elemento na fila
                       no índice tamanho, e incrementa tamanho
                       Lançar IllegalStateException caso a fila esteja cheia
     */
    public void insert(T info) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        this.fila[this.tamanho++] = info;
    }

    /* Método peek - retorna o primeiro elemento da fila, sem removê-lo */
    public T peek() {
        return this.fila[0];
    }

    /* Método poll - remove e retorna o primeiro elemento da fila, se a fila não estiver
       vazia. Quando um elemento é removido, a fila "anda", e tamanho é decrementado
       Depois que a fila andar, "limpar" o ex-último elemento da fila, atribuindo null
     */
    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        T response = peek();
        tamanho--;
        for (int i = 0; i < tamanho; i++) {
            this.fila[i] = this.fila[i + 1];
            this.fila[i + 1] = null;
        }
        return response;
    }

    /* Método exibe() - exibe o conteúdo da fila */
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila esta vazia");
        } else {
            System.out.println("Conteudo da fila:");
            for (int i = 0; i < tamanho; i++) {
                System.out.println(fila[i]);
            }
        }

    }

    /* Usado nos testes  - complete para que fique certo */
    public int getTamanho() {
        return tamanho;
    }
}

