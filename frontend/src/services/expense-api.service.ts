import { Expense, ExpenseSummary, NewExpense } from '../models/expense.model';

/**
 * Talks to the FinTrack REST API. Use relative `/api/...` URLs (the Vite dev-server proxy
 * forwards them — see vite.config.ts).
 *
 * TODO: implement the three methods below with `fetch`:
 *   getExpenses(): Promise<Expense[]>              -> GET  /api/expenses
 *   getSummary():  Promise<ExpenseSummary>         -> GET  /api/expenses/summary
 *   addExpense(payload: NewExpense): Promise<Expense> -> POST /api/expenses
 * POST requests must send a JSON body with the 'Content-Type: application/json' header.
 */
export const expenseApi = {
  getExpenses(): Promise<Expense[]> {
    throw new Error('TODO: implement getExpenses()');
  },

  getSummary(): Promise<ExpenseSummary> {
    throw new Error('TODO: implement getSummary()');
  },

  addExpense(_payload: NewExpense): Promise<Expense> {
    throw new Error('TODO: implement addExpense()');
  },
};
