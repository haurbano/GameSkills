package com.hamilton.gamesskillst.domain.models

import java.io.Serializable

data class Action(
    val action: String,
    val button: String,
    val index: Int
): Serializable