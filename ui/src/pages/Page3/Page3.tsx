import React from 'react';
import { Button } from 'antd';
import isLogin from '../../utils/auth';
import { Redirect } from 'react-router-dom';

const Page3: React.FC<any> = (props) => {
  const { location: { pathname } } = props;
  console.log(pathname);

  return (
    !isLogin() ? <Redirect to='/login' /> :
      <Button type='primary' size='large'>Large Button In Page3</Button>
  );
}

export default Page3;