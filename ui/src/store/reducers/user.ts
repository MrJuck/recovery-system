const userReducer = (state = {}, action: any) => {
  switch (action.type) {
    case 'IS_LOGIN':
      return { ...state, isLogin: true, username: action.payload.username };
    default: return state;
  }
}

export default userReducer;