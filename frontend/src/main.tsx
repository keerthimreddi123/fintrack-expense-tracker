import React from 'react';
import { flushSync } from 'react-dom';
import { createRoot } from 'react-dom/client';
import { App } from './App';
import type { Expense, ExpenseSummary } from './models/expense.model';
import './styles.css';

function getJsonSync<T>(url: string, fallback: T): T {
  try {
    const request = new XMLHttpRequest();

    request.open('GET', url, false);
    request.send();

    if (
      request.status >= 200 &&
      request.status < 300 &&
      request.responseText
    ) {
      return JSON.parse(request.responseText) as T;
    }
  } catch {
    // ExpenseList will retry asynchronously after mount.
  }

  return fallback;
}

const initialExpenses = getJsonSync<Expense[]>(
  '/api/expenses',
  []
);

const initialSummary = getJsonSync<ExpenseSummary>(
  '/api/expenses/summary',
  { total: 0 }
);

(window as any).__FINTRACK_INITIAL_EXPENSES__ =
  initialExpenses;

(window as any).__FINTRACK_INITIAL_TOTAL__ =
  initialSummary.total;

const root = createRoot(document.getElementById('root')!);

flushSync(() => {
  root.render(
    <React.StrictMode>
      <App />
    </React.StrictMode>
  );
});