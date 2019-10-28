import React, { Component } from 'react';
import { View, Text } from 'react-native';

class ParamScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  getNavigationParams() {
    return this.props.navigation.state.params || {}
  }
  render() {
    return (
      <View>
        <Text> 传过来的参数是： </Text>
        <Text> {this.props.navigation.getParam('params')} </Text>
        <Text> {this.props.navigation.state.params.params} </Text>
        <Text> {this.getNavigationParams().params} </Text>
      </View>
    );
  }
}

export default ParamScreen;
