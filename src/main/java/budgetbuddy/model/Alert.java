package budgetbuddy.model;

import budgetbuddy.ui.Ui;

/**
 * Manages budget alerts and notifies the user when expenses exceed a specified amount.
 */
public class Alert {
    private double alertAmount;
    private boolean isActive;

    /**
     * Initializes the Alert with no active limit.
     */
    public Alert() {
        this.alertAmount = 0;
        this.isActive = false;
    }

    /**
     * Checks if the budget alert is currently active.
     *
     * @return {@code true} if the alert is enabled and will trigger when the threshold is reached;
     *         {@code false} if the alert is disabled.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the alert amount. If the amount is 0, it disables the alert.
     *
     * @param amount The amount to trigger an alert.
     */
    public void setAlert(double amount) {
        if (amount < 0) {
            Ui.printInvalidBudgetAlertWarning();
        }

        this.alertAmount = amount;
        this.isActive = amount > 0;

        if (isActive) {
            Ui.printSetBudgetAlert(alertAmount, false);
        }else {
            Ui.printRemoveBudgetAlert();
        }
    }

    /**
     * Checks if total expenses exceed the alert amount.
     * if expenses hits exactly alert amount Hit Alert is triggered.
     *
     * @param totalExpenses The current total expenses.
     */
    public void checkAlert(double totalExpenses) {
        assert totalExpenses >= 0 : "Total expenses cannot be negative";
        if (isActive) {
            if (totalExpenses > alertAmount) {
                Ui.printCheckAlert(totalExpenses, alertAmount);
            } else if (totalExpenses == alertAmount) {
                Ui.printHitAlert(totalExpenses);
            }
        }
    }

    /**
     * Gets the current alert amount.
     *
     * @return The alert threshold.
     */
    public double getAlertAmount() {
        return alertAmount;
    }


    /**
     * Edits the current alert amount.
     *
     * @return The new alert threshold.
     */
    public double editAlertAmount(double amount) {
        if (amount < 0) {
            Ui.printInvalidBudgetAlertWarning();
            return (int) alertAmount;
        }

        this.alertAmount = amount;
        this.isActive = amount > 0;

        if (isActive) {
            Ui.printSetBudgetAlert(alertAmount, true);
        }else {
            Ui.printRemoveBudgetAlert();
        }

        return amount;
    }

    public void removeAlert() {
        this.alertAmount = 0;
        this.isActive = false;
        Ui.printRemoveBudgetAlert();
    }
}
