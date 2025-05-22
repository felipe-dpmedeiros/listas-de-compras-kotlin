# Android Lista de Compras

Um aplicativo Android para criar uma de lista de compras, desenvolvido em Kotlin.

## Funcionalidades
- **Adicionar itens**: Digite o nome do item no campo de texto e clique no botão para adicioná-lo à lista
- **Remover itens**: Toque no botão de remoção ao lado de qualquer item para excluí-lo da lista
- **Persistência de dados**: Todos os itens são salvos localmente usando Room Database

## Tela inicial
### Tela principal e funcionamento do app
![Página inicial do aplicativo](assets/telaprincipal.gif)

## Características

- **Arquitetura MVVM** com ViewModel e LiveData
- **Uso de RecyclerView**
- **Uso de SQLite para persistência de dados**
- **Persistência local** com SQLite


## Arquitetura do Projeto

### Padrão MVVM (Model-View-ViewModel)
O aplicativo segue o padrão arquitetural MVVM, proporcionando:
- Separação clara de classes e etc.
- Facilidade de testes
- Manutenibilidade do código


- [Estrutura do Projeto](estrutura_projeto.md)
- [Tecnologias Utilizadas](tecnologias_utilizadas.md)
- [Explicação do Código](explicacao_codigo.md)
- [Layouts XML](layouts.md)
- [MainActivity](mainactivity.md)
- [Configurações Gradle](gradle_config.md)
