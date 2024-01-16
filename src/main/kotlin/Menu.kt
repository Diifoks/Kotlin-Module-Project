class Menu(private val items: List<Pair<String, () -> Unit>>) {

    fun displayMenu() {
        for ((index, menuItem) in items.withIndex()) {
            println("$index. ${menuItem.first}")
        }
    }

    fun run() {
        var exit = false

        while (!exit) {
            displayMenu()

            var input: String?
            var selectedItem: Int? = null
            val n = items.size - 1

            // Цикл проверки ввода
            while (selectedItem == null || selectedItem < 0 || selectedItem > n) {
                print("Введите номер пункта: ")
                input = readLine()
                selectedItem = input?.toIntOrNull()

                if (selectedItem == null) {
                    println("Некорректный ввод. Пожалуйста, введите целое число.")
                } else if (selectedItem < 0 || selectedItem > n) {
                    println("Введите число в соответствии с пунктами меню")
                }

                if (selectedItem == 2) {
                    exit = true
                }
            }

            items[selectedItem].second()
        }
    }
}