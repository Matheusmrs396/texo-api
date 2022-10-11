Para rodar o projeto:
- Foram utilizados JDK 17, Maven 3.8.6 e Spring Boot 2.7.4 para o desenvolvimento;
- Após baixar o projeto e abri-lo em uma IDE, execute um clean install no Maven para que baixe as dependencias necessárias para sumirem os erros;
- Basta acessar a classe TexoMoviesApplication localizada em src/main/java/com/api/texomovies/TexoMoviesApplication.java e clicar em "run";
- O endpoint com a proposta da api está localizado na url http://localhost:8080/movies/awards-interval usando o método GET do padrão HTTP;
- Endpoint de listagem de todos os filmes localizado na url http://localhost:8080/movies usando o método GET do padrão HTTP;
- Endpoint para cadastrar filme localizado na url http://localhost:8080/movies usando o método POST do padrão HTTP;
- Endpoint para excluir filme localizado na url http://localhost:8080/movies/{idDoFilme} usando o método DELETE do padrão HTTP;
- Endpoint para atualizar(editar) filme localizado na url http://localhost:8080/movies/{idDoFilme} usando o método PUT do padrão HTTP;
- Endpoint para buscar um filme localizado na url http://localhost:8080/movies/{idDoFilme} usando o método GET do padrão HTTP;
- Caso queira verificar o banco basta acessar a url http://localhost:8080/h2 user:sa password:


Para rodar os teste de integração: 
- Basta acessar a classe MovieControllerTest localizada em src/test/java/com/api/texomovies/MovieControllerTest.java e clicar em "run"