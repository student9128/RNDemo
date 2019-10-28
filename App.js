
import React, { Component } from 'react'
import {
  Text,
  View,
  StatusBar,
  StyleSheet,
  Button,
  Easing,
  Animated,
  I18nManager
} from 'react-native'
import { createAppContainer } from 'react-navigation';
import { createStackNavigator } from 'react-navigation-stack';
import TestScreen from './src/testScreen'
import HomeScreen from './src/homeScreen'
import ReactNativeInteractionScreen from './src/reactNativeInteractionScreen'
import ReactNavigationScreen from './src/reactNavigationScreen';
import SceneOneScreen from './src/react-navigation/normal'
import SceneTwoScreen from './src/react-navigation/sceneTwo'
import Colors from './src/common/colors';
import Icon from 'react-native-vector-icons/Ionicons';
import ParamScreen from './src/react-navigation/paramScreen'
import ScreenModule from './src/screens-module/index'
const AppNavigator = createStackNavigator({
  ...ScreenModule,
  Test: {
    screen: TestScreen,
    navigationOptions: () => ({
      title: 'Test',
      headerBackTitle: null
    })
  },
  Home: {
    screen: HomeScreen,
    navigationOptions: () => ({
      title: 'RNDemo',
      headerRight: null
    })
  },
  ReactNativeInteractionScreen: {
    screen: ReactNativeInteractionScreen,
    navigationOptions: () => ({
      title: 'ReactNativeInteractionScreen',
    })
  },
  ReactNavigationScreen: {
    screen: ReactNavigationScreen,
    navigationOptions: () => ({
      title: 'ReactNavigationScreen'
    })
  },
  SceneOne: {
    screen: SceneOneScreen
  },
  SceneTwo: {
    screen: SceneTwoScreen,
    navigationOptions: () => ({
      header: null
    })
  },
  ParamScreen:{
    screen:ParamScreen,
    navigationOptions: () => ({
      title: 'ParamScreen'
    })
  }

}, {
  initialRouteName: 'Home',
  defaultNavigationOptions: ({
    headerStyle: {
      backgroundColor: Colors.colorPrimary,

    },
    // headerLeft:(<View style={{  height: 24,alignItems:'center',
    // backgroundColor:'red',
    //   width: 48,
    //   transform: [{ scaleX: I18nManager.isRTL ? -1 : 1 }],
    //   }}><Icon name={'md-arrow-back'} size={25} color={'white'} /></View>),
    headerRight: (<View style={{
      height: 24,
      width: 48,
      transform: [{ scaleX: I18nManager.isRTL ? -1 : 1 }],
    }}></View>),
    headerTitleStyle: {
      color: 'white',
      fontSize: 20,
      flex: 1,
      textAlign: 'center',
      alignSelf: 'center',
    },
    headerTintColor: 'white',
    gesturesEnabled: true,
    gestureResponseDistance: { horizontal: 50 },
    headerPressColorAndroid:'transparent'//去掉安卓返回键点击水波纹效果
  }),
  mode: 'card',//平台默认切换动画
  headerMode: 'float',//侧滑返回用float,切换的时候用screen效果相对较好
  transitionConfig: () => ({
    transitionSpec: {
      duration: 500,
      easing: Easing.out(Easing.poly(4)),
      timing: Animated.timing,
      useNativeDriver: true,
    },
    screenInterpolator: sceneProps => {
      const { layout, position, scene } = sceneProps
      const { index } = scene
      const height = layout.initHeight
      const width = layout.initWidth
      const translateY = position.interpolate({
        inputRange: [index - 1, index, index + 1],
        outputRange: [height, 0, 0]
      })
      const translateX = position.interpolate({
        inputRange: [index - 1, index, index + 1],
        outputRange: I18nManager.isRTL ? [-width, 0, width * 0.3] : [width, 0, width * -0.3],
        extrapolate: 'clamp'
      })
      const opacity = position.interpolate({
        inputRange: [index - 1, index - 0.99, index, index + 0.99, index + 1],
        outputRange: [0, 1, 1, 0.85, 0]
      })
      return { opacity, transform: [{ translateX }] }
    }
  })

  // tabBarOptions:{
  //   activeTintColor: 'tomato',
  //   inactiveTintColor: 'gray',
  //   style: {
  //     backgroundColor: '#cd00ea', // TabBar 背景色
  //     paddingBottom: 0,
  //     // borderTopWidth: 0.5,
  //     // borderTopColor: '#ccc',
  //     // margin:0
  //   },
  // }
})
const AppContainer = createAppContainer(AppNavigator)


export class App extends Component {
  render() {
    return <View style={{ flex: 1 }}>
      <StatusBar backgroundColor={Colors.colorPrimaryDark} />
      <AppContainer />
    </View>
  }
}

export default App
const styles = StyleSheet.create({
  container: {
    flex: 1
  },
})