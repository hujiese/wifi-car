package scut.hujie.wificar;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import my.wificar.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MyVideo extends Activity {
	private Button TakePhotos;
	private Button ViewPhotos;

	URL videoUrl;
	private Button BtnForward, BtnBackward, BtnLeft, BtnRight, BtnStop;
	public static String CameraIp;
	public static String CtrlIp;
	public static String CtrlPort;
	MySurfaceView r;
	private Socket socket;
	OutputStream socketWriter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐去标题（应用的名字必须要写在setContentView之前，否则会有异常）
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.myvideo);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		r = (MySurfaceView) findViewById(R.id.mySurfaceViewVideo);
		TakePhotos = (Button) findViewById(R.id.TakePhoto);
		ViewPhotos = (Button) findViewById(R.id.ViewPhoto);

		BtnForward = (Button) findViewById(R.id.button_forward);
		BtnBackward = (Button) findViewById(R.id.button_backward);
		BtnLeft = (Button) findViewById(R.id.button_left);
		BtnRight = (Button) findViewById(R.id.button_right);
		BtnStop = (Button) findViewById(R.id.button_stop);

		Intent intent = getIntent();
		// 从Intent当中根据key取得value
		CameraIp = intent.getStringExtra("CameraIp");
		CtrlIp = intent.getStringExtra("ControlUrl");
		CtrlPort = intent.getStringExtra("Port");
		Log.d("wifirobot", "control is :++++" + CtrlIp);
		Log.d("wifirobot", "CtrlPort is :++++" + CtrlPort);
		r.GetCameraIP(CameraIp);
		InitSocket();
		BtnForward.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					socketWriter.write(new byte[] { '1' });
					socketWriter.flush();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		BtnBackward.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					socketWriter.write(new byte[] { '2' });
					socketWriter.flush();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		BtnRight.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					socketWriter.write(new byte[] { '3' });
					socketWriter.flush();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		BtnLeft.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					socketWriter.write(new byte[] { '4' });
					socketWriter.flush();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		BtnStop.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					socketWriter.write(new byte[] { '5' });
					socketWriter.flush();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		TakePhotos.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (null != Constant.handler) {
					Message message = new Message();
					message.what = 1;
					Constant.handler.sendMessage(message);
				}
			}

		});

		ViewPhotos.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MyVideo.this, BgPictureShowActivity.class);
				// 通过Intent对象启动另外一个Activity
				MyVideo.this.startActivity(intent);

			}

		});

	}

	public void InitSocket() {

		try {
			socket = new Socket(InetAddress.getByName(CtrlIp),
					Integer.parseInt(CtrlPort));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socketWriter = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "获取网络流失败！", Toast.LENGTH_LONG).show();
		}

	}

	public void onDestroy() {
		super.onDestroy();

	}

	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			if ((System.currentTimeMillis() - exitTime) > 2500) // System.currentTimeMillis()无论何时调用，肯定大于2500
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
