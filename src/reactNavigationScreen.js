import React, { Component } from 'react';
import { View, Text, Image,TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/Ionicons';
import TextButton from './common/TextButton'
import Styles from './common/Styles';
const ThemeContext = React.createContext(null)
const ThemeConstants={
  light:{
    backgroundColor:'#ffb400',
    fontColor:'black'
  },
  dark:{
    backgroundColor:'#474744',
    fontColor:'white'
  }
}
class ReactNavigationScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      theme:'light'
    };
  }
  _toggleTheme=()=>{
    this.setState(({theme})=>({
      theme:theme==='light'?'dark':'light'
    }))
  }

  render() {
    return (
      <View style={{flex:1}}>
      <View style={Styles.commonContainer}>
        <TextButton title='使用场景一'
        onPress={()=>{this.props.navigation.navigate('SceneOne')}}/>
        <TextButton title='使用场景二'
        onPress={()=>{this.props.navigation.navigate('SceneTwo')}}/>
        <TextButton title='使用场景三'
        onPress={()=>{this.props.navigation.navigate('ScreenOne')}}/>
        <TextButton title='传参'
        onPress={()=>{this.props.navigation.navigate('ParamScreen',{params:'Hello,Navigation~'})}}/>
           <TextButton title='修改主题'
        onPress={()=>{this._toggleTheme()}}/>
           <TextButton title='Drawer Screen'
        onPress={()=>{this.props.navigation.navigate('DrawerScreen')}}/>
      </View>
        <ThemeContext.Provider
        value={{theme:this.state.theme,toggleTheme:this._toggleTheme}}>
        <TestV/>
        </ThemeContext.Provider>
      </View>
    );
  }
}

export default ReactNavigationScreen;


class TestV extends React.Component {
  render() {
    return (
      <ThemedView
        style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}
      >
        <ThemeContext.Consumer>
          {({ toggleTheme }) => (
            <ThemedButton title="Toggle theme" onPress={toggleTheme} />
          )}
        </ThemeContext.Consumer>
      </ThemedView>
    );
  }
}
class ThemedButton extends React.Component {
  render() {
    let { title, ...props } = this.props;
    return (
      <TouchableOpacity {...props}>
        <ThemeContext.Consumer>
          {({ theme }) => (
            <Text style={{ color: ThemeConstants[theme].fontColor }}>
              {title}
            </Text>
          )}
        </ThemeContext.Consumer>
      </TouchableOpacity>
    );
  }
}
class ThemedView extends React.Component {
  render() {
    return (
      <ThemeContext.Consumer>
        {({ theme }) => (
          <View
            {...this.props}
            style={[
              this.props.style,
              { backgroundColor: ThemeConstants[theme].backgroundColor },
            ]}
          />
        )}
      </ThemeContext.Consumer>
    );
  }
}