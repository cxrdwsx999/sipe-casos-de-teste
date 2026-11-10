package sipe;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.util.function.Supplier;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/** Tira um print ao fim de cada teste e salva em evidencias/ com o ID do caso, o navegador e o resultado. */
public class EvidenciaExtension implements AfterTestExecutionCallback {

    private final Supplier<WebDriver> driver;

    public EvidenciaExtension(Supplier<WebDriver> driver) {
        this.driver = driver;
    }

    @Override
    public void afterTestExecution(ExtensionContext contexto) {
        WebDriver d = driver.get();
        if (d == null) return;
        String status = contexto.getExecutionException().isPresent() ? "FALHA" : "OK";
        String nome = limpar(contexto.getDisplayName()) + "_" + DriverFactory.nomeDoNavegador() + "_" + status + ".png";
        try {
            Path pasta = Path.of(System.getProperty("user.dir"), "evidencias");
            Files.createDirectories(pasta);
            File origem = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
            Files.copy(origem.toPath(), pasta.resolve(nome), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            // sem print (pagina fechada, por exemplo): o teste segue normalmente
        }
    }

    static String limpar(String texto) {
        String semAcento = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        String limpo = semAcento.replaceAll("[^A-Za-z0-9-]+", "_");
        return limpo.length() > 70 ? limpo.substring(0, 70) : limpo;
    }
}
