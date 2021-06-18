package com.iswan.main.core.ui

//@AppScope
//class ViewModelFactory @Inject constructor(
////    private val tourismUseCase: TourismUseCase
//    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
//): ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
//            modelClass.isAssignableFrom(it.key)
//        }?.value ?: throw IllegalArgumentException("unknown model class")
//        return creator.get() as T
//    }

//    companion object {
//        @Volatile
//        private var instance: `ViewModelFactory.bak`? = null
//
//        fun getInstance(context: Context): `ViewModelFactory.bak` =
//            instance
//                ?: synchronized(this) {
//                instance
//                    ?: `ViewModelFactory.bak`(
//                        `Injection.bak`.provideTourismUseCase(
//                            context
//                        )
//                    )
//            }
//    }

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T =
//        when {
//            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
//                HomeViewModel(tourismUseCase) as T
//            }
//            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
//                FavoriteViewModel(tourismUseCase) as T
//            }
//            modelClass.isAssignableFrom(DetailTourismViewModel::class.java) -> {
//                DetailTourismViewModel(tourismUseCase) as T
//            }
//            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
//        }
//}