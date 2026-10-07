/** @vitest-environment jsdom */
import { afterEach, beforeEach, describe, expect, it, vi, Mock } from 'vitest';
import { expenseApi } from './expense-api.service';

/**
 * PUBLIC tests — visible to you. They describe the contract expenseApi must satisfy.
 * Run with `npm test`. The hidden eval.spec.tsx adds the component-level checks.
 */

const jsonResponse = (body: unknown) =>
  new Response(JSON.stringify(body), {
    status: 200,
    headers: { 'Content-Type': 'application/json' },
  });

describe('expenseApi', () => {
  let fetchMock: Mock;

  beforeEach(() => {
    fetchMock = vi.fn();
    vi.stubGlobal('fetch', fetchMock);
  });

  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it('is exported with all three methods', () => {
    expect(expenseApi.getExpenses).toBeTypeOf('function');
    expect(expenseApi.getSummary).toBeTypeOf('function');
    expect(expenseApi.addExpense).toBeTypeOf('function');
  });

  it('getExpenses() issues GET /api/expenses', async () => {
    fetchMock.mockResolvedValue(jsonResponse([]));
    await expenseApi.getExpenses();
    expect(fetchMock).toHaveBeenCalledTimes(1);
    const [url, init] = fetchMock.mock.calls[0];
    expect(url).toBe('/api/expenses');
    expect(init?.method ?? 'GET').toBe('GET');
  });

  it('getSummary() issues GET /api/expenses/summary and returns the body', async () => {
    fetchMock.mockResolvedValue(jsonResponse({ total: 42 }));
    const summary = await expenseApi.getSummary();
    expect(fetchMock.mock.calls[0][0]).toBe('/api/expenses/summary');
    expect(summary).toEqual({ total: 42 });
  });

  it('addExpense() POSTs to /api/expenses with the payload', async () => {
    const payload = { description: 'Coffee', category: 'Food', amount: 4.5, incurredOn: '2026-06-01' };
    fetchMock.mockResolvedValue(jsonResponse({ id: 1, ...payload }));
    const created = await expenseApi.addExpense(payload);
    const [url, init] = fetchMock.mock.calls[0];
    expect(url).toBe('/api/expenses');
    expect(init?.method).toBe('POST');
    expect(JSON.parse(init?.body as string)).toEqual(payload);
    expect(created).toMatchObject({ id: 1, description: 'Coffee' });
  });
});
