import React, { Component } from 'react';
import { View, Text } from 'react-native';
import TextButton from '../common/TextButton'
class ScreenTwo extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <View>
        <Text> screenTwo </Text>
        <TextButton title='Go ScreenThree' onPress={()=>this.props.navigation.navigate('ScreenThree')}/>
      </View>
    );
  }
}

export default ScreenTwo;
