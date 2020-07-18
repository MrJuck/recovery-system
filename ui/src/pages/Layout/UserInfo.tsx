import React from 'react';
import { withRouter } from 'react-router-dom';
import { Avatar, Dropdown, Menu, message } from 'antd';
import _ from 'lodash';
import { logout } from '../../utils/auth';
import { connect } from 'react-redux';

const UserInfo: React.FC<any> = (props) => {

  console.log('UserInfo', props)
  //@ts-ignore
  const onClickMenu = ({ key }) => {
    if (_.isEqual(key, 'logout')) {
      message.info('退出成功');
      logout();
      props.history.push('/login');
    }
  }

  const popMenu = () => {
    return (
      <Menu onClick={(item: any) => { onClickMenu(item) }}>
        <Menu.Item key='logout'>退出登录</Menu.Item>
        <Menu.Item key='B'>B</Menu.Item>
        <Menu.Item key='C'>C</Menu.Item>
      </Menu>
    );
  }

  return (
    <Dropdown overlay={popMenu()}>
      <Avatar size='large'>{props.username}</Avatar>
    </Dropdown>
  );
}

export default connect(state => state)(withRouter(UserInfo));