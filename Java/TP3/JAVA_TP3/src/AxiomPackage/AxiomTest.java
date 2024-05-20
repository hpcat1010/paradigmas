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
        Axiom axiom = new Axiom();
        axiom.Command("i");
        assertEquals(1, axiom.currentSpeed());
    }

    @Test
    public void testDecreaseSpeed() {
        Axiom axiom = new Axiom();
        axiom.Command("s");
        assertEquals(0, axiom.currentSpeed());
    }

    @Test
    public void testIncreaseSpeedTwice() {
        Axiom axiom = new Axiom();
        axiom.Command("i");
        axiom.Command("i");
        assertEquals(2, axiom.currentSpeed());
    }

    @Test
    public void testDecreaseSpeedTwice() {
        Axiom axiom = new Axiom();
        axiom.Command("s");
        axiom.Command("s");
        assertEquals(0, axiom.currentSpeed());
    }

    @Test
    public void testTurnRight() {
        Axiom axiom = new Axiom();
        axiom.Command("r");
        assertEquals("East", axiom.currentDirection());
    }

    @Test
    public void testTurnLeft() {
        Axiom axiom = new Axiom();
        axiom.Command("l");
        assertEquals("West", axiom.currentDirection());
    }

    @Test
    public void testTurnRightThenLeft() {
        Axiom axiom = new Axiom();
        axiom.Command("r");
        axiom.Command("l");
        assertEquals("North", axiom.currentDirection());
    }

    @Test
    public void deplySonda() {
        Axiom axiom = new Axiom();
        axiom.Command("i");
        axiom.Command("d");
        assertTrue(axiom.currentSonda());
    }

    @Test
    public void retractSonda() {
        Axiom axiom = new Axiom();
        axiom.Command("i");
        axiom.Command("d");
        axiom.Command("f");
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testSondaCannotStop() {
        Axiom axiom = new Axiom();
        axiom.Command("i");
        axiom.Command("d");
        assertThrowsLike("Error Catasttrofico de la Sonda", () -> axiom.Command("s"));
    }

    @Test
    public void testSondaCannotTurnRight() {
        Axiom axiom = new Axiom();
        axiom.Command("i");
        axiom.Command("d");
        assertThrowsLike("Error Catasttrofico de la Sonda", () -> axiom.Command("r"));
    }

    @Test
    public void testSondaCannotTurnLeft() {
        Axiom axiom = new Axiom();
        axiom.Command("i");
        axiom.Command("d");
        assertThrowsLike("Error Catasttrofico de la Sonda", () -> axiom.Command("l"));
    }

    @Test
    public void testSondaCannotDeployWhenStopped() {
        Axiom axiom = new Axiom();
        assertThrowsLike("Error Catasttrofico de la Sonda", () -> axiom.Command("d"));
    }
    @Test
    public void testDeployAndRetractSonda() {
        Axiom axiom = new Axiom();
        axiom.Command("idfi");
        assertEquals(2, axiom.currentSpeed());
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testTwoCommands() {
        Axiom axiom = new Axiom();
        axiom.Command("ii");
        assertEquals(2, axiom.currentSpeed());

    }

    @Test
    public void testMultipleCommands() {
        Axiom axiom = new Axiom();
        axiom.Command("iirlrrllll");
        assertEquals(2, axiom.currentSpeed());
        assertEquals("South", axiom.currentDirection());
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testInvalidCommand() {
        Axiom axiom = new Axiom();
        axiom.Command("x");
        assertEquals(0, axiom.currentSpeed());
        assertEquals("North", axiom.currentDirection());
        assertFalse(axiom.currentSonda());
    }

    @Test
    public void testEmptyCommand() {
        Axiom axiom = new Axiom();
        axiom.Command("");
        assertEquals(0, axiom.currentSpeed());
        assertEquals("North", axiom.currentDirection());
        assertFalse(axiom.currentSonda());
    }
    @Test
    public void testAllCommands() {
        Axiom axiom = new Axiom();
        axiom.Command("iirlrrllliiiddididfrld");
        assertEquals(7, axiom.currentSpeed());
        assertEquals("West", axiom.currentDirection());
        assertTrue(axiom.currentSonda());
    }


    private static void assertThrowsLike(String ErrorMessage, Executable bodyToEval) {
        assertEquals(ErrorMessage,
                assertThrows(Exception.class, bodyToEval).getMessage());
    }
}
