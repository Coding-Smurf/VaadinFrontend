import React, { useState, useEffect } from 'react';
import Login from './_Login';
import Dashboard from './_Dashboard';
import NotFound from './_NotFound';

const App = () => {
  const [role, setRole] = useState(() => {
    return parseInt(localStorage.getItem('userRole'), 10) || null;
  });

  useEffect(() => {
    const handleRoute = () => {
      const path = window.location.pathname;
      if (path === '/login') {
        return;
      }

      if (!role && path !== '/login') {
        window.location.pathname = '/login';
      } else if (path === '/dashboard') {
        if (role === 1 || role === 2) {
          return;
        } else {
          window.location.pathname = '/login';
        }
      } else if (path !== '/login' && path !== '/dashboard') {
        window.location.pathname = '/404';
      }
    };

    handleRoute();
  }, [role]);

  const renderPage = () => {
    const path = window.location.pathname;
    if (path === '/login') {
      return <Login setAuth={setRole} />;
    } else if (path === '/dashboard') {
      return <Dashboard role={role} />;
    } else if (path === '/404') {
      return <NotFound />;
    }
  };

  return <div>{renderPage()}</div>;
};

export default App;
