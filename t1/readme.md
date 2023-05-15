# Instruções para rodar o projeto
## Baixar sqlite-jdbc
- A versão usada no projeto é a 3.41.2.1 portanto essa é a que deve ser baixada
- Para usar outras versões de jdbc é necessário alterar o nome da versão no arquivo run.sh
- O projeto foi feito para a versão 3.41.2.1, não é garantido que funcionará usando outras versões
- O arquivo jdbc deve ficar posicionado na raíz do projeto, fora da pasta src, se não será necessário alterar o script run.sh
- O link para baixar o arquivo jdbc.jar pode ser encontrado no repositório: https://github.com/xerial/sqlite-jdbc
## Scripts de execução
### run.sh
- Script para compilar e executar o projeto, pode ser executado com o comando ```bash run.sh```
### populateBooks.sh
- Script auxiliar que popula o banco de dados com livros de exemplo para que seja mais fácil executar as relações,
pode ser executado usando ```bash populateBooks.sh```
