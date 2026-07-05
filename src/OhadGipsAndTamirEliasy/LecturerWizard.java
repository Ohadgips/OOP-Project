package OhadGipsAndTamirEliasy;

import java.util.*;
import java.util.stream.Collectors;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883

public class LecturerWizard extends WizardWorkflow {
    private final College college;
    private final List<String> prompts = new ArrayList<>(List.of(
            "Enter Lecturer Name: ",
            "Enter lecturer ID (number): ",
            "Enter kind of degree (Bachelor, Master, Doctoral, Professional): ",
            "Enter name of degree: ",
            "Enter lecturer wage (number): "
    ));
    public LecturerWizard(College college) {
        this.college = college;
    }

    @Override
    protected int getStepsCount() { return prompts.size(); }

    @Override
    protected String getPrompt(int step) { return prompts.get(step); }

    @Override
    protected void validateInput(int step, String input) throws Exception {
        if (input.isEmpty()) throw new IllegalArgumentException("Input cannot be empty");

        if (step == 0 && college.lecturerExist(input)) {
            throw new IllegalArgumentException("This name is already in use. Try a different name");
        }
        if (step == 1 || step == 4) {
            int val = Integer.parseInt(input);
            if (val < 0) throw new IllegalArgumentException("Number cannot be negative");
        }
        if (step == 2) {
            try {
                Lecturer.Degree degree = Lecturer.Degree.valueOf(input);
                adjustSteps(degree);
            } catch (IllegalArgumentException e) {
                throw new EnumDoNotExists();
            }
        }
    }

    private void adjustSteps(Lecturer.Degree degree) {
        while (prompts.size() > 5) {
            prompts.removeLast();
        }
        if (degree == Lecturer.Degree.Doctoral || degree == Lecturer.Degree.Professional) {
            prompts.add("Enter articles comma-separated (or press Enter for none): ");
        }
        if (degree == Lecturer.Degree.Professional) {
            prompts.add("Enter place that gave the degree of this professor: ");
        }
    }

    @Override
    protected void onUndo(int step) {
        if (step == 2) {
            while (prompts.size() > 5) {
                prompts.removeLast();
            }
        }
    }
    @Override
    protected HasName createEntity(List<String> data) {
        String name = data.get(0);
        int id = Integer.parseInt(data.get(1));
        Lecturer.Degree degree = Lecturer.Degree.valueOf(data.get(2));
        String degreeName = data.get(3);
        int wage = Integer.parseInt(data.get(4));

        if (degree == Lecturer.Degree.Doctoral || degree == Lecturer.Degree.Professional) {
            HashSet<String> articles = new HashSet<>();
            if (data.size() > 5 && !data.get(5).isEmpty()) {
                articles = Arrays.stream(data.get(5).split(",")).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toCollection(HashSet::new));
            }
            if (degree == Lecturer.Degree.Professional) {
                String inst = data.get(6);
                return new Professor(name, id, degree, degreeName, wage, inst, articles);
            }
            return new Doctor(name, id, degree, degreeName, wage, articles);
        }
        return new Lecturer(name, id, degree, degreeName, wage);
    }
}