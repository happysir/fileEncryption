package cn.wz;

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
import android.widget.Toast;

public class FileSelectActivity extends Activity {
    /** Called when the activity is first created. */
	private int id=1;
	private Button SelFileBtn;
	private EditText SelFileText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_select);
        SelFileBtn=(Button) this.findViewById(R.id.SelFileBtn);
        SelFileText=(EditText) this.findViewById(R.id.SelFileText);
        SelFileBtn.setOnClickListener(new SelFileBtnListener());
    }
    
    class SelFileBtnListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			if(check.isNull(SelFileText.getText().toString())){
				Toast toast = Toast.makeText(getApplicationContext(),"请输入一个文件名", Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
				return;
			}
			Bundle tmp=getIntent().getBundleExtra("MAINMENU");
			if(tmp==null) return;
	        String action=tmp.getString("action");
	        if(action==null) return;
	        Intent intent;
	        if(action.equals("menuEncryptBtn")){
	        	if(!fileOperation.exist(config.path()+SelFileText.getText().toString())){
					Toast toast = Toast.makeText(getApplicationContext(),"文件不存在", Toast.LENGTH_LONG);
			        toast.setGravity(Gravity.CENTER, 0, 0);
			        toast.show();
					return;
				}
	        	intent=new Intent(FileSelectActivity.this,EncAlgSelActivity.class);
	        }
	        else if(action.equals("menuDecryptBtn")){
	        	if(!fileOperation.exist(config.path()+SelFileText.getText().toString())){
					Toast toast = Toast.makeText(getApplicationContext(),"文件不存在", Toast.LENGTH_LONG);
			        toast.setGravity(Gravity.CENTER, 0, 0);
			        toast.show();
					return;
				}
	        	intent=new Intent(FileSelectActivity.this,DecAlgSelActivity.class);
	        }
	        else if(action.equals("menuEditBtn")){
	        	intent=new Intent(FileSelectActivity.this,MenuEditActivity.class);
	        }
	        else if(action.equals("menuDelBtn")){
	        	if(!fileOperation.exist(config.path()+SelFileText.getText().toString())){
					Toast toast = Toast.makeText(getApplicationContext(),"文件不存在", Toast.LENGTH_LONG);
			        toast.setGravity(Gravity.CENTER, 0, 0);
			        toast.show();
					return;
				}
	        	java.io.File delfile=new java.io.File(config.path()+SelFileText.getText().toString());
	        	boolean delr=delfile.delete();
	        	if(delr){
	        		Toast toast = Toast.makeText(getApplicationContext(),"删除成功", Toast.LENGTH_LONG);
			        toast.setGravity(Gravity.CENTER, 0, 0);
			        toast.show();
	        	}
	        	else{
	        		Toast toast = Toast.makeText(getApplicationContext(),"删除失败", Toast.LENGTH_LONG);
			        toast.setGravity(Gravity.CENTER, 0, 0);
			        toast.show();
	        	}
	        	return;
	        }
	        else{
	        	Toast toast = Toast.makeText(getApplicationContext(), "error: evalid action: "+action, Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.CENTER, 0, 0);
		        toast.show();
	        	return;
	        }
			Bundle b=new Bundle();
			b.putString("action", "SelFileBtn");
			b.putString("selectFile", SelFileText.getText().toString());
			intent.putExtra("FILESEL", b);
			startActivityForResult(intent,id);
		}
    }
}