##################################################################################
##WORK IN PROGRESS                         NOT WORKING YET                       #
##################################################################################

## React Native module to auto scan documents

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
3. ## IMPORTANT - Go to folder app/settings.gradle and add 

include ':react-native-documentscanner-android'
project(':react-native-documentscanner-android').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-documentscanner-android/android')

#### Add this
include ':openCVLibrary310'
project(':openCVLibrary310').projectDir = new File(rootProject.projectDir,'../node_modules/react-native-documentscanner-android/android/openCVLibrary310')



### Usage
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


##### Params

| Props  | Params  | type  | Description  |
| ------------ | ------------ | ------------ | ------------ |
| onPictureTaken  | function(data){ console.log(data.path)}  | function  | this function is passed to get the path of image |
| enableTorch  | false  | boolean  | Enable or disable torch mode  |
| detectionCountBeforeCapture  | 15  | number | number of rectangles detected before to capture the image  |

### Contributors are welcome !!
