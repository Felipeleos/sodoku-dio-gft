#!/usr/bin/env bash
if [ -z "$1" ]; then
  echo "Uso: ./run.sh "<arg-string>""
  echo "Exemplo: ./run.sh "0,0;4,false 1,0;7,false 2,0;9,true ...""
  exit 1
fi
mvn compile exec:java -Dexec.mainClass="com.sudoku.Main" -Dexec.args="$1"
