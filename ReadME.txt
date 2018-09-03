 Passo a passo de execução do projeto

1 - Pré requisitos
    - Java 8
    - Servidor MySQL rodando na porta 3306 e com o ip 127.0.0.1
    - Servidor activemq escutando na porta padrão 61616


2 - Como testar o projeto

    - Carregar a base de dados com a API ( METHOD POST)  http://localhost:8080/database/create

    - Executar a API  para gerar  todos os PDFS : ( METHOD POST) http://localhost:8080/cliente/gerarPedidos/-1 ( Onde -1 é o comando para gerar todos os Documentos)

    - Caso queria testar, temos uma API para gerar os cupoms  informando o mes  ( METHOD POST) http://localhost:8080/cliente/gerarPedidos/mes/9 Method POST

    Obs :.. Temos pedidos sendo gerados somente para o mes corrente !


