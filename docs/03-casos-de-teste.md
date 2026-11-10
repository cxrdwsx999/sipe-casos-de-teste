# 03 — Casos de Teste

Modalidade escolhida pelo grupo: **Resumo simples** (350 a 500 palavras, até 5 palavras-chave, .docx, apresentação oral de 5 minutos).
Os casos do Resumo expandido (CT-SUB-09 a 13) ficam como `Fora do escopo`.

Legenda de status: `Não executado` · `Aprovado` · `Reprovado` · `Bloqueado` · `Fora do escopo`.
Prioridade: A (alta) · M (média) · B (baixa). As colunas "Resultado obtido" e "Status" são preenchidas na execução
(veja [04-relatorio-execucao.md](04-relatorio-execucao.md)).

## Navegação (RF01)

| ID | Req. | Objetivo | Passos | Resultado esperado | Prio. | Status |
|---|---|---|---|---|---|---|
| CT-NAV-01 | RF01 | Abrir a página inicial | Acessar a URL do SIPE | Página carrega com banner, título "XIV SIPE FADERGS 2026" e menu | A | Não executado |
| CT-NAV-02 | RF01 | Menu: Início | Clicar em "Início" | Retorna à página inicial | A | Não executado |
| CT-NAV-03 | RF01 | Menu: Edital | Clicar em "Edital" | Abre o edital com o conteúdo legível | A | Não executado |
| CT-NAV-04 | RF01 | Menu: Local | Clicar em "Local" | Exibe informações do local (FADERGS, Porto Alegre) | M | Não executado |
| CT-NAV-05 | RF01 | Menu: Contatos | Clicar em "Contatos" | Exibe meios de contato válidos | M | Não executado |
| CT-NAV-06 | RF01 | Edições anteriores | Abrir SIPE 2022, 2023 e 2024 | Cada página abre sem erro | M | Não executado |
| CT-NAV-07 | RF01 | Título retorna ao início | Em qualquer página, clicar no título do evento | Volta à página inicial | B | Não executado |
| CT-NAV-08 | RF01 | Links de redes sociais | Clicar nos ícones do topo | Abrem os perfis corretos (nova aba) | B | Não executado |
| CT-NAV-09 | RF01 | Página inexistente | Acessar `/fadergs/sipe/xyz.php` | Mensagem de erro amigável (404), sem exibir erro técnico do servidor | M | Não executado |

## Downloads (RF02)

| ID | Req. | Objetivo | Passos | Resultado esperado | Prio. | Status |
|---|---|---|---|---|---|---|
| CT-DWN-01 | RF02 | Baixar modelo de Resumo Simples | Menu Modelos > Resumo Simples | Baixa .docx que abre no Word | A | Não executado |
| CT-DWN-02 | RF02 | Baixar modelo de Resumo Expandido | Menu Modelos > Resumo Expandido | Baixa .docx que abre no Word | A | Não executado |
| CT-DWN-03 | RF02 | Baixar modelo de Pôster | Menu Modelos > Pôster | Baixa .pptx que abre no PowerPoint | A | Não executado |
| CT-DWN-04 | RF02 | Conteúdo dos modelos é de 2026 | Abrir os 3 arquivos | Mostram XIV SIPE / 2026, sem referência a edição anterior | M | Não executado |
| CT-DWN-05 | RF02 | Botões "Baixar o modelo" da página inicial | Clicar nos 3 botões da seção Modelos | Mesmo arquivo do menu | B | Não executado |

## Consistência de conteúdo (RNF04)

