package com.bagaspardanailham.greenheroesapp.presentation.home

import android.R.attr.bitmap
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.BackHandler
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagaspardanailham.greenheroesapp.R
import com.bagaspardanailham.greenheroesapp.data.model.AnalyzeCategoryItem
import com.bagaspardanailham.greenheroesapp.data.model.analyzeCategoryItems
import com.bagaspardanailham.greenheroesapp.presentation.AnalyzeCategoryItemDataState
import com.bagaspardanailham.greenheroesapp.presentation.UiState
import com.bagaspardanailham.greenheroesapp.presentation.vm.AnalyzeVM
import com.bagaspardanailham.greenheroesapp.presentation.vm.DiseaseResult
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_bold
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_medium
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_regular
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnalyzeScreen(
    controller: LifecycleCameraController,
    viewModel: AnalyzeVM = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    var currentBottomSheetContent: (@Composable () -> Unit)? by remember { mutableStateOf(null) }

    key(modalSheetState.currentValue) {
        if (modalSheetState.currentValue == ModalBottomSheetValue.Hidden) {
            currentBottomSheetContent = null
        }
    }

    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContent = {
            currentBottomSheetContent?.invoke()
        }
    ) {
        Scaffold { _ ->
            Box(modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                CameraPreviewScreen(controller, Modifier.fillMaxSize())
                LineAnalyzer(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val (topAppBar, alizeSelectorLayout, analyzeBtn) = createRefs()
                    TopAppBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(topAppBar) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(horizontal = 16.dp),
                        title = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = "Analyze",
                                fontFamily = poppins_medium,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        },
                        navigationIcon = {
                            Icon(
                                modifier = Modifier.size(34.dp),
                                imageVector = Icons.Filled.ArrowCircleLeft,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        },
                        colors = TopAppBarColors(
                            containerColor = Color.Transparent,
                            navigationIconContentColor = Color.White,
                            titleContentColor = Color.White,
                            scrolledContainerColor = Color.Transparent,
                            actionIconContentColor = Color.White
                        )
                    )
                    Box(
                        modifier = Modifier
                            .constrainAs(alizeSelectorLayout) {
                                top.linkTo(topAppBar.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(start = 16.dp, end = 16.dp, top = 30.dp)
                            .alpha(.6f)
                            .background(color = Color.White, shape = CircleShape)
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .clickable(onClick = {
                                currentBottomSheetContent = {
                                    AnalyzeSelector(coroutineScope, modalSheetState)
                                    coroutineScope.launch {
                                        modalSheetState.show()
                                    }
                                }
                            })
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            text = "Plant Identification",
                            fontFamily = poppins_regular,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterEnd),
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Dropdown Icon"
                        )
                    }

                    FloatingActionButton(
                        shape = CircleShape,
                        contentColor = Color.Black,
                        containerColor = colorResource(id = R.color.light_green),
                        onClick = {
//                            val image = R.drawable.disease_sample1
//                            val bitmap = BitmapFactory.decodeResource(context.resources, image)
                            takePictureToBitmap(context, controller) { bitmap ->
                                if (bitmap != null) {
                                    // Just keep in variable / send to ViewModel
                                    viewModel.analyzeImage(bitmap)
                                }
                            }
//                            analyzeImage()
                        },
                        modifier = Modifier
                            .padding(bottom = 40.dp)
                            .constrainAs(analyzeBtn) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .border(1.dp, Color.White, CircleShape),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.DocumentScanner,
                            contentDescription = "Analyze"
                        )
                    }
                }

                when(val state = state.apiResp) {
                    is UiState.Loading -> {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                color = colorResource(id = R.color.light_green)
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = 8.dp),
                                text = "Analyzing...",
                                fontFamily = poppins_medium,
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        }
                    }
                    is UiState.Success -> {
                        currentBottomSheetContent = {
                            AnalyzeResultDetail(
                                modalSheetState = modalSheetState,
                                coroutineScope = coroutineScope,
                                state = state.data ?: DiseaseResult()
                            )
                            LaunchedEffect(state.data) {
                                modalSheetState.show()
                            }
                        }
                    }
                    is UiState.Error -> {
                        println("ERROR -> ${state.message}")
                    }
                    else -> {

                    }
                }
            }
        }
    }
}

@Composable
fun AnalyzeSelector(
    coroutineScope: CoroutineScope,
    modalSheetState: ModalBottomSheetState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .padding(26.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Analyze For",
                fontFamily = poppins_medium,
                fontSize = 20.sp,
                color = Color.Black
            )
            Icon(
                imageVector = Icons.Filled.Close,
                tint = Color.Black,
                contentDescription = "Close Icon",
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            modalSheetState.hide()
                        }
                    }
            )
        }

        val analyzeCatDataState = remember {
            AnalyzeCategoryItemDataState()
        }
        analyzeCatDataState.setAnalyzeCatList(analyzeCategoryItems)
        LazyColumn(
            modifier = Modifier
                .padding(start = 26.dp, end = 26.dp, bottom = 26.dp)
        ) {
            items(analyzeCatDataState.analyzeCategoryList, key = { it.id }) {
                AnalyzeCategoryCardItem(
                    category = it,
                    onSelectChange = analyzeCatDataState::onItemSelected
                )
            }
        }
    }
}

