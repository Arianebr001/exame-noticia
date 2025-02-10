package com.news.noticiasapp.views
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.news.noticiasapp.viewmodels.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNewsScreen(
    newsId: Int,
    onNewsUpdated: () -> Unit,
    viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val currentNews by viewModel.currentNews
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    LaunchedEffect(newsId) {
        viewModel.fetchNewsById(newsId)
    }

    LaunchedEffect(currentNews) {
        currentNews?.let {
            title = it.title
            content = it.content
            author = it.author
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Notícia") },
                navigationIcon = {
                    IconButton(onClick = onNewsUpdated) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Fechar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Conteúdo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Autor") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    currentNews?.let {
                        viewModel.updateNews(
                            newsId,
                            it.copy(
                                title = title,
                                content = content,
                                author = author
                            )
                        )
                    }
                    onNewsUpdated()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Alterações")
            }
        }
    }
}