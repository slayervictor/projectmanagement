// Laurits
package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProjectDatabase {
	private static ProjectDatabase instance;
	private static Map<String, ArrayList<Activity>> projectAct = new HashMap<>();
	private static Map<String, Project> projects = new HashMap<>();
	private ObservableList<String> projectItems = FXCollections.observableArrayList();

    public static Map<String, String> actString = new HashMap<>();

    //public ObservableList<String> actString = FXCollections.observableArrayList();


	public ProjectDatabase() {
		projects = new HashMap<>();
		try (Scanner scanner = new Scanner(new File("src/main/java/application/projects.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (line.isEmpty())
					throw new IOException("Empty line in database file");

				// Find the index of the first comma
				int firstCommaIndex = line.indexOf(',');

				// Extract the project name and the rest of the data
				String projectName = line.substring(0, firstCommaIndex).trim();
				String[] parts = line.substring(firstCommaIndex + 1).split(",");

				if (parts.length >= 3) {
					// String createdBy = parts[1];
					// String projectLeader = parts[2];
					int startWeek = Integer.parseInt(parts[0]);
					int endWeek = Integer.parseInt(parts[1]);
					boolean completed = Boolean.parseBoolean(parts[2]);
					projects.put(projectName, new Project(projectName, startWeek, endWeek, completed));
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void setActivities(String p, ArrayList<Activity> actList) {
	 * projectAct.put(p, actList); }
	 */
	public void setActivity(String p, Activity act) {
		if (projectAct.get(p) == null) {
			ArrayList<Activity> temp = new ArrayList<Activity>();
			temp.add(act);
			projectAct.put(p, temp);

		} else {
			ArrayList<Activity> temp = projectAct.get(p);
			temp.add(act);
			projectAct.put(p, temp);

		}
    }
	
    public static ArrayList<Activity> getActivities(String p) {
        return projectAct.get(p);
    }

    public ArrayList<Activity> getActivitiesNonStatic(String p) {
        ArrayList<Activity> act = getActivities(p);
        return act;
    }

    public static ProjectDatabase getInstance() {
        if (instance == null) {
            synchronized (ProjectDatabase.class) {
                if (instance == null) {
                    instance = new ProjectDatabase();
                }
            }
        }
        return instance;
    }
    public void deleteActivity(String projectName, String activityName) throws Exception {
        ArrayList<Activity> activities = projectAct.get(projectName);
        Activity activity = activities.get(0).getActivityByName(activityName);
        if (activities != null) {
            activity.deleteActivityFromList();
            activities.removeIf(projectAct -> activity.getActivityName().equals(activityName));
        }
    }

	public Project getProjectByName(String projectName) {
		return projects.get(projectName);
	}

	public static Project createNewProject(String projectName, int startWeek, int endWeek) throws Exception {
		if (startWeek > endWeek)
			throw new Exception("Start week must be before end week.");

		if (!projects.containsKey(projectName)) {
            projects.put(projectName, new Project(projectName, startWeek, endWeek, false));  
            
            // Save the project to the file
            try (FileWriter writer = new FileWriter("src/main/java/application/projects.txt", true)) { 
                writer.write(projectName + "," + startWeek + "," + endWeek + "," + "false" + "\n"); 
                
            } catch (IOException e) {
                throw new RuntimeException("Failed to write to projects.txt", e);
            }
            
            return projects.get(projectName);
        }
        else
            throw new Exception("Project with name: " + projectName + " already exists.");
    }

	public static void updateProject(String project, Project updatedProject) {
		projects.replace(project, updatedProject);
	}

	public static void deleteProject(String projectName, int currentWeek) throws Exception {
		Project project = projects.get(projectName);
		if (projectName.equals("Vacation / Sickness")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Vacation / Sickness cannot be deleted.");
			alert.show();
		} else if (currentWeek > project.getStartWeek()) {
			throw new Exception("Project cannot be deleted after it has started");
		} else {
			projects.remove(projectName);

			// Read all lines from the file
			File file = new File("src/main/java/application/projects.txt");
			List<String> out = Files.lines(Paths.get(file.toURI())).filter(line -> !line.startsWith(projectName + ","))
					.collect(Collectors.toList());
			System.out.println(projectName);

			// Write remaining projects back to the file
			try (FileWriter writer = new FileWriter(file, false)) { // false to overwrite the file
				for (String line : out) {
					writer.write(line + System.lineSeparator());
				}
			} catch (IOException e) {
				throw new RuntimeException("Failed to update projects.txt", e);
			}
		}
	}

	public ArrayList<String> getProjectNamesArrayList() {

		ArrayList<String> projectNameList = new ArrayList<String>();
		for (String projectName : projects.keySet()) {
			projectNameList.add(projectName);
		}

		return projectNameList;
	}

	public ObservableList<String> getProjectNames() {

		ObservableList<String> projectNameList = FXCollections.observableArrayList();
		for (String projectName : projects.keySet()) {
			projectNameList.add(projectName);
		}

		return projectNameList;
	}

	public ObservableList<String> getObservableActivities(String p) {
		ObservableList<String> activityNameList = FXCollections.observableArrayList();
		ArrayList<Activity> arr = getActivities(p);

		if (getActivities(p) == null) {
			return activityNameList;

		} else {
			for (Activity act : arr) {
				activityNameList.add(act.getActivityName());
			}
			return activityNameList;
		}
	}
}
