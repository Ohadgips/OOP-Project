package OhadGipsAndTamirEliasy;

import java.util.*;

public abstract class WizardWorkflow {
    protected final List<String> gatheredData = new ArrayList<>();
    protected final Stack<Command> stack = new Stack<>();
    protected int currentStep = 0;
    protected Scanner sc = new Scanner(System.in);

    public HasName runWorkflow() throws Exception {
        initialize();
        processSteps();
        return completeWorkflow();
    }
    protected void initialize() {
        System.out.println("Tip: type 'back' to go to the previous step, 'cancel' to abort.\n");
    }
    protected void processSteps() throws Exception {
        while (currentStep < getStepsCount()) {
            System.out.print("[Step " + (currentStep + 1) + "/" + getStepsCount() + "] " + getPrompt(currentStep));
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("cancel")) {
                currentStep = getStepsCount();
                gatheredData.clear();
                return;
            }
            if (input.equalsIgnoreCase("back")) {
                if (stack.isEmpty()) {
                    System.out.println("Already at the first step.\n");
                } else {
                    Command cmd = stack.pop();
                    cmd.undo();
                    System.out.println("Went back.\n");
                }
                continue;
            }

            try {
                validateInput(currentStep, input);
                final String finalInput = input;
                Command moveCmd = new Command() {
                    @Override
                    public void execute() {
                        gatheredData.add(finalInput);
                        currentStep++;
                    }
                    @Override
                    public void undo() {
                        gatheredData.removeLast();
                        currentStep--;
                        onUndo(currentStep);
                    }
                };
                moveCmd.execute();
                stack.push(moveCmd);

            } catch (Exception e) {
                System.out.println("Invalid: " + e.getMessage() + " — try again.\n");
            }
        }
    }
    protected HasName completeWorkflow() throws Exception {
        if (gatheredData.isEmpty()) {
            System.out.println("Cancelled.");
            return null;
        }
        return createEntity(gatheredData);
    }

    protected abstract int getStepsCount();
    protected abstract String getPrompt(int step);
    protected abstract void validateInput(int step, String input) throws Exception;
    protected abstract HasName createEntity(List<String> data) throws Exception;
    protected void onUndo(int step) {}
}