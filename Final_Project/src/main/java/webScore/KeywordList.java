package webScore;

import java.util.ArrayList;

import nodeCountScore.Keyword;

public class KeywordList {
	
	webScore.Keyword k0 = new webScore.Keyword("Netflix", 5);			
	webScore.Keyword k1 = new webScore.Keyword("Disney+", 5);
	webScore.Keyword k2 = new webScore.Keyword("影集", 5);
	webScore.Keyword k3 = new webScore.Keyword("劇", 5);
	webScore.Keyword k4 = new webScore.Keyword("紀錄片", 5);
	webScore.Keyword k5 = new webScore.Keyword("韓劇", 5);
	webScore.Keyword k6 = new webScore.Keyword("美劇", 5);
	webScore.Keyword k7 = new webScore.Keyword("日劇", 5);
	webScore.Keyword k8 = new webScore.Keyword("台劇", 5);
	webScore.Keyword k9 = new webScore.Keyword("陸劇", 5);
	webScore.Keyword k10 = new webScore.Keyword("電影", 5);
	webScore.Keyword k11 = new webScore.Keyword("片單", 4);
	webScore.Keyword k12 = new webScore.Keyword("追劇", 4);
	webScore.Keyword k13 = new webScore.Keyword("線上看", 4);
	webScore.Keyword k14 = new webScore.Keyword("必看", 4);
	webScore.Keyword k15 = new webScore.Keyword("推薦", 4);
	webScore.Keyword k16 = new webScore.Keyword("愛奇藝", 3);
	webScore.Keyword k17 = new webScore.Keyword("劇情", 3);
	webScore.Keyword k18 = new webScore.Keyword("排行榜", 2);
	webScore.Keyword k19 = new webScore.Keyword("男主角", 2);
	webScore.Keyword k20 = new webScore.Keyword("女主角", 2);
	webScore.Keyword k21 = new webScore.Keyword("得獎", 2);
	public ArrayList<webScore.Keyword> key;
	
	public ArrayList<webScore.Keyword> getList() {
		key = new ArrayList<>();
		key.add(k0);
		key.add(k1);
		key.add(k2);
		key.add(k3);
		key.add(k4);
		key.add(k5);
		key.add(k6);
		key.add(k7);
		key.add(k8);
		key.add(k9);
		key.add(k10);
		key.add(k11);
		key.add(k12);
		key.add(k13);
		key.add(k14);
		key.add(k15);
		key.add(k16);
		key.add(k17);
		key.add(k18);
		key.add(k19);
		key.add(k20);
		key.add(k21);
		return key;
	}
	
}
