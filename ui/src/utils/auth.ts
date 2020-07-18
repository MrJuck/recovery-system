import LocalStorageUtil from './localStorageUtil'

const TOKEN_KEY = 'TOKEN';

export default function isLogin() {
  return LocalStorageUtil.get(TOKEN_KEY);
}

export function logout() {
  LocalStorageUtil.remove(TOKEN_KEY);
}