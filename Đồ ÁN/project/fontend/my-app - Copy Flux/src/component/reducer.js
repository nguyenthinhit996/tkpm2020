
const reducer = (state, action) => {
    console.log(action);
    switch(action.type){
      case 'inital':
        return {
          items : action.payLoad.items,
          valueFilter : action.payLoad.valueFilter
        }

      case 'filter':
        return {
          items : state.items,
          valueFilter : action.payLoad.valueFilter
        }

      case 'add':
        return {
          items : action.payLoad.items,
          valueFilter : state.valueFilter
        }
      case 'del':
        return {
          items : action.payLoad.items,
          valueFilter : state.valueFilter
        }
      default:
        return state;
    }
  }
export default reducer