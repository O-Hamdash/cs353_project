import './Signup.css'
import React, { useState } from 'react';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import TextField from '@mui/material/TextField';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import { Link } from '@mui/material';


export default function Signup() {
    
    

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const date = new Date();
    const today = date.getFullYear() + "-" + (date.getMonth() < 10 ? "0" : "") 
                + date.getMonth() + "-" + (date.getDay() < 10 ? "0" : "") + date.getDay();
    const [birthDate, setBirthDate] = useState(today);
    const [secondPassword, setSecondPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [clicked, setClicked] = useState(false);

    function getAge(birthday) {
        let years = today.substring(0,4).valueOf() - String(birthday).substring(0,4).valueOf();
        let months = today.substring(5,7).valueOf() - String(birthday).substring(5,7).valueOf();
        let days = today.substring(8).valueOf() - String(birthday).substring(8).valueOf();
        if (months < 0 || (months === 0 && days < 0)) {
            years--;
        }   
        return years;
    }

    return (
    <div className='Signup'>
        <img src='./logo.png' className="logo" alt="GoBooking-logo" margin="150px"/>
        <header className='Signup-header'>GoBooking</header>
        <br></br>
        <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
          <InputLabel htmlFor="name">Name</InputLabel>
          <OutlinedInput
            id="name-field"
            type={'name'}
            value={name}
            label="Name"
            onChange={e => setName(e.target.value)}
        />
        </FormControl>
        <br></br>
        <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
          <InputLabel htmlFor="lastName">Last Name</InputLabel>
          <OutlinedInput
            id="last-name-field"
            type={'lastName'}
            value={lastName}
            label="Last Name"
            onChange={e => setLastName(e.target.value)}
            />
        </FormControl>
        <br></br>
        <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
          <InputLabel htmlFor="birthDate">Birth Date</InputLabel>
          <OutlinedInput
            id="birthdate-field"
            type="date"
            value={birthDate}
            label="Birth Date"
            onChange={newValue => setBirthDate(newValue.target.value)}
            />
        </FormControl>
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
        <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
            <TextField
            id="second-password-field"
            label="Password(Again)"
            variant="outlined"
            margin="dense"
            type={showPassword ? 'text' : 'password'}
            value={secondPassword}
            helperText={(password != secondPassword ? "Passwords should match" : "") ||
                        ( (getAge(birthDate) < 18 || getAge(birthDate > 150)) ? 
                            "Your age must be +18 -150": "")}
            onChange={e=>setSecondPassword(e.target.value)}
            />
        </FormControl>
        <br></br>
        <Link className='Login-link' onClick={()=>{console.log("Clicked")}}>Forget Password?</Link>
        <br></br>
        <br></br>
        <Button className = 'Signup-button' 
                variant="contained" 
                onClick={() => {if(getAge(birthDate) > 18 && getAge(birthDate) < 150 && 
                                    password != "" && email != "" && name != "" && 
                                    lastName != "" && password == secondPassword){
                                            console.log("Button clicked"); setClicked(!clicked)}}}
                style={{ backgroundColor: clicked ? "#01228B" : '#0122BB' }}>Login</Button>
    </div>
    )
}