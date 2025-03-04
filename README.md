## 個人資料介接服務驗證系統
>資訊系統在提供個人資訊的處理過程中，確保資料傳輸的機密性及完整性很重要；因此本系統以MyData平台之資訊傳輸之驗證服務為範例，提供符合個人資料傳輸安全處理的程序及步驟，包含個人資料的請求方式、安全權杖的驗證及個人資料的封裝，支援訊息及檔案傳輸格式的傳輸功能驗證。透過本系統，可協助改善技術人員因不熟悉處理個人資訊訊息流程及錯誤處理機制，而在開發測試過程中耗時費力的情境，加速將機關資訊系統個人資料提給例如MyData等平台的創新服務應用。
## 1.使用技術
Spring Boot
## 2.安裝指南
> 請確保您的環境中安裝有 Java 22 及 Maven 3 以上版本
執行步驟
```bat
> git clone https://github.com/TsubasaRush/mydata-notify-and-authentication-testing-module.git
> cd mydata-notify-and-authentication-testing-module
> mvn clean install
> java -jar target/mydata-notify-and-authentication-testing-module-0.0.1-SNAPSHOT.jar
```
將程式建置為jar包，並且於本機或需求的主機上執行該jar
服務將預設啟動於8080 port，若有其他需求可於application.properties設定
可模擬
- 1.mydata 請求呼叫
- 2.mydata資料驗證
> http://localhost:8080/connect/introspect?token=token
- 3.mydata資料交換
> http://localhost:8080/connect/userinfo
並列印相關內容供查驗

## 3.使用情境範例
- 1.mydata 請求呼叫
> 模擬mydata請求呼叫指定url
mydata呼叫請求利用header進行資料交換
其中的客製化header參數設定可於mydataHeaderInfo.properties進行設定，範例使用INQ_YR,INQ_DATE
可於mydataTest.properties進行GET或POST模擬請求的開關設定，同時也能設定呼叫指定的API URL及header中所固定帶入之驗證token
預設60秒進行一次呼叫，並且會於console列印回傳內容。
- 2.mydata資料驗證
> 使用程式組成HTTP請求呼叫http://localhost:8080/connect/introspect?token=token
驗證之token會帶在url參數中，此API主要為模擬設計之DP-API收到Mydata請求後所需進行的token驗證
驗證使用之token即為1.中所使用的驗證token
若正常會回傳200 OK，若異常則會回傳401 未經授權異常。
- 3.mydata資料交換
> 使用程式組成HTTP請求呼叫http://localhost:8080/connect/userinfo
模擬mydata流程中token驗證結束後DP-API透過呼叫使用者資料交換API以取得使用者資訊的行為
會先進行token驗證
驗證使用之token即為1.中所使用的驗證token，若異常則會回傳401 未經授權異常。
若無異常則會回傳可於mydataUserInfo.properties設定之User資料物件以利DP-API利用使用者資訊進行進一步的資料查詢。




