import React from 'react'
import { Badge, Card, Container, ButtonGroup, Button } from 'react-bootstrap';
import { FaRegTrashCan } from "react-icons/fa6";
import { LuPencil } from "react-icons/lu";
import { Link } from 'react-router-dom';
const TaskCard = () => {
  const id = 1234;
  return (
    <Card style={{ width: '18rem' }}>
      <Card.Body>
        <Card.Title>Card Title</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">Project Name</Card.Subtitle>
        <Card.Subtitle className="mb-2">Username</Card.Subtitle>
        <Container className="my-2">
          <Badge bg="primary" className='mx-1'>Priority</Badge>
          <Badge bg="danger" className='mx-1'>Status</Badge>
        </Container>
        <Card.Text>
          Task Description
        </Card.Text>
        <Card.Subtitle className="text-sm">Due Date</Card.Subtitle>
        <Container className="d-flex justify-content-between mt-2">
          <Button variant="success" size="sm"><LuPencil/> Update Task</Button>
          <Button variant="warning" size="sm"><FaRegTrashCan /> Delete Task</Button>
        </Container>

      </Card.Body>
    </Card>
  );
}

export default TaskCard