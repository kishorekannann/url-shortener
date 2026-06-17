import api from './api';

const createShortUrl = (originalUrl) => {
  return api.post('/urls', { originalUrl });
};

const getUserUrls = () => {
  return api.get('/urls');
};

const urlService = {
  createShortUrl,
  getUserUrls,
};

export default urlService;
