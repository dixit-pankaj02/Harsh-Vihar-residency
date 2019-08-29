package com.example.harshviharresidency.preferences;

public class Provider {
  private final static IStorage storage;

  static {
    storage = IStorage.Factory.get();
  }

  public static IStorage getStorage() {
    return storage;
  }
}
