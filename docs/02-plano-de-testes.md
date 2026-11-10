# 02 — Plano de Testes

## 1. Objetivo
Verificar se o site do XIV SIPE FADERGS 2026 atende aos requisitos de [01-requisitos.md](01-requisitos.md),
identificando defeitos de funcionalidade, conteúdo, usabilidade e compatibilidade.

## 2. Escopo
**Dentro:** navegação, downloads de modelos, consistência de conteúdo (site × edital), login,
regras de submissão (limites do edital), responsividade, compatibilidade de navegadores, acessibilidade básica.

**Modalidade testada:** Resumo simples. Os limites do Resumo expandido (páginas, 250 palavras) estão fora do escopo.

**Fora:** teste de carga e de segurança ofensiva (invasão, injeção), conteúdo científico dos trabalhos,
infraestrutura do servidor, avaliação e premiação (processos internos da organização).

## 3. Técnicas de teste
| Técnica | Uso |
|---|---|
| Partição de equivalência | Tipos de arquivo, categorias, modalidades |
| Análise de valor limite | 349/350/500/501 palavras; 0/1/5/6 palavras-chave; 6/7 autores |
| Tabela de decisão | Categoria × modalidade × dentro/fora do prazo |
| Teste exploratório | Pontos que o edital não especifica |
| Teste de consistência | Comparação entre as páginas do site e o edital |

## 4. Ambiente
| Item | Valor |
|---|---|
| Sistema | https://eventosanima.com.br/fadergs/sipe/ |
| Navegadores | Chrome, Firefox, Edge (versões a registrar na execução) |
| Dispositivos | Desktop, tablet (emulado), celular (emulado e real) |
| Data de execução | _(preencher)_ |

### Ferramentas
| Ferramenta | Uso |
|---|---|
| Java 17 + Maven + JUnit 5 | Execução e organização dos testes automatizados |
| Selenium WebDriver | Automação do navegador (Chrome, Firefox, Edge) |
| DevTools / Lighthouse | Responsividade, desempenho e acessibilidade (manual) |
| GitHub Issues e Actions | Registro de bugs e execução manual da suíte |

Divisão entre testes automáticos e manuais: veja [06-automacao.md](06-automacao.md).

## 5. Critérios
- **Entrada:** edital disponível, site acessível, integrantes com conta de teste ou conta própria.
- **Aprovação do caso:** resultado obtido igual ao esperado.
- **Saída:** todos os casos de prioridade Alta executados e bugs registrados como Issues.

## 6. Status possíveis
`Aprovado` · `Reprovado` · `Bloqueado` (não executável sem afetar o sistema real) · `Não executado` · `Fora do escopo`

## 7. Riscos
- O sistema está em produção e com inscrições abertas: **não** criar submissões falsas.
- O prazo de submissão foi prorrogado de 09/11 para **15/11/2026** (domingo). Após o encerramento alguns comportamentos mudam (CT-SUB-18). As demais datas do cronograma (avaliação, divulgação, envio do PDF) podem ter mudado: conferir na fonte oficial.
- Instabilidade do servidor pode gerar falsos negativos: repetir o teste antes de reportar o bug.

## 8. Severidade dos bugs
| Nível | Exemplo |
|---|---|
| Crítica | Impede login ou submissão |
| Alta | Regra do edital não validada |
| Média | Inconsistência de informação |
| Baixa | Erro visual ou de texto |
