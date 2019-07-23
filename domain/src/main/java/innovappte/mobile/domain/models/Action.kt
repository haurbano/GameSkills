package innovappte.mobile.domain.models

import java.io.Serializable

class Action: Serializable{
    lateinit var action: String
    lateinit var button: String
    var index: Int = 0
}