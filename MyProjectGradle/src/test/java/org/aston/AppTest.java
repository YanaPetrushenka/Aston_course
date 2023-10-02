package org.aston;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private App app = new App();

    @Test
    void getHelloWorld() {
        var expectedMessage = "HelloWorld";
        assertEquals(expectedMessage, app.getHelloWorld());
    }
}