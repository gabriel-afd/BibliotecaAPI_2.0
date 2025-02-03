# 📚 BibliotecaAPI 2.0

Bem-vindo ao repositório da **BibliotecaAPI 2.0**! Este projeto é uma API robusta para gerenciamento de bibliotecas, desenvolvida com as melhores práticas e tecnologias modernas para garantir eficiência, segurança e escalabilidade. Ela permite operações básicas de CRUD (Create, Read, Update, Delete) para livros, autores e usuários, além de funcionalidades como empréstimo e devolução de livros.

---

## 🛠️ Tecnologias Utilizadas

Aqui estão as principais tecnologias utilizadas neste projeto, organizadas por categoria:

### 📌 Linguagem e Framework
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)

### 🗄️ Persistência e Banco de Dados
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white)

### ⚙️ Automação e Build
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

### 🔒 Segurança
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)

### 🧪 Testes
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-5A5A5A?style=for-the-badge&logo=mockito&logoColor=white)

### 📌 Utilitários
![Lombok](https://img.shields.io/badge/Lombok-CC0200?style=for-the-badge&logo=lombok&logoColor=white)
![Springdoc OpenAPI](https://img.shields.io/badge/Springdoc-6DB33F?style=for-the-badge&logo=openapi-initiative&logoColor=white)

---
---
##  Diagrama Entidade Relacionamento (ER)

    erDiagram
    Livro ||--o{ ReservaLivro : ""
    Livro }o--|| Categoria: ""
    ReservaLivro }o--|| Usuario: ""
    ReservaLivro }o--|| Leitor: ""

    Livro {
        Long id PK
        String title
        String isbn
        String language
        String autor
        LocalDateTime publication
        String category
        Boolean ativo
    }

    ReservaLivro {
        Long id PK
        Long livro_id FK
        Long leitor_id FK
        LocalDateTime data
        Integer qtdDias
    }

    Usuario {
        Long id PK
        String login
        String senha
    }

    Leitor {
        Long id PK
        String nome
        String email
    }

    Categoria {
        Long id PK
        String nome
    }

---

## 📂 Estrutura do Projeto

A aplicação segue a arquitetura MVC (Model-View-Controller), organizada da seguinte forma:

- **Model**: Contém as entidades principais como Médico, Paciente e Consulta, representando as tabelas do banco de dados.
- **Repository**: Interfaces que estendem o JpaRepository, fornecendo métodos para operações CRUD nas entidades.
- **Service**: Camada responsável pela lógica de negócios, intermediando as operações entre o Controller e o Repository.
- **Controller**: Gerencia as requisições HTTP, direcionando-as para os serviços apropriados e retornando as respostas adequadas.

---

## 🎯 Princípios Utilizados

A **BibliotecaAPI 2.0** foi desenvolvida seguindo os **princípios SOLID**, garantindo um código limpo, desacoplado e fácil de manter.

- **Single Responsibility Principle (SRP)**: Cada classe possui uma única responsabilidade, tornando o código mais organizado.
- **Open/Closed Principle (OCP)**: O código é projetado para ser aberto para extensão, mas fechado para modificação.
- **Liskov Substitution Principle (LSP)**: Garantia de que classes derivadas podem substituir suas classes base sem alterar o comportamento esperado.
- **Interface Segregation Principle (ISP)**: Interfaces menores e mais específicas para evitar dependências desnecessárias.
- **Dependency Inversion Principle (DIP)**: Utilização de injeção de dependência para desacoplar módulos e facilitar testes.

---

## 🛠️ Funcionalidades

- **Gerenciamento de Livros**: Adicione, atualize, remova e consulte livros no acervo da biblioteca.
- **Gerenciamento de Usuários**: Controle de usuários com autenticação e autorização.
- **Gerenciamento de Leitores**: Cadastro, atualização e remoção de leitores da biblioteca.
- **Empréstimos**: Funcionalidades para gerenciar empréstimos de livros, incluindo datas de devolução e renovações.
- **Notificações**: Sistema de notificações para alertar usuários sobre prazos de devolução e reservas disponíveis.

---
# Validações de Reserva de Livro

Este projeto contém diversas validações para garantir que as reservas de livros na biblioteca sigam as regras estabelecidas. As validações cobrem horários de funcionamento, limitações de dias de reserva, controle de livros disponíveis, e quantidades máximas de reservas por usuário.

## Validações Implementadas

