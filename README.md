<div align="center">
  <h1>FIAP Hackathon SOAT1</h1>
</div>

![Logo](imgs/postech.gif)

## Timesheet
![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/FIAP-Grupo56-SOAT1/MICROSERV_PEDIDO_FAST-EATS/main-pipeline.yml?logo=github)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=bugs)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API)

O Timesheet é um aplicativo de registro de ponto desenvolvido para uma hackathon como parte do programa de pós-graduação em Arquitetura de Software. O aplicativo facilita o registro de pontos dos funcionários e gerar relatórios referentes aos registros de ponto.

## Funcionalidades
- **Registro de Ponto**: Os usuários podem registrar suas horas de trabalho.
- **Autenticação de Usuário**: Sistema de login seguro para garantir a privacidade dos dados.
- **Relatórios**: Gere relatórios sobre os registros de ponto.

## Arquitetura
![image](https://github.com/FIAP-Grupo56-SOAT1/HACKATHON_API/assets/47258234/65f93f48-3a6b-4100-93e6-773e63017c7b)


O aplicativo Timesheet é construído utilizando a arquitetura hexagonal, também conhecida como arquitetura de portas e adaptadores. Este estilo arquitetônico promove modularidade, manutenibilidade e testabilidade, dividindo o aplicativo em várias camadas:

- **Domínio Central**: Contém a lógica de negócios central e as entidades de domínio.
- **Serviços de Aplicação**: Implementa casos de uso e orquestra interações entre a camada de domínio e sistemas externos.
- **Adaptadores**: Conecta o aplicativo a sistemas externos, como bancos de dados, interfaces de usuário e serviços externos.

## Tecnologias Utilizadas
- **Linguagem de Programação**: Java
- **Framework**: Spring Boot
- **Banco de Dados**: MySQL
- **Autenticação**: Spring Security
- **Gestão de Dependências e Compilação**: Maven
- **Testes**: JUnit, Mockito

## Executando o Projeto

### Variáveis de Ambiente

- Na raiz do projeto existe o arquivo [env-exemplo](env-exemplo) onde estão todas as variáveis de ambiente necessárias para a execução da aplicação.
- **`Para facilitar a avaliação do projeto pelos professores já deixamos pronto o arquivo .env com todas as variáveis necessárias.`**

### Executando com Docker
Para realização dos testes do projeto inteiro de forma local foi criado um arquivo [docker-compose](docker-compose.yml) na raiz do repositório da API, executando o comando docker-compose up -d no diretório onde está esse arquivo será executada a aplicação Timesheet e o banco de dados que a aplicação utiliza.

<br></br>
Execute o comando abaixo no diretório raiz do projeto para executar a aplicação em conjunto com os demais serviços:

```bash
docker-compose up -d
```

<br></br>
Para realizar testes de forma local apenas para este microserviço no diretorio `src/test/resources/docker-compose` existem os arquivos docker-compose para auxiliar.