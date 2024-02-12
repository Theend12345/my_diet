package kjxv.dietmy.com.domain.model


data class ExerciseModel(
    var id: Int = 0,
    val title: String,
    val content: String,
    val img: String,
    val type: String,
    var video: String? = null,
    var favorite: Boolean,
): Searchable {
    override fun searchField() = title.trim()
}
