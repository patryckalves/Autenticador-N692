import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        String caminho = "src/main/resources/texto.txt";

        ArrayList<String> linhas = LeitorArquivo.lerArquivo(caminho);

        StringBuilder hashFinal = new StringBuilder();

        for (String linha : linhas) {

            ArrayList<String> palavras = ListaPalavras.separarPalavras(linha);

            ArvoreAVL arvore = new ArvoreAVL();

            for (int i = palavras.size() - 1; i >= 0; i--) {
                arvore.inserir(palavras.get(i));
            }

            String resultado = arvore.emOrdemTexto(arvore.raiz);

            if (resultado.trim().isEmpty()) {
                continue;
            }

            System.out.println(resultado);

            String hash = HashUtil.gerarSHA1(resultado);
            System.out.println("HASH: " + hash);

            hashFinal.append(hash);
        }

        String codigoFinal = HashUtil.gerarSHA1(hashFinal.toString());

        System.out.println("\nCódigo Autenticador");
        System.out.println(codigoFinal);
    }
}