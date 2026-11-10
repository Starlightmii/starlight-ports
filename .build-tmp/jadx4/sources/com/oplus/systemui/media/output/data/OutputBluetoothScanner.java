package com.oplus.systemui.media.output.data;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.KotlinNothingValueException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OutputBluetoothScanner {
    public static final Companion Companion = new Companion();
    private static final String DEVICE_TYPE_AIR_PODS = "AirPods";
    private static final String DEVICE_TYPE_OPLUS_EARBUD = "oplusEarBuds";
    private static final String EXTRA_OPLUS_MODEL_KEY = "android.bluetooth.device.extra.oplus_model";
    private static final String TAG = "OutputBluetoothScanner";
    private final MutableStateFlow _devices;
    private final Map<String, BluetoothDevice> allDevices;
    private final Context applicationContext;
    private final CoroutineScope bgScope;
    private final BluetoothAdapter bluetoothAdapter;
    private final Map<String, BluetoothDevice> boundedDevices;
    private final BroadcastDispatcher broadcastDispatcher;
    private OutputBluetoothDeviceIconController deviceIconController;
    private final StateFlow devices;
    private final Executor mainExecutor;
    private final OutputBluetoothScanner$receiver$1 receiver;
    public volatile boolean register;
    public boolean scan;
    private Job scanJob;
    private final Map<String, BluetoothWrapper> tempDevices;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [com.oplus.systemui.media.output.data.OutputBluetoothScanner$receiver$1] */
    public OutputBluetoothScanner(Context context, CoroutineScope coroutineScope, BluetoothAdapter bluetoothAdapter, BroadcastDispatcher broadcastDispatcher, Executor executor) {
        this.applicationContext = context;
        this.bgScope = coroutineScope;
        this.bluetoothAdapter = bluetoothAdapter;
        this.broadcastDispatcher = broadcastDispatcher;
        this.mainExecutor = executor;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(CollectionsKt__CollectionsKt.emptyList());
        this._devices = MutableStateFlow;
        this.devices = FlowKt.asStateFlow(MutableStateFlow);
        this.boundedDevices = new LinkedHashMap();
        this.tempDevices = new LinkedHashMap();
        this.allDevices = new LinkedHashMap();
        this.receiver = new BroadcastReceiver() { // from class: com.oplus.systemui.media.output.data.OutputBluetoothScanner$receiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                BluetoothDevice bluetoothDevice;
                if ("android.bluetooth.device.action.FOUND".equals(intent.getAction()) && (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE", BluetoothDevice.class)) != null) {
                    String stringExtra = intent.getStringExtra("android.bluetooth.device.extra.oplus_model");
                    if (this.this$0.allDevices.containsKey(bluetoothDevice.getAddress())) {
                        return;
                    }
                    this.this$0.allDevices.put(bluetoothDevice.getAddress(), bluetoothDevice);
                    OutputBluetoothScanner outputBluetoothScanner = this.this$0;
                    BuildersKt.launch$default(outputBluetoothScanner.bgScope, (CoroutineContext) null, (CoroutineStart) null, new OutputBluetoothScanner.onBluetoothDeviceFound.1(outputBluetoothScanner, bluetoothDevice, stringExtra, (Continuation) null), 3);
                }
            }
        };
    }

    public static final void access$addDevice(OutputBluetoothScanner outputBluetoothScanner, BluetoothWrapper bluetoothWrapper) {
        outputBluetoothScanner.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            BluetoothDevice device = bluetoothWrapper.getDevice();
            int type = bluetoothWrapper.getType();
            String appleType = bluetoothWrapper.getAppleType();
            StringBuilder sb = new StringBuilder("addDevice: ");
            sb.append(device);
            sb.append(" ");
            sb.append(type);
            sb.append(" ");
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(appleType, "OutputBluetoothScanner-->", "SystemUi--QuickSettings", sb);
        }
        outputBluetoothScanner.tempDevices.put(bluetoothWrapper.getDevice().getAddress(), bluetoothWrapper);
        OutputBluetoothDeviceIconController outputBluetoothDeviceIconController = outputBluetoothScanner.deviceIconController;
        if (outputBluetoothDeviceIconController != null) {
            outputBluetoothDeviceIconController.getDeviceIcon(bluetoothWrapper);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$collectBluetoothDeviceIcon(OutputBluetoothScanner outputBluetoothScanner, ContinuationImpl continuationImpl) {
        OutputBluetoothScanner$collectBluetoothDeviceIcon$1 outputBluetoothScanner$collectBluetoothDeviceIcon$1;
        SharedFlow deviceBitmap;
        outputBluetoothScanner.getClass();
        if (continuationImpl instanceof OutputBluetoothScanner$collectBluetoothDeviceIcon$1) {
            outputBluetoothScanner$collectBluetoothDeviceIcon$1 = (OutputBluetoothScanner$collectBluetoothDeviceIcon$1) continuationImpl;
            int i = outputBluetoothScanner$collectBluetoothDeviceIcon$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                outputBluetoothScanner$collectBluetoothDeviceIcon$1.label = i - Integer.MIN_VALUE;
            } else {
                outputBluetoothScanner$collectBluetoothDeviceIcon$1 = new OutputBluetoothScanner$collectBluetoothDeviceIcon$1(outputBluetoothScanner, continuationImpl);
            }
        } else {
            outputBluetoothScanner$collectBluetoothDeviceIcon$1 = new OutputBluetoothScanner$collectBluetoothDeviceIcon$1(outputBluetoothScanner, continuationImpl);
        }
        Object obj = outputBluetoothScanner$collectBluetoothDeviceIcon$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = outputBluetoothScanner$collectBluetoothDeviceIcon$1.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                OutputBluetoothDeviceIconController outputBluetoothDeviceIconController = outputBluetoothScanner.deviceIconController;
                if (outputBluetoothDeviceIconController == null || (deviceBitmap = outputBluetoothDeviceIconController.getDeviceBitmap()) == null) {
                    Result.constructor-impl((Object) null);
                    return Unit.INSTANCE;
                }
                collectBluetoothDeviceIcon.2.1 r2 = new collectBluetoothDeviceIcon.2.1();
                r2.$this_runCatching = outputBluetoothScanner;
                VarHandle.storeStoreFence();
                outputBluetoothScanner$collectBluetoothDeviceIcon$1.L$0 = SpillingKt.nullOutSpilledVariable(outputBluetoothScanner);
                outputBluetoothScanner$collectBluetoothDeviceIcon$1.I$0 = 0;
                outputBluetoothScanner$collectBluetoothDeviceIcon$1.label = 1;
                if (deviceBitmap.collect(r2, outputBluetoothScanner$collectBluetoothDeviceIcon$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public static final BluetoothWrapper access$filterDevice(OutputBluetoothScanner outputBluetoothScanner, BluetoothDevice bluetoothDevice, String str) {
        BluetoothClass bluetoothClass;
        Integer num;
        outputBluetoothScanner.getClass();
        String stringMetaData = BluetoothUtils.getStringMetaData(bluetoothDevice, 17);
        String stringMetaData2 = BluetoothUtils.getStringMetaData(bluetoothDevice, 17);
        boolean z = TextUtils.isEmpty(stringMetaData2) ? !((bluetoothClass = bluetoothDevice.getBluetoothClass()) == null || !BluetoothUtils.BLUETOOTH_DEVICE_CLASS_HEADSET.contains(Integer.valueOf(bluetoothClass.getDeviceClass()))) : "Headset".equals(stringMetaData2) || "Untethered Headset".equals(stringMetaData2);
        int majorDeviceClass = bluetoothDevice.getBluetoothClass().getMajorDeviceClass();
        String name = bluetoothDevice.getName();
        if (name == null || name.length() == 0) {
            return null;
        }
        if (str != null ? StringsKt__StringsJVMKt.startsWith(str, DEVICE_TYPE_AIR_PODS, false) : false) {
            num = 2;
        } else if (Intrinsics.areEqual(stringMetaData, DEVICE_TYPE_OPLUS_EARBUD)) {
            num = 1;
        } else {
            num = (z || majorDeviceClass == 1024) ? 0 : null;
        }
        boolean zContainsKey = outputBluetoothScanner.boundedDevices.containsKey(bluetoothDevice.getAddress());
        if (num != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("filterDevice: deviceMetaType = ", stringMetaData, ", oplusModel = ", str, ", bounded = ");
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isHeadset = ", ", btMajorClass = ", sbM, zContainsKey, z);
            MobileInputLogger$.ExternalSyntheticOutline0.m(majorDeviceClass, "OutputBluetoothScanner-->", "SystemUi--QuickSettings", sbM);
        }
        if (zContainsKey || num == null) {
            return null;
        }
        return new BluetoothWrapper(bluetoothDevice, name, num.intValue(), (Drawable) null, str);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0046  */
    /* JADX WARN: Code duplicated, block: B:29:0x0059 A[Catch: all -> 0x0034, TryCatch #0 {all -> 0x0034, blocks: (B:12:0x0030, B:38:0x0083, B:27:0x0054, B:29:0x0059, B:32:0x0061, B:34:0x0065, B:35:0x006c), top: B:41:0x0030 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x005e  */
    /* JADX WARN: Code duplicated, block: B:32:0x0061 A[Catch: all -> 0x0034, TryCatch #0 {all -> 0x0034, blocks: (B:12:0x0030, B:38:0x0083, B:27:0x0054, B:29:0x0059, B:32:0x0061, B:34:0x0065, B:35:0x006c), top: B:41:0x0030 }] */
    /* JADX WARN: Code duplicated, block: B:43:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7, types: [int] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0080 -> B:38:0x0083). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:0:?
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object access$scanBluetoothDevice(com.oplus.systemui.media.output.data.OutputBluetoothScanner r6, kotlinx.coroutines.CoroutineScope r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r6.getClass()
            boolean r0 = r8 instanceof com.oplus.systemui.media.output.data.OutputBluetoothScanner$scanBluetoothDevice$1
            if (r0 == 0) goto L16
            r0 = r8
            com.oplus.systemui.media.output.data.OutputBluetoothScanner$scanBluetoothDevice$1 r0 = (com.oplus.systemui.media.output.data.OutputBluetoothScanner$scanBluetoothDevice$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.oplus.systemui.media.output.data.OutputBluetoothScanner$scanBluetoothDevice$1 r0 = new com.oplus.systemui.media.output.data.OutputBluetoothScanner$scanBluetoothDevice$1
            r0.<init>(r6, r8)
        L1b:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L36
            java.lang.Object r7 = r0.L$1
            com.oplus.systemui.media.output.data.OutputBluetoothScanner r7 = (com.oplus.systemui.media.output.data.OutputBluetoothScanner) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L34
            goto L83
        L34:
            r8 = move-exception
            goto L89
        L36:
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r6)
            r6 = 0
            return r6
        L3d:
            kotlin.ResultKt.throwOnFailure(r8)
        L40:
            boolean r8 = kotlinx.coroutines.CoroutineScopeKt.isActive(r7)
            if (r8 != 0) goto L54
            kotlinx.coroutines.Job r8 = r6.scanJob
            if (r8 == 0) goto L51
            boolean r8 = r8.isCancelled()
            if (r8 != 0) goto L51
            goto L54
        L51:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            goto L82
        L54:
            android.bluetooth.BluetoothAdapter r8 = r6.bluetoothAdapter     // Catch: java.lang.Throwable -> L34
            r2 = 0
            if (r8 == 0) goto L5e
            boolean r8 = r8.isDiscovering()     // Catch: java.lang.Throwable -> L34
            goto L5f
        L5e:
            r8 = r2
        L5f:
            if (r8 != 0) goto L6c
            android.bluetooth.BluetoothAdapter r4 = r6.bluetoothAdapter     // Catch: java.lang.Throwable -> L34
            if (r4 == 0) goto L6c
            boolean r4 = r4.startDiscovery()     // Catch: java.lang.Throwable -> L34
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch: java.lang.Throwable -> L34
        L6c:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L34
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch: java.lang.Throwable -> L34
            r0.L$1 = r4     // Catch: java.lang.Throwable -> L34
            r0.I$0 = r2     // Catch: java.lang.Throwable -> L34
            r0.I$1 = r8     // Catch: java.lang.Throwable -> L34
            r0.label = r3     // Catch: java.lang.Throwable -> L34
            r4 = 6000(0x1770, double:2.9644E-320)
            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r4, r0)     // Catch: java.lang.Throwable -> L34
            if (r8 != r1) goto L83
        L82:
            return r1
        L83:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L34
            kotlin.Result.constructor-impl(r8)     // Catch: java.lang.Throwable -> L34
            goto L40
        L89:
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
            kotlin.Result.constructor-impl(r8)
            goto L40
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.media.output.data.OutputBluetoothScanner.access$scanBluetoothDevice(com.oplus.systemui.media.output.data.OutputBluetoothScanner, kotlinx.coroutines.CoroutineScope, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final StateFlow getDevices() {
        return this.devices;
    }

    public final boolean getScan() {
        return this.scan;
    }

    public final void setScan(boolean z) {
        if (z == this.scan) {
            return;
        }
        this.scan = z;
        if (z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OutputBluetoothScanner-->startScan: ");
            }
            if (!this.register) {
                this.register = true;
                BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.receiver, new IntentFilter("android.bluetooth.device.action.FOUND"), this.mainExecutor, (UserHandle) null, 0, 56);
            }
            OutputBluetoothDeviceIconController outputBluetoothDeviceIconController = new OutputBluetoothDeviceIconController(this.applicationContext, this.bgScope);
            outputBluetoothDeviceIconController.startObserver();
            this.deviceIconController = outputBluetoothDeviceIconController;
            this.boundedDevices.clear();
            this.tempDevices.clear();
            this.allDevices.clear();
            Job job = this.scanJob;
            if (job != null) {
                Job.Key key = Job.Key;
                job.cancel((CancellationException) null);
            }
            this.scanJob = BuildersKt.launch$default(this.bgScope, (CoroutineContext) null, (CoroutineStart) null, new startScan.3(this, (Continuation) null), 3);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OutputBluetoothScanner-->stopScan: ");
        }
        BuildersKt.launch$default(this.bgScope, (CoroutineContext) null, (CoroutineStart) null, new stopScan.2(this, (Continuation) null), 3);
        if (this.register) {
            this.register = false;
            this.broadcastDispatcher.unregisterReceiver(this.receiver);
        }
        OutputBluetoothDeviceIconController outputBluetoothDeviceIconController2 = this.deviceIconController;
        if (outputBluetoothDeviceIconController2 != null) {
            outputBluetoothDeviceIconController2.release();
        }
        this._devices.setValue(CollectionsKt__CollectionsKt.emptyList());
        this.boundedDevices.clear();
        this.tempDevices.clear();
        this.allDevices.clear();
        Job job2 = this.scanJob;
        if (job2 != null) {
            Job.Key key2 = Job.Key;
            job2.cancel((CancellationException) null);
        }
        this.scanJob = null;
    }
}
