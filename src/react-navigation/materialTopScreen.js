import React, { Component } from 'react';
import { View, Text,Dimensions } from 'react-native';
import {createMaterialTopTabNavigator} from 'react-navigation-tabs'
import { StackActions,NavigationActions } from 'react-navigation';
import TextButton from '../common/TextButton'
import Colors from '../common/colors';
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
  const width = Dimensions.get('window').width
  const height = Dimensions.get('window').height
const MaterialTopScreen = createMaterialTopTabNavigator({
    Home: HomeScreen,
    Settings: SettingsScreen,
},{
    initialRouteName:'Home',
    tabBarOptions:{
        activeTintColor:Colors.colorPrimary,
        inactiveTintColor:Colors.gray,
        tabStyle:{backgroundColor:'#fff'},
        upperCaseLabel:false,
        style: {
            backgroundColor: '#fff',
            shadowColor: '#fff',
            elevation:1,
            shadowOffset: {
              width: 0,
              height: 0,
            }},
            indicatorStyle: {
                backgroundColor: Colors.colorAccent,
                height: 1,
                marginBottom: -1,
                width:width/8,
                marginHorizontal:width*3/16,
              },
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
