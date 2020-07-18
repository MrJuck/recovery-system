class LocalStorageUtil {
  static get(key:string) {
    return localStorage.getItem(key) || '';
  }

  static remove(key: string) {
    localStorage.removeItem(key);
  }

  static clear() {
    localStorage.clear();
  }

  static set(key:string, value:string) {
    localStorage.setItem(key, value);
  }
}

export default LocalStorageUtil;