import {requireNativeComponent,View} from 'react-native';
let ll = {
    name:'LinearWidget',
    propTypes:{
        ...View.propTypes
    }
}
export default requireNativeComponent('LinearWidget',ll);