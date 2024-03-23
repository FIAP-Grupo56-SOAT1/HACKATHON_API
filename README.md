<div align="center">
  <h1>FIAP Hackathon SOAT1</h1>
</div>

![Logo](imgs/postech.gif)

## Timesheet
![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/FIAP-Grupo56-SOAT1/MICROSERV_PEDIDO_FAST-EATS/main-pipeline.yml?logo=github)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=bugs)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=FIAP-Grupo56-SOAT1_HACKATHON_API&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=FIAP-Grupo56-SOAT1_HACKATHON_API)

O Timesheet é um aplicativo de registro de ponto desenvolvido para uma hackathon como parte do programa de pós-graduação em Arquitetura de Software. O aplicativo facilita o rastreamento e gerenciamento eficientes do tempo para várias tarefas e projetos.

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
