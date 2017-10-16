package Fragment;

import com.example.main1.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebAppViewFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container_web, Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.fragment_view_webapp, container_web,false);
		WebView webView = (WebView) root.findViewById(R.id.view_Webapp);

//		String uri_web = getResources().getString(R.string.url_web);
		webView.loadUrl(getResources().getString(R.string.url_web));
		
//		webView.loadUrl("http://192.168.76.1:51000");
		
		return root;
	}

}
