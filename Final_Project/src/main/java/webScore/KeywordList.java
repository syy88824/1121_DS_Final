package webScore;

import java.util.ArrayList;

public class KeywordList {
	
	Keyword k0 = new Keyword("Netflix", 5);			
	Keyword k1 = new Keyword("Disney+", 5);
	Keyword k2 = new Keyword("影集", 5);
	Keyword k3 = new Keyword("劇", 5);
	Keyword k4 = new Keyword("紀錄片", 5);
	Keyword k5 = new Keyword("韓劇", 5);
	Keyword k6 = new Keyword("美劇", 5);
	Keyword k7 = new Keyword("日劇", 5);
	Keyword k8 = new Keyword("台劇", 5);
	Keyword k9 = new Keyword("陸劇", 5);
	Keyword k10 = new Keyword("電影", 5);
	Keyword k11 = new Keyword("片單", 3);
	Keyword k12 = new Keyword("追劇", 3);
	Keyword k13 = new Keyword("QIY", 3);
	Keyword k14 = new Keyword("必看", 1);
	Keyword k15 = new Keyword("映画", 5);
	Keyword k16 = new Keyword("愛奇藝", 3);
	Keyword k17 = new Keyword("HBO", 5);
	Keyword k18 = new Keyword("영화", 5);
	Keyword k19 = new Keyword("男主角", 2);
	Keyword k20 = new Keyword("女主角", 2);
	Keyword k21 = new Keyword("연극", 5);
	Keyword k22 = new Keyword("시리즈", 5);
	Keyword k23 = new Keyword("シリーズ", 5);
	Keyword k24 = new Keyword("動漫", 5);
	Keyword k25 = new Keyword("番", 5);
	Keyword k26 = new Keyword("animation", 5);
	Keyword k27 = new Keyword("アニメ", 5);
	Keyword k28 = new Keyword("theater", -5);
	Keyword k29 = new Keyword("movie", 5);
	Keyword k30 = new Keyword("series", 5);
	Keyword k31 = new Keyword("binge-watching", 2);
	//webScore.Keyword k22 = new webScore.Keyword("女子圖艦", 20);
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
		key.add(k22);
		key.add(k23);
		key.add(k24);
		key.add(k25);
		key.add(k26);
		key.add(k27);
		key.add(k28);
		key.add(k29);
		key.add(k30);
		key.add(k31);
		return key;
	}
	
}
