import React, { Component } from 'react';
import { View, Text,Image } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
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

export default ReactNavigationScreen;