@Composable
fun AnalyzeResultDetail(state: DiseaseResult, modalSheetState: ModalBottomSheetState, coroutineScope: CoroutineScope) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(top = 52.dp, end = 26.dp, bottom = 26.dp, start = 26.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Analyze Report",
                fontFamily = poppins_medium,
                fontSize = 20.sp,
                color = Color.Black
            )
            Icon(
                imageVector = Icons.Filled.Close,
                tint = Color.Black,
                contentDescription = "Close Icon",
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            modalSheetState.hide()
                        }
                    }
            )
        }
        Divider()
        Spacer(modifier = Modifier.height(50.dp))
        Column {
            Text(
                "Disease Name :",
                fontFamily = poppins_bold,
                fontSize = 20.sp,
            )
            Text(
                state.diseaseName,
                fontFamily = poppins_regular,
                fontSize = 14.sp,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Text(
                "Description :",
                fontFamily = poppins_bold,
                fontSize = 20.sp,
            )
            Text(
                state.description,
                fontFamily = poppins_regular,
                fontSize = 14.sp,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Text(
                "Treatment :",
                fontFamily = poppins_bold,
                fontSize = 20.sp,
            )
            Text(
                state.treatment,
                fontFamily = poppins_regular,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
fun CameraPreviewScreen(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        factory = {
            PreviewView(it).apply {
                this.controller = controller
                controller.bindToLifecycle(lifecycleOwner)

            }
        },
        modifier = modifier
    )
}

fun takePictureToBitmap(
    context: Context,
    controller: LifecycleCameraController,
    onResult: (Bitmap?) -> Unit
) {
    val executor = ContextCompat.getMainExecutor(context)

    controller.takePicture(executor, object : ImageCapture.OnImageCapturedCallback() {
        override fun onCaptureSuccess(imageProxy: ImageProxy) {
            val bitmap = imageProxy.toBitmap()
            onResult(bitmap)
            imageProxy.close() // Important: close the image to free resources
        }

        override fun onError(exception: ImageCaptureException) {
            exception.printStackTrace()
            onResult(null)
        }
    })
}

@Composable
fun LineAnalyzer(modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offset by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 200f, // Adjust for desired movement distance
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500), // Adjust animation duration
            repeatMode = androidx.compose.animation.core.RepeatMode.Reverse
        ), label = ""
    )
    Box(modifier = modifier
        .fillMaxWidth()
        .offset(y = offset.dp)
        .height(4.dp)
        .drawBehind {

            // Custom drawing behind the content
            val path = Path().apply {
                moveTo(size.width / 2f, 0f)
                lineTo(size.width, size.height / 2f)
                lineTo(size.width / 2f, size.height)
                lineTo(0f, size.height / 2f)
                close()
            }

            // Draw the triangle path with a blue color
//            val color = colorResource(id = R.color.light_green) // Replace with your resource ID
            drawPath(path, Color.Green)
        }
    )

}

@Composable
fun AnalyzeCategoryCardItem(
    category: AnalyzeCategoryItem,
    onSelectChange: (AnalyzeCategoryItem) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Red,
            disabledContentColor = colorResource(id = R.color.light_grey),
            disabledContainerColor = colorResource(id = R.color.light_grey)
        ),
        border = BorderStroke(
            1.dp,
            colorResource(id = if (category.isSelected) R.color.dark_green else R.color.dark_grey)
        ),
        onClick = {
            onSelectChange(category.copy(isSelected = category.isSelected.not()))
        }
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 26.dp, vertical = 14.dp)
                .fillMaxWidth(),
            text = category.name,
            textAlign = TextAlign.Start,
            fontFamily = poppins_regular,
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = if (category.isSelected) colorResource(id = R.color.dark_green) else Color.Black
        )
    }
}

@Composable
fun BottomSheetLayout() {

//    var isSheetFullScreen by remember { mutableStateOf(false) }
//    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp
//
//    BackHandler(modalSheetState.isVisible) {
//        coroutineScope.launch { modalSheetState.hide() }
//    }
//
//    ModalBottomSheetLayout(
//        sheetState = modalSheetState,
//        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
//        sheetContent = {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
//            ) {
//                Button(
//                    onClick = {
//                        isSheetFullScreen = !isSheetFullScreen
//                    }
//                ) {
//                    Text(text = "Toggle Sheet Fullscreen")
//                }
//
//                Button(
//                    onClick = {
//                        coroutineScope.launch { modalSheetState.hide() }
//                    }
//                ) {
//                    Text(text = "Hide Sheet")
//                }
//            }
//        }
//    ) {
//        Scaffold { _ ->
//            Box(
//                modifier = Modifier
//                    .fillMaxSize(),
//                contentAlignment = Alignment.Center,
//            ) {
//                Button(
//                    onClick = {
//                        coroutineScope.launch {
//                            if (modalSheetState.isVisible)
//                                modalSheetState.hide()
//                            else
//                                modalSheetState.show()
//                        }
//                    },
//                ) {
//                    Text(text = "Open Sheet")
//                }
//            }
//        }
//    }
}