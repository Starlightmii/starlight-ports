package com.oplus.systemui.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import com.android.systemui.keyguard.OplusCommonSensorEx;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCommonSensorExImpl extends OplusCommonSensorEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCommonSensorExImpl";
    private final Context context;
    private Sensor mDefaultSensor;
    private Sensor mGestureProximitySensor;
    private Sensor mPocketSensor;
    private Sensor mProximitySensor;
    private SensorManager mSensorManager;
    private Sensor mTiltDetectorSensor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusCommonSensorExImpl(Context context) {
        super(context);
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public Sensor getGestureProximityTypeSensor() {
        if (this.mSensorManager == null) {
            Object systemService = this.context.getSystemService("sensor");
            this.mSensorManager = systemService instanceof SensorManager ? (SensorManager) systemService : null;
        }
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null && this.mGestureProximitySensor == null) {
            LogUtil.i$default(LogUtil.TAG_AOD, TAG, "getGestureProximityTypeSensor register GestureProximity sensor", null, 8);
            this.mGestureProximitySensor = sensorManager.getDefaultSensor(33171066, true);
        }
        return this.mGestureProximitySensor;
    }

    public Sensor getPocketTypeSensor() {
        if (this.mSensorManager == null) {
            Object systemService = this.context.getSystemService("sensor");
            this.mSensorManager = systemService instanceof SensorManager ? (SensorManager) systemService : null;
        }
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null && this.mPocketSensor == null) {
            LogUtil.i$default(LogUtil.TAG_AOD, TAG, "getPocketTypeSensor register pocket sensor", null, 8);
            this.mPocketSensor = sensorManager.getDefaultSensor(33171075, true);
        }
        return this.mPocketSensor;
    }

    public Sensor getProximitySensor() {
        if (this.mSensorManager == null) {
            Object systemService = this.context.getSystemService("sensor");
            this.mSensorManager = systemService instanceof SensorManager ? (SensorManager) systemService : null;
        }
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null && this.mDefaultSensor == null) {
            Sensor defaultSensor = sensorManager.getDefaultSensor(33171075, true);
            this.mDefaultSensor = defaultSensor;
            if (defaultSensor == null) {
                LogUtil.i$default(LogUtil.TAG_AOD, TAG, "doesn't support pocket , register TYPE_PROXIMITY", null, 8);
                this.mDefaultSensor = sensorManager.getDefaultSensor(8);
            } else {
                LogUtil.i$default(LogUtil.TAG_AOD, TAG, "register pocket sensor", null, 8);
            }
        }
        return this.mDefaultSensor;
    }

    public Sensor getProximityTypeSensor() {
        if (this.mSensorManager == null) {
            Object systemService = this.context.getSystemService("sensor");
            this.mSensorManager = systemService instanceof SensorManager ? (SensorManager) systemService : null;
        }
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null && this.mProximitySensor == null) {
            LogUtil.i$default(LogUtil.TAG_AOD, TAG, "getProximityTypeSensor register TYPE_PROXIMITY", null, 8);
            this.mProximitySensor = sensorManager.getDefaultSensor(8);
        }
        return this.mProximitySensor;
    }

    public Sensor getTiltDetectorTypeSensor() {
        if (this.mSensorManager == null) {
            Object systemService = this.context.getSystemService("sensor");
            this.mSensorManager = systemService instanceof SensorManager ? (SensorManager) systemService : null;
        }
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null && this.mTiltDetectorSensor == null) {
            LogUtil.i$default(LogUtil.TAG_AOD, TAG, "getTiltDetectorTypeSensor register TiltDetector sensor", null, 8);
            this.mTiltDetectorSensor = sensorManager.getDefaultSensor(65611, true);
        }
        return this.mTiltDetectorSensor;
    }
}
