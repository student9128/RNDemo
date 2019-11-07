import React, { Component } from 'react';
import { View, Text,StyleSheet,Button,TouchableWithoutFeedback } from 'react-native';
import { createDrawerNavigator } from 'react-navigation-drawer';
import { createAppContainer,Themed } from 'react-navigation';
import Icon from 'react-native-vector-icons/Ionicons';
import Colors from '../common/colors';
class MyHomeScreen extends React.Component {
    static navigationOptions = {
      drawerLabel: 'Home',
      drawerIcon: ({ tintColor }) => (
        <Icon
          name='md-home'
          size={30}
          color={tintColor}
        />
      ),
    };
  
    render() {
      return (
          <View>
                      <TouchableWithoutFeedback
        onPress={()=>{this.props.navigation.pop()}}>
              <View style={{height:56,backgroundColor:Colors.colorPrimary,justifyContent:'center'}}>
              <View
                style={{ marginLeft: 15, paddingHorizontal: 5 }}>
                <Icon name='md-arrow-back' size={25} color='white' />
            </View>
              </View>
            </TouchableWithoutFeedback>
              <Button
                onPress={() => this.props.navigation.navigate('Notifications')}
                title="Go to notifications"
              />
              </View>
              
      );
    }
  }
  
  class MyNotificationsScreen extends React.Component {
      static navigationOptions = {
          drawerLabel: 'Notifications',
          drawerIcon: ({ tintColor }) => (
              <Icon
                  name='logo-google'
                  size={30}
                  color={tintColor}
              />
          ),
      };
  
    render() {
      return (
          <View>
              <TouchableWithoutFeedback
        onPress={()=>{this.props.navigation.pop()}}>
              <View style={{height:56,backgroundColor:Colors.colorPrimary,justifyContent:'center'}}>
              <View
                style={{ marginLeft: 15, paddingHorizontal: 5 }}>
                <Icon name='md-arrow-back' size={25} color='white' />
            </View>
              </View>
            </TouchableWithoutFeedback>
              <Button
                  onPress={() => this.props.navigation.goBack()}
                  title="Go back home"
              />
          </View>
      );
    }
  }
  
  const styles = StyleSheet.create({
    icon: {
      width: 24,
      height: 24,
    },
  });
  
  const MyDrawerNavigator = createDrawerNavigator({
    MyHome: {
      screen: MyHomeScreen,
    },
    Notifications: {
      screen: MyNotificationsScreen,
    },
  },{
    initialRouteName:'MyHome',
    //   hideStatusBar:true,
    //   statusBarAnimation:'fade',
      drawerType:'front',
      keyboardDismissMode:'on-drag',
    //   overlayColor:1
    contentOptions:{
        activeTintColor:Colors.colorPrimary
    }
  });
  
  const MyApp = createAppContainer(MyDrawerNavigator);
// class DrawerScreen extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//     };
//   }

//   render() {
//     return (
//       <View>
//         <Text> drawerScreen </Text>
//       </View>
//     );
//   }
// }

export default MyDrawerNavigator;
