package budgetbuddy.command;

import budgetbuddy.model.BudgetManager;
import budgetbuddy.exception.InvalidInputException;
import budgetbuddy.parser.FindExpenseParser;

/**
 * The FindExpenseCommand class represents a command that searches for expenses in the Overall budget.
 *
 * <p>This command parses the input description, extracts the keyword after "find",
 * and then calls the {@link BudgetManager#findExpense(String)} method to find and display matching expenses.</p>
 */
public class FindExpenseCommand extends Command {

    public FindExpenseCommand(String description) {
        super(description);
    }

    /**
     * Executes the FindExpenseCommand by parsing the input and searching for the keyword in the Overall budget.
     *
     * <p>The method expects the user input to start with the keyword "find" followed by the search term.
     * Example: "find food" will search for expenses containing "food" in the description.</p>
     *
     * @param budgetManager The BudgetManager responsible for managing expenses and budgets.
     * @throws InvalidInputException If there is invalid input while parsing the description.
     */
    @Override
    public void execute(BudgetManager budgetManager) throws InvalidInputException {
        FindExpenseParser parser = new FindExpenseParser(description);
        String keyword = parser.parse();
        budgetManager.findExpense(keyword);
    }

    /**
     * Returns {@code false} as this command does not signify the end of the program.
     *
     * @return {@code false} to indicate the program should not exit after executing this command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
