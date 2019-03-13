import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';

import {
    DeviceEventEmitter, // android
    NativeModules,
    requireNativeComponent,
    View,
    TouchableOpacity
} from 'react-native';

import Styles from './styles';

var iface = {
    name: 'DocumentScanner',
    propTypes: {
      documentAnimation : PropTypes.bool,      
      detectionCountBeforeCapture : PropTypes.number,      
      enableTorch : PropTypes.bool,
      manualOnly: PropTypes.bool,   
      overlayColor: PropTypes.string,
      contrast: PropTypes.number,
      brightness: PropTypes.number,
      noGrayScale: PropTypes.bool,
      ...View.propTypes // include the default view properties
    },
  };

const DocumentScanner = requireNativeComponent('DocumentScanner', iface);
const CameraManager = NativeModules.DocumentScannerManager || {};

class Scanner extends PureComponent{

  static defaultProps = {
    onPictureTaken: ()=>{},
    onProcessing: ()=>{},
    style: Styles.fill,
  }

  componentWillMount(){
    const { onPictureTaken, onProcessing } = this.props;
    DeviceEventEmitter.addListener('onPictureTaken', onPictureTaken);
    DeviceEventEmitter.addListener('onProcessingChange', onProcessing);
  }
  
  componentWillUnmount(){
    const { onPictureTaken, onProcessing } = this.props;
    DeviceEventEmitter.removeListener('onPictureTaken', onPictureTaken);
    DeviceEventEmitter.removeListener('onProcessingChange', onProcessing);
  }

  capture = ()=>{
    CameraManager.capture();
  }

  focus = ()=>{
    CameraManager.focus();
  }

  render() {
    return (
      <View style={this.props.style}>
        <DocumentScanner {...this.props} />
        <TouchableOpacity activeOpacity={1} style={Styles.focusOverlay} onPressOut={() => this.focus()} />
      </View>
    );
  }
}

export default Scanner;
