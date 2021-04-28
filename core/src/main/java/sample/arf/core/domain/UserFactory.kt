package sample.arf.core.domain

object UserFactory {

    val id: Long = 0
    val name: String = "NameTest"
    val email: String = "emailTest@Test.com"
    val country: String = "countryTest"
    val data: String = "DateTest"
    val category_id: Int = 1


    fun create() = User(id, name, email, country, data, category_id)

    fun createList() = listOf<User>(

        User(0, "NameTest111", "emailTest@Test.com111", "countryTest111", "DateTest111", 1),
        User(1, "NameTest222", "emailTest@Test.com222", "countryTest222", "DateTest222", 1),
        User(2, "NameTest333", "emailTest@Test.com333", "countryTest333", "DateTest333", 2),
        User(3, "NameTest444", "emailTest@Test.com444", "countryTest444", "DateTest444", 1)
    )

}