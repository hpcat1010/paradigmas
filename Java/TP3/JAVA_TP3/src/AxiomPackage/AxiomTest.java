package AxiomPackage;

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
        Axiom axiom = AxiomWithSpeed1();
        assertEquals(1, axiom.currentSpeed());
    }

    @Test
    public void testDecreaseSpeed() {
        Axiom axiom = new Axiom();
        axiom.processMessage("s");
        assertEquals(0, axiom.currentSpeed());
    }

    @Test
    public void testIncreaseSpeedTwice() {
        Axiom axiom = AxiomWithSpeed1();
        axiom.processMessage("i");
        assertEquals(2, axiom.currentSpeed());
    }

    @Test
    public void testDecreaseSpeedTwice() {
        Axiom axiom = new Axiom();
        axiom.processMessage("s");
        axiom.processMessage("s");
        assertEquals(0, axiom.currentSpeed());
    }

    @Test
    public void testTurnRight() {
        Axiom axiom = new Axiom();
        axiom.processMessage("r");
        assertEquals("East", axiom.currentDirection());
    }

    @Test
    public void testTurnLeft() {
        Axiom axiom = new Axiom();
        axiom.processMessage("l");
        assertEquals("West", axiom.currentDirection());
    }

    @Test
    public void testTurnRightThenLeft() {
        Axiom axiom = new Axiom();
        axiom.processMessage("r");
        axiom.processMessage("l");
        assertEquals("North", axiom.currentDirection());
    }

    @Test
    public void deplySonda() {
        Axiom axiom = AxiomWithSpeed1();
        axiom.processMessage("d");
        assertTrue(axiom.currentSonda());
    }

    @Test
    public void retractSonda() {
        Axiom axiom = AxiomWithSpeed1();
        axiom.processMessage("d");
        axiom.processMessage("f");
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testSondaCannotStop() {
        Axiom axiom = AxiomWithSpeed1();
        axiom.processMessage("d");
        assertThrowsLike(() -> axiom.processMessage("s"));
    }

    @Test
    public void testSondaCanSlowDown() {
        Axiom axiom = AxiomWithSpeed1();
        axiom.processMessage("i");
        axiom.processMessage("d");
        axiom.processMessage("s");
        assertEquals(1, axiom.currentSpeed());
    }

    @Test
    public void testSondaCannotTurnRight() {
        Axiom axiom = AxiomWithSpeed1();
        axiom.processMessage("d");
        assertThrowsLike(() -> axiom.processMessage("r"));
    }

    @Test
    public void testSondaCannotTurnLeft() {
        Axiom axiom = AxiomWithSpeed1();
        axiom.processMessage("d");
        assertThrowsLike(() -> axiom.processMessage("l"));
    }

    @Test
    public void testSondaCannotDeployWhenStopped() {
        assertThrowsLike(() -> new Axiom().processMessage("d"));
    }

    @Test
    public void testDeployAndRetractSonda() {
        Axiom axiom = new Axiom();
        axiom.processMessage("idfi");
        assertEquals(2, axiom.currentSpeed());
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testTwoCommands() {
        Axiom axiom = new Axiom();
        axiom.processMessage("ii");
        assertEquals(2, axiom.currentSpeed());

    }

    @Test
    public void testMultipleCommands() {
        Axiom axiom = new Axiom();
        axiom.processMessage("iirlrrllll");
        assertEquals(2, axiom.currentSpeed());
        assertEquals("South", axiom.currentDirection());
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testInvalidCommand() {
        assertThrows(RuntimeException.class, () -> new Axiom().processMessage("x"));
    }

    @Test
    public void testEmptyCommand() {
        Axiom axiom = new Axiom();
        axiom.processMessage("");
        assertEquals(0, axiom.currentSpeed());
        assertEquals("North", axiom.currentDirection());
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testAllCommands() {
        Axiom axiom = new Axiom();
        axiom.processMessage("iirlrrllliiiddididfrld");
        assertEquals(7, axiom.currentSpeed());
        assertEquals("West", axiom.currentDirection());
        assertTrue(axiom.currentSonda());
    }

    private static Axiom AxiomWithSpeed1() {
        Axiom axiom = new Axiom();
        axiom.processMessage("i");
        return axiom;
    }

    private static void assertThrowsLike(Executable bodyToEval) {
        assertEquals(Axiom.ErrorSonda, assertThrows(Exception.class, bodyToEval).getMessage());
    }
}
