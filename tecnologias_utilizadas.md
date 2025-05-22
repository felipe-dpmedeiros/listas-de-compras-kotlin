##  Tecnologias Utilizadas

### Linguagem e Framework
- **Kotlin**: Linguagem principal
- **ViewModel**: Gerenciamento de dados 
  - **LiveData**: Observadores de dados reativos
  - **RecyclerView**: Listas recicláveis
  - **View Binding**: Interação com views
- **SQLite**: Banco de dados relacional embarcado
- **ContentValues**: Operações de banco estruturadas

### Interface do Usuário

- **CardView**: Componentes com elevação
- **Uso de icones**: uso de icone de lixo para deletar (baseline_delete_24.xml)


### Ferramentas de Build
- **Gradle**: gerenciamento de dependências

## Room Database
Biblioteca de persistência que fornece uma camada de abstração sobre SQLite, facilitando o trabalho com banco de dados local.

## LiveData
Componente de arquitetura que permite observar mudanças nos dados e atualizar a UI automaticamente.

## RecyclerView
Componente para exibir listas de dados de forma eficiente, com suporte a ViewHolder pattern.

## ViewModel
Classe projetada para armazenar e gerenciar dados relacionados à UI de forma consciente ao ciclo de vida.

## Coroutines
Recurso do Kotlin para programação assíncrona, usado para operações de banco de dados sem bloquear a thread principal.

- [Estrutura do Projeto](estrutura_projeto.md)
- [Tecnologias Utilizadas](tecnologias_utilizadas.md)
- [Explicação do Código](explicacao_codigo.md)
- [Layouts XML](layouts.md)
- [MainActivity](mainactivity.md)
- [Configurações Gradle](gradle_config.md)
