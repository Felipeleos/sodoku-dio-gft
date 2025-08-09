@echo off
if "%~1"=="" (
  echo Uso: run.bat "arg-string"
  goto :eof
)
mvn compile exec:java -Dexec.mainClass="com.sudoku.Main" -Dexec.args="%~1"
