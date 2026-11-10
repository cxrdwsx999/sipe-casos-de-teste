# Casos de Teste — Site do XIV SIPE FADERGS 2026

Trabalho semestral da disciplina de **Teste de Software**.
Objeto de teste: site oficial do **XIV Salão de Iniciação Científica, Pesquisa e Extensão (SIPE) da FADERGS**
https://eventosanima.com.br/fadergs/sipe/

- **Integrantes:** Gabriel Cardoso Possamai, Breno Paiva Pinter, Arthur Marques Sartori 
- **Professor(a):** Adalto Selau Sparremberger, Andre Flavio Treib 
- **Disciplina / Semestre:** 4° semestre

**Modalidade de submissão do grupo:** Resumo simples (350 a 500 palavras, até 5 palavras-chave, .docx).

## Como ler este repositório (passo a passo)

O histórico de commits conta a história do projeto. Cada documento é uma etapa:

| Etapa | Documento | O que contém |
|---|---|---|
| 1 | [docs/01-requisitos.md](docs/01-requisitos.md) | Requisitos extraídos do site e do edital |
| 2 | [docs/02-plano-de-testes.md](docs/02-plano-de-testes.md) | Escopo, estratégia, ambiente, critérios |
| 3 | [docs/03-casos-de-teste.md](docs/03-casos-de-teste.md) | Tabela completa de casos de teste |
| 4 | [docs/04-relatorio-execucao.md](docs/04-relatorio-execucao.md) | Resultados da execução e resumo |
| 5 | [docs/06-automacao.md](docs/06-automacao.md) + `src/test/` | Testes automatizados (Java, Maven, JUnit 5, Selenium) |
| 6 | Aba **Issues** do repositório | Um bug por Issue, com evidências |
| — | [docs/05-guia-git.md](docs/05-guia-git.md) | Sequência de commits e branches usada |

## Estrutura

```
docs/         documentação (etapas 1 a 4 e automação)
src/test/     testes automatizados em Java (JUnit 5 + Selenium)
evidencias/   prints de evidência (gerados pelos testes e manuais)
relatorios/  tabela de resultados gerada pelos testes (não versionada)
.github/      template de Issue e pipeline manual de testes
```

## Como rodar os testes automatizados

```bash
mvn test                  # requer JDK 17+, Maven e Google Chrome
mvn test -Dheadless=false # abre o navegador
```
Detalhes e variáveis de ambiente em [docs/06-automacao.md](docs/06-automacao.md).

## Aviso ético

Os testes foram feitos em um sistema real em período de inscrições. Não foram criadas inscrições
falsas nem foi feito teste de carga ou de segurança ofensivo. Casos que exigiriam registro real
estão marcados como **Bloqueado** quando não havia ambiente de teste autorizado.
