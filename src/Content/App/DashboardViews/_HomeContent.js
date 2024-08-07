import React from 'react';
import {  } from '@mui/material';
import { LineChart } from '@mui/x-charts/LineChart';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { styled } from '@mui/material/styles';

import './../../Css/HomeContent.css';

const StyledDateCalendar = styled(DateCalendar)(({ theme }) => ({
  pointerEvents: 'none', // Make the calendar non-interactive
  height: '300px', // Set the height of the calendar
  width: '300px', // Set the width of the calendar
  padding: '0px', // Add padding to the calendar
  margin: '0px', // Add margin to the calendar
  backgroundColor: 'none', // Background color of the calendar
  borderRadius: '20px', // Border radius of the calendar
  '& .MuiPickersDay-root': {
    color: '#black', // Font color of the days
    fontSize: '1rem', // Font size of the days
  },
  '& .MuiPickersArrowSwitcher-root': {
    display: 'none', // Hide navigation arrows
  },
  '& .MuiPickersDay-today': {
    color: 'white', // Font color of the today's date
    backgroundColor: '#512da8', // Background color of the circle
    borderRadius: '20px', // Ensures the background is a circle
    boxShadow: '0 5px 15px rgba(0, 0, 0, 0.35)',
  }
}));

const arrX = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
const arrY = [2, 3, 5.5, 8, 1.5, 5, 1, 4, 3, 8];

const HomeContent = () => {
  return (
    <div class="homePage">
      <div class="firstColumn">
          <div class="firstColumnTop">
            <div class="firstColumnTopContent">
              <h1>Hello, welcome!</h1>
              <br></br>
              <p>
                <i>"Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful."</i>
                <br></br> 
                <br></br>
                Albert Schweitzer
              </p>
            </div>
          </div>
          <div class="firstColumnBot">
              <LocalizationProvider dateAdapter={AdapterDayjs}>
                  <StyledDateCalendar readOnly />
              </LocalizationProvider>
          </div>
      </div>
      <div class="secondColumn">
        <div class="secondColumnTop">
          <div class="secondColumnTopAbove">
            <div class="statsWrapperTop">
              <div class="stats">
                <div style={{backgroundColor: '#D1D3F4'}} class="stats-left">
                  
                </div>
                <div class="stats-right">
                  <p>contacts</p>
                  <h2>10000</h2>
                </div>
              </div>
              <div class="stats">
                <div style={{backgroundColor: '#F0F5E4'}} class="stats-left">
                  
                </div>
                <div class="stats-right">
                  <p>countries</p>
                  <h2>5</h2>
                </div>
              </div>
            </div>
            <div class="statsWrapperTop">
              <div class="stats">
                <div style={{backgroundColor: '#C5E7E5'}} class="stats-left">

                </div>
                <div class="stats-right">
                  <p>influencers</p>
                  <h2>1230</h2>
                </div>
              </div>
              <div class="stats">
                <div style={{backgroundColor: '#EFE7C9'}} class="stats-left">
                  
                </div>
                <div class="stats-right">
                  <p>last backup</p>
                  <h2>1d</h2>
                </div>
              </div>
            </div>
            <div class="statsWrapperTop">
              <div class="stats">
                <div style={{backgroundColor: '#E7F8EB'}} class="stats-left">

                </div>
                <div class="stats-right">
                  <p>companies</p>
                  <h2>105</h2>
                </div>
              </div>
              <div class="stats">
                <div style={{backgroundColor: '#F4D9CF'}} class="stats-left">
                  
                </div>
                <div class="stats-right">
                  <p>users</p>
                  <h2>6</h2>
                </div>
              </div>
            </div>
          </div>
          <div class="statsWrapper">
              <div >
                <p>added</p>
                <h2>34</h2>
              </div>
              <div >
                <p>modified</p>
                <h2>12</h2>
              </div>
              <div >
                <p>deleted</p>
                <h2>3</h2>
              </div>
              <div >
                <p>exported</p>
                <h2>5</h2>
              </div>
          </div>
          <div class="chartWrapper">
            <LineChart 
              xAxis={[{
                  data: arrX,
                  stroke: '#000000', // Color for the x-axis
                  disableLine: true, // Hides the x-axis line
                  disableTicks: true, // Hides the x-axis ticks
                  tickLabelStyle: { display: 'none' }, // Hides the x-axis labels
                }
              ]}
              yAxis={[
                {
                  stroke: '#000000', // Color for the y-axis
                  disableLine: true, // Hides the y-axis line
                  disableTicks: true, // Hides the y-axis ticks
                }
              ]}
              series={[
                {
                  data: arrY, // Data for the line
                  type: 'line', // Type of series
                  showMark: false, // Hides the data points
                }
              ]}
              colors={['#512da8']} // Color for the line
            />
          </div>
          
        </div>
      </div>
  </div>


  );
};

export default HomeContent;