import React from 'react';
import {
  Card,
  CardActionArea,
  CardContent,
  CardMedia,
  Grid,
  Typography,
} from '@mui/material';
import { makeStyles } from '@material-ui/core/styles';
import computer1Image from '../images/c1.jpg';
import computer2Image from '../images/c2.jpg';
import computer3Image from '../images/c3.jpg';
import { useHistory } from 'react-router-dom';

var computers = [
  {
    id: 1,
    name: 'GilbertComputer',
    image: computer1Image,
  },
  {
    id: 2,
    name:'LwamComputer',
    image: computer2Image,
  },
  {
    id: 3,
    name: 'RufaelComputer',
    image: computer3Image,
  },
];
const useStyles = makeStyles({
  cardMedia: {
    height: 300,
    width: 300,
    borderRadius: 30,
  },
});

const ComputerList = ({ onComputerClick }) => {
  const classes = useStyles();
  return (
    <Grid container spacing={2}>
      {computers.map((computer) => (
        <Grid item xs={12} md={4} key={computer.id}>
          <Card onClick={() => onComputerClick(computer)}>
            <CardActionArea>
              <CardMedia
              
               className={classes.cardMedia}
               component="img"
                image={computer.image}
                alt={computer.name}
            
              />
              <CardContent>
                <Typography variant="h6">{computer.name}</Typography>
              </CardContent>
            </CardActionArea>
          </Card>
        </Grid>
      ))}
    </Grid>
  );
};

export default ComputerList;
