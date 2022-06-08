package com.sofka.hardware.collection;

import java.util.Objects;

public class Provider {

    private String idProvider;

    private String nameProvider;

    public Provider(String idProvider,
                    String nameProvider) {
        this.idProvider = idProvider;
        this.nameProvider = nameProvider;
    }

    public String getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(String idProvider) {
        this.idProvider = idProvider;
    }

    public String getNameProvider() {
        return nameProvider;
    }

    public void setNameProvider(String nameProvider) {
        this.nameProvider = nameProvider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(idProvider, provider.idProvider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProvider);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "idProvider='" + idProvider + '\'' +
                ", nameProvider='" + nameProvider + '\'' +
                '}';
    }
}
