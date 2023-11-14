import React, { createContext } from 'react'

const myContext = createContext();

const MyProvider = ({children}) => {
  
    const [jwt, setJwt] = React.useState('');
  
    return (
    <myContext.Provider value={{jwt,setJwt}}>
        {children}
    </myContext.Provider>
  )
}

export default MyProvider
