import React, { Component } from 'react';
import { View, Text, Button, TouchableWithoutFeedback } from 'react-native';
import { createStackNavigator } from 'react-navigation-stack';
import { createBottomTabNavigator } from 'react-navigation-tabs';
import Icon from 'react-native-vector-icons/Ionicons';
import Colors from '../common/colors'
class DetailsScreen extends React.Component {
    render() {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                <Text>Details!</Text>
            </View>
        );
    }
}

class HomeScreen extends React.Component {
    static navigationOptions = ({ navigation, screenProps }) => ({
        headerLeft: <TouchableWithoutFeedback
        onPress={()=>{navigation.pop()}}>
            <View
                style={{ marginLeft: 15, paddingHorizontal: 5 }}>
                <Icon name='md-arrow-back' size={25} color='white' />
            </View>
        </TouchableWithoutFeedback>
    })
    render() {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                {/* other code from before here */}
                <Button
                    title="Go to Details"
                    onPress={() => this.props.navigation.navigate('Details')}
                />
                <Button
                    title="Go Back"
                    onPress={() => this.props.navigation.pop()}
                />
            </View>
        );
    }
}

class SettingsScreen extends React.Component {
    render() {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                {/* other code from before here */}
                <Button
                    title="Go to Details"
                    onPress={() => this.props.navigation.navigate('Details')}
                />
            </View>
        );
    }
}

const HomeStack = createStackNavigator({
    Home: {
        screen: HomeScreen,
        navigationOptions: () => ({
            title: '场景二Home'
        })
    },
    Details: {
        screen: DetailsScreen,
        navigationOptions: () => ({
            title: 'Detail'
        })
    },
}, {
    defaultNavigationOptions: ({
        headerStyle: {
            backgroundColor: Colors.colorPrimary,

        },
        headerTintColor: 'white',
    })
});

const SettingsStack = createStackNavigator({
    Settings: {
        screen: SettingsScreen,
        navigationOptions: () => ({
            title: '场景二Settings'
        })
    },
    Details: {
        screen: DetailsScreen,
        navigationOptions: () => ({
            title: 'Detail'
        })
    },
}, {
    defaultNavigationOptions: ({
        headerStyle: {
            backgroundColor: Colors.colorPrimary,

        },
        headerTintColor: 'white',
    })
})
const TabNavigator = createBottomTabNavigator({
    Home: HomeStack,
    Settings: SettingsStack,
},
{
    defaultNavigationOptions: ({ navigation }) => ({
      tabBarIcon: ({ focused, horizontal, tintColor }) => {
        const { routeName } = navigation.state
        let badgeCount = 0
        let iconName;
        if (routeName === 'Home') {
          iconName = 'md-home';
          badgeCount=9
        } else if (routeName === 'Settings') {
          iconName = 'logo-android';
          badgeCount=0
        }
        return <Icon name={iconName} size={25} color={tintColor}/>
      }
    }),
    tabBarOptions: {
      activeTintColor: Colors.colorPrimary,
      inactiveTintColor: Colors.gray,
    },
  })
export default TabNavigator