| ID | Req. | Objetivo | Passos | Resultado esperado | Prio. | Status |
|---|---|---|---|---|---|---|
| CT-CNT-01 | RNF04 | Numeração da edição | Comparar site, edital e Anais | Todos usam "XIV" | M | Não executado |
| CT-CNT-02 | RNF04 | Datas do evento | Comparar site, edital e página Local | 23 a 27/11/2026 em todos | A | Não executado |
| CT-CNT-03 | RNF04 | Categorias: edital §3 × edital §4.1 × modelo de resumo simples | Comparar os três textos | Mesmos nomes de categoria nos três | M | Não executado |
| CT-CNT-04 | RNF04 | Edital acessível | Abrir `edital.php` | Texto do edital ou PDF visível e baixável | A | Não executado |
| CT-CNT-05 | RNF04 | Ortografia e links quebrados | Revisar todas as páginas | Sem erros de texto nem links quebrados | B | Não executado |
| CT-CNT-06 | RNF04 | Palavras-chave: edital × modelo | Comparar edital §4.1 ("até cinco") e modelo ("de três a cinco") | Mesma regra nos dois | M | Não executado |
| CT-CNT-07 | RNF04 | Prazo de encerramento: edital × site × comunicado | Comparar §13 do edital (09/11), site, e-mail ou comunicado de prorrogação (15/11) | Mesma data em todos; edital atualizado | A | Não executado |
| CT-CNT-08 | RNF04 | PDF "Saiba mais" da home abre | Rolar a home até "SAIBA MAIS" e abrir o PDF incorporado | PDF carrega e é legível | M | Não executado |

## Login (RF03)

| ID | Req. | Objetivo | Passos | Dados | Resultado esperado | Prio. | Status |
|---|---|---|---|---|---|---|---|
| CT-LOG-01 | RF03 | Abrir a tela de login | Clicar em "Entrar" | — | Tela de login carrega sem erro | A | Não executado |
| CT-LOG-02 | RF03 | Login válido | Preencher e enviar | Conta própria do testador | Acessa a área logada | A | Não executado |
| CT-LOG-03 | RF03 | Senha incorreta | Preencher e enviar | Usuário válido + senha errada | Mensagem de erro; não entra | A | Não executado |
| CT-LOG-04 | RF03 | Usuário inexistente | Preencher e enviar | E-mail não cadastrado | Mensagem de erro genérica; não entra | M | Não executado |
| CT-LOG-05 | RF03 | Campos vazios | Enviar sem preencher | vazio | Validação de campo obrigatório | A | Não executado |
| CT-LOG-06 | RF03 | E-mail em formato inválido | Preencher e enviar | `abc`, `abc@`, `@x.com` | Validação de formato | M | Não executado |
| CT-LOG-07 | RF03 | Senha mascarada | Digitar a senha | qualquer | Caracteres ocultos | M | Não executado |
| CT-LOG-08 | RF03 | Recuperar senha (exploratório) | Procurar a opção | — | Existe fluxo de recuperação | M | Não executado |
| CT-LOG-09 | RF03 | Sair da conta (exploratório) | Clicar em sair, depois no botão voltar do navegador | — | Sessão encerrada; área logada não reabre | M | Não executado |
| CT-LOG-10 | RF03 | Páginas internas sem login | Acessar URL interna sem estar logado | — | Redireciona para o login | M | Não executado |

## Submissão (RF04 a RF10)

> Casos que enviam arquivo ao sistema só devem ser executados com o **trabalho real** do grupo ou em
> ambiente de teste autorizado. Caso contrário, marcar como `Bloqueado`.
> A classe `GeradorDocxLimites` (`mvn test-compile exec:java`) gera os arquivos de valor limite em `tools/saida/`.

