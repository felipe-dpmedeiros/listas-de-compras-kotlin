## kotlin("kapt")
- KAPT = Kotlin Annotation Processing Tool
- Permite usar processadores de anotação Java em projetos Kotlin
## Dependencias usadas

```
//( Usado para expor os dados do banco de dados (a lista de itens) do ViewModel para a Activity)
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

(Utilizado para exibir a lista de compras. Cada item da lista é gerenciado e renderizado de forma eficiente pelo RecyclerView e seu Adapter, reciclando as visualizações dos itens para melhorar o desempenho.)
implementation("androidx.recyclerview:recyclerview:1.3.2")

(Garante que o aplicativo tenha uma aparência e comportamento consistentes)
implementation("androidx.appcompat:appcompat:1.4.1")

( Fornece extensões Kotlin (KTX) para o Android)
implementation("androidx.activity:activity-ktx:1.7.0")

(É a base para a persistência de dados local. Define como o banco de dados, entidades e DAOs são estruturados.)
implementation("androidx.room:room-runtime:2.4.1")

(Este é o processador de anotações do Room. Ele é usado pelo kapt para processar as anotações do Room (como @Database, @Entity, @Dao))
kapt("androidx.room:room-compiler:2.4.1")

( Fornece extensões Kotlin para Room, adicionando suporte de primeira classe para funcionalidades do Kotlin, como Coroutines e Flow.)
implementation("androidx.room:room-ktx:2.4.1")

(Adiciona suporte para Kotlin Coroutines em projetos Android. Coroutines simplificam o código assíncrono, tornando-o mais fácil de ler e manter.)
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
```
- [Estrutura do Projeto](estrutura_projeto.md)
- [Tecnologias Utilizadas](tecnologias_utilizadas.md)
- [Explicação do Código](explicacao_codigo.md)
- [Layouts XML](layouts.md)
- [MainActivity](mainactivity.md)
- [Configurações Gradle](gradle_config.md)

