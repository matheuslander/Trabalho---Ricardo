public class Main {

    public static void main(String[] args) {
        // Cria árvore com raiz 20
        MinhaArvore arvore = new MinhaArvore(20);

        // Verifica se árvore foi criada com sucesso
        if (!arvore.estaVazia()) {
            System.out.println("A árvore foi criada com sucesso.");
        }

        // Insere vários nós na árvore
        arvore.inserir(10);
        arvore.inserir(30);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(25);
        arvore.inserir(35);
        arvore.inserir(12);
        arvore.inserir(28);
        arvore.inserir(27);

        // Testa o método buscarPai
        No paiDo12 = arvore.buscarPai(12);
        if (paiDo12 != null) {
            System.out.println("\nPai do 12: " + paiDo12.getConteudo());
        }

        // Exibe a árvore inicial
        System.out.println("\nÁrvore inicial:");
        arvore.imprimirArvore();

        // Remoção de um nó folha
        System.out.println("\nRemovendo nó folha (5):");
        arvore.remover(5);
        arvore.imprimirArvore();

        // Remoção de um nó com 1 filho
        System.out.println("\nRemovendo nó com 1 filho (15):");
        arvore.remover(15);
        arvore.imprimirArvore();

        // Remoção de um nó com 2 filhos
        System.out.println("\nRemovendo nó com 2 filhos (30):");
        arvore.remover(30);
        arvore.imprimirArvore();

        // Remoção da raiz
        System.out.println("\nRemovendo nó raiz (20):");
        arvore.remover(20);
        arvore.imprimirArvore();

        // Verifica se a árvore está vazia no final
        if (arvore.estaVazia()) {
            System.out.println("\nA árvore está vazia agora.");
        } else {
            System.out.println("\nAinda há elementos na árvore.");
        }
    }
}