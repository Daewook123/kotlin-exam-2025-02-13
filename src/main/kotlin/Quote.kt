@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.ll

import kotlinx.serialization.Serializable


@Serializable
class Quote(var id:Int, var content: String, var author: String) {

}