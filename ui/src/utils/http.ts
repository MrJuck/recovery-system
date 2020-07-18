import { message } from 'antd';
import LocalStorageUtil from './localStorageUtil';

class HttpUtil {
  public static async get(url: string, data?: { [key: string]: string }) {
    let params: string = !data ? '' : this.convertParams(data);

    return await new Promise((resove, reject) => {
      fetch(`./api/${url.startsWith('/') ? url.substring(1) : url}?${params}`, {
        method: 'GET',
        headers: {
          'TOKEN': localStorage.getItem("TOKEN") || ''
        },
        mode: 'cors'
      })
        .then(res => res.json())
        .then((res) => {
          if (res.code === '000000') {
            LocalStorageUtil.clear();
            window.location.href = '/login';
          }

          return resove(res);
        })
        .catch(error => {
          message.error('error ' + error.message);
        });
    });
  }

  public static async post(url:string, body:{ [key: string]: string }) {
    return await new Promise((resove, reject) => {
      fetch(`./api/${url.startsWith('/') ? url.substring(1) : url}`, {
        method: 'POST',
        body: JSON.stringify(body),
        headers: {
          'TOKEN': localStorage.getItem("TOKEN") || '',
          'Content-Type': 'application/json'
        },
        mode: 'cors'
      })
        .then(res => res.json())
        .then((res) => {
          if (res.code === '000000') {
            LocalStorageUtil.clear();
            window.location.href = '/login';
          }

          return resove(res);
        })
        .catch(error => {
          message.error('error ' + error.message);
        });
    });
  }

  private static convertParams(data: { [key: string]: string }): string {
    let result: string = '';
    if (typeof data === 'object') {
      for (let key of Object.keys(data)) {
        result += `${key}=${data[key]}&`;
      }

      result = result.substring(0, result.lastIndexOf('&'));
    }

    return result;
  }
}



export default HttpUtil;