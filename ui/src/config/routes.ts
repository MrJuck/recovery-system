import Page1 from "../pages/Page1/Page1";
import Page2 from "../pages/Page2/Page2";
import Page3 from "../pages/Page3/Page3";
import PageNotFound from '../pages/PageNotFound/PageNotFound';

const routes = [
  {
    path: '/',
    component: Page1,
    title: 'Page1',
  },{
    path: '/page1',
    component: Page1,
    title: 'Page1',
    show: true,
  },{
    path: '/page2',
    component: Page2,
    title: 'Page2',
    show: true,
  },{
    path: '/admin/page3',
    component: Page3,
    title: 'Page3',
    show: true,
  },{
    path: '/404',
    component: PageNotFound,
    title: '404',
  }
];

export default routes;
