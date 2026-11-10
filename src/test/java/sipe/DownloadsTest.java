package sipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sipe.SipeConfig.BASE;

import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/** Modulo DWN: modelos disponiveis para download. */
class DownloadsTest extends BaseTest {

    private List<String> linksDeArquivos() {
        abrir(BASE);
        return driver.findElements(By.cssSelector("a[href]")).stream()
                .map((WebElement a) -> a.getAttribute("href"))
                .filter(h -> h != null && Pattern.compile("\\.(docx|pptx)(\\?|$)", Pattern.CASE_INSENSITIVE).matcher(h).find())
                .toList();
    }

    private void baixarEValidar(String trecho, String extensao) {
        List<String> todos = linksDeArquivos();
        String url = todos.stream()
                .filter(u -> u.toLowerCase().contains(trecho.toLowerCase()) && u.toLowerCase().split("\\?")[0].endsWith(extensao))
                .findFirst()
                .orElse(null);
        assertTrue(url != null, "Nenhum link " + extensao + " com '" + trecho + "' encontrado. Arquivos na home: " + todos);
        Http.Resposta r = Http.get(url);
        assertEquals(200, r.status(), url + " respondeu " + r.status());
        assertTrue(r.corpo().length > 1000, "Arquivo muito pequeno ou vazio");
        assertFalse(r.corpo()[0] != 'P' || r.corpo()[1] != 'K', "Nao parece um .docx/.pptx valido (assinatura ZIP ausente)");
    }

    @Test
    @DisplayName("CT-DWN-01 - Modelo de Resumo Simples")
    void ctDwn01ResumoSimples() {
        baixarEValidar("simples", ".docx");
    }

    @Test
    @DisplayName("CT-DWN-02 - Modelo de Resumo Expandido")
    void ctDwn02ResumoExpandido() {
        baixarEValidar("expandido", ".docx");
    }

    @Test
    @DisplayName("CT-DWN-03 - Modelo de Poster")
    void ctDwn03Poster() {
        baixarEValidar("", ".pptx");
    }
}
