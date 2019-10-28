import React, { Component } from 'react';
import { View, Text, Image } from 'react-native';
import Icon from 'react-native-vector-icons/Ionicons';
import TextButton from './common/TextButton'
import Styles from './common/Styles';
class ReactNavigationScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <View style={Styles.commonContainer}>
        <TextButton title='使用场景一'
        onPress={()=>{this.props.navigation.navigate('SceneOne')}}/>
        <TextButton title='使用场景二'
        onPress={()=>{this.props.navigation.navigate('SceneTwo')}}/>
        <TextButton title='使用场景三'
        onPress={()=>{this.props.navigation.navigate('ScreenOne')}}/>
        <TextButton title='传参'
        onPress={()=>{this.props.navigation.navigate('ParamScreen',{params:'Hello,Navigation~'})}}/>
      </View>
    );
  }
}

export default ReactNavigationScreen;
