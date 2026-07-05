package OhadGipsAndTamirEliasy;

import java.io.File;
import java.io.IOException;
import java.util.*;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class CollegeUI {
    private College college;
    private static Scanner sc;

    public CollegeUI() {

        sc = new Scanner(System.in);
        System.out.print("Enter College Name: ");
        String collegeName = sc.nextLine();
        String fileName = collegeName + "_backup.bin";
        File file = new File(fileName);
        if (file.exists()) {
            try {
                college = CollegeFileHandler.loadFromFile(fileName);
                System.out.println("Load saved data for this existing college: " + collegeName);
            } catch (Exception e) {
                System.err.println("Error loading file, start a new college");
                college = new College(collegeName); // constructor with name
            }
        } else {
            college = new College(collegeName); // new college
        }
    }

    public void UI() {
        int option = -1;
        do {
            try {
                System.out.println("""
                        Welcome To The System:
                        0- Exit
                        1- Add a lecturer
                        2- Add a committee
                        3- Assign a lecturer to a committee
                        4- Assign a New Chairperson to a committee
                        5- Remove a lecturer from a committee
                        6- Add a study department
                        7- Add a lecturer to a study department
                        8- Remove a lecturer from a study department
                        9- Show the average salary of all lecturers in college
                        10- Show the average salary of lecturers in a certain department
                        11- Show all lecturers information
                        12- Show all committees information
                        13 - Compare between doctors
                        14 - Compare between committees
                        15 - Duplicate committee""");

                option = sc.nextInt();
                String committeeName, departmentName;
                sc.nextLine();
                switch (option) {
                    case 1:
                        try {
                            if (addLecturerUI()) System.out.println("\n a new lecturer was created\n");

                            else System.out.println("\n a new lecturer was not created\n");
                        }
                        catch (EnumDoNotExists e) {
                            System.out.println(e.getMessage());
                        }
                        catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            if (addCommitteeUI()) {
                                System.out.println("a new committee has been created\n");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        AddToCommitteeUI();
                        break;
                    case 4:
                        ChangeChairpersonUI();
                        break;
                    case 5:
                        RemoveFromCommitteeUI();
                        break;
                    case 6:
                        addDepartmentUI();
                        break;
                    case 7:
                        addLecturerToDepartmentUI();
                        break;
                    case 8:
                        removeLecturerFromDepartmentUI();
                        break;
                    case 9:
                        System.out.printf("The average wage for lecturers in this college is: %s\n", college.salaryAverage());
                        break;
                    case 10:
                        try {
                            System.out.print("Enter department name: ");
                            departmentName = sc.nextLine();
                            Department department = College.getByName(college.getDepartments(), departmentName);
                            System.out.printf("The average wage for lecturers in this department is: %s\n", college.getSalaryAverageByDepartment(department));
                        } catch (DoNotExists e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 11:
                        System.out.println("Choose sorting criterion:");
                        System.out.println("1 - By Name");
                        System.out.println("2 - By ID");
                        System.out.println("3 - By Name Of Degree");
                        System.out.println("4 - By Wage");
                        System.out.print("Your choice: ");
                        option = sc.nextInt();
                        java.util.Comparator<Lecturer> comparator = switch (option) {
                            case 2 -> Comparator.comparingInt(Lecturer::getId);
                            case 3 -> Comparator.comparing(Lecturer::getNameOfDegree);
                            case 4 -> Comparator.comparingInt(Lecturer::getWage);
                            default -> Comparator.comparing(Lecturer::getName);
                        };
                        System.out.println(college.formatSortedLecturers(comparator));
                        break;
                    case 12:
                        System.out.println("Choose sorting criterion:");
                        System.out.println("1 - By Name");
                        System.out.println("2 - By Chairperson Name");
                        System.out.print("Your choice: ");
                        option = sc.nextInt();
                        java.util.Comparator<Committee> Committeecmp;
                        if (option == 2)
                            Committeecmp = Comparator.comparing(l -> l.getChairperson().getName());
                        else
                            Committeecmp = Comparator.comparing(Committee::getName);

                        System.out.println(college.formatSortedCommittees(Committeecmp));
                        break;
                    case 13:
                        CompareDoctorsUI();
                        break;
                    case 14:
                        CompareCommitteeUI();
                        break;
                    case 15:
                        try {
                            WizardWorkflow dupWizard = new DuplicateCommitteeWizard(college);
                            HasName result = dupWizard.runWorkflow();
                            if (result != null) {
                                committeeName = result.getName();
                                college.duplicateCommittee(committeeName);
                                System.out.printf("Committee '%s' duplicated successfully as '%s-new'\n",
                                        committeeName, committeeName);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        if (option != 0) System.out.println("Invalid input try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input try again");
                sc.nextLine();
            }
        }
        while (option != 0);
        System.out.println("You have left the system");
        try {
            CollegeFileHandler.saveCollege(college, college.getCollegeName() + "_backup.bin");
            System.out.println("Data saved. Goodbye!");
        } catch (
                IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
        }
    }

    public void AddToCommitteeUI() {
        try {
            WizardWorkflow addMemberWizard = new CommitteeActionWizard(
                    college,
                    "Enter committee name: ",
                    "Enter lecturer name: ",
                    "add"
            );

            HasName result = addMemberWizard.runWorkflow();
            if (result != null) {
                String[] parts = result.getName().split("\\|\\|\\|");
                String commName = parts[0];
                String lecName = parts[1];

                college.addLecturerToCommittee(commName, lecName);
                System.out.printf("%s has been added to the committee\n", lecName);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void ChangeChairpersonUI() {
        try {
            WizardWorkflow chairWizard = new CommitteeActionWizard(
                    college,
                    "Enter committee name: ",
                    "Enter lecturer name: ",
                    "chairperson"
            );

            HasName result = chairWizard.runWorkflow();
            if (result != null) {
                String[] parts = result.getName().split("\\|\\|\\|");
                String commName = parts[0];
                String lecName = parts[1];

                college.setNewChairperson(commName, lecName);
                System.out.printf("%s is now the chairperson of %s\n", lecName, commName);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void RemoveFromCommitteeUI() {
        try {
            WizardWorkflow removeWizard = new CommitteeActionWizard(
                    college,
                    "Enter committee name: ",
                    "Enter lecturer name: ",
                    "remove"
            );

            HasName result = removeWizard.runWorkflow();
            if (result != null) {
                String[] parts = result.getName().split("\\|\\|\\|");
                String commName = parts[0];
                String lecName = parts[1];

                college.removeCommitteeMember(commName, lecName);
                System.out.printf("%s has been removed from %s\n", lecName, commName);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean addCommitteeUI() {
        try {
            WizardWorkflow wizard = new CommitteeWizard(college);
            Committee committee = (Committee) wizard.runWorkflow();

            if (committee != null) {
                college.addCommittee(committee);
                return true;
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error creating committee: " + e.getMessage());
        }
        return false;
    }

    public void CompareDoctorsUI() {
        try {
            WizardWorkflow doctorWizard = new CompareWizard(
                    college,
                    "Enter first doctor name: ",
                    "Enter second doctor name: ",
                    "doctor"
            );

            HasName result = doctorWizard.runWorkflow();
            if (result != null) {
                String[] names = result.getName().split("\\|\\|\\|");
                String doc1 = names[0];
                String doc2 = names[1];
                System.out.println(college.compareDoctors(college.getDoctor(doc1), college.getDoctor(doc2)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void CompareCommitteeUI() {
        try {
            WizardWorkflow committeeWizard = new CompareWizard(
                    college,
                    "Enter first committee name: ",
                    "Enter second committee name: ",
                    "committee"
            );

            HasName result = committeeWizard.runWorkflow();
            if (result != null) {
                String[] names = result.getName().split("\\|\\|\\|");
                String comm1 = names[0];
                String comm2 = names[1];

                System.out.println(college.compareCommittees(
                        College.getByName(college.getCommittees(), comm1),
                        College.getByName(college.getCommittees(), comm2)
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean addLecturerUI() throws Exception {
        WizardWorkflow wizard = new LecturerWizard(college);

        Lecturer lecturer = (Lecturer) wizard.runWorkflow();

        if (lecturer != null) {
            boolean added = college.addLecturer(lecturer);
            System.out.println(added ? "Added successfully!" : "Lecturer already exists.");
            return added;
        }

        return false;
    }


    public void addDepartmentUI() {
        try {
            WizardWorkflow wizard = new DepartmentWizard(college);
            Department department = (Department) wizard.runWorkflow();

            if (department != null) {
                college.addDepartment(department);
                System.out.printf("%s department was added successfully.\n", department.getName());
            }
        } catch (Exception e) {
            System.out.println("Error creating the department: " + e.getMessage());
        }
    }

    public void addLecturerToDepartmentUI() {
        try {
            WizardWorkflow workflow = new LecturerDepartmentActionWizard(college, "add");
            HasName result = workflow.runWorkflow();

            if (result != null) {
                String[] parts = result.getName().split("\\|\\|\\|");
                String lecturerName = parts[0];
                String departmentName = parts[1];

                college.addLecturerToDepartment(lecturerName, departmentName);
                System.out.printf("%s has been added to the department\n", lecturerName);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void removeLecturerFromDepartmentUI() {
        try {
            WizardWorkflow workflow = new LecturerDepartmentActionWizard(college, "remove");
            HasName result = workflow.runWorkflow();

            if (result != null) {
                String[] parts = result.getName().split("\\|\\|\\|");
                String lecturerName = parts[0];
                String departmentName = parts[1];

                college.removeLecturerFromDepartment(lecturerName);
                System.out.printf("%s has been removed from %s\n", lecturerName, departmentName);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}




