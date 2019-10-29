import React, { Component } from 'react';
import { View, Text ,Dimensions} from 'react-native';
import AlbumView from './components/albumView'
class AlbumScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
        <AlbumView style={{width:Dimensions.get('window').width,height:Dimensions.get('window').height}}/>
    );
  }
}

export default AlbumScreen;
