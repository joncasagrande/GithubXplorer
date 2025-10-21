package com.joncasagrande.githubxplorer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joncasagrande.githubxplorer.ui.fragment.HomeFragment
import com.joncasagrande.githubxplorer.ui.fragment.RepoDetailsFragment
import com.joncasagrande.githubxplorer.ui.model.GithubUi
import com.joncasagrande.githubxplorer.ui.theme.GithubXplorerTheme
import com.joncasagrande.githubxplorer.ui.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(500)
            keepSplashScreen = false
        }
        enableEdgeToEdge()
        setContent {
            GithubXplorerTheme {
                ScreenMain()
            }
        }
    }

    @Composable
    fun ScreenMain() {
        var githubUi: GithubUi? = null
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.Home.route) {
            // Home
            composable(Routes.Home.route) {
                // pass the navController
                HomeFragment(viewModel) {
                    githubUi = it
                    navController.navigate(Routes.ReposDescription.route)
                }
            }
            // Breed List
            composable(Routes.ReposDescription.route) {
                RepoDetailsFragment(navController, githubUi!!)
            }
        }
    }
}