import { requireNativeComponent, UIManager, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-document-scanner-android' doesn't seem to be linked. Make sure: \n\n` + Platform.select({
  ios: "- You have run 'pod install'\n",
  default: ''
}) + '- You rebuilt the app after installing the package\n' + '- You are not using Expo managed workflow\n';
const ComponentName = 'DocumentScannerAndroidView';
export const DocumentScannerAndroidView = UIManager.getViewManagerConfig(ComponentName) != null ? requireNativeComponent(ComponentName) : () => {
  throw new Error(LINKING_ERROR);
};
//# sourceMappingURL=index.js.map