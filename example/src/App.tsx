import * as React from 'react';
import DocumentScannerAndroidView from 'react-native-document-scanner-android';

export default function App() {
  return (
    <DocumentScannerAndroidView
      style={{ flex: 1 }}
      onPictureTaken={(pic) => console.log('Pictture', pic)}
      onProcessing={(p) => console.log('Processing', p)}
    />
  );
}
