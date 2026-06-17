import React from 'react';

const StatCard = ({ title, value }) => {
  return (
    <div className="bg-white shadow rounded-lg p-6">
      <h4 className="text-gray-500 text-sm font-medium">{title}</h4>
      <p className="text-3xl font-bold text-gray-800">{value}</p>
    </div>
  );
};

export default StatCard;
