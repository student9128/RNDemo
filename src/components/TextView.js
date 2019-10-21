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
    }
    //如果这里不封装，可以直接在使用的地方调用
    _onClick(event) {
        if (!this.props.onClick) {
            return
        }
        this.props.onClick(event.nativeEvent.message)
    }

    render() {
        const { text, color, style } = this.props
        return (
            <NativeTextView {...this.props}
                onClick={(e) => this._onClick(e)}>
            </NativeTextView>
        );
    }
}

export default TextView;
