package kjxv.dietmy.com.domain.model


data class DietModel(
    val title: String,
    val content: String,
    val img: String,
    val type: String
): Searchable {
    override fun searchField() = title.trim()
}
