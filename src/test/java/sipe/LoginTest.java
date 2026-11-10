package sipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sipe.SipeConfig.BASE;
import static sipe.SipeConfig.ERROS_TECNICOS;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Modulo LOG. Os testes com credenciais so rodam se as variaveis de ambiente estiverem definidas
 * (SIPE_EMAIL, SIPE_SENHA, SIPE_TENTAR_LOGIN_INVALIDO). Nunca coloque senha no codigo.
 */
class LoginTest extends BaseTest {

    private static final By CAMPO_SENHA = By.cssSelector("input[type=password]");
    private static final By CAMPO_EMAIL = By.cssSelector(
            "input[type=email], input[name*=mail i], input[name*=login i], input[name*=user i]");

    private void abrirLogin() {
        abrir(BASE);
        Pattern entrar = Pattern.compile("entrar", Pattern.CASE_INSENSITIVE);
        WebElement link = driver.findElements(By.cssSelector("a, button")).stream()
                .filter(a -> a.isDisplayed() && entrar.matcher(a.getText()).find())
                .findFirst()
                .orElseThrow(() -> new AssertionError("Botao 'Entrar' nao encontrado na home"));
        link.click();
    }

    private WebElement campoSenha() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(CAMPO_SENHA));
    }

    private boolean senhaVisivel() {
        return driver.findElements(CAMPO_SENHA).stream().anyMatch(WebElement::isDisplayed);
    }

    private WebElement botaoEnviar() {
        List<WebElement> botoes = driver.findElements(By.cssSelector("button[type=submit], input[type=submit]"));
        if (!botoes.isEmpty()) return botoes.get(0);
        return driver.findElement(By.xpath(
                "//button[contains(normalize-space(.),'Entrar') or contains(normalize-space(.),'Acessar')]"));
    }

    @Test
    @DisplayName("CT-LOG-01 - Abre a tela de login")
    void ctLog01AbreTelaDeLogin() {
        abrirLogin();
        campoSenha();
        assertFalse(ERROS_TECNICOS.matcher(texto()).find(), "Erro tecnico na tela de login");
    }

    @Test
    @DisplayName("CT-LOG-05 - Campos vazios")
    void ctLog05CamposVazios() {
        abrirLogin();
        campoSenha();
        botaoEnviar().click();
        esperar(1000);
        boolean temAviso = Pattern.compile("obrigat|preench|informe|inv[aá]lid", Pattern.CASE_INSENSITIVE)
                .matcher(texto()).find();
        boolean camposInvalidos = !driver.findElements(By.cssSelector("input:invalid")).isEmpty();
        assertTrue(senhaVisivel() && (camposInvalidos || temAviso), "Sem validacao de campos vazios");
    }

    @Test
    @DisplayName("CT-LOG-06 - E-mail em formato invalido")
    void ctLog06EmailInvalido() {
        abrirLogin();
        campoSenha().sendKeys("qualquer");
        driver.findElement(CAMPO_EMAIL).sendKeys("abc");
        botaoEnviar().click();
        esperar(1000);
        assertTrue(senhaVisivel(), "O login aceitou um e-mail invalido");
    }

    @Test
    @DisplayName("CT-LOG-07 - Senha mascarada")
    void ctLog07SenhaMascarada() {
        abrirLogin();
        assertEquals("password", campoSenha().getAttribute("type"));
    }

    @Test
    @DisplayName("CT-LOG-03 - Senha incorreta")
    @EnabledIfEnvironmentVariable(named = "SIPE_TENTAR_LOGIN_INVALIDO", matches = "1")
    void ctLog03SenhaIncorreta() {
        abrirLogin();
        campoSenha().sendKeys("SenhaErrada#123");
        driver.findElement(CAMPO_EMAIL).sendKeys("teste.invalido@example.com");
        botaoEnviar().click();
        esperar(1500);
        assertTrue(senhaVisivel(), "Entrou com credenciais invalidas");
    }

    @Test
    @DisplayName("CT-LOG-02 - Login valido")
    @EnabledIfEnvironmentVariable(named = "SIPE_EMAIL", matches = ".+")
    @EnabledIfEnvironmentVariable(named = "SIPE_SENHA", matches = ".+")
    void ctLog02LoginValido() {
        abrirLogin();
        campoSenha().sendKeys(SipeConfig.senha());
        driver.findElement(CAMPO_EMAIL).sendKeys(SipeConfig.email());
        botaoEnviar().click();
        esperar(2000);
        assertTrue(existeLinkVisivel(Pattern.compile("sair|logout", Pattern.CASE_INSENSITIVE)),
                "Nao encontrou a opcao de sair apos o login");
    }
}
