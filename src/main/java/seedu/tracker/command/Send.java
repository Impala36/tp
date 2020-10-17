package seedu.tracker.command;

import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;
import seedu.tracker.sendEmail.SendEmail;
import seedu.tracker.ui.Ui;

public class Send extends Command{
    public static final String word = "--send";

    public Send(String line, ProjectList projects, Ui ui){
        super(line, projects, ui);
    }

    @Override
    public void execute() {
        int projectNumber = Integer.parseInt(line.trim())-1;
        if (projects.size() == 0) {
            ui.printBorderline("It appears that you have no project! Perhaps you should start creating one?");
            return;
        }
        if (line.isEmpty() || projectNumber > projects.size()) {
            ui.printBorderline("Please type in the right project");
            return;
        }

        //System.out.println(ui.displayProject(projects.get(projectNumber)));
        new SendEmail(getEmail(projects.get(projectNumber).toString()), ui.displayProject(projects.get(projectNumber)));

    }

    public String getEmail(String line){

        String arr[] = line.split("--");
        for(int i = 0; i < arr.length; i ++){
            String arr2[] = arr[i].split(" ", 2);
            if(arr2[0].contains("email")){
                line = arr2[1].trim().toLowerCase();
            }
        }
        return line;
    }


}