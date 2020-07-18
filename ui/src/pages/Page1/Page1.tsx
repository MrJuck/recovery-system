import React from 'react';
import { Button } from 'antd';
import HttpUtil from '../../utils/http';
import { connect } from 'react-redux';

const Page1: React.FC<any> = (props) => {
  console.log(props);

  const click = async () => {
    props.dispatch({
      type: 'IS_LOGIN',
      payload: { 'username': '张超' }
    });
    // const data = await HttpUtil.get('/test', { 'test': 'test' });

    // console.log(data);
  }

  return (
    <Button type='primary' size='large' onClick={() => {
      click()
    }}>Large Button In Page1</Button>
  );
}

export default connect(state => state)(Page1);
