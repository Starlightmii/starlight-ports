package com.oplus.systemui.qs.external;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import android.service.quicksettings.Tile;
import android.util.Log;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.qs.OplusTileController;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.external.TileServices;
import com.android.systemui.qs.tile.TileServicesEx;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileServicesWrapper extends TileServicesEx implements OplusTileController.ITileServicesWrapper, PluginListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TileServicesWrapper";
    private final BroadcastDispatcher broadcastDispatcher;
    private final Context context;
    public boolean isAdd;
    private final Executor mainExecutor;
    private OplusTileController oplusTileController;
    private final PluginManager pluginManager;
    private final TileServicesWrapper$receiver$1 receiver;
    private final ConcurrentHashMap<ComponentName, CustomTile> tileMap = new ConcurrentHashMap<>();
    private TileServices tileServices;
    private final UserManager userManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.content.BroadcastReceiver, com.oplus.systemui.qs.external.TileServicesWrapper$receiver$1] */
    public TileServicesWrapper(Context context, PluginManager pluginManager, UserManager userManager, BroadcastDispatcher broadcastDispatcher, Executor executor) {
        this.context = context;
        this.pluginManager = pluginManager;
        this.userManager = userManager;
        this.broadcastDispatcher = broadcastDispatcher;
        this.mainExecutor = executor;
        ?? r1 = new BroadcastReceiver() { // from class: com.oplus.systemui.qs.external.TileServicesWrapper$receiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                if ("android.intent.action.USER_UNLOCKED".equals(intent != null ? intent.getAction() : null)) {
                    LogUtil.i$default(LogUtil.TAG_QS, "TileServicesWrapper", "ACTION_USER_UNLOCKED", null, 8);
                    this.this$0.addPluginListener();
                }
            }
        };
        this.receiver = r1;
        BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, (BroadcastReceiver) r1, new IntentFilter("android.intent.action.USER_UNLOCKED"), executor, (UserHandle) null, 0, 56);
        boolean zIsUserUnlocked = userManager.isUserUnlocked();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isUserUnlocked = ", zIsUserUnlocked), null, 8);
        if (zIsUserUnlocked) {
            addPluginListener();
        }
    }

    public final void addPluginListener() {
        this.broadcastDispatcher.unregisterReceiver(this.receiver);
        if (this.isAdd) {
            return;
        }
        PluginManager.addPluginListener$default(this.pluginManager, this, OplusTileController.class, false, 4, (Object) null);
        this.isAdd = true;
    }

    public boolean bindAppWhenListening(ComponentName componentName) {
        OplusTileController oplusTileController = this.oplusTileController;
        if (oplusTileController != null) {
            return oplusTileController.bindAppWhenListening(componentName);
        }
        return false;
    }

    public void create(TileServices tileServices) {
        this.tileServices = tileServices;
    }

    public final void destroy() {
        this.pluginManager.removePluginListener(this);
    }

    public Tile getQsTile(ComponentName componentName) {
        TileServices tileServices;
        if (componentName == null || (tileServices = this.tileServices) == null) {
            return null;
        }
        return tileServices.getTile(componentName);
    }

    public void onPluginConnected(OplusTileController oplusTileController, Context context) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onPluginConnected", null, 8);
        oplusTileController.setWrapper(this);
        for (Map.Entry<ComponentName, CustomTile> entry : this.tileMap.entrySet()) {
            oplusTileController.addTile(entry.getKey(), entry.getValue().getUserContext());
        }
        this.oplusTileController = oplusTileController;
    }

    public void onTileAddOrChange(ComponentName componentName) {
        OplusTileController oplusTileController;
        if (!this.tileMap.containsKey(componentName) || (oplusTileController = this.oplusTileController) == null) {
            return;
        }
        CustomTile customTile = this.tileMap.get(componentName);
        oplusTileController.addTile(componentName, customTile != null ? customTile.getUserContext() : null);
    }

    public void onTileRemove(CustomTile customTile) {
        ComponentName component = customTile.getComponent();
        if (Intrinsics.areEqual(this.tileMap.get(component), customTile)) {
            this.tileMap.remove(component);
            OplusTileController oplusTileController = this.oplusTileController;
            if (oplusTileController != null) {
                oplusTileController.removeTile(component);
            }
        }
    }

    public void refreshState(ComponentName componentName) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileServicesWrapper-->".concat("refreshState: " + componentName));
        }
        TileServices tileServices = this.tileServices;
        if (tileServices != null) {
            tileServices.refreshState(componentName);
        }
    }

    public void setListening(boolean z, ComponentName componentName, Context context) {
        OplusTileController oplusTileController = this.oplusTileController;
        if (oplusTileController != null) {
            oplusTileController.setListening(z, componentName, context);
        }
    }

    public boolean shouldDisableTile(ComponentName componentName) {
        OplusTileController oplusTileController = this.oplusTileController;
        if (oplusTileController != null) {
            return oplusTileController.shouldDisableTile(componentName);
        }
        return false;
    }

    public void updateCurrentStateIsNotDirty(ComponentName componentName) {
        OplusTileController oplusTileController = this.oplusTileController;
        if (oplusTileController != null) {
            oplusTileController.updateCurrentStateIsNotDirty(componentName);
        }
    }

    public void updateQsTile(ComponentName componentName, Tile tile) {
        if (componentName == null || tile == null) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileServicesWrapper-->".concat("updateQsTile: " + componentName + ", state: " + tile.getState()));
        }
        TileServices tileServices = this.tileServices;
        if (tileServices != null) {
            tileServices.updateQsTile(componentName, tile);
        }
    }

    public void onTileAddOrChange(CustomTile customTile) {
        ComponentName component = customTile.getComponent();
        this.tileMap.put(component, customTile);
        OplusTileController oplusTileController = this.oplusTileController;
        if (oplusTileController != null) {
            oplusTileController.addTile(component, customTile.getUserContext());
        }
    }
}
