package sipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static sipe.SipeConfig.BASE;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

/** Modulo CNT: consistencia de conteudo do site. */
class ConteudoTest extends BaseTest {

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;

    @Test
    @DisplayName("CT-CNT-01 - Numeracao da edicao")
    void ctCnt01NumeracaoDaEdicao() {
        abrir(BASE);
        String tudo = driver.getTitle() + " " + texto();
        assertTrue(Pattern.compile("\\bXIV\\b").matcher(tudo).find(), "'XIV' nao aparece");
        assertFalse(Pattern.compile("\\bXV\\b").matcher(tudo).find(), "Aparece 'XV' (numeracao diferente)");
    }

    @Test
    @DisplayName("CT-CNT-02 - Datas do evento")
    void ctCnt02DatasDoEvento() {
        abrir(BASE);
        assertTrue(Pattern.compile("23\\s*(a|à|-|–)\\s*27\\s*(de)?\\s*novembro", FLAGS).matcher(texto()).find(),
                "Datas 23 a 27 de novembro nao encontradas na home");
    }

    @Test
    @DisplayName("CT-CNT-04 - Edital acessivel")
    void ctCnt04EditalAcessivel() {
        abrir(BASE + "edital.php");
        boolean temPdf = !driver.findElements(By.cssSelector(
                "a[href$='.pdf' i], iframe[src*='.pdf' i], embed[src*='.pdf' i], object[data*='.pdf' i]")).isEmpty();
        assertTrue(texto().length() > 500 || temPdf, "Pagina do edital sem texto e sem PDF incorporado ou linkado");
    }

    @Test
    @DisplayName("CT-CNT-07 - Prazo de encerramento unico")
    void ctCnt07PrazoUnico() {
        StringBuilder textos = new StringBuilder();
        for (String url : List.of(BASE, BASE + "edital.php")) {
            abrir(url);
            textos.append(' ').append(texto());
        }
        Set<String> datas = new TreeSet<>();
        for (String d : List.of("09/11", "15/11")) {
            if (textos.indexOf(d) >= 0) datas.add(d);
        }
        assumeFalse(datas.isEmpty(), "Nenhuma das duas datas aparece no texto das paginas");
        assertEquals(1, datas.size(), "Datas de encerramento divergentes no site: " + datas);
    }

    @Test
    @DisplayName("CT-CNT-08 - PDF 'Saiba mais' da home abre")
    void ctCnt08PdfSaibaMais() {
        abrir(BASE);
        var embeds = driver.findElements(By.cssSelector("embed[src$='.pdf' i]"));
        assertFalse(embeds.isEmpty(), "Nenhum PDF incorporado (embed) encontrado na home");
        String src = embeds.get(0).getAttribute("src");
        Http.Resposta r = Http.get(src);
        assertEquals(200, r.status(), src + " respondeu " + r.status());
        assertTrue(r.corpo().length > 4 && new String(r.corpo(), 0, 4).equals("%PDF"), "O arquivo nao e um PDF valido");
    }
}
