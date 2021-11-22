# Desafio Automação - API REST

Desafio da Base 2 para automação de API.



### Requisitos

- Instalação do Docker e importação do container "Mantis".

- Configuração do Mantis.

- Criação do Token manualmente pelo front.

  - Apontamento da chave na arquitetura.

- Importação das dependências.

  

  

###  Instalação

#### 	Mantis

1. Criar uma pasta  em C:\ com nome Mantis.

2. Abra um terminal e acesse o diretório recém criado.

3. Colar o arquivo **docker-compose.yml** no diretório

4. Execute o comando> `docker-compose.exe up -d`

5. Após o processamento se tudo correr bem, as imagens serão baixadas e novos contêineres criados.

6. Para validar a criação e execução, execute o comando `docker ps -a` e os contêineres estarão disponíveis e executando:

   

   #### **Banco de Dados**

   Faça o seu primeiro acesso ao Mantis pelo endereço [*http://127.0.0.1:8989*](http://127.0.0.1:8989/)
   
   Após acessar será necessário configurar o banco de dados conforme tabela abaixo:
   
   | Variável                                        | Valor          |
   | ----------------------------------------------- | -------------- |
   | Type of Database                                | MySQL Improved |
   | Hostname (for Database Server)                  | mantis_db_1    |
   | Username (for Database)                         | mantisbt       |
   | Password (for Database)                         | mantisbt       |
   | Database name (for Database)                    | bugtracker     |
   | Admin Username (to create Database if required) | root           |
   | Admin Password (to create Database if required) | root           |
   
   Após preencher, clicar em **Continue** e aguardar o processamento.
   
   
   
   #### Token
   
   1.  Acessar o front do Mantins "http://localhost:8989/login_page.php".
   2. Usuário "***Administrator***" Senha: "***root***".
   3. Clicar em *administrator >> Minha Conta >> Tokens API*.
   4. Criar uma chave Token para acesso das APIs e apontar dentro da   ***globalParameters.properties*** do projeto.
   





## Desafio:

Desafio Automação - APIs REST

## Testes

- Implementar 50 scripts de testes que manipulem uma aplicação cuja interface é uma API REST.  -


*Testes distribuídos em classes localizadas na  pasta**tests** . Utilizado a arquitetura padrão disponibilizada pela Base2. Os testes foram baseados nas rotinas de  **usuários**(User),  **projetos** (Projects), **tarefas**(Issues) e **filtros**(Filters) conforme  documentação: [Mantis Bug Tracker REST API (getpostman.com)](https://documenter.getpostman.com/view/29959/mantis-bug-tracker-rest-api/7Lt6zkP#e91823cf-79bb-02bd-f1b1-5b9b3160ed1b)*.



- Alguns scripts devem ler dados de uma planilha Excel para implementar Data-Driven.


*Implementado o DDT via **CSV** e **Excel**. Criando as classes **ReadingCSVUtils.class** e **Excelutils.class** para leitura dos arquivos que se encontram dentro da pasta **resources** e na classe **PostUserTests.class**  foi feito a chamada dos testes **cadastrarVariosUsuarioscomSucessoDDTCSV()** e **cadastrarVariosUsuarioscomSucessoDDTExcel()** para execução dos DDT.*



- O projeto deve tratar autenticação. Exemplo: OAuth2.


*A aplicação do Mantis, exige autenticação via API Token. Token (gerado no front) foi utilizado em todos os testes, informado no **globalParameters.properties** e configurado no **RequestRestBase.class** no **headers.put("Authorization", GlobalParameters.TOKEN)***.



- Pelo menos um teste deve fazer a validação usando REGEX (Expressões Regulares).


*Na classe **PostUserTests.class** foi criado o teste **cadastrarUsuariocomvalidacaoRegexEmail()** que aplica a validação do e-mail com REGEX,  fazendo a chamada via metodo response.body("user.email",matchesRegex(Regex)).*



- O projeto deverá gerar um relatório de testes automaticamente.


*Ao final da execução dos testes é salvo um relatório na pasta **\target\reports**.  O relatório é configurado pela classe **ExtentReportsUtils.class**  e chamado nos métodos **@BeforeSuite, @BeforeMethod, @AfterMethod e @AfterSuite**  da classe **TestBase.class** para que seja criado em  cada execução de forma automática.*



- Implementar pelo menos dois ambientes (desenvolvimento / homologação).


*Os ambientes de teste podem ser parametrizados dentro da **globalParameters.properties** e apontados no **enviroment** ainda dentro deste mesmo arquivo. Podendo se preciso criar vários ambientes para troca rápida, necessário neste caso ajustar também dentro da classe **GlobalParameters.class**.(Criado o ambiente Teste (test)).*



- A massa de testes deve ser preparada neste projeto, seja com scripts carregando massa nova no BD ou com restore de banco de dados.


*Utilizado os parâmetros da classe **DButils.class** para conexão e execução das queries. Configurado o banco em **globalParameters.properties**. Criando algumas chamadas para execução antes de alguns testes, como por exemplo, excluir e cadastrar usuários antes de inicar os testes, no **@BeforeSuite** . A classes que chamam as queries fica na pasta **dbsteps** e os arquivos .sql estão salvos em **queries**.  Dessa forma podendo aproveitar a query mais de uma vez em diferentes testes.*



- Executar testes em paralelo. Pelo menos duas threads (25 testes cada).


*Dentro do **pom.xml**, definimos o caminho para salvar a **SuiteTests.XML**, dentro do arquivo **SuitesTests**, podemos definir* o numero de threads que podemos executar de forma simultânea pelo **thread-count="2"**.



- Testes deverão ser agendados pelo Azure DevOps, Gitlab-CI, Jenkins, CircleCI, TFS ou outra ferramenta de CI que preferir.

*Os testes rodam de forma continua pelo GitLab Runner, apos o processo merge na branche main. Foi necessário configurar o Runner  no servidor local, simulando o servidor PRD ou HML do ambiente de desenvolvimento e as configurações de execução do  CI estão presentes no arquivo **.gitlab-ci.ym**l.*

*Links de apoio:* 

- *[Git Runnes - Instalação.](https://docs.gitlab.com/runner/install/windows.html)*
- *[Git Runners - Configuração.](https://docs.gitlab.com/runner/register/index.html)*
- *[Configuração ambiente, se precisar](https://stackoverflow.com/questions/68050125/gitlab-runner-prepare-environment-failed-to-start-process-pwsh-in-windows).*
- *[Instalação MAVEN](http://luizricardo.org/2014/06/instalando-configurando-e-usando-o-maven-para-gerenciar-suas-dependencias-e-seus-projetos-java/).*

​	

