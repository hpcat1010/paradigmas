package AxiomPackageV4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class AxiomTest {
    @Test
    public void testNewAxiomHasNoSpeed() {
        assertEquals(0, new Axiom().currentSpeed());
    }

    @Test
    public void testNewAxiomHasNoDirection() {
        assertEquals("North", new Axiom().currentDirection());
    }

    @Test
    public void testNewAxiomHasNoSonda() {
        assertFalse(new Axiom().currentSonda());
    }

    @Test
    public void testIncreaseSpeed() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("i");
        assertEquals(1, axiom.currentSpeed());
    }

    @Test
    public void testDecreaseSpeed() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("s");
        assertEquals(0, axiom.currentSpeed());
    }

    @Test
    public void testIncreaseSpeedTwice() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("i");
        axiom.ProcessMessage("i");
        assertEquals(2, axiom.currentSpeed());
    }

    @Test
    public void testDecreaseSpeedTwice() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("s");
        axiom.ProcessMessage("s");
        assertEquals(0, axiom.currentSpeed());
    }

    @Test
    public void testTurnRight() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("r");
        assertEquals("East", axiom.currentDirection());
    }

    @Test
    public void testTurnLeft() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("l");
        assertEquals("West", axiom.currentDirection());
    }

    @Test
    public void testTurnRightThenLeft() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("r");
        axiom.ProcessMessage("l");
        assertEquals("North", axiom.currentDirection());
    }

    @Test
    public void deplySonda() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("i");
        axiom.ProcessMessage("d");
        assertTrue(axiom.currentSonda());
    }

    @Test
    public void retractSonda() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("i");
        axiom.ProcessMessage("d");
        axiom.ProcessMessage("f");
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testSondaCannotStop() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("i");
        axiom.ProcessMessage("d");
        assertThrowsLike(() -> axiom.ProcessMessage("s"));
    }

    @Test
    public void testSondaCanSlowDown() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("i");
        axiom.ProcessMessage("i");
        axiom.ProcessMessage("d");
        axiom.ProcessMessage("s");
        assertEquals(1,axiom.currentSpeed());

    }

    @Test
    public void testSondaCannotTurnRight() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("i");
        axiom.ProcessMessage("d");
        assertThrowsLike(() -> axiom.ProcessMessage("r"));
    }

    @Test
    public void testSondaCannotTurnLeft() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("i");
        axiom.ProcessMessage("d");
        assertThrowsLike(() -> axiom.ProcessMessage("l"));
    }

    @Test
    public void testSondaCannotDeployWhenStopped() {
        Axiom axiom = new Axiom();
        assertThrowsLike(() -> axiom.ProcessMessage("d"));
    }
    @Test
    public void testDeployAndRetractSonda() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("idfi");
        assertEquals(2, axiom.currentSpeed());
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testTwoCommands() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("ii");
        assertEquals(2, axiom.currentSpeed());

    }

    @Test
    public void testMultipleCommands() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("iirlrrllll");
        assertEquals(2, axiom.currentSpeed());
        assertEquals("South", axiom.currentDirection());
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testInvalidCommand() {
        Axiom axiom = new Axiom();
        assertThrows(RuntimeException.class, () -> axiom.ProcessMessage("x"));
    }

    @Test
    public void testEmptyCommand() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("");
        assertEquals(0, axiom.currentSpeed());
        assertEquals("North", axiom.currentDirection());
        assertFalse(axiom.currentSonda());
    }
    @Test
    public void testAllCommands() {
        Axiom axiom = new Axiom();
        axiom.ProcessMessage("iirlrrllliiiddididfrld");
        assertEquals(7, axiom.currentSpeed());
        assertEquals("West", axiom.currentDirection());
        assertTrue(axiom.currentSonda());
    }


    private static void assertThrowsLike(Executable bodyToEval) {
        assertEquals( Axiom.ErrorSonda, assertThrows(Exception.class, bodyToEval).getMessage());
    }
}
