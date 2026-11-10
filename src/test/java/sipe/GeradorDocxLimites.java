package sipe;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * Gera arquivos .docx com a quantidade EXATA de palavras (testes de valor limite do Resumo Simples).
 * Uso: mvn test-compile exec:java      -> saida em tools/saida/
 * ATENCAO: arquivos de teste. Nao envie ao sistema real sem autorizacao da organizacao.
 */
public class GeradorDocxLimites {

    public static void main(String[] args) throws Exception {
        Map<String, Integer> casos = new LinkedHashMap<>();
        casos.put("resumo_simples_349", 349);
        casos.put("resumo_simples_350", 350);
        casos.put("resumo_simples_500", 500);
        casos.put("resumo_simples_501", 501);

        Path saida = Path.of("tools", "saida");
        Files.createDirectories(saida);

        for (Map.Entry<String, Integer> caso : casos.entrySet()) {
            String texto = IntStream.rangeClosed(1, caso.getValue())
                    .mapToObj(i -> "palavra" + i)
                    .collect(Collectors.joining(" "));
            Path arquivo = saida.resolve(caso.getKey() + ".docx");

            try (XWPFDocument doc = new XWPFDocument(); OutputStream os = Files.newOutputStream(arquivo)) {
                doc.createParagraph().createRun().setText(texto);
                doc.write(os);
            }
            try (InputStream is = Files.newInputStream(arquivo); XWPFDocument lido = new XWPFDocument(is)) {
                int palavras = lido.getParagraphs().get(0).getText().trim().split("\\s+").length;
                System.out.println(arquivo.getFileName() + ": " + palavras + " palavras");
            }
        }
    }
}
