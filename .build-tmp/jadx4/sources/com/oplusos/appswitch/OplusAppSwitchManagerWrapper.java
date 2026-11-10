package com.oplusos.appswitch;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.android.wm.shell.ShellBackgroundThread;
import com.android.wm.shell.ShellMainThread;
import com.oplus.app.OplusAppEnterInfo;
import com.oplus.app.OplusAppExitInfo;
import com.oplus.app.OplusAppSwitchConfig;
import com.oplus.app.OplusAppSwitchManager;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusAppSwitchManagerWrapper {
    private static final int MSG_APP_SWITCH_DELAY = 7000;
    private static final String TAG = "OplusAppSwitchManagerWrapper";
    private static final ArrayList<String> sObservePkgs = new ArrayList<>(Arrays.asList("com.tencent.mm", "com.tencent.mobileqq", "com.android.mms", "com.ss.android.ugc.aweme", "com.kuaishou.nebula", "com.smile.gifmaker", "com.coloros.filemanager", "com.heytap.browser", "com.ss.android.ugc.aweme.lite", "com.dragon.read", "com.tencent.mtt", "com.chaoxing.mobile", "com.tencent.qqmusic", "com.tencent.tmgp.pubgmhd", "com.alibaba.android.rimet", "com.sina.weibo", "com.coloros.note", "com.xuexiaoyi.xxy", "com.xingin.xhs", "com.tencent.qqlive", "com.taobao.taobao", "com.ss.android.article.news", "com.baidu.searchbox", "com.ss.android.ugc.live", "com.qiyi.video", "com.coloros.calculator", "com.tencent.news", "com.ss.android.article.lite", "com.xunmeng.pinduoduo"));
    private final OplusAppSwitchManager mAppSwitchManager;
    private final OplusAppSwitchConfig mConfig;
    private final CopyOnWriteArrayList<OplusAppSwitchManager.OnAppSwitchObserver> mListeners;
    private final OplusAppSwitchManager.OnAppSwitchObserver mObserverWrapper;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    class LazyHolder {
        private static final OplusAppSwitchManagerWrapper INSTANCE = new OplusAppSwitchManagerWrapper(0);

        private LazyHolder() {
        }
    }

    private OplusAppSwitchManagerWrapper() {
        this.mListeners = new CopyOnWriteArrayList<>();
        this.mAppSwitchManager = OplusAppSwitchManager.getInstance();
        OplusAppSwitchConfig oplusAppSwitchConfig = new OplusAppSwitchConfig();
        this.mConfig = oplusAppSwitchConfig;
        oplusAppSwitchConfig.addAppConfig(2, sObservePkgs);
        this.mObserverWrapper = new OplusAppSwitchManager.OnAppSwitchObserver() { // from class: com.oplusos.appswitch.OplusAppSwitchManagerWrapper.1
            public void onActivityEnter(OplusAppEnterInfo oplusAppEnterInfo) {
                Iterator it = OplusAppSwitchManagerWrapper.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((OplusAppSwitchManager.OnAppSwitchObserver) it.next()).onActivityEnter(oplusAppEnterInfo);
                }
            }

            public void onActivityExit(OplusAppExitInfo oplusAppExitInfo) {
                Iterator it = OplusAppSwitchManagerWrapper.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((OplusAppSwitchManager.OnAppSwitchObserver) it.next()).onActivityExit(oplusAppExitInfo);
                }
            }

            public void onAppEnter(OplusAppEnterInfo oplusAppEnterInfo) {
                Iterator it = OplusAppSwitchManagerWrapper.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((OplusAppSwitchManager.OnAppSwitchObserver) it.next()).onAppEnter(oplusAppEnterInfo);
                }
            }

            public void onAppExit(OplusAppExitInfo oplusAppExitInfo) {
                Iterator it = OplusAppSwitchManagerWrapper.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((OplusAppSwitchManager.OnAppSwitchObserver) it.next()).onAppExit(oplusAppExitInfo);
                }
            }
        };
    }

    public static OplusAppSwitchManagerWrapper getInstance() {
        return LazyHolder.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$registerAppSwitchObserver$0(Context context) {
        this.mAppSwitchManager.unregisterAppSwitchObserver(context, this.mObserverWrapper);
        Log.e(TAG, "registerAppSwitchObserver result = " + this.mAppSwitchManager.registerAppSwitchObserver(context, this.mObserverWrapper, this.mConfig));
    }

    private synchronized void registerAppSwitchObserver(Context context) {
        try {
            if (!this.mAppSwitchManager.registerAppSwitchObserver(context, this.mObserverWrapper, this.mConfig)) {
                Log.e(TAG, "registerAppSwitchObserver failed");
                Handler handler = ShellBackgroundThread.getHandler();
                if (handler == null) {
                    handler = ShellMainThread.getHandler();
                }
                if (handler != null) {
                    OplusAppSwitchManagerWrapper$$ExternalSyntheticLambda0 oplusAppSwitchManagerWrapper$$ExternalSyntheticLambda0 = new OplusAppSwitchManagerWrapper$$ExternalSyntheticLambda0();
                    oplusAppSwitchManagerWrapper$$ExternalSyntheticLambda0.f$0 = this;
                    oplusAppSwitchManagerWrapper$$ExternalSyntheticLambda0.f$1 = context;
                    VarHandle.storeStoreFence();
                    handler.postDelayed(oplusAppSwitchManagerWrapper$$ExternalSyntheticLambda0, 7000L);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private synchronized void unRegisterAppSwitchObserver(Context context) {
        try {
            this.mAppSwitchManager.unregisterAppSwitchObserver(context, this.mObserverWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "unRegisterAppSwitchObserver failed");
        }
    }

    public synchronized void addObserver(Context context, OplusAppSwitchManager.OnAppSwitchObserver onAppSwitchObserver) {
        if (onAppSwitchObserver == null) {
            return;
        }
        try {
            if (this.mListeners.size() == 0) {
                registerAppSwitchObserver(context);
            }
            this.mListeners.add(onAppSwitchObserver);
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void removeObserver(Context context, OplusAppSwitchManager.OnAppSwitchObserver onAppSwitchObserver) {
        if (onAppSwitchObserver == null) {
            return;
        }
        this.mListeners.remove(onAppSwitchObserver);
        if (this.mListeners.size() == 0) {
            unRegisterAppSwitchObserver(context);
        }
    }

    public /* synthetic */ OplusAppSwitchManagerWrapper(int i) {
        this();
    }
}
