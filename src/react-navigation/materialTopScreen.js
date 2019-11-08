import React, { Component } from 'react';
import { View, Text } from 'react-native';
import {createMaterialTopTabNavigator} from 'react-navigation-tabs'
import { StackActions,NavigationActions } from 'react-navigation';
import TextButton from '../common/TextButton'
class SettingsScreen extends React.Component {
    render() {
      return (
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <Text>Settings!</Text>
        </View>
      );
    }
  }
  class HomeScreen extends React.Component {
    render() {
      const resetAction = StackActions.reset({
    index:0,//index值actions中括号里面的参数下标
    actions:[NavigationActions.navigate({routeName:'Home'})]
      })
      return (
        <View style={{ flex: 1,justifyContent:'center',alignItems: 'center' }}>
          <Text>Home!</Text>
          <TextButton title='resetAction' onPress={()=>{this.props.navigation.dispatch(resetAction)}}/>
          <TextButton title='popToTop' onPress={()=>{this.props.navigation.dispatch(StackActions.popToTop())}}/>
        </View>
      );
    }
  }
const MaterialTopScreen = createMaterialTopTabNavigator({
    Home: HomeScreen,
    Settings: SettingsScreen,
},{
    initialRouteName:'Home',
    tabBarOptions:{
        activeTintColor:'#009688',
        inactiveTintColor:'gray',
        tabStyle:{backgroundColor:'#fff'},
        upperCaseLabel:false,
        // indicatorStyle:{width:3}
    }
})
// class MaterialTopScreen extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//     };
//   }

//   render() {
//     return (
//       <View>
//         <Text> materialTopScreen </Text>
//       </View>
//     );
//   }
// }

export default MaterialTopScreen;
