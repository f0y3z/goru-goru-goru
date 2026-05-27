#!/bin/bash


# Compile
javac --module-path /usr/share/openjfx/lib \
      --add-modules javafx.controls,javafx.fxml \
      GameMenu.java

# Run
java --module-path /usr/share/openjfx/lib \
     --add-modules javafx.controls,javafx.fxml \
     GameMenu

