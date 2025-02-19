# Giro Backend Test - API Spring Boot

API RESTful desenvolvida para o teste técnico da Giro, construída com **Java 17**, **Spring Boot** e banco de dados **H2**. Inclui documentação Swagger e testes unitários.

[🔗 Repositório do Projeto](https://github.com/keniareis/giro-teste-backend/tree/main/backend-giro)

## ✨ Funcionalidades

- **API RESTful** para operações CRUD de usuários e registros
- Banco de dados **H2 em memória** (não requer configuração externa)
- Documentação interativa com **Swagger UI**
- **Testes unitários** com JUnit 5 e Mockito
- Validação de dados com Spring Validation
- Tratamento personalizado de exceções

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.8 ou superior
- (Opcional) IDE compatível com Spring Boot (IntelliJ, Eclipse, VS Code)

## 🚀 Instalação e Execução

1. **Clonar repositório**
```bash
git clone https://github.com/keniareis/giro-teste-backend.git
cd backend-giro
```

2. **Compilar o projeto**
```bash
mvn clean install
```

3. **Executar a aplicação**
```bash
mvn spring-boot:run
```

A aplicação estará disponível em:  
http://localhost:8080

## 🔍 Testando a Aplicação

A API pode ser testada das seguintes formas:

- **Swagger UI**: Interface interativa disponível em http://localhost:8080/swagger-ui.html
- **Insomnia ou Postman**: Envie requisições diretamente para os endpoints

**Executar todos os testes unitários:**
```bash
mvn test
```

## 📚 Documentação da API

Acesse a documentação interativa após iniciar a aplicação:
```http
http://localhost:8080/swagger-ui.html
```

### Principais Endpoints

**Taxa de câmbio**
- `POST /exchange-rates` - Criar nova taxa de câmbio
- `GET /exchange-rates` - Listar todas as taxas de câmbio
- `GET /exchange-rates/recent` - Buscar taxas de câmbio dos ultimos 7 dias
- `PUT /exchange-rates/{id}` - Atualizar taxa de câmbio por id
- `DELETE /exchange-rates/old` - Excluir taxa de câmbio antigas (30 dias)

**Investidor**
- `POST /investors` - Criar investidor
- `GET /investors` - Listar todos os investidores
- `GET /investor/{id}` - Buscar investidor por ID

**Moeda**
- `POST /currencies` - Criar moeda
- `GET /currencies` - Listar todas as moedas

**Investimento**
- `POST /investments` - Criar investimento


## 🛠️ Configuração do Banco de Dados

O projeto utiliza **H2 Database** em memória com configuração padrão:
```properties
# application.properties
spring.application.name=backend-giro
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=123
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
# http://localhost:8080/h2-console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

**Acessar console do H2:**  
http://localhost:8080/h2-console  
Credenciais:
- JDBC URL: `jdbc:h2:mem:testdb`
- User Name: `admin`
- Password: `123`


---

**Mais detalhes**: Consulte o [repositório do projeto](https://github.com/keniareis/giro-teste-backend/tree/main/backend-giro) para documentação técnica completa e histórico de commits.
```
Principais adaptações feitas para o contexto Spring Boot/H2:
- Adicionei instruções específicas para Maven
- Adaptei estrutura de diretórios padrão Spring
- Adicionei endpoints específicos mencionados no repositório
- Adicionei a documentação no swagger
- Criei testes unitarios para os principais services e repositories
