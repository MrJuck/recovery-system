import React from 'react';
import { Layout as AntLayout, Menu, Avatar } from 'antd';
import routes from '../../config/routes';
import { withRouter } from 'react-router-dom';
import UserInfo from './UserInfo';

const { Header, Content, Footer } = AntLayout;

const Layout: React.FC<any> = (props) => {

  const getMenu = () => {
    return routes.filter(e => e.show).map(item => {
      return <Menu.Item key={item.path} onClick={() => props.history.push(item.path)}>{item.title}</Menu.Item>
    });
  }

  return (
    <AntLayout>
      <Header style={{ display: 'flex', zIndex: 1, width: '100%', justifyContent: 'space-between' }}>
        <Menu theme="dark" mode="horizontal">
          {
            getMenu()
          }
        </Menu>
        <div style={{}}>
          <UserInfo />
        </div>
      </Header>
      <Content className="site-layout">
        <div className="site-layout-background" style={{ width: 960, height: window.innerHeight - 64 - 80, margin: '0 auto' }}>
          {props.children}
        </div>
      </Content>
      <Footer style={{ textAlign: 'center' }}>{`Created By Juck ©${new Date().getFullYear()}`}</Footer>
    </AntLayout >
  );
}

export default withRouter(Layout);
