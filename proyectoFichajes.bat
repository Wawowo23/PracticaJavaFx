@echo off
setlocal

:: Obtiene la ruta donde está este script (.bat)
set "APP_PATH=%~dp0"

:: Elimina la barra invertida final si la hay
set "APP_PATH=%APP_PATH:~0,-1%"

:: Lanza el .jar usando la ruta local del JavaFX
java --module-path "%APP_PATH%\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar "%APP_PATH%\fichajesJavaFX.jar"

endlocal
