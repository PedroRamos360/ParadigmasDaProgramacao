# Detalhes sobre o projeto

## Baixar sqlite-jdbc

- A versão usada no projeto é a 3.42.0.0 portanto essa é a que deve ser baixada
- Para usar outras versões de jdbc é necessário alterar o nome da versão no arquivo run.sh
- O projeto foi feito para a versão 3.42.0.0, não é garantido que funcionará usando outras versões
- O arquivo jdbc deve ficar posicionado na raíz do projeto, fora da pasta src, se não será necessário alterar o script run.sh
- O link para baixar o arquivo jdbc.jar pode ser encontrado [neste repositório.](https://github.com/xerial/sqlite-jdbc)

## Scripts de execução

### run.sh

- Script para compilar e executar o projeto, pode ser executado com o comando ```bash run.sh```

## Extração dos valores dos ativos

Para a extração dos valores dos ativos atualizados é feito por requisições get para a API gratuita da brapi dev portanto é importante lembrar que é necessário estar conectado na internet para rodar o projeto
