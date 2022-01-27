# react-native-documentscanner-android

## React Native module to auto scan documents (Android only)

Live document detection library. Returns either a URI of the captured image, allowing you to easily store it or use it as you wish !

Features:

- Live detection
- Perspective correction and image crop
- Flash


![enter image description 
here](https://media.giphy.com/media/KZBdm9gbGGRBlRZV1t/giphy.gif)
## Installation

`npm`
```sh
npm install react-native-documentscanner-android
```
`yarn`
```sh
yarn add react-native-documentscanner-android
```

### Observation
 #### In `android/app/src/main/AndroidManifest.xml`
 Change manifest header to avoid "Manifest merger error". After you add `xmlns:tools="http://schemas.android.com/tools"` should look like this:
 ```
 <manifest xmlns:android="http://schemas.android.com/apk/res/android" package="com.<yourAppName>" xmlns:tools="http://schemas.android.com/tools">
 ```
 Add `tools:replace="android:allowBackup"` in <application tag. It should look like this:
 ```
 <application tools:replace="android:allowBackup" android:name=".MainApplication" android:label="@string/app_name" android:icon="@mipmap/ic_launcher" android:allowBackup="false" android:theme="@style/AppTheme">
 ```

Add permissions:
 ```
 <uses-permission android:name="android.permission.CAMERA" />
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 ```
## Usage

```tsx
import DocumentScannerAndroid from "react-native-documentscanner-android";

// ...

<DocumentScannerAndroid 
    style={{flex: 1}}
    onPictureTaken={(document) => console.log(document.path)}
    onProcessing={(data) => console.log(data.processing)}
    enableTorch={false}
    detectionCountBeforeCapture={5}
/>
```

### Properties

| Props                       | Default | Type     | Description                                                                                                  |
| --------------------------- | ------- | -------- | ------------------------------------------------------------------------------------------------------------ |
| manualOnly                  | false   | `bool`   | if true, auto-detect is disabled                                                                             |
| enableTorch                 | false   | `bool`   | Allows to active or deactivate flash during document detection                                               |
| detectionCountBeforeCapture | 15      | `number` | Number of correct rectangle to detect before capture document                                                |
| brightness                  | 10      | `number` | This property only work to enhance document at the save moment                                               |
| contrast                    | 1       | `number` | This property only work to enhance document at the save moment                                               |
| noGrayScale                 | false   | `bool`   | Currently this module saves pictures only in gray scale, this property adds the option to disable gray scale |

### Manual capture

- Import `capture` method

```tsx
import { capture } from 'react-native-documentscanner-android';

```

- Then use it 

```ts
<Button
    title="Manual capture"
    onPress={() => capture()}
/>
```

### Returned Image

| Prop           | Params | Type   | Description                                                                            |
| -------------- | ------ | ------ | -------------------------------------------------------------------------------------- |
| onPictureTaken | data   | object | Returns an image in a object `{ path: ('imageUri')}`                                   |
| onProcessing   | data   | object | Returns an object `{processing: (true | false)}` to show is an image is processing yet |

The images are saved in `Documents` folder.


#### Todo

- Pass overlay color dynamically
- Pass contrast and brightness to preview
- Use front cam
- Use base64
## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT