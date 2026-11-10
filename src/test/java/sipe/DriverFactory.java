package sipe;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Cria o navegador conforme as propriedades:
 *   -Dbrowser=chrome|firefox|edge   (padrao: chrome)
 *   -Dheadless=false                (padrao: true, ou seja, sem abrir janela)
 * O Selenium Manager baixa o driver correto automaticamente.
 */
public final class DriverFactory {

    private DriverFactory() { }

    public static String nomeDoNavegador() {
        return System.getProperty("browser", "chrome").toLowerCase();
    }

    public static WebDriver criar() {
        WebDriver driver = novo(nomeDoNavegador(), !"false".equalsIgnoreCase(System.getProperty("headless", "true")));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return driver;
    }

    private static WebDriver novo(String navegador, boolean headless) {
        switch (navegador) {
            case "firefox": {
                FirefoxOptions o = new FirefoxOptions();
                if (headless) o.addArguments("-headless");
                o.addArguments("--width=1280", "--height=900");
                return new FirefoxDriver(o);
            }
            case "edge": {
                EdgeOptions o = new EdgeOptions();
                if (headless) o.addArguments("--headless=new");
                o.addArguments("--window-size=1280,900");
                return new EdgeDriver(o);
            }
            case "chrome": {
                ChromeOptions o = new ChromeOptions();
                if (headless) o.addArguments("--headless=new");
                o.addArguments("--window-size=1280,900", "--no-sandbox");
                return new ChromeDriver(o);
            }
            default:
                throw new IllegalArgumentException("Navegador desconhecido: " + navegador + " (use chrome, firefox ou edge)");
        }
    }
}
