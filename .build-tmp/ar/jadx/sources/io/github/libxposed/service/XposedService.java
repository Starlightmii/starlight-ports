package io.github.libxposed.service;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class XposedService {
    public static final int API_101 = 101;
    public static final int API_102 = 102;
    public static final long PROP_CAP_REMOTE = 2;
    public static final long PROP_CAP_SYSTEM = 1;
    public static final long PROP_RT_API_PROTECTION = 4;
    private final Map<String, RemotePreferences> mRemotePrefs = new HashMap();
    private final IXposedService mService;
    private static final Map<OnScopeEventListener, IXposedScopeCallback> scopeCallbacks = new ConcurrentHashMap();
    private static final Set<IHotReloadCallback> hotReloadCallbacks = ConcurrentHashMap.newKeySet();

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public interface HotReloadCallback {
        void onHotReloadResult(HookedTarget hookedTarget, HotReloadResult hotReloadResult);
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public interface OnScopeEventListener {
        /* JADX INFO: Access modifiers changed from: private */
        default IXposedScopeCallback asInterface() {
            return (IXposedScopeCallback) XposedService.scopeCallbacks.computeIfAbsent(this, new b(1, this));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* synthetic */ default IXposedScopeCallback lambda$asInterface$0(final OnScopeEventListener onScopeEventListener) {
            return new IXposedScopeCallback.Stub() { // from class: io.github.libxposed.service.XposedService.OnScopeEventListener.1
                @Override // io.github.libxposed.service.IXposedScopeCallback
                public void onScopeRequestApproved(List<String> list) {
                    onScopeEventListener.onScopeRequestApproved(list);
                    XposedService.scopeCallbacks.remove(onScopeEventListener);
                }

                @Override // io.github.libxposed.service.IXposedScopeCallback
                public void onScopeRequestFailed(String str) {
                    onScopeEventListener.onScopeRequestFailed(str);
                    XposedService.scopeCallbacks.remove(onScopeEventListener);
                }
            };
        }

        default void onScopeRequestApproved(List<String> list) {
        }

        default void onScopeRequestFailed(String str) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class ServiceException extends RuntimeException {
        public ServiceException(RemoteException remoteException) {
            super("Xposed service error", remoteException);
        }

        public ServiceException(String str) {
            super(str);
        }
    }

    public XposedService(IXposedService iXposedService) {
        this.mService = iXposedService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ RemotePreferences lambda$getRemotePreferences$0(String str) {
        try {
            return RemotePreferences.newInstance(this, str);
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    private void requireApi(int i5) {
        if (getApiVersion() >= i5) {
            return;
        }
        throw new UnsupportedOperationException("Requires Xposed service API " + i5);
    }

    private static HookedTarget toHookedTarget(HookedProcess hookedProcess) {
        String str = hookedProcess.processName;
        if (str != null) {
            return new HookedTarget(hookedProcess.targetId, hookedProcess.uid, hookedProcess.pid, str, toHookedTargetState(hookedProcess.state), hookedProcess.loadedVersionCode);
        }
        throw new ServiceException("Framework returns target with null processName");
    }

    private static HookedTarget.State toHookedTargetState(int i5) {
        if (i5 == 0) {
            return HookedTarget.State.UP_TO_DATE;
        }
        if (i5 == 1) {
            return HookedTarget.State.STALE;
        }
        if (i5 == 2) {
            return HookedTarget.State.RELOADING;
        }
        if (i5 == 3) {
            return HookedTarget.State.FAILED;
        }
        throw new ServiceException("Invalid hooked target state: " + i5);
    }

    public IXposedService asInterface() {
        return this.mService;
    }

    public boolean deleteRemoteFile(String str) {
        try {
            return this.mService.deleteRemoteFile(str);
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public synchronized void deleteRemotePreferences(String str) {
        try {
            try {
                RemotePreferences remotePreferences = this.mRemotePrefs.get(str);
                if (remotePreferences != null) {
                    remotePreferences.onDelete();
                }
                this.mService.deleteRemotePreferences(str);
            } catch (RemoteException e5) {
                throw new ServiceException(e5);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public int getApiVersion() {
        try {
            return this.mService.getApiVersion();
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public String getFrameworkName() {
        try {
            return this.mService.getFrameworkName();
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public long getFrameworkProperties() {
        try {
            return this.mService.getFrameworkProperties();
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public String getFrameworkVersion() {
        try {
            return this.mService.getFrameworkVersion();
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public long getFrameworkVersionCode() {
        try {
            return this.mService.getFrameworkVersionCode();
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public synchronized SharedPreferences getRemotePreferences(String str) {
        return this.mRemotePrefs.computeIfAbsent(str, new b(0, this));
    }

    public List<HookedTarget> getRunningTargets() {
        requireApi(102);
        try {
            List<HookedProcess> runningTargets = this.mService.getRunningTargets();
            if (runningTargets == null) {
                throw new ServiceException("Framework returns null");
            }
            ArrayList arrayList = new ArrayList(runningTargets.size());
            for (HookedProcess hookedProcess : runningTargets) {
                if (hookedProcess == null) {
                    throw new ServiceException("Framework returns null target");
                }
                arrayList.add(toHookedTarget(hookedProcess));
            }
            return Collections.unmodifiableList(arrayList);
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public List<String> getScope() {
        try {
            return this.mService.getScope();
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public void hotReloadModule(final HookedTarget hookedTarget, Bundle bundle, final HotReloadCallback hotReloadCallback) {
        requireApi(102);
        Objects.requireNonNull(hookedTarget);
        Objects.requireNonNull(hotReloadCallback);
        IHotReloadCallback.Stub stub = new IHotReloadCallback.Stub() { // from class: io.github.libxposed.service.XposedService.1
            @Override // io.github.libxposed.service.IHotReloadCallback
            public void onHotReloadResult(int i5, String str) {
                try {
                    hotReloadCallback.onHotReloadResult(hookedTarget, HotReloadResult.from(i5, str));
                } finally {
                    XposedService.hotReloadCallbacks.remove(this);
                }
            }
        };
        hotReloadCallbacks.add(stub);
        try {
            this.mService.hotReloadModule(hookedTarget.mTargetId, bundle, stub);
        } catch (RemoteException e5) {
            hotReloadCallbacks.remove(stub);
            throw new ServiceException(e5);
        } catch (RuntimeException e6) {
            hotReloadCallbacks.remove(stub);
            throw e6;
        }
    }

    public String[] listRemoteFiles() {
        try {
            String[] strArrListRemoteFiles = this.mService.listRemoteFiles();
            if (strArrListRemoteFiles != null) {
                return strArrListRemoteFiles;
            }
            throw new ServiceException("Framework returns null");
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public ParcelFileDescriptor openRemoteFile(String str) {
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenRemoteFile = this.mService.openRemoteFile(str);
            if (parcelFileDescriptorOpenRemoteFile != null) {
                return parcelFileDescriptorOpenRemoteFile;
            }
            throw new ServiceException("Framework returns null");
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public void removeScope(List<String> list) {
        try {
            this.mService.removeScope(list);
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }

    public void requestScope(List<String> list, OnScopeEventListener onScopeEventListener) {
        try {
            this.mService.requestScope(list, onScopeEventListener.asInterface());
        } catch (RemoteException e5) {
            throw new ServiceException(e5);
        }
    }
}
