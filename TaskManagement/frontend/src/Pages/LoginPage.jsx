import React, { useState } from 'react';
import { Form, Container, Row, Col, Button } from 'react-bootstrap';
import './LoginPage.css';
import img from '../assets/login.jpg';

const LoginPage = () => {
  const [isLogin, setIsLogin] = useState(false);
//     const []
//   const loginUser = async (username, password) => {
//     try {
//       const response = await axios.post(
//         'https://your-api-url.com/login',
//         {
//           username: username,
//           password: password
//         },
//         {
//           headers: {
//             'Content-Type': 'application/json',
//             'Authorization': 'Basic ' + btoa(username + ':' + password)
//           }
//         }
//       );
  
//       // Handle successful login
//       console.log('Login successful:', response.data);
//       return response.data;
  
//     } catch (error) {
//       // Handle errors
//       console.error('Login failed:', error.response ? error.response.data : error.message);
//       throw error;
//     }
//   };
  
//   // Usage in your component
//   const handleLogin = async (e) => {
//     e.preventDefault();
//     try {
//       const userData = await loginUser(username, password);
//       // Handle successful login (e.g., store token, redirect user)
//     } catch (error) {
//       // Handle login error (e.g., show error message to user)
//     }
  return (
    <Container fluid className="login-container">
      <Row className="justify-content-center align-items-center h-100">
        <Col xs={12} sm={8} md={6} lg={4}>
          <div className="login-form-container">
            <h1 className="text-center mb-4">{isLogin ? "Sign Up" : "Login"}</h1>
            <Form>
              <Form.Group className="mb-3">
                <Form.Label>Username</Form.Label>
                <Form.Control type="name" placeholder="Enter username" />
                <Form.Text className="text-muted">
                  Username must be between 5 and 15 characters
                </Form.Text>
              </Form.Group>

              {isLogin && (
                <Form.Group className="mb-3">
                  <Form.Label>Email address</Form.Label>
                  <Form.Control type="email" placeholder="Enter email" />
                  <Form.Text className="text-muted">
                    We'll never share your email with anyone else.
                  </Form.Text>
                </Form.Group>
              )}

              <Form.Group className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password" />
              </Form.Group>

              {isLogin && (
                <Form.Select className="mb-3" aria-label="Default select example">
                  <option>Select Role</option>
                  <option value="1">Admin</option>
                  <option value="2">User</option>
                </Form.Select>
              )}

              <Button variant="primary" type="submit" className="w-100 mb-3">
                {isLogin ? "Sign Up" : "Login"}
              </Button>

              <Container className="text-center">
                <Form.Text className="text-muted">
                  {isLogin ? "Already have an account?" : "Don't have an account?"}
                </Form.Text>{' '}
                <Form.Text
                  className="onToggle-text"
                  style={{ cursor: 'pointer' }}
                  onClick={() => setIsLogin(!isLogin)}
                >
                  {isLogin ? "Login to your existing account" : "Create a new Account"}
                </Form.Text>
              </Container>
            </Form>
          </div>
        </Col>
      </Row>
    </Container>
  );
};

export default LoginPage;