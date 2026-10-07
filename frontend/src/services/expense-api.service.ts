import { Expense, ExpenseSummary, NewExpense } from '../models/expense.model';

export const expenseApi = {
  async getExpenses(): Promise<Expense[]> {
    const response = await fetch('/api/expenses');

    if (!response.ok) {
      throw new Error('Failed to load expenses');
    }

    return response.json();
  },

  async getSummary(): Promise<ExpenseSummary> {
    const response = await fetch('/api/expenses/summary');

    if (!response.ok) {
      throw new Error('Failed to load expense summary');
    }

    return response.json();
  },

  async addExpense(payload: NewExpense): Promise<Expense> {
    const response = await fetch('/api/expenses', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(payload),
    });

    if (!response.ok) {
      throw new Error('Failed to add expense');
    }

    return response.json();
  },
};