| ID | Req. | Técnica | Objetivo | Dados | Resultado esperado | Prio. | Status |
|---|---|---|---|---|---|---|---|
| CT-SUB-01 | RF04 | Partição | Categorias disponíveis | — | Exatamente 3: Pesquisa científica, Extensão universitária, Ensino e práticas pedagógicas | A | Não executado |
| CT-SUB-02 | RF05 | Partição | Modalidades disponíveis | — | Exatamente 2: Resumo simples e Resumo expandido | A | Não executado |
| CT-SUB-03 | RF06 | Valor limite | Resumo simples com 349 palavras | .docx com 349 palavras | Rejeitado ou alertado | A | Não executado |
| CT-SUB-04 | RF06 | Valor limite | Resumo simples com 350 palavras | .docx com 350 palavras | Aceito | A | Não executado |
| CT-SUB-05 | RF06 | Valor limite | Resumo simples com 500 palavras | .docx com 500 palavras | Aceito | A | Não executado |
| CT-SUB-06 | RF06 | Valor limite | Resumo simples com 501 palavras | .docx com 501 palavras | Rejeitado ou alertado | A | Não executado |
| CT-SUB-07 | RF06 | Valor limite | 5 palavras-chave | 5 palavras | Aceito | A | Não executado |
| CT-SUB-08 | RF06 | Valor limite | 6 palavras-chave | 6 palavras | Rejeitado ou alertado | A | Não executado |
| CT-SUB-09 | RF07 | Valor limite | Expandido com 3 páginas | .docx de 3 páginas | Rejeitado ou alertado | A | Fora do escopo |
| CT-SUB-10 | RF07 | Valor limite | Expandido com 4 páginas | .docx de 4 páginas | Aceito | A | Fora do escopo |
| CT-SUB-11 | RF07 | Valor limite | Expandido com 6 páginas | .docx de 6 páginas | Aceito | A | Fora do escopo |
| CT-SUB-12 | RF07 | Valor limite | Expandido com 7 páginas | .docx de 7 páginas | Rejeitado ou alertado | A | Fora do escopo |
| CT-SUB-13 | RF07 | Valor limite | Resumo com 250 e 251 palavras | 250 / 251 palavras | 250 aceito; 251 rejeitado ou alertado | M | Fora do escopo |
| CT-SUB-14 | RF08 | Valor limite | 6 autores / 7 autores | 6 / 7 nomes | 6 aceito; 7 bloqueado | A | Não executado |
| CT-SUB-15 | RF06/07 | Partição | Arquivo que não é .docx | .pdf, .txt, .jpg | Rejeitado com mensagem clara | A | Não executado |
| CT-SUB-16 | RF06/07 | Exploratório | Arquivo vazio e arquivo muito grande | 0 KB; > 20 MB | Rejeitado com mensagem clara | M | Não executado |
| CT-SUB-17 | RF04/05 | Tab. decisão | Enviar sem categoria ou sem modalidade | campos vazios | Validação de campo obrigatório | A | Não executado |
| CT-SUB-18 | RF09 | Tab. decisão | Submissão após o encerramento (15/11/2026, prorrogado) | executar somente após o prazo | Envio bloqueado com aviso de prazo encerrado | A | Não executado |
| CT-SUB-19 | RF09 | Tab. decisão | Submissão antes de 16/10/2026 | (não executável hoje) | Envio bloqueado | B | Bloqueado |
| CT-SUB-20 | RF10 | Partição | Enviar dois trabalhos pelo mesmo autor | 2 trabalhos reais | Ambos registrados | M | Não executado |
| CT-SUB-21 | RF11 | Exploratório | E-mail de confirmação | submissão real | Recebe confirmação no e-mail informado | M | Não executado |
| CT-SUB-22 | — | Exploratório | Editar/excluir submissão antes do prazo | submissão real | Edital não prevê; registrar comportamento | M | Não executado |
| CT-SUB-23 | RF14 | Partição | Ausência de cobrança | fluxo completo | Nenhuma tela pede pagamento | B | Não executado |
| CT-SUB-24 | RF06 | Partição | Usar o modelo oficial de Resumo Simples | Baixar o modelo (CT-DWN-01), preencher e enviar | Aceito sem pedir adequação de formatação | A | Não executado |
| CT-SUB-25 | RF06 | Partição | Resumo simples enviado na categoria correta | Escolher cada uma das 3 categorias em submissões reais do grupo | Sistema aceita a modalidade em todas as categorias do §3 (ver inconsistência com §4.1, CT-CNT-03) | A | Não executado |
| CT-SUB-26 | RF06 | Valor limite | Palavras-chave: 2, 3, 5 e 6 (modelo diz "de três a cinco"; edital diz "até cinco") | 2 / 3 / 5 / 6 palavras | 2 alerta ou aceito (registrar); 3 e 5 aceitos; 6 rejeitado ou alertado | A | Não executado |
| CT-SUB-27 | RF06 | Exploratório | Contagem de palavras: com ou sem título, referências e palavras-chave | Texto de 350 palavras + título e palavras-chave | Registrar como o sistema conta (edital não especifica) | M | Não executado |
| CT-SUB-28 | RF12 | Exploratório | Envio da apresentação em PDF (só após aprovação) | PDF do pôster/slides | Aceita PDF; rejeita outros formatos | M | Bloqueado |
| CT-SUB-29 | RF06 | Valor limite | Título com 20 e 21 palavras (modelo: máx. 20) | título de 20 / 21 palavras | 20 aceito; 21 rejeitado ou alertado | M | Não executado |
| CT-SUB-30 | RF06 | Partição | Campo de categoria usa os nomes do modelo | Abrir a tela de submissão | Opções coerentes com o modelo (Projeto Científico/Aplicado/Extensão) ou com o edital §3; registrar qual | A | Não executado |
| CT-SUB-31 | RF06 | Exploratório | Resumo sem as seções do modelo (Introdução, Metodologia, Resultados e Discussão, Considerações finais) | .docx de 350 palavras sem seções | Sistema aceita (validação é humana) ou alerta; registrar | B | Não executado |
| CT-SUB-32 | RF06 | Exploratório | Modelo enviado com as instruções em vermelho/negrito não removidas | modelo sem editar | Sistema aceita (validação é humana) ou alerta; registrar | B | Não executado |

