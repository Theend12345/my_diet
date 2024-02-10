package kjxv.dietmy.com.data.util

import kjxv.dietmy.com.data.entity.*
import kjxv.dietmy.com.domain.model.*

fun ArticleEntity.toModel() = ArticleModel(title, content, img, type)
fun DietEntity.toModel() = DietModel(title, content, img, type)
fun ExerciseEntity.toModel() = ExerciseModel(id, title, content, img, type, video, favorite)

fun ExerciseModel.toEntity() = ExerciseEntity(id, title, content, img, type, video, favorite)