# 04 — Relatório de Execução

| Item | Valor |
|---|---|
| Data de execução | _(preencher)_ |
| Executado por | _(preencher)_ |
| Navegador(es) e versão | _(preencher)_ |
| Sistema operacional | _(preencher)_ |

## Resumo

| Status | Quantidade |
|---|---|
| Aprovado | |
| Reprovado | |
| Bloqueado | |
| Não executado | |
| Fora do escopo | |
| **Total** | |

## Resultado por caso

Preencher uma linha por caso executado. Evidência: arquivo em `evidencias/` com o nome do caso (ex.: `CT-NAV-03.png`).

| ID | Resultado obtido | Status | Evidência | Issue |
|---|---|---|---|---|
| CT-NAV-01 | | | | |

## Pontos a confirmar na execução (observados na análise prévia)

Estas observações vieram da leitura do site e do edital e **precisam ser confirmadas** no navegador antes de virarem bug:

1. **Edição do evento:** o site e o edital dizem "XIV" (14ª edição). Confirmar que nenhuma página usa outra numeração (CT-CNT-01).
2. **Categorias:** o §3 do edital lista Pesquisa científica, Extensão universitária e Ensino e práticas pedagógicas, mas o §4.1 e o modelo de Resumo Simples citam "Projeto Científico, Projeto Aplicado e Projeto de Extensão" (CT-CNT-03, CT-SUB-30). Inconsistência confirmada na leitura dos documentos; falta ver qual nomenclatura a plataforma usa.
2b. **Palavras-chave:** o edital diz "até cinco" e o modelo diz "de três a cinco" (CT-CNT-06, CT-SUB-26).
3. **Prazo:** o edital em PDF (§13) informa encerramento em 09/11/2026, mas o grupo foi informado de prorrogação para 15/11/2026 (CT-CNT-07). Se o PDF/site não foram atualizados, registrar como inconsistência. Anotar onde o aviso foi visto (site, e-mail, professor) e anexar print.
3b. **Página do Edital:** na leitura automatizada, `edital.php` não exibiu o texto do edital (CT-CNT-04). Confirmar no navegador.
4. **Tela de login:** o caminho real da página de login ainda não foi confirmado (a análise prévia tentou um endereço presumido, `login.php`, que retornou erro e **não** serve como evidência de defeito). Descobrir pelo botão "Entrar" e testar (CT-LOG-01).

## Observações confirmadas pela análise do HTML da home (capturada com usuário logado)

Evidência: o código-fonte da página (Ctrl+U). Ainda devem ser reproduzidas no navegador, com print, antes de virarem Issue.

| # | Observação | Caso | Severidade sugerida |
|---|---|---|---|
| 1 | O banner principal (`banner_xiv_sipe.png`) não tem atributo `alt` | CT-ACE-02 | Média |
| 2 | Links só com ícone (redes sociais e "voltar ao topo") não têm texto nem `aria-label` | CT-ACE-04 | Média |
| 3 | IDs repetidos: `navbarDropdown` (2 vezes) e `sobre` (3 vezes) | CT-HTM-01 | Baixa |
| 4 | Marcação inválida: tag `<a>` sem fechamento ao lado dos botões "Baixar o modelo", e botão dentro de link | CT-HTM-02 | Baixa |
| 5 | Biblioteca jQuery 3.4.1, anterior à 3.5.0 (que corrigiu falhas de XSS conhecidas) | observação | Baixa |
| 6 | Categorias: a home segue o §3 do edital (Pesquisa Científica, Extensão universitária, Ensino e práticas pedagógicas), mas o modelo de Resumo Simples usa Projeto Científico/Aplicado/Extensão | CT-CNT-03 | Média |
| 7 | A home não exibe aviso de prorrogação de prazo. No HTML há um aviso antigo (de 2025) comentado | CT-CNT-07 | Média |
| 8 | Itens de menu e links de modelos conferem com o esperado (Início, Edital, Local, Contatos, Trabalhos, Modelos, Edições Anteriores 2022 a 2024) | CT-NAV, CT-DWN | — |

## Conclusão

_(preencher: percentual de aprovação, bugs mais graves, recomendações à organização)_
