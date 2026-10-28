package org.example.interfaces;

import org.example.entity.Cargo;

public interface Loadable {
    void load(Cargo cargo);

    Cargo unload();

    Cargo getCargo();
}
