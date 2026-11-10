package com.oplus.systemui.media.output.data;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.media.AudioManager;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.android.systemui.statusbar.policy.BluetoothControllerImpl;
import com.oplus.bluetooth.OplusA2dpSharingManager;
import com.oplus.bluetooth.OplusA2dpSharingStateCallback;
import com.oplus.systemui.media.output.OplusMediaOutputSwitchingPage;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OutputAudioSharingRepository {
    public static final Companion Companion = new Companion();
    private static final String PROPERTY_AUDIO_SHARING = "ro.oplus.audio.support.audio_sharing";
    private static final String TAG = "OutputAudioSharingRepository";
    private final MutableStateFlow _audioSharingAvailable;
    private final MutableStateFlow _audioSharingState;
    private final MutableStateFlow _audioSharingTemporary;
    private final Lazy audioManager$delegate;
    private final StateFlow audioSharingAvailable;
    private final StateFlow audioSharingState;
    private final StateFlow audioSharingTemporary;
    private final CoroutineScope bgScope;
    private final BluetoothController bluetoothController;
    private final OutputBluetoothScanner bluetoothScanner;
    public boolean bluetoothState;
    private final Context context;
    private final Lazy isAudioSharingSupport$delegate;
    public boolean isBluetoothConnected;
    public boolean isPhoneModeRegistered;
    public volatile boolean isStateCallbackRegistered;
    private OplusMediaOutputSwitchingPage.OnDataChangeListener listener;
    private final Executor mainExecutor;
    private final AudioManager.OnModeChangedListener modeListener;
    private OplusA2dpSharingManager oplusA2dpSharingManager;
    public int phoneMode;
    public boolean scanBluetoothDevice;
    private final StateFlow scannedDevices;
    private WeakReference<Consumer<Boolean>> sharingCallback;
    private final OutputAudioSharingRepository$stateCallback$1 stateCallback;

    /* JADX INFO: renamed from: com.oplus.systemui.media.output.data.OutputAudioSharingRepository$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements BluetoothController.Callback {
        public /* synthetic */ OutputAudioSharingRepository this$0;

        public final void onBluetoothDevicesChanged() {
        }

        public final void onBluetoothStateChange(boolean z) {
            this.this$0.setBluetoothState(z);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$nYCPHz3sMWITQO-gw0qxjmkY0VU, reason: not valid java name */
    public static AudioManager m19$r8$lambda$nYCPHz3sMWITQOgw0qxjmkY0VU(OutputAudioSharingRepository outputAudioSharingRepository) {
        return (AudioManager) outputAudioSharingRepository.context.getSystemService("audio");
    }

    /* JADX WARN: Type inference failed for: r1v11, types: [com.oplus.systemui.media.output.data.OutputAudioSharingRepository$stateCallback$1] */
    public OutputAudioSharingRepository(Context context, OutputBluetoothScanner outputBluetoothScanner, CoroutineScope coroutineScope, Executor executor, BluetoothController bluetoothController) {
        this.context = context;
        this.bluetoothScanner = outputBluetoothScanner;
        this.bgScope = coroutineScope;
        this.mainExecutor = executor;
        this.bluetoothController = bluetoothController;
        Boolean bool = Boolean.FALSE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._audioSharingState = MutableStateFlow;
        this.audioSharingState = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._audioSharingTemporary = MutableStateFlow2;
        this.audioSharingTemporary = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._audioSharingAvailable = MutableStateFlow3;
        this.audioSharingAvailable = FlowKt.asStateFlow(MutableStateFlow3);
        this.isAudioSharingSupport$delegate = LazyKt__LazyJVMKt.lazy(new OutputAudioSharingRepository$$ExternalSyntheticLambda0());
        OutputAudioSharingRepository$$ExternalSyntheticLambda1 outputAudioSharingRepository$$ExternalSyntheticLambda1 = new OutputAudioSharingRepository$$ExternalSyntheticLambda1();
        outputAudioSharingRepository$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.audioManager$delegate = LazyKt__LazyJVMKt.lazy(outputAudioSharingRepository$$ExternalSyntheticLambda1);
        this.phoneMode = getAudioManager().getMode();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        ((BluetoothControllerImpl) bluetoothController).addCallback(anonymousClass1);
        this.stateCallback = new OplusA2dpSharingStateCallback() { // from class: com.oplus.systemui.media.output.data.OutputAudioSharingRepository$stateCallback$1
            public final void onSharingStarted(int i, int i2, int i3, BluetoothDevice bluetoothDevice, BluetoothDevice bluetoothDevice2) {
                boolean z = i == 0;
                boolean z2 = i3 == 2;
                this.this$0._audioSharingTemporary.setValue(Boolean.valueOf(z2));
                this.this$0._audioSharingState.setValue(Boolean.valueOf(z));
                StringBuilder sb = new StringBuilder("onSharingStarted: status = ");
                sb.append(i);
                sb.append(", errCode = ");
                RectList$.ExternalSyntheticOutline0.m(i2, i3, ", sharingType = ", ", primary = ", sb);
                sb.append(bluetoothDevice);
                sb.append(", secondary = ");
                sb.append(bluetoothDevice2);
                sb.append(" , inAudioSharing = ");
                LogUtil.i$default(LogUtil.TAG_QS, "OutputAudioSharingRepository", KeyguardLogger$.ExternalSyntheticOutline0.m(sb, z, " inAudioSharingTemporary = ", z2), null, 8);
                BuildersKt.launch$default(this.this$0.bgScope, (CoroutineContext) null, (CoroutineStart) null, new onSharingStarted.1(this.this$0, z, (Continuation) null), 3);
                this.this$0.checkAudioSharingAvailable();
            }

            public final void onSharingStopped(int i, int i2, int i3, BluetoothDevice bluetoothDevice, BluetoothDevice bluetoothDevice2) {
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onSharingStopped: status = ", i, i2, ", errCode = ", ", sharingType = ");
                sbM.append(i3);
                sbM.append(", primary = ");
                sbM.append(bluetoothDevice);
                sbM.append(", secondary = ");
                sbM.append(bluetoothDevice2);
                LogUtil.i$default(LogUtil.TAG_QS, "OutputAudioSharingRepository", sbM.toString(), null, 8);
                this.this$0._audioSharingState.setValue(Boolean.FALSE);
                BuildersKt.launch$default(this.this$0.bgScope, (CoroutineContext) null, (CoroutineStart) null, new onSharingStopped.1(this.this$0, (Continuation) null), 3);
                this.this$0.checkAudioSharingAvailable();
            }
        };
        this.scannedDevices = outputBluetoothScanner.getDevices();
        OutputAudioSharingRepository$modeListener$1 outputAudioSharingRepository$modeListener$1 = new OutputAudioSharingRepository$modeListener$1();
        outputAudioSharingRepository$modeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.modeListener = outputAudioSharingRepository$modeListener$1;
    }

    public static final Object access$onSharingCallback(OutputAudioSharingRepository outputAudioSharingRepository, boolean z, SuspendLambda suspendLambda) {
        outputAudioSharingRepository.getClass();
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new onSharingCallback.2(outputAudioSharingRepository, z, (Continuation) null), suspendLambda);
        return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    private final AudioManager getAudioManager() {
        return (AudioManager) this.audioManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusA2dpSharingManager getOplusA2dpSharingManager() {
        if (this.oplusA2dpSharingManager == null) {
            this.oplusA2dpSharingManager = OplusA2dpSharingManager.getOplusA2dpSharingManager();
        }
        return this.oplusA2dpSharingManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBluetoothState(boolean z) {
        boolean z2 = this.bluetoothState != z;
        this.bluetoothState = z;
        if (!z) {
            if (z2) {
                BuildersKt.launch$default(this.bgScope, (CoroutineContext) null, (CoroutineStart) null, new OutputAudioSharingRepository$unregisterStateCallback$1(this, null), 3);
            }
        } else if (z2 || !this.isStateCallbackRegistered) {
            BuildersKt.launch$default(this.bgScope, (CoroutineContext) null, (CoroutineStart) null, new OutputAudioSharingRepository$registerStateCallback$1(this, null), 3);
        }
    }

    public final void addOnPhoneModeChangedListener(OplusMediaOutputSwitchingPage.OnDataChangeListener onDataChangeListener) {
        if (this.isPhoneModeRegistered) {
            return;
        }
        this.phoneMode = getAudioManager().getMode();
        this.listener = onDataChangeListener;
        getAudioManager().addOnModeChangedListener((Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR), this.modeListener);
        this.isPhoneModeRegistered = true;
    }

    public final void checkAudioSharingAvailable() {
        BuildersKt.launch$default(this.bgScope, (CoroutineContext) null, (CoroutineStart) null, new checkAudioSharingAvailable.1(this, (Continuation) null), 3);
    }

    public final StateFlow getAudioSharingAvailable() {
        return this.audioSharingAvailable;
    }

    public final StateFlow getAudioSharingState() {
        return this.audioSharingState;
    }

    public final StateFlow getAudioSharingTemporary() {
        return this.audioSharingTemporary;
    }

    public final OplusMediaOutputSwitchingPage.OnDataChangeListener getListener() {
        return this.listener;
    }

    public final int getPhoneMode() {
        return this.phoneMode;
    }

    public final boolean getScanBluetoothDevice() {
        return this.scanBluetoothDevice;
    }

    public final StateFlow getScannedDevices() {
        return this.scannedDevices;
    }

    public final boolean isAudioSharingSupport() {
        return ((Boolean) this.isAudioSharingSupport$delegate.getValue()).booleanValue();
    }

    public final void removeOnPhoneModeChangedListener() {
        if (this.isPhoneModeRegistered) {
            getAudioManager().removeOnModeChangedListener(this.modeListener);
            this.isPhoneModeRegistered = false;
        }
    }

    public final void setBluetoothConnected(boolean z) {
        if (this.isBluetoothConnected != z) {
            this.isBluetoothConnected = z;
            checkAudioSharingAvailable();
        }
    }

    public final void setListener(OplusMediaOutputSwitchingPage.OnDataChangeListener onDataChangeListener) {
        this.listener = onDataChangeListener;
    }

    public final void setPhoneMode(int i) {
        this.phoneMode = i;
    }

    public final void setScanBluetoothDevice(boolean z) {
        this.scanBluetoothDevice = z;
        this.bluetoothScanner.setScan(z);
    }

    public final void startSharing(BluetoothDevice bluetoothDevice, Consumer<Boolean> consumer) {
        this.sharingCallback = new WeakReference<>(consumer);
        BuildersKt.launch$default(this.bgScope, (CoroutineContext) null, (CoroutineStart) null, new startSharing.1(this, bluetoothDevice, (Continuation) null), 3);
    }
}
