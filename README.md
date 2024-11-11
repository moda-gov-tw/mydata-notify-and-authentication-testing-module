## MyData個人資料管理服務通知驗測模組
>本模組提供一個模擬Mydata呼叫請求及使用者資料交換的服務，以供測試時Mydata呼叫的模擬及提供一個介面模擬Mydata使用者參數交換服務，以達到數發部Mydata呼叫及使用者資訊交換API的測試模擬。

## 1.使用技術
Spring Boot
Maven 
## 2.安裝指南
> 請確保您的環境中安裝有 Java 22 及 Maven 3 以上版本
執行步驟
```bat
> git clone https://gitlab.iisigroup.com/ps150/g-p231301/modapc/mydata-notify-and-authentication-testing-module.git
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
- 2.mydata資料驗證
> 使用程式組成HTTP請求呼叫http://localhost:8080/connect/introspect?token=token
- 3.mydata資料交換
> 使用程式組成HTTP請求呼叫http://localhost:8080/connect/userinfo




