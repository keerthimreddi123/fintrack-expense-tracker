export interface ExpenseFormProps {
  /** Fired after a successful POST so the list reloads. */
  onAdded: () => void;
}

/**
 * Controlled form to add an expense. On successful POST, call `onAdded` so the list reloads.
 *
 * TODO:
 *  - keep description / category / amount / incurredOn in component state (controlled inputs)
 *  - on submit, call expenseApi.addExpense(...), clear the form, and call `onAdded` on success
 *
 * The E2E test fills inputs by data-cy and clicks submit:
 *   data-cy="description", data-cy="category", data-cy="amount", data-cy="incurredOn", data-cy="submit"
 */
export function ExpenseForm(_props: ExpenseFormProps) {
  // TODO: implement the controlled form + submit + the onAdded callback.
  return <p>TODO: implement the expense form.</p>;
}
