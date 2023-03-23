import React from 'react';

import { Map, Sidebar } from 'core/components';

import { useAppDispatch } from 'app/typings/redux';

import { start } from 'store/restrooms/slice';

import 'style/main.css';

import './index.css';

const MainPage = (): JSX.Element => {
  const dispatch = useAppDispatch();

  React.useEffect(() => {
    dispatch(start());
  }, []);

  return (
    <main className="mainContainer">
      <Sidebar />
      <Map />
    </main>
  );
};

export default MainPage;
