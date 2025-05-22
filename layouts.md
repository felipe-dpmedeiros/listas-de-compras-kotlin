## Layouts XML

Os arquivos de layout XML definem a interface do usuário do aplicativo.

### `activity_main.xml`
Este é o layout principal da aplicação, onde o usuário interage para adicionar e visualizar os itens da lista de compras . Ele contém um `Toolbar` para o título, um `EditText` para inserir o nome do produto, um `Button` para adicionar o item e um `RecyclerView` para exibir a lista de itens .

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android" xmlns:app="http://schemas.android.com/apk/res-auto" xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent" android:layout_height="match_parent" android:orientation="vertical" tools:context="com.example.trabalhoandroidfelipe.MainActivity">
<androidx.appcompat.widget.Toolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:layout_marginBottom="16dp"
    android:background="?attr/colorPrimary"
    android:elevation="4dp"
    android:theme="@style/ThemeOverlay.AppCompat.ActionBar"
    app:popupTheme="@style/ThemeOverlay.AppCompat.Light" />

<EditText
    android:id="@+id/editText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginStart="16dp"
    android:layout_marginEnd="16dp"
    android:hint="Nome do produto"
    android:inputType="text"/>

<Button
    android:id="@+id/button"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginStart="16dp"
    android:layout_marginEnd="16dp"
    android:text="inserir" />

<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_marginStart="16dp"
    android:layout_marginEnd="16dp"
    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager" />
    </LinearLayout>
```

### `item_layout.xml`
Este arquivo define o layout de cada item individual na lista exibida pelo RecyclerView . Ele consiste em um TextView para mostrar o nome do item e um ImageButton para permitir a remoção do item .

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:gravity="center_vertical">

    <TextView
        android:id="@+id/textViewItem"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginEnd="16dp"
        android:layout_weight="1"
        android:textSize="18sp"
        android:textStyle="bold"
        tools:text="Novo Item" />

    <ImageButton
        android:id="@+id/imageButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@android:color/transparent"
        android:src="@drawable/baseline_delete_24"
        android:layout_marginEnd="16dp"/>

</LinearLayout>
```

### `drawable/baseline_delete_24.xml`
Este é um arquivo vetorial que define o ícone de lixeira usado no botão de remoção de itens em item_layout.xml

```
<vector xmlns:android="http://schemas.android.com/apk/res/android" android:height="24dp" android:tint="#000000" android:viewportHeight="24" android:viewportWidth="24" android:width="24dp">

    <path android:fillColor="@android:color/white" android:pathData="M6,19c0,1.1 0.9,2 2,2h8c1.1,0 2,-0.9 2,-2V7H6v12zM19,4h-3.5l-1,-1h-5l-1,1H5v2h14V4z"/>

</vector>
```

- [Estrutura do Projeto](estrutura_projeto.md)
- [Tecnologias Utilizadas](tecnologias_utilizadas.md)
- [Explicação do Código](explicacao_codigo.md)
- [Layouts XML](layouts.md)
- [MainActivity](mainactivity.md)
- [Configurações Gradle](gradle_config.md)
