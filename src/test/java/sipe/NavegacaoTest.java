package sipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sipe.SipeConfig.BASE;
import static sipe.SipeConfig.ERROS_TECNICOS;

import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

/** Modulo NAV (docs/03-casos-de-teste.md). */
class NavegacaoTest extends BaseTest {

    @Test
    @DisplayName("CT-NAV-01 - Pagina inicial carrega")
    void ctNav01PaginaInicial() {
        assertTrue(Http.get(BASE).status() < 400, "A home nao respondeu com sucesso");
        abrir(BASE);
        assertTrue(Pattern.compile("XIV\\s*SIPE", Pattern.CASE_INSENSITIVE)
                .matcher(driver.getTitle() + " " + texto()).find(), "'XIV SIPE' nao aparece na home");
    }

    @Test
    @DisplayName("CT-NAV-02 - Menu Inicio")
    void ctNav02MenuInicio() {
        clicarMenu("Início");
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("sipe"), "URL inesperada: " + driver.getCurrentUrl());
    }

    @Test
    @DisplayName("CT-NAV-03 - Menu Edital")
    void ctNav03MenuEdital() {
        clicarMenu("Edital");
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("edital"), "URL inesperada: " + driver.getCurrentUrl());
        assertFalse(ERROS_TECNICOS.matcher(texto()).find(), "Erro tecnico exibido na pagina");
    }

    @Test
    @DisplayName("CT-NAV-04 - Menu Local")
    void ctNav04MenuLocal() {
        clicarMenu("Local");
        assertFalse(ERROS_TECNICOS.matcher(texto()).find(), "Erro tecnico exibido na pagina");
        assertTrue(Pattern.compile("FADERGS|Porto Alegre", Pattern.CASE_INSENSITIVE).matcher(texto()).find(),
                "Informacoes do local nao encontradas");
    }

    @Test
    @DisplayName("CT-NAV-05 - Menu Contatos")
    void ctNav05MenuContatos() {
        clicarMenu("Contatos");
        assertFalse(ERROS_TECNICOS.matcher(texto()).find(), "Erro tecnico exibido na pagina");
        assertTrue(Pattern.compile("@|\\(\\d{2}\\)|\\d{4}-\\d{4}").matcher(texto()).find(),
                "Nenhum e-mail ou telefone encontrado");
    }

    @Test
    @DisplayName("CT-NAV-06 - Edicoes anteriores abrem")
    void ctNav06EdicoesAnteriores() {
        abrir(BASE);
        for (String ano : List.of("2022", "2023", "2024")) {
            List<WebElement> links = linksPorConteudo(Pattern.compile(ano));
            assertFalse(links.isEmpty(), "Link da edicao " + ano + " nao encontrado");
            String url = links.get(0).getAttribute("href");
            int status = Http.get(url).status();
            assertTrue(status < 400, "Edicao " + ano + " (" + url + ") respondeu " + status);
        }
    }

    @Test
    @DisplayName("CT-NAV-09 - Pagina inexistente")
    void ctNav09PaginaInexistente() {
        Http.Resposta r = Http.get(BASE + "pagina-que-nao-existe-xyz.php");
        assertEquals(404, r.status(), "Esperado 404");
        assertFalse(ERROS_TECNICOS.matcher(r.texto()).find(), "Erro tecnico exposto ao usuario");
    }
}
