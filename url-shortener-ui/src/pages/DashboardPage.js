import React, { useState, useEffect } from 'react';
import urlService from '../services/urlService';
import UrlTable from '../components/Dashboard/UrlTable';
import StatCard from '../components/Dashboard/StatCard';

const DashboardPage = () => {
  const [originalUrl, setOriginalUrl] = useState('');
  const [shortUrl, setShortUrl] = useState('');
  const [urls, setUrls] = useState([]);
  const [totalClicks, setTotalClicks] = useState(0);

  useEffect(() => {
    const fetchUrls = async () => {
      try {
        const response = await urlService.getUserUrls();
        setUrls(response.data);
        const clicks = response.data.reduce((acc, url) => acc + url.clickCount, 0);
        setTotalClicks(clicks);
      } catch (error) {
        console.error('Failed to fetch URLs', error);
      }
    };
    fetchUrls();
  }, []);

  const handleShorten = async (e) => {
    e.preventDefault();
    try {
      const response = await urlService.createShortUrl(originalUrl);
      setShortUrl(response.data);
      setUrls([...urls, { originalUrl, shortUrl: response.data, clickCount: 0 }]);
      setOriginalUrl('');
    } catch (error) {
      console.error('Failed to shorten URL', error);
    }
  };

  return (
    <div className="bg-gray-100 min-h-screen">
      <div className="container mx-auto px-6 py-12">
        <h1 className="text-3xl font-bold text-gray-800">Dashboard</h1>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mt-8">
          <StatCard title="Total Links" value={urls.length} />
          <StatCard title="Total Clicks" value={totalClicks} />
        </div>
        <div className="mt-12">
          <div className="bg-white shadow rounded-lg p-8">
            <h2 className="text-2xl font-bold text-gray-800 mb-4">Create a new link</h2>
            <form onSubmit={handleShorten} className="flex space-x-4">
              <input
                type="text"
                value={originalUrl}
                onChange={(e) => setOriginalUrl(e.target.value)}
                placeholder="Enter URL to shorten"
                className="flex-grow px-3 py-2 border rounded-md"
                required
              />
              <button type="submit" className="px-4 py-2 text-white bg-blue-500 rounded-md hover:bg-blue-600">
                Shorten
              </button>
            </form>
            {shortUrl && (
              <div className="p-4 mt-4 text-center bg-green-100 rounded-md">
                Short URL: <a href={`http://localhost:8080/${shortUrl}`} target="_blank" rel="noopener noreferrer" className="text-blue-600">{`http://localhost:8080/${shortUrl}`}</a>
              </div>
            )}
          </div>
        </div>
        <UrlTable urls={urls} />
      </div>
    </div>
  );
};

export default DashboardPage;
