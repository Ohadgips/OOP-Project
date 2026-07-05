package OhadGipsAndTamirEliasy;

import java.util.*;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883

public class LecturerDepartmentActionWizard extends WizardWorkflow {
    private final College college;
    private final List<String> prompts = new ArrayList<>(List.of(
            "Enter lecturer name: ",
            "Enter department name: "
    ));
    private final String actionType;
    private boolean needsConfirmation = false;

    public LecturerDepartmentActionWizard(College college, String actionType) {
        this.college = college;
        this.actionType = actionType;
    }

    @Override
    protected int getStepsCount() { return prompts.size(); }

    @Override
    protected String getPrompt(int step) { return prompts.get(step); }

    @Override
    protected void validateInput(int step, String input) throws Exception {
        if (input.isEmpty()) throw new IllegalArgumentException("Input cannot be empty");

        // שלב 0: בדיקת קיום המרצה
        if (step == 0) {
            try {
                College.getByName(college.getLecturers(), input);
            } catch (DoNotExists e) {
                throw new IllegalArgumentException(e.getMessage() + ". Try a different name");
            }
        }
        if (step == 1) {
            String lecturerName = gatheredData.getFirst();

            Lecturer lecturer = College.getByName(college.getLecturers(), lecturerName);

            if (!college.departmentExists(input)) {
                throw new IllegalArgumentException("Department '" + input + "' does not exist.");
            }

            if (actionType.equalsIgnoreCase("add")) {
                if (lecturer.getDepartment() != null) {
                    if (lecturer.getDepartment().getName().equalsIgnoreCase(input)) {
                        throw new IllegalArgumentException(lecturerName + " is already part of " + input + "!");
                    } else {
                        needsConfirmation = true;
                        if (prompts.size() == 2) {
                            prompts.add(String.format("%s is already part of a department. Do you want to change his department? (yes/no): ", lecturerName));
                        }
                    }
                }
            }

            if (actionType.equalsIgnoreCase("remove")) {
                if (lecturer.getDepartment() == null || !lecturer.getDepartment().getName().equalsIgnoreCase(input)) {
                    throw new IllegalArgumentException(lecturerName + " is not a member of " + input + ", so they cannot be removed from it.");
                }
            }
        }

        if (step == 2) {
            if (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
                throw new IllegalArgumentException("You must enter 'yes' or 'no'");
            }
            if (input.equalsIgnoreCase("no")) {
                throw new IllegalArgumentException("Action aborted by user ('no')");
            }
        }
    }

    @Override
    protected void onUndo(int step) {
        if (step == 1 && needsConfirmation) {
            needsConfirmation = false;
            if (prompts.size() > 2) {
                prompts.remove(2);
            }
        }
    }

    @Override
    protected HasName createEntity(List<String> data) {
        return () -> data.getFirst() + "|||" + data.get(1);
    }
}