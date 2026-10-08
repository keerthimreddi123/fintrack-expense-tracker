import {
  forwardRef,
  useCallback,
  useEffect,
  useImperativeHandle,
  useMemo,
  useState,
} from 'react';

import { Expense } from '../models/expense.model';
import { expenseApi } from '../services/expense-api.service';

export interface ExpenseListHandle {
  reload: () => void;
}

export const ExpenseList = forwardRef<ExpenseListHandle>(
  function ExpenseList(_props, ref) {
    const initialExpenses =
  typeof window !== 'undefined'
    ? ((window as any).__FINTRACK_INITIAL_EXPENSES__ as Expense[] | undefined) ?? []
    : [];

const initialTotal =
  typeof window !== 'undefined'
    ? Number((window as any).__FINTRACK_INITIAL_TOTAL__ ?? 0)
    : 0;
    const [expenses, setExpenses] =
  useState<Expense[]>(initialExpenses);

const [total, setTotal] =
  useState<number>(initialTotal);
    const [categoryFilter, setCategoryFilter] = useState('');
    const [error, setError] = useState('');

    const reload = useCallback(async () => {
      try {
        setError('');

        const [expenseData, summaryData] = await Promise.all([
          expenseApi.getExpenses(),
          expenseApi.getSummary(),
        ]);

        setExpenses(expenseData);
        setTotal(summaryData.total);
      } catch (err) {
        setError(
          err instanceof Error ? err.message : 'Failed to load expenses'
        );
      }
    }, []);

    useImperativeHandle(
      ref,
      () => ({
        reload,
      }),
      [reload]
    );

    useEffect(() => {
      reload();
    }, [reload]);

    const filteredExpenses = useMemo(() => {
      const filter = categoryFilter.trim().toLowerCase();

      if (!filter) {
        return expenses;
      }

      return expenses.filter(
        (expense) => expense.category.toLowerCase() === filter
      );
    }, [expenses, categoryFilter]);

    return (
      <section>
        <h2>Expenses</h2>

        <div>
          <label htmlFor="category-filter">Filter by category: </label>
          <input
            id="category-filter"
            data-cy="category-filter"
            type="text"
            value={categoryFilter}
            onChange={(event) => setCategoryFilter(event.target.value)}
            placeholder="e.g. Food"
          />
        </div>

        {error && <p role="alert">{error}</p>}

        <table>
          <thead>
            <tr>
              <th>Description</th>
              <th>Category</th>
              <th>Amount</th>
              <th>Date</th>
            </tr>
          </thead>

          <tbody>
            {filteredExpenses.map((expense) => (
              <tr key={expense.id} data-cy="expense-row">
                <td>{expense.description}</td>
                <td>{expense.category}</td>
                <td data-cy="row-amount">{Number(expense.amount).toFixed(2)}
                  </td>                
                <td>{expense.incurredOn}</td>
              </tr>
            ))}
          </tbody>
        </table>

        <p>
          Total:{' '}
          <strong data-cy="total">
            {Number(total).toFixed(2)}
          </strong>
        </p>
      </section>
    );
  }
);