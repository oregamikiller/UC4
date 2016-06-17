package com.semidream.UC4;


import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnViewChangeListener,
		OnClickListener {
	private MyScrollLayout mScrollLayout;
	private LinearLayout[] mImageViews;
	private int mViewCount;
	private int mCurSel;

	private TextView liaotian;
	private TextView faxian;
	private TextView tongxunlu;

	private TextView pz1;
	private TextView pz2;
	private TextView pz3;
	private TextView pz4;
	private TextView pz5;
	private TextView pz6;
	
	private ListView listview1;
	private ListView listview2;
	private ListView listview3;


	

	
	ListItemSingle c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		

//		AdManager.getInstance(this).init("ddcad56b56df7df6", "854ea6b908dac452", false);
//		SpotManager.getInstance(this).loadSpotAds();
//		 SpotManager.getInstance(this).setShowInterval(20);// 设置20秒的显示时间间隔
//  		SpotManager.getInstance(this).setSpotOrientation(
//  				SpotManager.ORIENTATION_PORTRAIT);
//		AdView adView = new AdView(this, AdSize.FIT_SCREEN);
//		// 获取要嵌入广告条的布局
//		LinearLayout adLayout=(LinearLayout)findViewById(R.id.adLayout);
//		// 将广告条加入到布局中
//		adLayout.addView(adView);

//		UpdateManager um = new UpdateManager(MainActivity.this);
//
//		um.checkUpdateInfo();

		init();

	}

	private void init()

	{
		liaotian = (TextView) findViewById(R.id.liaotian);
		faxian = (TextView) findViewById(R.id.faxian);
		tongxunlu = (TextView) findViewById(R.id.tongxunlu);

		listview1 = (ListView) findViewById(R.id.listView1);
		listview2 = (ListView) findViewById(R.id.listView2);
		listview3 = (ListView) findViewById(R.id.listView3);

		ListItemMultiLineAdapter ha = new ListItemMultiLineAdapter(this,
				getListItemMultiLine());
		listview1.setAdapter(ha);
		listview1.setCacheColorHint(0);
		listview1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				ListItemMultiLineAdapter.H  h=(ListItemMultiLineAdapter.H )arg1.getTag();
					Intent intent = new Intent(MainActivity.this,MyWebActivity.class);
					intent.putExtra("url", "http://d7vg.com/psngame/7028");

					startActivity(intent);
			}

		});

		ListItemSingleAdapter hc = new ListItemSingleAdapter(this, getContact());
		listview2.setAdapter(hc);
		listview2.setCacheColorHint(0);

		listview2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent intent = new Intent(MainActivity.this,
						MyWebActivity.class);
				intent.putExtra("type", "monster");
				intent.putExtra("index", arg2);
				startActivity(intent);
			}
		});


		ListItemSingleAdapter hd = new ListItemSingleAdapter(this, getContact());
		listview3.setAdapter(hd);
		listview3.setCacheColorHint(0);

		listview3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {

				Intent intent = new Intent(MainActivity.this,
						MyWebActivity.class);
				intent.putExtra("type", "monster");
				intent.putExtra("index", arg2);
				startActivity(intent);
			}
		});


		mScrollLayout = (MyScrollLayout) findViewById(R.id.ScrollLayout);

		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lllayout);

		mViewCount = mScrollLayout.getChildCount();

		mImageViews = new LinearLayout[mViewCount];

		for (int i = 0; i < mViewCount; i++)

		{

			mImageViews[i] = (LinearLayout) linearLayout.getChildAt(i);

			mImageViews[i].setEnabled(true);

			mImageViews[i].setOnClickListener(this);

			mImageViews[i].setTag(i);

		}

		mCurSel = 0;

		mImageViews[mCurSel].setEnabled(false);

		mScrollLayout.SetOnViewChangeListener(this);

	}

	private ArrayList<ListItemSingle> getContact() {
		ArrayList<ListItemSingle> hcList = new ArrayList<ListItemSingle>();
		String[] data = {"xxxx","yyyy","zzzzzzz"};

		for (String x : data) {
			ListItemSingle c = new ListItemSingle();
			c.setTxPath(R.drawable.title + "");
			c.setName(x);
			hcList.add(c);
		}
		return hcList;
	}

	private ArrayList<ListItemMultiLine> getListItemMultiLine() {
		String[] data = {"1|最后一次|获得所有奖杯",
				"2|通关！ - 探险家|在探险家模式下完成游戏",
				"3|通关！ - 简单|在探险家模式下完成游戏",
				"4|通关！ - 正常|以“正常”难度通关",
				"5|通关！ - 困难|以“困难”难度通关",
				"6|通关！ - 惨烈|以“惨烈”难度通关",
				"7|航路确定！ - 极速通关|在 6 小时以内完成游戏",
				"8|第一桶金|找到 1 个宝藏",
				"9|宝藏猎人|找到 50 个宝藏",
				"10|宝藏大师|找到所有宝藏",
				"11|文物发掘者|找到奇异的文物",
				"12|匆匆记下|找到 1 则日志条目",
				"13|博闻强识|找到全部日志条目",
				"14|记笔记|找到 1 张日志笔记",
				"15|散佚的历史|找到全部日志笔记",
				"16|猎头达人！|命中头部击败 20 名敌人",
				"17|猎头之王|命中头部击败 50 名敌人",
				"18|绳之以法|在绳索上命中敌人头部 20 次",
				"19|先偷袭，再说别的|使用隐身攻击击败 5 名敌人",
				"20|安息吧|使用隐身攻击击败 30 名敌人",
				"21|我好像听到有动静|完成 30 次垂直隐身击倒",
				"22|奔行的射手|使用随手射击或盲射击败 50 名敌人",
				"23|两栖战士|一次击败 10 名敌人，且交替使用枪械和徒手攻击",
				"24|无人可挡！|在不被击败的情况下连续击败 100 名敌人",
				"25|神枪手|以至少 70% 的命中率完成游戏",
				"26|全能王|在 15 秒内依次使用手枪、机枪与手雷击败敌人",
				"27|荒冢幽灵|在不击倒敌人、也不被敌人发现的情况下通过苏格兰墓地战",
				"28|手太滑|迫使 10 名敌人的手雷脱手",
				"29|大爆炸|一次爆炸击败 4 名敌人（任何爆炸道具或武器都可以）",
				"30|60 秒内击败 10 人：华湖榴弹枪|在 60 秒内使用华湖榴弹发射器击败 10 名敌人",
				"31|抓稳了！|在绳索上摧毁 10 辆载具",
				"32|大获全胜|在 15 秒内，依次使用隐身攻击、近战攻击、命中头部与爆炸物击败敌人",
				"33|以卵击石|只用近战攻击击败 5 名重甲型敌人",
				"34|和平解决|在章节“孤立无援”之后直到藏宝库之前不杀死任何人",
				"35|僚机员|和同伴施展组合技 10 次",
				"36|攀岩专家|挂在边缘拉下 20 名敌人",
				"37|不按剧本来|击败 1000 名敌人",
				"38|禁止投喂|和市场里的狐猴玩游戏，让它偷走你的苹果",
				"39|一不小心就全用了|使用过游戏中每一种武器",
				"40|天下无冢|撞倒马达加斯加所有石冢",
				"41|从这里能看得到我家！|爬到城市钟塔塔顶",
				"42|试炼与苦难|在 10 步以内完成苏格兰的第一个试炼",
				"43|最高分！|在复古电视游戏中击败最高分",
				"44|伶牙俐齿|听到游戏中全部支线对话",
				"45|马可·波罗回来了！|在沉船附近的海里玩耍",
				"46|怯场|在城市追逐战开始前，保持原地站立不动 30 秒（舞台演示失败场面）",
				"47|油门踩到底！|和伊莲娜驾驶吉普逃跑，不撞到任何敌人",
				"48|遛海豚|让三只海豚跟着小船",
				"49|宝刀未老|用玩具枪击中阁楼内所有目标",
				"50|迷人一击|为苏利拍一张照片",
				"51|击剑大师|在与雷夫用剑对决时完美格挡他的进攻，不被他砍伤或刺伤",
				"52|继续冒险|完成《秘境探险》多人游戏教程",
				"53|游戏开始了|完成 5 场多人游戏对战",
				"54|火线试炼|在多人游戏“中等”难度中完成全部试炼",
				"55|好哥们！|在多人游戏中重生 10 名搭档",
				"56|医疗兵！|在多人游戏中复活 10 名盟友"
		};

		ArrayList<ListItemMultiLine> hhList = new ArrayList<ListItemMultiLine>();

		for (String x : data) {
			ListItemMultiLine h1 = new ListItemMultiLine();
			h1.setTxPath(getImageId(this,"t" + x.split("\\|")[0])+ "");
			h1.setName1(x.split("\\|")[1]);
			h1.setLastContent(x.split("\\|")[2]);
			hhList.add(h1);
		}

		
		return hhList;
	}

	private void setCurPoint(int index)

	{

		if (index < 0 || index > mViewCount - 1 || mCurSel == index) {

			return;

		}

		mImageViews[mCurSel].setEnabled(true);

		mImageViews[index].setEnabled(false);

		mCurSel = index;

		if (index == 0) {

			liaotian.setTextColor(0xff228B22);

			faxian.setTextColor(Color.BLACK);

			tongxunlu.setTextColor(Color.BLACK);

		} else if (index == 1) {

			liaotian.setTextColor(Color.BLACK);

			faxian.setTextColor(0xff228B22);

			tongxunlu.setTextColor(Color.BLACK);

		} else {

			liaotian.setTextColor(Color.BLACK);

			faxian.setTextColor(Color.BLACK);

			tongxunlu.setTextColor(0xff228B22);

		}

	}




	public static int getImageId(Context context, String imageName) {

		System.out.println("zzz" + context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName()));
		return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());

	}
	@Override
	public void OnViewChange(int view) {
		// TODO Auto-generated method stub
		setCurPoint(view);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int pos = (Integer) (v.getTag());
		setCurPoint(pos);
		mScrollLayout.snapToScreen(pos);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_MENU)) {

			return true;

		}
		return super.onKeyDown(keyCode, event);
	}

	

	
}
