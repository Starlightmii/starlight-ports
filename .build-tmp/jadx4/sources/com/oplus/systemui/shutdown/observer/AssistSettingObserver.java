package com.oplus.systemui.shutdown.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.android.keyguard.OplusLockIconView$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AssistSettingObserver extends ContentObserver {
    public static final Companion Companion = new Companion();
    private static final String KEY_ASSIST_START = "op_assist_started";
    private static final String QUICK_TURN_ON_VOICE_ASSISTANT = "quick_turn_on_voice_assistant";
    private static final String TAG = "AssistSettingObserver";
    private final Context context;
    public final Uri mQuickTurnOnVoiceUri;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AssistSettingObserver(Context context) {
        super(new Handler());
        this.context = context;
        this.mQuickTurnOnVoiceUri = Settings.System.getUriFor(QUICK_TURN_ON_VOICE_ASSISTANT);
    }

    public final Context getContext() {
        return this.context;
    }

    public final void observe() {
        this.context.getContentResolver().registerContentObserver(this.mQuickTurnOnVoiceUri, false, this, -1);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_GLOBALACTIONS)) {
            Log.d("SystemUi--GlobalActions", "AssistSettingObserver-->AssistManager init SettingsObserver");
        }
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        Uri uri2 = this.mQuickTurnOnVoiceUri;
        if (uri2 == null || !Intrinsics.areEqual(uri2, uri)) {
            return;
        }
        boolean z2 = Settings.System.getInt(this.context.getContentResolver(), QUICK_TURN_ON_VOICE_ASSISTANT, -1) == 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_GLOBALACTIONS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("isVoiceAssistant = ", "AssistSettingObserver-->", "SystemUi--GlobalActions", z2);
        }
        if (z2) {
            return;
        }
        Settings.Secure.putIntForUser(this.context.getContentResolver(), KEY_ASSIST_START, 0, -2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_GLOBALACTIONS)) {
            Log.d("SystemUi--GlobalActions", "AssistSettingObserver-->AssistManager op_assist_started set 0");
        }
    }
}
