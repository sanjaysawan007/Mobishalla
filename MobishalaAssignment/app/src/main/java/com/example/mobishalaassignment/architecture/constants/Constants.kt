package com.example.mobishalaassignment.architecture.constants

class Constants {


    object Injection {
        const val API_CURRENT_URL = "currentURL"
        const val API_DEVELOPMENT_URL = "developmentURL"
        const val API_TESTING_URL = "testingURL"
        const val API_LIVE_URL = "liveURL"
        const val API_PRODUCTION_URL = "productionURL"

        /**
         * Network Class v1 constants
         */
        const val RETROFIT_V1 = "RETROFIT_V1"
        const val GSON_V1 = "GSON_V1"
        const val ENDPOINT_V1 = "GSON_V1"
        const val OKHHTP_CACHE_V1 = "OKHTTP_CACHE_V1"
        const val OKHHTP_CLIENT_V1 = "OKHTTP_CLIENT_V1"
        const val INTERCEPTOR_HEADER_V1 = "INTERCEPTOR_HEADER_V1"
        const val INTERCEPTOR_LOGGING_V1 = "INTERCEPTOR_LOGGING_V1"
        const val INTERCEPTOR_RESPONSE_V1 = "INTERCEPTOR_RESPONSE_V1"
    }

    object API {
        object URL {

            //Development URL
            const val URL_DEVELOPMENT = "https://api.themoviedb.org/3/"

            const val URL_TESTING = ""
            const val URL_LIVE = ""
            const val URL_PRODUCTION = ""

        }
    }

    companion object APP {
        const val API_KEY = "9856f68b49d497add082a6673c102098"
        const val IMAGE_BASE_URL ="https://image.tmdb.org/t/p/w500/"

    }



}



