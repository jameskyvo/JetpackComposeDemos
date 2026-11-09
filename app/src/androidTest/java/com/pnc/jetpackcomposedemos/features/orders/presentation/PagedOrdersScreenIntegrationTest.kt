package com.pnc.jetpackcomposedemos.features.orders.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import com.pnc.jetpackcomposedemos.HiltTestActivity
import com.pnc.jetpackcomposedemos.features.orders.presentation.pagedOrdersScreen
import com.pnc.jetpackcomposedemos.features.auth.domain.SessionManager
import com.pnc.jetpackcomposedemos.features.auth.domain.User
import com.pnc.jetpackcomposedemos.testutil.builders.aMyOrder
import com.pnc.jetpackcomposedemos.testutil.builders.aMyOrderLine
import com.pnc.jetpackcomposedemos.testutil.builders.aMyOrdersResponse
import com.pnc.jetpackcomposedemos.testutil.fakes.FakeMyOrdersDataSource
import com.pnc.jetpackcomposedemos.ui.theme.KazooTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class PagedOrdersScreenIntegrationTest {


    @get:Rule()
    val hiltRule = HiltAndroidRule(this)

    @get:Rule()
    val composeRule = createAndroidComposeRule<HiltTestActivity>()

    @Inject
    lateinit var session: SessionManager

    @Inject
    var ordersApi: FakeMyOrdersDataSource

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    private fun showScreen() {
        composeRule.setContent {
            KazooTheme {
                PagedOrdersScreen(useTwoPaneLayout = false)
            }
        }
    }

    private fun signInAs(id: String = "11000", name: String = "Cara Cole") {
        session.signIn(User(id = id, username = name, token = "test-token"))
    }

    // paging loads data on a background thread which compose cannot see
    private fun waitForText(text: String) {
        composeRule.onAllNodesWithText(text, useUnmergedTree = true)
            .fetchSemanticsNodes()
            .isNotEmpty()
    }

    private fun httpError(code: Int) = HttpException(

    )

    @Test
    fun givenSignedInCustomerWithOrders_whenScreenOpens_thenTheOrdersAreListed() {
        signInAs()
        ordersApi.response = aMyOrdersResponse(
            11000,
            aMyOrder(
                orderNumber = 1001, lines = listOf(aMyOrderLine(productName = "Road Bike"))),
                aMyOrder(orderNumber = 1002, lines = listOf(aMyOrderLine(lineId = 2, productName = "Helmet")))
        )

        showScreen()

        waitForText("Order #:1001")
        composeRule.onNodeWithText("Order #:1002").assertIsDisplayed()
        composeRule.onNodeWithText("Road Bike").assertIsDisplayed()
        composeRule.onNodeWithText("Helmet").assertIsDisplayed()


    }
}