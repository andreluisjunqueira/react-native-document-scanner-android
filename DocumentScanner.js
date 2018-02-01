import React, { Component } from 'react';
import PropTypes from 'prop-types';

import {
    DeviceEventEmitter, // android
    NativeAppEventEmitter, // ios
    NativeModules,
    Platform,
    StyleSheet,
    requireNativeComponent,
    View,
    ViewPropTypes
} from 'react-native';

var iface = {
    name: 'DocumentScanner',
    propTypes: {
      documentAnimation : PropTypes.bool,      
      detectionCountBeforeCapture : PropTypes.number,      
      enableTorch : PropTypes.bool,      
      ...View.propTypes // include the default view properties
    },
  };

const DocumentScanner = requireNativeComponent('DocumentScanner', iface);

class Scanner extends Component{

  state = {
    onPictureTaken : null
  }

  componentWillMount(){
    const { onPictureTaken } = this.props;
    DeviceEventEmitter.addListener('onPictureTaken',onPictureTaken)
    this.setState({onPictureTaken});
  }
  
  componentWillUnmount(){
    DeviceEventEmitter.removeListener('onPictureTaken',this.state.onPictureTaken);
  }

  render(){
    return<DocumentScanner {...this.props} />
  }
}

Scanner.defaultProps = {
  onPictureTaken : ()=>{}
}
export default Scanner;