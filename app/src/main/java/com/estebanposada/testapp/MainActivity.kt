package com.estebanposada.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.estebanposada.testapp.ui.detail.DetailScreen
import com.estebanposada.testapp.ui.resul.ResultScreen
import com.estebanposada.testapp.ui.search.SearchScreen
import com.estebanposada.testapp.ui.theme.Banana
import com.estebanposada.testapp.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val canNavigateUp = remember { mutableStateOf(false) }
            TestAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(containerColor = Banana),
                            title = { Text(text = stringResource(id = R.string.app_name)) },
                            navigationIcon = {
                                if (canNavigateUp.value)
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                            contentDescription = "back"
                                        )
                                    }
                            }
                        )
                    }) { innerPadding ->
                    NavHost(
                        navController = navController, startDestination = Search,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(10.dp)
                    ) {
                        composable<Search> {
                            SearchScreen {
                                navController.navigate(Result(it))
                            }
                        }
                        composable<Result> {
                            canNavigateUp.value = navController.previousBackStackEntry != null
                            val result = it.toRoute<Result>()
                            ResultScreen(result.query) { id ->
                                navController.navigate(Detail(id))
                            }
                        }
                        composable<Detail> {
                            canNavigateUp.value = navController.previousBackStackEntry != null
                            val detail = it.toRoute<Detail>()
                            DetailScreen(detail.id)
                        }
                    }
                }
            }
        }
    }
}