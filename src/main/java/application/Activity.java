// Written by Maria
package application;

import java.util.ArrayList;
import java.util.List;

// Maria
public class Activity {

	private String activityName;
	private float estimatedHours;
	private float spentHours;
	private int startWeek;
	private int endWeek;
	private static List<Activity> allActivities = new ArrayList<Activity>();

	public Activity() {
		initActivities();
	}

	public Activity(String activityName) throws Exception {
		throw new Exception("Estimated time is required for activity creation");
	}

	public Activity(String activityName, float estimatedHours, float spentHours, int startWeek, int endWeek) {
		new Activity(activityName, estimatedHours, spentHours);
		
		if(startWeek > 0 && endWeek < 53 && endWeek > startWeek) {
			this.startWeek = startWeek;
			this.endWeek = endWeek;
		}
	}
	
	public Activity(String activityName, float estimatedHours, float spentHours) {
		try {
			if (Float.compare(estimatedHours, 0) > 0 | Float.compare(spentHours, 0) >= 0) {
				this.activityName = activityName;
				this.estimatedHours = estimatedHours;
				this.spentHours = spentHours;
				allActivities.add(this);
			}
			throw new Exception("");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void initActivities() {
		try {
			Activity a1 = new Activity("User Interface Design", 30, 20);
			Activity a2 = new Activity("Code Review", 15, 10);
			Activity a3 = new Activity("Testing", 25, 25);
			allActivities.add(a1);
			allActivities.add(a2);
			allActivities.add(a3);
		} catch (Exception e) {
			System.out.println("Failed to initialize activities: " + e.getMessage());
		}
	}

	public float getSpentHours() { return spentHours; }
	public float getEstimatedHours() { return estimatedHours; }
	public String getActivityName() { return activityName; }
	public int getStartWeek() { return startWeek; }
    public int getEndWeek() { return endWeek; }
	public static List<Activity> getAllActivities() { return allActivities; }

	public void deleteActivityFromList() {
        allActivities.remove(this);
    }

	public Activity getActivityByName(String act) {

        for (Activity activity : allActivities) {
            if (activity.getActivityName().equals(act))
                return activity;
        }
        return null;
    }

	public boolean setEstimatedHours(float newHours) {
		if (newHours <= 0) {
			System.out.println("Estimated hours cannot be 0.");
			return false;
		}

		this.estimatedHours = newHours;
		return true;

	}

	public void setStartWeek(int sW)
	{
		this.startWeek = sW;
	}

	public void setEndWeek(int eW)
	{
		this.endWeek = eW;
	}

	public void updateHours(float addedHours) {
		this.spentHours += addedHours;
	}
	
	public void updateHoursBetter(Activity act, float addedHours) {
		this.spentHours += addedHours;
	}
	
}
