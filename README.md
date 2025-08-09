# Projeto Sudoku - CLI (Java + Maven)

Projeto pronto para ser enviado ao GitHub. Implementação simples de um jogo/estado de Sudoku em Java com interface por terminal (CLI).

## Estrutura
- `src/main/java/com/sudoku` — código-fonte
- `pom.xml` — build Maven
- `run.sh` / `run.bat` — scripts de execução
- `README.md` — este arquivo

## Como rodar
1. Com Maven instalado:
```bash
# compila
mvn compile

# roda passando a string de células como único argumento (entre aspas)
mvn exec:java -Dexec.mainClass="com.sudoku.Main" -Dexec.args="0,0;4,false 1,0;7,false 2,0;9,true ..."
```

2. Usando o script `run.sh` (Linux/macOS):
```bash
chmod +x run.sh
./run.sh "0,0;4,false 1,0;7,false 2,0;9,true ..."
```

3. Windows:
```cmd
run.bat "0,0;4,false 1,0;7,false 2,0;9,true ..."
```

### Formato do argumento
Cada célula é um token com o formato:
```
col,row;value,isFixed
```
Exemplo:
```
0,0;4,false
```
Significa: coluna 0, linha 0, valor 4, não fixa.

> Observação: caso o seu conjunto de argumentos use `row,col` em vez de `col,row`, basta inverter os valores ao montar a string ou me avisar que eu ajusto o parser.

### Funcionalidades
- Constrói o tabuleiro 9x9 a partir do argumento.
- Exibe o tabuleiro no terminal com células fixas indicadas.
- Modo interativo simples: insira comandos `set r c v` para definir um valor (se a célula não for fixa), `check` para validar regras (linhas/colunas/regionais), `print` para reimprimir, e `exit` para sair.
- Testes unitários básicos.

### Entrada de exemplo (use a string completa do enunciado)
```
0,0;4,false 1,0;7,false 2,0;9,true 3,0;5,false ...
```

Boa sorte! Se quiser, posso:
- Ajustar o parser para `row,col` automaticamente.
- Implementar resolução (solver) ou salvar/ carregar jogos.
- Fazer branch com interface gráfica (JavaFX / Swing).
