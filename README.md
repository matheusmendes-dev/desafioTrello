# Trello Board

A idéia deste aplicativo é permitir visualizar informações de boards e cards a partir da API do Trello. O projeto foi desenvolvido utilizando a arquitetura MVVM (Model-View-ViewModel) e integra-se com a API do Trello para obter dados em tempo real, como boards, listas e cards.

## Funcionalidades

- Listagem de boards do usuário

## Tecnologias Utilizadas

- **Kotlin**: Linguagem principal de desenvolvimento.
- **Jetpack Compose**: Para criação de telas e componentes.
- **Jetpack Navigation**: Para navegação entre as telas.
- **Retrofit**: Para comunicação HTTP com a API do Trello.
- **Coroutines**: Para operações assíncronas e chamadas à API.
- **StateFlow**: Para gerenciar estados da UI de maneira reativa.
- **Dagger-Hilt**: Injeção de dependência.
- **Material Design 3**: Interface de usuário seguindo os padrões do Material Design.
- **JUnit + MockK**: Para testes unitários.
- **MVVM**: Arquitetura para manter a separação de responsabilidades.

## Requisitos

Antes de executar o projeto, você precisa ter:

- **Android Studio** (recomendado: versão mais recente)
- **JDK 11** ou superior
- Conta no [Trello](https://trello.com) e uma API Key e Token válidos para acesso à API

## Configuração do Ambiente

1. Clone o repositório para a sua máquina local:
```bash
   git clone https://github.com/matheusmendes-dev/desafioTrello.git
```

2. Adicione suas credenciais da API do Trello nas variáveis de ambiente:
```bash
TRELLO_API_BASE_URL=
TRELLO_API_KEY=
TRELLO_API_TOKEN=
```

## Testes

Este projeto inclui testes unitários para a lógica de negócios. Para rodar os testes:
```bash
./gradlew test
```

Os testes foram implementados utilizando JUnit e MockK para simulação de dependências e validação de comportamentos.

## Estrutura do Projeto

- **data**: Contém os repositórios e as chamadas para a API do Trello.
- **domain**: Contém as entidades e os casos de uso (Use Cases).
- **ui**: Contém os componentes da interface de usuário (Views e ViewModels).
- **di**: Configurações de injeção de dependências (Dagger-Hilt).
- **common**: Contém componentes que podem ser reutilizados em todo app.
- **helper**: Contém classes de funcionalidades.

## Autenticação com a API do Trello

Para acessar as informações de boards, é necessário configurar a autenticação com a API do Trello:

1. Acesse o [site](https://developer.atlassian.com/cloud/trello/guides/rest-api/api-introduction/) de desenvolvimento do Trello e siga os passos para gerar sua API Key.
2. Gere um Token de Acesso para a sua aplicação.
3. Adicione essas credenciais nas variáveis de ambiente, como descrito acima.

[Link](https://developer.atlassian.com/cloud/trello/rest/api-group-actions/#api-group-actions) da documentação da API do Trello.

## Melhorias Futuras ##

- Implementar caching de dados para uso offline.
- Suporte para editar e criar novos cards.
- Notificações push para alterações em boards e cards.
