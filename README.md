# 💻 Cadastro de Usuários em Console

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red?style=for-the-badge&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

## 📖 Descrição

Aplicação de console desenvolvida em Java para o cadastro de pessoas. O sistema coleta dados do usuário, busca informações de endereço a partir do CEP utilizando a API externa [ViaCEP](https://viacep.com.br/), e salva os registros de forma persistente em um arquivo JSON local.

Este projeto foi construído para demonstrar habilidades fundamentais e práticas da indústria de software, incluindo consumo de APIs, manipulação de JSON, gerenciamento de dependências com Maven, e a criação de uma aplicação robusta e executável.

## ✨ Funcionalidades

-   [x] **Cadastro de Pessoas:** Coleta de nome, e-mail, CEP e complemento.
-   [x] **Validação de Entradas:** Verificação básica de formato para e-mail e CEP.
-   [x] **Consulta de Endereço via API:** Integração com a API ViaCEP para buscar dados de endereço automaticamente.
-   [x] **Persistência de Dados:** Salvamento e carregamento de todos os cadastros em um arquivo `pessoas.json`.
-   [x] **Tratamento de Erros:** O programa lida com casos como a não existência do arquivo de dados na primeira execução.
-   [x] **Listagem de Cadastros:** Exibição de todas as pessoas salvas no arquivo.

## 🛠️ Tecnologias Utilizadas

-   **Java 17:** Linguagem principal do projeto.
-   **Maven:** Gerenciamento de dependências e do ciclo de vida do build.
-   **`java.net.http.HttpClient`:** Cliente HTTP nativo do Java para fazer as requisições à API.
-   **Gson:** Biblioteca do Google para serializar e desserializar objetos Java para o formato JSON.
-   **Maven Shade Plugin:** Utilizado para empacotar o projeto em um "fat JAR" executável, incluindo todas as dependências.
-   **(Planejado) JUnit 5 & Mockito:** Frameworks para a escrita de testes unitários e simulação de dependências.

## 🚀 Como Executar o Projeto

Para executar este projeto localmente, siga os passos abaixo.

### Pré-requisitos

-   [JDK (Java Development Kit)](https://www.oracle.com/java/technologies/downloads/) - Versão 17 ou superior.
-   [Apache Maven](https://maven.apache.org/download.cgi)
-   [Git](https://git-scm.com/downloads)

### Passo a Passo

```bash
# 1. Clone o repositório para sua máquina local
git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)

# 2. Navegue para o diretório raiz do projeto
cd seu-repositorio

# 3. Compile e construa o projeto com o Maven
# (Este comando vai baixar as dependências e criar o JAR executável)
mvn clean install

# 4. Execute a aplicação
java -jar target/cadastro-console-1.0.0.jar