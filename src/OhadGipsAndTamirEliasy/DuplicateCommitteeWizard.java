package OhadGipsAndTamirEliasy;

import java.util.List;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883

public class DuplicateCommitteeWizard extends WizardWorkflow {
    private final College college;
    private final List<String> prompts = List.of("Enter committee name to duplicate: ");

    public DuplicateCommitteeWizard(College college) {
        this.college = college;
    }

    @Override
    protected int getStepsCount() { return prompts.size(); }

    @Override
    protected String getPrompt(int step) { return prompts.get(step); }

    @Override
    protected void validateInput(int step, String input) {
        if (input.isEmpty())
            throw new IllegalArgumentException("Input cannot be empty");
        if (!college.committeeExist(input))
            throw new IllegalArgumentException("Committee '" + input + "' does not exist.");
    }

    @Override
    protected HasName createEntity(List<String> data) {
        return () -> data.getFirst();
    }
}