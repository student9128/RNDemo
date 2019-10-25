import React, { Component } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import Icon from 'react-native-vector-icons/Ionicons';

class IconWithBadge extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    const { name, badgeCount, color, size } = this.props
    return (
      <View style={{ width: 24, height: 24, margin: 5,}}>
        <Icon name={name} size={size} color={color} />
        {badgeCount > 0 ?
          <View style={[styles.badgeStyle, {
            right: badgeCount > 99 ? -20 : badgeCount > 9 ? -12 : -6,
            width: badgeCount > 99 ? 28 : badgeCount > 9 ? 20 : 12,
          }]}>
            <Text style={{ color: 'white', fontSize: 12 }}>{badgeCount}</Text>
          </View> : null
        }
      </View>
    );
  }
}

export default IconWithBadge;
const styles = StyleSheet.create({
  badgeStyle: {
    position: 'absolute',
    right: -6,
    top: -3,
    backgroundColor: 'red',
    borderRadius: 6,
    width: 12,
    height: 12,
    justifyContent: 'center',
    alignItems: 'center'
  }
})