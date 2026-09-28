package com.pnc.jetpackcomposedemos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pnc.jetpackcomposedemos.ui.theme.JetpackComposeDemosTheme

@Composable
fun BoardMemberCard(boardMember: BoardMember) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = "${boardMember.firstName} ${boardMember.lastName}",
                style = MaterialTheme.typography.titleMedium
            )
            Text("Id : ${boardMember.id}")
            Text("Title : ${boardMember.title}")
            Text("Gender : ${boardMember.gender}")
            Text("Description : ${boardMember.description}")
        }
    }
}

@Preview
@Composable
fun BoardMemberCardPreview() {
    JetpackComposeDemosTheme {
        val boardMember = BoardMember(
            id = 1,
            firstName = "John",
            lastName = "Normal",
            title = "Employee",
            gender = "Male",
            photoUrl = "",
            description = "Just a regular guy"
        )
        BoardMemberCard(boardMember)
    }
}