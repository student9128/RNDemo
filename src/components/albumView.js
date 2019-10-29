import React, { Component } from 'react'
import { Text, View } from 'react-native'
import Album from './albumViewSource'
import PropTypes from 'prop-types'
export class AlbumView extends Component {
static propTypes={
    ...View.propTypes
}
    render() {
        return (
                <Album {...this.props}></Album>
        )
    }
}

export default AlbumView
