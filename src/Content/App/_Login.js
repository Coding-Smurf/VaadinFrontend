import React, { useRef, useEffect } from 'react';
import './../Css/Login.css';

const Login = ({ setAuth }) => {

  // Create refs for the container and buttons
  const containerRef = useRef(null);
  const registerBtnRef = useRef(null);
  const loginBtnRef = useRef(null);

  useEffect(() => {
    const container = containerRef.current;
    const registerBtn = registerBtnRef.current;
    const loginBtn = loginBtnRef.current;

    if (container && registerBtn && loginBtn) {
      const handleRegisterClick = () => {
        container.classList.add('active');
      };

      const handleLoginClick = () => {
        container.classList.remove('active');
      };

      registerBtn.addEventListener('click', handleRegisterClick);
      loginBtn.addEventListener('click', handleLoginClick);

      // Cleanup event listeners on unmount
      return () => {
        if (registerBtn && loginBtn) {
          registerBtn.removeEventListener('click', handleRegisterClick);
          loginBtn.removeEventListener('click', handleLoginClick);
        }
      };
    }
  }, []); // Empty dependency array ensures this runs once on mount

  const handleLogin = async (e) => { //!TODO IMPLEMENT LOGIN LOGIC AND API CALL

    const result = { status: 0 };

    e.preventDefault();

    
    const username = e.target[0].value;
    const password = e.target[1].value;
    

    if (!username || !password) {
      alert('Please enter both username and password');
      return;
    }

    if (username === 'admin' && password === 'admin') {
      localStorage.setItem('userRole', 2);
      result.status = 2;
      window.location.pathname = '/dashboard';
      return;
    }
    
    if (result.status === 1 || result.status === 2) {
      localStorage.setItem('userRole', result.status);
      setAuth(result.status);
      window.location.pathname = '/dashboard';
    } else {
      alert('Login failed');
    }
  };

  return (
    <div className="bodyBackground">
      <div className="container" ref={containerRef}>
        <div className="form-container sign-up">
          <form>
            <h1>Request Account</h1>
            <div className="social-icons">
              <a href='https://www.google.com/' className="icon"><i className="fa-brands fa-google-plus-g"></i></a>
              <a href='https://www.google.com/' className="icon"><i className="fa-brands fa-facebook-f"></i></a>
              <a href='https://www.google.com/' className="icon"><i className="fa-brands fa-github"></i></a>
              <a href='https://www.google.com/' className="icon"><i className="fa-brands fa-linkedin-in"></i></a>
            </div>
            <input type="text" placeholder="Name"/>
            <input type="text" placeholder="Surname"/>
            <input type="email" placeholder="Email" />
            <button type="button">Send request</button>
          </form>
        </div>
        <div className="form-container sign-in">
          <form onSubmit={handleLogin}>
            <h1>Sign In</h1>
            <div className="social-icons">
              <a href='https://www.google.com/' className="icon"><i className="fa-brands fa-google-plus-g"></i></a>
              <a href='https://www.google.com/' className="icon"><i className="fa-brands fa-google-plus-g"></i></a>
              <a href='https://www.google.com/' className="icon"><i className="fa-brands fa-google-plus-g"></i></a>
              <a href='https://www.google.com/' className="icon"><i className="fa-brands fa-google-plus-g"></i></a>
            </div>
            <input type="text" placeholder="Username" />
            <input type="password" placeholder="Password" />
            <a href='https://www.google.com/'>Forgot your password?</a>
            <button type="submit">Sign In</button>
          </form>
        </div>
        <div className="toggle-container">
          <div className="toggle">
            <div className="toggle-panel toggle-left">
              <h2>Already got an account and just forgot !?</h2>
              <p>
                Happens to the best of us!
                <br/>
                Click the button below to go to the login page and get back on track!</p>
              <button className="hidden" ref={loginBtnRef}>Sign In</button>
            </div>
            <div className="toggle-panel toggle-right">
              <h2>Can't remember if you have an account !?</h2>
              <p>
                Don't worry, we can help you with that.
                <br/>
                Hit the button below and ask our team to check for you.
              </p>
              <button className="hidden" ref={registerBtnRef}>Notify the admin</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
