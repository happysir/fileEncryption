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

public class EncAlgSelActivity extends Activity {
    /** Called when the activity is first created. */
	private RadioGroup encAlgSelRG;
	private RadioButton encAlgSelR1;
	private RadioButton encAlgSelR2;
	private RadioButton encAlgSelR3;
	private EditText encAlgKey;
	private EditText encOutText;
	private Button encAlgSelBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enc_alg_sel);
        encAlgSelRG=(RadioGroup) this.findViewById(R.id.encAlgSelRG);
        encAlgSelR1=(RadioButton) this.findViewById(R.id.encAlgSelR1);
        encAlgSelR2=(RadioButton) this.findViewById(R.id.encAlgSelR2);
        encAlgSelR3=(RadioButton) this.findViewById(R.id.encAlgSelR3);  
        encAlgKey=(EditText) this.findViewById(R.id.encAlgKey);
        encOutText=(EditText) this.findViewById(R.id.encOutText);
        encAlgSelBtn=(Button) this.findViewById(R.id.encAlgSelBtn);
        encAlgSelBtn.setOnClickListener(new encAlgSelBtnListener());
        

    }
    
    class encAlgSelBtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(check.isNull(encOutText.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"����������ļ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(fileOperation.exist(config.path()+encOutText.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"����ļ��Ѵ���", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			if(check.isNull(encAlgKey.getText().toString())){
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
	        
	        String inputStr=fileOperation.readString(config.path()+selFile);
	        if(inputStr==null){
	        	Toast toast = Toast.makeText(getApplicationContext(), "�ļ�������: "+selFile, Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
	        }

			String alg;
			if(encAlgSelRG.getCheckedRadioButtonId()==encAlgSelR1.getId()){
				alg="DES";
			}
			else if(encAlgSelRG.getCheckedRadioButtonId()==encAlgSelR2.getId()){
				alg="DESede";
			}
			else if(encAlgSelRG.getCheckedRadioButtonId()==encAlgSelR3.getId()){
				alg="AES";
			}
			else{
				alg="DES";
			}
			byte[] outputBytes=SymEncrypt.encrypt(inputStr, encAlgKey.getText().toString(), alg);
			int fowbr=fileOperation.writeBytes(config.path()+encOutText.getText().toString(), outputBytes);
			if(fowbr!=0){
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