import { Dimensions } from 'react-native';

export const deviceHeight = () => {
    return Dimensions.get('window').height
}
export const realDeviceHeight = () => {
    return Dimensions.get('screen').height
}
export function deviceWidth(){
    return Dimensions.get('window').width
}
export default Utils={
    deviceWidth: deviceWidth(),
    deviceHeight:deviceHeight(),
    realDeviceHeight:realDeviceHeight()
}