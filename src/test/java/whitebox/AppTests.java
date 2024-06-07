// Written by Maria
package whitebox;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.App;
import application.Employee;

class AppTests {

	private App app = new App(); 
	private Employee emp1 = new Employee("jdoe","JD01",true,10);
	
    @Test
    void testLoginSuccessful() {
        assertTrue(app.login(emp1.getID()));
        assertEquals(emp1.getID(), App.getCurrentUser());
    }

    @Test
    void testLoginFailedInvalidCredentials() {
        assertFalse(app.login("validUserButWrongPassword"));
        assertNull(App.getCurrentUser());
    }
    
    @Test
    void testLoginFailedUserDoesNotExist() {
        assertFalse(app.login("nonExistentUser"));
        assertNull(App.getCurrentUser());
    }
    
    @Test
    void testLogoutWhenUserLoggedIn() {
    	// Ensure login
    	app.login(emp1.getID());
    	assertDoesNotThrow(() -> App.logout());
        assertNull(App.getCurrentUser());
    }

    @Test
    void testLogoutWhenNoUserLoggedIn() {
        // Ensure no user is set
        App.setCurrentUser(null);
        AssertionError exception = assertThrows(AssertionError.class, () -> App.logout());
        
        assertEquals("No user is currently logged in", exception.getMessage());
    }
}
