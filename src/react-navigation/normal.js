import React, { Component } from 'react';
import { View, Text, Image } from 'react-native';
import Icon from 'react-native-vector-icons/Ionicons';
import { createBottomTabNavigator } from 'react-navigation-tabs';
import Colors from '../common/colors'
import TextButton from '../common/TextButton'
import { StackActions,NavigationActions } from 'react-navigation';
import IconWithBadge  from '../common/IconWithBadge'
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
const TabNavigator = createBottomTabNavigator({
  Home: HomeScreen,
  Settings: SettingsScreen,
}, {
  defaultNavigationOptions: ({ navigation }) => ({
    tabBarIcon: ({ focused, horizontal, tintColor }) => {
      const { routeName } = navigation.state
      let badgeCount = 0
      let iconName;
      if (routeName === 'Home') {
        iconName = 'md-home';
        badgeCount=9
      } else if (routeName === 'Settings') {
        iconName = 'md-settings';
        badgeCount=0
      }
      return <IconWithBadge name={iconName} size={25} color={tintColor} badgeCount={badgeCount}/>
    }
  }),
  tabBarOptions: {
    activeTintColor: Colors.colorPrimary,
    inactiveTintColor: Colors.gray,
  },
})
TabNavigator.navigationOptions = ({ navigation }) => {
  const { routeName } = navigation.state.routes[navigation.state.index]
  const headerTitle = routeName
  return {
    headerTitle,
  }
}

export default TabNavigator;
