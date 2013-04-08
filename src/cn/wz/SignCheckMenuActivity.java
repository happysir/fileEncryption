package cn.wz;

import security.RsaVerify;
import security.check;
import security.config;
import security.fileOperation;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignCheckMenuActivity extends Activity {
    /** Called when the activity is first created. */
	private EditText signCheckSrcFile;
	private EditText signCheckSignFile;
	private EditText signCheckKeyFile;
	private Button signCheckMenuBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_check_menu);
        
        signCheckSrcFile=(EditText) this.findViewById(R.id.signCheckSrcFile);
        signCheckSignFile=(EditText) this.findViewById(R.id.signCheckSignFile);
        signCheckKeyFile=(EditText) this.findViewById(R.id.signCheckKeyFile);
        signCheckMenuBtn=(Button) this.findViewById(R.id.signCheckMenuBtn);
        signCheckMenuBtn.setOnClickListener(new signCheckMenuBtnListener());
    }
    
    class signCheckMenuBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(check.isNull(signCheckSrcFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"������Դ�ļ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(!fileOperation.exist(config.path()+signCheckSrcFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"Դ�ļ�������", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(check.isNull(signCheckSignFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"������ǩ���ļ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(!fileOperation.exist(config.path()+signCheckSignFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"ǩ���ļ�������", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(check.isNull(signCheckKeyFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"�����빫Կ�ļ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(!fileOperation.exist(config.path()+signCheckKeyFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"��Կ�ļ�������", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			String s=fileOperation.readString(config.path()+signCheckSrcFile.getText().toString());
			if(s==null){
				Toast toast = Toast.makeText(getApplicationContext(),
						"�ļ�������: "+signCheckSrcFile.getText().toString(), Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
			}
			boolean checkr=RsaVerify.checkSign(s, 
					config.path()+signCheckKeyFile.getText().toString(), 
					config.path()+signCheckSignFile.getText().toString());
			if(!checkr){
				Toast toast = Toast.makeText(getApplicationContext(),"ǩ������ȷ", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			Toast toast = Toast.makeText(getApplicationContext(),"ǩ����ȷ", Toast.LENGTH_LONG);
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.show();
			return;
		}
		
    	
    }
}