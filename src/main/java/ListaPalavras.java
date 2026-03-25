import java.util.ArrayList;

public class ListaPalavras {

    public static ArrayList<String> separarPalavras(String linha) {

        ArrayList<String> palavras = new ArrayList<>();

        String[] partes = linha.split(" ");

        for (String palavra : partes) {

            if (!palavra.isEmpty()) {
                palavras.add(palavra);
            }

        }

        return palavras;
    }
}