import { test, expect } from '@playwright/test';
import { addExpense, total, rowCount } from './support/helpers';

/**
 * PUBLIC SAMPLE E2E spec — shipped so you can see the shape of the end-to-end checks and the
 * `data-cy` hooks your UI must expose. This is just one example.
 *
 * At grading time the grader REPLACES the entire `tests/` spec set with a much larger hidden
 * battery of eval specs (create / persistence / validation / filtering / summary / precision /
 * API-contract / empty-state). Build to the documented contract, not to this file.
 *
 * Contract the UI must honour (data-cy hooks):
 *   form inputs: description, category, amount, incurredOn ; button: submit
 *   list:        one [data-cy="expense-row"] per expense, with the amount cell marked
 *                [data-cy="row-amount"] ; total in [data-cy="total"]
 */
test.describe('[SAMPLE] Expense create flow (UI -> API -> DB -> UI)', () => {
  test('adds an expense and reflects it in the list and total', async ({ page }) => {
    await page.goto('/');

    const startCount = await rowCount(page);
    const startTotal = await total(page);

    const desc = `Coffee ${Date.now()}`;
    await addExpense(page, { description: desc, category: 'Food', amount: '4.50', incurredOn: '2026-06-01' });

    await expect(page.locator('[data-cy="expense-row"]', { hasText: desc })).toBeVisible();
    await expect(page.locator('[data-cy="expense-row"]')).toHaveCount(startCount + 1);
    await expect.poll(() => total(page)).toBeCloseTo(startTotal + 4.5, 2);
  });
});
