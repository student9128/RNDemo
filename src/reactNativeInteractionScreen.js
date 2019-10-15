import React, { Component } from 'react';
import {
  View,
  Text,
  Button,
  TouchableNativeFeedback,
  TextInput,
  NativeModules,
  StyleSheet,
  NativeEventEmitter,
  DeviceEventEmitter
} from 'react-native';
const RNModule = NativeModules.RNModule
import TextButton from './common/TextButton';
import Colors from './common/colors'
import { deviceWidth, realDeviceHeight } from './common/Utils';
/**
 * RN与原生交互
 */
class ReactNativeInteractionScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      textValue: ''
    };
  }
  async usePromise() {
    try {
      const { content } = await RNModule.usePromise(this.state.textValue+'第二个')
      alert(content)
    } catch (e) {
      console.error(e)
    }
  }
componentDidMount(){
  const eventEmitter = new NativeEventEmitter(RNModule)
  eventEmitter.addListener('toRN',(event)=>{
    alert(event.content)
  })

}
  render() {
    return (
      <View>
        <Text> reactNativeInteractionScreen </Text>
        <TouchableNativeFeedback
          onPress={() => { RNModule.showToast('abs', RNModule.SHORT) }}>
          <View style={{
            backgroundColor: 'red', alignSelf: 'flex-start',
            paddingHorizontal: 10,
            paddingVertical: 10,
            borderRadius: 5
          }}>
            <Text>showToast</Text>
          </View>
        </TouchableNativeFeedback>
        <TextInput
          autoCapitalize='none'
          autoFocus={false}
          multiline={false}
          style={styles.textInputStyle}
          underlineColorAndroid="transparent"
          maxLength={18}
          onChangeText={(text) => this.state.textValue = text} />
        <TextButton title='通过callback和原生交互'
          onPress={() => {
            RNModule.showBoolean(this.state.textValue, (callback) => {
              if (callback) {
                RNModule.showToast('true', RNModule.SHORT)
              } else {
                RNModule.showToast('false', RNModule.SHORT)

              }
            })
          }}
        />
        <TextButton title='通过Promise和原生交互'
          onPress={() => {
            RNModule.usePromise(this.state.textValue)
              .then((x) => {
                alert(x.content)
              })
          }} />
        <TextButton title='通过Promise和原生交互'
          onPress={() => {
            this.usePromise()
          }} />
        <TextButton title='发送事件到RN'
          onPress={() => {
            RNModule.sendEvent()
          }} />
      </View>
    );
  }
}

export default ReactNativeInteractionScreen;

const styles = StyleSheet.create({
  textInputStyle: {
    borderColor: Colors.colorPrimary,
    borderWidth: 2,
    paddingHorizontal: 5,
    paddingVertical: 2,
    borderRadius: 5,
    width: deviceWidth() - 30
  }
})