package whitebox;

import org.junit.jupiter.api.Test;

import application.Activity;
import application.TimeRegistration;

import static org.junit.jupiter.api.Assertions.*;

class TimeRegistrationTests {
    private TimeRegistration db = new TimeRegistration();
    
    @SuppressWarnings("unused")
	private Activity act = new Activity(); // Need to initialize list of activities

    @Test
    void testNegativeHours() {
        AssertionError exception = assertThrows(AssertionError.class, () -> db.registerTime("JD01", 10, "Code Review", -1));
        assertEquals("Invalid time registration", exception.getMessage());
    }
    
    @Test
    void testValidRegistration() {
        assertDoesNotThrow(() -> db.registerTime("JD01", 10, "Code Review", 5));
    }
    
    @Test
    void testWeekOutOfRange() {
    	AssertionError exception = assertThrows(AssertionError.class, () -> db.registerTime("JD01", 53, "Code Review", 5));
        assertEquals("Invalid time registration", exception.getMessage());
    }
    
    @Test
    void testInvalidActivity() {
    	AssertionError exception = assertThrows(AssertionError.class, () -> db.registerTime("JD01", 10, "Unknown Activity", 5));
        assertEquals("Activity does not exist", exception.getMessage());
    }
	
}

