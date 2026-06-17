import React from 'react';

const UrlTable = ({ urls }) => {
  return (
    <div className="mt-8">
      <h3 className="text-xl font-bold mb-4">Your Links</h3>
      <div className="bg-white shadow rounded-lg">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Original URL</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Short URL</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Clicks</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {urls.map((url) => (
              <tr key={url.id}>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{url.originalUrl}</td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-blue-500">
                  <a href={`http://localhost:8080/${url.shortUrl}`} target="_blank" rel="noopener noreferrer">
                    {`http://localhost:8080/${url.shortUrl}`}
                  </a>
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{url.clickCount}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default UrlTable;
