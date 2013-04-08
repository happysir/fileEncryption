package cn.wz;

import security.SymEncrypt;
import security.check;
import security.config;
import security.fileOperation;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DecAlgSelActivity extends Activity {
	private RadioGroup delAlgSelRG;
	private RadioButton decAlgSelR1;
	private RadioButton decAlgSelR2;
	private RadioButton decAlgSelR3;
	private EditText decAlgKey;
	private EditText delOutText;
	private Button decAlgSelBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dec_alg_sel);
        delAlgSelRG=(RadioGroup) this.findViewById(R.id.delAlgSelRG);
        decAlgSelR1=(RadioButton) this.findViewById(R.id.decAlgSelR1);
        decAlgSelR2=(RadioButton) this.findViewById(R.id.decAlgSelR2);
        decAlgSelR3=(RadioButton) this.findViewById(R.id.decAlgSelR3);  
        decAlgKey=(EditText) this.findViewById(R.id.decAlgKey);
        delOutText=(EditText) this.findViewById(R.id.delOutText);
        decAlgSelBtn=(Button) this.findViewById(R.id.decAlgSelBtn);
        decAlgSelBtn.setOnClickListener(new decAlgSelBtnListener());   
        
    }
    
    class decAlgSelBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(check.isNull(delOutText.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"����������ļ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(fileOperation.exist(config.path()+delOutText.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"����ļ��Ѵ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(check.isNull(decAlgKey.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"��������Կ", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			Intent intent=getIntent(); 
	        Bundle getb=intent.getBundleExtra("FILESEL");
	        if(getb==null){
	        	Toast toast = Toast.makeText(getApplicationContext(), "error: evalid Bundle", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
	        }
	        String selFile=getb.getString("selectFile");
	        String action=getb.getString("action");
	        if(selFile==null){
	        	Toast toast = Toast.makeText(getApplicationContext(), "error: no selFile", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
	        }
	        if(action==null||!action.equals("SelFileBtn")){
	        	Toast toast = Toast.makeText(getApplicationContext(), "error: evalid action: "+action, Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
	        }
	        
	        byte[] inputBytes=fileOperation.readBytes(config.path()+selFile);
	        if(inputBytes==null){
	        	Toast toast = Toast.makeText(getApplicationContext(), "�����ļ���ȡ����", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
	        }

			String alg;
			if(delAlgSelRG.getCheckedRadioButtonId()==decAlgSelR1.getId()){
				alg="DES";
			}
			else if(delAlgSelRG.getCheckedRadioButtonId()==decAlgSelR2.getId()){
				alg="DESede";
			}
			else if(delAlgSelRG.getCheckedRadioButtonId()==decAlgSelR3.getId()){
				alg="AES";
			}
			else{
				alg="DES";
			}
			String outputStr=SymEncrypt.decrypt(inputBytes, decAlgKey.getText().toString(), alg);
			int fowws=fileOperation.writeString(config.path()+delOutText.getText().toString(), outputStr,0);
			if(fowws!=0){
				Toast toast = Toast.makeText(getApplicationContext(), "�ļ�д�����", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
		        return;
			}
			
			Toast toast = Toast.makeText(getApplicationContext(), "���������", Toast.LENGTH_LONG);
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.show();
	        
		}
    	
    }
}