package sipe;

import static sipe.SipeConfig.BASE;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chromium.ChromiumDriver;

/** Base de todos os testes: abre um navegador novo por teste e oferece utilitarios. */
public abstract class BaseTest {

    protected WebDriver driver;

    @RegisterExtension
    final EvidenciaExtension evidencia = new EvidenciaExtension(() -> driver);

    @BeforeEach
    void abrirNavegador() {
        driver = DriverFactory.criar();
    }

    @AfterEach
    void fecharNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected void abrir(String url) {
        driver.get(url);
    }

    protected String texto() {
        return driver.findElement(By.tagName("body")).getText();
    }

    protected Object js(String script) {
        return ((JavascriptExecutor) driver).executeScript(script);
    }

    protected void esperar(long milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected static Pattern textoExato(String texto) {
        return Pattern.compile("^\\s*" + Pattern.quote(texto) + "\\s*$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    }

    /** Links visiveis cujo texto bate exatamente com o padrao (itens de menu). */
    protected boolean existeLinkVisivel(Pattern padrao) {
        return existeLinkVisivel(By.tagName("a"), padrao);
    }

    /** Igual ao anterior, mas procurando só dentro de um escopo (ex.: apenas o menu). */
    protected boolean existeLinkVisivel(By escopo, Pattern padrao) {
        return driver.findElements(escopo).stream()
                .anyMatch(a -> a.isDisplayed() && padrao.matcher(a.getText()).matches());
    }

    /** Todos os links (mesmo escondidos em submenus) cujo conteudo de texto contem o padrao. */
    protected List<WebElement> linksPorConteudo(Pattern padrao) {
        return driver.findElements(By.tagName("a")).stream()
                .filter(a -> padrao.matcher(String.valueOf(a.getAttribute("textContent"))).find())
                .toList();
    }

    protected void clicarMenu(String nome) {
        abrir(BASE);
        Pattern padrao = textoExato(nome);
        WebElement link = driver.findElements(By.tagName("a")).stream()
                .filter(a -> a.isDisplayed() && padrao.matcher(a.getText()).matches())
                .findFirst()
                .orElseThrow(() -> new AssertionError("Link de menu '" + nome + "' nao encontrado ou nao visivel"));
        link.click();
    }

    /** Simula largura de tela (celular/tablet/desktop). No Chrome/Edge usa emulacao de dispositivo. */
    protected void definirLargura(int largura) {
        if (driver instanceof ChromiumDriver) {
            Map<String, Object> parametros = Map.<String, Object>of(
                    "width", largura, "height", 900, "deviceScaleFactor", 1, "mobile", largura <= 768);
            ((ChromiumDriver) driver).executeCdpCommand("Emulation.setDeviceMetricsOverride", parametros);
        } else {
            driver.manage().window().setSize(new Dimension(largura, 900));
        }
    }
}
