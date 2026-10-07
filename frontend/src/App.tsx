import { useRef } from 'react';
import { ExpenseForm } from './components/ExpenseForm';
import { ExpenseList, ExpenseListHandle } from './components/ExpenseList';

export function App() {
  const list = useRef<ExpenseListHandle>(null);

  return (
    <>
      <h1>FinTrack</h1>
      <ExpenseForm onAdded={() => list.current?.reload()} />
      <ExpenseList ref={list} />
    </>
  );
}
