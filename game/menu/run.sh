#!/bin/bash

# Project directory
cd ~/Study/TLOU-Vater-Hotel/game/menu

# Compile
javac --module-path ~/Downloads/javafx-sdk-21.0.11/lib \
      --add-modules javafx.controls,javafx.fxml \
      GameMenu.java

# Run
java --module-path ~/Downloads/javafx-sdk-21.0.11/lib \
     --add-modules javafx.controls,javafx.fxml \
     GameMenu

