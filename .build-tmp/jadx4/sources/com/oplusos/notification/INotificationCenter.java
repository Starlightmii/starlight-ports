package com.oplusos.notification;

import android.app.NotificationChannel;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.UserHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface INotificationCenter extends IInterface {
    public static final String DESCRIPTOR = "com.oplusos.notification.INotificationCenter";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class Stub extends Binder implements INotificationCenter {
        static final int TRANSACTION_areNotificationsEnabledForPackage = 8;
        static final int TRANSACTION_canShowBanner = 4;
        static final int TRANSACTION_disableAllNotificationChannel = 15;
        static final int TRANSACTION_enableAllNotificationChannel = 14;
        static final int TRANSACTION_getBadgeOption = 9;
        static final int TRANSACTION_getChannelName = 3;
        static final int TRANSACTION_getLockscreenVisibility = 5;
        static final int TRANSACTION_getStowOption = 13;
        static final int TRANSACTION_getSupportNumberBadgePackages = 7;
        static final int TRANSACTION_getVersion = 19;
        static final int TRANSACTION_isChangeableFold = 6;
        static final int TRANSACTION_isFold = 2;
        static final int TRANSACTION_notifyBrodcast = 20;
        static final int TRANSACTION_notifyChannelOrGroupAdded = 21;
        static final int TRANSACTION_notifyPackageNotificationStateChanged = 22;
        static final int TRANSACTION_setChangedByUserForPackageState = 17;
        static final int TRANSACTION_setFold = 1;
        static final int TRANSACTION_setNotificationsEnabledWithImportanceLockForPackage = 18;
        static final int TRANSACTION_setStowOption = 12;
        static final int TRANSACTION_switchNotificationChannel = 16;
        static final int TRANSACTION_updateChannelSound = 10;
        static final int TRANSACTION_updateChannelVibration = 11;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class Proxy implements INotificationCenter {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.oplusos.notification.INotificationCenter
            public boolean areNotificationsEnabledForPackage(String str, int i) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readBoolean();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oplusos.notification.INotificationCenter
            public boolean canShowBanner(String str, int i, String str2) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readBoolean();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void disableAllNotificationChannel(String str) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(Stub.TRANSACTION_disableAllNotificationChannel, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void enableAllNotificationChannel(String str) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(Stub.TRANSACTION_enableAllNotificationChannel, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public int getBadgeOption(String str, int i) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public String getChannelName(String str, int i, String str2) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public final String getInterfaceDescriptor() {
                return INotificationCenter.DESCRIPTOR;
            }

            @Override // com.oplusos.notification.INotificationCenter
            public int getLockscreenVisibility(String str, int i, String str2) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public int getStowOption(String str, int i) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public List<String> getSupportNumberBadgePackages() {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public int getVersion() {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getVersion, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public boolean isChangeableFold(String str, int i, String str2) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readBoolean();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public boolean isFold(String str, int i, String str2) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readBoolean();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void notifyBrodcast(String str, Intent intent) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedObject(intent, 0);
                    this.mRemote.transact(20, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void notifyChannelOrGroupAdded(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedObject(userHandle, 0);
                    parcelObtain.writeTypedObject(notificationChannel, 0);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(21, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void notifyPackageNotificationStateChanged(String str, int i) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(22, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void setChangedByUserForPackageState(String str, int i, boolean z) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeBoolean(z);
                    this.mRemote.transact(17, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void setFold(String str, int i, String str2, boolean z) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeBoolean(z);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void setNotificationsEnabledWithImportanceLockForPackage(String str, int i, boolean z, int i2) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeBoolean(z);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_setNotificationsEnabledWithImportanceLockForPackage, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void setStowOption(String str, int i, int i2) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void switchNotificationChannel(String str, int i, String str2, String str3, boolean z, int i2) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeBoolean(z);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(16, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public boolean updateChannelSound(String str, int i, String str2, String str3) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readBoolean();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplusos.notification.INotificationCenter
            public void updateChannelVibration(String str, int i, String str2, boolean z) {
                Parcel parcelObtain = Parcel.obtain(asBinder());
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationCenter.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeBoolean(z);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, INotificationCenter.DESCRIPTOR);
        }

        public static INotificationCenter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(INotificationCenter.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof INotificationCenter)) ? new Proxy(iBinder) : (INotificationCenter) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(INotificationCenter.DESCRIPTOR);
            }
            switch (i) {
                case 1:
                    String string = parcel.readString();
                    int i3 = parcel.readInt();
                    String string2 = parcel.readString();
                    boolean z = parcel.readBoolean();
                    parcel.enforceNoDataAvail();
                    setFold(string, i3, string2, z);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    String string3 = parcel.readString();
                    int i4 = parcel.readInt();
                    String string4 = parcel.readString();
                    parcel.enforceNoDataAvail();
                    boolean zIsFold = isFold(string3, i4, string4);
                    parcel2.writeNoException();
                    parcel2.writeBoolean(zIsFold);
                    return true;
                case 3:
                    String string5 = parcel.readString();
                    int i5 = parcel.readInt();
                    String string6 = parcel.readString();
                    parcel.enforceNoDataAvail();
                    String channelName = getChannelName(string5, i5, string6);
                    parcel2.writeNoException();
                    parcel2.writeString(channelName);
                    return true;
                case 4:
                    String string7 = parcel.readString();
                    int i6 = parcel.readInt();
                    String string8 = parcel.readString();
                    parcel.enforceNoDataAvail();
                    boolean zCanShowBanner = canShowBanner(string7, i6, string8);
                    parcel2.writeNoException();
                    parcel2.writeBoolean(zCanShowBanner);
                    return true;
                case 5:
                    String string9 = parcel.readString();
                    int i7 = parcel.readInt();
                    String string10 = parcel.readString();
                    parcel.enforceNoDataAvail();
                    int lockscreenVisibility = getLockscreenVisibility(string9, i7, string10);
                    parcel2.writeNoException();
                    parcel2.writeInt(lockscreenVisibility);
                    return true;
                case 6:
                    String string11 = parcel.readString();
                    int i8 = parcel.readInt();
                    String string12 = parcel.readString();
                    parcel.enforceNoDataAvail();
                    boolean zIsChangeableFold = isChangeableFold(string11, i8, string12);
                    parcel2.writeNoException();
                    parcel2.writeBoolean(zIsChangeableFold);
                    return true;
                case 7:
                    List<String> supportNumberBadgePackages = getSupportNumberBadgePackages();
                    parcel2.writeNoException();
                    parcel2.writeStringList(supportNumberBadgePackages);
                    return true;
                case 8:
                    String string13 = parcel.readString();
                    int i9 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    boolean zAreNotificationsEnabledForPackage = areNotificationsEnabledForPackage(string13, i9);
                    parcel2.writeNoException();
                    parcel2.writeBoolean(zAreNotificationsEnabledForPackage);
                    return true;
                case 9:
                    String string14 = parcel.readString();
                    int i10 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    int badgeOption = getBadgeOption(string14, i10);
                    parcel2.writeNoException();
                    parcel2.writeInt(badgeOption);
                    return true;
                case 10:
                    String string15 = parcel.readString();
                    int i11 = parcel.readInt();
                    String string16 = parcel.readString();
                    String string17 = parcel.readString();
                    parcel.enforceNoDataAvail();
                    boolean zUpdateChannelSound = updateChannelSound(string15, i11, string16, string17);
                    parcel2.writeNoException();
                    parcel2.writeBoolean(zUpdateChannelSound);
                    return true;
                case 11:
                    String string18 = parcel.readString();
                    int i12 = parcel.readInt();
                    String string19 = parcel.readString();
                    boolean z2 = parcel.readBoolean();
                    parcel.enforceNoDataAvail();
                    updateChannelVibration(string18, i12, string19, z2);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    String string20 = parcel.readString();
                    int i13 = parcel.readInt();
                    int i14 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    setStowOption(string20, i13, i14);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    String string21 = parcel.readString();
                    int i15 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    int stowOption = getStowOption(string21, i15);
                    parcel2.writeNoException();
                    parcel2.writeInt(stowOption);
                    return true;
                case TRANSACTION_enableAllNotificationChannel /* 14 */:
                    String string22 = parcel.readString();
                    parcel.enforceNoDataAvail();
                    enableAllNotificationChannel(string22);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_disableAllNotificationChannel /* 15 */:
                    String string23 = parcel.readString();
                    parcel.enforceNoDataAvail();
                    disableAllNotificationChannel(string23);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    String string24 = parcel.readString();
                    int i16 = parcel.readInt();
                    String string25 = parcel.readString();
                    String string26 = parcel.readString();
                    boolean z3 = parcel.readBoolean();
                    int i17 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    switchNotificationChannel(string24, i16, string25, string26, z3, i17);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    String string27 = parcel.readString();
                    int i18 = parcel.readInt();
                    boolean z4 = parcel.readBoolean();
                    parcel.enforceNoDataAvail();
                    setChangedByUserForPackageState(string27, i18, z4);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setNotificationsEnabledWithImportanceLockForPackage /* 18 */:
                    String string28 = parcel.readString();
                    int i19 = parcel.readInt();
                    boolean z5 = parcel.readBoolean();
                    int i20 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    setNotificationsEnabledWithImportanceLockForPackage(string28, i19, z5, i20);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getVersion /* 19 */:
                    int version = getVersion();
                    parcel2.writeNoException();
                    parcel2.writeInt(version);
                    return true;
                case 20:
                    String string29 = parcel.readString();
                    Intent intent = (Intent) parcel.readTypedObject(Intent.CREATOR);
                    parcel.enforceNoDataAvail();
                    notifyBrodcast(string29, intent);
                    parcel2.writeNoException();
                    return true;
                case 21:
                    String string30 = parcel.readString();
                    UserHandle userHandle = (UserHandle) parcel.readTypedObject(UserHandle.CREATOR);
                    NotificationChannel notificationChannel = (NotificationChannel) parcel.readTypedObject(NotificationChannel.CREATOR);
                    int i21 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    notifyChannelOrGroupAdded(string30, userHandle, notificationChannel, i21);
                    parcel2.writeNoException();
                    return true;
                case 22:
                    String string31 = parcel.readString();
                    int i22 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    notifyPackageNotificationStateChanged(string31, i22);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    boolean areNotificationsEnabledForPackage(String str, int i);

    boolean canShowBanner(String str, int i, String str2);

    void disableAllNotificationChannel(String str);

    void enableAllNotificationChannel(String str);

    int getBadgeOption(String str, int i);

    String getChannelName(String str, int i, String str2);

    int getLockscreenVisibility(String str, int i, String str2);

    int getStowOption(String str, int i);

    List<String> getSupportNumberBadgePackages();

    int getVersion();

    boolean isChangeableFold(String str, int i, String str2);

    boolean isFold(String str, int i, String str2);

    void notifyBrodcast(String str, Intent intent);

    void notifyChannelOrGroupAdded(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i);

    void notifyPackageNotificationStateChanged(String str, int i);

    void setChangedByUserForPackageState(String str, int i, boolean z);

    void setFold(String str, int i, String str2, boolean z);

    void setNotificationsEnabledWithImportanceLockForPackage(String str, int i, boolean z, int i2);

    void setStowOption(String str, int i, int i2);

    void switchNotificationChannel(String str, int i, String str2, String str3, boolean z, int i2);

    boolean updateChannelSound(String str, int i, String str2, String str3);

    void updateChannelVibration(String str, int i, String str2, boolean z);
}
