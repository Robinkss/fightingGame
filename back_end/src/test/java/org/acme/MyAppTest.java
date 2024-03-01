package org.acme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyAppTest {

    @Test
    void title_p0() {
        MyApp myApp = new MyApp();
        assertEquals("##carmageddon", myApp.returnTitle("carmageddon", 0));
    }

    @Test
    void title_p2() {
        MyApp myApp = new MyApp();
        assertEquals("##carmageddon", myApp.returnTitle("carmageddon", 2));
    }

    @Test
    void title_p5() {
        MyApp myApp = new MyApp();
        assertEquals("#####carmageddon", myApp.returnTitle("carmageddon", 5));
    }

    @Test
    void title_maxValue() {
        MyApp myApp = new MyApp();
        assertEquals("#####carmageddon", myApp.returnTitle("carmageddon", Integer.MAX_VALUE));
    }

    @Test
    void title_minValue() {
        MyApp myApp = new MyApp();
        assertEquals("##carmageddon", myApp.returnTitle("carmageddon", Integer.MIN_VALUE));
    }

    @Test
    void title() {
        MyApp myApp = new MyApp();
        assertEquals("#####carmageddon", myApp.returnTitle());
    }

    @Test
    void returnTitle() {
        MyApp myApp = new MyApp();
        assertEquals("#####carmageddon", myApp.returnTitle("carmageddon", 5));
        assertEquals("##carmageddon", myApp.returnTitle("carmageddon", 2));
        assertEquals("#####carmageddon", myApp.returnTitle());
        assertEquals("##carmageddon", myApp.returnTitle("carmageddon", 0));
        assertEquals("##carmageddon", myApp.returnTitle("carmageddon", Integer.MIN_VALUE));
        assertEquals("#####carmageddon", myApp.returnTitle("carmageddon",  Integer.MAX_VALUE));

    }

    @Test
    void run() throws Exception {
        MyApp myApp = new MyApp();
        assertEquals(0, myApp.run());
    }
}