package com.mdelacruz.imagefindermvp;

import android.app.Application;

import com.mdelacruz.imagefindermvp.injection.AppComponent;
import com.mdelacruz.imagefindermvp.injection.AppModule;
import com.mdelacruz.imagefindermvp.injection.DaggerAppComponent;

public class App extends Application {
  private static App instance;

  public static App getInstance() {
    return instance;
  }

  protected AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
    appComponent.inject(this);
  }


  public AppComponent getAppComponent() {
    return appComponent;
  }
}
