package Fragment;

import com.example.main1.LoginActivity;
import com.example.main1.R;
import com.example.main1.RegisterActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalCenterUnLoginedFragment extends Fragment{

	View personalcenterunlogined_root_view;
	ImageView login;
	TextView tv2,tv3;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		personalcenterunlogined_root_view = inflater.inflate(R.layout.fragment_personal_center_unlogined, container,false);
		
		login = (ImageView) personalcenterunlogined_root_view.findViewById(R.id.personalcenterunlogined_iv_headform);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
			}
		});
		
		tv2 = (TextView) personalcenterunlogined_root_view.findViewById(R.id.tv2);
		tv3 = (TextView) personalcenterunlogined_root_view.findViewById(R.id.tv3);
		
		tv2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(getActivity(),LoginActivity.class);
				startActivity(intent1);
			}
		});

		tv3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(getActivity(),RegisterActivity.class);
				startActivity(intent1);
			}
		});
		
		return personalcenterunlogined_root_view;
	}
}
