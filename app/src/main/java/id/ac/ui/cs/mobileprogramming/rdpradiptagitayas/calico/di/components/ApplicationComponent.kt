package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.di.components

import dagger.Component
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.di.modules.ApplicationModule

import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

}