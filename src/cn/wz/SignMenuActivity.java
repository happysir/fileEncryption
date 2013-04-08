package cn.wz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SignMenuActivity extends Activity {
    /** Called when the activity is first created. */
	private int id=2;
	private Button signSetBtn;
	private Button signCheckBtn;
	private Button signCreatKeyBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_menu);

        signSetBtn=(Button) this.findViewById(R.id.signSetBtn);
        signCheckBtn=(Button) this.findViewById(R.id.signCheckBtn);
        signCreatKeyBtn=(Button) this.findViewById(R.id.signCreatKeyBtn);
        
        signSetBtn.setOnClickListener(new signSetBtnListener());
        signCheckBtn.setOnClickListener(new signCheckBtnListener());
        signCreatKeyBtn.setOnClickListener(new signCreatKeyBtnListener());
    }
    
    class signSetBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(SignMenuActivity.this,SignSetMenuActivity.class);
			startActivityForResult(intent,id);
		}
    	
    }
    
    class signCheckBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(SignMenuActivity.this,SignCheckMenuActivity.class);
			startActivityForResult(intent,id);
		}
    	
    }

    class signCreatKeyBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(SignMenuActivity.this,SignCreatKeyActivity.class);
			startActivityForResult(intent,id);
		}
	
    }
    
}