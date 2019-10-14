import React, { Component } from 'react';
import {
  View,
  Text,
  TouchableNativeFeedback,
  StyleSheet
} from 'react-native';
import Colors from './colors';

class TextButton extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    const { title, onPress, bgStyle, titleStyle } = this.props
    return (
      <TouchableNativeFeedback onPress={() => { onPress && onPress() }}
      >
        <View style={{
          ...Styles.containerStyle,
          ...bgStyle,
        }}>
          <Text style={{
            ...Styles.textStyle,
            ...titleStyle,
          }}> {title} </Text>
        </View>
      </TouchableNativeFeedback>
    );
  }
}

export default TextButton;
const Styles = StyleSheet.create({
  containerStyle: {
    alignSelf: 'flex-start',
    paddingStart: 10, paddingEnd: 10,
    borderRadius: 5,
    paddingTop: 5,
    paddingBottom: 5,
    margin:5,
    backgroundColor: Colors.colorAccent
  },
  textStyle: {
    color: Colors.white
  }
})
