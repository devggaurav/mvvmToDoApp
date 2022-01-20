package com.gc.gcmvvmtodo.util

//
// Created by gaurav on 14/01/22.
// Copyright (c) 2022 gc. All rights reserved.
//

sealed class UIEvent {

    object PopBackStack : UIEvent()

    data class Navigate(val route: String) : UIEvent()

    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ) : UIEvent()

}
