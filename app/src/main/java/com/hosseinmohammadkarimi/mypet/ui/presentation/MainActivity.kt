package com.hosseinmohammadkarimi.mypet.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hosseinmohammadkarimi.mypet.ui.presentation.register_user.RegisterScreen
import com.hosseinmohammadkarimi.mypet.ui.presentation.users_list.UsersListScreen
import com.hosseinmohammadkarimi.mypet.ui.theme.MyPetTheme
import com.hosseinmohammadkarimi.mypet.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPetTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.USERS_LIST
                ) {
                    composable(Routes.USERS_LIST) {
                        UsersListScreen(
                            onNavigate = {
                                navController.navigate(it.rout)
                            }
                        )
                    }
                    composable(Routes.REGISTER_USER) {
                        RegisterScreen(
                            onPopBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
