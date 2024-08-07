import React, { useState } from 'react';
import { Drawer } from '@mui/material';
import { LogoutRounded, AccountCircleOutlined, ViewWeekOutlined, HomeOutlined } from '@mui/icons-material';
import NotFound from './_NotFound';
import './../Css/Dashboard.css';
import HomeContent from './DashboardViews/_HomeContent';  // Importar componente HomeContent
import TableContent from './DashboardViews/_TableContent';  // Importar componente TableContent
import ProfileContent from './DashboardViews/_ProfileContent';  // Importar componente ProfileContent

const Dashboard = ({ role }) => {
  const [content, setContent] = useState('home');

  const handleLogout = () => {
    localStorage.removeItem('userRole');
    window.location.pathname = '/login';
  };

  const handleHomeClick = () => {
    setContent('home');
  };

  const handleViewClick = () => {
    setContent('view');
  }

  const handleProfileClick = () => {
    setContent('profile');
  };

  if (role === 0 || role === null) {
    return <NotFound />;
  }

  const renderContent = () => {
    switch(content) {
      case 'home':
        return <HomeContent />;
      case 'view':
        return <TableContent />;
      case 'profile':
        return <ProfileContent />;
      default:
        return <HomeContent />;
    }
  };

  // Styles for the selected button
  const buttonStyle = (selected) => ({
    backgroundColor: selected ? '#512da8' : 'transparent',
    color: selected ? 'white' : 'black',
    borderRadius: '5px',
    padding: '10px',
    border: 'none',
    cursor: 'pointer',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    margin: '5px 0',
  });

  return (
    <div className="appContainer">
      <Drawer variant='permanent' anchor="left" classes={{ paper: 'drawerPaper' }}>
        <div className="drawerContent">
          <div>
            <button
              onClick={handleHomeClick}
              style={buttonStyle(content === 'home')}
            >
              <HomeOutlined sx={{ fontSize: 35, color: content === 'home' ? 'white' : 'inherit' }} />
            </button>
            <button
              onClick={handleViewClick}
              style={buttonStyle(content === 'view')}
            >
              <ViewWeekOutlined sx={{ fontSize: 30, color: content === 'view' ? 'white' : 'inherit' }} />
            </button>
            <button
              onClick={handleLogout}
              style={buttonStyle(false)}
            >
              <LogoutRounded sx={{ fontSize: 30 }} />
            </button>
          </div>
        </div>
      </Drawer>
      <div className="mainContent">
        <div className="mainBox">
          {renderContent()}
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
