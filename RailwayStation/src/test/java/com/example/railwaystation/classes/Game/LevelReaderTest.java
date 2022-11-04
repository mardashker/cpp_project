package com.example.railwaystation.classes.Game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelReaderTest {

    @Test
    void loadLevel() {
        GameLevel lvl = LevelReader.loadLevel("/home/avenortoz/univ/term5/kpp_proj/cpp/cpp_project/RailwayStation/src/main/resources/com/example/railwaystation/assets/levels/sample.json");
    }
}