## Responsividade, compatibilidade e outros (RNF01 a RNF05)

| ID | Req. | Objetivo | Passos | Resultado esperado | Prio. | Status |
|---|---|---|---|---|---|---|
| CT-RSP-01 | RNF01 | Layout em celular (360 px) | Abrir o site no modo responsivo | Sem rolagem horizontal; texto legível | A | Não executado |
| CT-RSP-02 | RNF01 | Menu em tela pequena | Abrir menu e submenus (Modelos, Edições) | Menu abre e todos os itens são clicáveis | A | Não executado |
| CT-RSP-03 | RNF01 | Layout em tablet (768 px) | Abrir no modo responsivo | Layout organizado | M | Não executado |
| CT-RSP-04 | RNF01 | Rotação do celular | Girar para paisagem | Conteúdo se adapta | B | Não executado |
| CT-CMP-01 | RNF02 | Chrome | Executar CT-NAV-01 a 08 | Mesmo resultado esperado | A | Não executado |
| CT-CMP-02 | RNF02 | Firefox | Executar CT-NAV-01 a 08 | Mesmo resultado esperado | A | Não executado |
| CT-CMP-03 | RNF02 | Edge | Executar CT-NAV-01 a 08 | Mesmo resultado esperado | M | Não executado |
| CT-DES-01 | RNF03 | Tempo de carregamento | Medir no DevTools (aba Network) ou Lighthouse | Página inicial carrega em até 3 s | M | Não executado |
| CT-ACE-01 | RNF05 | Navegação por teclado | Percorrer o menu com Tab e Enter | Todos os itens alcançáveis, foco visível | M | Não executado |
| CT-ACE-02 | RNF05 | Texto alternativo no banner | Inspecionar a imagem do banner | Atributo `alt` descritivo presente | M | Não executado |
| CT-ACE-03 | RNF05 | Contraste de cores | Rodar Lighthouse (Accessibility) | Sem falhas de contraste graves | B | Não executado |
| CT-ACE-04 | RNF05 | Links só com ícone têm nome acessível | Inspecionar ícones de redes sociais e botão "voltar ao topo" | Cada link tem texto, aria-label ou title | M | Não executado |
| CT-HTM-01 | RNF05 | IDs únicos no HTML | Consultar ids repetidos no console ou rodar o teste automatizado | Nenhum id repetido (a análise do HTML apontou `navbarDropdown` e `sobre`) | B | Não executado |
| CT-HTM-02 | RNF05 | HTML válido | Validar a home em validator.w3.org | Sem erros de marcação (a análise apontou `<a>` sem fechamento ao lado dos botões de modelo) | B | Não executado |
| CT-SEG-01 | — | Uso de HTTPS | Verificar o cadeado e tentar `http://` | Conexão segura; http redireciona para https | M | Não executado |
