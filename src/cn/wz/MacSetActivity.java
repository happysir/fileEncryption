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

public class MacSetActivity extends Activity {
    /** Called when the activity is first created. */
	private EditText macSetSrcFile;
	private EditText macSetMacFile;
	private RadioGroup macSetRG;
	private RadioButton macSetR1;
	private RadioButton macSetR2;
	private Button macSetMenuBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mac_set);
        
        macSetSrcFile=(EditText) this.findViewById(R.id.macSetSrcFile);
        macSetMacFile=(EditText) this.findViewById(R.id.macSetMacFile);
        macSetRG=(RadioGroup) this.findViewById(R.id.macSetRG);
        macSetR1=(RadioButton) this.findViewById(R.id.macSetR1);
        macSetR2=(RadioButton) this.findViewById(R.id.macSetR2);
        macSetMenuBtn=(Button) this.findViewById(R.id.macSetMenuBtn);
        macSetMenuBtn.setOnClickListener(new macSetMenuBtnListener());
    }
    
    class macSetMenuBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(check.isNull(macSetSrcFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"请输入源文件名", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(!fileOperation.exist(config.path()+macSetSrcFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"源文件不存在", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(check.isNull(macSetMacFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"请输入鉴别码文件名", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(fileOperation.exist(config.path()+macSetMacFile.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"鉴别码文件已存在", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			
			int alg;
			if(macSetRG.getCheckedRadioButtonId()==macSetR1.getId()){
				alg=0;
			}
			else if(macSetRG.getCheckedRadioButtonId()==macSetR2.getId()){
				alg=1;
			}
			else{
				alg=0;
			}
			
			String s=fileOperation.readString(config.path()+macSetSrcFile.getText().toString());
			if(s==null){
	        	Toast toast = Toast.makeText(getApplicationContext(),
	        			"文件不存在: "+macSetSrcFile.getText().toString(), Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
	        }
			
			byte[] mac;
			switch(alg){
			case 0:
				mac=FileDigest.digestMD5(s);
				break;
			case 1:
				mac=FileDigest.digestSHA1(s);
				break;
			default:
				mac=FileDigest.digestMD5(s);
			}
			
			if(mac==null){
				Toast toast = Toast.makeText(getApplicationContext(),
	        			"鉴别码生成出错", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
			}
			
			int fowbr=fileOperation.writeBytes(config.path()+macSetMacFile.getText().toString(), mac);
			if(fowbr!=0){
				Toast toast = Toast.makeText(getApplicationContext(), "文件写入出错", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
		        return;
			}
			Toast toast = Toast.makeText(getApplicationContext(), "鉴别码已生成", Toast.LENGTH_LONG);
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.show();
			
		}
    	
    }
}