### 1. **ValidacaoHorarioFuncionamentoBiblioteca 🕒**
Esta validação assegura que a reserva de um livro seja feita dentro do horário de funcionamento da biblioteca. As seguintes condições são verificadas:
- **Reservas no domingo**: Não são permitidas.
- **Antes das 8:00**: Reservas feitas antes do horário de abertura da biblioteca.
- **Durante o horário de almoço**: Reservas feitas entre 12:00 e 14:00, período em que a biblioteca está fechada para o almoço.
- **Após as 18:00**: Reservas feitas após o horário de fechamento da biblioteca.

Caso qualquer uma dessas condições seja atendida, a validação lança uma exceção com a mensagem: 
> "Biblioteca fora do horário de funcionamento".

### 2. **ValidacaoReservaMaximo7Dias 📅**
Essa validação garante que a data da reserva esteja dentro do intervalo permitido de 7 dias a partir da data atual. As condições verificadas são:
- **Data no passado**: Se a data de reserva for anterior à data atual, a validação lança uma exceção com a mensagem: 
  > "A data de reserva não pode ser no passado".
- **Reserva maior que 7 dias**: Se a reserva for realizada com mais de 7 dias de antecedência, a validação lança uma exceção com a mensagem: 
  > "A reserva do livro pode ser feita por, no máximo, 7 dias".

### 3. **ValidadorLivroAtivo 📚**
Essa validação verifica se o livro selecionado está **ativo** no catálogo da biblioteca. Caso o livro não esteja mais disponível para reserva, a validação lança uma exceção com a mensagem: 
> "O livro que você selecionou não está mais disponível no catálogo".

### 4. **ValidadorMaximoLivrosPorPessoa 🧑‍🤝‍🧑**
Essa validação impõe um limite na quantidade de livros que um usuário pode reservar ao mesmo tempo. As regras de controle são:
- **Limitação de livros por usuário**: O usuário não pode reservar mais de 2 livros ao mesmo tempo se a data da reserva estiver a menos de 4 dias da data atual.

Caso o usuário ultrapasse o limite, a validação lança uma exceção com a mensagem: 
> "Você não pode reservar mais de 2 livros".

### 5. **ValidadorReservaIndisponivel 🚫**
Essa validação assegura que um livro não possa ser reservado mais de uma vez para a mesma data. Se o livro já tiver uma reserva para a data desejada, a validação lança uma exceção com a mensagem: 
> "Livro já reservado!".

Essas validações são cruciais para garantir o bom funcionamento do processo de reserva de livros na biblioteca. Elas asseguram que as reservas estejam dentro dos horários de funcionamento, que os livros disponíveis sejam reservados corretamente e que os usuários cumpram as regras de quantidade de reservas.

As exceções geradas por essas validações ajudam a fornecer mensagens claras e amigáveis para os usuários, tornando o sistema mais eficiente e fácil de usar.

---

## 🧪 Testes

A **BibliotecaAPI 2.0** foi desenvolvida com um forte enfoque em testes e validações para garantir sua robustez:

- **Testes Unitários**: Utilizando JUnit e Mockito, cobrimos as unidades individuais da aplicação para assegurar que cada parte funcione isoladamente.
- **Testes de Integração**: Com o Spring Test, realizamos testes que abrangem múltiplos componentes, garantindo que eles funcionem harmoniosamente.

---

## 📌 Principais Endpoints

### 📚 Livros
- `GET /livros` - Lista todos os livros
- `POST /livros` - Cadastra um novo livro
- `GET /livros/{id}` - Busca um livro por ID
- `PUT /livros/{id}` - Atualiza um livro
- `DELETE /livros/{id}` - Remove um livro

### 👤 Usuários
- `POST /login` - Autenticação de usuários

### 📖 Reservas
- `POST /reserva` - Realiza um empréstimo

### 👥 Leitores
- `GET /leitor` - Lista todos os leitores
- `POST /leitor` - Cadastra um novo leitor
- `PUT /leitor/{id}` - Atualiza os dados de um leitor
- `DELETE /leitor/{id}` - Remove um leitor

---

## 🏃‍♂️ Como Executar

1. **Pré-requisitos**:
   - Java 11 ou superior instalado.
   - Maven instalado.

2. **Clonar o Repositório**:
   ```bash
   git clone https://github.com/gabriel-afd/BibliotecaAPI_2.0.git  
   cd BibliotecaAPI_2.0  
   ```  

3. **Construir o Projeto**:
   ```bash
   mvn clean install  
   ```  

4. **Executar a Aplicação**:
   ```bash
   mvn spring-boot:run  
   ```
   
---

 Por [gabriel-afd](https://github.com/gabriel-afd) 🚀
