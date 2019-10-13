import React, { Component } from 'react';
import { View, Text,ART,Button } from 'react-native';
import  ArtTest from './artTest'
import  Wedge from './arcTest'

const {Surface,Shape,Path,Group}=ART
class HomeScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <View style={{flex:1,}}>
        <Text> homeScreen </Text>
        <Button onPress={()=>{this.props.navigation.navigate('ReactNativeInteractionScreen')}}
        title='RN与原生交互'></Button>
        {/* <Surface width={100} height={100}>
        <ArtTest
         outerRadius={50}
         startAngle={0}
         endAngle={60}
         originX={50}
         originY={50}
         fill="blue"/>
        </Surface>
        <Surface width={300} height={400} style={{backgroundColor: 'yellow', marginTop: 10}}>
  <Wedge
        outerRadius={100}
        startAngle={0}
        endAngle={160}
        originX={80}
        originY={50}
        fill="blue" />
</Surface> */}
      </View>
    );
  }
}

export default HomeScreen;
