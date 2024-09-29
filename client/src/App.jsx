import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Document from './components/Document';
import Dashboard from './components/Dashboard';
import LandingPage from './components/LandingPage';
import ProtectedRoute from './components/ProtectedRoute';
import './App.css'

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route path="/dashboard" element={ <ProtectedRoute><Dashboard /></ProtectedRoute>} />
                <Route path="/document/:id" element={<Document />} />
            </Routes>
        </Router>
    );
}

export default App
