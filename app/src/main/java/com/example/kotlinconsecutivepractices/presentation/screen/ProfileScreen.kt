package com.example.kotlinconsecutivepractices.presentation.screen

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.kotlinconsecutivepractices.R
import com.example.kotlinconsecutivepractices.presentation.utils.SystemBroadcastReceiver
import com.example.kotlinconsecutivepractices.presentation.viewModel.GamesListViewModel
import com.example.kotlinconsecutivepractices.presentation.viewModel.ProfileViewModel
import org.koin.compose.viewmodel.koinViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onEditNavigation: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val uiState = viewModel.profileState
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        text = stringResource(R.string.profile)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { onEditNavigation() }
                            .padding(end = 5.dp)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AsyncImage(
                model = uiState.photoUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(125.dp)
                    .clip(CircleShape),
                error = painterResource(R.drawable.account_circle)
            )
            Text(
                text = uiState.name,
                style = MaterialTheme.typography.headlineMedium
            )
            Button(onClick = { enqueueDownloadRequest(uiState.resumeUrl, context) }) {
                Text(text = stringResource(R.string.resume))
            }
        }
    }
}

private const val DOWNLOAD_COMPLETE_ACTION = "android.intent.action.DOWNLOAD_COMPLETE"

@Composable
private fun InitBroadcastReceiver(context: Context) {
    SystemBroadcastReceiver(
        systemAction = DOWNLOAD_COMPLETE_ACTION,
        onSystemEvent = { intent ->
            if (intent?.action == DOWNLOAD_COMPLETE_ACTION) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
                if (id != -1L) {
                    navigateToDownloadedInvoice(context)
                }
            }
        })
}

private fun enqueueDownloadRequest(
    url: String,
    context: Context
) {
    val request: DownloadManager.Request = DownloadManager.Request(Uri.parse(url))
    with(request) {
        setTitle("resume.pdf")
        setMimeType("application/pdf")
        setDescription("Downloading pdf...")
        setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "resume.pdf"
        )
    }
    val manager: DownloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    manager.enqueue(request)
}

private fun navigateToDownloadedInvoice(context: Context) {
    try {
        val file = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ),
            "resume.pdf"
        )
        val uri = FileProvider.getUriForFile(
            context,
            context.applicationContext?.packageName + ".provider",
            file
        )
        val intent =
            Intent(Intent.ACTION_VIEW)
        with(intent) {
            setDataAndType(
                uri,
                "application/pdf"
            )
            flags =
                Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}