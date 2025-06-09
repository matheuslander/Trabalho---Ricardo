public class MinhaArvore {
    private No raiz;

    // Construtor define o primeiro valor como a raiz
    public MinhaArvore(int valorRaiz) {
        raiz = new No(valorRaiz);
    }

    // Verifica se a árvore está vazia
    public boolean estaVazia() {
        return raiz == null;
    }

    // Método público para inserir valor
    public void inserir(int valor) {
        No novo = new No(valor);
        raiz = inserirRec(raiz, novo);
    }

    // Inserção recursiva
    private No inserirRec(No atual, No novo) {
        if (atual == null) return novo;

        if (novo.getConteudo() < atual.getConteudo()) {
            atual.setEsquerda(inserirRec(atual.getEsquerda(), novo));
        } else {
            atual.setDireita(inserirRec(atual.getDireita(), novo));
        }

        return atual;
    }

    // Remoção de nó da árvore
    public void remover(int valor) {
        No alvo = buscarFilho(valor);
        No pai = buscarPai(valor);

        if (alvo == null) return; // valor não encontrado

        if (alvo.getEsquerda() != null && alvo.getDireita() != null) {
            removerComDoisFilhos(alvo);
        } else if (alvo.getEsquerda() != null || alvo.getDireita() != null) {
            removerComUmFilho(pai, alvo);
        } else {
            removerFolha(pai, alvo);
        }

        // Caso especial
        if (alvo == raiz && alvo.getEsquerda() == null && alvo.getDireita() == null) {
            raiz = null;
        }
    }

    // Remove nó com dois filhos usando o maior dos menores
    private void removerComDoisFilhos(No alvo) {
        No[] resultado = encontrarMaior(alvo.getEsquerda(), alvo);
        No maior = resultado[0];
        No paiDoMaior = resultado[1];

        alvo.setConteudo(maior.getConteudo());

        if (paiDoMaior == alvo) {
            alvo.setEsquerda(maior.getEsquerda());
        } else {
            paiDoMaior.setDireita(maior.getEsquerda());
        }
    }

    // Busca o maior nó da subárvore esquerda e seu pai
    private No[] encontrarMaior(No atual, No pai) {
        if (atual.getDireita() == null) {
            return new No[] { atual, pai };
        }
        return encontrarMaior(atual.getDireita(), atual);
    }

    // Remove um nó com apenas um filho
    private void removerComUmFilho(No pai, No alvo) {
        No filho = (alvo.getEsquerda() != null) ? alvo.getEsquerda() : alvo.getDireita();

        if (pai == null) {
            raiz = filho; // o alvo era a raiz
        } else if (pai.getEsquerda() == alvo) {
            pai.setEsquerda(filho);
        } else {
            pai.setDireita(filho);
        }
    }

    // Remove um nó folha (sem filhos)
    private void removerFolha(No pai, No alvo) {
        if (pai == null) {
            raiz = null;
        } else if (pai.getEsquerda() == alvo) {
            pai.setEsquerda(null);
        } else {
            pai.setDireita(null);
        }
    }

    // Busca um nó com determinado valor
    public No buscarFilho(int valor) {
        return buscarFilhoRec(raiz, valor);
    }

    private No buscarFilhoRec(No atual, int valor) {
        if (atual == null) return null;

        if (atual.getConteudo() == valor) return atual;
        if (valor < atual.getConteudo()) return buscarFilhoRec(atual.getEsquerda(), valor);
        return buscarFilhoRec(atual.getDireita(), valor);
    }

    // Busca o pai de um determinado nó
    public No buscarPai(int valor) {
        return buscarPaiRecursivo(this.raiz, buscarFilho(valor));
    }

    private No buscarPaiRecursivo(No pai, No filho) {
        if (filho == null) {
            System.out.println("Elemento não encontrado na árvore.");
            return null;
        }
        if (pai == null) return null;

        if (pai.getEsquerda() == filho || pai.getDireita() == filho) {
            return pai;
        }

        if (pai.getConteudo() > filho.getConteudo()) {
            return buscarPaiRecursivo(pai.getEsquerda(), filho);
        }

        return buscarPaiRecursivo(pai.getDireita(), filho);
    }

    // Exibe a árvore visualmente com indentação
    public void imprimirArvore() {
        imprimirArvore(raiz, 0);
    }

    private void imprimirArvore(No no, int nivel) {
        if (no == null) return;

        imprimirArvore(no.getDireita(), nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }
        System.out.println(no.getConteudo());

        imprimirArvore(no.getEsquerda(), nivel + 1);
    }
}