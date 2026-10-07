// PROVIDED. The API request/response shapes — do not change field names.

export interface Expense {
  id: number;
  description: string;
  category: string;
  amount: number;
  incurredOn: string; // ISO date, e.g. "2026-06-01"
}

// Payload for creating an expense (no id yet).
export type NewExpense = Omit<Expense, 'id'>;

export interface ExpenseSummary {
  total: number;
}
