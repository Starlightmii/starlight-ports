package com.oplus.systemui.notification.row.click;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Trace;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.bluetooth.qsdialog.DeviceItem$;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.multiuser.MultiUserManager;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationClickEffectControllerImpl extends NotificationClickEffectController {
    public static final Companion Companion = new Companion();
    public static final String TAG = "NotificationClickEffect";
    public static final String TALKBACK = "enabled_accessibility_services";
    public static final String TALKBACKSERVER = "TalkBackService";
    private final Context context;
    private final ContentObserver mTalkBackObserver;
    public boolean mTalkBackState;
    private final IMultiUserListener multiUserListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationClickEffectControllerImpl(Context context) {
        this.context = context;
        final Handler handler = new Handler();
        ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.notification.row.click.NotificationClickEffectControllerImpl$mTalkBackObserver$1
            public final void onChange(boolean z, Collection collection, int i, int i2) {
                super.onChange(z, collection, i, i2);
                this.this$0.initTalkBackState(i2);
            }
        };
        this.mTalkBackObserver = contentObserver;
        NotificationClickEffectControllerImpl$multiUserListener$1 notificationClickEffectControllerImpl$multiUserListener$1 = new NotificationClickEffectControllerImpl$multiUserListener$1();
        notificationClickEffectControllerImpl$multiUserListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.multiUserListener = notificationClickEffectControllerImpl$multiUserListener$1;
        initTalkBackState(0);
        OplusNotificationLogger.logNotificationClickEffect(TAG, "initSettingObserver");
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(TALKBACK), false, contentObserver, -1);
        MultiUserManager.Companion.getInstance().addListener(notificationClickEffectControllerImpl$multiUserListener$1);
    }

    public final boolean getTalkBackState() {
        OplusNotificationLogger.logNotificationClickEffect(TAG, " get talkBack state = " + this.mTalkBackState);
        return this.mTalkBackState;
    }

    public final void initTalkBackState(int i) {
        String stringForUser = Settings.Secure.getStringForUser(this.context.getContentResolver(), TALKBACK, i);
        boolean z = false;
        if (!TextUtils.isEmpty(stringForUser)) {
            stringForUser.getClass();
            if (StringsKt__StringsKt.contains(stringForUser, TALKBACKSERVER, false)) {
                z = true;
            }
        }
        this.mTalkBackState = z;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("REGION_LOG_TAG-->talkBackStatTemp = ", stringForUser, "talkBack state = ", z, " , userId = ");
        sbM.append(i);
        OplusNotificationLogger.logNotificationClickEffect(TAG, sbM.toString());
    }

    @Override // com.oplus.systemui.notification.row.click.NotificationClickEffectController
    public void startClickAnimation(ExpandableNotificationRow expandableNotificationRow, boolean z, boolean z2, String str, Runnable runnable) {
        View.OnClickListener clickerListener;
        if (expandableNotificationRow == null || expandableNotificationRow.getEntry() == null) {
            OplusNotificationLogger.logNotificationClickEffect(TAG, "ERROR!!!--> row is null or entry is null!!");
            return;
        }
        String encryptionKey = expandableNotificationRow.getEntry().getEx().getEncryptionKey();
        boolean zIsAttachedToWindow = expandableNotificationRow.isAttachedToWindow();
        boolean hasHandleDownEventAnim = expandableNotificationRow.mRowEx.getHasHandleDownEventAnim();
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("clickRow = ", encryptionKey, ", isRevert = ", z, ", triggerClick = ");
        DeviceItem$.ExternalSyntheticOutline0.m(", click reason = ", str, ", custEndAction = ", sbM, z2);
        sbM.append(runnable);
        sbM.append(",  row.isAttachedToWindow = ");
        sbM.append(zIsAttachedToWindow);
        sbM.append(", row.isHasHandleDownEventAnim = ");
        sbM.append(hasHandleDownEventAnim);
        OplusNotificationLogger.logNotificationClickEffect(TAG, sbM.toString());
        if (expandableNotificationRow.isAttachedToWindow()) {
            if (!z || expandableNotificationRow.mRowEx.getHasHandleDownEventAnim()) {
                Trace.traceBegin(8L, "NotificationClick#EffectAnimation");
                if (z) {
                    expandableNotificationRow.mRowEx.setHasHandleDownEventAnim(false);
                    if (z2 && (clickerListener = expandableNotificationRow.getClickerListener()) != null) {
                        clickerListener.onClick(expandableNotificationRow);
                    }
                    setEffectingRow(null);
                } else {
                    setEffectingRow(expandableNotificationRow);
                    expandableNotificationRow.mRowEx.setHasHandleDownEventAnim(true);
                }
                if (runnable != null) {
                    runnable.run();
                }
                Trace.traceEnd(8L);
            }
        }
    }
}
