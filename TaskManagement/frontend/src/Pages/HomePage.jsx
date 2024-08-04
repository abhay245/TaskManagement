import React from 'react';
import NavigationBar from '../Components/NavigationBar';
import photo from '../assets/homepage.svg';
import './HomePage.css';
import { Col, Container, Row } from 'react-bootstrap';

const HomePage = () => {
  return (
    <>
      <NavigationBar />
      <Container className="content">
        <Row>
          <Col>
            <h2>Welcome to Taskify: Your Ultimate Task Management Companion! 🚀</h2>
            <h3>Organize, Prioritize, and Conquer Your Tasks</h3>
            <p>
              Stay on top of your to-do list. Taskify is more than just a task manager;
              it’s your personal productivity hub. Whether you’re a busy professional,
              a student juggling assignments, or someone who simply wants to declutter
              their mind, Taskify has you covered.
            </p>
          </Col>
          <Col>
            <img src={photo} alt="Taskify" />
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default HomePage;
