import javax.swing.*;
import java.util.LinkedList;
import java.util.Stack;

public class TodoListManager {
    
    private LinkedList<String> todoList = new LinkedList<>();
    private LinkedList<String> completedTasks = new LinkedList<>();
    private Stack<String> undoStack = new Stack<>();
    
    public static void main(String[] args) {
        TodoListManager manager = new TodoListManager();
        manager.run();
    }

    
    public void run() {
        while (true) {
            String choice = showMenu();
            
            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    markTaskAsDone();
                    break;
                case "3":
                    undoAction();
                    break;
                case "4":
                    viewTodoList();
                    break;
                case "5":
                    viewCompletedTasks();  
                    break;
                case "6":
                    exitProgram();
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

    
    private String showMenu() {
        String menu = 
            "\n--- To-Do List Manager ---\n" +
            "1. Add Task\n" +
            "2. Mark Task as Done\n" +
            "3. Undo\n" +
            "4. View To-Do List\n" +
            "5. View Completed Tasks\n" +
            "6. Exit\n";
        
        return JOptionPane.showInputDialog(null, menu + "\nEnter your choice: ");
    }

    
    private void addTask() {
        String task = JOptionPane.showInputDialog("Enter the task to add:");
        if (task != null && !task.trim().isEmpty()) {
            todoList.add(task);
            undoStack.push("add:" + task);
            JOptionPane.showMessageDialog(null, "Task added.");
        } else {
            JOptionPane.showMessageDialog(null, "Task cannot be empty.");
        }
    }

    
    private void markTaskAsDone() {
        if (todoList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available to mark as done.");
            return;
        }

        StringBuilder taskList = new StringBuilder("\nTo-Do List:\n");
        for (int i = 0; i < todoList.size(); i++) {
            taskList.append((i + 1) + ". " + todoList.get(i) + "\n");
        }

        String taskNumberString = JOptionPane.showInputDialog(taskList.toString() + "Enter the number of the task to mark as done:");
        try {
            int taskNumber = Integer.parseInt(taskNumberString);
            if (taskNumber < 1 || taskNumber > todoList.size()) {
                JOptionPane.showMessageDialog(null, "Invalid task number.");
                return;
            }

            String task = todoList.remove(taskNumber - 1);
            completedTasks.add(task);
            undoStack.push("done:" + task);
            JOptionPane.showMessageDialog(null, "Task marked as done.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
        }
    }

    
    private void undoAction() {
        if (undoStack.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No actions to undo.");
            return;
        }

        String lastAction = undoStack.pop();
        String[] actionParts = lastAction.split(":");
        String actionType = actionParts[0];
        String task = actionParts[1];

        if ("add".equals(actionType)) {
            todoList.remove(task);
            JOptionPane.showMessageDialog(null, "Added task undone.");
        } else if ("done".equals(actionType)) {
            completedTasks.remove(task);
            todoList.add(task);
            JOptionPane.showMessageDialog(null, "Task marked as done undone.");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid action in undo stack.");
        }
    }

    
    private void viewTodoList() {
        if (todoList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "To-Do list is empty.");
        } else {
            StringBuilder list = new StringBuilder("\nTo-Do List:\n");
            for (int i = 0; i < todoList.size(); i++) {
                list.append((i + 1) + ". " + todoList.get(i) + "\n");
            }
            JOptionPane.showMessageDialog(null, list.toString());
        }
    }

    
    private void viewCompletedTasks() {
        if (completedTasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No completed tasks yet.");
        } else {
            StringBuilder list = new StringBuilder("\nCompleted Tasks:\n");
            for (String task : completedTasks) {
                list.append("- " + task + "\n");
            }
            JOptionPane.showMessageDialog(null, list.toString());
        }
    }

    
    private void exitProgram() {
        JOptionPane.showMessageDialog(null, "Goodbye! Exiting the program.");
    }
}
