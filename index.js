
import { NativeModules } from 'react-native';

const { RNYandexCheckoutSdk } = NativeModules;



export async function init(){
    const result = await  RNYandexCheckoutSdk.init();
    return result;
}

export async function deinit(){
    const result = await  RNYandexCheckoutSdk.deinit();
    return result;
}


export async function pay(params){
        const defaultParams = {
            // test env params
            enableTestMode:true,
            googlePayAvailable: false,
            googlePayTestEnvironment: false,
            // shop init params
            paymentMethodTypes: [ "BANK_CARD" ], // GOOGLE_PAY, SBERBANK
            shopTitle:"Shop title",
            shopDescription: "Shop description",
            amount: "10.0",
            currency: "RUB",
            clientApplicationKey: "test_NTUzNzg2ek361boKMc67WZqIy1Hm6gO4c_ApfVH-A4M",
            shopId:"553786",
            showLogo:false,
            enableGooglePay: false
        }
        const _params = Object.assign(defaultParams, params)
        const result = await  RNYandexCheckoutSdk.pay(_params);
        return result;
}
