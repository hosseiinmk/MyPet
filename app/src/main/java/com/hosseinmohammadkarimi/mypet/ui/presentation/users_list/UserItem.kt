package com.hosseinmohammadkarimi.mypet.ui.presentation.users_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hosseinmohammadkarimi.mypet.R
import com.hosseinmohammadkarimi.mypet.data.model.User

@Composable
fun UserItem(
    user: User,
    onEvent: (UsersListEvent) -> Unit,
    modifier: Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = user.username)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = user.phoneNumber)
        }
        IconButton(
            onClick = {
                onEvent(UsersListEvent.OnDeleteUser(user))
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "Delete Icon"
            )
        }
    }
}