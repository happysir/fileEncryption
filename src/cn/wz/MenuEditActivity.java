package cn.wz;

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
import android.widget.Toast;

public class MenuEditActivity  extends Activity{
	/** Called when the activity is first created. */
	private Button menuEditSave;
	private EditText menuEditText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_edit);
        
        menuEditSave=(Button) this.findViewById(R.id.menuEditSave);
        menuEditText=(EditText) this.findViewById(R.id.menuEditText);
        
        menuEditSave.setOnClickListener(new menuEditSaveListener());
        
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
        if(!fileOperation.exist(config.path()+selFile)){
        	if(fileOperation.writeString(config.path()+selFile, "", 0)!=0){
        		Toast toast = Toast.makeText(getApplicationContext(), "error: create file failed", Toast.LENGTH_LONG);
    	        toast.setGravity(Gravity.CENTER, 0, 0);
    	        toast.show();
            	return;
        	}
		}
        String tmp=fileOperation.readString(config.path()+selFile);
        if(tmp==null){
        	Toast toast = Toast.makeText(getApplicationContext(), "文件不存在: "+selFile, Toast.LENGTH_LONG);
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.show();
        	return;
        }
        menuEditText.setText(tmp);
    }
    
    class menuEditSaveListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
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
	        String writestr=menuEditText.getText().toString();
	        if(fileOperation.writeString(config.path()+selFile, writestr, 1)!=0){
        		Toast toast = Toast.makeText(getApplicationContext(), "error: write file failed", Toast.LENGTH_LONG);
    	        toast.setGravity(Gravity.CENTER, 0, 0);
    	        toast.show();
        	}
	        Toast toast = Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_LONG);
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.show();
		}
    	
    }
}
