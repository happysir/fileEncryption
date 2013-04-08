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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignCreatKeyActivity extends Activity {
    /** Called when the activity is first created. */
	private RadioGroup signCKSizeRG;
	private RadioButton signCKSizeR1;
	private RadioButton signCKSizeR2;
	private RadioButton signCKSizeR3;
	private EditText signCrKeyPRK;
	private EditText signCrKeyPUK;
	private Button signCreatKeyMenuBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_creat_key);
        
        signCKSizeRG=(RadioGroup) this.findViewById(R.id.signCKSizeRG);
        signCKSizeR1=(RadioButton) this.findViewById(R.id.signCKSizeR1);
        signCKSizeR2=(RadioButton) this.findViewById(R.id.signCKSizeR2);
        signCKSizeR3=(RadioButton) this.findViewById(R.id.signCKSizeR3);  
        signCrKeyPRK=(EditText) this.findViewById(R.id.signCrKeyPRK);
        signCrKeyPUK=(EditText) this.findViewById(R.id.signCrKeyPUK);
        signCreatKeyMenuBtn=(Button) this.findViewById(R.id.signCreatKeyMenuBtn);
        signCreatKeyMenuBtn.setOnClickListener(new signCreatKeyMenuBtnListener());
    }
    
    class signCreatKeyMenuBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(check.isNull(signCrKeyPRK.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"请输入私钥文件名", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(fileOperation.exist(config.path()+signCrKeyPRK.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"私钥文件已存在", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(check.isNull(signCrKeyPUK.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"请输入公钥文件名", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(fileOperation.exist(config.path()+signCrKeyPUK.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"公钥文件已存在", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			
			int size;
			if(signCKSizeRG.getCheckedRadioButtonId()==signCKSizeR1.getId()){
				size=512;
			}
			else if(signCKSizeRG.getCheckedRadioButtonId()==signCKSizeR2.getId()){
				size=768;
			}
			else if(signCKSizeRG.getCheckedRadioButtonId()==signCKSizeR3.getId()){
				size=1024;
			}
			else{
				size=512;
			}
	        
			boolean isCreatKey=RsaVerify.creatKey(size, 
					config.path()+signCrKeyPRK.getText().toString(), 
					config.path()+signCrKeyPUK.getText().toString());
	        if(!isCreatKey){
				Toast toast = Toast.makeText(getApplicationContext(), "生成密钥出错", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
		        return;
			}
			Toast toast = Toast.makeText(getApplicationContext(), "密钥已生成", Toast.LENGTH_LONG);
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.show();
	        
		}		
    	
    }
}