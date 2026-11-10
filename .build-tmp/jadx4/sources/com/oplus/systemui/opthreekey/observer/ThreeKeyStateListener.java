package com.oplus.systemui.opthreekey.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.databinding.Observable;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.plugins.qs.NonNullObservableField;
import com.oplus.systemui.volume.repository.RingModeRepository;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreeKeyStateListener {
    public AtomicReference mApplicationContext;
    public AtomicInteger mCustomThreeKeyMode;
    public AnonymousClass1 mCustomThreeKeyModeUpdater;
    public InternalHandler mHandler;
    public NonNullObservableField mIsStealthModeOn;
    public Set mObservers;
    public SettingObserver mSettingObserver;
    public ThreeKeyStateListener$.ExternalSyntheticLambda0 mStealModeCallback;
    public NonNullObservableField mThreeKeyMode;
    public AnonymousClass1 mThreeKeyModeUpdater;
    public NonNullObservableField mThreeKeyPosition;

    /* JADX INFO: renamed from: com.oplus.systemui.opthreekey.observer.ThreeKeyStateListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends Observable.OnPropertyChangedCallback {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ ThreeKeyStateListener this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        /* JADX WARN: Code duplicated, block: B:20:0x0061  */
        /* JADX WARN: Code duplicated, block: B:21:0x0063  */
        public final void onPropertyChanged(Observable observable, int i) {
            switch (this.$r8$classId) {
                case 0:
                    ThreeKeyStateListener threeKeyStateListener = this.this$0;
                    int iIntValue = ((Integer) threeKeyStateListener.mThreeKeyPosition.get()).intValue();
                    VolumeFeatureOption.INSTANCE.getClass();
                    int i2 = 1;
                    if (VolumeFeatureOption.isSupportsStealthMode()) {
                        if (((Boolean) threeKeyStateListener.mIsStealthModeOn.get()).booleanValue()) {
                            i2 = 5;
                        } else if (iIntValue != 2) {
                            if (iIntValue == 3) {
                                i2 = 3;
                            } else {
                                i2 = -1;
                            }
                        }
                    } else if (iIntValue != 1) {
                        if (iIntValue == 2) {
                            i2 = 2;
                        } else if (iIntValue == 3) {
                            i2 = 3;
                        } else {
                            i2 = -1;
                        }
                    }
                    if (i2 != -1) {
                        threeKeyStateListener.mThreeKeyMode.set(Integer.valueOf(i2));
                    }
                    break;
                default:
                    ThreeKeyStateListener threeKeyStateListener2 = this.this$0;
                    Integer num = (Integer) threeKeyStateListener2.mThreeKeyMode.get();
                    num.getClass();
                    InternalHandler internalHandler = threeKeyStateListener2.mHandler;
                    int i3 = InternalHandler.$r8$clinit;
                    internalHandler.removeMessages(101);
                    internalHandler.sendMessage(internalHandler.obtainMessage(100, num));
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class InternalHandler extends Handler {
        public static final /* synthetic */ int $r8$clinit = 0;

        public InternalHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            if (i == 100 || i == 101) {
                int iIntValue = ((Integer) message.obj).intValue();
                ThreeKeyStateListener threeKeyStateListener = ThreeKeyStateListener.this;
                LogUtil.d(LogUtil.TAG_VOLUME, "ThreeKeyStateListener", TextFieldBuffer$.ExternalSyntheticOutline0.m(iIntValue, threeKeyStateListener.mCustomThreeKeyMode.getAndSet(iIntValue), "notifyObservers: currentState = ", ", prevState = "));
                Iterator it = threeKeyStateListener.mObservers.stream().iterator();
                while (it.hasNext()) {
                    ((ThreeKeyStateObserver) it.next()).stateChange(iIntValue);
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SettingObserver extends ContentObserver {
        public final Uri mAlertSliderUri;
        public final AtomicBoolean mIsRegistered;

        public SettingObserver() {
            super(ThreeKeyStateListener.this.mHandler);
            this.mAlertSliderUri = Settings.Global.getUriFor(RingModeRepository.ALERT_SLIDER_MODE);
            this.mIsRegistered = new AtomicBoolean(false);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            Uri uri2;
            super.onChange(z, uri);
            Context context = (Context) ThreeKeyStateListener.this.mApplicationContext.get();
            if (context == null || (uri2 = this.mAlertSliderUri) == null || !uri2.equals(uri)) {
                return;
            }
            int i = Settings.Global.getInt(context.getContentResolver(), RingModeRepository.ALERT_SLIDER_MODE, -1);
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "current threeKeyStatus : ", LogUtil.TAG_VOLUME, "ThreeKeyStateListener");
            if (i == -1) {
                return;
            }
            ThreeKeyStateListener.this.mThreeKeyPosition.set(Integer.valueOf(i));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class Singleton {
        public static final ThreeKeyStateListener INSTANCE;

        static {
            ThreeKeyStateListener threeKeyStateListener = new ThreeKeyStateListener();
            threeKeyStateListener.mApplicationContext = new AtomicReference();
            threeKeyStateListener.mCustomThreeKeyMode = new AtomicInteger(-1);
            threeKeyStateListener.mObservers = new HashSet();
            InternalHandler internalHandler = threeKeyStateListener.new InternalHandler(Looper.getMainLooper());
            threeKeyStateListener.mHandler = internalHandler;
            threeKeyStateListener.mSettingObserver = threeKeyStateListener.new SettingObserver();
            NonNullObservableField nonNullObservableField = new NonNullObservableField(Boolean.FALSE);
            threeKeyStateListener.mIsStealthModeOn = nonNullObservableField;
            NonNullObservableField nonNullObservableField2 = new NonNullObservableField(-1);
            threeKeyStateListener.mThreeKeyPosition = nonNullObservableField2;
            threeKeyStateListener.mThreeKeyMode = new NonNullObservableField(-1);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
            anonymousClass1.this$0 = threeKeyStateListener;
            VarHandle.storeStoreFence();
            AnonymousClass1 anonymousClass2 = new AnonymousClass1(1);
            anonymousClass2.this$0 = threeKeyStateListener;
            VarHandle.storeStoreFence();
            threeKeyStateListener.mCustomThreeKeyModeUpdater = anonymousClass2;
            ThreeKeyStateListener$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ThreeKeyStateListener$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = internalHandler;
            VarHandle.storeStoreFence();
            threeKeyStateListener.mStealModeCallback = externalSyntheticLambda0;
            nonNullObservableField.addOnPropertyChangedCallback(anonymousClass1);
            nonNullObservableField2.addOnPropertyChangedCallback(anonymousClass1);
            VarHandle.storeStoreFence();
            INSTANCE = threeKeyStateListener;
        }
    }

    public static ThreeKeyStateListener getInstance() {
        return Singleton.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x002b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:17:0x002c A[RETURN] */
    public static int getThreeKeyMode(Context context) {
        int i = Settings.Global.getInt(context.getContentResolver(), RingModeRepository.ALERT_SLIDER_MODE, -1);
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isSupportsStealthMode()) {
            if (i == 1) {
                return 5;
            }
            if (i != 2) {
                if (i == 3) {
                    return 3;
                }
                return -1;
            }
            return 1;
        }
        if (i != 1) {
            if (i == 2) {
                return 2;
            }
            if (i == 3) {
                return 3;
            }
            return -1;
        }
        return 1;
    }

    public final void addObserver(Context context, ThreeKeyStateObserver threeKeyStateObserver) {
        Context context2;
        if (context != null) {
            this.mApplicationContext.compareAndSet(null, context.getApplicationContext());
        }
        if (this.mObservers.isEmpty() && (context2 = (Context) this.mApplicationContext.get()) != null) {
            ThreeKeyStealthModeObserver.getInstance().addObserver(context2, this.mStealModeCallback);
            SettingObserver settingObserver = this.mSettingObserver;
            settingObserver.getClass();
            LogUtil.d(LogUtil.TAG_VOLUME, "ThreeKeyStateListener", "register : ");
            if (settingObserver.mAlertSliderUri != null && settingObserver.mIsRegistered.compareAndSet(false, true)) {
                context2.getContentResolver().registerContentObserver(settingObserver.mAlertSliderUri, false, settingObserver, -2);
                ThreeKeyStateListener.this.mIsStealthModeOn.set(Boolean.valueOf(ThreeKeyStealthModeObserver.getInstance().isStealthModeOn()));
                ThreeKeyStateListener.this.mThreeKeyPosition.set(Integer.valueOf(Settings.Global.getInt(context2.getContentResolver(), RingModeRepository.ALERT_SLIDER_MODE, -1)));
                ThreeKeyStateListener threeKeyStateListener = ThreeKeyStateListener.this;
                threeKeyStateListener.mCustomThreeKeyMode.set(((Integer) threeKeyStateListener.mThreeKeyMode.get()).intValue());
                ThreeKeyStateListener threeKeyStateListener2 = ThreeKeyStateListener.this;
                threeKeyStateListener2.mThreeKeyMode.addOnPropertyChangedCallback(threeKeyStateListener2.mCustomThreeKeyModeUpdater);
            }
        }
        this.mObservers.add(threeKeyStateObserver);
    }

    public final void removeObserver(Context context, ThreeKeyStateObserver threeKeyStateObserver) {
        Context context2;
        if (context != null) {
            this.mApplicationContext.compareAndSet(null, context.getApplicationContext());
        }
        this.mObservers.remove(threeKeyStateObserver);
        if (!this.mObservers.isEmpty() || (context2 = (Context) this.mApplicationContext.get()) == null) {
            return;
        }
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver.mIsRegistered.compareAndSet(true, false)) {
            context2.getContentResolver().unregisterContentObserver(ThreeKeyStateListener.this.mSettingObserver);
            ThreeKeyStateListener threeKeyStateListener = ThreeKeyStateListener.this;
            threeKeyStateListener.mThreeKeyMode.removeOnPropertyChangedCallback(threeKeyStateListener.mCustomThreeKeyModeUpdater);
        }
        ThreeKeyStealthModeObserver.getInstance().removeObserver(context2, this.mStealModeCallback);
    }

    public int getThreeKeyMode() {
        return this.mCustomThreeKeyMode.get();
    }
}
