![enter image description 
here](https://media.giphy.com/media/KZBdm9gbGGRBlRZV1t/giphy.gif)

## React Native module to auto scan documents (Android only)

Live document detection library. Returns either a URI of the captured image, allowing you to easily store it or use it as you wish !

Features:

- Live detection
- Perspective correction and image crop
- Flash

### Get started

`npm install --save react-native-documentscanner-android`

In MainApplication.java, add this Line `import com.documentscanner.DocumentScannerPackage;` at the top of file,

```java
@Override
protected  List<ReactPackage> getPackages() {
  return Arrays.<ReactPackage>asList(
    new  MainReactPackage(),
    new  DocumentScannerPackage() <--- this  line,
    ...
  );
}
```

#### IMPORTANT - Go to folder app/settings.gradle and add

```java
include ':react-native-documentscanner-android'
project(':react-native-documentscanner-android').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-documentscanner-android/android')
```

#### Add this (don't forget)

```java
include ':openCVLibrary310'
project(':openCVLibrary310').projectDir = new File(rootProject.projectDir,'../node_modules/react-native-documentscanner-android/android/openCVLibrary310')
```

### Usage

```javascript
import DocumentScanner from 'react-native-documentscanner-android';

class YourComponent extends Component {
  render() {
    return (
      <View>
        <DocumentScanner
          onPictureTaken={data => {
            console.log(data.path);
          }}
          enableTorch={false}
          detectionCountBeforeCapture={5}
        />
      </View>
    );
  }
}
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

- Get the component ref

`<DocumentScanner ref={(ref) => this.scanner = ref} />`

- Then

```javascript
this.scanner.capture();
```

### Returned Image

| Prop           | Params | Type   | Description                                                                            |
| -------------- | ------ | ------ | -------------------------------------------------------------------------------------- |
| onPictureTaken | data   | object | Returns an image in a object `{ path: ('imageUri')}`                                   |
| onProcessing   | data   | object | Returns an object `{processing: (true | false)}` to show is an image is processing yet |

The images are saved in `Documents` folder.

### Manual Focus

- If the device has autofocus feature, you can use this method to focus on the center of the screen programmatically

- Get the component ref

`<DocumentScanner ref={(ref) => this.scanner = ref} />`

- Then

```javascript
this.scanner.focus();
```

#### Todo

- Pass overlay color dynamically
- Pass contrast and brightness to preview
- Use front cam
- Use base64

## Contributors are welcome !!

Inspired in android project

- https://github.com/ctodobom/OpenNoteScanner
- https://github.com/Michaelvilleneuve/react-native-document-scanner
