package fr.miage.klein.BusinessLogic.ParkAccess;

import fr.miage.klein.Controller.IDatabaseController;

public abstract class ParkAccess {
    protected IDatabaseController db;

    public ParkAccess(IDatabaseController db) {
        this.db = db;
    }

    public abstract boolean validate();
}

