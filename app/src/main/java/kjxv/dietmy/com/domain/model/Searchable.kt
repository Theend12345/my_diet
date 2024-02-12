package kjxv.dietmy.com.domain.model

import java.io.Serializable

interface Searchable: Serializable {
    fun searchField(): String
}