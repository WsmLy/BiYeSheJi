package Servers;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

@SuppressWarnings("deprecation")
public class ConnServ {
	
	public static final int SHOW_RESPONSE = 0;

	@SuppressWarnings("unused")
	public void ConnectServers(){
		new Thread(
			new Runnable() {
				public void run() {
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet("http://192.168.191.1:8080");
					try {
						HttpResponse httpResponse = httpClient.execute(httpGet);
						//第四步：检查相应状态是否正常，检查状态码值为200是正常
						if (httpResponse.getStatusLine().getStatusCode()==200) {
							//第五步：从相应对象中取出数据，放到entity中
							HttpEntity entity = httpResponse.getEntity();
							String response = EntityUtils.toString(entity,"utf-8");//将entity中的数据转换成字符串
							//在子线程中将message对象发送出去
							Message message = new Message();
							message.what = SHOW_RESPONSE;
							message.obj = response.toString();
							handler.sendMessage(message);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
				
			}
			
		).start();
	}
	
	//新建handler对象，接收message并更新textview控件内容
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@SuppressLint("HandlerLeak")
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch(msg.what){
			case SHOW_RESPONSE:
				@SuppressWarnings("unused")
				String response = (String) msg.obj;
				//textView_response.setText(response);
				break;
			default:
				break;
			}
		}
	};
	
}
