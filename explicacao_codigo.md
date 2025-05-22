## Explicação do Código e suas Implementações

Esta seção detalha as principais classes Kotlin que compõem a lógica do aplicativo Lista de Compras, seguindo a arquitetura MVVM (Model-View-ViewModel).

### Classes do Modelo de Dados (Model)

**`ItemModel.kt`**: Representa a estrutura de um item individual na lista de compras. É uma classe de dados simples (`data class`) anotada com `@Entity` para ser reconhecida como uma tabela pelo Room Database. Cada item possui um `id` (chave primária gerada automaticamente) e um `name` (o nome do item).

```
// ItemModel.kt
@Entity
data class ItemModel(
@PrimaryKey(autoGenerate = true)
val id: Int = 0,
val name: String
)
```
Esta classe define como cada item da lista de compras será armazenado no banco de dados.

### Camada de Acesso a Dados (DAO)

**`ItemDao.kt`**: Esta interface define as operações de banco de dados permitidas para os `ItemModel`. Utiliza anotações do Room para especificar as consultas SQL.
- `@Dao`: Marca a interface como um Objeto de Acesso a Dados para o Room.
- `@Query("SELECT * FROM ItemModel")`: Define um método para buscar todos os itens da tabela `ItemModel`. Retorna `LiveData<List<ItemModel>>`, permitindo que a UI observe as mudanças nos dados de forma reativa.
- `@Insert`: Define um método para inserir um novo `ItemModel` no banco de dados.
- `@Delete`: Define um método para deletar um `ItemModel` existente do banco de dados.

```
// ItemDao.kt
@Dao
interface ItemDao {
@Query("SELECT * FROM ItemModel")
fun getAll(): LiveData<List<ItemModel>> // Busca todos os itens e observa mudanças
```
O `ItemDao` é crucial para interagir com o banco de dados SQLite de forma organizada e eficiente, abstraindo os detalhes das consultas SQL. As funções `insert` e `delete` são marcadas como `suspend` para serem executadas em coroutines, não bloqueando a thread principal.

### Banco de Dados (Room Database)

**`ItemDatabase.kt`**: Esta classe abstrata herda de `RoomDatabase` e serve como o ponto principal de acesso ao banco de dados persistente do aplicativo.
- `@Database(entities = [ItemModel::class], version = 1)`: Anotação que define quais entidades (tabelas) fazem parte deste banco de dados e a sua versão.
- `abstract fun itemDao(): ItemDao`: Fornece uma instância do `ItemDao` para que o ViewModel possa interagir com o banco de dados.

```
// ItemDatabase.kt
@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
abstract fun itemDao(): ItemDao // Método para obter o DAO
companion object {
    @Volatile
    private var INSTANCE: ItemDatabase? = null

    fun getDatabase(context: Context): ItemDatabase {
        return INSTANCE ?: synchronized(this) { // Garante uma única instância (Singleton)
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ItemDatabase::class.java,
                "item_database" // Nome do arquivo do banco de dados
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
```

A implementação do `companion object` com o método `getDatabase` utiliza o padrão Singleton para garantir que apenas uma instância do banco de dados seja criada durante o ciclo de vida da aplicação, prevenindo condições de corrida e uso excessivo de recursos.

### ViewModel

**`ItemsViewModel.kt`**: Atua como intermediário entre a UI (Activity/Fragment) e a camada de dados (repositório/banco de dados). Ele mantém os dados relacionados à UI, sobrevive a mudanças de configuração (como rotação da tela) e contém a lógica de negócios.
- `private val itemDao: ItemDao`: Recebe uma instância do DAO para interagir com o banco.
- `val allItems: LiveData<List<ItemModel>> = itemDao.getAll()`: Expõe a lista de itens como `LiveData`, permitindo que a UI observe as atualizações automaticamente.
- `fun addItem(itemName: String)`: Cria um novo `ItemModel` e o insere no banco de dados usando uma coroutine (`viewModelScope.launch`) para não bloquear a thread principal.
- `fun removeItem(item: ItemModel)`: Remove um item do banco de dados, também utilizando coroutines.

