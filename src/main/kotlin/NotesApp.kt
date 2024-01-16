class NotesApp {
    private val archives = mutableListOf<Archive>()

    private fun createArchive() {
        print("Введите имя архива: ")
        val name = readLine()?.trim() ?: ""
        if (name.isNotBlank()) {
            archives.add(Archive(name))
            println("Архив создан")
        } else {
            println("Имя архива не может быть пустым")
        }
    }

    private fun selectArchive() {
        if (archives.isEmpty()) {
            println("Нет доступных архивов")
            return
        }
        println("Список архивов: ")
        for ((index, archive) in archives.withIndex()) {
            println("$index. ${archive.name}")
        }
        println("${archives.size}. Выход")
        print("Выберите архив: ")
        val choice = readLine()?.toIntOrNull() ?: -1
        if (choice >= 0 && choice < archives.size) {
            val selectedArchive = archives[choice]
            val notesMenu = createNotesMenu(selectedArchive)
            notesMenu.run()
        } else if (choice == archives.size) {
            println("Выход")
        } else {
            println("Неправильный пункт меню.")
        }
    }

    private fun createNotesMenu(archive: Archive): Menu {
        val items = listOf(
            "Добавить заметку" to { addNoteToArchive(archive) },
            "Выбрать заметку" to { selectNote(archive) },
            "Выход" to { selectArchive() }
        )
        return Menu(items)
    }

    private fun addNoteToArchive(archive: Archive) {
        print("Введите имя заметки: ")
        val name = readLine()?.trim() ?: ""
        print("Введите текст заметки: ")
        val text = readLine()?.trim() ?: ""
        if (text.isNotBlank() && name.isNotBlank()) {
            val note = Note(name,text)
            archive.addNote(note)
            println("Заметка добавлена")
        } else {
            println("Текст или Имя заметки не может быть пустым")
        }
    }

    private fun selectNote(archive: Archive) {
        if (archive.notes.isEmpty()) {
            println("Архив не содержит заметок")
            return
        }
        println("Список заметок: ")
        for ((index, archive) in archive.notes.withIndex()) {
            println("$index. ${archive.name}")
        }
        println("${archive.notes.size}. Выход")
        print("Выберите заметку: ")
        val choice = readLine()?.toIntOrNull() ?: -1
        if (choice >= 0 && choice < archive.notes.size) {
            val selectedNote = archive.notes[choice]
            println("Текст заметки: ${selectedNote.text}")
        } else if (choice == archive.notes.size) {
            println("Назад.")
        } else {
            println("Неправильный пункт меню.")
        }
    }

    fun run() {
        val items = listOf(
            "Создать архив" to { createArchive() },
            "Выбрать архив" to { selectArchive() },
            "Выход" to { println("Выход."); System.exit(0) }
        )
        val mainMenu = Menu(items)
        mainMenu.run()
    }
}