package sipe;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

/**
 * Registrado via ServiceLoader (src/test/resources/META-INF/services). Ao fim de "mvn test", escreve
 * relatorios/tabela-resultados.md, pronta para colar em docs/04-relatorio-execucao.md.
 */
public class TabelaResultadosListener implements TestExecutionListener {

    private final List<String> linhas = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger aprovados = new AtomicInteger();
    private final AtomicInteger reprovados = new AtomicInteger();
    private final AtomicInteger ignorados = new AtomicInteger();

    @Override
    public void executionSkipped(TestIdentifier teste, String motivo) {
        if (teste.isTest()) registrar(teste, "Ignorado", motivo);
    }

    @Override
    public void executionFinished(TestIdentifier teste, TestExecutionResult resultado) {
        if (!teste.isTest()) return;
        String mensagem = resultado.getThrowable().map(Throwable::getMessage).orElse("");
        switch (resultado.getStatus()) {
            case SUCCESSFUL:
                registrar(teste, "Aprovado", "");
                break;
            case FAILED:
                registrar(teste, "Reprovado", mensagem);
                break;
            default:
                registrar(teste, "Ignorado", mensagem);
        }
    }

    private void registrar(TestIdentifier teste, String status, String observacao) {
        switch (status) {
            case "Aprovado": aprovados.incrementAndGet(); break;
            case "Reprovado": reprovados.incrementAndGet(); break;
            default: ignorados.incrementAndGet();
        }
        String obs = observacao == null ? "" : observacao.split("\\R", 2)[0].replace("|", "/");
        if (obs.length() > 140) obs = obs.substring(0, 140) + "...";
        linhas.add("| " + teste.getDisplayName() + " | " + obs + " | " + status + " | evidencias/ | |");
    }

    @Override
    public void testPlanExecutionFinished(TestPlan plano) {
        List<String> saida = new ArrayList<>();
        saida.add("Navegador: " + DriverFactory.nomeDoNavegador());
        saida.add("");
        saida.add("| ID e descricao | Resultado obtido | Status | Evidencia | Issue |");
        saida.add("|---|---|---|---|---|");
        List<String> ordenadas = new ArrayList<>(linhas);
        Collections.sort(ordenadas);
        saida.addAll(ordenadas);
        saida.add("");
        saida.add("Aprovados: " + aprovados + " | Reprovados: " + reprovados + " | Ignorados: " + ignorados);
        try {
            Path pasta = Path.of(System.getProperty("user.dir"), "relatorios");
            Files.createDirectories(pasta);
            Files.write(pasta.resolve("tabela-resultados.md"), saida, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Nao foi possivel gravar a tabela de resultados: " + e.getMessage());
        }
    }
}
