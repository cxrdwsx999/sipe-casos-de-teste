# 05 — Guia Git: sequência de commits

Um commit por etapa, com mensagens no padrão `tipo: descrição`.

```bash
git init
git add README.md .github evidencias
git commit -m "chore: cria repositório, README e template de issue"

git checkout -b etapa-1-requisitos
git add docs/01-requisitos.md
git commit -m "docs: adiciona requisitos extraídos do site e do edital"

git checkout main && git merge etapa-1-requisitos
git checkout -b etapa-2-plano
git add docs/02-plano-de-testes.md
git commit -m "docs: adiciona plano de testes"

git checkout main && git merge etapa-2-plano
git checkout -b etapa-3-casos
git add docs/03-casos-de-teste.md tools
git commit -m "docs: adiciona casos de teste de navegação, downloads e login"
# depois, um commit por módulo adicionado ou ajustado:
git commit -am "docs: adiciona casos de valor limite da submissão"

git checkout main && git merge etapa-3-casos
git checkout -b etapa-4-execucao
git add evidencias docs/04-relatorio-execucao.md
git commit -m "test: registra execução e evidências"
```

Para cada bug: abrir uma **Issue** com o template e depois citar no commit (`docs: registra bug #3 no relatório`).
Uma boa prática é cada integrante fazer commits próprios, para o histórico mostrar a divisão do trabalho.

Publicar no GitHub:
```bash
git remote add origin https://github.com/USUARIO/NOME-DO-REPOSITORIO.git
git push -u origin main --all
```
