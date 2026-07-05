package OhadGipsAndTamirEliasy;

import java.util.List;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883

public class CompareWizard extends WizardWorkflow {
    private final College college;
    private final List<String> prompts;
    private final String comparisonType;

    public CompareWizard(College college, String prompt1, String prompt2, String comparisonType) {
        this.college = college;
        this.comparisonType = comparisonType;
        this.prompts = List.of(prompt1, prompt2);
    }
    @Override
    protected int getStepsCount() { return prompts.size(); }

    @Override
    protected String getPrompt(int step) { return prompts.get(step); }

    @Override //[cite: 14]
    protected void validateInput(int step, String input) {
        if (input.isEmpty()) throw new IllegalArgumentException("Input cannot be empty");
        if (comparisonType.equalsIgnoreCase("doctor")) {
            try {
                college.getDoctor(input);
            } catch (DoNotExists e) {
                throw new IllegalArgumentException(e.getMessage() + ". Try a different name");
            }
        }
        else if (comparisonType.equalsIgnoreCase("committee")) {
            if (!college.committeeExist(input)) {
                throw new IllegalArgumentException("Committee '" + input + "' does not exist. Try a different name");
            }
        }
    }
    @Override
    protected HasName createEntity(List<String> data) {
        return () -> data.getFirst() + "|||" + data.get(1);
    }
}
