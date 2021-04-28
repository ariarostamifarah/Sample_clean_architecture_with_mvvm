package sample.arf.core.domain

object CategoryFactory {

    var cate_id = 0
    var cate_name = "AndroidTest"


    fun create(): Category = Category(cate_id, cate_name)

    fun createList(): List<Category> = listOf(

        Category(0, "AndroidTest11111111111"),
        Category(1, "AndroidTest22222222222"),
        Category(2, "AndroidTest33333333333"),
        Category(3, "AndroidTest44444444444")
    )

}