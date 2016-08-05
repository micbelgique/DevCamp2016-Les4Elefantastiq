package com.les4elefantastiq.les4elefantcowork.managers;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.les4elefantastiq.les4elefantcowork.dataaccess.CoworkspaceDataAccess;
import com.les4elefantastiq.les4elefantcowork.models.Coworker;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;

import java.util.List;

public class CoworkspacesManager {

    // -------------- Objects, Variables -------------- //

    // ---------------- Public Methods ---------------- //

    @Nullable
    @WorkerThread
    public static Coworkspace getCoworkspace(String coworkspaceId) {
        List<Coworkspace> coworkspaces = getCoworkspaces();

        if (coworkspaces != null)
            for (Coworkspace coworkspace : coworkspaces)
                if (coworkspace.id.equals(coworkspaceId))
                    return coworkspace;

        return null;
    }

    @Nullable
    @WorkerThread
    public static List<Coworkspace> getCoworkspaces() {
        return CoworkspaceDataAccess.getAllCoworkspace();
    }

    @Nullable
    @WorkerThread
    public static List<Coworker> getCoworkers(Coworkspace coworkspace) {
        return CoworkspaceDataAccess.getCoworkers(coworkspace);
    }

    @Nullable
    @WorkerThread
    public static void checkIn(Coworkspace coworkspace, Coworker coworker) {
        CoworkspaceDataAccess.setCheckIn(coworkspace, coworker, true);
    }

    @Nullable
    @WorkerThread
    public static void checkOut(Coworkspace coworkspace, Coworker coworker) {
        CoworkspaceDataAccess.setCheckIn(coworkspace, coworker, false);
    }


    // ---------------- Private Methods --------------- //

}