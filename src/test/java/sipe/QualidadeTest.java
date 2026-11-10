package sipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sipe.SipeConfig.BASE;
import static sipe.SipeConfig.BASE_HTTP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/** Modulos RSP (responsividade), ACE (acessibilidade), SEG (HTTPS) e DES (desempenho). */
class QualidadeTest extends BaseTest {

    private static final By MENU = By.cssSelector(".navbar-nav a");

    @ParameterizedTest(name = "CT-RSP-01 - Sem rolagem horizontal a {0}px")
    @ValueSource(ints = {360, 768, 1280})
    void ctRsp01SemRolagemHorizontal(int largura) {
        definirLargura(largura);
        abrir(BASE);
        esperar(500);
        Number excesso = (Number) js("return document.documentElement.scrollWidth - window.innerWidth;");
        assertTrue(excesso.intValue() <= 1, "Rolagem horizontal de " + excesso + "px em " + largura + "px de largura");
    }

    @Test
    @DisplayName("CT-RSP-02 - Menu em tela pequena")
    void ctRsp02MenuEmTelaPequena() {
        definirLargura(360);
        abrir(BASE);
        var edital = textoExato("Edital");
        if (!existeLinkVisivel(MENU, edital)) {
            List<WebElement> botoes = driver.findElements(By.cssSelector(
                    "button[aria-label*=menu i], .navbar-toggler, .menu-toggle, [class*=hamburger]"));
            assertFalse(botoes.isEmpty(), "Menu escondido e sem botao para abri-lo");
            botoes.get(0).click();
            esperar(500);
        }
        assertTrue(existeLinkVisivel(MENU, edital), "O item 'Edital' nao fica acessivel em tela pequena");
    }

    @Test
    @DisplayName("CT-ACE-01 - Navegacao por teclado")
    void ctAce01NavegacaoPorTeclado() {
        abrir(BASE);
        Set<String> alcancados = new HashSet<>();
        Actions acoes = new Actions(driver);
        for (int i = 0; i < 20; i++) {
            acoes.sendKeys(Keys.TAB).perform();
            alcancados.add(String.valueOf(js(
                    "return document.activeElement ? document.activeElement.outerHTML.slice(0, 80) : '';")));
        }
        assertTrue(alcancados.size() >= 5, "O teclado alcanca poucos elementos: " + alcancados.size());
    }

    @Test
    @DisplayName("CT-ACE-02 - Imagens com texto alternativo")
    void ctAce02ImagensComAlt() {
        abrir(BASE);
        List<?> semAlt = (List<?>) js(
                "return Array.from(document.images).filter(i => !i.hasAttribute('alt')).map(i => i.src);");
        assertTrue(semAlt.isEmpty(), "Imagens sem atributo alt: " + semAlt);
    }

    @Test
    @DisplayName("CT-ACE-04 - Links com icone tem nome acessivel")
    void ctAce04LinksSemNome() {
        abrir(BASE);
        List<?> semNome = (List<?>) js(
                "return Array.from(document.querySelectorAll('a[href]')).filter(a => !(a.textContent.trim()"
                + " || a.getAttribute('aria-label') || a.getAttribute('title')"
                + " || (a.querySelector('img[alt]') && a.querySelector('img[alt]').alt.trim()))).map(a => a.href);");
        assertTrue(semNome.isEmpty(), "Links sem texto, aria-label ou title: " + semNome);
    }

    @Test
    @DisplayName("CT-HTM-01 - IDs unicos no HTML")
    void ctHtm01IdsUnicos() {
        abrir(BASE);
        List<?> repetidos = (List<?>) js(
                "const c = {}; document.querySelectorAll('[id]').forEach(e => c[e.id] = (c[e.id] || 0) + 1);"
                + " return Object.keys(c).filter(k => c[k] > 1);");
        assertTrue(repetidos.isEmpty(), "IDs repetidos na pagina: " + repetidos);
    }

    @Test
    @DisplayName("CT-SEG-01 - HTTP redireciona para HTTPS")
    void ctSeg01HttpRedirecionaParaHttps() {
        Http.Resposta r = Http.get(BASE_HTTP);
        assertTrue(r.urlFinal().startsWith("https://"), "O http nao redireciona para https (final: " + r.urlFinal() + ")");
    }

    @Test
    @DisplayName("CT-DES-01 - Tempo de carregamento")
    void ctDes01TempoDeCarregamento() {
        abrir(BASE);
        esperar(500);
        Number ms = (Number) js("const n = performance.getEntriesByType('navigation')[0]; return n.loadEventEnd - n.startTime;");
        assertTrue(ms.doubleValue() > 0 && ms.doubleValue() < 3000,
                "Carregamento levou " + Math.round(ms.doubleValue()) + " ms (meta: menos de 3000 ms)");
    }
}
