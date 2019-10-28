import React, { Component } from 'react';
import { View, Text } from 'react-native';
import { StackActions,NavigationActions} from 'react-navigation';
import TextButton from '../common/TextButton'
class ScreenThree extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <View>
        <Text> screenThree </Text>
        <TextButton title='回到首页' onPress={()=>{this.props.navigation.dispatch(StackActions.reset({
            index:0,
            actions:[NavigationActions.navigate({routeName:'Home'})]
        }))}}/>
      </View>
    );
  }
}

export default ScreenThree;
