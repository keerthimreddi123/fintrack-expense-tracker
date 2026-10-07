import { defineConfig, devices } from '@playwright/test';

export default defineConfig({
  // All specs live under tests/ (the sample here; the hidden eval battery is dropped into
  // tests/eval/ by the grader). Playwright discovers *.spec.ts recursively.
  testDir: '.',

  // The specs share one real backend + SQLite database and several assert on absolute row
  // counts / running totals, so they must run serially — never across parallel workers.
  fullyParallel: false,
  workers: 1,

  // JUnit XML so the CI `integration` stage can surface per-test results, plus a console list.
  reporter: [
    ['junit', { outputFile: 'results/results.xml' }],
    ['list'],
  ],

  use: {
    // The frontend is served on :4200 and proxies /api to the backend on :8080, so the tests
    // only need the UI base URL. Override with PLAYWRIGHT_BASE_URL in CI.
    baseURL: process.env.PLAYWRIGHT_BASE_URL || 'http://localhost:4200',
    actionTimeout: 10000,
    screenshot: 'only-on-failure',
    trace: 'on-first-retry',
  },

  projects: [
    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] },
    },
  ],
});
