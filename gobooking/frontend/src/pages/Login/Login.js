import './Login.css'
import React, { useState } from 'react';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import { Link } from '@mui/material';

export default function Login() {
    
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [showPassword, setShowPassword] = useState(false);
    
    return (
    <div className='Login'>
        <img src='./logo.png' className="logo" alt="GoBooking-logo" margin="150px"/>
        <header className='Login-header'>GoBooking</header>
        <br></br>
        <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
          <InputLabel htmlFor="email">Email</InputLabel>
          <OutlinedInput
            id="email-field"
            type={'email'}
            value={email}
            label="Email"
            onChange={e => setEmail(e.target.value)}
          />
        </FormControl>
        <br></br>
        <br></br>
        <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
          <InputLabel htmlFor="password">Password</InputLabel>
          <OutlinedInput
            id="password-field"
            type={showPassword ? 'text' : 'password'}
            endAdornment={
              <InputAdornment position="end">
                <IconButton
                  aria-label="toggle password visibility"
                  onClick={() => setShowPassword((show) => !show)}
                  edge="end"
                >
                  {showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>
              </InputAdornment>
            }
            value={password}
            label="Password"
            onChange={e=>setPassword(e.target.value)}
          />
        </FormControl>
        <br></br>
        <Link className='Login-link' onClick={()=>{console.log("Clicked")}}>Forget Password?</Link>
        <br></br>
        <br></br>
        <Button className = 'Login-button' variant="contained" onClick={() => {console.log("Button clicked")}}>Login</Button>
    </div>
    )
}