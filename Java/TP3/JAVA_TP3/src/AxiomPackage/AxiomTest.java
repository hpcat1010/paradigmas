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
        axiom.Comand('i');
        assertEquals(1, axiom.currentSpeed());
    }
    @Test
    public void testDecreaseSpeed() {
        Axiom axiom = new Axiom();
        axiom.Comand('s');
        assertEquals(0, axiom.currentSpeed());
    }
    @Test
    public void testIncreaseSpeedTwice() {
        Axiom axiom = new Axiom();
        axiom.Comand('i');
        axiom.Comand('i');
        assertEquals(2, axiom.currentSpeed());
    }
    @Test
    public void testDecreaseSpeedTwice() {
        Axiom axiom = new Axiom();
        axiom.Comand('s');
        axiom.Comand('s');
        assertEquals(0, axiom.currentSpeed());
    }
    @Test
    public void testTurnRight() {
        Axiom axiom = new Axiom();
        axiom.Comand('r');
        assertEquals("East", axiom.currentDirection());
    }
    @Test
    public void testTurnLeft() {
        Axiom axiom = new Axiom();
        axiom.Comand('l');
        assertEquals("West", axiom.currentDirection());
    }
    @Test
    public void testTurnRightThenLeft() {
        Axiom axiom = new Axiom();
        axiom.Comand('r');
        axiom.Comand('l');
        assertEquals("North", axiom.currentDirection());
    }
    @Test
    public void deplySonda() {
        Axiom axiom = new Axiom();
        axiom.Comand('i');
        axiom.Comand('d');
        assertTrue(axiom.currentSonda());
    }
    @Test
    public void retractSonda() {
        Axiom axiom = new Axiom();
        axiom.Comand('i');
        axiom.Comand('d');
        axiom.Comand('f');
        assertFalse(axiom.currentSonda());
    }
    @Test
    public void testSondaCannotStop() {
        Axiom axiom = new Axiom();
        axiom.Comand('i');
        axiom.Comand('d');
        assertThrowsLike("Error Catasttrofico de la Sonda", () -> axiom.Comand('s'));
    }
    @Test
    public void testSondaCannotTurnRight() {
        Axiom axiom = new Axiom();
        axiom.Comand('i');
        axiom.Comand('d');
        assertThrowsLike("Error Catasttrofico de la Sonda", () -> axiom.Comand('r'));
    }
    @Test
    public void testSondaCannotTurnLeft() {
        Axiom axiom = new Axiom();
        axiom.Comand('i');
        axiom.Comand('d');
        assertThrowsLike("Error Catasttrofico de la Sonda", () -> axiom.Comand('l'));
    }
    @Test
    public void testSondaCannotDeployWhenStopped() {
        Axiom axiom = new Axiom();
        assertThrowsLike("Error Catasttrofico de la Sonda", () -> axiom.Comand('d'));
    }
    private static void assertThrowsLike(String ErrorMessage, Executable bodyToEval) {
        assertEquals(ErrorMessage,
                assertThrows(Exception.class, bodyToEval).getMessage());
    }
}
