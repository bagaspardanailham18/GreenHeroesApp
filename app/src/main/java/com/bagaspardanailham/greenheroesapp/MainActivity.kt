package com.bagaspardanailham.greenheroesapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bagaspardanailham.greenheroesapp.data.model.bottomNavItems
import com.bagaspardanailham.greenheroesapp.presentation.Screen
import com.bagaspardanailham.greenheroesapp.presentation.home.AnalyzeScreen
import com.bagaspardanailham.greenheroesapp.presentation.home.CommunityScreen
import com.bagaspardanailham.greenheroesapp.presentation.home.HomeScreen
import com.bagaspardanailham.greenheroesapp.presentation.home.ShopScreen
import com.bagaspardanailham.greenheroesapp.presentation.home.VideoScreen
import com.bagaspardanailham.greenheroesapp.presentation.vm.ShopVM
import com.bagaspardanailham.greenheroesapp.ui.theme.GreenHeroesAppTheme
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_medium
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class MainActivity : ComponentActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()

    init {
        injectContentInActivityKoin()
    }

    private val shopViewModel: ShopVM by viewModel()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(5000)
            keepSplashScreen = false
        }
        if (!hasCameraPermission()) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), 0
            )
        }
        setContent {
            GreenHeroesAppTheme {
                val rootNavController = rememberNavController()
                val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

                val homeNavController = rememberNavController()
                val homeNavBackStackEntry by homeNavController.currentBackStackEntryAsState()

                val routeBottomNavVisible = listOf("home", "shop", "community", "video")

                Scaffold(
                    bottomBar = {
                        if (routeBottomNavVisible.contains(navBackStackEntry?.destination?.route)) {
                            NavigationBar(
                                modifier = Modifier,
                                containerColor = Color.White,
                                tonalElevation = 2.dp
                            ) {
                                bottomNavItems.forEach { item ->
                                    val isSelected =
                                        item.title.lowercase() == navBackStackEntry?.destination?.route
                                    NavigationBarItem(
                                        selected = isSelected,
                                        label = {
                                            Text(
                                                fontSize = 11.sp,
                                                fontFamily = poppins_medium,
                                                text = item.title
                                            )
                                        },
                                        icon = {
                                            (if (isSelected) item.selectedIcon else item.unselectedIcon)?.let {
                                                Icon(
                                                    imageVector = it,
                                                    contentDescription = item.title
                                                )
                                            }
                                        },
                                        enabled = item.enable,
                                        colors = NavigationBarItemColors(
                                            selectedTextColor = Color.Black,
                                            unselectedTextColor = Color.LightGray,
                                            selectedIconColor = if (item.title == "Analyze") Color.Transparent else Color.Black,
                                            unselectedIconColor = if (item.title == "Analyze") Color.Transparent else Color.LightGray,
                                            selectedIndicatorColor = Color.Transparent,
                                            disabledIconColor = if (item.title == "Analyze") Color.Transparent else Color.Red,
                                            disabledTextColor = if (item.title == "Analyze") Color.LightGray else Color.Red
                                        ),

                                        onClick = {
                                            if (item.title != "Analyze") {
                                                rootNavController.navigate(item.title.lowercase()) {
                                                    popUpTo(rootNavController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        if (routeBottomNavVisible.contains(navBackStackEntry?.destination?.route)) {
                            Box {
                                FloatingActionButton(
                                    shape = CircleShape,
                                    contentColor = Color.Black,
                                    containerColor = colorResource(id = R.color.light_green),
                                    onClick = {
//                                    val intent =
//                                        Intent(this@MainActivity, AnalyzeActivity::class.java)
//                                    startActivity(intent)
                                        rootNavController.navigate("analyze") {
                                            popUpTo(rootNavController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    modifier = Modifier.offset(y = 45.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.DocumentScanner,
                                        contentDescription = "Analyze"
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { _ ->
                    NavHost(navController = rootNavController, startDestination = "home") {
                        composable("home") {
                            HomeNavHost(homeNavController)
                        }
                        composable("shop") {
                            ShopNavHost(shopViewModel)
                        }
                        composable("analyze") {
                            AnalyzeNavHost()
                        }
                        composable("community") {
                            CommunityNavHost()
                        }
                        composable("video") {
                            VideoNavHost()
                        }
                    }
                }
            }
        }
    }
    private fun hasCameraPermission() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}

@Composable
fun HomeNavHost(
    homeNavController: NavHostController,
) {
    NavHost(
        navController = homeNavController,
        startDestination = Screen.HomeScreen
    ) {
        composable<Screen.HomeScreen> {
            HomeScreen()
        }
    }
}

@Composable
fun ShopNavHost(
    shopVM: ShopVM
) {
    ShopScreen(shopVM)
}

@Composable
fun AnalyzeNavHost() {
    val analyzeNavController = rememberNavController()
    val analyzeNavBackStackEntry by analyzeNavController.currentBackStackEntryAsState()

    NavHost(
        navController = analyzeNavController,
        startDestination = Screen.AnalyzeScreen
    ) {
        composable<Screen.AnalyzeScreen> {
            val context = LocalContext.current.applicationContext
            val controller = remember {
                LifecycleCameraController(context).apply {
                    setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
//                    setImageAnalysisAnalyzer(
//                        ContextCompat.getMainExecutor(context),
//                        analyzer
//                    )
                }
            }
            AnalyzeScreen(controller)
        }
    }
}

@Composable
fun CommunityNavHost() {
    CommunityScreen()
}

@Composable
fun VideoNavHost() {
    VideoScreen()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreenHeroesAppTheme {

    }
}