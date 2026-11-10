package com.oplus.systemui.util;

import android.R;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.LogWindowBase;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import kotlin.text.StringsKt__StringBuilderJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LogWindowUtils extends LogWindowBase {
    private StringBuilder buffLog;
    private final Context context;
    private final ContentObserver debugModeObserver;
    public boolean inited;
    private LinearLayout root;
    private TextView tv;

    public LogWindowUtils(Context context) {
        super(context);
        this.context = context;
        this.buffLog = new StringBuilder("");
        ContentObserver contentObserver = new debugModeObserver.1(this, new Handler());
        this.debugModeObserver = contentObserver;
        LogWindowBase.Companion companion = LogWindowBase.Companion;
        companion.setDebugMode(Settings.System.getIntForUser(context.getContentResolver(), companion.getDEBUG_MODE_SETTING_KEY(), 0, -2) == 1);
        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor(companion.getDEBUG_MODE_SETTING_KEY()), contentObserver, -1);
    }

    public void buffLog(String str) {
        if (LogWindowBase.getDebugAble()) {
            initLogWm();
            synchronized (this.buffLog) {
                this.buffLog.append("\n---" + str);
            }
        }
    }

    public final void initLogWm() {
        synchronized (LogWindowUtils.class) {
            try {
                if (!this.inited) {
                    this.inited = true;
                    WindowManager windowManager = (WindowManager) this.context.getSystemService("window");
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.type = 2017;
                    layoutParams.width = -1;
                    layoutParams.height = -1;
                    layoutParams.gravity = 8388659;
                    layoutParams.flags = 65848;
                    layoutParams.privateFlags = 16;
                    layoutParams.format = -3;
                    layoutParams.setTitle("Log");
                    View viewInflate = LayoutInflater.from(this.context).inflate(2131559072, (ViewGroup) null, false);
                    LinearLayout linearLayout = viewInflate instanceof LinearLayout ? (LinearLayout) viewInflate : null;
                    this.root = linearLayout;
                    if (linearLayout != null) {
                        linearLayout.setAlpha(LogWindowBase.Companion.getWIN_ALPHA());
                    }
                    LinearLayout linearLayout2 = this.root;
                    if (linearLayout2 != null) {
                        linearLayout2.setBackgroundColor(this.context.getResources().getColor(R.color.holo_green_dark, this.context.getTheme()));
                    }
                    windowManager.addView(this.root, layoutParams);
                }
                LinearLayout linearLayout3 = this.root;
                this.tv = linearLayout3 != null ? (TextView) linearLayout3.findViewById(2131365797) : null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void showLogText(String str) {
        if (LogWindowBase.getDebugAble()) {
            initLogWm();
            synchronized (this.buffLog) {
                StringsKt__StringBuilderJVMKt.clear(this.buffLog);
                TextView textView = this.tv;
                if (textView != null) {
                    textView.setText(str);
                }
            }
        }
    }
}
