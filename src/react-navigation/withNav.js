import React, { Component } from 'react';
import { View, Text} from 'react-native';
import { withNavigation } from 'react-navigation';
import TextButton from '../common/TextButton'
class BackButton extends React.Component {
    render() {
      return (
        <TextButton title='Back to top use withNavigation'
          onPress={() => { this.props.navigation.pop() }} />
      )
    }
  }
  
export default withNavigation(BackButton);
