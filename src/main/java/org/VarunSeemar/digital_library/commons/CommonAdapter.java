package org.VarunSeemar.digital_library.commons;

import java.util.List;

public interface CommonAdapter <E,M>{
    M save(E e);
    M update(E e);
    List<M> getAll();
}
