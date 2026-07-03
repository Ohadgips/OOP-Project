package OhadGipsAndTamirEliasy;
import java.util.*;

public class CommitteeActionWizard extends WizardWorkflow {
    private final College college;
    private final List<String> prompts;
    private final String actionType;

    public CommitteeActionWizard(College college, String prompt1, String prompt2, String actionType) {
        this.college = college;
        this.prompts = List.of(prompt1, prompt2);
        this.actionType = actionType;
    }

    @Override
    protected int getStepsCount() { return prompts.size(); }

    @Override
    protected String getPrompt(int step) { return prompts.get(step); }

    @Override
    protected void validateInput(int step, String input) {
        if (input.isEmpty()) throw new IllegalArgumentException("Input is empty");

        if (step == 0) {
            if (!college.committeeExist(input)) {
                throw new IllegalArgumentException("Committee '" + input + "' does not exist.");
            }
        }

        if (step == 1) {
            try {
                Lecturer lecturer = College.getByName(college.getLecturers(), input);

                String committeeName = gatheredData.getFirst();
                Committee committee = College.getByName(college.getCommittees(), committeeName);

                if (actionType.equalsIgnoreCase("remove")) {
                    if (committee.getChairperson() != null && committee.getChairperson().getName().equalsIgnoreCase(input)) {
                        throw new IllegalArgumentException("Cannot remove this lecturer because they are currently the chairperson of this committee.");
                    }
                }

                if (actionType.equalsIgnoreCase("chairperson")) {
                    if (lecturer.getKindOfDegree() != Lecturer.Degree.Doctoral &&
                            lecturer.getKindOfDegree() != Lecturer.Degree.Professional) {
                        throw new IllegalArgumentException("This lecturer cannot be a chairperson (must be Doctoral/Professional)");
                    }
                }
            } catch (DoNotExists e) {
                throw new IllegalArgumentException(e.getMessage() + ". Try a different name");
            }
        }
    }

    @Override
    protected HasName createEntity(List<String> data) {
        return () -> data.getFirst() + "," + data.get(1);
    }
}