# 06 — Automação dos testes (Java + Maven + JUnit 5 + Selenium)

Parte dos casos de [03-casos-de-teste.md](03-casos-de-teste.md) é executada por código. Os demais continuam **manuais**
(principalmente a submissão, que mexe no sistema real).

> **Importante:** o código foi escrito a partir da estrutura geral do site e **ainda não foi compilado nem executado
> contra ele** (o ambiente em que foi escrito não tinha Maven nem acesso ao site). Só a sintaxe foi verificada.
> Na primeira execução podem aparecer erros de compilação pequenos ou testes que falham por texto/seletor diferente
> do esperado (ex.: o menu não se chama exatamente "Início"). Isso é normal: antes de reportar bug, abra o site e
> confirme se o problema está no site ou no teste. Ajustar um teste é um commit como qualquer outro (`test: ajusta ...`).

## 1. Pré-requisitos

- **JDK 17 ou superior** (`java -version`)
- **Maven 3.9+** (`mvn -version`)
- **Google Chrome** (ou Firefox / Edge). O Selenium Manager baixa o driver sozinho.
- **IDE** (opcional): IntelliJ IDEA ou Eclipse, abrindo a pasta como projeto Maven.

## 2. Estrutura do código (`src/test/java/sipe/`)

| Classe | Função |
|---|---|
| `SipeConfig` | URL do site e leitura de credenciais por variável de ambiente |
| `Http` | Requisições GET (status, redirecionamento, download de arquivos) |
| `DriverFactory` | Cria o navegador (chrome, firefox ou edge; com ou sem janela) |
| `BaseTest` | Abre/fecha o navegador em cada teste e traz utilitários (menu, largura de tela etc.) |
| `EvidenciaExtension` | Tira um print ao fim de cada teste e salva em `evidencias/` |
| `NavegacaoTest` | CT-NAV |
| `DownloadsTest` | CT-DWN |
| `ConteudoTest` | CT-CNT |
| `LoginTest` | CT-LOG |
| `QualidadeTest` | CT-RSP, CT-ACE, CT-HTM, CT-SEG, CT-DES |
| `TabelaResultadosListener` | Gera `relatorios/tabela-resultados.md` ao fim da execução |
| `GeradorDocxLimites` | Gera .docx com 349/350/500/501 palavras (valor limite) |

Cada teste leva o ID do caso no `@DisplayName` (ex.: `CT-NAV-03 - Menu Edital`), o que liga o código ao documento de casos.

## 3. Executar

```bash
mvn test                                        # todos os testes, Chrome sem abrir janela
mvn test -Dheadless=false                       # abre o navegador (ótimo para a apresentação)
mvn test -Dtest=NavegacaoTest                   # só uma classe
mvn test -Dtest=NavegacaoTest#ctNav03MenuEdital # só um caso
mvn test -Dbrowser=firefox                      # compatibilidade (CT-CMP-02)
mvn test -Dbrowser=edge                         # Microsoft Edge instalado (CT-CMP-03)
```

Testes que usam login ficam **ignorados** até você definir variáveis de ambiente (nunca coloque senha no código ou no Git):

```bash
# Linux/macOS
export SIPE_EMAIL="seu-email"            # conta PRÓPRIA do integrante
export SIPE_SENHA="sua-senha"
export SIPE_TENTAR_LOGIN_INVALIDO=1      # permite UMA tentativa com senha errada (CT-LOG-03)

# Windows (PowerShell)
$env:SIPE_EMAIL="seu-email"; $env:SIPE_SENHA="sua-senha"; $env:SIPE_TENTAR_LOGIN_INVALIDO="1"
```

Para apontar para outro endereço: `SIPE_BASE_URL=http://localhost:8000/`.

Gerar os arquivos de valor limite do Resumo Simples: `mvn test-compile exec:java` (saída em `tools/saida/`).

## 4. O que cada execução gera

- `evidencias/CT-NAV-03_Menu_Edital_chrome_OK.png` (ou `_FALHA.png`): print automático de cada teste.
- `relatorios/tabela-resultados.md`: tabela pronta para colar em [04-relatorio-execucao.md](04-relatorio-execucao.md).
- `target/surefire-reports/`: relatório bruto do Maven (não vai para o Git).

