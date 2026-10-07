import { forwardRef } from 'react';

/** Imperative API the parent uses to refresh the list (see App.tsx) — part of the contract. */
export interface ExpenseListHandle {
  reload: () => void;
}

/**
 * Shows the expense table, the running total, and a category filter.
 *
 * TODO:
 *  - load expenses + summary on mount (implement reload())
 *  - keep `expenses`, `total`, and `error` in state
 *  - expose `reload` to the parent via useImperativeHandle (see App.tsx) —
 *    it is called after a new expense is added
 *
 * The E2E test looks for:
 *   - a table with one row per expense (use data-cy="expense-row")
 *   - the running total in an element with data-cy="total"
 */
export const ExpenseList = forwardRef<ExpenseListHandle>(function ExpenseList(_props, _ref) {
  // TODO: implement state + reload(), and wire the ref handle.
  return <p>TODO: implement the expense list.</p>;
});
