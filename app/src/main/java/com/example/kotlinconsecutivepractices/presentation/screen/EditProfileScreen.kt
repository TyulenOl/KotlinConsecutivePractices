package com.example.kotlinconsecutivepractices.presentation.screen

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.kotlinconsecutivepractices.R
import com.example.kotlinconsecutivepractices.presentation.components.TimePickerDialog
import com.example.kotlinconsecutivepractices.presentation.viewModel.EditProfileViewModel
import org.koin.compose.viewmodel.koinViewModel
import java.io.File
import java.time.LocalTime
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(onBackNavigation: () -> Unit) {
    val context = LocalContext.current

    val viewModel = koinViewModel<EditProfileViewModel>()
    val uiState = viewModel.editProfileState

    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val pickMedia: ActivityResultLauncher<PickVisualMediaRequest> =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            viewModel.onImageSelected(uri)
        }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        { map: Map<String, Boolean> ->
            if (map.values.contains(false)) {
                val dialog = AlertDialog.Builder(context)
                    .setMessage("Ну, так не пойдет...")
                    .setCancelable(false)
                    .setPositiveButton("OK") { _, _ ->
                        onBackNavigation()
                    }

                dialog.show()
            }
            viewModel.onPermissionClosed()
        }

    val mGetContent = rememberLauncherForActivityResult<Uri, Boolean>(
        ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) {
            viewModel.onImageSelected(photoUri)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        text = stringResource(R.string.edit_profile),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { onBackNavigation() }
                            .padding(end = 5.dp)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { viewModel.saveParameters(onBackNavigation) }
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
                    .clip(CircleShape)
                    .clickable { viewModel.onIconClick() },
                error = painterResource(R.drawable.account_circle)
            )
            TextField(
                value = uiState.name,
                onValueChange = { viewModel.setName(it) },
                label = { Text(stringResource(R.string.profile_name)) },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.resumeUrl,
                onValueChange = { viewModel.setUrl(it) },
                label = { Text(stringResource(R.string.profile_url)) },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.timeString,
                onValueChange = { viewModel.onTimeChanged(it) },
                label = { Text(stringResource(R.string.timer)) },
                isError = uiState.timeError != null,
                trailingIcon = {
                    Icon(
                        painterResource(R.drawable.timer),
                        contentDescription = null,
                        modifier = Modifier.clickable { viewModel.onTimeInputClicked() }
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            uiState.timeError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                )
            }
            if (uiState.showTimePicker) {
                DialWithDialogExample(
                    onConfirm = { h, m -> viewModel.onTimeConfirmed(h, m) },
                    onDismiss = { viewModel.onTimeDialogDismiss() },
                    time = uiState.time
                )
            }
        }
    }

    if (uiState.showPermission) {
        LaunchedEffect(Unit) {
            val permissions = mutableListOf<String>()
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }

            if (
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissions.add(Manifest.permission.POST_NOTIFICATIONS)
            }

            requestPermissionLauncher.launch(permissions.toTypedArray())
        }
    }

    fun onCameraSelected() {
        val baseDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        val pictureFile = File(baseDir, "picture_${Date().time}.jpg")
        photoUri = FileProvider.getUriForFile(
            context,
            context.packageName + ".provider",
            pictureFile
        )
        photoUri?.let { mGetContent.launch(it) }
    }

    if (uiState.showSelect) {
        Dialog(onDismissRequest = { viewModel.onSelectDismiss() }) {
            Surface(
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.camera),
                        Modifier.clickable {
                            onCameraSelected()
                            viewModel.onSelectDismiss()
                        }
                    )
                    HorizontalDivider()
                    Text(text = stringResource(R.string.gallery),
                        Modifier.clickable {
                            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            viewModel.onSelectDismiss()
                        })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialWithDialogExample(
    onConfirm: (Int, Int) -> Unit,
    onDismiss: () -> Unit,
    time: LocalTime
) {
    val timePickerState = rememberTimePickerState(
        initialHour = time.hour,
        initialMinute = time.minute,
        is24Hour = true,
    )

    TimePickerDialog(
        onDismiss = { onDismiss() },
        onConfirm = { onConfirm(timePickerState.hour, timePickerState.minute) }
    ) {
        TimePicker(
            state = timePickerState,
        )
    }
}
