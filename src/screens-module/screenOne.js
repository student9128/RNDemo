import React, { Component } from 'react';
import { View, Text } from 'react-native';
import TextButton from '../common/TextButton'
class ScreenOne extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <View>
        <Text> screenOne </Text>
        <TextButton title='Go ScreenTwo' onPress={()=>this.props.navigation.navigate('ScreenTwo')}/>
      </View>
    );
  }
}

export default ScreenOne;
