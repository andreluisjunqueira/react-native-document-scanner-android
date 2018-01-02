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
      ...View.propTypes // include the default view properties
    },
  };

const DocumentScanner = requireNativeComponent('DocumentScanner', iface);

class Scanner extends Component{

  componentWillMount(){
    
    DeviceEventEmitter.addListener('onPictureTaken',(data)=>{
      console.log('Dadossss-->',data)
    })
  }

  render(){
    return<DocumentScanner/>
  }
}
export default Scanner;