## 各個package功能 (Java)
## main
放Browser.jsp的Server (其他再看看)
### wordCounter (Lab3)
算出每一個class中的關鍵字數量
### nodeCountScore (Lab4)
算出每一個Web或subWeb的關鍵字得分
### webScore (Lab6)
用tree算出每一個web的網頁分數(要包含其所有的subWebs)
### webCrawler (Lab8)
將瀏覽器中所有的網頁資料取出
### quickSort (Lab9)
將每一個網頁的分數依大小排列好(要由大到小排好)
## 前端頁面 (.jsp)
> 路徑：Project/src/main/webapp (可在webapp中再建資料夾)
> 
> 目前研究到的是css檔一定要放在jsp裡面(無法外嵌)，javascript還不確定
>
> 可以先在vscode把html/css/javascript寫好以後再複製到java的jsp檔
### Browser.jsp
Element：
1. 搜索bar (html tag = input) 
2. 點擊搜尋鍵 (html tag = button)
```sh
button type="submit"
```
的話 按enter就可以執行該button的event
### SearchResult.jsp
Element：
1. 搜索bar (讓user仍可於SearchResult頁面中重新搜尋)
2. 搜索結果(劇名)
> 看能不能點劇名之後連結弄到那部劇的維基或其他頁面
3. (如果還有時間的話)該劇的照片 (看要當劇名後面的背景還是放在劇名旁邊)

## 跟老師的code比起來可以改的東西
* 將quickSort換成time complexity更小的演算法 (quickSort O=nlog(n))
* 僅擷取網頁中一部份的資料(不是放上整個網頁的網址，而是只放上劇名)
* (如果還有餘力) 抓取該劇的照片並呈現於網頁中
