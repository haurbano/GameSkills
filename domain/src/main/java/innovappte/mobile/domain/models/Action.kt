package innovappte.mobile.domain.models

import java.io.Serializable

class Action: Serializable{
    lateinit var action: String
    var button: String? = null
    var index: Int = 0
}