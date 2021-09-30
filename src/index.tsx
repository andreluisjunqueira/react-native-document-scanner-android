import React, { useEffect } from 'react';

import {
  requireNativeComponent,
  UIManager,
  NativeModules,
  DeviceEventEmitter,
  ViewStyle,
} from 'react-native';

const LINKING_ERROR =
  "The package 'react-native-document-scanner-android' doesn't seem to be linked. Make sure: \n\n" +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

type DocumentScannerAndroidProps = {
  documentAnimation?: boolean;
  detectionCountBeforeCapture?: number;
  enableTorch?: boolean;
  manualOnly?: boolean;
  overlayColor?: string;
  contrast?: number;
  brightness?: number;
  noGrayScale?: boolean;
  style?: ViewStyle;
};

const ComponentName = 'DocumentScannerAndroidView';

const DocumentScannerAndroidView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<DocumentScannerAndroidProps>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

const CameraManager = NativeModules.DocumentScannerManager || {};

type OnPicture = {
  path: string;
};

type OnProcessing = {
  processing: boolean;
};

type ScannerProps = {
  onPictureTaken?: (data: OnPicture) => void;
  onProcessing?: (data: OnProcessing) => void;
} & DocumentScannerAndroidProps;

const DocumentScannerAndroid: React.FC<ScannerProps> = ({
  onPictureTaken,
  onProcessing,
  ...restProps
}) => {
  useEffect(() => {
    if (onPictureTaken)
      DeviceEventEmitter.addListener('onPictureTaken', onPictureTaken);

    if (onProcessing)
      DeviceEventEmitter.addListener('onProcessingChange', onProcessing);

    return () => {
      if (onPictureTaken)
        DeviceEventEmitter.removeListener('onPictureTaken', onPictureTaken);

      if (onProcessing)
        DeviceEventEmitter.removeListener('onProcessingChange', onProcessing);
    };
  }, [onPictureTaken, onProcessing]);

  return <DocumentScannerAndroidView {...restProps} />;
};

export const capture = CameraManager.capture;
export default React.memo(DocumentScannerAndroid);
