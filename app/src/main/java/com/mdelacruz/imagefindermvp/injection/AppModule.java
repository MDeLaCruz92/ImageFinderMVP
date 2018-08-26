package com.mdelacruz.imagefindermvp.injection;

import android.app.Application;
import android.content.Context;

import com.mdelacruz.imagefindermvp.api.ImageService;
import com.mdelacruz.imagefindermvp.ui.presenter.MainFragmentPresenter;
import com.mdelacruz.imagefindermvp.ui.presenter.PopUpDialogFragmentPresenter;
import com.mdelacruz.imagefindermvp.util.AppSchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AppModule {
  Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  @ForApplication
  Context provideContext() {
    return application;
  }

  @Provides
  @Singleton
  MainFragmentPresenter providesMainFragmentPresenter(ImageService service,
                                                      CompositeDisposable disposable,
                                                      AppSchedulerProvider scheduler) {

    return new MainFragmentPresenter(service, disposable, scheduler);
  }

  @Provides
  @Singleton
  PopUpDialogFragmentPresenter providesPopupDialogFragmentPresenter(ImageService service,
                                                                    CompositeDisposable disposable,
                                                                    AppSchedulerProvider scheduler) {

    return new PopUpDialogFragmentPresenter(service, disposable, scheduler);
  }

  @Provides
  @Singleton
  CompositeDisposable provideCompositeDisposable() {
    return new CompositeDisposable();
  }

  @Provides
  @Singleton
  AppSchedulerProvider provideSchedulerProvider() {
    return new AppSchedulerProvider();
  }
}
