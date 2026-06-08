#!/bin/bash
cd ../..
javac --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml \
    -d . game/menu/GameMenu.java game/menu/PauseMenu.java


java --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml game.menu.GameMenu

