# 04 — Relatório de Execução

| Item | Valor |
|---|---|
| Data de execução | 27/11/2026 |
| Executado por | Gabriel Cardoso Possamai |
| Navegador | Google Chrome, modo sem janela (versão: 157.0.8103.79 ) |
| Sistema operacional | Windows |
| Forma de execução | Automatizada (Java 17+, Maven, JUnit 5, Selenium), no IntelliJ IDEA |
| Visão do site | Visitante (sem login) |

## Resumo da execução automatizada (Chrome)

| Status | Execuções |
|---|---|
| Aprovado | 25 |
| Reprovado | 3 |
| Ignorado | 3 |
| **Total** | **31** |

O CT-RSP-01 roda três vezes (360, 768 e 1280 px), por isso são 31 execuções para 29 casos de teste.
Os 3 ignorados dependem de variáveis de ambiente ou de uma condição do site (veja a tabela).

## Resultado por caso

| ID | Resultado obtido | Status | Evidência | Issue |
|---|---|---|---|---|
| CT-ACE-01 | Teclado alcança os elementos da página | Aprovado | evidencias/CT-ACE-01_* | |
| CT-ACE-02 | Imagem sem atributo `alt`: banner_xiv_sipe.png | **Reprovado** | evidencias/CT-ACE-02_*_FALHA.png | #1 |
| CT-ACE-04 | Links sem texto, aria-label ou title: redes sociais (Twitter, Facebook, LinkedIn, Instagram) e botão "voltar ao topo" | **Reprovado** | evidencias/CT-ACE-04_*_FALHA.png | #2 |
| CT-CNT-01 | Numeração "XIV" consistente | Aprovado | evidencias/CT-CNT-01_* | |
| CT-CNT-02 | Datas 23 a 27 de novembro presentes | Aprovado | evidencias/CT-CNT-02_* | |
| CT-CNT-04 | Edital acessível (texto ou PDF) | Aprovado | evidencias/CT-CNT-04_* | |
| CT-CNT-07 | Nenhuma das datas (09/11 e 15/11) aparece no texto da home e do edital; teste ignorado | Ignorado | — | |
| CT-CNT-08 | PDF "Saiba mais" abre e é um PDF válido | Aprovado | evidencias/CT-CNT-08_* | |
| CT-DES-01 | Carregamento abaixo de 3 s | Aprovado | evidencias/CT-DES-01_* | |
| CT-DWN-01 | Modelo de Resumo Simples baixa e é .docx válido | Aprovado | evidencias/CT-DWN-01_* | |
| CT-DWN-02 | Modelo de Resumo Expandido baixa e é .docx válido | Aprovado | evidencias/CT-DWN-02_* | |
| CT-DWN-03 | Modelo de Pôster baixa e é .pptx válido | Aprovado | evidencias/CT-DWN-03_* | |
| CT-HTM-01 | IDs repetidos: `navbarDropdown` e `sobre` | **Reprovado** | evidencias/CT-HTM-01_*_FALHA.png | #3 |
| CT-LOG-01 | Tela de login abre, com campo de senha | Aprovado | evidencias/CT-LOG-01_* | |
| CT-LOG-02 | Variável SIPE_EMAIL não definida (login válido não executado) | Ignorado | — | |
| CT-LOG-03 | Variável SIPE_TENTAR_LOGIN_INVALIDO não definida | Ignorado | — | |
| CT-LOG-05 | Campos vazios são validados | Aprovado | evidencias/CT-LOG-05_* | |
| CT-LOG-06 | E-mail inválido não é aceito | Aprovado | evidencias/CT-LOG-06_* | |
| CT-LOG-07 | Senha mascarada (`type=password`) | Aprovado | evidencias/CT-LOG-07_* | |
| CT-NAV-01 | Página inicial carrega, com "XIV SIPE" | Aprovado | evidencias/CT-NAV-01_* | |
| CT-NAV-02 | Menu Início | Aprovado | evidencias/CT-NAV-02_* | |
| CT-NAV-03 | Menu Edital | Aprovado | evidencias/CT-NAV-03_* | |
| CT-NAV-04 | Menu Local | Aprovado | evidencias/CT-NAV-04_* | |
| CT-NAV-05 | Menu Contatos | Aprovado | evidencias/CT-NAV-05_* | |
| CT-NAV-06 | Edições anteriores 2022, 2023 e 2024 abrem | Aprovado | evidencias/CT-NAV-06_* | |
| CT-NAV-09 | Página inexistente retorna 404 sem erro técnico | Aprovado | evidencias/CT-NAV-09_* | |
| CT-RSP-01 (1280 px) | Sem rolagem horizontal | Aprovado | evidencias/CT-RSP-01_*1280px* | |
| CT-RSP-01 (360 px) | Sem rolagem horizontal | Aprovado | evidencias/CT-RSP-01_*360px* | |
| CT-RSP-01 (768 px) | Sem rolagem horizontal | Aprovado | evidencias/CT-RSP-01_*768px* | |
| CT-RSP-02 | Menu acessível em tela pequena | Aprovado | evidencias/CT-RSP-02_* | |
| CT-SEG-01 | HTTP redireciona para HTTPS | Aprovado | evidencias/CT-SEG-01_* | |

