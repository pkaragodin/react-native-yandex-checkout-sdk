
package com.reactlibrary;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import ru.yandex.money.android.sdk.Amount;
import ru.yandex.money.android.sdk.Checkout;
import ru.yandex.money.android.sdk.Configuration;
import ru.yandex.money.android.sdk.PaymentMethodType;
import ru.yandex.money.android.sdk.ShopParameters;

public class RNYandexCheckoutSdkModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private static final BigDecimal MAX_AMOUNT = new BigDecimal("99999.99");
  public static final Currency RUB = Currency.getInstance("RUB");
  public static final String KEY_AMOUNT = "amount";

  public RNYandexCheckoutSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @ReactMethod
  public void init(Promise promise){
    try {
      Activity activity = getCurrentActivity();
      android.support.v4.app.FragmentManager fragmentManager =
              ((android.support.v4.app.FragmentActivity) activity).getSupportFragmentManager();
      Checkout.attach(fragmentManager);
      promise.resolve("success");
    }catch (Exception e){
      promise.reject(e);
    }
  }

  @ReactMethod
  public void pay(ReadableMap params, final Promise promise){
    try {

      Checkout.setResultCallback(new Checkout.ResultCallback() {
        @Override
        public void onResult(@NonNull String paymentToken, @NonNull PaymentMethodType type) {
          //result handling
          WritableMap result = new WritableNativeMap();
          result.putString("paymentToken", paymentToken);
          result.putString("type", type.name());
          promise.resolve(result);
        }
      });
      Boolean enableTestMode = params.getBoolean("enableTestMode");
      if(enableTestMode ||  params.getBoolean("googlePayTestEnvironment")){
      Checkout.configureTestMode(
              new Configuration(
                      params.getBoolean("enableTestMode"),
                      false,
                      false,
                      2,
                      params.getBoolean("googlePayAvailable"),
                      params.getBoolean("googlePayTestEnvironment")
                      // enableTestMode - is test mode enabled,
                      // completeWithError - complete tokenization with error,
                      // paymentAuthPassed - user authenticated,
                      // linkedCardsCount - test linked cards count,
                      // googlePayAvailable - emulate google pay availability,
                      // googlePayTestEnvironment - use google pay test environment
              )
      );
      }
      Set<PaymentMethodType> paymentMethodTypes = new HashSet<>();
      ReadableArray _paymentMethodTypes = params.getArray("paymentMethodTypes");
      for(int i = 0; i< _paymentMethodTypes.size(); i++){
        paymentMethodTypes.add(PaymentMethodType.valueOf(_paymentMethodTypes.getString(i)));
      }

      Checkout.tokenize(
              this.getCurrentActivity(),
              new Amount(new BigDecimal(params.getString("amount")),
                      Currency.getInstance(params.getString("currency"))),
              new ShopParameters(
                      params.getString("shopTitle"),
                      params.getString("shopDescription"),
                      params.getString("clientApplicationKey"),
                      paymentMethodTypes,
                      params.getBoolean("enableGooglePay"),
                      params.getString("shopId"),
                      params.getString("gatewayId"),
                      params.getBoolean("showLogo")


              )
      );


    } catch (Exception e){
      promise.reject("error", e);
    }
  }

  @ReactMethod
  public void deinit(Promise promise){
    try{
      Checkout.detach();
      promise.resolve("success");
    }catch (Exception e){
      promise.reject(e);
    }
  }

  private void handleTokenizeResult(String paymentToken, PaymentMethodType type){

  }
  @ReactMethod
  public void test(Promise promise){
    //final Settings settings = new Settings(this.getCurrentActivity().getApplicationContext());
    //final Set<PaymentMethodType> paymentMethodTypes = getPaymentMethodTypes(settings);
    try {
      Checkout.configureTestMode(
              new Configuration(
                      true,
                      false,
                      false,
                      2,
                      false,
                      true
                      // enableTestMode - is test mode enabled,
                      // completeWithError - complete tokenization with error,
                      // paymentAuthPassed - user authenticated,
                      // linkedCardsCount - test linked cards count,
                      // googlePayAvailable - emulate google pay availability,
                      // googlePayTestEnvironment - use google pay test environment
              )
      );
      Set<PaymentMethodType> paymentMethodTypes = new HashSet<>();
      paymentMethodTypes.add(PaymentMethodType.BANK_CARD);
      Checkout.tokenize(
              this.getCurrentActivity(),

              new Amount(new BigDecimal("10.0"), Currency.getInstance("RUB")),
              new ShopParameters(
                      "Название магазина",
                      "Описание магазина",
                      "test_NTUzNzg2ek361boKMc67WZqIy1Hm6gO4c_ApfVH-A4M",
                      paymentMethodTypes

              )
      );

      promise.resolve("success");
    } catch (Exception e){
      promise.reject("error", e);
    }
  }

  @ReactMethod
  public void destroy(Promise promise){
    Checkout.detach();
  }


  @Override
  public String getName() {
    return "RNYandexCheckoutSdk";
  }
}
