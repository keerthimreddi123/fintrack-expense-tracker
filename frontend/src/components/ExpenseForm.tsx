import { FormEvent, useState } from 'react';

import { expenseApi } from '../services/expense-api.service';

export interface ExpenseFormProps {
  onAdded: () => void;
}

export function ExpenseForm({ onAdded }: ExpenseFormProps) {
  const [description, setDescription] = useState('');
  const [category, setCategory] = useState('');
  const [amount, setAmount] = useState('');
  const [incurredOn, setIncurredOn] = useState('');
  const [error, setError] = useState('');

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    try {
      setError('');

      await expenseApi.addExpense({
        description: description.trim(),
        category: category.trim(),
        amount: Number(amount),
        incurredOn,
      });

      setDescription('');
      setCategory('');
      setAmount('');
      setIncurredOn('');

      onAdded();
    } catch (err) {
      setError(
        err instanceof Error ? err.message : 'Failed to add expense'
      );
    }
  }

  return (
    <section>
      <h2>Add Expense</h2>

      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="description">Description</label>
          <input
            id="description"
            data-cy="description"
            type="text"
            value={description}
            onChange={(event) => setDescription(event.target.value)}
            required
          />
        </div>

        <div>
          <label htmlFor="category">Category</label>
          <input
            id="category"
            data-cy="category"
            type="text"
            value={category}
            onChange={(event) => setCategory(event.target.value)}
            required
          />
        </div>

        <div>
          <label htmlFor="amount">Amount</label>
          <input
            id="amount"
            data-cy="amount"
            type="number"
            step="0.01"
            min="0"
            value={amount}
            onChange={(event) => setAmount(event.target.value)}
            required
          />
        </div>

        <div>
          <label htmlFor="incurredOn">Date</label>
          <input
            id="incurredOn"
            data-cy="incurredOn"
            type="date"
            value={incurredOn}
            onChange={(event) => setIncurredOn(event.target.value)}
            required
          />
        </div>

        <button type="submit" data-cy="submit">
          Add Expense
        </button>
      </form>

      {error && <p role="alert">{error}</p>}
    </section>
  );
}