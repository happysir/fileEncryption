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

public class SignSetMenuActivity extends Activity {
    /** Called when the activity is first created. */
	private EditText signSetSrcFile;
	private EditText signSetTarFile;
	private EditText signSetKeyFile;
	private Button signSetMenuBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_set_menu);
        signSetSrcFile=(EditText) this.findViewById(R.id.signSetSrcFile);
        signSetTarFile=(EditText) this.findViewById(R.id.signSetTarFile);
        signSetKeyFile=(EditText) this.findViewById(R.id.signSetKeyFile);
        signSetMenuBtn=(Button) this.findViewById(R.id.signSetMenuBtn);
        signSetMenuBtn.setOnClickListener(new signSetMenuBtnListener());
    }
    
    class signSetMenuBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(check.isNull(signSetSrcFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"������Դ�ļ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(!fileOperation.exist(config.path()+signSetSrcFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"Դ�ļ�������", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(check.isNull(signSetTarFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"������Ŀ���ļ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(fileOperation.exist(config.path()+signSetTarFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"Ŀ���ļ��Ѵ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(check.isNull(signSetKeyFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"������˽Կ�ļ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(!fileOperation.exist(config.path()+signSetKeyFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"˽Կ�ļ�������", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			String s=fileOperation.readString(config.path()+signSetSrcFile.getText().toString());
			if(s==null){
				Toast toast = Toast.makeText(getApplicationContext(),
						"�ļ�������: "+signSetSrcFile.getText().toString(), Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
			}
			boolean signr=RsaVerify.sign(s, 
					config.path()+signSetKeyFile.getText().toString(), 
					config.path()+signSetTarFile.getText().toString());
			if(!signr){
				Toast toast = Toast.makeText(getApplicationContext(),"ǩ��ʧ��", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			Toast toast = Toast.makeText(getApplicationContext(),"ǩ���ɹ�", Toast.LENGTH_LONG);
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.show();
			return;
		}
    	
    }
}