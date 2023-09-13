import './App.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import HomePage from './Pages/HomePage/HomePage'
import QuestionPage from './Pages/QuestionPage/QuestionPage'
import { useEffect, useState } from 'react'
import UserPage from './Pages/UserPage/UserPage'
import Layout from './Pages/Layout'
import SignIn from './Pages/Auth/SignIn'
import NewQuestion from './Pages/NewQuestion/NewQuestion'
import Register from './Pages/Auth/Register'
import Users from './Pages/Users/Users'

function App() {

  useEffect(() => {
    if (!JSON.parse(window.localStorage.getItem("loginInfo"))) window.localStorage.setItem("loginInfo", null);
  }, []);


  return (
    <div className="App">
      <Router>
        <Routes>

          <Route element={<Layout />}>

            <Route path='/' element={<HomePage />} />
            <Route path='question/:id' element={<QuestionPage />} />
            <Route path='user/:id' element={<UserPage />} />
            <Route path='new' element={<NewQuestion />} />
            <Route path="/users" element={<Users />} />
          </Route>

          <Route path='/signin' element={<SignIn />} />
          <Route path='/register' element={<Register />} />

        </Routes>
      </Router>
    </div>
  )
}

export default App
