import React from 'react';
import { Link } from 'react-router-dom';

const Hero = () => {
  return (
    <div className="bg-blue-50 py-20">
      <div className="container mx-auto px-6 text-center">
        <h1 className="text-5xl font-bold text-gray-800">Shorten URLs, Not Your Reach</h1>
        <p className="text-gray-600 mt-4 text-lg">Create short, memorable links in seconds. Track every click and optimize your marketing campaigns.</p>
        <div className="mt-8">
          <Link to="/register" className="bg-blue-500 text-white rounded-full px-8 py-3 hover:bg-blue-600">Get Started for Free</Link>
        </div>
      </div>
    </div>
  );
};

export default Hero;
