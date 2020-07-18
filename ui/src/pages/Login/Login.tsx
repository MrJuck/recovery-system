import React, { useState } from 'react';
import { Form, Input, Button, Modal, Card, Row, Col, Alert } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import { withRouter, Redirect } from 'react-router-dom';
import isLogin from '../../utils/auth';

const Login: React.FC<any> = (props) => {
  const [visible, setVisible] = useState<boolean>(true);
  const [showBanner, setShowBanner] = useState<boolean>(false);
  const [verificationCodeUrl, setVerificationCodeUrl] = useState<string>(`./api/verification?time=${new Date().getTime()}`);

  const onFinish = (values: any) => {
    fetch('./api/login', {
      body: JSON.stringify(values),
      method: 'post',
      headers: {
        'Content-Type': 'application/json'
      },
      mode: 'cors',

    }).then(res => res.json())
      .then(res => {
        if (res.verification === false) {
          setShowBanner(true);
          setVerificationCodeUrl(`./api/verification?time=${new Date().getTime()}`);
          return;
        }

        setShowBanner(false);
        localStorage.setItem("TOKEN", res.token);
        localStorage.setItem('USER', res.username);
        setVisible(false);
        props.history.replace('/page1');
      });
  };

  const [form] = Form.useForm();
  return (
    isLogin() ? <Redirect to='/page1' /> :
      <Modal
        visible={visible}
        title="Recovery Therapy System"
        centered
        maskClosable={false}
        keyboard={false}
        footer={null}
        closable={false}
      >
        {
          showBanner ?
            <Alert
              message='验证码错误!'
              type="error"
              showIcon
              banner /> :
            null
        }
        <Card >
          <Form
            form={form}
            name="normal_login"
            className="login-form"
            initialValues={{ remember: true }}
            onFinish={onFinish}
          >
            <Form.Item
              name="username"
              rules={[{ required: true, message: 'Please input your Username!' }]}
            >
              <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
            </Form.Item>
            <Form.Item
              name="password"
              rules={[{ required: true, message: 'Please input your Password!' }]}
            >
              <Input
                prefix={<LockOutlined className="site-form-item-icon" />}
                type="password"
                placeholder="Password"
              />
            </Form.Item>

            <Form.Item name='verificationCode' rules={[{ required: true, message: '请输入验证码' }]}>
              <Row justify='space-between'>
                <Col>
                  <Input
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    placeholder="请输入验证码"
                  />
                </Col>

                <Col>
                  <a title='点击刷新' onClick={() => setVerificationCodeUrl(`./api/verification?time=${new Date().getTime()}`)}>
                    <img src={verificationCodeUrl} alt='点击刷新' />
                  </a>
                </Col>
              </Row>
            </Form.Item>

            <Form.Item style={{ textAlign: 'center' }}>
              <Button type="primary" htmlType="submit">
                登录
            </Button>
              <Button type="primary" htmlType="submit" style={{ marginLeft: 20 }}>
                忘记密码
            </Button>

            </Form.Item>
          </Form>
        </Card>
      </Modal>
  );
};

export default withRouter(Login);
