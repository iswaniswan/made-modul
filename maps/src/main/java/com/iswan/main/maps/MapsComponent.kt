package com.iswan.main.maps

import android.content.Context
import com.dicoding.tourismapp.di.MapsDependencies
import dagger.BindsInstance
import dagger.Component


@Component(dependencies = [MapsDependencies::class])
interface MapsComponent {

    fun inject(activity: MapsActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun mapsDependencies(mapsDependencies: MapsDependencies): Builder
        fun build(): MapsComponent
    }


}