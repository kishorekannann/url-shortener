import api from './api';

const login = (username, password) => {
  return api.post('/auth/login', { username, password });
};

const register = (username, email, password) => {
  return api.post('/auth/register', { username, email, password });
};

const logout = () => {
  localStorage.removeItem('token');
};

const authService = {
  login,
  register,
  logout,
};

export default authService;
