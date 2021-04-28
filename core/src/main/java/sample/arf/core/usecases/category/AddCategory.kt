package sample.arf.core.usecases.category

import sample.arf.core.data.repo.RepoCategory
import sample.arf.core.domain.Category
import javax.inject.Inject

class AddCategory(private val repoCategory: RepoCategory) {

    suspend operator fun invoke(category: Category) = repoCategory.Add(category)
}