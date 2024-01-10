## 各個package功能 (Java)
### main
放兩個jsp檔的Server (其他再看看)
### wordCounter (Lab3)
算出每一個class中的關鍵字數量
### nodeCountScore (Lab4)
算出每一個Web或subWeb的關鍵字得分
### webScore (Lab6)
用tree算出每一個web的網頁分數(要包含其所有的subWebs)
### webCrawler (Lab8)
將瀏覽器中所有的網頁資料及其子網頁取出
### quickSort (Lab9)
將每一個網頁的分數依大小排列好(要由大到小排好 並取出前7高的網頁)
## 前端頁面 (.jsp)
> 路徑：Project/src/main/webapp (可在webapp中再建資料夾)
### index.jsp
Element：
1. 搜索bar (html tag = input) 
2. 點擊搜尋鍵 (html tag = button)
```sh
button type="submit"
```
的話 按enter就可以執行該button的event
### index2.jsp
Element：
1. 搜索bar (讓user仍可於SearchResult頁面中重新搜尋)
2. 結果網址
