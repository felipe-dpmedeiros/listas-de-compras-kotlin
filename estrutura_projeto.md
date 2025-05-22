## Estrutura do Projeto (apenas mostrando as modificadas/criadas)

```
├── app/
│ ├── build.gradle.kts (App-specific build configuration)
│ └── src/
│ └── main/
│ ├── java/
│ │ └── com/
│ │ └── example/
│ │ └── trabalhoandroidfelipe/
│ │ ├── MainActivity.kt (Atividade principal)
│ │ ├── model/ (Classes de modelo de dados)
│ │ │ └── ItemModel.kt
│ │ ├── data/ (Camada de dados)
│ │ │ ├── ItemDatabase.kt
│ │ │ └── ItemDao.kt
│ │ └── viewmodel/ (ViewModel e Adapter)
│ │ ├── ItemsViewModel.kt
│ │ ├── ItemsViewModelFactory.kt
│ │ └── ItemsAdapter.kt
│ └── res/ (Layout files, resources)
│ └── layout/
│ ├── activity_main.xml
│ └── item_layout.xml
│ └── drawable/
│ └── baseline_delete_24.xml
├── build.gradle.kts 
└── gradle.properties 
```
*   **Model:** Define a classe `ItemModel` que representa os dados dos itens da lista.
*   **View:** Compreende a interface do usuário, representada pela `MainActivity.kt` e seus layouts correspondentes (`activity_main.xml` e `item_layout.xml`).
*   **ViewModel:** Gerencia a lógica de negócios e a interação com a camada de dados.
*   **Data:** Contém as classes para acesso ao banco de dados Room (`ItemDatabase.kt` e `ItemDao.kt`).


- [Estrutura do Projeto](estrutura_projeto.md)
- [Tecnologias Utilizadas](tecnologias_utilizadas.md)
- [Explicação do Código](explicacao_codigo.md)
- [Layouts XML](layouts.md)
- [MainActivity](mainactivity.md)
- [Configurações Gradle](gradle_config.md)

