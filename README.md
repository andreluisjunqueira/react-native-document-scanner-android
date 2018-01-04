## React Native module to auto scan documents
###### USE manual installation, link is not working fine

1.  In MainApplication.java, add this Line `import com.documentscanner.DocumentScannerPackage;` at the top of file,
2. after that add this:
```java
    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
            new DocumentScannerPackage() <--- this line,
            ...
      );
    }
```
3. IMPORTANT - Go to folder app/settings.graddle and add 
    include ':react-native-documentscanner-android'
    project(':react-native-documentscanner-android').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-documentscanner-android/android')

    include ':openCVLibrary310'
    project(':openCVLibrary310').projectDir = new File(rootProject.projectDir

###Usage
```javascript
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


#####Params
| Props  | Params  | type | Description  |
| ------------ | ------------ | ------------ | ------------ |
| onPictureTaken  | function(data){ console.log(data.path)}  | function  | this function is passed to get the path of captured image  |
| detectionCountBeforeCapture    | 15  | Int  | number of rectangles detected before to capture the image  |
| enableTorch   | false | boolean  | Enable or disable torch mode  |
