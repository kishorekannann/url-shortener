import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../assets/logo.svg';

const Header = () => {
  return (
    <header className="bg-white shadow-sm">
      <div className="container mx-auto px-6 py-4 flex justify-between items-center">
        <div className="flex items-center">
          <img src={logo} alt="SwiftLink Logo" className="h-8 w-auto" />
          <span className="text-xl font-bold text-gray-800 ml-2">SwiftLink</span>
        </div>
        <div className="flex items-center">
          <Link to="/login" className="text-gray-600 hover:text-blue-500 px-4">Login</Link>
          <Link to="/register" className="bg-blue-500 text-white rounded-full px-4 py-2 hover:bg-blue-600">Sign Up</Link>
        </div>
      </div>
    </header>
  );
};

export default Header;
