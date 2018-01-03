## React Native module to auto scan documents

### USE manual installation, link is not working fine

--> npm install --save react-native-documentscanner-android

1. In MainApplication.java, add this Line `import com.documentscanner.DocumentScannerPackage;` at the top of file,
after that add this:
`
    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
            new DocumentScannerPackage() <--- this line,
            ...
      );
    }
`
2. IMPORTANT - Go to folder app/settings.graddle and add `
    include ':react-native-documentscanner-android'
    project(':react-native-documentscanner-android').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-documentscanner-android/android')

    include ':openCVLibrary310'
    project(':openCVLibrary310').projectDir = new File(rootProject.projectDir,'../node_modules/react-native-documentscanner-android/android/openCVLibrary310')

`

### USAGE 

import DocumentScanner from 'react-native-documentscanner-android';

class YourComponent extends Component {
  render() {
    return (
      <View>
        <DocumentScanner
          onPictureTaken={data =>{
              console.log(data.path)
          }}
          enableTorch={false}
          detectionCountBeforeCapture={5}
        />
      </View>
    );
  }
}

```

## Params

| Props             | Params                                 | Type     | Description |
|-------------------|----------------------------------------|----------|-------------|
| onPictureTaken | `function(data:path){}` | `function` | is called whenever a image is captured|
| enableTorch | `false` | `bool` | enables the torch mode|
| detectionCountBeforeCapture | `15` | `Number` | Amount of rectangle capturated before to take the picture|

