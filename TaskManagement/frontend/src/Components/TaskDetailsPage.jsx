import React from 'react';
import { Container, Row, Col, Card, Badge, Button, CardTitle } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';

const TaskDetailsPage = () => {
  const { id } = useParams(); 
  const navigate = useNavigate();
  const cardData = {
    id: id,
    title: 'Card Title',
    projectName: 'Project Name',
    username: 'Username',
    priority: 'High',
    status: 'In Progress',
    description: 'This is a detailed description of the task. It provides more information about what needs to be done.',
    dueDate: '2023-07-15',
    createdAt: '2023-06-01',
    assignedTo: 'John Doe',
  };

  const handleDelete = () => {
    console.log('Deleting card:', id);
    navigate('/');
  };

  const handleUpdate = () => {
    navigate(`/update/${id}`);
  };

  return (
    <Container fluid className="vh-100 my-5">
<Row className="justify-content-center">
        <Col md={8}>
          <Card>
            <Card.Body>
              <Card.Title as="h2">{cardData.title}</Card.Title>
              <Card.Subtitle className="mb-2 text-muted">Project: {cardData.projectName}</Card.Subtitle>
              <Card.Subtitle className="mb-3">Assigned to: {cardData.assignedTo}</Card.Subtitle>
              
              <div className="mb-3">
                <Badge bg="primary" className="me-2">Priority: {cardData.priority}</Badge>
                <Badge bg="info">Status: {cardData.status}</Badge>
              </div>
              
              <Card.Text>{cardData.description}</Card.Text>
              
              <Row className="mt-4">
                <Col>
                  <strong>Created:</strong> {cardData.createdAt}
                </Col>
                <Col>
                  <strong>Due Date:</strong> {cardData.dueDate}
                </Col>
              </Row>
              
              <div className="mt-4">
                <Button variant="warning" onClick={handleUpdate} className="me-2">Update</Button>
                <Button variant="danger" onClick={handleDelete}>Delete</Button>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default TaskDetailsPage;

/*
      <Row className="justify-content-center">
        <Col md={8}>
          <Card>
            <Card.Body>
              <Card.Title as="h2">{cardData.title}</Card.Title>
              <Card.Subtitle className="mb-2 text-muted">Project: {cardData.projectName}</Card.Subtitle>
              <Card.Subtitle className="mb-3">Assigned to: {cardData.assignedTo}</Card.Subtitle>
              
              <div className="mb-3">
                <Badge bg="primary" className="me-2">Priority: {cardData.priority}</Badge>
                <Badge bg="info">Status: {cardData.status}</Badge>
              </div>
              
              <Card.Text>{cardData.description}</Card.Text>
              
              <Row className="mt-4">
                <Col>
                  <strong>Created:</strong> {cardData.createdAt}
                </Col>
                <Col>
                  <strong>Due Date:</strong> {cardData.dueDate}
                </Col>
              </Row>
              
              <div className="mt-4">
                <Button variant="warning" onClick={handleUpdate} className="me-2">Update</Button>
                <Button variant="danger" onClick={handleDelete}>Delete</Button>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
*/