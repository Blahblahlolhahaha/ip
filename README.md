# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   # SadStudent (Duke) — User Guide

   This repository contains the SadStudent (Duke) Java application — a simple task manager with a graphical JavaFX UI.

   **Contents of this guide**
   - Quick start (build & run)
   - Using the GUI
   - Commands supported (text UI)
   - Styling and customization
   - Troubleshooting
   - Contributing

   ## Quick start

   Prerequisites: JDK 17 and Gradle wrapper (included).

   From a terminal in the project root run:

   ```bash
   ./gradlew build
   ./gradlew run
   ```

   On Windows PowerShell use:

   ```powershell
   .\gradlew build
   .\gradlew run
   ```

   You can also open the project in IntelliJ IDEA and run the `Main` class (FXML-based GUI) or run Gradle tasks from the IDE.

   ## Using the GUI

   - The main window shows a scrollable conversation view and an input field at the bottom.
   - Type a command in the input box and press Enter or click the `Send` button.
   - Replies appear as dialog bubbles; avatars show on either side to indicate user vs SadStudent.

   Tips to avoid horizontal scrolling:
   - The dialog view auto-wraps long messages and fits to the window width. Resize the window to see more content.

   ## Commands (text-based)

   The application supports the same commands used by the text UI. Typical commands include:

   - `todo <description>` — create a todo task
   - `deadline <description> /by <date/time>` — create a deadline task
   - `event <description> /from <date/time> /to <date/time>` — create an event
   - `list` — list existing tasks
   - `delete <index>` — delete task at index
   - `mark <index>` / `unmark <index>` — mark/unmark tasks as done
   - `find <keyword>` — search tasks by keyword
   - `priority <index> <priority>` — set a numeric priority for the task at `<index>` (e.g. `priority 2 3` sets task 2 to priority 3)
   - `bye` — exit the application

   Refer to the tests in `test/java/sadstudent/task` and `test/java/sadstudent/parser` for more examples of supported input formats.

   ## Styling and customization

   - The JavaFX stylesheet is located at `src/main/resources/styles/style.css`.
   - Edit that file to change fonts, colors, and dialog bubble styling. The stylesheet is loaded in `Main.java`.
   - Dialog sizing and wrapping are handled in `DialogBox.java` and `MainWindow.java`.

   ## Troubleshooting

   - Build errors: ensure you are using JDK 17. Check `./gradlew --version` and `java -version`.
   - UI not appearing: run `./gradlew run` from the project root or run the `Main` class from your IDE.
   - CSS changes not visible: the CSS is packaged from `src/main/resources`; rebuild with `./gradlew build` after edits.

   ## Contributing

   - Fork the repository and open a pull request with focused changes.
   - Keep UI changes in `src/main/resources/view` and styles in `src/main/resources/styles`.

   If you want, I can expand any section (examples, screenshots, or step-by-step IDE setup).
