package OhadGipsAndTamirEliasy;

import java.util.*;

public class CommitteeWizard extends WizardWorkflow {
    private final College college;

    private final List<String> prompts = new ArrayList<>(List.of(
            "Enter committee name: ",
            "Enter chairperson name: ",
            "Enter committee degree type (Bachelor, Master, Doctoral, Professional): "
    ));

    public CommitteeWizard(College college) {
        this.college = college;

        if (!college.HasDoctoralLecturer()) {
            throw new IllegalStateException("There are no doctoral lecturers in the college to create a committee");
        }
    }

    @Override
    protected int getStepsCount() { return prompts.size(); }

    @Override
    protected String getPrompt(int step) { return prompts.get(step); }

    @Override
    protected void validateInput(int step, String input) throws Exception {
        if (input.isEmpty()) throw new IllegalArgumentException("Input cannot be empty");

        if (step == 0) {
            if (college.committeeExist(input)) {
                throw new IllegalArgumentException("This committee already exists. Try a different name");
            }
        }

        if (step == 1) {
            try {
                Lecturer chairperson = College.getByName(college.getLecturers(), input);
                if (chairperson.getKindOfDegree() != Lecturer.Degree.Doctoral &&
                        chairperson.getKindOfDegree() != Lecturer.Degree.Professional) {
                    throw new IllegalArgumentException("This lecturer does not meet the requirements (must be Doctoral/Professional)");
                }
            } catch (DoNotExists e) {
                throw new IllegalArgumentException(e.getMessage() + ". Try a different name");
            }
        }

        if (step == 2) {
            try {
                Lecturer.Degree.valueOf(input);
            } catch (IllegalArgumentException e) {
                throw new EnumDoNotExists();
            }
        }
    }

    @Override
    protected HasName createEntity(List<String> data) throws Exception {
        String committeeName = data.get(0);
        String chairpersonName = data.get(1);
        Lecturer.Degree kindOfDegree = Lecturer.Degree.valueOf(data.get(2));

        Lecturer chairperson = College.getByName(college.getLecturers(), chairpersonName);

        Committee newCommittee = new Committee(committeeName, chairperson, kindOfDegree);

        chairperson.addCommittee(newCommittee);

        return newCommittee;
    }
}