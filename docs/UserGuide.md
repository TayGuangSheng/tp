# User Guide - Budget Buddy

## Introduction

Budget Buddy is designed for students who want to manage their finances efficiently. These users often have limited 
income, and busy schedules, and need an easy-to-use tool to track their daily expenses, set budgets, and stay 
financially aware. They may struggle with overspending, forgetfulness, and the challenge of balancing expenses across 
different categories.

### Table of Content 
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Notes about the command format](#notes-about-the-command-format)
  - [Saving of Data](#saving-of-data)
  - [Setting a Budget: `set-budget`](#setting-a-budget-set-budget)
  - [Adding an Expense: `add`](#adding-an-expense-add)
  - [Adding a Recurring Expense: `add-recurring`](#adding-a-recurring-expense-add-recurring)
  - [Deleting an Expense: `delete`](#deleting-an-expense-delete)
  - [Listing all Expenses: `list`](#listing-all-expenses-list)
  - [Editing an Expense: `edit-expense`](#editing-an-expense-edit-expense)
  - [Checking Budget: `check-budget`](#checking-budget-check-budget)
  - [Editing Budget: `edit-budget`](#editing-budget-edit-budget)
  - [Summary of Budget: `summary`](#summary-of-budget-summary)
  - [Add Alert: `alert`](#add-alert-alert)
  - [Delete Alert: `delete-alert`](#delete-alert-delete-alert)
  - [Find: `find`](#find-find)
  - [Help: `help`](#help-help)
  - [Bye: `bye`](#bye-bye)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `tp.jar` from [here](https://github.com/AY2425S2-CS2113-T12-4/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your address book.
4. Open a command terminal, cd into the folder you put the jar file in and type the following command:
`java -jar tp.jar`
5. A GUI similar to the one below should appear in a few seconds.

```
|                     |
|  $$$$       $$$$    |
| $    $     $    $   |
| $    $     $    $   |
|  $$$$       $$$$    |
|_____________________|
Hello! I'm your Budget Buddy
What can I do for you?
Input 'help' if you want to know what I can do!!
__________________________________________
```

## Features 

### Notes about the Command Format

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user. 
e.g. in `check-budget c/<CATEGORY>`, `CATEGORY` is a parameter which can be used as `check-budget c/Food`. 
* Items in square brackets are optional.
  e.g. in `check-budget [c/CATEGORY]`, can be used as `check-budget` or  `check-budget c/Food`. 
* Parameters are always in fixed order.
e.g. only `add <AMOUNT> c/ <CATEGORY> d/ <DESCRIPTION> t/ <DATE TIME>` is acceptable, in the given order 
* Extraneous parameters for commands that do not take in parameters (such as help, list, exit and clear) will be ignored.
e.g. if the command specifies help 123, it will be interpreted as help.

### Caution
* Data will only be saved when exiting the program using the `bye` command.
* If the program is closed directly (i.e., without `bye`), data will not be saved.
* Data will be saved in the file `budget_data.txt` in the same folder as the jar file.
* Do not edit the file `budget_data.txt` directly, as it may corrupt the data or cause the program to malfunction.

### Setting a Budget: `set-budget`
Sets a spending limit for all expenses or for a specific category. 
Users can set an Overall budget, create new budget categories, or update existing category budgets.

**Format:** `set-budget [c/<CATEGORY>] <AMOUNT>`

* The `AMOUNT` can be any positive number less than 100000.
* The `AMOUNT` is recommended to be 2 decimal place. Any values with more than 2 decimal place may be rounded off. 
* If no c/ marker is used, the budget will apply to the Overall Budget. 
* If a c/<CATEGORY> is provided, a category-specific budget will be set. 
* If the category doesn't already exist, it will be automatically created. 
* If it exists, the budget limit will be updated.
  * Categories are case-sensitive

**Example 1:**
`set-budget 1000`

**Expected Output 1:**
```
__________________________________________
Overall budget set to: $1000.0
__________________________________________
```

**Example 2:**
`set-budget c/Food 300`

**Expected Output 2:**
```
__________________________________________
Budget for Food set to: $300.0
__________________________________________
```

**Example 3:**
`set-budget c/Food 10000000000`

**Expected Output 3:**
```
__________________________________________
Invalid input format: Amount must be between 0 and 100000.0
__________________________________________
```

### Adding an Expense: `add`
Adds a new item to the list of expenses. 

**Format:** `add <AMOUNT> c/ <CATEGORY> d/ <DESCRIPTION> [t/ <DATE TIME>]`

* The `AMOUNT` can be any positive number, with a limit set to $10,000. 
* The `CATEGORY` can be in natural language format. To add expense to Overall budget, use `c/Overall`.
* The `DESCRIPTION` can be in natural language format.  
* The `CATEGORY` and `DESCRIPTION` cannot contain `d/` and `t/`
* The `CATEGORY` is case-sensitive.
* The `DATE TIME` must follow the format `MMM dd yyyy at HH:mm`, where:
  - **Only the first letter of `MMM` is capitalized** (e.g., `Jan 15 2025 at 11:30`).
  - **`HH:mm` follows the 24-hour clock format** (e.g., `13:45 for 1:45 PM`). 
  - If an incorrect date format is provided, the **current date and time** will be used instead.
* Users can choose to leave `CATEGORY` and `DATE TIME` blank, in which case:
  - The expense will default to the **Overall Budget** if `CATEGORY` is not provided or cannot be found.
  - The `DATE TIME` will default to the **current date and time** if left blank or formatted incorrectly.
* Users **must still include `c/` even if leaving it blank**.
* Argument order is strict. Users should follow the recommended format. Reordering the markers will not execute command.

**Example 1:**
`add 200 c/ d/ food t/`

**Expected Output 1:**
```
___________________________________________
Expense Added: $200.00 spent on food (Apr 04 2025 at 23:12)
___________________________________________
```

**Example 2:**
`add 100 c/ Overall d/ bus fares t/`

**Expected Output 2:**
```
___________________________________________
Expense Added: $100.00 spent on bus fares (Apr 04 2025 at 23:13)
___________________________________________
```

**Example 3:** 
`add 50.34 c/ Overall d/ cab fares t/ Jan 15 2025 at 11:30`

**Expected Output 3:**
```
___________________________________________
Expense Added: $50.34 spent on cab fares (Jan 15 2025 at 11:30)
___________________________________________
```

### Adding a Recurring Expense: `add-recurring`
Adds a recurring expense to the list of expenses based on the given frequency and number of iterations. 
Each entry is treated as a separate expense added to the system.

**Format:**

`add-recurring <AMOUNT> c/ <CATEGORY> d/ <DESCRIPTION> t/ <START DATE TIME> f/ <FREQUENCY_IN_DAYS> i/ <ITERATIONS>`

* The `AMOUNT` must be a positive number. The amount limit is set to $10,000.
* The `CATEGORY` can be any valid budget category.
* To save to overall budget , use `c/Overall`. 
* The `DESCRIPTION` is in natural language format. It cannot contain markers like `t/`, `f/`, or `i/`.
* The `START DATE TIME` must follow the format `MMM dd yyyy at HH:mm`, where:
  * Only the first letter of `MMM` is capitalized (e.g., `Apr 24 2025 at 12:00`)
  * `HH:mm` follows the 24-hour clock format
  * If the date is incorrectly formatted, the current system date and time will be used instead.
* The `FREQUENCY` (`f/`) specifies the number of days between each recurrence.
* The `ITERATIONS` (`i/`) specifies how many times the expense should recur.
* Maximum frequency allowed is **1000 days**.
* Maximum number of iterations allowed is **10**.

#### Example 1:
`add-recurring 20 c/Food d/Lunch t/Apr 24 2025 at 12:00 f/30 i/5`

**Expected Output:**
```
Adding recurring expense to budget... 
Hooray! Added recurring expense(s) to budget. 
Here is the list:
1.$20.00 spent on Lunch (Apr 24 2025 at 12:00)
2.$20.00 spent on Lunch (May 24 2025 at 12:00)
3.$20.00 spent on Lunch (Jun 23 2025 at 12:00)
4.$20.00 spent on Lunch (Jul 23 2025 at 12:00)
5.$20.00 spent on Lunch (Aug 22 2025 at 12:00)
```

#### Example 2:
`add-recurring 15.75 c/Transport d/Bus Pass t/Jan 01 2026 at 09:00 f/15 i/3`

**Expected Output:**
```
Adding recurring expense to budget... 
Hooray! Added recurring expense(s) to budget. 
Here is the list:
1.$15.75 spent on Bus Pass (Jan 01 2026 at 09:00)
2.$15.75 spent on Bus Pass (Jan 16 2026 at 09:00)
3.$15.75 spent on Bus Pass (Jan 31 2026 at 09:00)
```
#### Error Example (Invalid Format):
`add-recurring 20 c/Food d/Lunch t/Apr 2025 f/10 i/5`

**Expected Output: (in this example, current system time is Mar 10 2025, 12:00)**
```
Wrong time format used. Will use system current dateTime instead.
Format guide: "MMM dd yyyy at HH:mm" 
Adding recurring expense to budget... 
Hooray! Added recurring expense(s) to budget. 
Here is the list:
$20.00 spent on Lunch (Mar 10 2025 at 12:00)
$20.00 spent on Lunch (Mar 20 2025 at 12:00)
$20.00 spent on Lunch (Mar 30 2025 at 12:00)
$20.00 spent on Lunch (Apr 10 2025 at 12:00)
$20.00 spent on Lunch (Apr 20 2025 at 12:00)
```

### Deleting an Expense: `delete`
Removes a recorded expense from the Overall Budget and its corresponding category budget using its index.
This `INDEX` is found from the list command, which displays all recorded expenses.

**Format:** `delete <INDEX>`

* The `INDEX` must be a positive integer representing the position of the expense in the Overall Budget. 
* Deleting an expense from the Overall Budget will also remove it from the corresponding category budget.

**Example:** `delete 4`

**Expected Output:**
```
___________________________________________
The following expense has been deleted successfully.
-> $5.00 spent on Tacos (Apr 02 2025 at 17:48)
___________________________________________
Expense also deleted from category 'Food'.
___________________________________________
```

### Listing all Expenses: `list`
Displays all recorded expenses under the Overall Budget, including their amount, description, and date/time.

**Format:** `list [start/<TIME>] [end/<TIME>]`

* Lists expenses in chronological order from the Overall Budget, with latest displayed first. 
* Each expense includes the amount, description, and timestamp.
* Both start and end are optional. Users can choose to apply both or either or none. 
* Format for time is "MMM dd yyyy at HH:mm" 
* Here, 
  * M: Month (**Only the first alphabet of month should be capitalised**)
  * d: Day 
  * y: Year 
  * H: Hour 
  * m: Minute
* If incorrect date time formats are used for end time, then programme will use system date and time.


**Example 1:** `list`

**Expected Output 1:**
```
___________________________________________
Expense List:
1. $25.00 spent on Bus Fare (Apr 03 2025 at 08:15)
2. $12.50 spent on Coffee (Apr 02 2025 at 10:30)
3. $60.00 spent on Groceries (Apr 01 2025 at 16:45)
___________________________________________
```

**Example 2:** `list start/Apr 01 2025 at 12:45 end/Apr 02 2025 at 11:40`

**Expected Output 2:**
```
___________________________________________
Expense List:
2. $12.50 spent on Coffee (Apr 02 2025 at 10:30)
3. $60.00 spent on Groceries (Apr 01 2025 at 16:45)
___________________________________________
```
**Example 3:** `list start/Apr 01 2025 at 23:00`

**Expected Output 3:**
```
___________________________________________
Expense List:
1. $25.00 spent on Bus Fare (Apr 03 2025 at 08:15)
2. $12.50 spent on Coffee (Apr 02 2025 at 10:30)
___________________________________________
```


### Editing an Expense: `edit-expense`
Edit an item on the list of expenses. Expense `AMOUNT`, `DESCRIPTION` or `DATE TIME` can be edited, with all three being 
optional but requiring minimally one.

**Format:** `edit-expense <INDEX> [a/ <AMOUNT>] [d/ <DESCRIPTION>] [t/ <DATE TIME>]`

* The `INDEX` refers to the index of the expense when the `list` function is used.
* The `AMOUNT` can be any positive number less than 100000.
* The `DESCRIPTION` can be in natural language format.
* The `DESCRIPTION` cannot contain `d/` and `t/`
* The `DATE TIME` has to follow the format of `MMM dd yyyy at HH:mm`, where **only** the first letter of `MMM` is
  capitalized, and HH:mm follows the 24-hour clock format.
* The `DATE TIME` must follow the format `MMM dd yyyy at HH:mm`, where:
  - **Only the first letter of `MMM` is capitalized** (e.g., `Jan 15 2025 at 11:30`).
  - **`HH:mm` follows the 24-hour clock format** (e.g., `13:45 for 1:45 PM`).
  - If an incorrect date format is provided, the **current date and time** will be used instead.
* Users can omit `a/`, `c/` **or** `t/` but **cannot omit all three**.

**Example 1:**

`edit-expense 1 a/ 200`

**Expected Output 1:**

```
___________________________________________
Got it, the expense at index 1 has been updated!
Updated expense -> $200.00 spent on apple juice (Jan 18 2025 at 11:20)
___________________________________________
```

**Example 2:**
`edit-expense 1 a/ 100 d/ bus fares`

**Expected Output 2:**
```
___________________________________________
Got it, the expense at index 1 has been updated!
Updated expense -> $100.00 spent on bus fares (Jan 18 2025 at 11:20)
___________________________________________
```

**Example 3:**
`edit-expense 1 t/ Jan 15 2025 at 11:30`

**Expected Outcome 3:**
```
___________________________________________
Got it, the expense at index 1 has been updated!
Updated expense -> $100.00 spent on bus fares (Jan 30 2025 at 11:25)
___________________________________________
```


### Checking Budget: `check-budget`
Displays the budget allocation, amount spent, and remaining balance for a specified category.
If no category is provided, it displays the Overall Budget.

**Format:** `check-budget c/<CATEGORY>`

* The budget breakdown includes:
  * Total Budget (allocated amount)
  * Spent (total expenses recorded)
  * Remaining 
* If the Amount Spent exceeds the Total Budget, Remaining Budget remains 0.

**Example 1:** `check-budget`

**Expected Output 1 :**
```
__________________________________________
Overall Budget:

Total Budget: $10000.00
Spent: $246.00
Remaining: $9754.00
__________________________________________
```

**Example 2:** `check-budget c/Food`

**Expected Output 2 :**
```
__________________________________________
Budget for Food

Total Budget: $10000.00
Spent: $0.00
Remaining: $10000.00
__________________________________________
```

### Editing Budget: `edit-budget`
Modifies an existing budget by updating its limit, name, or both.

**Format:** `edit-budget old/<CURRENT_NAME> a/<NEW_AMOUNT> c/<NEW_NAME>`

* `CURRENT_NAME` must be an existing budget name.  
* `NEW_AMOUNT` is optional but must be a positive number less than 100000 to update the budget limit. 
* `NEW_NAME` is optional but must be non-empty if provided. 
* If `NEW_NAME` is provided, the budget will be renamed accordingly.
* At least one of the two, new amount or new name must be specified.
* To edit overall budget, use `old/Overall`, however, it cannot be renamed.

**Example 1:** `edit-budget old/Food a/500`

**Expected Output 1:**
```
__________________________________________
Updated limit of budget 'Food' to $500.0
__________________________________________
```

**Example 2:** `edit-budget old/Food c/Groceries`

**Expected Output 2:**
```
__________________________________________
Renamed budget 'Food' to 'Groceries'
__________________________________________
```

**Example 3:** `edit-budget old/Food a/500 c/Groceries`

**Expected Output 3:**
```
__________________________________________
Updated limit of budget 'Food' to $1000.0
__________________________________________
__________________________________________
Renamed budget 'Food' to 'Groceries'
__________________________________________
```

**Example 4:** `edit-budget old/Overall a/500`

**Expected Output 4:**
```
__________________________________________
Budget limit for Overall updated to: $1232.0
__________________________________________
```

### Summary of Budget: `summary`
View a summarized budget across all categories or for selected ones. 
The summary includes the total expenses, remaining budget, and spending limit for each category.

**Format:**
`summary [c/<CATEGORY1> c/<CATEGORY2>... ]`

* If no category is specified, the summary will cover **all existing budgets**.
* If specific categories are included using the `c/` marker, only those categories will be shown.
* If a specified category does not exist, an error will be shown.
* If the total expenses exceed the budget limit, the remaining budget will be shown as 0.
* Commas **should not** be placed between categories.

**Example 1 (summary across all budgets):**
`summary`

**Expected Output 1:**
```
___________________________________________
Budget Summary:
Category: Overall 
Total Expenses: $300.00
Remaining Budget: $200.00 
Spending Limit: $500.00

Category: Food 
Total Expenses: $120.00 
Remaining Budget: $30.00 
Spending Limit: $150.00
___________________________________________
```

**Example 2 (summary of specific categories):**
`summary c/Food c/Transport`

**Expected Output 2:**
````
___________________________________________
Budget Summary:

Category: Food 
Total Expenses: $120.00 
Remaining Budget: $30.00 
Spending Limit: $150.00

Category: Transport 
Total Expenses: $50.00 
Remaining Budget: $100.00 
Spending Limit: $150.00
___________________________________________
````
**Error Example (invalid category):**
`summary c/Repairs`

**Expected Output:**
```
__________________________________________
Invalid input format: Category 'Repairs' does not exist.
__________________________________________
```

### Add Alert: `alert`
Sets a budget alert to notify the user when expenses exceed a specific limit.

**Format:** `alert <AMOUNT>`

* The `AMOUNT` can be any positive number less than 100000.
* Any `AMOUNT` with more than 2 decimal places will be rounded to 2 decimal places.
* The budget alert remains active until it is manually updated or removed
* To remove the alert, input `alert 0`

**Example 1:**
`alert 100`

**Expected Outcome 1:**
```
___________________________________________
Budget alert set at $100.0. You will be notified if expenses exceed this amount.
___________________________________________
```

**Example 2:** `alert 0`

**Expected Outcome 2:**
```
___________________________________________
Budget alert has been removed.
___________________________________________
```

### Delete Alert: `delete-alert`
Removes the budget alert set by the user.

**Format:** `delete-alert`

* The alert will be removed, and the user will no longer receive notifications when expenses exceed the set limit.
* This command is a placeholder and does not require any additional parameters.
* Extraneous parameters are not allowed. Only input exactly `delete-alert`.

**Example:** `delete-alert`

**Expected Output:**
```
___________________________________________
Budget alert has been removed.
___________________________________________
```

### Find: `find`
Searches for expenses in the Overall budget using a keyword. 

**Format:** `find <KEYWORD>`

* `<KEYWORD>` is the search term used to match expenses.
* The command displays all expenses that contain the keyword in their description. 
* Only one word is allowed as a keyword.

**Example 1:** `find food`

**Expected Output 1:**
```
__________________________________________
Expenses Matching: 'food'
No matching expenses found for keyword: food
__________________________________________
```

**Example 2:**`find Chicken`

**Expected Output 2:**
```
__________________________________________
Expenses Matching: 'Chicken'
1. $13.00 spent on Chicken Rice (Apr 08 2025 at 03:03)
__________________________________________
```

### Help: `help`
View all available commands in Budget Buddy, including their functions and formats.

**Format:** `help`

* The command will ignore any extraneous parameters and display the help information.

**Example:** `help`

**Expected Outcome:**
```
__________________________________________
Available Commands:

Add Expense: add
Format: add [AMOUNT] c/[CATEGORY] d/[DESCRIPTION] t/[MMM dd yyyy 'at' hh:mm]
Examples: add 15.50 c/Food d/Lunch t/Oct 05 2025 at 12:30, 
          add 40 c/Transport d/Taxi Ride t/Oct 10 2025 at 14:35

Add Recurring Expense: add-recurring
Format: add-recurring [AMOUNT] c/[CATEGORY] d/[DESCRIPTION] t/[TIME] f/[FREQUENCY] i/[ITERATIONS]
Examples: add-recurring 20 c/Food d/Lunch t/Apr 24 2025 at 12:00 f/30 i/5

Delete Expense: delete
Format: delete [INDEX]
Examples: delete 2
          delete 5

View Expenses: list
Format: list start/[START_TIME] end/[END_TIME]
Example: list
         list start/Apr 24 2025 at 12:00 , 
         list start/Apr 24 2025 at 12:00 end/May 01 2025 at 12:00

Edit Expense: edit-expense
Format: edit-expense INDEX a/[AMOUNT] d/[DESCRIPTION] t/[MMM dd yyyy 'at' hh:mm]
Examples: edit-expense 1 a/25.00, 
          edit-expense 2 d/Dinner t/Nov 15 2025 at 19:00

Set Budget: set-budget
Format: set-budget [AMOUNT] 
          set-budget c/[CATEGORY] [AMOUNT]
Examples: set-budget 1000
          set-budget c/Food 300

Check Budget: check-budget
Format: check-budget c/[CATEGORY]
Examples: check-budget
          check-budget c/Groceries

Edit Budget: edit-budget
Format: edit-budget old/[CURRENT_NAME] a/[NEW_AMOUNT] c/[NEW_NAME]
Examples: edit-budget old/Food a/500, 
          edit-budget old/Food c/Groceries

Budget Summary: summary
Format: summary c/[CATEGORY1] c/[CATEGORY2] ...
Examples: summary 
          summary c/Food
          summary c/Food c/Transport

Set Alert: alert
Format: alert [AMOUNT]
Examples: alert 500
          alert 0

Delete Alert: delete-alert
Format: delete-alert
Example: delete-alert

Find Expenses: find
Format: find [KEYWORD]
Example: find coffee

Exit Program: bye
Format: bye
Example: bye
__________________________________________
```

### Bye: `bye`
Exits the program.

**Format:** `bye`
* The program will save all data before exiting.
* The program will ignore any extraneous parameters behind `bye`.

*Example 1:* `bye`

**Expected Output 1:**
```
___________________________________________
Thank you for using Budget Buddy.
Goodbye!
___________________________________________
```

*Example 2:* `bye 123`

**Expected Output 2:**
```
___________________________________________
Thank you for using Budget Buddy.
Goodbye!
___________________________________________
```

## FAQ

> **Q**: How do I transfer my data to another computer? 

**A**: You can navigate to your root folder, and find the file `budget_data.txt`. Transfer the file to your other 
computer and put it in your root folder.

> **Q**: How do I add an expense without a category?

**A**: Use 'add 10.50 c/ d/Lunch t/' - it will default to "Overall" category and current time.

> **Q**: Why does my date/time reset to current time?

**A**: Required format: 'MMM dd yyyy at HH:mm' (e.g. 'Jan 15 2025 at 14:30').

> **Q**: How to delete multiple expenses?

**A**: Run 'delete' for each index (e.g. 'delete 3' then 'delete 5').

> **Q**: What happens if I exceed my budget?

**A**: Set alerts with 'alert 500'. Check funds with 'check-budget'.

> **Q**: Why does 'find coffee' return nothing?
 
**A**: Searches match exact keywords (try 'find cafe').

> **Q**: Can I use decimal amounts? 
 
**A**: Yes! (e.g. 'add 4.99', 'set-budget 200.50').


## Command Summary

| Command           | Format                                                                                                              |
|-------------------|---------------------------------------------------------------------------------------------------------------------|
| **add**           | `add <AMOUNT> c/<CATEGORY> d/<DESCRIPTION> t/<DATE_TIME>`                                                           |
| **add-recurring** | `add-recurring <AMOUNT> c/ <CATEGORY> d/ <DESCRIPTION> t/ <START DATE TIME> f/ <FREQUENCY_IN_DAYS> i/ <ITERATIONS>` |
| **delete**        | `delete <INDEX>`                                                                                                    |
| **list**          | `list [start/<TIME>] [end/<TIME>]`                                                                                  |
| **edit-expense**  | `edit-expense <INDEX> [a/<AMOUNT>] [d/<DESCRIPTION>] [t/<DATE_TIME>]`                                               |
| **set-budget**    | `set-budget <AMOUNT>` or `set-budget c/<CATEGORY> <AMOUNT>`                                                         |
| **check-budget**  | `check-budget [c/<CATEGORY>]`                                                                                       |
| **edit-budget**   | `edit-budget old/<CURRENT_NAME> [a/<NEW_AMOUNT>] [c/<NEW_NAME>]`                                                    |
| **summary**       | `summary [c/<CATEGORY1> c/<CATEGORY2>... ]`                                                                         |
| **alert**         | `alert <AMOUNT>`                                                                                                    |
| **find**          | `find <KEYWORD>`                                                                                                    |
| **help**          | `help`                                                                                                              |
| **bye**           | `bye`                                                                                                               |