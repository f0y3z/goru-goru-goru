#!/bin/bash
cd ../..
javac --module-path /usr/share/openjfx/lib --add-modules javafx.controls -d . game/cow/Cow.java game/core/Core.java
java --module-path /usr/share/openjfx/lib --add-modules javafx.controls game.core.Core

