declare namespace Types{
  interface RouteProp {
    path: string,
    component: React.FC,
    title?: string,
    show?: boolean
  }
  
  interface HttpResult {
    code: string,
    data?: object,
    message?: string
  }
}

export default Types;

