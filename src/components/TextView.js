import React, { Component } from 'react';
import { View, Text } from 'react-native';
import NativeTextView from './NativeViewSource'
import PropTypes from 'prop-types'
class TextView extends Component {
    static propTypes = {
        text: PropTypes.string.isRequired,
        ...View.propTypes
    }
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        const { text, color,style } = this.props
        return (
            <NativeTextView text={text}
                color={color}
                style={style}>
            </NativeTextView>
        );
    }
}

export default TextView;
