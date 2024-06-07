// Written by Maria
package application;

import java.util.*;

public class TimeRegistration {
	
	private static Map<String, Map<Integer, Map<String, Float>>> registrationDatabase = new HashMap<>();
	
	public TimeRegistration() {
		// List of employee IDs
        String[] employeeIds = {"JD01", "AS02", "BW03", "CW04", "DJ05"};

        // Data for each employee
        for (String id : employeeIds) {
            Map<Integer, Map<String, Float>> weekData = new HashMap<>();

            // Create sample data for different weeks
            weekData.put(10, createActivityData("User Interface Design2", 0.0f));
            weekData.put(2, createActivityData("Meetings", 15.0f));
            weekData.put(3, createActivityData("Testing", 25.0f));

            // Put week data in the main database for this employee
            registrationDatabase.put(id, weekData);
        }
	}
	
	// Helper method to create a map of activities and hours
    private static Map<String, Float> createActivityData(String activity, Float hours) {
        Map<String, Float> activityData = new HashMap<>();
        activityData.put(activity, hours);
        return activityData;
    }

    /**
     * Registers or updates time for a specific activity in a given week for an employee.
     * @param employeeId The unique ID of the employee.
     * @param week The calendar week.
     * @param activity The name of the activity.
     * @param hours The number of hours to register.
     * @throws Exception 
     */
    public void registerTime(String employeeId, int week, String activity, float hours) throws Exception {
    	// Preconditions
    	System.out.println(hours);
        assert hours > 0 : "Invalid time registration";
        assert week >= 1 && week <= 52 : "Invalid time registration";
    	if (hours < 0 | week < 1 | week > 52) throw new IllegalArgumentException("Invalid time registration");
    	
    	// Get or create the nested maps for the employee and week.
        Map<Integer, Map<String, Float>> weeks = registrationDatabase.computeIfAbsent(employeeId, k -> new HashMap<>());
        Map<String, Float> activities = weeks.computeIfAbsent(week, k -> new HashMap<>());

        // Safely update the time for the activity
        float currentHours = activities.getOrDefault(activity, 0.0f);
        float newTotalHours = currentHours + hours;
        activities.put(activity, newTotalHours);
        
        // Assert postcondition: Time added correctly
        assert activities.get(activity) == newTotalHours : "Time should be added correctly";
        
        // Add or update the time for the activity.
        List<Activity> acts = Activity.getAllActivities();
        boolean found = false;
        for (Activity a : acts) {
        	if (a.getActivityName().equals(activity)) {
        		a.updateHours(hours);
        		found = true;
        	}
        }
        
        assert found : "Activity does not exist";
    	if (!found) 
    	    throw new IllegalArgumentException("Activity does not exist");
    }
    
    /**
     * Retrieves the registered time for a specific activity in a given week for an employee.
     * @param employeeId The unique ID of the employee.
     * @param week The calendar week.
     * @param activity The name of the activity.
     * @return The number of hours registered, or 0 if none.
     */
    public float getTime(String employeeId, int week, String activity) {
        return registrationDatabase.getOrDefault(employeeId, new HashMap<>())
                       .getOrDefault(week, new HashMap<>())
                       .getOrDefault(activity, (float) 0);
    }
    
    /**
     * Retrieves all registered times for a specific week for an employee.
     * @param employeeId The unique ID of the employee.
     * @param week The calendar week.
     * @return A map of activities and their corresponding registered hours for that week.
     */
    public Map<String, Float> getTimesForWeek(String employeeId, int week) {
        return new HashMap<>(registrationDatabase.getOrDefault(employeeId, new HashMap<>()).getOrDefault(week, new HashMap<>()));
    }
    
    /**
     * Returns a formatted string representation of all registered times for a specific employee and week.
     * This can be used to display the complete time registrations in a user-friendly format.
     * 
     * @param employeeId The unique ID of the employee.
     * @param week The calendar week.
     * @return A formatted string detailing all activities and their hours for the specified week.
     */
    public String viewTimeRegistrations(String employeeId, int week) {
        Map<String, Float> weekRegistrations = getTimesForWeek(employeeId, week);
        if (weekRegistrations.isEmpty()) {
            return "No time registrations for week " + week;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Time Registrations for Week ").append(week).append(":\n");
        weekRegistrations.forEach((activity, hours) ->
            sb.append("Activity: ").append(activity).append(", Hours: ").append(hours).append("\n"));
        return sb.toString();
    }

}
