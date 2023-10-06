import { createContext, useState } from "react";

const Database = createContext()
export default Database

export const DatabaseProvider = ({ children }) => {
    const [database, setDatabase] = useState(new Map());

    const resetDatabase = () => setDatabase(new Map())

    return (
        <Database.Provider value={{ database, setDatabase, resetDatabase }}>
            {children}
        </Database.Provider>
    )
}