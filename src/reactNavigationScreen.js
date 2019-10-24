import React, { Component } from 'react';
import { View, Text,Image } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import { createBottomTabNavigator } from 'react-navigation-tabs';
import { createAppContainer } from 'react-navigation';
import {  } from 'react-navigation';

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
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Text>Home!</Text>
      </View>
    );
  }
}
class ReactNavigationScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <View>
         <Icon.Button name="facebook" backgroundColor="#3b5998"></Icon.Button>
        <Text> reactNavigationScreen </Text>
      </View>
    );
  }
}
const TabNavigator = createBottomTabNavigator({
  Home:HomeScreen,
  SettingsScreen:SettingsScreen
},{
  defaultNavigationOptions:({navigation})=>({

  })
})

// export default ReactNavigationScreen;
// export default createAppContainer(TabNavigator);
export default TabNavigator;
