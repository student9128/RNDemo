import React, { Component } from 'react';
import { View, Text } from 'react-native';
import LinearWidget from './linearWidget'

class LinearView extends Component {
 static propTypes={
     ...View.propTypes
 }

  render() {
      const {style}= this.props
    return (
      <LinearWidget style={style}>
      </LinearWidget>
    );
  }
}

export default LinearView;
