package com.estebanposada.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.estebanposada.testapp.ui.detail.DetailScreen
import com.estebanposada.testapp.ui.resul.ResultScreen
import com.estebanposada.testapp.ui.search.SearchScreen
import com.estebanposada.testapp.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TestAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
                            val result = it.toRoute<Result>()
                            ResultScreen(result.query) { id ->
                                navController.navigate(Detail(id))
                            }
                        }
                        composable<Detail> {
                            val detail = it.toRoute<Detail>()
                            DetailScreen(detail.id)
                        }
                    }
                }
            }
        }
    }
}