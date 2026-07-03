package OhadGipsAndTamirEliasy;

import java.util.*;

public class DepartmentWizard extends WizardWorkflow {
    private final College college;
    private final List<String> prompts = new ArrayList<>(List.of(
            "Enter department name: ",
            "Enter number of students in the department: "
    ));

    public DepartmentWizard(College college) {
        this.college = college;
    }

    @Override
    protected int getStepsCount() { return prompts.size(); }

    @Override
    protected String getPrompt(int step) { return prompts.get(step); }

    @Override
    protected void validateInput(int step, String input) {
        if (input.isEmpty()) throw new IllegalArgumentException("Input cannot be empty");
        if (step == 0 && college.departmentExists(input)) {
            throw new IllegalArgumentException("This department already exists. Try a different name.");
        }
        if (step == 1) {
            int students = Integer.parseInt(input);
            if (students < 0) throw new IllegalArgumentException("Number of students cannot be negative");
        }
    }

    @Override
    protected HasName createEntity(List<String> data) {
        return new Department(data.get(0), Integer.parseInt(data.get(1)));
    }
}