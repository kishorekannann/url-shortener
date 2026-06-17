import React from 'react';

const features = [
  {
    title: 'Easy URL Shortening',
    description: 'Create clean, short URLs from long, unwieldy links with a single click.',
  },
  {
    title: 'Click Analytics',
    description: 'Track every click on your shortened links to understand your audience.',
  },
  {
    title: 'Customizable Links',
    description: 'Create custom-branded links to build brand recognition and trust.',
  },
];

const Features = () => {
  return (
    <div className="py-20">
      <div className="container mx-auto px-6">
        <h2 className="text-3xl font-bold text-center text-gray-800 mb-12">Why SwiftLink?</h2>
        <div className="flex flex-wrap -mx-4">
          {features.map((feature, index) => (
            <div key={index} className="w-full md:w-1/3 px-4 mb-8">
              <div className="bg-white rounded-lg shadow-lg p-8">
                <h3 className="text-xl font-bold text-gray-800 mb-4">{feature.title}</h3>
                <p className="text-gray-600">{feature.description}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Features;
