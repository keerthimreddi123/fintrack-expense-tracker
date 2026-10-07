import { Page } from '@playwright/test';

// Shared helpers used by the sample spec and the hidden E2E eval specs — the Playwright
// equivalent of the Cypress custom commands the suite used to ship.

export interface ExpenseInput {
  description: string;
  category: string;
  amount: string;
  incurredOn: string;
}

/** Fill the add-expense form and submit it. */
export async function addExpense(page: Page, e: ExpenseInput): Promise<void> {
  await page.locator('[data-cy="description"]').fill(e.description);
  await page.locator('[data-cy="category"]').fill(e.category);
  await page.locator('[data-cy="amount"]').fill(e.amount);
  await page.locator('[data-cy="incurredOn"]').fill(e.incurredOn);
  await page.locator('[data-cy="submit"]').click();
}

/** Current running total parsed from [data-cy="total"]. */
export async function total(page: Page): Promise<number> {
  const text = await page.locator('[data-cy="total"]').innerText();
  return parseFloat(text.replace(/[^0-9.]/g, '')) || 0;
}

/** Current number of rendered expense rows. */
export async function rowCount(page: Page): Promise<number> {
  return page.locator('[data-cy="expense-row"]').count();
}
