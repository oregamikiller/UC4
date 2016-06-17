package com.semidream.UC4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 





import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
 
public class UpdateManager {
    private Context context;
    private final static String CHECKURL = "http://semidream.com/appVersionCheck";
    private static String downloadUrl = "";
    private Dialog noticeDialog;
    private ProgressBar mProgress;
    private boolean interceptFlag = false;
    private Dialog downloadDialog;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;
    private int progress;
    private static final String savePath = Environment
            .getExternalStorageDirectory() + "/UC4/update/";
    private static final String saveFileName = savePath + "UC4.apk";
 
    public UpdateManager(Context context) {
        this.context = context;
    }
 
    /**
     */
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case DOWN_UPDATE:
                mProgress.setProgress(progress);
                break;
            case DOWN_OVER:
                if (downloadDialog != null)
                    downloadDialog.dismiss();
                installApk();
                break;
            default:
                break;
            }
        };
    };
 
    /**
     * �汾���½ӿ�
     */
    public void checkUpdateInfo() {
        // �����������������������Ҫ�����򵯳���ʾ�Ի���
        if (isNeedUpdate()) {
            // ��ʾ��ʾ�Ի���
            showNoticeDialog();
        }
    }
 
    /**
     * ��鵱ǰ�汾�Ƿ���Ҫ����
     * 
     * @return
     */
    private boolean isNeedUpdate() {
        String versionName = getVersionName(context);
        boolean isNeedUpdate = testVersion(versionName);
        return isNeedUpdate;
    }
 
    /**
     *
     * @param context
     * @return
     */
    private String getVersionName(Context context) {
        String versionName = "";
        try {
        	 PackageManager packageManager = context.getPackageManager();
             PackageInfo packInfo = packageManager.getPackageInfo("com.semidream.UC4",0);
             versionName = packInfo.versionName;  
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
 
    /**
     * @param versionName
     * @return
     */
    private boolean testVersion(String version) {
        boolean isNeedUpdate = false;
        String url = CHECKURL + "?version=" + version+"&appID="+"UC4"+"&platform=android";
        String result = get(url);
        try {
            JSONObject jsonObj = new JSONObject(result);
            int code = jsonObj.getInt("code");
            if (code == 0) {
                JSONObject valueObj = jsonObj.getJSONObject("value");
                String lastVersion = valueObj.getString("version");
                downloadUrl = valueObj.getString("url");
                if (version.equals(lastVersion)) {
                    isNeedUpdate = false;
                } else {
                    isNeedUpdate = true;
                }
            } else {
                isNeedUpdate = false;
            }
 
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isNeedUpdate;
    }
 
    /**
     *
     * @param url
     * @return
     */
    private String get(String url) {
    	
    
        BufferedReader reader = null;
        StringBuffer sb = null;
        String result = "";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                reader = new BufferedReader(new InputStreamReader(response
                        .getEntity().getContent()));
                sb = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_SERVICE_UNAVAILABLE) {
                result = "1";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (IOException e) {
                result = "2";
                e.printStackTrace();
            }
        }
        if (sb != null) {
            result = sb.toString();
        }
        return result;
    }
 
    /**
     */
    private void showNoticeDialog() {
        AlertDialog.Builder builder = new Builder(context);
        builder.setTitle("3");
        builder.setMessage("4");
        builder.setPositiveButton("5", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
               showDownloadDialog();
            }
        });
        builder.setNegativeButton("6", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        noticeDialog = builder.create();
        noticeDialog.show();
        WindowManager.LayoutParams lp = noticeDialog.getWindow()
                .getAttributes();
        lp.width = 400;
        lp.height = 500;
        noticeDialog.getWindow().setAttributes(lp);
    }
 
    /**
     * ���ؽ�ȶԻ���
     */
    private void showDownloadDialog() {
        AlertDialog.Builder builder = new Builder(context);
        builder.setTitle("7");
        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.progress);
        builder.setView(v);
        builder.setNegativeButton("8", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                interceptFlag = true;
            }
        });
        downloadDialog = builder.create();
        downloadDialog.show();
        downloadApk();
    }
 
    /**
     */
    private void downloadApk() {
        Thread downLoadThread = new Thread(downApkRunnable);
        downLoadThread.start();
    }
 
    /**
     */
    private Runnable downApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL("Http://semidream.com"+downloadUrl);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();
                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String apkFile = saveFileName;
                File ApkFile = new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);
 
                int count = 0;
                byte buf[] = new byte[1024];
                do {
                    int numread = is.read(buf);
                    count += numread;
                    progress = (int) (((float) count / length) * 100);
                    handler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        handler.sendEmptyMessage(DOWN_OVER);
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);// ���ȡ���ֹͣ����.
                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
 
    /**
     */
    private void installApk() {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        context.startActivity(i);
    }
}