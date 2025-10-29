# CarSell - Sistema de Vendas de Automóveis

CarSell é uma aplicação Spring Boot desenvolvida em Kotlin para gerenciar vendas de automóveis, incluindo funcionalidades de cadastro de carros, clientes, agendamentos de test-drive e processamento de vendas com integração de email via AWS SES.

## 🚀 Tecnologias Utilizadas

- **Kotlin** - Linguagem de programação principal
- **Spring Boot 3.5.4** - Framework web e de injeção de dependência
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **AWS SES** - Serviço de envio de emails
- **Docker** - Containerização do banco de dados
- **Gradle** - Gerenciamento de dependências e build

## 📋 Funcionalidades

### Gestão de Carros
- Cadastro, listagem, atualização e exclusão de veículos
- Controle de status de vendido/disponível
- Importação de dados mockados via JSON
- Atributos: marca, modelo, ano, cor, tipo de combustível, quilometragem, preço

### Gestão de Clientes
- CRUD completo de clientes
- Controle de saldo para validação de compras
- Dados: nome, email, telefone, CPF, saldo

### Sistema de Vendas
- Processamento de vendas com validação de saldo
- Suporte a múltiplos itens por venda
- Controle de cancelamento com regras de tempo (até 2 horas)
- Diferentes formas de pagamento

### Agendamentos
- Sistema de agendamento de test-drive
- Vinculação entre cliente e carro
- Observações personalizadas
- Integração com envio de email automático

### Notificações
- Envio de emails via AWS SES
- Notificações de agendamento de test-drive
- Configuração flexível de remetente e destinatário

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
src/main/kotlin/monokai/com/carsell/
├── config/          # Configurações (AWS SES)
├── domain/model/    # Entidades JPA
├── repositories/    # Repositórios Spring Data
├── rest/           # Controllers REST
├── service/        # Lógica de negócio
└── CarsellApplication.kt
```

### Entidades Principais

- **Carro**: Representa os veículos disponíveis
- **Cliente**: Dados dos compradores
- **Venda**: Transações de venda com itens
- **ItemVenda**: Itens individuais de uma venda
- **Agendamento**: Test-drives agendados

## 🛠️ Configuração e Instalação

### Pré-requisitos
- Java 17+
- Docker e Docker Compose
- Gradle

### 1. Clone o repositório
```bash
git clone <repository-url>
cd carsell
```

### 2. Configure o banco de dados
```bash
docker-compose up -d
```

### 3. Configure as credenciais AWS SES
Edite o arquivo `src/main/resources/application.properties`:
```properties
aws.ses.configuration.access-key=SUA_ACCESS_KEY
aws.ses.configuration.secret-key=SUA_SECRET_KEY
```

### 4. Execute a aplicação
```bash
./gradlew bootRun
```

A aplicação estará disponível em `http://localhost:8080`

## 📡 API Endpoints

### Carros
- `GET /carros` - Lista todos os carros
- `GET /carros/{id}` - Busca carro por ID
- `POST /carros` - Cadastra novo carro
- `PUT /carros/{id}` - Atualiza carro
- `DELETE /carros/{id}` - Remove carro
- `GET /carros/inserir-dados` - Importa dados do carros.json

### Clientes
- `GET /clientes` - Lista todos os clientes
- `GET /clientes/{id}` - Busca cliente por ID
- `POST /clientes` - Cadastra novo cliente
- `PUT /clientes/{id}` - Atualiza cliente
- `DELETE /clientes/{id}` - Remove cliente
- `GET /clientes/inserir-dados` - Cria clientes mockados
- `DELETE /clientes/limpar-base` - Remove todos os clientes

### Vendas
- `GET /vendas` - Lista todas as vendas
- `GET /vendas/{id}` - Busca venda por ID
- `POST /vendas` - Processa nova venda
- `PUT /vendas/{id}` - Atualiza venda
- `DELETE /vendas/{id}` - Remove venda

### Agendamentos
- `GET /agendamentos` - Lista agendamentos
- `GET /agendamentos/{id}` - Busca agendamento por ID
- `POST /agendamentos` - Cria novo agendamento
- `PUT /agendamentos/{id}` - Atualiza agendamento
- `DELETE /agendamentos/{id}` - Remove agendamento

## 💼 Regras de Negócio

### Vendas
- Cliente deve ter saldo suficiente para a compra
- Vendas podem ser canceladas em até 2 horas após a criação
- Carros vendidos são marcados como indisponíveis

### Agendamentos
- Cada agendamento vincula um cliente a um carro específico
- Email de confirmação é enviado automaticamente
- Suporte a observações personalizadas

## 🗄️ Banco de Dados

O sistema utiliza PostgreSQL com as seguintes tabelas:
- `CARRO` - Dados dos veículos
- `clientes` - Informações dos clientes
- `vendas` - Transações de venda
- `item_venda` - Itens das vendas
- `agendamento` - Test-drives agendados

## 🔧 Configurações

### Banco de Dados
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vendasdb
spring.datasource.username=vendasdb
spring.datasource.password=vendasdb
```

### AWS SES
```properties
aws.ses.configuration.access-key=<sua-access-key>
aws.ses.configuration.secret-key=<sua-secret-key>
```

## 🧪 Testes

Execute os testes com:
```bash
./gradlew test
```

## 📦 Build e Deploy

### Build da aplicação
```bash
./gradlew build
```

### Gerar JAR executável
```bash
./gradlew bootJar
```

O JAR será gerado em `build/libs/carsell-0.0.1-SNAPSHOT.jar`

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 📞 Suporte

Para suporte e dúvidas, entre em contato através do email configurado no sistema ou abra uma issue no repositório.