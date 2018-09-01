package scut.hujie.wificar;

import my.wificar.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyMainFrm extends Activity {

	private EditText CameraIP;// 全地址
	private EditText ControlIP;// IP号
	private EditText Port;// 端口号
	private Button Button_go;// 确认按钮
	private String videoUrl;// 获取CameraIP中内容
	private String controlUrl;// 获取ControlIP中内容
	private String port;// 获取Port中内容
	public static String CameraIp;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * 这个类是第一个界面，在界面中可以输入无线摄像头的获取视频数据流地址 edIP：视频地址文本框 Button_go 启动按钮
		 */

		setContentView(R.layout.mymainfrm);

		CameraIP = (EditText) findViewById(R.id.editIP);
		ControlIP = (EditText) findViewById(R.id.ip);
		Port = (EditText) findViewById(R.id.port);

		Button_go = (Button) findViewById(R.id.button_go);

		videoUrl = CameraIP.getText().toString();
		controlUrl = ControlIP.getText().toString();
		port = Port.getText().toString();

		Button_go.requestFocusFromTouch();

		Button_go.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// 生成一个Intent对象
				Intent intent = new Intent();
				// 在Intent对象当中添加一个键值对
				intent.putExtra("CameraIp", videoUrl);
				intent.putExtra("ControlUrl", controlUrl);
				intent.putExtra("Port", port);

				intent.putExtra("Is_Scale", true);
				// 设置Intent对象要启动的Activity
				intent.setClass(MyMainFrm.this, MyVideo.class);
				// 通过Intent对象启动另外一个Activity
				startActivity(intent);
				finish();
				System.exit(0);
			}
		});

	}

	/*
	 * 以下为按一下返回键后，提示“再按一次退出程序”
	 */
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			if ((System.currentTimeMillis() - exitTime) > 2000) // System.currentTimeMillis()无论何时调用，肯定大于2000
			{
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
