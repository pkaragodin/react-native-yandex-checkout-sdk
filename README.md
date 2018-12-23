
# react-native-yandex-checkout-sdk

## Getting started

`$ npm install react-native-yandex-checkout-sdk --save`

### Mostly automatic installation

`$ react-native link react-native-yandex-checkout-sdk`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-yandex-checkout-sdk` and add `RNYandexCheckoutSdk.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNYandexCheckoutSdk.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNYandexCheckoutSdkPackage;` to the imports at the top of the file
  - Add `new RNYandexCheckoutSdkPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-yandex-checkout-sdk'
  	project(':react-native-yandex-checkout-sdk').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-yandex-checkout-sdk/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-yandex-checkout-sdk')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNYandexCheckoutSdk.sln` in `node_modules/react-native-yandex-checkout-sdk/windows/RNYandexCheckoutSdk.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Yandex.Checkout.Sdk.RNYandexCheckoutSdk;` to the usings at the top of the file
  - Add `new RNYandexCheckoutSdkPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNYandexCheckoutSdk from 'react-native-yandex-checkout-sdk';

// TODO: What to do with the module?
RNYandexCheckoutSdk;
```
  