## Defeitos encontrados

| # | Defeito | Caso | Severidade | Issue |
|---|---|---|---|---|
| 1 | O banner principal da home não tem texto alternativo (`alt`) | CT-ACE-02 | Média | #1 |
| 2 | Links só com ícone (redes sociais e "voltar ao topo") não têm nome acessível | CT-ACE-04 | Média | #2 |
| 3 | IDs repetidos no HTML (`navbarDropdown` 2x e `sobre` 3x); o menu "Edições Anteriores" aponta, por `aria-labelledby`, para o botão do menu "Modelos" | CT-HTM-01 | Baixa | #3 |

## Casos ainda não executados (manuais)

| Grupo | Casos | Observação |
|---|---|---|
| Navegação | CT-NAV-07, CT-NAV-08 | Título volta ao início; links de redes sociais abrem os perfis corretos |
| Downloads | CT-DWN-04, CT-DWN-05 | Abrir os arquivos no Word/PowerPoint e conferir a edição 2026 |
| Consistência | CT-CNT-03, CT-CNT-05, CT-CNT-06 | Comparar categorias e palavras-chave entre edital e modelo; ortografia; links quebrados |
| Login | CT-LOG-04, 08, 09, 10 e, opcionalmente, 02 e 03 | CT-LOG-02 e 03 exigem conta própria |
| Responsividade | CT-RSP-03, CT-RSP-04 | Tablet e rotação do celular |
| Compatibilidade | CT-CMP-01, 02, 03 | Repetir a suíte em Firefox e Edge (`-Dbrowser=firefox` e `-Dbrowser=edge`) |
| Qualidade | CT-ACE-03, CT-HTM-02 | Lighthouse e validador W3C |
| Submissão | CT-SUB-01 a 32 | **Bloqueado**: o prazo de submissão do SIPE já encerrou e não foram criadas submissões de teste no sistema real |

## Pontos verificados na análise prévia

1. **Edição do evento:** confirmada a numeração "XIV" (CT-CNT-01 aprovado).
2. **Categorias:** a home segue o §3 do edital, mas o modelo de Resumo Simples e o §4.1 usam outros nomes. Falta registrar manualmente (CT-CNT-03).
3. **Prazo:** o teste automático foi ignorado, porque as datas não aparecem no texto da home. Verificar manualmente no PDF do edital (CT-CNT-07).
4. **Página do edital:** acessível (CT-CNT-04 aprovado).
5. **Tela de login:** o botão "Entrar" leva à tela de login, com campo de senha mascarado e validação de campos (CT-LOG-01, 05, 06 e 07 aprovados).

## Conclusão

Na execução automatizada em Chrome, 25 de 31 execuções foram aprovadas, 3 foram reprovadas e 3 foram ignoradas por dependerem de
credenciais ou de uma condição do site. As três reprovações são defeitos reais de acessibilidade e de marcação HTML,
todos corrigíveis sem alterar a regra de negócio. Navegação, downloads dos modelos, consistência de edição e datas, validação do
login, responsividade, HTTPS e tempo de carregamento atenderam ao esperado. Restam os testes manuais e a repetição da suíte em
Firefox e Edge. Os casos de submissão ficaram bloqueados, por se tratar de um sistema real e com prazo encerrado.
