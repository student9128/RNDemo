import React, { Component } from 'react';
import { View, Text ,Dimensions,
  UIManager,
  findNodeHandle,
DeviceEventEmitter} from 'react-native';
import AlbumView from './components/albumView'
class AlbumScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  componentDidMount(){
    DeviceEventEmitter.addListener('photoApply', (data) => {
      alert(JSON.stringify(data))
      //返回的是数组
    })
  }
  componentWillUnmount(){
    DeviceEventEmitter.removeAllListeners
  }
  _onItemClick=(event)=>{
    alert(JSON.stringify(event.nativeEvent))
    // alert(event.nativeEvent.path)

  }

  render() {
    return (
        <AlbumView style={{flex: 1,}}
        onItemClick={(data)=>{this._onItemClick(data)}}/>
    );
  }
}

export default AlbumScreen;
