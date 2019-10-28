import ScreenOne from './screenOne'
import ScreenTwo from './screenTwo'
import ScreenThree from './screenThree'
import {createStackNavigator} from 'react-navigation-stack'

const ScreenModule = {
    ScreenOne:{screen:ScreenOne,   navigationOptions: () => ({
        title: 'ScreenOne'
      })},
    ScreenTwo:{screen:ScreenTwo, navigationOptions: () => ({
        title: 'ScreenTwo'
      })},
    ScreenThree:{screen:ScreenThree, navigationOptions: () => ({
        title: 'ScreenThree'
      })},

}
export default ScreenModule
