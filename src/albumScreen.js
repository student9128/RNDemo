import React, { Component } from 'react';
import { View, Text ,Dimensions,
  UIManager,
  findNodeHandle} from 'react-native';
import AlbumView from './components/albumView'
class AlbumScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  _onItemClick=(event)=>{
    alert(JSON.stringify(event.nativeEvent))
    // alert(event.nativeEvent.path)

  }

  render() {
    return (
        <AlbumView style={{width:Dimensions.get('window').width,
        height:Dimensions.get('window').height}}
        onItemClick={(data)=>{this._onItemClick(data)}}/>
    );
  }
}

export default AlbumScreen;
