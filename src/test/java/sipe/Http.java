package sipe;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/** Requisicoes GET simples (para status, redirecionamentos e downloads). */
public final class Http {

    public record Resposta(int status, String urlFinal, byte[] corpo) {
        public String texto() { return new String(corpo, StandardCharsets.UTF_8); }
    }

    private static final HttpClient CLIENTE = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    private Http() { }

    public static Resposta get(String url) {
        try {
            HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                    .timeout(Duration.ofSeconds(30))
                    .header("User-Agent", "Mozilla/5.0 (trabalho academico de teste de software)")
                    .GET()
                    .build();
            HttpResponse<byte[]> r = CLIENTE.send(req, HttpResponse.BodyHandlers.ofByteArray());
            return new Resposta(r.statusCode(), r.uri().toString(), r.body());
        } catch (IOException e) {
            throw new RuntimeException("Falha ao acessar " + url + ": " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Requisicao interrompida: " + url, e);
        }
    }
}