```
// ItemsViewModel.kt
class ItemsViewModel(application: Application) : AndroidViewModel(application) {
private val itemDao: ItemDao
val allItems: LiveData<List<ItemModel>>

init {
    val database = ItemDatabase.getDatabase(application)
    itemDao = database.itemDao()
    allItems = itemDao.getAll() // Inicializa a observação dos itens
}

fun addItem(itemName: String) {
    viewModelScope.launch(Dispatchers.IO) { // Executa em uma thread de background
        itemDao.insert(ItemModel(name = itemName))
    }
}

fun removeItem(item: ItemModel) {
    viewModelScope.launch(Dispatchers.IO) { // Executa em uma thread de background
        itemDao.delete(item)
    }
}
}
```

O `ItemsViewModel` gerencia o estado da lista de compras e lida com as ações do usuário (adicionar/remover itens), delegando as operações de persistência ao `ItemDao`.

### ViewModel Factory

**`ItemsViewModelFactory.kt`**: Como o `ItemsViewModel` possui um construtor que requer o `Application` (para obter o contexto para o banco de dados), precisamos de uma `ViewModelProvider.Factory` para instanciá-lo.
- `override fun <T : ViewModel> create(modelClass: Class<T>): T`: Este método é responsável por criar a instância do `ItemsViewModel`. Ele verifica se a classe solicitada é `ItemsViewModel` e, em caso afirmativo, a retorna com a dependência `application` injetada.


```
// ItemsViewModelFactory.kt
class ItemsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
override fun <T : ViewModel> create(modelClass: Class<T>): T {
if (modelClass.isAssignableFrom(ItemsViewModel::class.java)) {
@Suppress("UNCHECKED_CAST")
return ItemsViewModel(application) as T // Cria e retorna o ViewModel
}
throw IllegalArgumentException("Unknown ViewModel class")
}
}
```

Esta factory permite que o sistema Android crie instâncias do `ItemsViewModel` corretamente, especialmente durante recriações de Activities ou Fragments.

### Adapter para RecyclerView

**`ItemsAdapter.kt`**: O `RecyclerView.Adapter` é responsável por pegar os dados (a lista de `ItemModel`) e criar as views para cada item que será exibido na `RecyclerView`.
- `class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)`: Define como cada item da lista será visualmente representada e mantém referências para os componentes da UI (como `TextView` para o nome do item e `ImageButton` para remoção).
- `onBindViewHolder`: Popula os dados de um `ItemModel` específico na view do `ItemViewHolder`.
- `onItemRemoved: (ItemModel) -> Unit`: Um callback para notificar a `Activity` ou `ViewModel` quando o botão de remover de um item é clicado.

```
// ItemsAdapter.kt
class ItemsAdapter(
private var items: List<ItemModel>,
private val onItemRemoved: (ItemModel) -> Unit // Callback para remoção
) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {
// ViewHolder para cada item da lista
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewItem: TextView = itemView.findViewById(R.id.textViewItem)
    val imageButtonRemove: ImageButton = itemView.findViewById(R.id.imageButton)
}

// Cria novas views (invocado pelo layout manager)
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_layout, parent, false) // Infla o layout do item
    return ItemViewHolder(view)
}

// Substitui o conteúdo de uma view (invocado pelo layout manager)
override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    val currentItem = items[position]
    holder.textViewItem.text = currentItem.name
    holder.imageButtonRemove.setOnClickListener {
        onItemRemoved(currentItem) // Chama o callback ao clicar no botão de remover
    }
}

// Retorna o tamanho da sua dataset (invocado pelo layout manager)
override fun getItemCount() = items.size

// Atualiza a lista de itens e notifica o adapter
fun updateItems(newItems: List<ItemModel>) {
    items = newItems
    notifyDataSetChanged() // Notifica que os dados mudaram, para redesenhar a lista
}
}
```

O `ItemsAdapter` é fundamental para exibir a lista de compras de forma eficiente, reciclando views e atualizando-as conforme os dados mudam.

- [Estrutura do Projeto](estrutura_projeto.md)
- [Tecnologias Utilizadas](tecnologias_utilizadas.md)
- [Explicação do Código](explicacao_codigo.md)
- [Layouts XML](layouts.md)
- [MainActivity](mainactivity.md)
- [Configurações Gradle](gradle_config.md)
