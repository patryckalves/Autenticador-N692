import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorArquivo {

    public static ArrayList<String> lerArquivo(String caminho) {
        ArrayList<String> linhas = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(caminho));
            String linha;

            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return linhas;
    }
}