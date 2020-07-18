import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Layout from './pages/Layout/Layout';

import Page1 from './pages/Page1/Page1';
import Page2 from './pages/Page2/Page2';
import Page3 from './pages/Page3/Page3';
import Login from './pages/Login/Login';
import PageNotFound from './pages/PageNotFound/PageNotFound';

const App: React.FC = () => {
  return (
    <div style={{ height: '100%' }}>
      <Router>
        <Layout>
          <Switch>
            <Route path='/login' component={Login} />
            <Route exact path='/page1' component={Page1}></Route>
            <Route exact path='/page2' component={Page2}></Route>
            <Route exact path='/admin/page3' component={Page3}></Route>
            <Route component={PageNotFound} />
          </Switch>
        </Layout>
      </Router>
    </div>
  )
}

export default App;