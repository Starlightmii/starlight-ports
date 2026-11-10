package com.oplus.systemui.notification.envelope;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.collection.FloatList$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import com.android.systemui.Dependency;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.oplus.systemui.notification.envelope.settings.EnvelopeAutoOpenObserver;
import com.oplus.systemui.notification.envelope.settings.EnvelopeEnableObserver;
import com.oplus.systemui.notification.envelope.settings.EnvelopeSoundObserver;
import com.oplus.systemui.notification.envelope.view.EnvelopeViewManager;
import com.oplus.systemui.provider.ProviderUtil;
import com.oplus.systemui.qs.tiles.EnvelopeTile;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class EnvelopeHelper {
    public static final String[] LOCAL_LIST = {"com.android.mms", "com.tencent.mm", "com.tencent.mobileqq", "com.sina.weibo", "com.twitter.android", "org.telegram.messenger", "com.whatsapp", "com.facebook.orca", "com.viber.voip", "com.imo.android.imoim", "jp.naver.line.android"};
    public final HashMap mChildWeChatEnvelopeList;
    public final HashMap mCloneWeChatEnvelopeList;
    public final Context mContext;
    public int mEnvelopeAssistantStatus;
    public final AnonymousClass3 mEnvelopeAutoOpenObserver;
    public final AnonymousClass5 mEnvelopeCallBack;
    public final AnonymousClass2 mEnvelopeEnableObserver;
    public final Object mEnvelopeNotificationLock = new Object();
    public final AnonymousClass1 mEnvelopeReceiver;
    public final AnonymousClass4 mEnvelopeSoundObserver;
    public final CopyOnWriteArraySet mEnvelopeStateChangeCallbackList;
    public final EnvelopeViewManager mEnvelopeViewManager;
    public int mIsAutoOpen;
    Lazy mPrivacyHelper;
    public final HashMap mQqEnvelopeList;
    public int mSoundEnable;
    EnvelopeHandler mWeChatEnvelopeHandler;
    public final HashMap mWeChatEnvelopeList;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.envelope.EnvelopeHelper$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 {
        public /* synthetic */ EnvelopeHelper this$0;
    }

    /* JADX INFO: renamed from: -$$Nest$menqueueEnvelopeNotification, reason: not valid java name */
    public static void m54$$Nest$menqueueEnvelopeNotification(EnvelopeHelper envelopeHelper, int i, Notification notification, String str, int i2, int i3, int i4) {
        UserEnvelopeRecord envelopeRecord;
        if (i == 0 || notification == null) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "enqueueEnvelopeNotification: category or notification is null");
            return;
        }
        String string = notification.extras.getCharSequence("android.text").toString();
        String envelopeInfo = WeChatEnvelopeFieldList.getInstance().getEnvelopeInfo("envelope_content_tag");
        if (envelopeInfo != null && string.indexOf(envelopeInfo) == -1) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "notification content = ".concat(string));
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "enqueueEnvelopeNotification: intercept envelope");
            return;
        }
        StringBuilder sb = new StringBuilder("enqueueEnvelopeNotification: category = ");
        sb.append(i);
        sb.append(" notification = ");
        sb.append(notification);
        sb.append(" relatedKey = ");
        ConstraintWidget$.ExternalSyntheticOutline0.m(i2, str, " uid = ", " lockScreenVisibility = ", sb);
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(i3, LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", sb);
        synchronized (envelopeHelper.mEnvelopeNotificationLock) {
            try {
                String stringExtra = notification.contentIntent.getIntent().getStringExtra(WeChatEnvelopeFieldList.getInstance().getEnvelopeInfo("envelope_user_field"));
                if (stringExtra == null) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "enqueueEnvelopeNotification: wechatUserID is null");
                    return;
                }
                boolean zContains = stringExtra.contains(WeChatEnvelopeFieldList.getInstance().getEnvelopeInfo("envelope_group_tag"));
                int i5 = 1;
                if (i == 1) {
                    if (UserHandle.getUserId(i2) == 0) {
                        envelopeRecord = getEnvelopeRecord(envelopeHelper.mWeChatEnvelopeList, stringExtra, str, i3);
                    } else {
                        envelopeRecord = OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(UserHandle.getUserId(i2))) ? getEnvelopeRecord(envelopeHelper.mCloneWeChatEnvelopeList, stringExtra, str, i3) : getEnvelopeRecord(envelopeHelper.mChildWeChatEnvelopeList, stringExtra, str, i3);
                    }
                    UserEnvelopeRecord userEnvelopeRecord = envelopeRecord;
                    envelopeHelper.mWeChatEnvelopeHandler.handleEnvelopeNotification(envelopeHelper.mContext, UserEnvelopeRecord.-$$Nest$mgetNotificationID(userEnvelopeRecord), userEnvelopeRecord.getCount(), notification, i, i2, stringExtra, envelopeHelper.mIsAutoOpen, zContains, envelopeHelper.mEnvelopeCallBack, i4);
                }
                AbsSettingsValueProxy.setSecureIntValue(envelopeHelper.mContext, "key_envelope_count", AbsSettingsValueProxy.getSecureIntValue(envelopeHelper.mContext, "key_envelope_count", 0) + 1);
                Context context = envelopeHelper.mContext;
                if (AbsSettingsValueProxy.getSecureIntValue(context, "envelope_count_changed", 0) != 0) {
                    i5 = 0;
                }
                AbsSettingsValueProxy.setSecureIntValue(context, "envelope_count_changed", i5);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public EnvelopeHelper(Context context, BroadcastDispatcher broadcastDispatcher, Lazy lazy) {
        SharedPreferences sharedPreferences;
        int i;
        new ArrayList(Arrays.asList(LOCAL_LIST));
        this.mWeChatEnvelopeList = new HashMap();
        this.mCloneWeChatEnvelopeList = new HashMap();
        this.mQqEnvelopeList = new HashMap();
        new HashMap();
        this.mChildWeChatEnvelopeList = new HashMap();
        this.mEnvelopeStateChangeCallbackList = new CopyOnWriteArraySet();
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.notification.envelope.EnvelopeHelper.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                int intExtra = intent.getIntExtra("envelope_category", 0);
                int intExtra2 = intent.getIntExtra("envelope_related_uid", 0);
                String action = intent.getAction();
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onReceive category:", intExtra, intExtra2, "  uid:", "  action:");
                sbM.append(action);
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", sbM.toString());
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                action.hashCode();
                if (!action.equals("com.android.systemui.envelope.cancel_envelope")) {
                    if (action.equals("com.android.systemui.envelope.envelope_notification")) {
                        try {
                            int intExtra3 = intent.getIntExtra("lock_screen_visibility", 0);
                            EnvelopeHelper.m54$$Nest$menqueueEnvelopeNotification(EnvelopeHelper.this, intExtra, (Notification) intent.getParcelableExtra("transitive_envelope"), intent.getStringExtra("related_key"), intExtra2, intExtra3, intent.getIntExtra("suppressed_visual_effects", 0));
                            return;
                        } catch (IllegalArgumentException e) {
                            LogUtil.e(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "onReceive IllegalArgumentException:" + e.getMessage());
                            return;
                        }
                    }
                    return;
                }
                EnvelopeHelper envelopeHelper = EnvelopeHelper.this;
                if (intExtra != 1) {
                    if (intExtra == 2) {
                        synchronized (envelopeHelper.mEnvelopeNotificationLock) {
                            try {
                                NotificationManager notificationManagerFrom = NotificationManager.from(envelopeHelper.mContext);
                                Iterator it = envelopeHelper.mQqEnvelopeList.values().iterator();
                                while (it.hasNext()) {
                                    notificationManagerFrom.cancel(UserEnvelopeRecord.-$$Nest$mgetNotificationID((UserEnvelopeRecord) it.next()));
                                }
                                envelopeHelper.mQqEnvelopeList.clear();
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        return;
                    }
                    return;
                }
                synchronized (envelopeHelper.mEnvelopeNotificationLock) {
                    try {
                        if (intExtra2 == 0) {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "clear envelope list");
                            envelopeHelper.clearEnvelope(envelopeHelper.mWeChatEnvelopeList);
                        } else if (OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(intExtra2))) {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "clear clone envelope list");
                            envelopeHelper.clearEnvelope(envelopeHelper.mCloneWeChatEnvelopeList);
                        } else {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "clear child envelope list");
                            envelopeHelper.clearEnvelope(envelopeHelper.mChildWeChatEnvelopeList);
                        }
                        envelopeHelper.mEnvelopeViewManager.removeHeadUpViewImmediately();
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
            }
        };
        EnvelopeEnableObserver envelopeEnableObserver = new EnvelopeEnableObserver() { // from class: com.oplus.systemui.notification.envelope.EnvelopeHelper.2
            @Override // com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                EnvelopeHelper envelopeHelper = EnvelopeHelper.this;
                Context context2 = envelopeHelper.mContext;
                if (context2 != null) {
                    envelopeHelper.mEnvelopeAssistantStatus = AbsSettingsValueProxy.getSecureIntValue(context2, "envelope_assistant_enable", 1);
                }
                Iterator it = EnvelopeHelper.this.mEnvelopeStateChangeCallbackList.iterator();
                while (it.hasNext()) {
                    ((EnvelopeTile) it.next()).refreshState((Object) null);
                }
                EnvelopeHelper envelopeHelper2 = EnvelopeHelper.this;
                EnvelopeViewManager envelopeViewManager = envelopeHelper2.mEnvelopeViewManager;
                if (envelopeViewManager == null) {
                    return;
                }
                int i2 = envelopeHelper2.mEnvelopeAssistantStatus;
                if (i2 == 1) {
                    envelopeViewManager.registerReceiver();
                    return;
                }
                if (i2 == 0) {
                    synchronized (envelopeViewManager.mLock) {
                        try {
                            if (envelopeViewManager.mRegistered) {
                                envelopeViewManager.mRegistered = false;
                                ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).unregisterReceiver(envelopeViewManager.mReceiver);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            }
        };
        EnvelopeAutoOpenObserver envelopeAutoOpenObserver = new EnvelopeAutoOpenObserver() { // from class: com.oplus.systemui.notification.envelope.EnvelopeHelper.3
            @Override // com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                EnvelopeHelper envelopeHelper = EnvelopeHelper.this;
                Context context2 = envelopeHelper.mContext;
                if (context2 != null) {
                    envelopeHelper.mIsAutoOpen = AbsSettingsValueProxy.getSecureIntValue(context2, "envelope_auto_open", 0);
                }
            }
        };
        EnvelopeSoundObserver envelopeSoundObserver = new EnvelopeSoundObserver() { // from class: com.oplus.systemui.notification.envelope.EnvelopeHelper.4
            @Override // com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                EnvelopeHelper envelopeHelper = EnvelopeHelper.this;
                Context context2 = envelopeHelper.mContext;
                if (context2 != null) {
                    envelopeHelper.mSoundEnable = AbsSettingsValueProxy.getSecureIntValue(context2, "envelope_notice_sound_enable", 1);
                }
            }
        };
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        this.mEnvelopeCallBack = anonymousClass5;
        this.mContext = context;
        this.mEnvelopeAssistantStatus = AbsSettingsValueProxy.getSecureIntValue(context, "envelope_assistant_enable", 1);
        this.mIsAutoOpen = AbsSettingsValueProxy.getSecureIntValue(context, "envelope_auto_open", 0);
        this.mSoundEnable = AbsSettingsValueProxy.getSecureIntValue(context, "envelope_notice_sound_enable", 1);
        envelopeEnableObserver.registerForUserSwitch(context, true);
        envelopeAutoOpenObserver.registerForUserSwitch(context, true);
        envelopeSoundObserver.registerForUserSwitch(context, true);
        new AutoOpenNotificationHandler(context);
        NotificationFeatureOption.INSTANCE.getClass();
        if (NotificationFeatureOption.isEnvelopSupport && Process.myUserHandle().isSystem()) {
            EnvelopeViewManager envelopeViewManager = (EnvelopeViewManager) lazy.get();
            this.mEnvelopeViewManager = envelopeViewManager;
            envelopeViewManager.registerReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.android.systemui.envelope.envelope_notification");
            intentFilter.addAction("com.android.systemui.envelope.cancel_envelope");
            broadcastDispatcher.registerReceiver(broadcastReceiver, intentFilter, (Executor) null, UserHandle.ALL, 2, ProviderUtil.OPLUS_COMPONENT_SAFE);
        } else {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "initHelper isEnvelopSupport = " + NotificationFeatureOption.isEnvelopSupport + " isSystem = " + Process.myUserHandle().isSystem());
        }
        WeChatEnvelopeFieldList weChatEnvelopeFieldList = WeChatEnvelopeFieldList.getInstance();
        weChatEnvelopeFieldList.mContext = context;
        weChatEnvelopeFieldList.mHasInitialized = true;
        WeChatEnvelopeFieldList.updateEnvelopeDefaultInfo();
        weChatEnvelopeFieldList.mBootCompletedReceiver.register(context);
        if (context.getSharedPreferences("envelope_pre_key", 0).getInt("envelope_count_key", 0) == 0 || (i = (sharedPreferences = context.getSharedPreferences("envelope_pre_key", 0)).getInt("envelope_count_key", 0)) == 0) {
            return;
        }
        AbsSettingsValueProxy.setSecureIntValue(context, "key_envelope_count", i);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt("envelope_count_key", 0);
        editorEdit.commit();
    }

    public static UserEnvelopeRecord getEnvelopeRecord(HashMap map, String str, String str2, int i) {
        int i2;
        if (map.containsKey(str)) {
            UserEnvelopeRecord userEnvelopeRecord = (UserEnvelopeRecord) map.get(str);
            userEnvelopeRecord.setKey(str2);
            userEnvelopeRecord.setLockScreenVisibility(i);
            synchronized (userEnvelopeRecord.mLock) {
                userEnvelopeRecord.mCount++;
            }
            return userEnvelopeRecord;
        }
        int i3 = 20000;
        if (!TextUtils.isEmpty(str2)) {
            String[] strArrSplit = str2.split("\\|");
            if (strArrSplit == null || strArrSplit.length <= 2) {
                i2 = 0;
            } else {
                try {
                    i2 = Integer.parseInt(strArrSplit[2]);
                } catch (NumberFormatException unused) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "generateNotificationID error--key:".concat(str2));
                    i2 = 0;
                }
            }
            i3 = 20000 + i2;
        }
        UserEnvelopeRecord userEnvelopeRecord2 = new UserEnvelopeRecord();
        userEnvelopeRecord2.mLock = new Object();
        userEnvelopeRecord2.mKey = str2;
        userEnvelopeRecord2.mNotificationID = i3;
        userEnvelopeRecord2.mLockScreenVisibility = i;
        userEnvelopeRecord2.mCount = 1;
        VarHandle.storeStoreFence();
        map.put(str, userEnvelopeRecord2);
        return userEnvelopeRecord2;
    }

    public final void clearEnvelope(HashMap map) {
        NotificationManager notificationManagerFrom = NotificationManager.from(this.mContext);
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            notificationManagerFrom.cancelAsUser(WeChatEnvelopeHandler.class.getName(), UserEnvelopeRecord.-$$Nest$mgetNotificationID((UserEnvelopeRecord) it.next()), UserHandle.CURRENT);
        }
        map.clear();
    }

    public int getAutoOpenStatus() {
        return this.mIsAutoOpen;
    }

    public int getEnvelopeAssistantStatus() {
        return this.mEnvelopeAssistantStatus;
    }

    public int getSoundStatus() {
        return this.mSoundEnable;
    }
}
