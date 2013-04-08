package cn.wz;

import security.config;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private int id=0;
	private Button menuEncryptBtn;
	private Button menuDecryptBtn;
	private Button menuSignBtn;
	private Button menuMacBtn;
	private Button menuEditBtn;
	private Button menuDelBtn;
	//private
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*Intent intent=getIntent(); 
        Bundle b=intent.getBundleExtra("AA");
        String s=b.getString("aaa");
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        menuEncryptBtn=(Button) this.findViewById(R.id.menuEncryptBtn);
        menuDecryptBtn=(Button) this.findViewById(R.id.menuDecryptBtn);
        menuSignBtn=(Button) this.findViewById(R.id.menuSignBtn);
        menuMacBtn=(Button) this.findViewById(R.id.menuMacBtn);
        menuEditBtn=(Button) this.findViewById(R.id.menuEditBtn);
        menuDelBtn=(Button) this.findViewById(R.id.menuDelBtn);
        
        menuEncryptBtn.setOnClickListener(new menuEncryptBtnListener());
        menuDecryptBtn.setOnClickListener(new menuDecryptBtnListener());
        menuSignBtn.setOnClickListener(new menuSignBtnListener());
        menuMacBtn.setOnClickListener(new menuMacBtnListener());
        menuEditBtn.setOnClickListener(new menuEditBtnListener());
        menuDelBtn.setOnClickListener(new menuDelBtnListener());
        java.io.File path = new java.io.File(config.path());
        if(!path.exists()){
        	path.mkdirs();
        }
    }
    
    class menuDelBtnListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,FileSelectActivity.class);
			Bundle b=new Bundle();
			b.putString("action", "menuDelBtn");
			intent.putExtra("MAINMENU", b);
			startActivityForResult(intent,id);
		}
    }
    
    class menuEditBtnListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,FileSelectActivity.class);
			Bundle b=new Bundle();
			b.putString("action", "menuEditBtn");
			intent.putExtra("MAINMENU", b);
			startActivityForResult(intent,id);
		}
    }
    
    class menuEncryptBtnListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,FileSelectActivity.class);
			Bundle b=new Bundle();
			b.putString("action", "menuEncryptBtn");
			intent.putExtra("MAINMENU", b);
			startActivityForResult(intent,id);
		}
    }
    
    class menuDecryptBtnListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,FileSelectActivity.class);
			Bundle b=new Bundle();
			b.putString("action", "menuDecryptBtn");
			intent.putExtra("MAINMENU", b);
			startActivityForResult(intent,id);
		}
    }
    
    class menuSignBtnListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,SignMenuActivity.class);
			Bundle b=new Bundle();
			b.putString("action", "menuSignBtn");
			intent.putExtra("MAINMENU", b);
			startActivityForResult(intent,id);
		}
    }
    
    class menuMacBtnListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,MacMenuActivity.class);
			Bundle b=new Bundle();
			b.putString("action", "menuMacBtn");
			intent.putExtra("MAINMENU", b);
			startActivityForResult(intent,id);
		}
    }
    
    
    
}