# 01 — Requisitos

Fontes: site (páginas Início, Edital, Local, Contatos) e Edital do XIV SIPE 2026 (PDF).

## Requisitos funcionais

| ID | Requisito | Fonte |
|---|---|---|
| RF01 | O site deve exibir Início, Edital, Local e Contatos | Site |
| RF02 | O site deve disponibilizar os modelos de Pôster (.pptx), Resumo Expandido (.docx) e Resumo Simples (.docx) | Site |
| RF03 | O site deve oferecer acesso à plataforma por login ("Entrar") | Site |
| RF04 | O autor deve escolher uma das 3 categorias: Pesquisa científica, Extensão universitária, Ensino e práticas pedagógicas | Edital §3 |
| RF05 | O autor deve escolher a modalidade: Resumo simples ou Resumo expandido | Edital §4 |
| RF06 | Resumo simples: 350 a 500 palavras, até 5 palavras-chave, arquivo .docx | Edital §4.1 |
| RF07 | _(fora do escopo do grupo)_ Resumo expandido: 4 a 6 páginas, resumo de até 250 palavras, até 5 palavras-chave, arquivo .docx | Edital §4.2 |
| RF08 | Cada trabalho aceita até 6 autores; orientador pode integrar a lista | Edital §5 |
| RF09 | A submissão só é aceita de 16/10/2026 até o encerramento. Edital (PDF) diz 09/11/2026; prazo prorrogado para **15/11/2026** segundo informação recebida pelo grupo (fonte a registrar) | Edital §13 + aviso de prorrogação |
| RF10 | É possível submeter mais de um trabalho | Edital §11 |
| RF11 | A comunicação de aprovação é feita pelo e-mail informado na inscrição | Edital §7 |
| RF12 | Trabalhos selecionados enviam a apresentação em PDF até 20/11/2026 | Edital §13 |
| RF13 | Certificados são disponibilizados na plataforma | Edital §11 |
| RF14 | A participação é gratuita (sem cobrança de taxa) | Edital §12 |

### Regras adicionais do modelo oficial de Resumo Simples (MODELO_RESUMO_SIMPLES_2026.docx)

| ID | Regra |
|---|---|
| RM01 | Título em maiúsculas, Times New Roman 12, negrito, centralizado, no máximo 20 palavras |
| RM02 | Linha de categoria: Projeto Científico / Projeto Aplicado / Projeto de Extensão |
| RM03 | Autores, orientadores e filiação em itálico; e-mail do autor principal |
| RM04 | Texto de 350 a 500 palavras, em terceira pessoa, com Introdução, Metodologia, Resultados e Discussão e Considerações finais |
| RM05 | Palavras-chave: de três a cinco, separadas por ponto e vírgula, na mesma linha |
| RM06 | Referências em ABNT, em ordem alfabética, apenas as citadas no texto |

## Requisitos não funcionais

| ID | Requisito |
|---|---|
| RNF01 | O site deve ser exibido corretamente em desktop, tablet e celular |
| RNF02 | O site deve funcionar em Chrome, Firefox e Edge |
| RNF03 | As páginas devem carregar em tempo aceitável (meta: até 3 s em conexão comum) |
| RNF04 | Informações do site e do edital devem ser consistentes entre si |
| RNF05 | O site deve ser utilizável por teclado e ter contraste e textos alternativos adequados |

## Pontos não especificados no edital (a verificar no sistema)

Tamanho máximo de arquivo, campos obrigatórios do cadastro, política de senha, possibilidade de editar ou
excluir uma submissão antes do prazo, e mensagens de erro. Os casos de teste correspondentes são
marcados como "exploratório".
