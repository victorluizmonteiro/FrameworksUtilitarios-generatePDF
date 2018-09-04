 Passo a passo de execução do projeto
Entrega inclui desenho de uma arquitetura utilizando  o Pattern Paginator do EIP, arquivo FIAP-EIP.jpeg
1 - Pré requisitos
    - Java 8
    - Servidor MySQL rodando na porta 3306 e com o ip 127.0.0.1
    - Servidor activemq escutando na porta padrão 8161
    - Alterar  á pasta destino dos pdfs no arquivo application.properties , proriedade : folder.destiny.pdf

   Obs:.. na pasta de destino dos PDFS, sempre deixar o ultimo nome como cupom, exemplo C:\\PDF\\cupom

2 - Como testar o projeto

    - Utilizar a branch master

    - Carregar a base de dados com a API ( METHOD POST)  http://localhost:8080/database/create

    - Executar a API  para gerar  todos os PDFS : ( METHOD POST) http://localhost:8080/cliente/gerarPedidos/-1 ( Onde -1 é o comando para gerar todos os Documentos)

    - Caso queria testar, temos uma API para gerar os cupoms  informando o mes  ( METHOD POST) http://localhost:8080/cliente/gerarPedidos/mes/9 Method POST

    Obs :.. Temos pedidos sendo gerados somente para o mes corrente !


    Obs 1 :. Nossa aplicação está levando em média 2 segundos para postar todos os pedidos na fila de processamento.
    Para vermos está performance, comentamos a linha 58 da classe JmsConsumerPDF.javaque é responsavel por gerar os PDFS  e executamos o passo á
     passo de testes da aplicação.

