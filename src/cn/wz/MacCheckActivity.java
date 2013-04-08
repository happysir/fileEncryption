package cn.wz;

import security.FileDigest;
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

public class MacCheckActivity extends Activity {
    /** Called when the activity is first created. */
	private EditText macCheckSrcFile;
	private EditText macCheckMacFile;
	private RadioGroup macCheckRG;
	private RadioButton macCheckR1;
	private RadioButton macCheckR2;
	private Button macCheckMenuBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mac_check);
        
        macCheckSrcFile=(EditText) this.findViewById(R.id.macCheckSrcFile);
        macCheckMacFile=(EditText) this.findViewById(R.id.macCheckMacFile);
        macCheckRG=(RadioGroup) this.findViewById(R.id.macCheckRG);
        macCheckR1=(RadioButton) this.findViewById(R.id.macCheckR1);
        macCheckR2=(RadioButton) this.findViewById(R.id.macCheckR2);
        macCheckMenuBtn=(Button) this.findViewById(R.id.macCheckMenuBtn);
        macCheckMenuBtn.setOnClickListener(new macCheckMenuBtnListener());
    }
    
    class macCheckMenuBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(check.isNull(macCheckSrcFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"请输入源文件名", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(!fileOperation.exist(config.path()+macCheckSrcFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"源文件不存在", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(check.isNull(macCheckMacFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"请输入鉴别码文件名", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(!fileOperation.exist(config.path()+macCheckMacFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"鉴别码文件不存在", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			
			int alg;
			if(macCheckRG.getCheckedRadioButtonId()==macCheckR1.getId()){
				alg=0;
			}
			else if(macCheckRG.getCheckedRadioButtonId()==macCheckR2.getId()){
				alg=1;
			}
			else{
				alg=0;
			}
			
			String s=fileOperation.readString(config.path()+macCheckSrcFile.getText().toString());
			if(s==null){
	        	Toast toast = Toast.makeText(getApplicationContext(),
	        			"文件不存在: "+macCheckSrcFile.getText().toString(), Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
	        }
			
			byte[] mac=fileOperation.readBytes(config.path()+macCheckMacFile.getText().toString());
			if(mac==null){
	        	Toast toast = Toast.makeText(getApplicationContext(),
	        			"文件读取错误: "+macCheckMacFile.getText().toString(), Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
	        }
			
			boolean isEqual=FileDigest.isEqual(mac, s, alg);
			
			if(isEqual){
				Toast toast = Toast.makeText(getApplicationContext(), "鉴别码正确", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
			else{
				Toast toast = Toast.makeText(getApplicationContext(), "鉴别码不正确", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
			
		}
    	
    }
}