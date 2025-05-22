
### `mainactivity.md`


### MainActivity

*Agora, chegamos ao MainActivity, ele vai ser o responsavel por integrar a atualização de UI com as ações do usuário.*

```
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalhoandroidfelipe.model.ItemModel
import com.example.trabalhoandroidfelipe.viewmodel.ItemsAdapter
import com.example.trabalhoandroidfelipe.viewmodel.ItemsViewModel
import com.example.trabalhoandroidfelipe.viewmodel.ItemsViewModelFactory
```

### A MainActivity herda de AppCompatActivity para usar os recursos de UI.

```
class MainActivity : AppCompatActivity() {
private lateinit var itemsViewModel: ItemsViewModel
private lateinit var itemsAdapter: ItemsAdapter

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Configuração da toolbar
    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)
    supportActionBar?.title = "Lista de Compras"

    // Inicialização do ViewModel
    itemsViewModel = ViewModelProvider(this, ItemsViewModelFactory(application))[ItemsViewModel::class.java]

    // Configuração do RecyclerView
    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
    recyclerView.layoutManager = LinearLayoutManager(this)
    itemsAdapter = ItemsAdapter(emptyList()) { item ->
        itemsViewModel.removeItem(item)
    }
    recyclerView.adapter = itemsAdapter

    // Observador para a lista de itens
    itemsViewModel.allItems.observe(this, Observer { items ->
        itemsAdapter.updateItems(items)
    })

    // Configuração do botão de adicionar
    val editText: EditText = findViewById(R.id.editText)
    val button: Button = findViewById(R.id.button)
    button.setOnClickListener {
        val itemName = editText.text.toString().trim()
        if (itemName.isNotEmpty()) {
            itemsViewModel.addItem(itemName)
            editText.text.clear()
        }
    }
}
}
```
- `setContentView(R.layout.activity_main)`: Define o layout da atividade (`activity_main.xml`).
- Configura a toolbar.
- Inicializa o `ViewModel` utilizando a `ItemsViewModelFactory`.
- Configura o `RecyclerView` com um `LinearLayoutManager` e o `ItemsAdapter`.
- Cria um `Observer` para a lista de itens no `ViewModel` e atualiza o `Adapter` quando a lista muda.
- Define um `OnClickListener` para o botão de adicionar, que pega o texto do `EditText`, adiciona um novo item à lista através do `ViewModel` e limpa o campo de texto.

- [Estrutura do Projeto](estrutura_projeto.md)
- [Tecnologias Utilizadas](tecnologias_utilizadas.md)
- [Explicação do Código](explicacao_codigo.md)
- [Layouts XML](layouts.md)
- [MainActivity](mainactivity.md)
- [Configurações Gradle](gradle_config.md)
