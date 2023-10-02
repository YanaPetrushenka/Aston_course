package org.aston;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {

    private App app = new App();

    @Test
    public void testApp() {
        String expectedMessage = "HelloWorld";
        Assertions.assertEquals(expectedMessage, app.getHelloWorld());
    }
}
