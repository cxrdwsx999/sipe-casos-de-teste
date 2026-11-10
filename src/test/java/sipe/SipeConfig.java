package sipe;

import java.util.regex.Pattern;

/** Configuracoes compartilhadas. Credenciais vem de variaveis de ambiente, nunca do codigo. */
public final class SipeConfig {

    public static final String BASE = valor("SIPE_BASE_URL", "https://eventosanima.com.br/fadergs/sipe/");
    public static final String BASE_HTTP = BASE.replaceFirst("^https://", "http://");

    public static final Pattern ERROS_TECNICOS = Pattern.compile(
            "Traceback|Fatal error|Warning:|Notice:|mysqli|SQLSTATE|Stack trace|Internal Server Error",
            Pattern.CASE_INSENSITIVE);

    private SipeConfig() { }

    public static String email() { return System.getenv("SIPE_EMAIL"); }

    public static String senha() { return System.getenv("SIPE_SENHA"); }

    private static String valor(String variavel, String padrao) {
        String v = System.getenv(variavel);
        return (v == null || v.isBlank()) ? padrao : v;
    }
}