## 5. Falhas esperadas (e o que elas significam)

Pela análise do HTML da home, **CT-ACE-02, CT-ACE-04 e CT-HTM-01 devem falhar**, porque o banner não tem `alt`, há links só com ícone
e há IDs repetidos. Nesses casos a falha **é o resultado do teste**: o site tem o defeito. Reproduzam no navegador, tirem print
e abram a Issue. Já uma falha por "elemento não encontrado" costuma indicar que o seletor do teste precisa de ajuste.

## 5b. Quais casos são automáticos

| Módulo | Automatizados | Continuam manuais |
|---|---|---|
| NAV | 01, 02, 03, 04, 05, 06, 09 | 07, 08 |
| DWN | 01, 02, 03 | 04, 05 (abrir os arquivos no Word/PowerPoint) |
| CNT | 01, 02, 04, 07, 08 | 03, 05, 06 (comparar textos dos documentos) |
| LOG | 01, 02*, 03*, 05, 06, 07 | 04, 08, 09, 10 |
| RSP | 01 (360, 768 e 1280 px), 02 | 03, 04 |
| CMP | mesmos testes com `-Dbrowser=` | conferência visual |
| ACE / HTM / SEG / DES | ACE-01, ACE-02, ACE-04, HTM-01, SEG-01, DES-01 | ACE-03 (Lighthouse), HTM-02 (validador W3C) |
| SUB | nenhum (afeta o sistema real) | todos |

\* só rodam com variáveis de ambiente definidas.

## 6. Segurança e ética

- Não há teste de carga nem de invasão. Os testes só fazem o que um usuário comum faria.
- O login inválido é **uma única tentativa** e só com `SIPE_TENTAR_LOGIN_INVALIDO=1`.
- A pipeline do GitHub Actions (`.github/workflows/testes.yml`) roda **apenas manualmente** (aba *Actions > Run workflow*),
  para não bombardear o site a cada commit.

## 7. Commits sugeridos para a etapa de código

```bash
git checkout -b etapa-5-automacao
git add pom.xml .gitignore src/test/java/sipe/SipeConfig.java src/test/java/sipe/Http.java \
        src/test/java/sipe/DriverFactory.java src/test/java/sipe/BaseTest.java src/test/java/sipe/EvidenciaExtension.java
git commit -m "test: configura projeto Maven, JUnit 5 e Selenium"

git add src/test/java/sipe/NavegacaoTest.java
git commit -m "test: automatiza casos de navegacao (CT-NAV)"

git add src/test/java/sipe/DownloadsTest.java src/test/java/sipe/ConteudoTest.java
git commit -m "test: automatiza downloads e consistencia de conteudo"

git add src/test/java/sipe/LoginTest.java
git commit -m "test: automatiza casos de login (sem credenciais no codigo)"

git add src/test/java/sipe/QualidadeTest.java
git commit -m "test: automatiza responsividade, acessibilidade, https e desempenho"

git add src/test/java/sipe/TabelaResultadosListener.java src/test/java/sipe/GeradorDocxLimites.java \
        src/test/resources .github/workflows/testes.yml docs/06-automacao.md
git commit -m "chore: tabela de resultados automatica, gerador de docx e pipeline manual"

# depois de rodar mvn test:
git add evidencias docs/04-relatorio-execucao.md
git commit -m "test: registra primeira execucao automatizada"
```

## 8. Ciclo de trabalho de cada integrante

1. `mvn test -Dtest=<Classe>` e olhar o resultado.
2. Falhou? Abrir o site e **reproduzir à mão**.
   - Site correto, teste errado → corrigir o teste (commit `test:`).
   - Site com defeito → abrir **Issue** com o print de `evidencias/` (`..._FALHA.png`).
3. Copiar a tabela de `relatorios/tabela-resultados.md` para o relatório e atualizar o `Status` em `03-casos-de-teste.md`.
4. Commit e push.
