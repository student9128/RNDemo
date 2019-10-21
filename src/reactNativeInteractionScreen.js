import React, { Component } from 'react';
import {
  View,
  Text,
  TouchableNativeFeedback,
  TextInput,
  NativeModules,
  StyleSheet,
  NativeEventEmitter,
  UIManager,
  findNodeHandle
} from 'react-native';
const RNModule = NativeModules.RNModule
import TextButton from './common/TextButton';
import Colors from './common/colors'
import { deviceWidth, realDeviceHeight } from './common/Utils';
import TextView from './components/TextView'
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
      const { content } = await RNModule.usePromise(this.state.textValue + '(二)')
      alert(content)
    } catch (e) {
      console.error(e)
    }
  }
  componentDidMount() {
    const eventEmitter = new NativeEventEmitter(RNModule)
    eventEmitter.addListener('toRN', (event) => {
      alert(event.content)
    })

  }
  _onClick = (msg) => {
    // RNModule.showToast(event.nativeEvent.message, RNModule.SHORT)
    RNModule.showToast(msg, RNModule.SHORT)
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this.nativeText),'1',[Colors.white]
    )
    
  }
  render() {
    return (
      <View style={{ flex: 1, flexDirection: 'row', flexWrap: 'wrap', }}>
        <TouchableNativeFeedback
          onPress={() => { RNModule.showToast('abs', RNModule.SHORT) }}>
          <View style={styles.toastViewStyle}>
            <Text style={{ color: 'white' }}>showToast</Text>
          </View>
        </TouchableNativeFeedback>
        <TextInput
          autoCapitalize='none'
          autoFocus={false}
          multiline={false}
          style={styles.textInputStyle}
          underlineColorAndroid="transparent"
          maxLength={18}
          placeholder='输入内容点击下面的按钮试试效果，1为true'
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
        <TextButton title='通过Promise和原生交互(二)'
          onPress={() => {
            this.usePromise()
          }} />
        <TextButton title='发送事件到RN'
          onPress={() => {
            RNModule.sendEvent()
          }} />
        <TextButton title='StartActivityForResult'
          onPress={() => {
            RNModule.startActivity('com.rndemo.NextActivity')
              .then((x) => {
                alert(x)
              })
          }} />
        <TextView text='I am native widget，try click me' style={{ width: deviceWidth(), height: 100, backgroundColor: Colors.colorAccent }}
          onClick={(msg) => { this._onClick(msg) }}
          ref={v=>this.nativeText=v} />
      </View>
    );
  }
}

export default ReactNativeInteractionScreen;

const styles = StyleSheet.create({
  toastViewStyle: {
    backgroundColor: Colors.colorAccent, alignSelf: 'flex-start',
    paddingHorizontal: 10,
    paddingVertical: 5,
    borderRadius: 15,
    marginHorizontal: 15,
    marginVertical: 10,
  },
  textInputStyle: {
    borderColor: Colors.colorPrimary,
    borderWidth: 2,
    paddingHorizontal: 5,
    paddingVertical: 2,
    borderRadius: 5,
    width: deviceWidth() - 30,
    marginHorizontal: 15,
  }
})