
import React, { Component } from 'react'
import { Text, View,StatusBar,StyleSheet,Button} from 'react-native'
import { createAppContainer } from 'react-navigation';
import { createStackNavigator } from 'react-navigation-stack';
import TestScreen from './src/testScreen'
import HomeScreen from './src/homeScreen'
import ReactNativeInteractionScreen from './src/reactNativeInteractionScreen'
import Colors from './src/common/colors';
const AppNavigator = createStackNavigator({
  Test:{
    screen:TestScreen,
    navigationOptions:()=>({
      title:'Test',
      headerBackTitle:null
    })
  },
  Home:{
    screen:HomeScreen,
    navigationOptions:()=>({
      title:'Home',
    })
  },
  ReactNativeInteractionScreen:{
    screen:ReactNativeInteractionScreen,
    navigationOptions:()=>({
      title:'ReactNativeInteractionScreen'
    })
  }

},{
  initialRouteName:'Home',
  animationEnabled:true,
  gestureEnabled:true,
  defaultNavigationOptions:({
    headerStyle:{
      backgroundColor:Colors.colorPrimary,
    },
    headerTitleStyle:{
      color:'white',
      fontSize:20,
      flex:1,
      textAlign:'center',
      alignSelf:'center',
    },
    headerTintColor:'white',

  }),
  mode:'card',
  headerMode:'screen',
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
    return <AppContainer/>
  }
}

export default App
const styles = StyleSheet.create({
  container:{
    flex:1
  },
})