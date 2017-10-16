package AsyncTask;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.EntityEnclosingRequestWrapper;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import Adapter.ShoppingAdapter;
import Fragment.MainFragment;
import Fragment.RecommendFragment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.Toast;


/*
 * 新建一个DataTask类继承自AsyncTask类
 * 使有ListView的内容调用
 * 实现方法复用，节省代码量
 */
public class DataTask extends AsyncTask<Void, Void, Void> {
	
	PullToRefreshListView lv_base_detail;
	Activity activity;
	int timer;

	/*
	 * 重写构造方法
	 * 创建activity和listview用于实现复用和Toast
	 */
	public DataTask(Activity activity,PullToRefreshListView lv_base_detail,int timer) {
		this.activity = activity;
		this.lv_base_detail = lv_base_detail;
		this.timer = timer;
	}

	@SuppressWarnings("finally")
	@Override
	protected Void doInBackground(Void... params) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			return null;
		}
	}

	protected void onPostExecute(Void result) {
		if(lv_base_detail.isHeaderShown()){
//			tv_recommend_list_content.clear();
//			tv_recommend_list_title.clear();
//			connServ.ConnectServers();
//			init_list_recommend();
//			Toast.makeText(getActivity(), "已更新", Toast.LENGTH_SHORT).show();
//			ma_recommend_list_title = new RecommendAdapter(tv_recommend_list_title);
//
//			Toast.makeText(getActivity(), "已刷新", Toast.LENGTH_SHORT).show();
//			//				lv_recommend_detail.setAdapter(ma_recommend_list_title);
//			tv_recommend_list_title.add("ccc");
			Toast.makeText(activity, "刷新完成", Toast.LENGTH_SHORT).show();
			timer = 0;
			lv_base_detail.onRefreshComplete();
			super.onPostExecute(result);
		}else{
			
			Toast.makeText(activity, "加载完成", Toast.LENGTH_SHORT).show();
			lv_base_detail.onRefreshComplete();//同样是为了。。秒后停止
			super.onPostExecute(result);
		}
	}



//上拉加载的实现失败
//			lv_recommend_detail.setOnRefreshListener(new OnRefreshListener2() {
//
//				public void onPullDownToRefresh(PullToRefreshBase refreshView) {
//					// TODO Auto-generated method stub
//					
//				}
//				public void onPullUpToRefresh(PullToRefreshBase refreshView){
//					
//				}
//			});


}
