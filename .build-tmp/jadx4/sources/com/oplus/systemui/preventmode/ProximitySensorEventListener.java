package com.oplus.systemui.preventmode;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ProximitySensorEventListener implements SensorEventListener {
    public static final Companion Companion = new Companion();
    public int downOrNot;
    private ProximityStateChangedCallback mProximityStateChangedCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ProximityStateChangedCallback {
    }

    public final int getDownOrNot() {
        return this.downOrNot;
    }

    public ProximityStateChangedCallback getMProximityStateChangedCallback() {
        return this.mProximityStateChangedCallback;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null) {
            float[] fArr = sensorEvent.values;
            int i = -1;
            if (fArr.length == 0) {
                KgdLogUtil.i(OplusPreventModeExImpl.TAG, "Proximity: Event has no values!");
                ProximityStateChangedCallback mProximityStateChangedCallback = getMProximityStateChangedCallback();
                if (mProximityStateChangedCallback != null) {
                    ((OplusPreventModeExImpl) mProximityStateChangedCallback).onProximityStateChanged(0, -1);
                    return;
                }
                return;
            }
            float f = fArr[0];
            Sensor sensor = sensorEvent.sensor;
            KgdLogUtil.i(OplusPreventModeExImpl.TAG, "Proximity: Event: value =  " + f + ", type : " + (sensor != null ? Integer.valueOf(sensor.getType()) : null));
            Sensor sensor2 = sensorEvent.sensor;
            if (sensor2 != null && sensor2.getType() == 33171075) {
                float[] fArr2 = sensorEvent.values;
                if (fArr2.length > 1) {
                    float f2 = fArr2[1];
                    if (f2 == 1.0f) {
                        i = 1;
                    } else if (f2 == 0.0f) {
                        i = 0;
                    }
                    this.downOrNot = i;
                    KgdLogUtil.i(OplusPreventModeExImpl.TAG, "SENSOR_TYPE_POCKET: Event: value =  " + f2);
                }
            }
            ProximityStateChangedCallback mProximityStateChangedCallback2 = getMProximityStateChangedCallback();
            if (mProximityStateChangedCallback2 != null) {
                ((OplusPreventModeExImpl) mProximityStateChangedCallback2).onProximityStateChanged(sensorEvent.values[0] != 0.0f ? 2 : 1, this.downOrNot);
            }
        }
    }

    public final void setDownOrNot(int i) {
        this.downOrNot = i;
    }

    public void setMProximityStateChangedCallback(ProximityStateChangedCallback proximityStateChangedCallback) {
        this.mProximityStateChangedCallback = proximityStateChangedCallback;
    }
}
