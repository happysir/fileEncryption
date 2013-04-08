package cn.wz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MacMenuActivity extends Activity {
    /** Called when the activity is first created. */
	private int id=3;
	private Button macSetBtn;
	private Button macCheckBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mac_menu);
        
        macSetBtn=(Button) this.findViewById(R.id.macSetBtn);
        macCheckBtn=(Button) this.findViewById(R.id.macCheckBtn);
        
        macSetBtn.setOnClickListener(new macSetBtnListener());
        macCheckBtn.setOnClickListener(new macCheckBtnListener());
    }
    
    class macSetBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MacMenuActivity.this,MacSetActivity.class);
			startActivityForResult(intent,id);
		}
    	
    }
    
    class macCheckBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MacMenuActivity.this,MacCheckActivity.class);
			startActivityForResult(intent,id);
		}
    	
    }
}