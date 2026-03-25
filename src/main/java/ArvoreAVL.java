public class ArvoreAVL {

    NoAVL raiz;

    public void inserir(String valor) {
        raiz = inserirRec(raiz, valor);
    }

    public void emOrdem(NoAVL no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            emOrdem(no.direita);
        }
    }

    public String emOrdemTexto(NoAVL no) {
        if (no == null) {
            return "";
        }

        return emOrdemTexto(no.esquerda)
                + no.valor + " "
                + emOrdemTexto(no.direita);
    }

    private int altura(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return no.altura;
    }

    private int getBalance(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return altura(no.esquerda) - altura(no.direita);
    }

    private NoAVL inserirRec(NoAVL no, String valor) {

        // 1. INSERÇÃO NORMAL
        if (no == null) {
            return new NoAVL(valor);
        }

        int comparacao = valor.compareToIgnoreCase(no.valor);

        if (comparacao < 0) {
            no.esquerda = inserirRec(no.esquerda, valor);
        } else if (comparacao > 0) {
            no.direita = inserirRec(no.direita, valor);
        } else {
            return no; // duplicado
        }

        // 2. ATUALIZA ALTURA
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        // 3. CALCULA BALANCEAMENTO
        int balance = getBalance(no);

        // 4. ROTAÇÕES

        // Esquerda-Esquerda
        if (balance > 1 && valor.compareToIgnoreCase(no.esquerda.valor) < 0) {
            return rotacaoDireita(no);
        }

        // Direita-Direita
        if (balance < -1 && valor.compareToIgnoreCase(no.direita.valor) > 0) {
            return rotacaoEsquerda(no);
        }

        // Esquerda-Direita
        if (balance > 1 && valor.compareToIgnoreCase(no.esquerda.valor) > 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        // Direita-Esquerda
        if (balance < -1 && valor.compareToIgnoreCase(no.direita.valor) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }
}