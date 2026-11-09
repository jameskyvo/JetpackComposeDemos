package com.pnc.jetpackcomposedemos.features.auth.presentation.viewmodels

import com.pnc.jetpackcomposedemos.features.auth.domain.LoginUseCase
import com.pnc.jetpackcomposedemos.features.auth.domain.RecordingAuthRepository
import com.pnc.jetpackcomposedemos.features.auth.presentation.LoginUiState
import com.pnc.jetpackcomposedemos.features.auth.presentation.LoginViewModel
import com.pnc.jetpackcomposedemos.testutil.MainDispatcherRule
import com.pnc.jetpackcomposedemos.testutil.builders.aUser
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    // When using this rule approach instead of @Before and @After,
    // Do NOT create the ViewModel in a class constructor or property initializer,
    // Those execute before the test starting method in our main dispatcher rule
    // Create the ViewModel inside the test or in its own @Before function
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `given valid credentials, when logging in, then state is success`() = runTest {

        val user = aUser()
        val repo = RecordingAuthRepository(result = Result.success(user))
        val viewModel = LoginViewModel(LoginUseCase(repo))

        viewModel.login("hello", "world")
        advanceUntilIdle() // wait until the coroutine launched by login() is finished

        assertEquals(LoginUiState.Success(user), viewModel.uiState.value)
    }
}